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
customElements.define('mi-cabecera', miCabecera);
function guardar_resena(){
    var resena = document.getElementById('resena').value;
    var autor=sessionStorage.getItem("autor");
    var titulo=sessionStorage.getItem("titulo");
    var usu=sessionStorage.getItem("usu");
    console.log(usu);
    var tipo = "1";
    $.ajax({
		type: "POST",
		url:"http://localhost:8080/tfgPagina/resenas?usu="+usu+"&resena="+resena+"&autor="+autor+"&titulo="+titulo+"&tipo="+tipo,
		success:function(){
			alert("Se ha realizado el guardado de la reseña correctamente");
			sessionStorage.removeItem("autor");
			sessionStorage.removeItem("titulo");
			window.location.href = "main.html";
		},
		error:function(){
			alert("Ha habido un error");
			window.location.href = "main.html";
		}
	})
}