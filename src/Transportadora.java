//sem warnings que possam causar problemas

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Transportadora implements Serializable {
    private String name;
    private String Formula;
    private double margem_de_lucro;

    //estes valores começam pre-definidos, mas no enunciado diz que depois do avanço no tempo, estes podems ser alterados
    private double peq;
    private double med;
    private double grande;
    private Map<String, Encomenda> encomendas;
    // private Map<String, Artigos> listArtigos;
    //isto não seria redudante visto que a encomenda já tem a lista de artigos que será atribuida a cada transportadora (remover listArtigos?)


    public Transportadora (){
        this.name = ";";
        this.Formula = null;
        this.margem_de_lucro = 0;
        this.peq = 2;
        this.med = 4;
        this.grande = 7;
        this.encomendas = new HashMap<>();
        //this.listArtigos = new HashMap<>();
    }
    public Transportadora(Transportadora transporte) {
        this.name = getName();
        this.Formula = transporte.getFormula ();
        this.margem_de_lucro = transporte.getMargem_de_lucro();
        this.peq = transporte.getPeq();
        this.med = transporte.getMed();
        this.grande = transporte.getGrande();
        this.encomendas = getEncomendas();
        //this.listArtigos = transporte.getVendedores();
    }
    public Transportadora(String nome, String formula,  double margem_de_lucro, int peq, int med, int grande){
        this.name = nome;
        this.Formula = formula;
        this.margem_de_lucro = margem_de_lucro;
        this.peq = peq;
        this.med = med;
        this.grande = grande;
        //this.listArtigos = list;
    }

    public String getName (){
        return this.name;
    }
    // public Map<String, Artigos> getVendedores (){
    //    return this.listArtigos;
    //}
    public String getFormula (){return this.Formula;}
    public double getMargem_de_lucro() {
        return margem_de_lucro;
    }
    public double getPeq() {
        return this.peq;
    }
    public double getMed() {
        return this.med;
    }
    public double getGrande() {
        return this.grande;
    }
    public Map<String, Encomenda> getEncomendas (){
        return this.encomendas;
    }

    public void setFormula(String formula){this.Formula = formula;}
    public void setMargem_de_lucro(double margem_de_lucro) {
        this.margem_de_lucro = margem_de_lucro;
    }
    public void setPeq(double peq) {
        this.peq = peq;
    }
    public void setMed(double med) {
        this.med = med;
    }
    public void setGrande(double grande) {
        this.grande = grande;
    }
    public void setName (String name){
        this.name = name;
    }
    public void setEncomendas (Map<String, Encomenda> encomendas){
        this.encomendas = encomendas;
    }
    //public void setListVendedores  (Map<String, Artigos> artigos){
    //    this.listArtigos = artigos;
    //}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transportadora transporte = (Transportadora) o;
        return (this.Formula.equals(transporte.getFormula()) &&
                this.name.equals(transporte.getName()) &&
                this.margem_de_lucro == transporte.getMargem_de_lucro());
        //this.listArtigos.equals(transporte.getVendedores()));
    }

    public Transportadora clone(){
        return new Transportadora(this);
    }

    // public void add_artigo (Artigos artigo) {
    //   this.listArtigos.put(artigo.getCode(), artigo);
    // }
    public void add_encomenda (Encomenda encomenda) {
        this.encomendas.put(encomenda.getComprador().getCode(), encomenda);
    }


   /* public double FormulaToExpressionToResult (){
       String formula = this.Formula;
        formula = formula.replaceAll("ValorBase", Double.toString (ValorBase));
        //falta arranjar maneira de conectar a lista de encomendas com o transporte e saber o tamanho da encomenda para dar o decido valor
        formula = formula.replaceAll ("MargemDeLucro", Double.toString(margem_de_lucro));
        return Calculadora.calcular(formula);
        }

*/
}
