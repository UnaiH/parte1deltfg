package clases.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import clases.ListaResenas;
import clases.Resena;

class ListaResenasTest {

	@Test
	void testListaResenas() {
		ListaResenas lista = null;
		assertNull(lista);
		lista = new ListaResenas();
		assertNotNull(lista);
	}

	@Test
	void testGetResena() {
		ListaResenas lista = new ListaResenas();
		lista.addResena(new Resena("a","a"));
		assertNotNull(lista.getResena(0));
		lista.addResena(null);
		assertNull(lista.getResena(1));
	}

	@Test
	void testAddResena() {
		ListaResenas lista = new ListaResenas();
		lista.addResena(new Resena("a","a"));
		assertNotNull(lista.getResena(0));
		lista.addResena(null);
		assertNull(lista.getResena(1));
	}

	@Test
	void testDeleteResena() {
		ListaResenas lista = new ListaResenas();
		lista.addResena(new Resena("a","a"));
		assertNotNull(lista.getResena(0));
		lista.addResena(null);
		assertNull(lista.getResena(1));
		lista.deleteResena("a", "a", "a");
		assertNull(lista.getResena(0));
	}

	@Test
	void testSize() {
		ListaResenas lista = new ListaResenas();
		lista.addResena(new Resena("a","a"));
		assertNotNull(lista.getResena(0));
		assertEquals(lista.size(),1);
		lista.addResena(null);
		assertNull(lista.getResena(1));
		assertEquals(lista.size(),2);
		lista.deleteResena("a", "a", "a");
		assertEquals(lista.size(),1);
	}

}
