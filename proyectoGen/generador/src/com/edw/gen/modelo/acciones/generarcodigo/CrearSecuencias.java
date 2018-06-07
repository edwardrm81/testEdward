/**
 * 
 */
package com.edw.gen.modelo.acciones.generarcodigo;

import java.util.Iterator;
import java.util.Map;

import com.edw.gen.apigen.ExcepcionNegocio;
import com.edw.gen.modelo.formatearcodigo.FormatearEnJavaFachadas;
import com.edw.gen.modelo.formatearcodigo.FormatearEnJavaSecuenciasIncrementos;
import com.edw.gen.modelo.util.Archivos;
import com.edw.gen.modelo.util.Constantes;

/**
 * @author edward
 *
 */
public class CrearSecuencias {

	private Map oMapInfoBaseDatos;
	private String paqueteDestino;
	private String rutaPaqueteDestino;
	private String formatoGeneracionDeArchivos;
	
	public CrearSecuencias(Map oMapInfoBaseDatos,String paqueteDestino, String rutaPaqueteDestino, String formatoGeneracionDeArchivos) {
		this.oMapInfoBaseDatos = oMapInfoBaseDatos;
		this.paqueteDestino = paqueteDestino;
		this.rutaPaqueteDestino = rutaPaqueteDestino;
		this.formatoGeneracionDeArchivos = formatoGeneracionDeArchivos;
	}

	public void crear() throws Exception {
		if(oMapInfoBaseDatos==null) throw new ExcepcionNegocio("No ha ingresado los información de la Base de Datos");
		if(paqueteDestino==null || paqueteDestino.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino");
		if(rutaPaqueteDestino==null || rutaPaqueteDestino.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado la ruta destino");
		if(formatoGeneracionDeArchivos==null || formatoGeneracionDeArchivos.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el formato de generación de archivos");
		
		//Recupera info de Tablas
		Map oMapTablas = (Map)oMapInfoBaseDatos.get("tablas");

		StringBuffer oStringBufferArchivo = new StringBuffer();
		//Crea la Fachada:
    	if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_JAVA)){
    		FormatearEnJavaSecuenciasIncrementos oFormatearEnJavaSecuencias = new FormatearEnJavaSecuenciasIncrementos(oMapTablas,paqueteDestino+".secuenciasincrementos");
    		oStringBufferArchivo = oFormatearEnJavaSecuencias.getFormatoSecuenciasIncrementos();
    	}
    	else throw new ExcepcionNegocio("El formato de generación no es válido");
    	// Crea el archivo de las Secuencias
    	if(oStringBufferArchivo.length()>0) new Archivos().escribirArchivo(rutaPaqueteDestino+Constantes.FILE_SEPARATOR+"secuenciasincrementos", "SecuenciasIncrementos.java", oStringBufferArchivo.toString());

    	
		/*
		//Por cada Tabla se generan la Fachada y se crea el archivo correspondiente
		Iterator itTablas = oMapTablas.entrySet().iterator();
		while(itTablas.hasNext()) {
			Map.Entry oMapEntryTabla = (Map.Entry) itTablas.next();
			
			Map oMapTabla = (Map)oMapEntryTabla.getValue();
			
			String nombreTabla = (String)oMapTabla.get("nombre");
			String nombreEnBeanTabla = (String)oMapTabla.get("nombreEnBean");
			String esquemaTabla = (String) oMapTabla.get("esquema");
			
			StringBuffer oStringBufferArchivo = new StringBuffer();
			//Crea la Fachada:
        	if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_JAVA)){
        		FormatearEnJavaFachadas oFormatearEnJavaFachada = new FormatearEnJavaFachadas(nombreEnBeanTabla,mapCamposDeLaTabla,paqueteDestino+".acciones."+nombreEnBeanTabla.toLowerCase(),paqueteDestino+".beans",false);
        		oStringBufferArchivo = oFormatearEnJavaFachada.getFormatoFachada();
        	}
        	else throw new ExcepcionNegocio("El formato de generación no es válido");
        	// Crea el archivo de la Fachada
        	if(oStringBufferArchivo.length()>0) new Archivos().escribirArchivo(rutaPaqueteDestino+Constantes.FILE_SEPARATOR+"acciones"+Constantes.FILE_SEPARATOR+nombreEnBeanTabla.toLowerCase(), "Fachada"+nombreEnBeanTabla+".java", oStringBufferArchivo.toString());
		}
		*/
	}

}
