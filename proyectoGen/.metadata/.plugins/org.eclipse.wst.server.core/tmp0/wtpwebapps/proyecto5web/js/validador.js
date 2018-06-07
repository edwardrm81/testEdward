/*
  -------------------------------------------------------------------------
		      JavaScript Form Validator (gen_validatorv31.js)
              Version 3.1.2
	Copyright (C) 2003-2008 JavaScript-Coder.com. All rights reserved.
	You can freely use this script in your Web pages.
	You may adapt this script for your own needs, provided these opening credit
    lines are kept intact.
		
	The Form validation script is distributed free from JavaScript-Coder.com
	For updates, please visit:
	http://www.javascript-coder.com/html-form/javascript-form-validation.phtml
	
	Questions & comments please send to form.val at javascript-coder.com
  -------------------------------------------------------------------------  
*/

// Para Resolver dudas: http://www.javascript-coder.com/html-form/javascript-form-validation.phtml#descriptors
// http://www.verdicio.net/~rjarl/?p=5

	// SIRVEN:
	//Entero positivo: "[^0-9]"
	//Entero: "[^0-9\-]"
	//Flotante positivo: "[^0-9\.]"
	//Flotante: "[^0-9\.\-]"
	//Alfanumerico: "[^a-zA-Z0-9]"
	//Alfanumerico con espacios: "[^a-zA-Z0-9\\s]"
	//Alfabetico con espacios: "[^a-zA-Z\\s]"
	//Fecha: "[^0-9\-]"

	//Fecha: "/^/(0?[1-9]|1[012])/(3[01]|0?[1-9]|[12]d)/d{4}/" <-- No sirve
	//Fecha: "/^/d{4}/(0?[1-9]|1[012])/(3[01]|0?[1-9]|[12]d)/" <-- No sirve
	//Fecha: "[^([0][1-9]|[12][0-9]|3[01])(/|-)(0[1-9]|1[012])\\2(\\d{4})$]"
	
	// ESTOS PARECE QUE NO SIRVE NINGUNO:
    // D.N.I.: ^\d{1,8}$
    // Entero: ^(?:\+|-)?\d+$
    // Real: ^(?:\+|-)?\d+\.\d*$
    // Hora: ^(0[1-9]|1\d|2[0-3]):([0-5]\d):([0-5]\d)$
    // Fecha: ^([0][1-9]|[12][0-9]|3[01])(/|-)(0[1-9]|1[012])\2(\d{4})$ <-- No me funciona
    // E-mail: (^[0-9a-zA-Z]+(?:[._][0-9a-zA-Z]+)*)@([0-9a-zA-Z]+(?:[._-][0-9a-zA-Z]+)*\.[0-9a-zA-Z]{2,3})$

function TestInputType(objValue,strRegExp,strError)
{
   var ret = true;

    var charpos = objValue.value.search(strRegExp); 
    if(objValue.value.length > 0 &&  charpos >= 0) 
    { 
      alert(strError);
      ret = false; 
   }//if 
 return ret;
}