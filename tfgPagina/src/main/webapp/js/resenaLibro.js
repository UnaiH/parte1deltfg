//Esta clase es para definir la cabecera de la pagina;
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
//Permite mostrar en la pagina la cabecera;
customElements.define('mi-cabecera', miCabecera);
//Esta funcion se llama al cargar la pagina correspondiente y se emplea para obtener los datos necesarios para la pagina web esto es las resenas de un libro concreto.
function preparar(){
	var titulo = sessionStorage.getItem("titulo");
	var autor = sessionStorage.getItem("autor");
	var tipo = "1";
	var administrador = sessionStorage.getItem("administrador");
	//Se escriben en la pagina web el titulo y el autor.
    document.getElementById("titulo").innerHTML = "Titulo: "+titulo;
    document.getElementById("autor").innerHTML = "Autor: "+autor;
    //Se realiza una llamada para obtener los datos de las resenas de un libro en concreto
	$.ajax({
		type: "GET",
		url:"http://localhost:8080/tfgPagina/resenas?titulo="+titulo+"&autor="+autor+"&tipo="+tipo,
		success:function(respuesta){
			//El codigo que se usa si es exitosa
			var resultados = respuesta.split(",");
			var long = resultados[0];
			var i = 0;
			var j = 1;
			var k = 0;
			var m = long;
			var tabla = document.getElementById("cuerpo");
			//Aqui el codigo que introduce la informacion en la tabla de la pagina
			while(long > i){
				var l = 0;
				var row = tabla.insertRow(k);
				var cell1 = row.insertCell(l);
				l++;
				var cell2 = row.insertCell(l);
				cell1.innerHTML=resultados[j];
				j++;
				cell2.innerHTML=resultados[j];
				j++;
				//Si el usuario es administrador se creara el boton para eliminar la resena
				if(administrador=="true"){
					l++;
					var cell3 = row.insertCell(l);
					cell3.innerHTML='<button title="Eliminar" onclick=llamarEliminar('+k+');><img src="http://localhost:8080/tfgPagina/imagenes/borrar.jpg"></button>';
				}
				i++;
				k++;
				m--;
			}
		},
		error:function(){
			//Si se produce un error en la llamada al servler o en la obtencion de datos se lanzara un mensaje mediante un alert y se redirigira a la pagina main.html al cerrar el alert
			alert("Ha habido un problema para realizar la búsqueda");
			window.location.href = "main.html";
		}
	})
}
//Esta funcion realiza la llamada a la funcion comun llamada borrar resena que realiza la funcion que su nombre indica.
function llamarEliminar(i){
	borrarResena(document.getElementById("cuerpo").rows[i].cells[0].innerHTML.trim(),document.getElementById("autor").innerHTML.split(": ")[1],document.getElementById("titulo").innerHTML.split(": ")[1],"resenasLibro.html");
}