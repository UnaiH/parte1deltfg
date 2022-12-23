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

function registrarse(){
    var contr = "";
    var usu = document.getElementById("usuario").value;
    var contr = document.getElementById("contr").value;
    var contrConf = document.getElementById("confContr").value;
    var tieneNumero = false;
    var tieneLetraMin = false;
    var tieneLetraMax = false;
    var tieneEspecial = false;
    if(contr!=null && contrConf!=null){
        for(var letra=0; letra < contr.length; letra++){
            if(!isNaN(contr.charAt(letra))){
                tieneNumero=true;
            }
            else{
                if(/[a-z]/.test(contr.charAt(letra))){
                    tieneLetraMin=true;
                }else{
                    if(/[A-Z]/.test(contr.charAt(letra))){
                        tieneLetraMax=true;
                    }else{
                        tieneEspecial=true;
                    }
                }
            }
        }
        if(!tieneNumero){
            alert("La contraseña debe tener al menos un número");
        }else{
            if(!tieneLetraMin){
                alert("La contraseña debe tener al menos una letra minúscula");
            }
            else{
                if(!tieneLetraMax){
                    alert("La contraseña debe tener al menos una letra mayúscula");
                }
                else{
                    if(!tieneEspecial){
                        alert("La contraseña debe tener al menos un caracter especial");
                    }
                }
            }
        }
    }
    if (contr==contrConf && tieneEspecial && tieneLetraMax && tieneNumero && tieneLetraMin && usu!=null && usu!=""){
        if(contr.length<10){
            alert("La contraseña debe tener al menos 10 caracteres");
        }else{
            $.ajax({
				type: "POST",
				url:"http://localhost:8080/tfgPagina/registrarse?usuario="+usu+"&password="+contr,
				success:function(){
					alert("Se ha realizado el registro correctamente");
				},
				error:function(d,s,e){
					document.getElementById("usuario").value = "";
		       		document.getElementById("contrasena").value = "";
		       		alert("Error al registrarse. El usuario ya existe");
				}
			})
            sessionStorage.setItem("iniciado", "true");
            sessionStorage.setItem("usu", usu);
            window.location.href = "main.html";
        }
    }
    else{
        if(contr!=contrConf){
            alert("Las constraseñas no coinciden")
            document.getElementById("usuario").value = "";
            document.getElementById("contr").value = "";
            document.getElementById("confContr").value = "";
        }
    }
}