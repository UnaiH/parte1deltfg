package clases;

public class Resena {
	//Esta clase guarda la informacion de una resena como es el nombre de usuario y el texto.
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