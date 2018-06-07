/**
 * 
 */
package com.edw.gen.modelo.acciones.generarcodigo;

import java.util.Iterator;
import java.util.Map;

import com.edw.gen.apigen.ExcepcionNegocio;
import com.edw.gen.modelo.formatearcodigo.jsf.FormatearControladorEdicionEnJSF;
import com.edw.gen.modelo.formatearcodigo.jsf.FormatearControladorListaEnJSF;
import com.edw.gen.modelo.formatearcodigo.jsf.FormatearVistaEdicionEnJSF;
import com.edw.gen.modelo.formatearcodigo.jsf.FormatearVistaListaEnJSF;
import com.edw.gen.modelo.util.Archivos;
import com.edw.gen.modelo.util.Constantes;

/**
 * @author edward
 *
 */
public class CrearVistas {

	private Map oMapInfoBaseDatos;
	private String paqueteDestino;
	private String rutaPaqueteDestino;
	private String rutaVistaDestino;
	private String formatoGeneracionDeArchivos;
	private String framework;
	
	public CrearVistas(Map oMapInfoBaseDatos,String paqueteDestino, String rutaPaqueteDestino, String rutaVistaDestino, String formatoGeneracionDeArchivos, String framework) {
		this.oMapInfoBaseDatos = oMapInfoBaseDatos;
		this.paqueteDestino = paqueteDestino;
		this.rutaPaqueteDestino = rutaPaqueteDestino;
		this.rutaVistaDestino = rutaVistaDestino;
		this.formatoGeneracionDeArchivos = formatoGeneracionDeArchivos;
		this.framework = framework;
	}

	public void crear() throws Exception {
		if(oMapInfoBaseDatos==null) throw new ExcepcionNegocio("No ha ingresado los información de la Base de Datos");
		if(paqueteDestino==null || paqueteDestino.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino");
		if(rutaPaqueteDestino==null || rutaPaqueteDestino.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado la ruta destino");
		if(rutaVistaDestino==null || rutaVistaDestino.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado la ruta destino de la vista");
		if(formatoGeneracionDeArchivos==null || formatoGeneracionDeArchivos.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el formato de generación de archivos");
		if(framework==null || framework.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el framework de generación de la vista y controlador");
		
		//Recupera info de Vistas y de Tablas
		Map oMapVistas = (Map)oMapInfoBaseDatos.get("vistas");
		Map oMapTablas = (Map)oMapInfoBaseDatos.get("tablas");
		
		//Por cada Vista de la Base de Datos se generan una vista (ventana) de la lista
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
			//Crea la vista(ventana) de la Lista:
			{
	        	if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_JAVA)){
	        		if(framework.equalsIgnoreCase(Constantes.FRAMEWORK_JSF)){
	        			FormatearVistaListaEnJSF oFormatearVistaListaEnJSF = new FormatearVistaListaEnJSF(nombreEnBeanVista,mapCamposDeLaVista);
		        		oStringBufferArchivo = oFormatearVistaListaEnJSF.getFormatoVista();
	        		}
	        		else  throw new ExcepcionNegocio("El framework de generación no es válido");
	        	}
	        	else throw new ExcepcionNegocio("El formato de generación no es válido");
	        	// Crea el archivo de la vista(ventana) de la Lista
	        	if(oStringBufferArchivo.length()>0) new Archivos().escribirArchivo(rutaVistaDestino+Constantes.FILE_SEPARATOR+"vistas", nombreEnBeanVista.substring(0,1).toLowerCase()+nombreEnBeanVista.substring(1)+"Lista.jsp", oStringBufferArchivo.toString());
			}
		}
		
		//Por cada Tabla se generan la vista(ventana) de la lista y la vista(ventana) de edición
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
			//Crea la vista(ventana) de la Lista:
			{
	        	if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_JAVA)){
	        		if(framework.equalsIgnoreCase(Constantes.FRAMEWORK_JSF)){
	        			FormatearVistaListaEnJSF oFormatearVistaListaEnJSF = new FormatearVistaListaEnJSF(nombreEnBeanTabla,mapCamposDeLaTabla);
		        		oStringBufferArchivo = oFormatearVistaListaEnJSF.getFormatoVista();
	        		}
	        		else  throw new ExcepcionNegocio("El framework de generación no es válido");
	        	}
	        	else throw new ExcepcionNegocio("El formato de generación no es válido");
	        	// Crea el archivo de la vista(ventana) de la Lista
	        	if(oStringBufferArchivo.length()>0) new Archivos().escribirArchivo(rutaVistaDestino+Constantes.FILE_SEPARATOR+"vistas", nombreEnBeanTabla.substring(0,1).toLowerCase()+nombreEnBeanTabla.substring(1)+"Lista.jsp", oStringBufferArchivo.toString());
			}
			//Crea la vista(ventana) de Edición:
			{	
	        	if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_JAVA)){
	        		if(framework.equalsIgnoreCase(Constantes.FRAMEWORK_JSF)){
	        			FormatearVistaEdicionEnJSF oFormatearVistaEdicionEnJSF = new FormatearVistaEdicionEnJSF(nombreEnBeanTabla,mapCamposDeLaTabla);
		        		oStringBufferArchivo = oFormatearVistaEdicionEnJSF.getFormatoVista();
	        		}
	        		else  throw new ExcepcionNegocio("El framework de generación no es válido");
	        	}
	        	else throw new ExcepcionNegocio("El formato de generación no es válido");
	        	// Crea el archivo de la vista(ventana) de Edición
	        	if(oStringBufferArchivo.length()>0) new Archivos().escribirArchivo(rutaVistaDestino+Constantes.FILE_SEPARATOR+"vistas", nombreEnBeanTabla.substring(0,1).toLowerCase()+nombreEnBeanTabla.substring(1)+"Edicion.jsp", oStringBufferArchivo.toString());
			}
		}
	}

}
