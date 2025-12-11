//sem warnings que possam causar problemas

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Comprador extends Utilizadores implements Serializable {
    //mudei para List<Artigos> pois eles podem ter comprado mais de um artigo
    private Map<String, Artigos> artigos_comprados;
    public Comprador() {
        this.artigos_comprados = new HashMap<>();
    }

    public Comprador(String code, String email, String nome, String morada, String NIF, Map <String, Artigos> artigos) {
        super(code, email, nome, morada, NIF);
        this.artigos_comprados = artigos;
    }
    public Comprador(Comprador comprador) {
        this.artigos_comprados = comprador.getArtigos();
    }

    public Map<String, Artigos> getArtigos (){
        return new HashMap<>(this.artigos_comprados);
    }

    public void setArtigos_comprados (Map<String, Artigos> artigos) {
        this.artigos_comprados = artigos.entrySet()
                .stream()
                .collect(Collectors.toMap(k-> k.getKey(), k-> k.getValue().clone()));
    }
    public void addArtigos (Map <String, Artigos> encomendados) {
        this.artigos_comprados.putAll(encomendados);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comprador comprador = (Comprador) o;
        return (this.artigos_comprados.equals(comprador.getArtigos()));
    }
    public Comprador clone () {
        return new Comprador (this);
    }
}