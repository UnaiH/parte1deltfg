class miCabecera extends HTMLElement{
    connectedCallback(){
       var iniciado=sessionStorage.getItem("iniciado");
        if(iniciado=="false"){
            this.innerHTML= `
            <header id="main-header">
                <nav>
                    <ul>
                        <li><a href="main.html">Inicio</a></li>
                        <li><a href="inicioSesion.html">Iniciar Sesión</a></li>
                    </ul>
                </nav>
            </header>`
        }
        else{
            this.innerHTML= `
            <header id="main-header">
                <nav>
                    <ul>
                        <li><a href="main.html">Inicio</a></li>
                        <li><a onclick=cerrarSesion()>Cerrar Sesión</a></li>
                    </ul>
                </nav>
            </header>`
        }
    }
}
customElements.define('mi-cabecera', miCabecera);
function preparar(){
	var titulo = sessionStorage.getItem("titulo");
	var autor = sessionStorage.getItem("autor");
	var tipo = "1";
	var administrador = sessionStorage.getItem("administrador");
    document.getElementById("titulo").innerHTML = "Titulo: "+titulo;
    document.getElementById("autor").innerHTML = "Autor: "+autor;
	$.ajax({
		type: "GET",
		url:"http://localhost:8080/tfgPagina/resenas?titulo="+titulo+"&autor="+autor+"&tipo="+tipo,
		success:function(respuesta){
			var resultados = respuesta.split(",");
			var long = resultados[0];
			var i = 0;
			var j = 1;
			var k = 0;
			var m = long;
			var tabla = document.getElementById("cuerpo");
			while(long > i){
				var l = 0;
				var row = tabla.insertRow(k);
				var cell1 = row.insertCell(l);
				l++;
				var aux = "Enlace" + m;
				var cell2 = row.insertCell(l);
				cell1.innerHTML=resultados[j];
				j++;
				cell2.innerHTML=resultados[j];
				j++;
				if(administrador=="true"){
					l++;
					var cell3 = row.insertCell(l);
					cell3.innerHTML='<button title="Eliminar" onclick=console.log("Pasa");llamarEliminar('+k+');><img src="http://localhost:8080/tfgPagina/imagenes/borrar.jpg"></button>';
				}
				i++;
				k++;
				m--;
			}
		},
		error:function(){
			alert("Ha habido un problema para realizar la búsqueda");
			window.location.href = "main.html";
		}
	})
}
function llamarEliminar(i){
	borrarResena(document.getElementById("cuerpo").rows[i].cells[0].innerHTML.trim(),document.getElementById("autor").innerHTML.split(": ")[1],document.getElementById("titulo").innerHTML.split(": ")[1],"resenasLibro.html");
}