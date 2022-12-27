package servlet;

import java.io.IOException;
import clases.*;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class prueba
 */
@WebServlet("/eliminarusu")
public class EliminarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //Este servlet si recibe una peticion get realizara lo necesario para eliminar el usuario. Si no se elimina por un error se devuelve un codigo de error
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		String aux = request.getParameter("usuario");
		boolean eliminado = Comparador.getMiComparador().eliminarUsuario(aux);
		if(!eliminado) {
			response.sendError(452);
		}
	}


}
