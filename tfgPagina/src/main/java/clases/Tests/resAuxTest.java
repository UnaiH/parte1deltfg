package clases.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import clases.Resena;
import clases.Usuario;
import clases.resAux;

class resAuxTest {

	@Test
	void testResAux() {
		resAux aux = new resAux("a","a","a","a");
		resAux aux2=null;
		assertNotNull(aux);
		assertEquals(aux.getAut(),"a");
		assertEquals(aux.getRes(),"a");
		assertEquals(aux.getTit(),"a");
		assertEquals(aux.getUsu(),"a");
		assertNull(aux2);
	}

	@Test
	void testGetUsu() {
		resAux aux = new resAux("a","a","a","a");
		assertEquals(aux.getUsu(),"a");
		assertNotEquals(aux.getUsu(),"b");
	}

	@Test
	void testGetRes() {
		resAux aux = new resAux("a","a","a","a");
		assertEquals(aux.getRes(),"a");
		assertNotEquals(aux.getRes(),"b");
	}

	@Test
	void testGetTit() {
		resAux aux = new resAux("a","a","a","a");
		assertEquals(aux.getTit(),"a");
		assertNotEquals(aux.getTit(),"b");
	}

	@Test
	void testGetAut() {
		resAux aux = new resAux("a","a","a","a");
		assertEquals(aux.getAut(),"a");
		assertNotEquals(aux.getAut(),"b");
	}

}
