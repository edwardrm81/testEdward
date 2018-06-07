/**
 * 
 */
package com.edw.gen.modelo.acciones.generarcodigo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.edw.gen.apigen.ExcepcionNegocio;
import com.edw.gen.modelo.formatearcodigo.FormatearEnJavaAcciones;
import com.edw.gen.modelo.formatearcodigo.FormatearEnJavaBeans;
import com.edw.gen.modelo.formatearcodigo.FormatearEnJavaExecute;
import com.edw.gen.modelo.formatearcodigo.jsf.FormatearContextBeanEnJSF;
import com.edw.gen.modelo.formatearcodigo.jsf.FormatearFacesConfigEnJSF;
import com.edw.gen.modelo.util.Archivos;
import com.edw.gen.modelo.util.Constantes;

/**
 * @author edward
 *
 */
public class CrearAdicionales {

	private Map oMapInfoBaseDatos;
	private String paqueteDestino;
	private String rutaPaqueteDestino;
	private String rutaVistaDestino;
	private String formatoGeneracionDeArchivos;
	private String framework;

	public CrearAdicionales(Map oMapInfoBaseDatos,String paqueteDestino, String rutaPaqueteDestino, String rutaVistaDestino, String formatoGeneracionDeArchivos,String framework) {
		
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
		
		StringBuffer oStringBufferArchivo = new StringBuffer();
		{	
			oStringBufferArchivo = new StringBuffer();
			
        	if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_JAVA)){
        		if(framework.equalsIgnoreCase(Constantes.FRAMEWORK_JSF)){
        			//Se genera el ContextBean y se genera el archivo correspondiente
        			{
	        			// Formatea el archivo del ContextBean
		        		FormatearContextBeanEnJSF oFormatearContextBeanEnJSF = new FormatearContextBeanEnJSF(oMapInfoBaseDatos,paqueteDestino+".controladores");
		        		oStringBufferArchivo = oFormatearContextBeanEnJSF.getFormatoContextBean();
		        		
		            	// Crea el archivo del Context Bean
		            	if(oStringBufferArchivo.length()>0) new Archivos().escribirArchivo(rutaPaqueteDestino+Constantes.FILE_SEPARATOR+"controladores","ContextBean.java", oStringBufferArchivo.toString());
        			}
        			
        			//Se genera el faces-config.xml y se genera el archivo correspondiente
        			{
	        			// Formatea el archivo del faces-config.xml
		        		FormatearFacesConfigEnJSF oFormatearFacesConfigEnJSF = new FormatearFacesConfigEnJSF(oMapInfoBaseDatos,paqueteDestino+".controladores");
		        		oStringBufferArchivo = oFormatearFacesConfigEnJSF.getFormatoFacesConfig();
		        		
		            	// Crea el archivo del faces-config.xml
		            	if(oStringBufferArchivo.length()>0) new Archivos().escribirArchivo(rutaVistaDestino+Constantes.FILE_SEPARATOR+"WEB-INF","faces-config.xml", oStringBufferArchivo.toString());
        			}
        			
	            	
        		}
        		else  throw new ExcepcionNegocio("El framework de generación no es válido");
        	}
        	else throw new ExcepcionNegocio("El formato de generación no es válido");
		}
	}
}
