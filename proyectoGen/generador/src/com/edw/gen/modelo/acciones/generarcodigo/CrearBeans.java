package com.edw.gen.modelo.acciones.generarcodigo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.edw.gen.apigen.ExcepcionNegocio;
import com.edw.gen.modelo.formatearcodigo.FormatearEnJavaBeans;
import com.edw.gen.modelo.util.Archivos;
import com.edw.gen.modelo.util.Constantes;

public class CrearBeans {

	private Map oMapInfoBaseDatos;
	private String paqueteDestino;
	private String rutaPaqueteDestino;
	private String formatoGeneracionDeArchivos;
	
	public CrearBeans(Map oMapInfoBaseDatos,String paqueteDestino, String rutaPaqueteDestino, String formatoGeneracionDeArchivos) {
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
		
		//Por cada Vista se genera un Bean y se crea el archivo correspondiente
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
        	if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_JAVA)){
        		oStringBufferArchivo = new FormatearEnJavaBeans().getFormato(nombreEnBeanVista,esquemaVista,comentarioVista,mapCamposDeLaVista,paqueteDestino+".vbeans");
        	}
        	else throw new ExcepcionNegocio("El formato de generación no es válido");
        	
        	// Crea los archivos de los beans de Vistas
        	if(oStringBufferArchivo.length()>0) new Archivos().escribirArchivo(rutaPaqueteDestino+Constantes.FILE_SEPARATOR+"vbeans", nombreEnBeanVista+".java", oStringBufferArchivo.toString());
		}
		
		//Por cada tabla se genera un Bean y se crea el archivo correspondiente
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
        	if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_JAVA)){
        		oStringBufferArchivo = new FormatearEnJavaBeans().getFormato(nombreEnBeanTabla,esquemaTabla,comentarioTabla,mapCamposDeLaTabla,paqueteDestino+".beans");
        	}
        	else throw new ExcepcionNegocio("El formato de generación no es válido");
        	
        	// Crea los archivos de los beans de Tablas
        	if(oStringBufferArchivo.length()>0) new Archivos().escribirArchivo(rutaPaqueteDestino+Constantes.FILE_SEPARATOR+"beans", nombreEnBeanTabla+".java", oStringBufferArchivo.toString());
		}
	}
}
