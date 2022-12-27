package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import clases.*;

/**
 * Servlet implementation class registrarse
 */
@WebServlet("/registrarse")
public class registrarse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registrarse() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    //Al recibir una solicitud post este servlet llevara a cabo el proceso correspondiente para registrar en la base de datos.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Se ha recogido la peticion en el servlet");
		Comparador com = Comparador.getMiComparador();
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		boolean registrado = com.registrarse(password,usuario);
		//Si se recibiera false se devolveria un codigo de error y sino no se devolveria nada
		if(!registrado) {
			response.sendError(450);
		}
	}

}
