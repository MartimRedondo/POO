import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Fatura implements Serializable {

    private String code;
    private String NIF;
    private String user;
    private Map <Double, Artigos> preco;
    private String transportadora;
    private double preco_final;
    double preco_transporte;
    private LocalDate data_emissao;


    public Fatura(){
        this.code = "";
        this.NIF ="";
        this.user ="";
        this.preco = new HashMap<>();
        this.transportadora ="";
        this.preco_final =0.0;
        this.preco_transporte = 0.0;
        this.data_emissao =LocalDate.now();
    }

    public Fatura(String code, String NIF, String user, Map <Double, Artigos> preco, String transportadora, double preco_final, double preco_transporte, LocalDate data_emissao) {
        this.code = code;
        this.NIF = NIF;
        this.user = user;
        this.preco = preco;
        this.preco_final = preco_final;
        this.transportadora = transportadora;
        this.preco_transporte = preco_transporte;
        this.data_emissao = data_emissao;
    }

    public Fatura(Fatura a){
        this.code =a.getCode();
        this.NIF =a.getNIF();
        this.user =a.getUser();
        //this.preco = a.getpreco();
        this.transportadora =a.getTransportadora();
        this.preco_final =a.getPreco_final();
        this.preco_transporte = a.getPreco_transporte();
        this.data_emissao =a.getData_emissao();
    }

    public LocalDate getData_emissao(){
        return this.data_emissao;
    }
    public String getCode(){
        return this.code;
    }
    public double getPreco_final() {
        return this.preco_final;
    }
    public String getNIF() {
        return this.NIF;
    }
    public String getUser() {
        return this.user;
    }
    public double getPreco_transporte() {
        return this.preco_transporte;
    }
    public String getTransportadora() {
        return this.transportadora;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setTransportadora(String transportadora) {
        this.transportadora = transportadora;
    }

    public void setCode(String code){
        this.code = code;
    }
    public void setNIF(String NIF) {
        this.NIF = NIF;
    }
    public void setPreco_final(double preco_final) {
        this.preco_final = preco_final;
    }
    public void setData_emissao(LocalDate date_emissao) {
        this.data_emissao = date_emissao;
    }
    public void setPreco_transporte(double preco_transporte) {
        this.preco_transporte = preco_transporte;
    }

    public LocalDate setBefore(LocalDate before){
        return this.data_emissao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fatura fatura = (Fatura) o;
        return (this.preco_final ==fatura.getPreco_final() &&
                this.NIF.equals(fatura.getNIF()) &&
                this.user.equals(fatura.getUser())&&
                this.transportadora.equals(fatura.getTransportadora())&&
                this.preco_transporte == fatura.getPreco_transporte() &&
                this.data_emissao.equals(fatura.getData_emissao()));
    }



    public Fatura clone() throws CloneNotSupportedException {
        Fatura clone = (Fatura) super.clone();
        return new Fatura(this);
    }

}
