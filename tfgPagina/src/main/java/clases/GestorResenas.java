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
    	//Se llama al GestorBD para eliminar la resena
    	GestorBD.miGestorBD().eliminarResena(titulo, autor, usuario);
    }

    public void escribirResena(String usuario, String titulo, String autor, String texto) {
    	//Se llama al GestorBD para anadir el libro que verifica que existe y si no existe lo almacena en la BD
    	GestorBD.miGestorBD().anadirLibro(titulo, autor);
    	//Se llama al GestorBD para anadir la resena
    	GestorBD.miGestorBD().escribirResena(usuario, titulo, autor, texto);
    }

    public ListaResenas mostarResenasLibro(String titulo, String autor) {
    	//Se llama al GestorBD para obtener las resenas de un libro determinado
        return GestorBD.miGestorBD().mostrarResena(titulo, autor);
    }

    public HashMap<String,ListaResenasAux> mostrarTodasResenas() {
    	//Se llama al GestorBD para obtener las resenas desglosadas por usuario
        return GestorBD.miGestorBD().mostrarTodasResenas();
    }

}