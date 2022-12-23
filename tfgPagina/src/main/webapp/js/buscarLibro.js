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

function buscar_libro(){
	var titulo = sessionStorage.getItem("titulo");
	var autor = sessionStorage.getItem("autor");
	document.getElementById("titulo").innerHTML = "Titulo: " + titulo;
    document.getElementById("autor").innerHTML = "Autor: " + autor;
	var tipo = "1";
	console.log("Empezando busqueda");
	$.ajax({
		type: "GET",
		url:"http://localhost:8080/tfgPagina/precios?titulo="+titulo+"&autor="+autor+"&tipo="+tipo+"",
		success:function(respuesta){
			console.log(respuesta);
			k=respuesta.split(/\r|\n/);
			var precio; var enlace;
			console.log(k.length);
			for(i=0;i<k.length;i++){
				if(i==0){
					precio=k[i];
					console.log(precio);
				}if(i==2){
					enlace=k[i]
					console.log(enlace);
				}
			}
			document.getElementById("precio").innerHTML = "Precio: " + precio;
    		document.getElementById("enlace").innerHTML = "Enlace más barato";
    		if(enlace.trim()=="#"){
				document.getElementById("enlace").innerHTML="No se ha encontrado resultado";
				document.getElementById("enlace").removeAttribute("href");
			}else{
    			document.getElementById("enlace").href = enlace;
    		}
		},
		error:function(){
			sessionStorage.removeItem("titulo");
			sessionStorage.removeItem("autor");
			alert("Ha habido un problema para realizar la búsqueda");
			window.location.href = "main.html";
		}
	})
}