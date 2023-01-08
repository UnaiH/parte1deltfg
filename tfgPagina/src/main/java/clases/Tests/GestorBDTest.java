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
		assertTrue(baseDatos.registrarse("usuarioTest1", "usuarioTest1"));
		assertFalse(baseDatos.registrarse("usuarioTest1", "usuarioTest1"));
		assertTrue(baseDatos.eliminarUsuario("usuarioTest1"));
	}

	@Test
	void testMostrarTodasResenas() {
		GestorBD baseDatos = GestorBD.miGestorBD();
		HashMap<String,ListaResenasAux> todasResenas = null;
		todasResenas = baseDatos.mostrarTodasResenas();
		assertNotNull(todasResenas);
		Object[] llaves = todasResenas.keySet().toArray();
		assertEquals(llaves.length,2);
	}

	@Test
	void testMostrarResena() {
		GestorBD baseDatos = GestorBD.miGestorBD();
		ListaResenas todasResenas = null;
		todasResenas = baseDatos.mostrarResena("libroTest","autorTest");
		assertEquals(todasResenas.size(),0);
		baseDatos.anadirLibro("libroTest", "autorTest");
		baseDatos.registrarse("usuarioTest1", "usuarioTest1");
		baseDatos.escribirResena("usuarioTest1", "libroTest", "autorTest", "test");
		todasResenas = baseDatos. mostrarResena("libroTest","autorTest");
		assertNotNull(todasResenas);
		assertEquals(todasResenas.size(),1);
		baseDatos.eliminarUsuario("usuarioTest1");
		baseDatos.eliminarResena("libroTest", "autorTest", "usuarioTest1");
	}

	@Test
	void testIniciarSesion() {
		GestorBD baseDatos = GestorBD.miGestorBD();
		assertTrue(baseDatos.registrarse("usuarioTest1", "usuarioTest1"));
		assertTrue(baseDatos.iniciarSesion("usuarioTest1", "usuarioTest1"));
		assertFalse(baseDatos.iniciarSesion("usuarioTest1", "usuarioTest2"));
		assertTrue(baseDatos.eliminarUsuario("usuarioTest1"));
	}

	@Test
	void testEscribirResena() {
		GestorBD baseDatos = GestorBD.miGestorBD();
		HashMap<String,ListaResenasAux> todasResenas = null;
		todasResenas = baseDatos.mostrarTodasResenas();
		assertNotNull(todasResenas);
		Object[] llaves = todasResenas.keySet().toArray();
		assertEquals(llaves.length,2);
		baseDatos.anadirLibro("libroTest", "autorTest");
		baseDatos.registrarse("usuarioTest1", "usuarioTest1");
		baseDatos.escribirResena("usuarioTest1", "libroTest", "autorTest", "test");
		todasResenas = baseDatos.mostrarTodasResenas();
		assertNotNull(todasResenas);
		llaves = todasResenas.keySet().toArray();
		assertEquals(llaves.length,3);
		baseDatos.eliminarResena("libroTest", "autorTest", "usuarioTest1");
		baseDatos.eliminarUsuario("usuarioTest1");
	}

	@Test
	void testEliminarUsuario() {
		GestorBD baseDatos = GestorBD.miGestorBD();
		assertTrue(baseDatos.registrarse("usuarioTest1", "usuarioTest1"));
		assertTrue(baseDatos.eliminarUsuario("usuarioTest1"));
		assertFalse(baseDatos.eliminarUsuario("usuarioTest1"));
	}

	@Test
	void testEliminarResena() {
		GestorBD baseDatos = GestorBD.miGestorBD();
		HashMap<String,ListaResenasAux> todasResenas = null;
		todasResenas = baseDatos.mostrarTodasResenas();
		assertNotNull(todasResenas);
		Object[] llaves = todasResenas.keySet().toArray();
		assertEquals(llaves.length,2);
		baseDatos.anadirLibro("libroTest", "autorTest");
		baseDatos.registrarse("usuarioTest1", "usuarioTest1");
		baseDatos.escribirResena("usuarioTest1", "libroTest", "autorTest", "test");
		todasResenas = baseDatos.mostrarTodasResenas();
		assertNotNull(todasResenas);
		llaves = todasResenas.keySet().toArray();
		assertEquals(llaves.length,3);
		baseDatos.eliminarUsuario("usuarioTest1");
		baseDatos.eliminarResena("libroTest", "autorTest", "usuarioTest1");
	}

	@Test
	void testAnadirLibro() {
	}

}
