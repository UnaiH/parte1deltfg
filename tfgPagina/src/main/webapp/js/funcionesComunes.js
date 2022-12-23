function borrarResena(usu,autor,titulo,ventana){
	var tipo = "2";
    console.log(usu);
    console.log(titulo);
    console.log(autor);
    console.log(ventana);
    alert(autor);
	$.ajax({
		type: "POST",
		url:"http://localhost:8080/tfgPagina/resenas?usu="+usu+"&autor="+autor+"&titulo="+titulo+"&tipo="+tipo,
		success:function(){
			alert("Se ha eliminado la resena correctamente");
			window.location.href = ventana;
		},
			error:function(d,s,e){
		    alert("Error al eliminar resena");
			}
	})
}
function cerrarSesion(){
	sessionStorage.setItem("administrador","false");
    sessionStorage.setItem("iniciado", "false");
    sessionStorage.removeItem("usu");
    window.location.href = "main.html";
}