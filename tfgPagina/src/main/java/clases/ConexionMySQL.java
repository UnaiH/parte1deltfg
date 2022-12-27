package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
	//Esta clase gestiona la conexion con la base de datos.
    private String port = "3306";
    private String url = "jdbc:mysql://localhost:3306/tfg";
    private String username = "tfg";
    private String password = "tfg1234";
    private static Connection connection;
    private static ConexionMySQL miConexionMySQL;

    private ConexionMySQL() {

    }

    public static ConexionMySQL getConexionMySQL() {
        if (miConexionMySQL == null) {
            miConexionMySQL = new ConexionMySQL();
        }
        return miConexionMySQL;
    }

    public Connection conectarMySQL() throws ClassNotFoundException {
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            Connection conn = null;
            connection = DriverManager.getConnection(url, username, password);
            System.out.print("Database is connected !");
            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}