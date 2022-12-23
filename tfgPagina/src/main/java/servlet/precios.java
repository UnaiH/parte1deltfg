package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;

import clases.*;

/**
 * Servlet implementation class precios
 */
@WebServlet("/precios")
public class precios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public precios() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Get del servlet precios");
		String titulo = request.getParameter("titulo");
		String autor = request.getParameter("autor");
		String tip = request.getParameter("tipo");
		int tipo =Integer.parseInt(tip);
		if(tipo==1) {
			System.out.println("Buscando mejor precio");
			LibroAuxiliar libro = Comparador.getMiComparador().buscarLibro(titulo.replace(" ", "_"), autor.replace(" ", "_"));
			if(libro != null) {
				System.out.println("Enlace: "+libro.getEnlace()+" Precio: "+libro.getPrecio());
				PrintWriter  out = response.getWriter();
				out.println(""+libro.getPrecio());
				out.println(""+libro.getEnlace());
			}else {
				response.sendError(453);
			}
		}else if(tipo==2) {
			System.out.println("Buscando todos los enlaces");
			ArrayList<LibroAuxiliar> libros = Comparador.getMiComparador().mostrarTodosEnlaces(titulo.replace(" ", "_"), autor.replace(" ", "_"));
			int i = 0;
			PrintWriter  out = response.getWriter();
			out.println(libros.size()+",");
			while(libros.size()>i) {
				out.println(libros.get(i).getPrecio()+",");
				out.println(libros.get(i).getEnlace()+",");
				i++;
			}
			System.out.println("Todos los precios buscados");
		}else {
			
		}
	}
}
