//Esta funcion hace una llamada al servlet para realizar la eliminacion de una resena concreta.
function borrarResena(usu,autor,titulo,ventana){
	var tipo = "2";
    console.log(usu);
    console.log(titulo);
    console.log(autor);
    console.log(ventana);
	$.ajax({
		type: "POST",
		url:"http://localhost:8080/tfgPagina/resenas?usu="+usu+"&autor="+autor+"&titulo="+titulo+"&tipo="+tipo,
		success:function(){
			alert("Se ha eliminado la resena correctamente");
			window.location.href = "main.html";
		},
			error:function(d,s,e){
		    alert("Error al eliminar resena");
		    window.location.href = "main.html";
			}
	})
}
//Esta funcion realiza el "cierre de la sesion". Esto es, es el codigo que implementa la funcionalidad del boton cerrar sesion.
function cerrarSesion(){
	sessionStorage.setItem("administrador","false");
    sessionStorage.setItem("iniciado", "false");
    sessionStorage.removeItem("usu");
    window.location.href = "main.html";
}