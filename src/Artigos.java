//sem warnings que possam causar problemas

import java.io.Serializable;
import java.time.LocalDate;

public class Artigos implements Serializable {
    private String estado;
    private int donos;
    private String descricao;
    private String marca;
    private String code;
    private double preco_base;
    private String transportadora;
    private LocalDate data_venda;
    private Vendedor vendedor;

    public Artigos () {
        this.estado = "";
        this.donos = 0;
        this.descricao = "";
        this.marca = "";
        this.code = "";
        this.preco_base = 0;
        this.transportadora = null;
        this.data_venda = null;
        this.vendedor = new Vendedor();
    }

    public Artigos (String estado, String donos, String descricao, String marca, String code, String preco_base, String transportadora, LocalDate data_venda, Vendedor vendedor) {
        this.estado = estado;
        this.donos = Integer.parseInt(donos);
        this.descricao = descricao;
        this.marca = marca;
        this.code = code;
        this.transportadora = transportadora;
        this.preco_base = Integer.parseInt(preco_base);
        this.data_venda = data_venda;
        this.vendedor = vendedor;
    }

    public Artigos (Artigos artigo) {
        this.estado = artigo.getEstado ();
        this.donos = artigo.getDonos ();
        this.descricao = artigo.getDescricao ();
        this.marca = artigo.getMarca ();
        this.code = artigo.getCode ();
        this.preco_base = artigo.getPreco_base ();
        this.transportadora = artigo.getTransportadora();
        this.data_venda = artigo.getData_Venda();
        this.vendedor = artigo.getVendedor();
    }
    public String getEstado (){
        return this.estado;
    }
    public int getDonos(){
        return this.donos;
    }
    public String getDescricao (){
        return this.descricao;
    }
    public String getMarca (){
        return this.marca;
    }
    public String getCode(){
        return this.code;
    }
    public double getPreco_base(){
        return this.preco_base;
    }
    public String getTransportadora(){
        return this.transportadora;
    }
    public LocalDate getData_Venda() { return this.data_venda; }
    public Vendedor getVendedor() { return this.vendedor; }

    public void setEstado(String estado){
        this.estado = estado;
    }
    public void setDonos(int donos){
        this.donos = donos;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    public void setMarca(String marca){
        this.marca = marca;
    }
    public void setCode(){
        this.code = GeradorCodigo.gerarCodigo();
    }
    public void setpreco(int preco_base){
        this.preco_base = preco_base;
    }
    public void setTransportadora (String transportadora){
        this.transportadora = transportadora;
    }
    public void setData_Venda() { this.data_venda = LocalDate.now(); }
    public void setVendedor(Vendedor vendedor) { this.vendedor = vendedor; }
    public int estadoToInt (String estado){
        if (estado.equals("novo")) return 1;
        if (estado.equals("pouco usado")) return 2;
        if (estado.equals("usado")) return 3;
        if (estado.equals("desgastado")) return 4;
        return 5;
    }
    public Artigos clone () {
        return new Artigos (this);
    }




}
