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
            con.setRequestMethod("GET");
            int codigo = con.getResponseCode();
            if (codigo == 200) {
            	InputStream input = con.getInputStream();
            	StringBuffer buffer = new StringBuffer();
            	if(input == null) {
            		return listaVacia;
            	}else {
            		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            		String line;
            		while((line = reader.readLine())!=null) {
            			buffer.append(line + "\n");
            		}
            		if(buffer.length()==0) {
            			return listaVacia;
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
        for(String libro : bookJSONString.split("kind")) {
        	if(libro.contains(autor)) {
        		librosAutor.add(libro);
        	}
        }
        ArrayList<LibroAuxiliar> listaLibro = new ArrayList<>();
        System.out.println(librosAutor.size());
        if(librosAutor.size()>0) {
        	int i = 0;
        	while(librosAutor.size()>i) {
        		String libAux = librosAutor.get(i);
        		if(libAux.contains(tit)){
	        		if(!libAux.contains("buyLink")) {
	        			String linkNoOnline = "http:"+libAux.split("infoLink")[1].split(",")[0].split(":")[2].replace("\"", "");
	        			LibroAuxiliar auxLib = new LibroAuxiliar(0.00,linkNoOnline);
	        			listaLibro.add(auxLib);
	        		}else {
	        			String precios = libAux.split("saleInfo")[1];
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
            con.setRequestMethod("GET");
            int codigo = con.getResponseCode();
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
        for(String libro : bookJSONString.split("kind")) {
        	if(libro.contains(autor)) {
        		librosAutor.add(libro);
        	}
        }
        Double precioMin = Double.POSITIVE_INFINITY;
        String linkMin = "#";
        if(librosAutor.size()>0) {
        	int i = 0;
        	while(librosAutor.size()>i) {
        		String libAux = librosAutor.get(i);
        		if(libAux.contains(tit)){
	        		if(!libAux.contains("buyLink")) {
	        			String linkNoOnline = "http:"+libAux.split("infoLink")[1].split(",")[0].split(":")[2].replace("\"", "");
	        			noPrecioOnline.add(new LibroAuxiliar(0.00,linkNoOnline));
	        		}else {
	        			String precios = libAux.split("saleInfo")[1];
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
        if(precioMin == Double.POSITIVE_INFINITY) {
        	if (noPrecioOnline.size()>0) {
        		return noPrecioOnline.get(0);
        	}else {
        		return new LibroAuxiliar(0.0, "#");
        	}
        }else {
        	return new LibroAuxiliar(precioMin, linkMin);
        }
    }
}
