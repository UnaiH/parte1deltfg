//Esta clase es para definir la cabecera de la pagina;
class miCabecera extends HTMLElement{
    connectedCallback(){
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
//Permite mostrar en la pagina la cabecera
customElements.define('mi-cabecera', miCabecera);
//Esta funcion premite grabar la resena en la base de datos
function guardar_resena(){
    var resena = document.getElementById('resena').value;
    var autor=sessionStorage.getItem("autor");
    var titulo=sessionStorage.getItem("titulo");
    var usu=sessionStorage.getItem("usu");
    var tipo = "1";
    //Se realiza la llamada al servlet resenas para guardarla.
    $.ajax({
		type: "POST",
		url:"http://localhost:8080/tfgPagina/resenas?usu="+usu+"&resena="+resena+"&autor="+autor+"&titulo="+titulo+"&tipo="+tipo,
		success:function(){
			//Si es correcta se lanza un alert informandolo, se elimina la informacion del almacenamiento correspondiente a autor y titulo y se devuelve al usuario a la pagina principal
			alert("Se ha realizado el guardado de la reseña correctamente");
			sessionStorage.removeItem("autor");
			sessionStorage.removeItem("titulo");
			window.location.href = "main.html";
		},
		error:function(){
			//Si hay un error se lanza un mensaje por alert y se devuelve a la pagina principal
			alert("Ha habido un error");
			window.location.href = "main.html";
		}
	})
}