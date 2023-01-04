package clases.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import clases.Libro;
import clases.LibroAuxiliar;

class LibroAuxiliarTest {

	@Test
	void testLibroAuxiliar() {
		LibroAuxiliar aux = new LibroAuxiliar(0.01,"a");
		LibroAuxiliar aux2=null;
		assertNotNull(aux);
		assertEquals(aux.getPrecio(),0.01);
		assertEquals(aux.getEnlace(),"a");
		assertNull(aux2);
	}

	@Test
	void testGetPrecio() {
		LibroAuxiliar aux = new LibroAuxiliar(0.01,"a");
		assertEquals(aux.getPrecio(),0.01);
		assertNotEquals(aux.getPrecio(),0.02);
	}

	@Test
	void testGetEnlace() {
		LibroAuxiliar aux = new LibroAuxiliar(0.01,"a");
		assertEquals(aux.getEnlace(),"a");
		assertNotEquals(aux.getEnlace(),"b");
	}

}
