//sem warnings que possam causar problemas

import java.time.LocalDate;

public class Malas extends Artigos{
    private int dimensao;
    private double desconto;
    private String material;
    private int ano;
    private String premium;
    double preco;

    public Malas() {
        this.dimensao = -1;
        this.material = "";
        this.ano = -1;
        this.premium = "";
        this.desconto = -1;
        this.preco = 0;

    }
    public Malas(String estado, String donos, String descricao, String marca, String code, String preco_base, String transportadora,
                 int dimensao, String material, int lancamento, String premium, double desconto, LocalDate data_venda, Vendedor vendedor) {
        super (estado, donos, descricao, marca, code, preco_base, transportadora, data_venda, vendedor);
        this.dimensao = dimensao;
        this.material = material;
        this.ano = lancamento;
        this.premium = premium;
        this.desconto = desconto;
    }

    public Malas(Malas malas) {
        this.dimensao = malas.getDimensao();
        this.material = malas.getMaterial();
        this.ano = malas.getAno();
        this.premium = malas.getPremium();
        this.desconto = malas.getDesconto();
    }

    public int getDimensao (){
        return this.dimensao;
    }
    public String getMaterial (){
        return this.material;
    }
    public int getAno (){
        return this.ano;
    }
    public String getPremium (){
        return this.premium;
    }
    public double getDesconto (){
        return this.desconto;
    }
    public double getPreco (){
        return this.preco;
    }

    public void setDimensao (int dimensao){
        this.dimensao = dimensao;
    }
    public void setMaterial (String material){
        this.material = material;
    }
    public void setAno (int ano){
        this.ano = ano;
    }
    public void setPremium (String premium){
        this.premium = premium;
    }
    public void setdesconto (double desconto){
        this.desconto = desconto;
    }
    public void setPreco (double preco){
        this.preco = preco;
    }
    void setPrecoaux (){
        double preco = getPreco_base();
        int donos = getDonos();
        int estado = estadoToInt(getEstado ());
        if (this.premium.equals("TRUE")) {
            preco = preco * 0.5/this.dimensao;
        }
        preco = preco * (this.desconto/this.dimensao);
        setPreco (preco);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Malas mala = (Malas) o;
        return (this.dimensao == mala.getDimensao() &&
                this.material.equals(mala.getMaterial()) &&
                this.ano == mala.getAno() &&
                this.desconto == mala.getDesconto() &&
                this.premium.equals(mala.getPremium()));
    }
    @Override
    public Malas clone() {
        return new Malas(this);
    }

}
