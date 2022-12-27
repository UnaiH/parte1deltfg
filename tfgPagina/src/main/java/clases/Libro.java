package clases;

public class Libro {
	//Esta clase guarda la informacion de un libro como es el titulo del libro, el autor del libro y el precio del libro.
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