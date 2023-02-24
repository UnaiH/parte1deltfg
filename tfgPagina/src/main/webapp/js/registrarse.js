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
//Esta funcion se encarga de realizar el registro de un usuario
function registrarse(){
    var contr = "";
    var usu = document.getElementById("usuario").value;
    var contr = document.getElementById("contr").value;
    var contrConf = document.getElementById("confContr").value;
    var tieneNumero = false;
    var tieneLetraMin = false;
    var tieneLetraMax = false;
    var tieneEspecial = false;
    //Se verifica que la contrasena y la confirmacion de contrasena no sean null
    if(contr!=null && contrConf!=null){
		//Con este bucle y serie de ifs se verifica que la contrasena cumpla una serie de condiciones, que teenga un numero, una letra minuscula, una letra mayuscula y que tenga al menos un caracter especial. Sino cumple alguna se lanza un alert
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
    //Se comprueba que la contrasena tenga un minimo de 10 caracteres sino lanza un alert.
    if (contr==contrConf && tieneEspecial && tieneLetraMax && tieneNumero && tieneLetraMin && usu!=null && usu!=""){
        if(contr.length<10){
            alert("La contraseña debe tener al menos 10 caracteres");
        }else{
			//Se lanza una peticion al servlet registrarse que realiza la funcion de grabar el usuario y contrasena en la base de datos
            $.ajax({
				type: "POST",
				url:"http://localhost:8080/tfgPagina/registrarse?usuario="+usu+"&password="+contr,
				success:function(){
					//Si funciona correctamente se inicia sesion y se redirije a la pagina inicial
					alert("Se ha realizado el registro correctamente");
		            sessionStorage.setItem("iniciado", "true");
		            sessionStorage.setItem("usu", usu);
		            window.location.href = "main.html";
				},
				error:function(d,s,e){
					//Si falla se borran los campos y se lanza un alert
					document.getElementById("usuario").value = "";
		       		document.getElementById("contr").value = "";
					document.getElementById("confContr").value = "";
		       		alert("Error al registrarse. El usuario ya existe");
				}
			})
        }
    }
    else{
        if(contr!=contrConf){
			//Si la contrasena y la confirmacion de contrasena no coinciden se borran los datos introducidos en los campos de la pagina y se lanza un alerta diciendo que no coinciden
            alert("Las constraseñas no coinciden");
            document.getElementById("usuario").value = "";
            document.getElementById("contr").value = "";
            document.getElementById("confContr").value = "";
        }
    }
}