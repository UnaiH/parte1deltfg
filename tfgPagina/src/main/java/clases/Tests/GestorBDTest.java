package clases.Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import clases.GestorBD;
import clases.ListaResenas;
import clases.ListaResenasAux;

class GestorBDTest {

	@Test
	void testMiGestorBD() {
		GestorBD baseDatos = GestorBD.miGestorBD();
		assertNotNull(baseDatos);
	}

	@Test
	void testRegistrarse() {
		GestorBD baseDatos = GestorBD.miGestorBD();
		assertTrue(baseDatos.registrarse("usuarioTest2", "usuarioTest2"));
		assertFalse(baseDatos.registrarse("usuarioTest2", "usuarioTest2"));
		assertTrue(baseDatos.eliminarUsuario("usuarioTest2"));
	}

	@Test
	void testMostrarTodasResenas() {
		GestorBD baseDatos = GestorBD.miGestorBD();
		HashMap<String,ListaResenasAux> todasResenas = null;
		todasResenas = baseDatos.mostrarTodasResenas();
		assertNotNull(todasResenas);
		Object[] llaves = todasResenas.keySet().toArray();
		System.out.println("Hay "+llaves.length);
		assertEquals(llaves.length,1);
	}

	@Test
	void testMostrarResena() {
		GestorBD baseDatos = GestorBD.miGestorBD();
		ListaResenas todasResenas = null;
		todasResenas = baseDatos.mostrarResena("libroTest","autorTest");
		assertEquals(todasResenas.size(),0);
		baseDatos.anadirLibro("libroTest", "autorTest");
		baseDatos.registrarse("usuarioTest3", "usuarioTest3");
		baseDatos.escribirResena("usuarioTest3", "libroTest", "autorTest", "test");
		todasResenas = baseDatos. mostrarResena("libroTest","autorTest");
		assertNotNull(todasResenas);
		assertEquals(todasResenas.size(),1);
		baseDatos.eliminarResena("libroTest", "autorTest", "usuarioTest3");
		baseDatos.eliminarUsuario("usuarioTest3");
	}

	@Test
	void testIniciarSesion() {
		GestorBD baseDatos = GestorBD.miGestorBD();
		assertTrue(baseDatos.registrarse("usuarioTest4", "usuarioTest4"));
		assertTrue(baseDatos.iniciarSesion("usuarioTest4", "usuarioTest4"));
		assertFalse(baseDatos.iniciarSesion("usuarioTest4", "usuarioTest2"));
		assertTrue(baseDatos.eliminarUsuario("usuarioTest4"));
	}

	@Test
	void testEscribirResena() {
		GestorBD baseDatos = GestorBD.miGestorBD();
		HashMap<String,ListaResenasAux> todasResenas = null;
		todasResenas = baseDatos.mostrarTodasResenas();
		assertNotNull(todasResenas);
		Object[] llaves = todasResenas.keySet().toArray();
		assertEquals(llaves.length,1);
		baseDatos.anadirLibro("libroTest", "autorTest");
		baseDatos.registrarse("usuarioTest5", "usuarioTest5");
		baseDatos.escribirResena("usuarioTest5", "libroTest", "autorTest", "test");
		todasResenas = baseDatos.mostrarTodasResenas();
		assertNotNull(todasResenas);
		llaves = todasResenas.keySet().toArray();
		assertEquals(llaves.length,2);
		baseDatos.eliminarResena("libroTest", "autorTest", "usuarioTest5");
		baseDatos.eliminarUsuario("usuarioTest5");
	}

	@Test
	void testEliminarUsuario() {
		GestorBD baseDatos = GestorBD.miGestorBD();
		assertTrue(baseDatos.registrarse("usuarioTest6", "usuarioTest6"));
		assertTrue(baseDatos.eliminarUsuario("usuarioTest6"));
		assertFalse(baseDatos.eliminarUsuario("usuarioTest6"));
	}

	@Test
	void testEliminarResena() {
		GestorBD baseDatos = GestorBD.miGestorBD();
		HashMap<String,ListaResenasAux> todasResenas = null;
		todasResenas = baseDatos.mostrarTodasResenas();
		assertNotNull(todasResenas);
		Object[] llaves = todasResenas.keySet().toArray();
		assertEquals(llaves.length,1);
		baseDatos.anadirLibro("libroTest", "autorTest");
		baseDatos.registrarse("usuarioTest7", "usuarioTest7");
		baseDatos.escribirResena("usuarioTest7", "libroTest", "autorTest", "test");
		todasResenas = baseDatos.mostrarTodasResenas();
		assertNotNull(todasResenas);
		llaves = todasResenas.keySet().toArray();
		assertEquals(llaves.length,2);
		baseDatos.eliminarResena("libroTest", "autorTest", "usuarioTest7");
		baseDatos.eliminarUsuario("usuarioTest7");
	}

	@Test
	void testAnadirLibro() {
	}

}
