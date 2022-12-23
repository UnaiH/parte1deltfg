package clases;

public class Usuario {
    private String usuario;
    private String password;
    private boolean administrador;

    public Usuario(String pUsuario, String pPassword, boolean pAdministrador) {
        this.usuario = pUsuario;
        this.password = pPassword;
        this.administrador = pAdministrador;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean getAdministrador() {
        return this.administrador;
    }
}