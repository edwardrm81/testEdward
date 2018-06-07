/**
 * 
 */
package com.edw.gen.modelo.acciones.generarcodigo;

import java.util.Iterator;
import java.util.Map;

import com.edw.gen.apigen.ExcepcionNegocio;
import com.edw.gen.modelo.formatearcodigo.FormatearEnJavaAcciones;
import com.edw.gen.modelo.formatearcodigo.FormatearEnJavaFachadas;
import com.edw.gen.modelo.util.Archivos;
import com.edw.gen.modelo.util.Constantes;

/**
 * @author edward
 *
 */
public class CrearFachadas {

	private Map oMapInfoBaseDatos;
	private String paqueteDestino;
	private String rutaPaqueteDestino;
	private String formatoGeneracionDeArchivos;
	
	public CrearFachadas(Map oMapInfoBaseDatos,String paqueteDestino, String rutaPaqueteDestino, String formatoGeneracionDeArchivos) {
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
		
		//Recupera info de Vistas y de Tablas
		Map oMapVistas = (Map)oMapInfoBaseDatos.get("vistas");
		Map oMapTablas = (Map)oMapInfoBaseDatos.get("tablas");
		
		//Por cada Vista se generan la Fachada y se crea el archivo correspondiente
		Iterator itVistas = oMapVistas.entrySet().iterator();
		while(itVistas.hasNext()) {
			Map.Entry oMapEntryVista = (Map.Entry) itVistas.next();
			
			//String nombreVista = (String)oMapEntryVista.getKey();
			Map oMapVista = (Map)oMapEntryVista.getValue();
			
			String nombreVista = (String) oMapVista.get("nombre");
			String nombreEnBeanVista = (String) oMapVista.get("nombreEnBean");
			String esquemaVista = (String) oMapVista.get("esquema");
			String comentarioVista = (String) oMapVista.get("comentario");
			Map mapCamposDeLaVista = (Map)oMapVista.get("campos");
			
			StringBuffer oStringBufferArchivo = new StringBuffer();
			//Crea la Fachada:        	
        	if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_JAVA)){
        		FormatearEnJavaFachadas oFormatearEnJavaFachada = new FormatearEnJavaFachadas(nombreEnBeanVista,mapCamposDeLaVista,paqueteDestino+".acciones."+nombreEnBeanVista.toLowerCase(),paqueteDestino+".vbeans",true);
        		oStringBufferArchivo = oFormatearEnJavaFachada.getFormatoFachada();
        	}
        	else throw new ExcepcionNegocio("El formato de generación no es válido");
        	// Crea el archivo de la Fachada
        	if(oStringBufferArchivo.length()>0) new Archivos().escribirArchivo(rutaPaqueteDestino+Constantes.FILE_SEPARATOR+"acciones"+Constantes.FILE_SEPARATOR+nombreEnBeanVista.toLowerCase(), "Fachada"+nombreEnBeanVista+".java", oStringBufferArchivo.toString());
		}
		
		//Por cada Tabla se generan la Fachada y se crea el archivo correspondiente
		Iterator itTablas = oMapTablas.entrySet().iterator();
		while(itTablas.hasNext()) {
			Map.Entry oMapEntryTabla = (Map.Entry) itTablas.next();
			
			//String nombreTabla = (String)oMapEntryTabla.getKey();
			Map oMapTabla = (Map)oMapEntryTabla.getValue();
			
			String nombreTabla = (String)oMapTabla.get("nombre");
			String nombreEnBeanTabla = (String)oMapTabla.get("nombreEnBean");
			String esquemaTabla = (String) oMapTabla.get("esquema");
			String comentarioTabla = (String) oMapTabla.get("comentario");
			Map mapCamposDeLaTabla = (Map)oMapTabla.get("campos");

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
	}

}
