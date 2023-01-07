//Esta clase es para definir la cabecera de la pagina;
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
//Permite mostrar en la pagina la cabecera;
customElements.define('mi-cabecera', miCabecera);
//Esta funcion se encarga de ocultar el boton de eliminar usuario junto al campo para escribir si el usuario no es administrador y oculta el boton de escribir resena si no ha iniciado sesion
function ocultar(){
    if(sessionStorage.getItem('iniciado')=="false"){
        document.getElementById("bEscribirResena").hidden=true;
        
    }
    if(sessionStorage.getItem('administrador')=="false"){
		document.getElementById("usuElim").hidden=true;
		document.getElementById("btnElim").hidden=true;
	}
}
//Se verifica en esta fucion que los campos de titulo, nombre del autor y apellidos del autor no estan vacios si lo estan se lanza un mensaje mediante un alert. Si no se redirige a la pagina para escribir la resena
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
//Se verifica en esta fucion que los campos de titulo, nombre del autor y apellidos del autor no estan vacios si lo estan se lanza un mensaje mediante un alert. Si no se redirige ala pagina de buscar libro 
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
////Se verifica en esta fucion que los campos de titulo, nombre del autor y apellidos del autor no estan vacios si lo estan se lanza un mensaje mediante un alert. Si no se redirige a la pagina de lista de precios
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
//Se verifica en esta fucion que los campos de titulo, nombre del autor y apellidos del autor no estan vacios si lo estan se lanza un mensaje mediante un alert. Si no se redirige a la pagina resenas del libro
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
//Esta funcion redirige a la pagina que muestra las resenas en funcion de los usuarios
function buscar_todas_resenas(){
    window.location.href = "resenaUsuario.html";
}
//Esta funcion se encarga de eliminar el usuario indicado en el campo correspondiente
function eliminar_usuario(){
    var usuElim = document.getElementById("usuElim").value;
    if(usuElim!=null && usuElim!=""){
		//Se verifica que se pretenda eliminar el usuario administrador para evitar esa posibilidad. Si es el usuario administrador se lanza un alert y se borra lo escrito en el campo correspondiente.
		if(usuElim != "administrador"){
	        $.ajax({
				type: "GET",
				url:"http://localhost:8080/tfgPagina/eliminarusu?usuario="+usuElim,
				success:function(){
					//Si funciona correctamente se borra el campo correspondiente y se lanza un mensaje
					document.getElementById("usuElim").value="";
					alert("Se ha eliminado el usuario correctamente");
				},
				error:function(d,s,e){
					//Si  da error se borra el campo correspondiente y se lanza un mensaje
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