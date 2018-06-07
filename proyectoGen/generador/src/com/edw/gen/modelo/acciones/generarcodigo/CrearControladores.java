/**
 * 
 */
package com.edw.gen.modelo.acciones.generarcodigo;

import java.util.Iterator;
import java.util.Map;

import com.edw.gen.apigen.ExcepcionNegocio;
import com.edw.gen.modelo.formatearcodigo.jsf.FormatearControladorEdicionEnJSF;
import com.edw.gen.modelo.formatearcodigo.jsf.FormatearControladorListaEnJSF;
import com.edw.gen.modelo.util.Archivos;
import com.edw.gen.modelo.util.Constantes;

/**
 * @author edward
 *
 */
public class CrearControladores {

	private Map oMapInfoBaseDatos;
	private String paqueteDestino;
	private String rutaPaqueteDestino;
	private String formatoGeneracionDeArchivos;
	private String framework;
	
	public CrearControladores(Map oMapInfoBaseDatos,String paqueteDestino, String rutaPaqueteDestino, String formatoGeneracionDeArchivos, String framework) {
		this.oMapInfoBaseDatos = oMapInfoBaseDatos;
		this.paqueteDestino = paqueteDestino;
		this.rutaPaqueteDestino = rutaPaqueteDestino;
		this.formatoGeneracionDeArchivos = formatoGeneracionDeArchivos;
		this.framework = framework;
	}
	
	public void crear() throws Exception {
		if(oMapInfoBaseDatos==null) throw new ExcepcionNegocio("No ha ingresado los información de la Base de Datos");
		if(paqueteDestino==null || paqueteDestino.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino");
		if(rutaPaqueteDestino==null || rutaPaqueteDestino.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado la ruta destino");
		if(formatoGeneracionDeArchivos==null || formatoGeneracionDeArchivos.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el formato de generación de archivos");
		if(framework==null || framework.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el framework de generación de la vista y controlador");
		
		//Recupera info de Vistas y de Tablas
		Map oMapVistas = (Map)oMapInfoBaseDatos.get("vistas");
		Map oMapTablas = (Map)oMapInfoBaseDatos.get("tablas");
		
		//Por cada Vista se generan el controlador de la lista
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
			//Crea el controlador de la Lista:
			{
	        	if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_JAVA)){
	        		if(framework.equalsIgnoreCase(Constantes.FRAMEWORK_JSF)){
	        			FormatearControladorListaEnJSF oFormatearControladorListaEnJSF = new FormatearControladorListaEnJSF(nombreEnBeanVista,mapCamposDeLaVista,paqueteDestino+".controladores",paqueteDestino+".vbeans",paqueteDestino+".acciones."+nombreEnBeanVista.toLowerCase());
		        		oStringBufferArchivo = oFormatearControladorListaEnJSF.getFormatoControlador();
	        		}
	        		else  throw new ExcepcionNegocio("El framework de generación no es válido");
	        	}
	        	else throw new ExcepcionNegocio("El formato de generación no es válido");
	        	// Crea el archivo del controlador de la Lista
	        	if(oStringBufferArchivo.length()>0) new Archivos().escribirArchivo(rutaPaqueteDestino+Constantes.FILE_SEPARATOR+"controladores", "Control"+nombreEnBeanVista+"Lista.java", oStringBufferArchivo.toString());
			}
		}
		
		//Por cada Tabla se generan el controlador de la lista y el de edición
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
			//Crea el controlador de la Lista:
			{
	        	if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_JAVA)){
	        		if(framework.equalsIgnoreCase(Constantes.FRAMEWORK_JSF)){
	        			FormatearControladorListaEnJSF oFormatearEnJSFControladorLista = new FormatearControladorListaEnJSF(nombreEnBeanTabla,mapCamposDeLaTabla,paqueteDestino+".controladores",paqueteDestino+".beans",paqueteDestino+".acciones."+nombreEnBeanTabla.toLowerCase());
		        		oStringBufferArchivo = oFormatearEnJSFControladorLista.getFormatoControlador();
	        		}
	        		else  throw new ExcepcionNegocio("El framework de generación no es válido");
	        	}
	        	else throw new ExcepcionNegocio("El formato de generación no es válido");
	        	// Crea el archivo del controlador de la Lista
	        	if(oStringBufferArchivo.length()>0) new Archivos().escribirArchivo(rutaPaqueteDestino+Constantes.FILE_SEPARATOR+"controladores", "Control"+nombreEnBeanTabla+"Lista.java", oStringBufferArchivo.toString());
			}
			//Crea el controlador de Edición:
			{	
	        	if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_JAVA)){
	        		if(framework.equalsIgnoreCase(Constantes.FRAMEWORK_JSF)){
	        			FormatearControladorEdicionEnJSF oFormatearEnJSFControladorEdicion = new FormatearControladorEdicionEnJSF(nombreEnBeanTabla,mapCamposDeLaTabla,paqueteDestino+".controladores",paqueteDestino+".beans",paqueteDestino+".acciones."+nombreEnBeanTabla.toLowerCase());
		        		oStringBufferArchivo = oFormatearEnJSFControladorEdicion.getFormatoControlador();
	        		}
	        		else  throw new ExcepcionNegocio("El framework de generación no es válido");
	        	}
	        	else throw new ExcepcionNegocio("El formato de generación no es válido");
	        	// Crea el archivo del controlador de Edición
	        	if(oStringBufferArchivo.length()>0) new Archivos().escribirArchivo(rutaPaqueteDestino+Constantes.FILE_SEPARATOR+"controladores", "Control"+nombreEnBeanTabla+"Edicion.java", oStringBufferArchivo.toString());
			}
		}
	}

}
