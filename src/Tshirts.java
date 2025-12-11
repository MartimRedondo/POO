//sem warnings que possam causar problemas

import java.time.LocalDate;

public class Tshirts extends Artigos {
    private double preco;
    private String tamanho;
    private String padrao;

    public Tshirts () {
        this.tamanho = "";
        this.padrao = "";
        this.preco = 0;
    }
    public Tshirts(String estado, String donos, String descricao, String marca, String code, String preco_base,
                   String transportadora, LocalDate data_venda, String tamanho, String padrao, Vendedor vendedor, double preco) {
        super (estado, donos, descricao, marca, code, preco_base, transportadora, data_venda, vendedor);
        this.preco = preco;
        this.tamanho = tamanho;
        this.padrao = padrao;

    }

    public Tshirts(Tshirts tshirts) {
        this.tamanho = tshirts.getTamanho();
        this.padrao = tshirts.getPadrao();
    }

    public String getTamanho (){
        return this.tamanho;
    }
    public String getPadrao (){
        return this.padrao;
    }
    public double getPreco (){
        return this.preco;
    }

    public void setTamanho(String tamanho){
        this.tamanho = tamanho;
    }
    public void setPadrao(String padrao){
        this.padrao = padrao;
    }
    public void setPreco(double preco){
        this.preco = preco;
    }
    void setprecoaux () {
        double preco_base = getPreco_base();
        double preco = preco_base;
        int ano = LocalDate.now().getYear();
        int estado = estadoToInt(getEstado());
        if (this.padrao.equals("liso") || estado == 1) {
            setPreco (preco_base);
        }
        else {
            preco = preco / 2;
            setPreco (preco);
        }
    }
    Boolean tamanho_correto (String tamanho) {
        return (tamanho.equals("S") || tamanho.equals("M") || tamanho.equals("L") || tamanho.equals("XL"));
    }
    Boolean padrao_correto (String padrao) {
        return (padrao.equals("liso") || padrao.equals("riscas") || padrao.equals("palmeiras"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tshirts tshirt = (Tshirts) o;
        return (this.tamanho.equals(tshirt.getTamanho()) &&
                this.padrao.equals(tshirt.getPadrao()));
    }
    public Tshirts clone() {
        return new Tshirts(this);
    }

}
