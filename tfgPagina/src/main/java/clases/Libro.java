package clases;

public class Libro {
    private String titulo;
    private String autor;
    private Double precio;

    public Libro(String pTitulo, String pAutor, Double pPrecio) {
        this.titulo = pTitulo;
        this.autor = pAutor;
        this.precio = pPrecio;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getAutor() {
        return this.autor;
    }

    public Double getPrecio() {
        return this.precio;
    }
}