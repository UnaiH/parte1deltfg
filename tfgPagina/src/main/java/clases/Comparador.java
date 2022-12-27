package clases;

import java.util.ArrayList;
import java.util.HashMap;

public class Comparador {
    private static Comparador miComparador;

    private Comparador() {
    }

    public static Comparador getMiComparador() {
        if (miComparador == null) {
            miComparador = new Comparador();
        }
        return miComparador;
    }

    public boolean iniciarSesion(String password, String usuario) {
    	//Esta funcion llama al gesto de sesion para realizar el inicio de sesion
        return GestorSesion.getMiGestorSesion().iniciarSesion(password, usuario);
    }

    public boolean registrarse(String password, String usuario) {
    	//Esta funcion llama al gesto de sesion para realizar el registro del usuario
    	return GestorSesion.getMiGestorSesion().registrarse(password, usuario);
    }

    public boolean eliminarUsuario(String usuario) {
    	//Esta funcion llama al gesto de sesion para eliminar el usuario
        return GestorSesion.getMiGestorSesion().eliminarUsuario(usuario);
    }

    public ArrayList<LibroAuxiliar> mostrarTodosEnlaces(String titulo, String autor) {
    	//Esta funcion llama al gestor de conexiones a apis para realizar la obtencion de todos los enlaces posibles de un libro junto al precio
        return GestorConexionApis.getGestorConexionApis().mostrarTodosEnlaces(titulo, autor);
    }

    public LibroAuxiliar buscarLibro(String titulo, String autor) {
    	//Esta funcion llama al gestor de conexiones a apis para realizar la obtencion del precio mas bajo junto a su enlace
        return GestorConexionApis.getGestorConexionApis().buscarLibro(titulo, autor);
    }

    public void eliminarResena(String usuario, String titulo, String autor) {
    	//Esta funcion llama al gesto de resenas para eliminar la resena
        GestorResenas.getMiGestorResenas().eliminarResena(usuario, titulo, autor);
    }

    public void escribirResena(String usuario, String titulo, String autor, String texto) {
    	//Esta funcion llama al gesto de resenas para guardar la resena
        GestorResenas.getMiGestorResenas().escribirResena(usuario, titulo, autor, texto);
    }

    public ListaResenas mostrarResenasLibro(String titulo, String autor) {
    	//Esta funcion llama al gesto de resenas para obtener todas las resenas de un libro en concreto
        return GestorResenas.getMiGestorResenas().mostarResenasLibro(titulo, autor);
    }

    public HashMap<String,ListaResenasAux> mostrarTodasResenas() {
    	//Esta funcion llama al gesto de resenas para obtener todas las resenas de todos los libros clasificados por usuario.
        return GestorResenas.getMiGestorResenas().mostrarTodasResenas();
    }
}
