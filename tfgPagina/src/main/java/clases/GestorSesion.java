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
    	//Esta funcion llama al gestorBD para inciar sesion
        return GestorBD.miGestorBD().iniciarSesion(password, usuario);
    }

    public boolean registrarse(String password, String usuario) {
    	//Esta funcion llama al gestorBD para registrar un usuario
    	return GestorBD.miGestorBD().registrarse(password, usuario);
    }

    public boolean eliminarUsuario(String usuario) {
    	//Esta funcion llama al gestorBD para eliminar un usuario
    	return GestorBD.miGestorBD().eliminarUsuario(usuario);
    }
}