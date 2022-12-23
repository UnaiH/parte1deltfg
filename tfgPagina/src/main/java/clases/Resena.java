package clases;

public class Resena {
    private String texto;
    private String usuario;

    public Resena(String pTexto, String pUsuario) {
        this.texto = pTexto;
        this.usuario = pUsuario;
    }

    public String getTexto() {
        return this.texto;
    }

    public String getUsuario() {
        return this.usuario;
    }
}