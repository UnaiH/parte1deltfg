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
//Esta funcion se encarga de obtener la informacion del libro (precio más bajo y enlace a este)
function buscar_libro(){
	var titulo = sessionStorage.getItem("titulo");
	var autor = sessionStorage.getItem("autor");
	document.getElementById("titulo").innerHTML = "Titulo: " + titulo;
    document.getElementById("autor").innerHTML = "Autor: " + autor;
	var tipo = "1";
	console.log("Empezando busqueda");
	//Esta llamada al servlet precios se emplea para obtener el precio minimo y el link.
	$.ajax({
		type: "GET",
		url:"http://localhost:8080/tfgPagina/precios?titulo="+titulo+"&autor="+autor+"&tipo="+tipo+"",
		success:function(respuesta){
			//Si la peticion es correcta devolvera el enlace con el precio y se escribira en la pagina web
			k=respuesta.split(/\r|\n/);
			var precio; var enlace;
			console.log(k.length);
			for(i=0;i<k.length;i++){
				if(i==0){
					precio=k[i];
				}if(i==2){
					enlace=k[i];
				}
			}
			document.getElementById("precio").innerHTML = "Precio: " + precio;
    		document.getElementById("enlace").innerHTML = "Enlace más barato";
    		//Si el enlace tuviera el valor de "#" entonces se considera que no se ha conseguido un enlace valido con un precio.
    		if(enlace.trim()=="#"){
				document.getElementById("enlace").innerHTML="No se ha encontrado resultado";
				document.getElementById("enlace").removeAttribute("href");
			}else{
			//Sino se crea un enlace con el link que se ha conseguido de la peticio
    			document.getElementById("enlace").href = enlace;
    		}
		},
		error:function(){
			//Si falla la peticion se lanza un alert, se borra de la memoria titulo y autor y se redirige a la pagina principal
			sessionStorage.removeItem("titulo");
			sessionStorage.removeItem("autor");
			alert("Ha habido un problema para realizar la búsqueda");
			window.location.href = "main.html";
		}
	})
}