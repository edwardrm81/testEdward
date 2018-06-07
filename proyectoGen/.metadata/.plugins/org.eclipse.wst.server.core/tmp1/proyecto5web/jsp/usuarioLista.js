

function validacionGuardar(){
	if(document.getElementById('form1:usuario').value==null || document.getElementById('form1:usuario').value=='') {
		alert('Debe ingresar el usuario');
		return false;
	}
	return true;
}