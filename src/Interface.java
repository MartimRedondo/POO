import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Interface {

    private Vintage model;
    private Parse parse;

    private Scanner sc;

    public Interface(){
        this.model = new Vintage();
        sc = new Scanner(System.in);
        this.parse = new Parse ();
        model = parse.parse();
    }
    public void run(){
        Menu menu = new Menu(new String[]{
                "Entrar como utilizador",
                "Entrar como admin"
        });

        // menu.setPreCondition(3,()-> this.model.getName().equals(""));
        menu.setHandler(1,this::userView);
        menu.setHandler(2,this::adminView);
        menu.run();

    }

    private void adminView(){
        Menu stateMenu = new Menu(new String[]{
                "Adicionar Transportadora",
                "Time Machine"});
        stateMenu.setHandler(1, this::adicionarTransportadora);
        stateMenu.setHandler(2, this::timeMachine);
        stateMenu.run();
    }

    private void adicionarTransportadora() {
        System.out.println("Preencha os seguintes campos: ");
        System.out.print("Nome da transportadora: ");
        String transporte_name = sc.nextLine();
        System.out.println("Margem de lucro: ");
        double margem_de_lucro = Double.parseDouble(sc.nextLine());
        System.out.println(margem_de_lucro);
        System.out.println("Regras a ter em conta para escrever a fórmula da transportadora:");
        System.out.println("1º Há 3 tipos de variáveis possíveis, ValorBase (v), Impostos (i) e MargemDeLucro (l), qualquer outra variável não será aceite;");
        System.out.println("2º A fórmula só pode conter somas(+), subtrações(-), multiplicações(*), divisões(/) e terá atenção à prioridade dos parentêses;");
        System.out.println("3º Para números não inteiros, usar o . em vez da , .");
        System.out.println("");
        System.out.println("Depois das regras apresentadas, espode escrever a fórmula seguindo as diretrizes acima.");
        System.out.println("É permitido errar tres vezes ao digitar a fórmula, mais do que isso será usada um fórmula predefenida.");
        int i = 3;
        String formula = null;
        int check_invalido = 0;
        while (i != 0){
            check_invalido = 0;
            formula = sc.nextLine();
            if (formula.contains(",")) {
                System.out.println("A fórmula não pode usar vírgulas para números não inteiros");
                i--;
                check_invalido = -1;
            }
            else if (!formula.matches("[\\d+\\-*/.()vil]+")){
                System.out.println("A fórmula contem variáveis ou sinais indesejadas, só podem ser v, i, l, +, -, *, / e os parenteses");
                i--;
                check_invalido = -1;
            }
            else {i = 0;}

            if ( i!=0 ) System.out.println("Reescreva a fórmula seguindo as regras tem " + i + " opções restantes");
        }
        if (check_invalido == -1) formula = "(v*l*(1+i))*0.9";
        Transportadora transporte = new Transportadora(transporte_name, formula, margem_de_lucro, 2, 4, 7);
        this.model.add_Deliver(transporte);
        //por alguem motivo tudo está no MAP, mas quando tento aceder á key pelo getName() ela diz que está null, mas consigo-a usar como key :)
        this.model.writeFIle(transporte);
        this.adminView();
    }


    private void timeMachine() {
        LocalDate help = LocalDate.now();
        System.out.println("Quanto tempo pretende avançar, em dias, ou, se preferir, para que data (AAAA/MM/DD)");
        String avaliar = sc.nextLine();
        if (avaliar.contains("/")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate avanco = LocalDate.parse(avaliar, formatter);
            for (Encomenda objeto : this.model.getEncomendas().values()) {
                objeto.timechange((int) ChronoUnit.DAYS.between(help, avanco), avanco);
            }
        }
        else {
            int dias_avançados = Integer.parseInt(sc.nextLine());
            for (Encomenda objeto : this.model.getEncomendas().values()) {
                objeto.timechange(dias_avançados, help.plusDays(dias_avançados));
            }
        }
        this.afterTimeMachine();
    }
    private void afterTimeMachine() {
        Menu stateMenu = new Menu(new String[]{
                "Desejo mudar os valores base, de imposto e de margem de lucro de uma transportadora;",
                "Pretendo manter os valores base, de imposto ou de margem de lucro como se encontram;"});
        stateMenu.setHandler(1, this::mudancaTransporte);
        stateMenu.setHandler(2, this::adminView);
        stateMenu.run();
    }
    private void mudancaTransporte() {
        System.out.println("Digite a transportadora que deseja mudar.");
        String transportadora = sc.nextLine();
        Transportadora transporte = this.model.getDeliver().get(transportadora);
        System.out.println("Os valores dessa transportadora encontram-se da seguinte maneira:");
        System.out.println("Encomendas pequenas: " + transporte.getPeq());
        System.out.println("Encomendas medias: " + transporte.getMed());
        System.out.println("Encomendas grandes: "+ transporte.getGrande());
        System.out.println("Margem de Lucro: " + transporte.getMargem_de_lucro());
        System.out.println("Escreva pela mesma que apareceu os novos valores que pretende atribuir");
        double peq = Double.parseDouble(sc.nextLine());
        double med = Double.parseDouble(sc.nextLine());
        double grande = Double.parseDouble(sc.nextLine());
        double mgl = Double.parseDouble(sc.nextLine());
        transporte.setPeq(peq);
        transporte.setMed(med);
        transporte.setGrande(grande);
        transporte.setMargem_de_lucro(mgl);
        this.adminView();
    }


    private void userView(){
        Menu stateMenu = new Menu(new String[]{
                "Criar utilizador",
                "Iniciar sessão"});
        stateMenu.setHandler(1, this::criarUser);
        stateMenu.setHandler(2,this::entrarUser);
        stateMenu.run();
    }

    private void criarUser() {
        System.out.println("Preencha os seguintes campos");
        System.out.print("Digite o seu e-mail: ");
        String email = sc.nextLine();

        System.out.print("Digite o seu nome: ");
        String nome = sc.nextLine();
        String nif;
        while (true) {
            System.out.print("Digite o seu NIF: ");
            nif = sc.nextLine();
            if (nif.length() != 9) {
                System.out.println("O NIF deve ter 9 dígitos.");
            } else {
                try {
                    Integer.parseInt(nif);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("O NIF deve ser um número inteiro válido.");
                }
            }
        }

        System.out.print("Digite a sua morada: ");
        String morada = sc.nextLine();
        if (nome.isEmpty() || morada.isEmpty() || morada.isEmpty()) {
            System.out.println("Preencha todos os campos corretamente.");
            return;
        }
        System.out.println("Digite a sua password");
        String code = sc.nextLine();
        System.out.println("Por motivos de seguranca digite novamente a sua password");
        String code2 = sc.nextLine();
        if (!code.equals(code2)) {
            do {
                System.out.println("As palavras pass nao coincidem");
                System.out.println("Digite a sua password");
                code = sc.nextLine();
                System.out.println("Por motivos de seguranca digite novamente a sua password");
                code2 = sc.nextLine();
                if (code.equals(code2)) {
                    break;
                }
            } while (!code2.equals(code));
        }
        Vendedor vend = new Vendedor(code, email, nome, morada, nif, new HashMap<>(), new HashMap<>());
        Comprador comp = new Comprador(code, email, nome, morada, nif, new HashMap<>());
        this.model.add_Poor(vend);
        this.model.add_Rich(comp);
        this.model.writeFIle(comp);
        this.model.writeFIle(vend);
        this.userView();
    }

    private void entrarUser(){
        Menu menu = new Menu(new String[]{
                "Entrar como comprador",
                "Entrar como vendedor"
        });

        // menu.setPreCondition(3,()-> this.model.getName().equals(""));
        menu.setHandler(1,this::CompradorView);
        menu.setHandler(2,this::VendedorView);
        menu.run();
    }
    private void CompradorView () {
        String email;
        do {
            System.out.println("Digite o seu e-mail para iniciar sessão");
            email = sc.nextLine();
            if (!model.getRich().containsKey(email)) {
                System.out.println("Email invalido. Reescreva corretamente o seu email");
            }
            else {
                Comprador comprador = model.getRich().get(email);
                String code;
                System.out.println("Digite a sua senha para iniciar sessão");
                code = sc.nextLine();
                if (code.equals(comprador.getCode())) this.sessaoIniciada_Comprador();
                else {
                    email = "";
                }
            }
        } while (!model.getRich().containsKey(email));
    }
    private void VendedorView () {
        String email;
        do {
            System.out.println("Digite o seu e-mail para iniciar sessão");
            email = sc.nextLine();
            if (!model.getPoor().containsKey(email)) {
                System.out.println("Email invalido. Reescreva corretamente o seu email");
            }
            else {
                Vendedor vendedor = model.getPoor().get(email);
                String code;
                System.out.println("Digite a sua senha para iniciar sessão");
                code = sc.nextLine();
                if (code.equals(vendedor.getCode())) this.sessaoIniciada_Vendedor();
                else {
                    email = "";
                }
            }
        }
        while (!model.getPoor().containsKey(email));
    }

    private void sessaoIniciada_Vendedor(){
        Menu stateMenu = new Menu(new String[]{
                "Comprar Artigo",
                "Vender Artigo"});
    }

    private void sessaoIniciada_Comprador(){
        Menu stateMenu = new Menu(new String[]{
                "Comprar Artigo",
                "Vender Artigo"});
    }

    private void encomendafinalizada (Encomenda encomenda) {
        encomenda.comprador.addArtigos(encomenda.getEncomendados());
        addArtigos_Vendidos(encomenda);
        model.removeArtigos(encomenda);
        model.add_Encomendas(encomenda);
    }

    public void addArtigos_Vendidos (Encomenda encomenda) {
        for (Artigos artigo : encomenda.getEncomendados().values()) {
            Vendedor vendedor = artigo.getVendedor();
            vendedor.addArtigo_Vendido(artigo);
        }
    }


}