import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
public class Vintage implements Serializable { //ajudar a fazer a máquina do tempo e a trabalhar com informação entre classes
    LocalDate time = LocalDate.now();

    //Criação de um "List" só que com o uso de Map que permite usar classes como chave ou valor
    private Map<String, Comprador> rich;
    private Map<String, Vendedor> poor;
    private Map<String, Transportadora> deliver;
    private Map<String, Artigos> stock;
    private Map<String, Encomenda> encomendas;

    //1º forma de criar
    public Vintage() {
        this.rich = new HashMap<>();
        this.poor = new HashMap<>();
        this.deliver = new HashMap<>();
        this.stock = new HashMap<>();
        this.encomendas = new HashMap<>();
        //  this.carrinho = new HashMap<>();
    }

    //2º forma de criar (clone)
    public Vintage(Map<String, Comprador> rich, Map<String, Vendedor> poor, Map<String, Transportadora> deliver, Map<String, Artigos> artigos,
                   Map<String, Encomenda> encomendas) {

        this.rich = rich.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, k -> k.getValue().clone()));
        this.poor = poor.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, k -> k.getValue().clone()));

        this.stock = artigos.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, k -> k.getValue().clone()));

        this.deliver = deliver.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, k -> k.getValue().clone()));
        this.encomendas = encomendas.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, k -> k.getValue().clone()));
    }

    // 3º forma de criar (getters)
    public Vintage(Vintage aux) {
        this.rich = aux.getRich();
        this.poor = aux.getPoor();
        this.stock = aux.getStock();
        this.deliver = aux.getDeliver();
        this.encomendas = aux.getEncomendas();
        this.time = aux.getTime();
    }

    public Map<String, Transportadora> getDeliver() {
        return this.deliver.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, k -> k.getValue().clone()));

    }

    public Map<String, Comprador> getRich() {
        return this.rich.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, k -> k.getValue().clone()));
    }



    public Map<String, Vendedor> getPoor (){
        return this.poor.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, k-> k.getValue().clone()));
    }

    public Map<String, Artigos> getStock() {
        return this.stock.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, k -> k.getValue().clone()));
    }

    public Map<String, Encomenda> getEncomendas() {
        return this.encomendas.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, k -> k.getValue().clone()));
    }


    public void setDeliver(Map<String, Transportadora> deliver) {
        this.deliver = deliver.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, k -> k.getValue().clone()));
    }

    public void setRich(Map<String, Comprador> rich) {
        this.rich = rich.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, k -> k.getValue().clone()));
    }

    public void setPoor(Map<String, Vendedor> poor) {
        this.poor = poor.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, k -> k.getValue().clone()));
    }

    public void setStock(Map<String, Artigos> stock) {
        this.stock = stock.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, k -> k.getValue().clone()));
    }

    public void setEncomendas(Map<String, Encomenda> encomendas) {
        this.encomendas = encomendas.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, k -> k.getValue().clone()));
    }

    public void add_Rich(Comprador rich) {
        this.rich.put(rich.getEmail(), rich);
    }

    public void add_Poor(Vendedor poor) {
        this.poor.put(poor.getEmail(), poor);
    }

    public void add_Stock(Artigos artigo) {
        this.stock.put(artigo.getCode(), artigo);
    }

    public void add_Encomendas(Encomenda encomenda) {
        this.encomendas.put(encomenda.getCode(), encomenda);
    }

    public void add_Deliver(Transportadora transportadora) {
        this.deliver.put(transportadora.getName(), transportadora);
    }

    public void removeArtigos(Encomenda encomenda) {
        for (Artigos artigo : encomenda.getEncomendados().values()) {
            this.stock.remove(artigo.getCode());
        }
    }

    public LocalDate getTime() {
        return this.time;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vintage vintage = (Vintage) o;
        return (this.rich.equals(vintage.getRich()) &&
                this.poor.equals(vintage.getPoor()) &&
                this.stock.equals(vintage.getStock()) &&
                this.deliver.equals(vintage.getDeliver()) &&
                this.encomendas.equals(vintage.getEncomendas()));
    }

    public LocalDate advancedata(int days) {
        this.time = this.time.plusDays(days);
        return this.time;
    }

    public Vintage clone() throws CloneNotSupportedException {
        Vintage clone = (Vintage) super.clone();
        return new Vintage(this);
    }

    public Vendedor encontrarVendedorComMaiorFaturamento(LocalDate data, LocalDate dataFim) {
        Vendedor vendedorComMaiorFaturamento = null;
        double maiorFaturamento = 0.0;

        for (Vendedor vendedor : poor.values()) {
            double faturamento = 0.0;

            for (Artigos artigo : vendedor.getArtigos_Vendidos().values()) {
                if (artigo.getData_Venda().isAfter(data) && artigo.getData_Venda().isBefore(dataFim)) {
                    String subclass = determinarSubclasse(artigo);
                    switch (subclass) {
                        case "Sapatilhas" -> {
                            Sapatilhas sapatilhasCast = (Sapatilhas) artigo;
                            faturamento += sapatilhasCast.getPreco();
                        }
                        case "Malas" -> {
                            Malas malaCast = (Malas) artigo;
                            faturamento += malaCast.getPreco();
                        }
                        case "Tshirts" -> {
                            Tshirts tshirtCast = (Tshirts) artigo;
                            faturamento += tshirtCast.getPreco();
                        }
                    }
                }
            }
            if (faturamento > maiorFaturamento) {
                maiorFaturamento = faturamento;
                vendedorComMaiorFaturamento = vendedor;
            }
        }
        return vendedorComMaiorFaturamento;
    }

    public List<Vendedor> ordenarVendedoresPorFaturamento(LocalDate dataInicio, LocalDate dataFim) {
        // Cria um mapa para armazenar o faturamento de cada vendedor
        Map<Vendedor, Double> faturamentoPorVendedor = new HashMap<>();

        // Calcula o faturamento de cada vendedor no período de tempo especificado
        for (Vendedor vendedor : poor.values()) {
            double faturamento = 0.0;

            for (Artigos artigo : vendedor.getArtigos_Vendidos().values()) {
                LocalDate dataVenda = artigo.getData_Venda();

                if (dataVenda != null && !dataVenda.isBefore(dataInicio) && !dataVenda.isAfter(dataFim)) {
                    String subclass = determinarSubclasse(artigo);
                    switch (subclass) {
                        case "Sapatilhas" -> {
                            Sapatilhas sapatilhasCast = (Sapatilhas) artigo;
                            faturamento += sapatilhasCast.getPreco();
                        }
                        case "Malas" -> {
                            Malas malaCast = (Malas) artigo;
                            faturamento += malaCast.getPreco();
                        }
                        case "Tshirts" -> {
                            Tshirts tshirtCast = (Tshirts) artigo;
                            faturamento += tshirtCast.getPreco();
                        }
                    }
                }
            }

            faturamentoPorVendedor.put(vendedor, faturamento);
        }

        // Ordena os vendedores pelo faturamento, do maior para o menor
        List<Vendedor> vendedoresOrdenados = faturamentoPorVendedor.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return vendedoresOrdenados;
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

    public ArrayList<Encomenda> EncomendasVendedor(Vendedor vendedor) {
        double precoTotal = 0;
        ArrayList<Encomenda> encomendas = new ArrayList<>();
        for (Encomenda encomenda : this.encomendas.values()) {
            for (Artigos artigo : encomenda.getEncomendados().values())
                if (vendedor.equals(artigo.getVendedor())) {
                    encomendas.add(encomenda);
                    break;
                }
        }
        return encomendas;
    }

    public void TransportadoraComMaiorVolumeFaturacao() {
        double max = 0.0;
        double possivel_max = 0.0;
        for (Transportadora transportadora : this.deliver.values()) {
            for (Encomenda encomenda : transportadora.getEncomendas().values()) {
                possivel_max = encomenda.preco_transporte;
            }
            if (possivel_max > max) {
                max = possivel_max;
            }
            possivel_max = 0.0;
        }

    }

    public double calcularPrecoTotalEncomendas() {
        double precoTotal = 0;
        for (Encomenda encomenda : this.encomendas.values()) {
            precoTotal += encomenda.getPreco_final();
        }
        return precoTotal;
    }

    public void writeFIle(Object objeto) {
        try {
            FileOutputStream fileOut = new FileOutputStream("data.obj");
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);

            objOut.writeObject(objeto);

            objOut.close();
            fileOut.close();

            System.out.println("Objeto escrito com sucesso no arquivo data.obj");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
