package clases;

import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.jdbc.PreparedStatement;

public class GestorBD {
    private static GestorBD miGestorBD;

    private GestorBD() {
    }

    public static GestorBD miGestorBD() {
        if (miGestorBD == null) {
            miGestorBD = new GestorBD();
        }
        return miGestorBD;
    }

    public boolean registrarse(String password, String usuario) {
        try {
            Statement sql = (Statement) ConexionMySQL.getConexionMySQL().conectarMySQL().createStatement();
            String consulta = "SELECT usuario FROM usuario WHERE usuario ='" + usuario + "'";
            ResultSet res = sql.executeQuery(consulta);
            System.out.println("Verificación de existencia del usuario");
            if (res != null) {
            	System.out.println("Creando usuario en la base de datos");
                consulta = "INSERT INTO usuario(usuario, password, administrador) VALUES('" + usuario + "','"
                        + password + "'," + 0 + ")";
                System.out.println("Creando usuario en la base de datos");
                sql.executeUpdate(consulta);
                System.out.println("Usuario creado correctamente en la base de datos");
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
        return false;
    }

    public HashMap<String,ListaResenasAux> mostrarTodasResenas() {
        HashMap<String,ListaResenasAux> usuResenas = new HashMap<String,ListaResenasAux>();
        ListaResenasAux lista = null;
        System.out.println("Iniciada busqueda de las resenas");
        try {
            Statement sql = (Statement) ConexionMySQL.getConexionMySQL().conectarMySQL().createStatement();
            String consulta = "SELECT * FROM resena";
            ResultSet res = sql.executeQuery(consulta);
            if (res != null) {
                while (res.next()) {
                	if(!usuResenas.containsKey(res.getString("usuario"))) {
                		lista = new ListaResenasAux();
                		lista.addResena(new resAux(res.getString("usuario"), res.getString("texto"),res.getString("titulo"), res.getString("autor")));
                		usuResenas.put(res.getString("usuario"), lista);
                	}else {
                		usuResenas.get(res.getString("usuario")).addResena(new resAux(res.getString("usuario"), res.getString("texto"),res.getString("titulo"), res.getString("autor")));
                	}
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("Finalizada busqueda de las resenas");
        return usuResenas;
    }

    public ListaResenas mostrarResena(String titulo, String autor) {
    	ListaResenas lista = new ListaResenas();
        try {
        	System.out.println("Buscando resenas de "+titulo);
            Statement sql = (Statement) ConexionMySQL.getConexionMySQL().conectarMySQL().createStatement();
            String consulta = "SELECT * FROM resena WHERE titulo='" + titulo + "' AND autor='" + autor + "'";
            ResultSet res = sql.executeQuery(consulta);
            if (res != null) {
                while (res.next()) {
                    Resena aux = new Resena(res.getString("texto"), res.getString("usuario"));
                    lista.addResena(aux);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return lista;
    }

    public boolean iniciarSesion(String password, String usuario) {
        ResultSet res = null;
        try {
            Statement sql = (Statement) ConexionMySQL.getConexionMySQL().conectarMySQL().createStatement();
            System.out.println("Verificación de existencia del usuario");
            String consulta = "SELECT usuario FROM usuario WHERE password = '" + password + "' AND usuario ='"
                    + usuario
                    + "'";
            res = sql.executeQuery(consulta);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try {
			if (res.next()) {
				System.out.println("Existencia verificada correctamente");
			    return true;
			} else {
			    return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return false;
    }

    public void escribirResena(String usuario, String titulo, String autor, String texto) {
        try {
        	System.out.println("Iniciando escritura de resena");
            Statement sql = (Statement) ConexionMySQL.getConexionMySQL().conectarMySQL().createStatement();
            String consulta = "SELECT * FROM usuario WHERE usuario = '" + usuario + "'";
            ResultSet res = sql.executeQuery(consulta);
                if (res != null) {
                	res=null;
                	System.out.println("Existencia del usuario verificada");
                	consulta = "SELECT * FROM resena WHERE usuario = '" + usuario + "' AND titulo='"+titulo+"' AND autor='"+autor+"'";
                    res = sql.executeQuery(consulta);
                    if(res!=null) {
                    	System.out.println("No existencia de resena verificada");
                    	consulta = "INSERT INTO resena(usuario, titulo, autor, texto) VALUES('" + usuario + "','"
                                + titulo + "','" + autor + "','" + texto + "')";
	                    sql.executeUpdate(consulta);
	                    System.out.println("Resena creada correctamente");
                    }
                }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public boolean eliminarUsuario(String usuario) {
        try {
        	Statement sql = (Statement) ConexionMySQL.getConexionMySQL().conectarMySQL().createStatement();
            String consulta = "SELECT usuario FROM usuario WHERE usuario ='" + usuario + "'";
            ResultSet res = sql.executeQuery(consulta);
            System.out.println("Verificación de existencia del usuario");
            if (res != null) {
	        	System.out.println("Eliminando usuario");
	            sql = (Statement) ConexionMySQL.getConexionMySQL().conectarMySQL().createStatement();
	            consulta = "DELETE FROM usuario WHERE usuario'" + usuario + "'";
	            sql.executeQuery(consulta);
	            System.out.println("Usuario eliminado");
	            return true;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
        return false;
    }

    public void eliminarResena(String titulo, String autor, String usuario) {
        try {
            Statement sql = (Statement) ConexionMySQL.getConexionMySQL().conectarMySQL().createStatement();
            String consulta = "DELETE FROM Resena WHERE usuario='" + usuario + "' AND titulo='" + titulo + "' AND autor='"
                    + autor + "'";
            System.out.println(consulta);
            sql.executeUpdate(consulta);
            System.out.println("Resena eliminada de la base de datos");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void anadirLibro(String titulo, String autor) {
        try {
        	System.out.println("Comprobando existencia del libro");
            Statement sql = (Statement) ConexionMySQL.getConexionMySQL().conectarMySQL().createStatement();
            String consulta = "SELECT titulo FROM libro WHERE titulo ='" + titulo + "' AND autor ='" + autor + "'";
            ResultSet res = sql.executeQuery(consulta);
            System.out.println("Existencia verificada");
            if (!res.next()) {
            	System.out.println("Iniciando guardado del libro");
                consulta = "INSERT INTO libro(titulo, autor) VALUES('" + titulo + "','" + autor + "')";
                sql.executeUpdate(consulta);
                System.out.println("Libro guardado");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}