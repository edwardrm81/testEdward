

function validacionGuardar(){

	
	
	if(document.getElementById('form1:usuario') == null || document.getElementById('form1:usuario').value =='') {
		alert('El usuario es requerido');
		return false;
	}
	
	if(!TestInputType(document.getElementById('form1:usuario'),"[^0-9\-]","El usuario debe ser un valor entero")) {
		return false;
	}	
	
	//clave
		
	if(document.getElementById('form1:estado') == null || document.getElementById('form1:estado').value =='') {
		alert('El estado es requerido');
		return false;
	}

	if(document.getElementById('form1:nombre') == null || document.getElementById('form1:nombre').value =='') {
		alert('El nombre es requerido');
		return false;
	}
	
	if(!TestInputType(document.getElementById('form1:nombre'),"[^a-zA-Z\\s]","El nombre tiene caracteres invalidos")) {
		return false;
	}

	if(document.getElementById('form1:codigogrupo') == null || document.getElementById('form1:codigogrupo').value =='') {
		alert('El grupo es requerido');
		return false;
	}
	
	if(!TestInputType(document.getElementById('form1:codigogrupo'),"[^0-9\-]","El codigo del grupo tiene caracteres invalidos")) {
		return false;
	}
	
	
	if(document.getElementById('form1:campoFecha1') == null || document.getElementById('form1:campoFecha1').value =='') {
		alert('La fecha es requerida');
		return false;
	}
	
	if(!TestInputType(document.getElementById('form1:campoFecha1'),"[^0-9\-]","La fecha tiene caracteres invalidos")) {
		return false;
	}
	
	if(document.getElementById('form1:dinero') == null || document.getElementById('form1:dinero').value =='') {
		alert('El valor del dinero es requerida');
		return false;
	}
	
	if(!TestInputType(document.getElementById('form1:dinero'),"[^0-9\.]","El valor del dinero tiene caracteres invalidos")) {
		return false;
	}	
	
	return true;
}