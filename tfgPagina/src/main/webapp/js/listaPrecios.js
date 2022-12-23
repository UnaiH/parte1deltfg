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
//Esta funcion se emplea para obtener todos los enlaces del libro deseado junto con su precio incluso el enlace gratuito
function preparar(){
	var titulo = sessionStorage.getItem("titulo");
	var autor = sessionStorage.getItem("autor");
	var tipo = "2";
    document.getElementById("titulo").innerHTML = "Titulo: " + titulo;
    document.getElementById("autor").innerHTML = "Autor: " + autor;
    //Se realiza el llamamiento al servlet precios.
	$.ajax({
		type: "GET",
		url:"http://localhost:8080/tfgPagina/precios?titulo="+titulo+"&autor="+autor+"&tipo="+tipo,
		success:function(respuesta){
			//Se insertan en la tabla el enlace junto al precio en cada fila.
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
				//Si no se encontrara resultados se devolvera un dato con "#" que será que no hay resultados. Sino hay este dato se crea el enlace.
				if(resultados[j].trim()=="#"){
					cell2.innerHTML="No se ha encontrado resultado"
				}else{
					cell2.innerHTML='<a href="'+ resultados[j] +'">'+aux+'</a>';
				}
				j++;
				i++;
				m--;
			}
			alert("Búsqueda realizada correctamente");
		},
		error:function(){
			//Si hay algun problema se lanza un alert y se redirige a la pagina principal
			alert("Ha habido un problema para realizar la búsqueda");
			window.location.href = "main.html";
		}
	})
}