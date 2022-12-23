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
customElements.define('mi-cabecera', miCabecera);

function iniciar_sesion(){
    var usu = document.getElementById("usuario").value;
    var contr = document.getElementById("contrasena").value;
    var admin = true;
    $.ajax({
		type: "GET",
		url:"http://localhost:8080/tfgPagina/inicioSesion?usuario="+usu+"&password="+contr,
		success:function(res){
			sessionStorage.setItem('usu',usu);
			sessionStorage.setItem("iniciado","true");
			if(admin){
				sessionStorage.setItem("administrador","true");
			}
        	window.location.href = "main.html";
		},
		error:function(d,s,e){
			document.getElementById("usuario").value = "";
       		document.getElementById("contrasena").value = "";
       		alert("Error al iniciar sesión. El usuario o la contraseña es incorrecta");
		}
	})
}