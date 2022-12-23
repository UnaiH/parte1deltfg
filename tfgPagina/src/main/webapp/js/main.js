class miCabecera extends HTMLElement{
    connectedCallback(){
        if(sessionStorage.getItem('iniciado')==null){
            sessionStorage.setItem("iniciado","false");
            sessionStorage.setItem("administrador","false");
        }
        var iniciado=sessionStorage.getItem("iniciado");
        if(iniciado=="false"){
            this.innerHTML= `
            <header id="main-header">
                <nav>
                    <ul>
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
                        <li><a onclick=cerrarSesion()>Cerrar Sesión</a></li>
                    </ul>
                </nav>
            </header>`
        }
    }
}
customElements.define('mi-cabecera', miCabecera);

function ocultar(){
    if(sessionStorage.getItem('iniciado')=="false"){
        document.getElementById("bEscribirResena").hidden=true;
        
    }
    if(sessionStorage.getItem('administrador')=="false"){
		document.getElementById("usuElim").hidden=true;
		document.getElementById("btnElim").hidden=true;
	}
}
function escribir_resena(){
    var autorN = document.getElementById("nombre").value;
    var autorA = document.getElementById("apellidos").value;
    var titulo = document.getElementById("titulo").value;
    if (autorN == "" || autorN == null){
        alert("Campo de nombre de autor vacío");
    }else{
        if (autorA == "" || autorA == null){
            alert("Campo de apellidos de autor vacío");
        }else{
            if (titulo == "" || titulo == null){
                alert("Campo de titulo vacío");
            }else{
                sessionStorage.setItem("autor", autorN +" "+ autorA);
                sessionStorage.setItem("titulo", titulo);
                window.location.href = "escribirResena.html";
            }
        }
    }
}
function buscar_libro(){
    var autorN = document.getElementById("nombre").value;
    var autorA = document.getElementById("apellidos").value;
    var titulo = document.getElementById("titulo").value;
    if (autorN == "" || autorN == null){
        alert("Campo de nombre de autor vacío");
    }else{
        if (autorA == "" || autorA == null){
            alert("Campo de apellidos de autor vacío");
        }else{
            if (titulo == "" || titulo == null){
                alert("Campo de titulo vacío");
            }else{
                sessionStorage.setItem("autor", autorN +" "+ autorA);
                sessionStorage.setItem("titulo", titulo);
                window.location.href = "buscarLibro.html";
            }
        }
    }
}
function buscar_enlaces(){
    var autorN = document.getElementById("nombre").value;
    var autorA = document.getElementById("apellidos").value;
    var titulo = document.getElementById("titulo").value;
    if (autorN == "" || autorN == null){
        alert("Campo de nombre de autor vacío");
    }else{
        if (autorA == "" || autorA == null){
            alert("Campo de apellidos de autor vacío");
        }else{
            if (titulo == "" || titulo == null){
                alert("Campo de titulo vacío");
            }else{
                sessionStorage.setItem("autor", autorN +" "+ autorA);
                sessionStorage.setItem("titulo", titulo);
                window.location.href = "listaPrecios.html";
            }
        }
    }
}
function buscar_resenas(){
    var autorN = document.getElementById("nombre").value;
    var autorA = document.getElementById("apellidos").value;
    var titulo = document.getElementById("titulo").value;
    if (autorN == "" || autorN == null){
        alert("Campo de nombre de autor vacío");
    }else{
        if (autorA == "" || autorA == null){
            alert("Campo de apellidos de autor vacío");
        }else{
            if (titulo == "" || titulo == null){
                alert("Campo de titulo vacío");
            }else{
                sessionStorage.setItem("autor", autorN +" "+ autorA);
                sessionStorage.setItem("titulo", titulo);
                window.location.href = "resenasLibro.html";
            }
        }
    }
}
function buscar_todas_resenas(){
    window.location.href = "resenaUsuario.html";
}
function eliminar_usuario(){
    var usuElim = document.getElementById("usuElim").value;
    if(usuElim!=null && usuElim!=""){
		if(usuElim != "administrador"){
	        $.ajax({
				type: "POST",
				url:"http://localhost:8080/tfgPagina/eliminarusu?usuario="+usuElim,
				success:function(){
					document.getElementById("usuElim").value="";
					alert("Se ha eliminado el usuario correctamente");
				},
				error:function(d,s,e){
		       		document.getElementById("usuElim").value="";
		       		alert("Error al eliminar usuario o el usuario no existe");
				}
			})
        } else{
			alert("No se puede eliminar el usuario administrador");
			document.getElementById("usuElim").value="";
		}
    }
}
function cerrarSesion(){
    sessionStorage.setItem("iniciado", "false");
    sessionStorage.setItem("administrador","false");
    sessionStorage.removeItem("usu");
    window.location.href = "main.html";
}