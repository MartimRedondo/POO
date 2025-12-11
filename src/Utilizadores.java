//sem warnings que possam causar problemas

import java.io.Serializable;
public class Utilizadores implements Serializable {
    private String code;
    private String email;
    private String nome;
    private String morada;
    private String NIF;

    public Utilizadores (){
        this.code = "";
        this.email = "";
        this.nome = "";
        this.morada = "";
        this.NIF = "";


    }

    public Utilizadores (String code, String email, String nome, String morada, String NIF){
        this.code = code;
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.NIF = NIF;

    }

    public  Utilizadores (Utilizadores utils) {
        this.code = utils.getCode ();
        this.email = utils.getEmail ();
        this.nome = utils. getNome ();
        this.morada = utils.getMorada ();
        this.NIF = utils.getNIF ();

    }

    public String getCode (){
        return this.code;
    }
    public String getEmail (){
        return this.email;
    }
    public String getNome (){
        return this.nome;
    }
    public String getMorada (){
        return this.morada;
    }
    public String getNIF (){
        return this.NIF;
    }


    public void setCode(String code){
        this.code = code;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setMorada(String morada){
        this.morada = morada;
    }
    public void setNIF(String NIF){
        this.NIF = NIF;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilizadores user = (Utilizadores) o;
        return (this.code.equals(user.getCode()) &&
                this.email.equals(user.getEmail()) &&
                this.nome.equals(user.getNome()) &&
                this.morada.equals(user.getMorada()) &&
                this.NIF.equals(user.getNIF()));
    }
    public Utilizadores clone(){
        return new Utilizadores(this);
    }





}
