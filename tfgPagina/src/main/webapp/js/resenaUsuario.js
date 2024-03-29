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
//Esta funcion se llama al cargar la pagina correspondiente y se emplea para obtener los datos necesarios para la pagina web esto es las resenas con su usuario.
function preparar(){
	var titulo = "";
	var autor = "";
	var tipo = "2";
	var administrador = sessionStorage.getItem("administrador");
	//Se realiza la llamada al servlet para obtener los datos de las resenas
	$.ajax({
		type: "GET",
		url:"http://localhost:8080/tfgPagina/resenas?titulo="+titulo+"&autor="+autor+"&tipo="+tipo,
		success:function(respuesta){
			//Este codigo se emplea si la llamada al servlet es exitosa
			var resenas = respuesta.split("|");
			var i = 1;
			console.log(respuesta);
			var numUsu = resenas[1].split(".")[0].split("/")[0].trim();
			var numRes = resenas[1].split(".")[1].split("*")[0].trim();
			resenas = respuesta.split("*");
			var tabla = document.getElementById("cuerpo");
			var j = 0;
			while (i<=numUsu){
				console.log(resenas[i]);
				if(i==1){
					var usu = resenas[0].split(".")[0].split("/")[1].trim();
				}else{
					var usu = respuesta.split(";;")[i-1].split(".")[0].trim();
				}
				if(i==1){
					var numRes = resenas[i-1].split(".")[1].split("*")[0].trim();
				}else{
					var numRes = respuesta.split(";;")[i-1].split(".")[1].split("*")[0].trim();
				}
				var resAux = respuesta.split("*")[i].split(";");
				//Este bucle se realiza para introducir los datos en la tabla
				while(j<numRes){
					console.log("Pasando");
					//Se crea la fila para la tabla
					var row = tabla.insertRow(j);
					//Al ser distinto el primer registro del usuario en la tabla se modifica un poco el codigo
						auxiliar = resAux[j]
						//Se crea la celda correspondiente de esa fila
						var cell1 = row.insertCell(0);
						cell1.innerHTML=usu;
						var cell2 = row.insertCell(1);
						cell2.innerHTML=auxiliar.split(",")[0];
						var cell3 = row.insertCell(2);
						cell3.innerHTML=auxiliar.split(",")[1];
						var cell4 = row.insertCell(3);
						cell4.innerHTML=auxiliar.split(",")[2];
						var cell5 = row.insertCell(4);
						//Si el usuario es un administrador se introduce un boton para eliminar la resena y sino no lo introduce.
						if(administrador=="true"){
							cell5.innerHTML='<button title="Eliminar" onclick=llamarEliminar('+j+');><img src="http://localhost:8080/tfgPagina/imagenes/borrar.jpg"></button>';
						}else{
							cell5.innerHTML="";
						}
					j++;
				}
				i++;
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
	borrarResena(document.getElementById("cuerpo").rows[i].cells[0].innerHTML.trim(),document.getElementById("cuerpo").rows[i].cells[1].innerHTML.trim(),document.getElementById("cuerpo").rows[i].cells[2].innerHTML.trim(),"resenasLibro.html");
}