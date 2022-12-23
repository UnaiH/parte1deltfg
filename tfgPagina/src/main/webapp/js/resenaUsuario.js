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
	var titulo = "";
	var autor = "";
	var tipo = "2";
	var administrador = sessionStorage.getItem("administrador");
	$.ajax({
		type: "GET",
		url:"http://localhost:8080/tfgPagina/resenas?titulo="+titulo+"&autor="+autor+"&tipo="+tipo,
		success:function(respuesta){
			var resenas = respuesta.split("|");
			var i = 1;
			console.log(respuesta);
			var numUsu = resenas[1].split(".")[0].split("/")[0].trim();
			var numRes = resenas[1].split(".")[1].split("*")[0].trim();
			resenas = respuesta.split("*");
			var tabla = document.getElementById("cuerpo");
			while (i<=numUsu){
				console.log(resenas[i]);
				if(i==1){
					var usu = resenas[0].split(".")[0].split("/")[1].trim();
				}else{
					console.log(resenas[i]);
					var usu = resenas[i-1].split(".")[0].split(";;")[1].trim();
				}
				if(i==1){
					var numRes = resenas[i-1].split("/")[0].split("|")[1].trim();
				}else{
					var numRes = resenas[i-1].split(".")[1].split("*")[0].trim();
				}
				console.log("numRes: "+numRes);
				var j = 0;
				var resAux = respuesta.split("*")[i].split(";");
				console.log(j<numRes);
				while(j<numRes){
					console.log("Pasando");
					var row = tabla.insertRow(j);
					if(j==0){
						auxiliar = resAux[j]
						var cell1 = row.insertCell(0);
						cell1.innerHTML=usu;
						var cell2 = row.insertCell(1);
						cell2.innerHTML=auxiliar.split(",")[0];
						var cell3 = row.insertCell(2);
						cell3.innerHTML=auxiliar.split(",")[2];
						var cell4 = row.insertCell(3);
						if(administrador=="true"){
							cell4.innerHTML='<button title="Eliminar" onclick=console.log("Pasa");llamarEliminar('+j+');><img src="http://localhost:8080/tfgPagina/imagenes/borrar.jpg"></button>';
						}else{
							cell4.innerHTML="";
						}
					}else{
						auxiliar = resAux[j]
						var cell1 = row.insertCell(0);
						cell1.innerHTML="";
						var cell2 = row.insertCell(1);
						cell2.innerHTML=auxiliar.split(",")[0];
						var cell3 = row.insertCell(2);
						cell3.innerHTML=auxiliar.split(",")[2];
						var cell4 = row.insertCell(3);
						if(administrador=="true"){
							cell4.innerHTML='<button class="botonElim" title="Eliminar" onclick=console.log("Pasa");llamarEliminar('+j+');><img src="http://localhost:8080/tfgPagina/imagenes/borrar.jpg"></button>';
						}else{
							cell4.innerHTML="";
						}
					}
					j++;
				}
				i++;
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