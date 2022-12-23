package clases;

public class GestorSesion {
    private static GestorSesion miGestorSesion;

    private GestorSesion() {
    }

    public static GestorSesion getMiGestorSesion() {
        if (miGestorSesion == null) {
            miGestorSesion = new GestorSesion();
        }
        return miGestorSesion;
    }

    public boolean iniciarSesion(String password, String usuario) {
        return GestorBD.miGestorBD().iniciarSesion(password, usuario);
    }

    public boolean registrarse(String password, String usuario) {
    	return GestorBD.miGestorBD().registrarse(password, usuario);
    }

    public boolean eliminarUsuario(String usuario) {
    	return GestorBD.miGestorBD().eliminarUsuario(usuario);
    }
}