package clases;

import java.util.ArrayList;

public class ListaResenas {
    private ArrayList<Resena> resenas;

    public ListaResenas() {
        resenas = new ArrayList<>();
    }

    public Resena getResena(int i) {
        return resenas.get(i);
    }

    public void addResena(Resena resena) {
        resenas.add(resena);
    }

    public void deleteResena(String usuario, String titulo, String autor) {
        for (int i = 0; i < resenas.size(); i++) {
            if (resenas.get(i).getUsuario().equals(usuario)) {
            	resenas.remove(i);
                break;
            }
        }
    }
    public int size() {
        return resenas.size();
    }
}