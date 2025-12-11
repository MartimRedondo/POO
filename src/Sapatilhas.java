//sem warnings que possam causar problemas

import java.awt.*;
import java.time.LocalDate;

public class Sapatilhas extends Artigos {
    private int tamanho;
    private String atacadores;
    private String cor;
    private int ano;
    private String premium;
    private double desconto;
    private double preco;

    public Sapatilhas () {
        this.tamanho = 0;
        this.atacadores = "";
        this.cor = "";
        this.ano = -1;
        this.premium = "";
        this.desconto = -1;
        this.preco = 0;
    }
    public Sapatilhas (Sapatilhas sapatilhas) {
        this.tamanho = getTamanho();
        this.atacadores = getAtacadores();
        this.cor = getCor();
        this.ano = getLancamento();
        this.premium = getPremium();
        this.desconto = getDesconto();
        this.preco = preco;
    }
    public Sapatilhas (String estado, String donos, String descricao, String marca, String code, String preco_base, String transportadora, LocalDate data_venda,
                       int tamanho, String atacadores, String cor, int lancamento, String premium, double desconto, double preco, Vendedor vendedor) {
        super (estado, donos, descricao, marca, code, preco_base, transportadora, data_venda, vendedor);
        this.tamanho = tamanho;
        this.atacadores = atacadores;
        this.cor = cor;
        this.ano = lancamento;
        this.premium = premium;
        this.desconto = desconto;
        this.preco = preco;
    }
    public int getTamanho (){
        return this.tamanho;
    }
    public String getAtacadores (){
        return this.atacadores;
    }
    public String getCor (){
        return this.cor;
    }
    public int getLancamento (){
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

    public void setTamanho(int tamanho){
        this.tamanho = tamanho;
    }
    public void setAtacadores(String atacadores){
        this.atacadores = atacadores;
    }
    public void setCor(String cor){
        this.cor = cor;
    }
    public void setLancamento(int lancamento){
        this.ano = lancamento;
    }
    public void setPremium(String premium){
        this.premium = premium;
    }
    public void setDesconto(double desconto){
        this.desconto = desconto;
    }
    public void setPreco(double preco){
        this.preco = preco;
    }
    void setprecoaux (){
        double preco_base = getPreco_base();
        double preco = preco_base;
        int estado = estadoToInt(getEstado());
        int donos = getDonos();
        int anos = LocalDate.now().getYear() - this.ano;
        if (this.premium.equals("TRUE")) {
            while (anos != 0) {
                preco += preco_base*0.1;
                anos--;
            }
        }
        else if ((this.tamanho > 45 || estado != 1)) {
            preco = preco - (preco/donos * estado);
        }
        setPreco (preco);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sapatilhas sapato = (Sapatilhas) o;
        return (this.tamanho == sapato.getTamanho() &&
                this.atacadores.equals(sapato.getAtacadores()) &&
                this.cor.equals(sapato.getCor()) &&
                this.ano == sapato.getLancamento() &&
                this.desconto == sapato.getDesconto() &&
                this.premium.equals(sapato.getPremium()));
    }
    public Sapatilhas clone() {
        return new Sapatilhas(this);
    }
}

