package com.edw.gen.modelo.formatearcodigo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormateadoresBD {

	public FormateadoresBD() {
		
	}
	
    /*
     * Elimina los caracteres invalidos de los comentarios  
     */
    public String getDepurarCaracteresComentario(String cadena) {
    	
    	if(cadena==null) return "";    	
		cadena = cadena.replace("/"," ");
		cadena = cadena.replace("*"," ");
		
    	return cadena;
    }

    /*
     * Elimina los caracteres extraños o inválidos que provienen de los nombres de las tablas de la Base de Datos 
     * para prepararse al uso de los Beans, y pone la primer letra en Mayúscula y el resto en minúscula 
     */
    public String getNombreEnBeanTabla(String cadena) {
    	cadena =this.getNombreEnBeanCampo(cadena);
    	return cadena.substring(0,1).toUpperCase()+cadena.substring(1);
    }
    	

    /*
     * Elimina los caracteres extraños o inválidos que provienen de los nombres de los campos de la Base de Datos 
     * para prepararse al uso de los Beans, y pone todas las letras en minúsculas 
     */
    public String getNombreEnBeanCampo(String cadena) {
    	
    	if(cadena==null) return "";
    	
		////Me parece mejor obligar a que todas las letras sean en minusculas:
		//cadena = cadena.toLowerCase();
		
		cadena = cadena.replace("á","a"); cadena = cadena.replace("à","a");
		cadena = cadena.replace("é","e"); cadena = cadena.replace("è","e");
		cadena = cadena.replace("í","i"); cadena = cadena.replace("ì","i");
		cadena = cadena.replace("ó","o"); cadena = cadena.replace("ò","o");
		cadena = cadena.replace("ú","u"); cadena = cadena.replace("ù","u");
		
		cadena = cadena.replace("Á","A"); cadena = cadena.replace("À","A");
		cadena = cadena.replace("É","E"); cadena = cadena.replace("È","E");
		cadena = cadena.replace("Í","I"); cadena = cadena.replace("Ì","I");
		cadena = cadena.replace("Ó","O"); cadena = cadena.replace("Ò","O");
		cadena = cadena.replace("Ú","U"); cadena = cadena.replace("Ù","U");

		
		//Todos los caracteres que no pertenezcal al patron definido se cambian por guión bajo: _
		//Puse en el patron las mayusculas por si me arrpiento y permito las minúsculas
		Pattern patron = Pattern.compile("[^A-Za-z0-9_]+");
		Matcher coincidencia  = patron.matcher(cadena);
		StringBuffer sb = new StringBuffer();
		boolean encontroCadena = coincidencia.find();
		while(encontroCadena) {
			coincidencia.appendReplacement(sb, "_");
			encontroCadena = coincidencia.find();
		}
		coincidencia.appendTail(sb);
		
    	return sb.toString();
    }
    
    /*
     * Retorna el nombre del campo en formato válido para JDBC  
     */
    public String getNombreSQLEnJDBC(String cadena) {    	
    	if(cadena==null) return "";
    	
    	//PENDIENTE: revisar estos dos:
		cadena = cadena.replace("-","/-");
		cadena = cadena.replace(" ","/ ");
		
    	return cadena;
    }

}
