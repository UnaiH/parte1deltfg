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
        return GestorSesion.getMiGestorSesion().iniciarSesion(password, usuario);
    }

    public boolean registrarse(String password, String usuario) {
        return GestorSesion.getMiGestorSesion().registrarse(password, usuario);
    }

    public boolean eliminarUsuario(String usuario) {
        return GestorSesion.getMiGestorSesion().eliminarUsuario(usuario);
    }

    public ArrayList<LibroAuxiliar> mostrarTodosEnlaces(String titulo, String autor) {
        return GestorConexionApis.getGestorConexionApis().mostrarTodosEnlaces(titulo, autor);
    }

    public LibroAuxiliar buscarLibro(String titulo, String autor) {
        return GestorConexionApis.getGestorConexionApis().buscarLibro(titulo, autor);
    }

    public void eliminarResena(String usuario, String titulo, String autor) {
        GestorResenas.getMiGestorResenas().eliminarResena(usuario, titulo, autor);
    }

    public void escribirResena(String usuario, String titulo, String autor, String texto) {
        GestorResenas.getMiGestorResenas().escribirResena(usuario, titulo, autor, texto);
    }

    public ListaResenas mostrarResenasLibro(String titulo, String autor) {
        return GestorResenas.getMiGestorResenas().mostarResenasLibro(titulo, autor);
    }

    public HashMap<String,ListaResenasAux> mostrarTodasResenas() {
        return GestorResenas.getMiGestorResenas().mostrarTodasResenas();
    }
}
