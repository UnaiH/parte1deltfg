package clases;

public class Usuario {
	//Clase que se emplea para guardar la informacion de un usuario para transladarla a otras clases esta informacion es la del nombre de usuario, contrasena y si es administrador.
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