package clases.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import clases.Usuario;

class UsuarioTest {

	@Test
	void testUsuario() {
		Usuario aux = new Usuario("a","a",false);
		Usuario aux2=null;
		assertNotNull(aux);
		assertEquals(aux.getAdministrador(),false);
		assertEquals(aux.getPassword(),"a");
		assertEquals(aux.getUsuario(),"a");
		assertNull(aux2);
	}

	@Test
	void testGetUsuario() {
		Usuario aux = new Usuario("a","a",false);
		assertEquals(aux.getUsuario(),"a");
		assertNotEquals(aux.getUsuario(),"b");
	}

	@Test
	void testGetPassword() {
		Usuario aux = new Usuario("a","a",false);
		assertEquals(aux.getPassword(),"a");
		assertNotEquals(aux.getPassword(),"b");
	}

	@Test
	void testGetAdministrador() {
		Usuario aux = new Usuario("a","a",false);
		assertEquals(aux.getAdministrador(),false);
		assertNotEquals(aux.getAdministrador(),"false");
		assertNotEquals(aux.getAdministrador(),"b");
	}

}
