package clases;

public class LibroAuxiliar {
    private Double precio;
    private String enlace;

    public LibroAuxiliar(Double pPrecio, String pEnlace) {
        this.precio = pPrecio;
        this.enlace = pEnlace;
    }

    public Double getPrecio() {
        return this.precio;
    }

    public String getEnlace() {
        return this.enlace;
    }
}
