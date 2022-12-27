package clases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import com.google.gson.Gson;
import java.net.URL;

public class GestorConexionApis {
	//Esta clase realiza todas las conexiones a la api de google books
    private static GestorConexionApis miGestorConexionApis;

    private GestorConexionApis() {

    }

    public static GestorConexionApis getGestorConexionApis() {
        if (miGestorConexionApis == null) {
            miGestorConexionApis = new GestorConexionApis();
        }
        return miGestorConexionApis;
    }

    public ArrayList<LibroAuxiliar> mostrarTodosEnlaces(String tit, String autor) {
    	//Esta funcion obtiene todos los enlaces de un libro.
    	String bookJSONString=null;
        String url1 = "https://www.googleapis.com/books/v1/volumes?q=" + tit + "&inauthor:" + autor
                + "&key=AIzaSyBdNYZeVARrXAFO6ZULBgIsUQEteB1qxXg";
        LibroAuxiliar libVacio = new LibroAuxiliar(0.00,"#");
        ArrayList<LibroAuxiliar> listaVacia = new ArrayList<LibroAuxiliar>();
		listaVacia.add(libVacio);
        try {
        	System.out.println(url1);
            URL url = new URL(url1);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            //Se realiza la llamada a la api
            con.setRequestMethod("GET");
            //Se obtiene el codigo de resultado si es 200 se considera exitosa y sino se lanza una excepcion con el codigo
            int codigo = con.getResponseCode();
            if (codigo == 200) {
            	InputStream input = con.getInputStream();
            	StringBuffer buffer = new StringBuffer();
            	//Si la respuesta es null se devuelve una lista vacia sino se procesa la respuesta.
            	if(input == null) {
            		return listaVacia;
            	}else {
            		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            		String line;
            		//Se almacena la respuesta el StringBuffer si hay otra linea
            		while((line = reader.readLine())!=null) {
            			buffer.append(line + "\n");
            		}
            		//Si la longitud de buffer fuera 0 se devolveria una lista vacia
            		if(buffer.length()==0) {
            			return listaVacia;
            		}
            		//Se convierte el valor de la variable buffer en un String.
            		bookJSONString = buffer.toString();
            		//Si la conexion no es null se cierra la conexion a la api
            		if(con!=null) {
                    	con.disconnect();
                    }
            		//Si reader no es null se cierra la conexion de reader.
            		if(reader!=null) {
            			try {
            				reader.close();
            			}catch(IOException e) {
            				e.printStackTrace();
            			}
            		}
            		
            	}
            } else {
                throw new RuntimeException("Ha habido algún problema " + codigo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Ahora se procesara el String de la respuesta.
        tit=tit.replace("_", " ");
        autor=autor.replace("_", " ");
        System.out.println(autor);
        System.out.println(tit);
        ArrayList<String> librosAutor = new ArrayList<>();
        //Se separa el string en una lista de strings considerando "kind" como separador.
        for(String libro : bookJSONString.split("kind")) {
        	if(libro.contains(autor)) {
        		librosAutor.add(libro);
        	}
        }
        ArrayList<LibroAuxiliar> listaLibro = new ArrayList<>();
        System.out.println(librosAutor.size());
        //Si la lista de strings es mas que 0 se continua con el procesado
        if(librosAutor.size()>0) {
        	int i = 0;
        	//Se recorre la lista de strings
        	while(librosAutor.size()>i) {
        		String libAux = librosAutor.get(i);
        		//Si contiene el titulo del libro buscado pero no contiene el substring "buyLink" se guarda en una lista con el enlace y el precio "0.00"
        		if(libAux.contains(tit)){
	        		if(!libAux.contains("buyLink")) {
	        			String linkNoOnline = "http:"+libAux.split("infoLink")[1].split(",")[0].split(":")[2].replace("\"", "");
	        			LibroAuxiliar auxLib = new LibroAuxiliar(0.00,linkNoOnline);
	        			listaLibro.add(auxLib);
	        		}else {
	        			//Si contiene el substring "buylink" se sigue procesando la respuesta para obtener la informacion
	        			String precios = libAux.split("saleInfo")[1];
	        			//Si continene el substring "amount" se obtiene el precio y la url de donde comprarlo.
	        			if(precios.contains("amount")) {
		        			String link = "https:" + precios.split("buyLink")[1].split(",")[0].split(":")[2].replace("\"", "");
		        			String[] listaPrecio = precios.split("listPrice");
		        			int l=0;
		        			while(l<listaPrecio.length) {
		        				if(l==1) {
		        					int k=0;
			        				String[] amounts= listaPrecio[1].split("amount");
				        			while(k < amounts.length) {
				        				if(k==1) {
						        			int j=0;
						        			String[] filtrados=amounts[1].split(",");
						        			while(j < filtrados.length) {
						        				if(j==0) {
						        					String filtr = filtrados[0].replace("\": ", "");
						        					System.out.println(filtr);
						        					LibroAuxiliar auxLib = new LibroAuxiliar(Double.parseDouble(filtr),link);
						        					listaLibro.add(auxLib);
						        				}
						        				j++;
						        			}
				        				}
				        				k++;
				        			}
		        				}
		        				l++;
		        			}
	        			}
	        		}
        		}
        		i++;
        	}
        }else {
        	return listaVacia;
        }
        return listaLibro;
    }

    public LibroAuxiliar buscarLibro(String tit, String autor) {
    	String bookJSONString=null;
        String url1 = "https://www.googleapis.com/books/v1/volumes?q=" + tit + "&inauthor:" + autor
                + "&key=AIzaSyBdNYZeVARrXAFO6ZULBgIsUQEteB1qxXg";
        try {
        	System.out.println(url1);
            URL url = new URL(url1);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            //Se realiza la conexion a la api para obtener la informacion del libro
            con.setRequestMethod("GET");
            int codigo = con.getResponseCode();
            //Si el codigo de respuesta es 200 entonces se transforma la respuesta obtenida en un String
            if (codigo == 200) {
            	InputStream input = con.getInputStream();
            	StringBuffer buffer = new StringBuffer();
            	if(input == null) {
            		return new LibroAuxiliar(0.00,"#");
            	}else {
            		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            		String line;
            		while((line = reader.readLine())!=null) {
            			buffer.append(line + "\n");
            		}
            		if(buffer.length()==0) {
            			return new LibroAuxiliar(0.00,"#");
            		}
            		bookJSONString = buffer.toString();
            		if(con!=null) {
                    	con.disconnect();
                    }
            		if(reader!=null) {
            			try {
            				reader.close();
            			}catch(IOException e) {
            				e.printStackTrace();
            			}
            		}
            		
            	}
            } else {
                throw new RuntimeException("Ha habido algún problema " + codigo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        tit=tit.replace("_", " ");
        autor=autor.replace("_", " ");
        System.out.println(autor);
        System.out.println(tit);
        ArrayList<String> librosAutor = new ArrayList<>();
        ArrayList<LibroAuxiliar> noPrecioOnline = new ArrayList<>();
        //Se consigue una lista de string a partir de separar el string de respuesta por el substring "kind"
        for(String libro : bookJSONString.split("kind")) {
        	if(libro.contains(autor)) {
        		librosAutor.add(libro);
        	}
        }
        //A partir de aqui se obtiene el enlace junto al precio buscando el precio mas bajo de la respuesta.
        Double precioMin = Double.POSITIVE_INFINITY;
        String linkMin = "#";
        if(librosAutor.size()>0) {
        	int i = 0;
        	//Se procesa la respuesta para obtener el precio y el link
        	while(librosAutor.size()>i) {
        		String libAux = librosAutor.get(i);
        		if(libAux.contains(tit)){
	        		if(!libAux.contains("buyLink")) {
	        			//Los string sin el "buyLink" se guardara el enlace junto a un 0.00 como precio.
	        			String linkNoOnline = "http:"+libAux.split("infoLink")[1].split(",")[0].split(":")[2].replace("\"", "");
	        			noPrecioOnline.add(new LibroAuxiliar(0.00,linkNoOnline));
	        		}else {
	        			String precios = libAux.split("saleInfo")[1];
	        			if(precios.contains("amount")) {
		        			String link = "https:" + precios.split("buyLink")[1].split(",")[0].split(":")[2].replace("\"", "");
		        			String[] listaPrecio = precios.split("listPrice");
		        			int l=0;
		        			//A partir de aqui se hace la comprobacion del precio minimo para obtener el precio minimo junto con el link correspondiente
		        			while(l<listaPrecio.length) {
		        				if(l==1) {
			        			int k=0;
			        				String[] amounts= listaPrecio[1].split("amount");
				        			while(k < amounts.length) {
				        				if(k==1) {
						        			int j=0;
						        			String[] filtrados=amounts[1].split(",");
						        			while(j < filtrados.length) {
						        				if(j==0) {
						        					String filtr = filtrados[0].replace("\": ", "");
						        					System.out.println(filtr);
								        			if(precioMin>Double.parseDouble(filtr)) {
								        				precioMin = Double.parseDouble(filtr);
								        				linkMin = link;
								        			}
						        				}
						        				j++;
						        			}
				        				}
				        				k++;
				        			}
		        				}
		        				l++;
		        			}
	        			}
	        		}
        		}
        		i++;
        	}
        }else {
        	return new LibroAuxiliar(0.00, "#");
        }
        System.out.println(librosAutor.size());
        //Si no hubiera un enlace con un precio y no hubiera un enlace "gratuito" se devolveria LibroAuxiliar con los valores 0.00 par el precio y el string "#" como enlace (que en javascript redirigiria a la misma pagina)
        if(precioMin == Double.POSITIVE_INFINITY) {
        	if (noPrecioOnline.size()>0) {
        		return noPrecioOnline.get(0);
        	}else {
        		return new LibroAuxiliar(0.00, "#");
        	}
        }else {
        	return new LibroAuxiliar(precioMin, linkMin);
        }
    }
}
