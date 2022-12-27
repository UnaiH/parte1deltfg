package clases;

import java.util.ArrayList;

public class ListaResenasAux {
	//Esta clase almacena las resAux en una estrctura ArrayList.
    private ArrayList<resAux> resenas;

    public ListaResenasAux() {
        resenas = new ArrayList<>();
    }

    public resAux getResena(int i) {
        return resenas.get(i);
    }

    public void addResena(resAux resena) {
        resenas.add(resena);
    }

    public void deleteResena(String usuario, String titulo, String autor) {
        for (int i = 0; i < resenas.size(); i++) {
            if (resenas.get(i).getUsu().equals(usuario)) {
            	resenas.remove(i);
                break;
            }
        }
    }
    public int size() {
        return resenas.size();
    }
}