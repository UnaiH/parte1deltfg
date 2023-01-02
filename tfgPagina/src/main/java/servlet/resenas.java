package servlet;

import clases.*;
import java.util.HashMap;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//Este servlet se encarga de gestionar las solicitudes del frontend relacionadas con las resenas
/**
 * Servlet implementation class resenas
 */
@WebServlet("/resenas")
public class resenas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public resenas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String titulo = request.getParameter("titulo");
		String autor = request.getParameter("autor");
		System.out.println("Get de resenas");
		int tipo = Integer.parseInt(request.getParameter("tipo"));
		//Si la variable tipo es 1 se realizara una llamada a comparador para obtener todas las resenas de un libro
		if(tipo == 1) {
			System.out.println("Buscando resenas");
			ListaResenas lista = Comparador.getMiComparador().mostrarResenasLibro(titulo, autor);
			System.out.println(lista.size());
			System.out.println("Lista obtenida");
			int i = 0;
			PrintWriter  out = response.getWriter();
			out.println(lista.size()+",");
			//Si la respuesta no es vacia se devuelven los elementos de la respuesta.
			while(lista.size()>i) {
				//para separar cada dato se hace por ","
				out.println(lista.getResena(i).getUsuario()+",");
				out.println(lista.getResena(i).getTexto()+",");
				i++;
			}
			//Si la variable tipo es 2 se realizara una llamada a comparador para obtener todas las resenas existentes desglosadas por usuario
		}else if(tipo==2) {
			System.out.println("Buscando resenas por usuario");
			HashMap<String,ListaResenasAux> lista = Comparador.getMiComparador().mostrarTodasResenas();
			Object[] llaves = lista.keySet().toArray();
			int i = 0;
			Object key = "";
			PrintWriter  out = response.getWriter();
			out.println("|"+llaves.length+"/");
			System.out.println("Lista de usuarios obtenidas");
			//Si la respuesta no es vacia se devuelven los elementos de la respuesta.
			while(i<llaves.length) {
				key = llaves[i];
				ListaResenasAux listAux = lista.get(key);
				System.out.println("Lista de las resenas del usuario obtenidas");
				//Se usan distintos caracteres para separar los datos. El "." para separar el usuario del resto, "*" para separar el tamano de la lista del resto de datos, "," para separar los datos de un elemento concreto de la tabla del resto de datos de los elementos y ";" separa un elemento de la lista, ";;" separa las resenas de un usuario del resto de usuarios. 
				out.println(key+".");
				out.println(listAux.size()+"*");
				int j = 0;
				while(j<listAux.size()) {
					out.println(listAux.getResena(j).getAut()+", ");
					out.println(listAux.getResena(j).getTit()+", ");
					if((listAux.size()-1)>j) {
						out.println(listAux.getResena(j).getRes()+"; ");
					}else {
						out.println(listAux.getResena(j).getRes()+";; ");
					}
					j++;
				}
				i++;
			}
		}else {
			response.sendError(454);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int tipo=Integer.parseInt(request.getParameter("tipo"));
		String titulo = request.getParameter("titulo");
		String autor = request.getParameter("autor");
		String resena = request.getParameter("resena");
		System.out.println("Post de resenas");
		String usuario = request.getParameter("usu");
		if(tipo == 1) {
			//Si tipo es 1 se guarda la resena en la base de datos empezando con la llamada a Comparador.
			Comparador.getMiComparador().escribirResena(usuario, titulo, autor, resena);
			System.out.println("Resena guardada");
		}else if(tipo==2){
			//Si tipo es 2 se elimina la resena en la base de datos empezando con la llamada a Comparador.
			System.out.println("Se procede con la eliminacion de la resena");
			Comparador.getMiComparador().eliminarResena(usuario, titulo, autor);
			System.out.println("Resena borrada");
		}else {
			response.sendError(454);
		}
	}

}
