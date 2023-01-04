package clases.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import clases.Resena;
import clases.Usuario;

class ResenaTest {

	@Test
	void testResena() {
		Resena aux = new Resena("a","a");
		Resena aux2=null;
		assertNotNull(aux);
		assertEquals(aux.getTexto(),"a");
		assertEquals(aux.getUsuario(),"a");
		assertNull(aux2);
	}

	@Test
	void testGetTexto() {
		Resena aux = new Resena("a","a");
		assertEquals(aux.getTexto(),"a");
		assertNotEquals(aux.getTexto(),"b");
	}

	@Test
	void testGetUsuario() {
		Resena aux = new Resena("a","a");
		assertEquals(aux.getUsuario(),"a");
		assertNotEquals(aux.getUsuario(),"b");
	}

}
