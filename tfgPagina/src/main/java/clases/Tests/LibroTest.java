package clases.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import clases.Libro;

class LibroTest {

	@Test
	void testLibro() {
		Libro aux = new Libro("a","a",0.01);
		Libro aux2=null;
		assertNotNull(aux);
		assertEquals(aux.getPrecio(),0.01);
		assertEquals(aux.getAutor(),"a");
		assertEquals(aux.getTitulo(),"a");
		assertNull(aux2);
	}

	@Test
	void testGetTitulo() {
		Libro aux = new Libro("a","a",0.01);
		assertEquals(aux.getTitulo(),"a");
		assertNotEquals(aux.getTitulo(),"b");
	}

	@Test
	void testGetAutor() {
		Libro aux = new Libro("a","a",0.01);
		assertEquals(aux.getAutor(),"a");
		assertNotEquals(aux.getAutor(),"b");
	}

	@Test
	void testGetPrecio() {
		Libro aux = new Libro("a","a",0.01);
		assertEquals(aux.getPrecio(),0.01);
		assertNotEquals(aux.getPrecio(),0.02);
	}

}
