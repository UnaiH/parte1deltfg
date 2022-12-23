package clases;

import java.util.HashMap;

public class GestorResenas {
    private static GestorResenas miGestorResenas;

    private GestorResenas() {
    }

    public static GestorResenas getMiGestorResenas() {
        if (miGestorResenas == null) {
            miGestorResenas = new GestorResenas();
        }
        return miGestorResenas;
    }

    public void eliminarResena(String usuario, String titulo, String autor) {
    	GestorBD.miGestorBD().eliminarResena(titulo, autor, usuario);
    }

    public void escribirResena(String usuario, String titulo, String autor, String texto) {
    	GestorBD.miGestorBD().anadirLibro(titulo, autor);
    	GestorBD.miGestorBD().escribirResena(usuario, titulo, autor, texto);
    }

    public ListaResenas mostarResenasLibro(String titulo, String autor) {
        return GestorBD.miGestorBD().mostrarResena(titulo, autor);
    }

    public HashMap<String,ListaResenasAux> mostrarTodasResenas() {
        return GestorBD.miGestorBD().mostrarTodasResenas();
    }

}