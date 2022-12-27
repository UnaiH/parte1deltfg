package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clases.Comparador;

/**
 * Servlet implementation class inicioSesion
 */
@WebServlet("/inicioSesion")
public class inicioSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public inicioSesion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //Al recibir este servlet una peticion get que realizara la comprobacion para iniciar sesion sino es correcto se devuelve un mensaje de erorr
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = request.getParameter("usuario");
		String pass = request.getParameter("password");
		System.out.println("Usuario: "+user+" Contrasena: "+pass);
		System.out.println(Comparador.getMiComparador().iniciarSesion(pass, user));
		if(!Comparador.getMiComparador().iniciarSesion(pass, user)) {
			response.sendError(451);
		}
	}
}
