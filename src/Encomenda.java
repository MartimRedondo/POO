import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Encomenda {
    private String code;
    int dimensao;
    double preco_final;
    double preco_transporte;
    Comprador comprador;
    EncomendaEstado estado;
    LocalDate Date_Criacao;
    LocalDate Date_Recebida;
    LocalDate Date_Devolucao;
    Boolean Devolucao;
    Transportadora transportadora;
    private Map<String, Artigos> encomendados;
    private Fatura fatura;

    public Encomenda(int dimensao, double preco_final, double preco_transporte, Comprador comprador,
                     EncomendaEstado estado, LocalDate Date_Criacao, LocalDate Date_criacao, LocalDate Date_devolucao, Transportadora transportadora,
                     Boolean devolucao, Map<String, Artigos> encomendados, Fatura fatura, String code) {
        this.code = code;
        this.dimensao = dimensao;
        this.preco_final = preco_final;
        this.preco_transporte = preco_transporte;
        this.comprador = comprador;
        this.estado = estado;
        this.Date_Criacao = Date_Criacao;
        this.Date_Recebida = Date_criacao;
        this.Date_Devolucao = Date_devolucao;
        this.transportadora = transportadora;
        this.Devolucao = devolucao;
        this.encomendados = encomendados;
        this.fatura = fatura;
    }

    public Encomenda(Encomenda encomenda) {
        this.code = "";
        this.dimensao = 0;
        this.preco_final = 0.0;
        this.preco_transporte = 2.0;
        this.comprador = new Comprador();
        this.estado = new EncomendaEstado();
        this.Date_Criacao = null;
        this.Date_Recebida = null;
        this.Date_Devolucao = null;
        this.transportadora = new Transportadora();
        this.Devolucao = Boolean.FALSE;
        this.encomendados = new HashMap<>();
        this.fatura = new Fatura();
    }
    public String getCode(){
        return this.code;
    }
    public Map<String, Artigos> getEncomendados() {
        return new HashMap<>(this.encomendados);
    }

    public int getDimensao() {
        return this.dimensao;
    }
    public double getPreco_transporte() {
        return this.preco_transporte;
    }

    public double getPreco_final() {
        return this.preco_final;
    }
    public Comprador getComprador() {
        return new Comprador(this.comprador);
    }

    public EncomendaEstado getEstado() {
        return this.estado;
    }
    public LocalDate getDate_Criacao() {
        return this.Date_Criacao;
    }

    public LocalDate getDate_Recebida() {
        return this.Date_Recebida;
    }
    public Transportadora getTransportadora() {
        return new Transportadora(this.transportadora);
    }

    public Boolean getDevolucao() {
        return this.Devolucao;
    }

    public LocalDate getDate_Devolucao() {
        return this.Date_Devolucao;
    }
    public Fatura getFatura() {
        return new Fatura(this.fatura);
    }

    public void setCode(){
        this.code = GeradorCodigo.gerarCodigo();
    }
    public void setEncomendados(Map<String, Artigos> encomendados) {
        this.encomendados = encomendados.entrySet()
                .stream()
                .collect(Collectors.toMap(k-> k.getKey(), k-> k.getValue().clone()));;
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }
    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }
    public void setPreco_transporte(double preco_transporte) {
        this.preco_transporte = preco_transporte;
    }
    public void setPreco_final(double preco_final) {
        this.preco_final = preco_final;
    }

    public void setEstado(EncomendaEstado estado) {
        this.estado = estado;
    }

    public void setDate_Recebida(LocalDate Date_criacao) {
        this.Date_Recebida = Date_criacao;
    }
    public void setTransportadora(Transportadora transportadora) { this.transportadora = transportadora; }

    public void setDate_Devolucao(LocalDate Date_devolucao) {
        this.Date_Devolucao = Date_devolucao;
    }
    public void setFatura (Fatura fatura) {
        this.fatura = fatura;
    }

    public void auxsetDate_criacao() {
        LocalDate Date_criacao = LocalDate.now();
        setDate_Recebida(Date_criacao);
        auxsetDate_devolucao(Date_Devolucao);
    }

    public void auxsetDate_devolucao(LocalDate Date_criacao) {
        LocalDate Date_devolucao = Date_criacao.plusDays(2);
        setDate_Devolucao(Date_devolucao);
    }

    public void setDevolucao(Boolean devolucao) {
        this.Devolucao = devolucao;
    }

    void add_Artigo(Artigos artigo) {
        this.dimensao += 1;
        this.encomendados.put(artigo.getCode(), artigo);
        define_Preco_final(artigo, Boolean.TRUE);
    }

    void remove_Artigo(String code) {
        this.dimensao -= 1;
        this.encomendados.remove(code);
        define_Preco_final(encomendados.get(code), Boolean.FALSE);
    }

    void devolucao(Encomenda encomenda) {
        if (encomenda.Date_Devolucao.compareTo(LocalDate.now()) > 0) {
            System.out.println("Já não é possível devolver a encomenda, pois passaram mais de 48 horas");
        } else {
            System.out.println("Contacte a transportadora para proseguir com a devolução");
            setDevolucao(Boolean.FALSE);
        }
    }

    void define_Preco_final(Artigos artigo, Boolean V) {
        String estado = artigo.getEstado();
        if (estado.equalsIgnoreCase("novo")) {
            this.preco_final += 0.5;
        } else {
            this.preco_final += 0.25;
        }
        String subclass = determinarSubclasse(artigo);
        if (subclass.equals("Tshirts")) {
            Tshirts tshirtCast = (Tshirts) artigo;
            if (V) {
                this.preco_final += tshirtCast.getPreco();
            } else {
                this.preco_final -= tshirtCast.getPreco();
            }
        }
        else if (subclass.equals("Malas")) {
            Malas malaCast = (Malas) artigo;
            if (V) {
                this.preco_final += malaCast.getPreco();
            } else {
                this.preco_final -= malaCast.getPreco();
            }
        }
        else if (subclass.equals("Sapatilhas")) {
            Sapatilhas sapatilhasCast = (Sapatilhas) artigo;
            if (V) {
                this.preco_final += sapatilhasCast.getPreco();
            } else {
                this.preco_final -= sapatilhasCast.getPreco();
            }
        }
    }

    public String determinarSubclasse(Artigos artigo) {
        if (artigo instanceof Sapatilhas) {
            return "Sapatilhas";
        } else if (artigo instanceof Malas) {
            return "Malas";
        } else if (artigo instanceof Tshirts) {
            return "Tshirts";
        } else {
            return "Artigo desconhecido";
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Encomenda encomenda = (Encomenda) o;
        return (this.code.equals(encomenda.getCode()) &&
                this.preco_final == encomenda.getPreco_final() &&
                this.preco_transporte == encomenda.getPreco_transporte() &&
                this.dimensao == encomenda.getDimensao() &&
                this.comprador == encomenda.getComprador() &&
                this.estado == encomenda.getEstado() &&
                this.Date_Recebida.equals(encomenda.getDate_Recebida()) &&
                this.Date_Devolucao.equals(encomenda.getDate_Devolucao()) &&
                this.Devolucao.equals(encomenda.getDevolucao()) &&
                encomenda.getEncomendados().equals(this.encomendados) &&
                this.fatura == encomenda.getFatura());
    }

    public Encomenda clone () {
        return new Encomenda (this);
    }

    public void timechange (int time_gap, LocalDate date){
        GeradorCodigo codigo = new GeradorCodigo();
        int estado = this.estado.getI_Estado();
        this.estado.setI_Estado(estado + time_gap);
        if (this.estado.getI_Estado() >= 3) {
            int emissao = time_gap -(3 - estado);
            this.fatura.setCode(codigo.gerarCodigo());
            this.fatura.setNIF(this.comprador.getNIF());
            this.fatura.setUser(this.comprador.getNome());
            this.fatura.setPreco_transporte(this.preco_transporte);
            this.fatura.setTransportadora(transportadora.getName());
            //this.fatura.setPreco();
            this.fatura.setPreco_final(this.preco_final);
            this.fatura.setData_emissao(date.minusDays(emissao));
        }
    }

}