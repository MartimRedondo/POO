//sem warnings que possam causar problemas

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Vendedor extends Utilizadores implements Serializable {
    private Map<String, Artigos> artigos_vendidos;
    private Map<String, Artigos> artigos;

    public Vendedor() {
        this.artigos_vendidos = new HashMap<>();
        this.artigos = new HashMap<>();
    }
    public Vendedor(String code, String email, String nome, String morada, String NIF, Map <String, Artigos> artigos_vendidos, Map <String, Artigos> artigos) {
        super(code, email, nome, morada, NIF);
        this.artigos_vendidos = artigos_vendidos;
        this.artigos = artigos;
    }

    public Vendedor(Vendedor vendedor) {
        this.artigos_vendidos = vendedor.getArtigos_Vendidos();
        this.artigos = vendedor.getArtigos();
    }

    public Map<String, Artigos> getArtigos_Vendidos(){
        return new HashMap<>(this.artigos_vendidos);
    }
    public Map<String, Artigos> getArtigos(){
        return new HashMap<>(this.artigos);
    }

    public void setArtigos_Vendidos(Map<String, Artigos> artigos_vendidos){
        this.artigos_vendidos = artigos_vendidos.entrySet()
                .stream()
                .collect(Collectors.toMap(k-> k.getKey(), k-> k.getValue().clone()));
    }

    public void setArtigos (Map<String, Artigos> artigos){
        this.artigos = artigos.entrySet()
                .stream()
                .collect(Collectors.toMap(k-> k.getKey(), k-> k.getValue().clone()));
    }
    public void addArtigo_Vendido(Artigos artigo) {
        artigos.remove(artigo.getCode(), artigo);
        artigos_vendidos.put(artigo.getCode(), artigo);
    }
    public void addArtigo(Artigos artigo) {
        artigos.put(artigo.getCode(), artigo);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendedor vendedor = (Vendedor) o;
        return (this.artigos_vendidos.equals(vendedor.getArtigos_Vendidos()) &&
                this.artigos.equals(vendedor.getArtigos()));
    }

    public Vendedor clone () {
        return new Vendedor (this);
    }
}