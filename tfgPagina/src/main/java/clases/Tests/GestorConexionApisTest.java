package clases.Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import clases.GestorConexionApis;
import clases.LibroAuxiliar;

class GestorConexionApisTest {

	@Test
	void testGetGestorConexionApis() {
		assertNotNull(GestorConexionApis.getGestorConexionApis());
	}

	@Test
	void testMostrarTodosEnlaces() {
		GestorConexionApis api=GestorConexionApis.getGestorConexionApis();
		assertNotNull(api);
		ArrayList<LibroAuxiliar> res = api.mostrarTodosEnlaces("La_niebla", "Stephen_King");
		assertNotNull(res);
		int i = 0;
		System.out.println("Resultado obtenido");
		while(i>res.size()) {
			System.out.println("Enlace: " +res.get(i).getEnlace());
			System.out.println("Precio: " +res.get(i).getPrecio());
		}
		res = api.mostrarTodosEnlaces("Un_mundo_sin_fin", "Ken_Follet");
		assertNotNull(res);
		i = 0;
		System.out.println("Resultado obtenido");
		while(i>res.size()) {
			System.out.println("Enlace: " +res.get(i).getEnlace());
			System.out.println("Precio: " +res.get(i).getPrecio());
		}
	}

	@Test
	void testBuscarLibro() {
		GestorConexionApis api=GestorConexionApis.getGestorConexionApis();
		assertNotNull(api);
		LibroAuxiliar res = api.buscarLibro("La_Niebla", "Stephen_King");
		assertNotNull(res);
		System.out.println("Enlace: "+res.getEnlace());
		System.out.println("Precio: "+res.getPrecio());
	}

}
