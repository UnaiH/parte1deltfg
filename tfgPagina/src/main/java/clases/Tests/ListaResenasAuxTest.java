package clases.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import clases.ListaResenas;
import clases.ListaResenasAux;
import clases.Resena;
import clases.resAux;

class ListaResenasAuxTest {

	@Test
	void testListaResenasAux() {
		ListaResenasAux lista = null;
		assertNull(lista);
		lista = new ListaResenasAux();
		assertNotNull(lista);
	}

	@Test
	void testGetResena() {
		ListaResenasAux lista = new ListaResenasAux();
		lista.addResena(new resAux("a","a","a","a"));
		assertNotNull(lista.getResena(0));
		lista.addResena(null);
		assertNull(lista.getResena(1));
	}

	@Test
	void testAddResena() {
		ListaResenasAux lista = new ListaResenasAux();
		lista.addResena(new resAux("a","a","a","a"));
		assertNotNull(lista.getResena(0));
		lista.addResena(null);
		assertNull(lista.getResena(1));
	}

	@Test
	void testDeleteResena() {
		ListaResenasAux lista = new ListaResenasAux();
		lista.addResena(new resAux("a","a","a","a"));
		assertNotNull(lista.getResena(0));
		lista.addResena(null);
		assertNull(lista.getResena(1));
		lista.deleteResena("a", "a", "a");
		assertNull(lista.getResena(0));
	}

	@Test
	void testSize() {
		ListaResenasAux lista = new ListaResenasAux();
		lista.addResena(new resAux("a","a","a","a"));
		assertEquals(lista.size(),1);
		lista.addResena(null);
		assertEquals(lista.size(),2);
		lista.deleteResena("a", "a", "a");
		assertEquals(lista.size(),1);
	}

}
