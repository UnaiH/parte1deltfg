//Esta clase es para definir la cabecera de la pagina;
class miCabecera extends HTMLElement{
    connectedCallback(){
        if(sessionStorage.getItem('iniciado')==null){
            sessionStorage.setItem("iniciado","false");
        }
        var iniciado=sessionStorage.getItem("iniciado");
        if(iniciado=="false"){
            this.innerHTML= `
            <header id="main-header">
                <nav>
                    <ul>
                        <li><a href="main.html">Inicio</a></li>
                        <li><a href="registrarse.html">Registrarse</a></li>
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
                        <li><a href="registrarse.html">Registrarse</a></li>
                    </ul>
                </nav>
            </header>`
        }
    }
}
//Permite mostrar en la pagina la cabecera;
customElements.define('mi-cabecera', miCabecera);
//Esta funcion realiza el inicio de sesion
function iniciar_sesion(){
    var usu = document.getElementById("usuario").value;
    var contr = document.getElementById("contrasena").value;
    //Esta llamada al servlet inicioSesion no iniciara sesion si se devuelve un error (si la contrasena es incorrecta por ejemplo)
    $.ajax({
		type: "GET",
		url:"http://localhost:8080/tfgPagina/inicioSesion?usuario="+usu+"&password="+contr,
		success:function(res){
			//Se guarda en memoria el usuario, que se ha iniciado, se verifica si el usuario es administrador y se redirige a la pagina principal
			sessionStorage.setItem('usu',usu);
			sessionStorage.setItem("iniciado","true");
			if(usu=="administrador"){
				sessionStorage.setItem("administrador","true");
			}
        	window.location.href = "main.html";
		},
		error:function(d,s,e){
			//Si falla la peticion se borran los datos de los campos de la pagina y se lanza un alert indicando que la contrasena o el usuario son incorrectos
			document.getElementById("usuario").value = "";
       		document.getElementById("contrasena").value = "";
       		alert("Error al iniciar sesión. El usuario o la contraseña es incorrecta");
		}
	})
}