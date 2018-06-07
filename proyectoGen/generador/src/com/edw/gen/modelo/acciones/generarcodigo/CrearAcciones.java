/**
 * 
 */
package com.edw.gen.modelo.acciones.generarcodigo;

import java.util.Iterator;
import java.util.Map;

import com.edw.gen.apigen.ExcepcionNegocio;
import com.edw.gen.modelo.formatearcodigo.FormatearEnJavaAcciones;
import com.edw.gen.modelo.formatearcodigo.FormatearEnJavaBeans;
import com.edw.gen.modelo.formatearcodigo.FormatearEnJavaExecute;
import com.edw.gen.modelo.util.Archivos;
import com.edw.gen.modelo.util.Constantes;

/**
 * @author edward
 *
 */
public class CrearAcciones {

	private Map oMapInfoBaseDatos;
	private String paqueteDestino;
	private String rutaPaqueteDestino;
	private String formatoGeneracionDeArchivos;
	private String metodoCapturaUltimoIdInsertado;

	public CrearAcciones(Map oMapInfoBaseDatos,String paqueteDestino, String rutaPaqueteDestino, String formatoGeneracionDeArchivos, String metodoCapturaUltimoIdInsertado) {
		this.oMapInfoBaseDatos = oMapInfoBaseDatos;
		this.paqueteDestino = paqueteDestino;
		this.rutaPaqueteDestino = rutaPaqueteDestino;
		this.formatoGeneracionDeArchivos = formatoGeneracionDeArchivos;
		this.metodoCapturaUltimoIdInsertado = metodoCapturaUltimoIdInsertado;
	}
	
	public void crear() throws Exception {
		if(oMapInfoBaseDatos==null) throw new ExcepcionNegocio("No ha ingresado los información de la Base de Datos");
		if(paqueteDestino==null || paqueteDestino.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino");
		if(rutaPaqueteDestino==null || rutaPaqueteDestino.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado la ruta destino");
		if(formatoGeneracionDeArchivos==null || formatoGeneracionDeArchivos.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el formato de generación de archivos");
		if(metodoCapturaUltimoIdInsertado==null || metodoCapturaUltimoIdInsertado.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el método de captura del último ID insertado");
		
		{
			//Crea las clases de ejecución ExecuteTransaccional y ExecuteNoTransaccional:
			
			StringBuffer oStringBufferArchivoExecuteTransaccional = new StringBuffer();
			StringBuffer oStringBufferArchivoExecuteNoTransaccional = new StringBuffer();
			
	    	if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_JAVA)){
	    		FormatearEnJavaExecute oFormatearEnJavaExecute = new FormatearEnJavaExecute(paqueteDestino+".basedatos");
	    		oStringBufferArchivoExecuteTransaccional = oFormatearEnJavaExecute.getFormatoExecuteTransaccional();
	    		oStringBufferArchivoExecuteNoTransaccional = oFormatearEnJavaExecute.getFormatoExecuteNoTransaccional();
	    	}
	    	else throw new ExcepcionNegocio("El formato de generación no es válido");
	    	// Crea el archivo de ExecuteTransaccional
	    	new Archivos().escribirArchivo(rutaPaqueteDestino+Constantes.FILE_SEPARATOR+"basedatos", "ExecuteTransaccional.java", oStringBufferArchivoExecuteTransaccional.toString());
	    	// Crea el archivo de ExecuteNoTransaccional
	    	new Archivos().escribirArchivo(rutaPaqueteDestino+Constantes.FILE_SEPARATOR+"basedatos", "ExecuteNoTransaccional.java", oStringBufferArchivoExecuteNoTransaccional.toString());	    	
		}
    	
		//Recupera info de Vistas y de Tablas
		Map oMapVistas = (Map)oMapInfoBaseDatos.get("vistas");
		Map oMapTablas = (Map)oMapInfoBaseDatos.get("tablas");
		
		//Por cada Vista se generan las Acciones estándares y se crea el archivo correspondiente
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
			{
				//Crea la AccionInfoListar:        	
	        	if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_JAVA)){
	        		FormatearEnJavaAcciones oFormatearEnJavaAcciones = new FormatearEnJavaAcciones(nombreVista,nombreEnBeanVista, mapCamposDeLaVista, paqueteDestino+".acciones."+nombreEnBeanVista.toLowerCase(),paqueteDestino+".vbeans",paqueteDestino+".vdaos",paqueteDestino+".basedatos",paqueteDestino+".secuenciasincrementos",metodoCapturaUltimoIdInsertado,true);
	        		oStringBufferArchivo = oFormatearEnJavaAcciones.getFormatoAccionInfoListar();
	        	}
	        	else throw new ExcepcionNegocio("El formato de generación no es válido");
	        	// Crea el archivo de la AccionInfoListar
	        	new Archivos().escribirArchivo(rutaPaqueteDestino+Constantes.FILE_SEPARATOR+"acciones"+Constantes.FILE_SEPARATOR+nombreEnBeanVista.toLowerCase(), "AccionInfoListar"+nombreEnBeanVista+".java", oStringBufferArchivo.toString());
			}
		}
		
		//Por cada Tabla se generan las Acciones estándares y se crea el archivo correspondiente
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
			
			{
				//Crea la AccionInfoListar:
	        	if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_JAVA)){
	        		FormatearEnJavaAcciones oFormatearEnJavaAcciones = new FormatearEnJavaAcciones(nombreTabla,nombreEnBeanTabla,mapCamposDeLaTabla,paqueteDestino+".acciones."+nombreEnBeanTabla.toLowerCase(),paqueteDestino+".beans",paqueteDestino+".daos",paqueteDestino+".basedatos",paqueteDestino+".secuenciasincrementos",metodoCapturaUltimoIdInsertado,false);
	        		oStringBufferArchivo = oFormatearEnJavaAcciones.getFormatoAccionInfoListar();
	        	}
	        	else throw new ExcepcionNegocio("El formato de generación no es válido");
	        	// Crea el archivo de la AccionInfoListar
	        	if(oStringBufferArchivo.length()>0) new Archivos().escribirArchivo(rutaPaqueteDestino+Constantes.FILE_SEPARATOR+"acciones"+Constantes.FILE_SEPARATOR+nombreEnBeanTabla.toLowerCase(), "AccionInfoListar"+nombreEnBeanTabla+".java", oStringBufferArchivo.toString());
			}

			{
				//Crea la AccionInfoEditar:
	        	oStringBufferArchivo = new StringBuffer();
	        	if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_JAVA)){
	        		FormatearEnJavaAcciones oFormatearEnJavaAcciones = new FormatearEnJavaAcciones(nombreTabla,nombreEnBeanTabla,mapCamposDeLaTabla,paqueteDestino+".acciones."+nombreEnBeanTabla.toLowerCase(),paqueteDestino+".beans",paqueteDestino+".daos",paqueteDestino+".basedatos",paqueteDestino+".secuenciasincrementos",metodoCapturaUltimoIdInsertado,false);
	        		oStringBufferArchivo = oFormatearEnJavaAcciones.getFormatoAccionInfoEditar();
	        	}
	        	else throw new ExcepcionNegocio("El formato de generación no es válido");
	        	// Crea el archivo de la AccionInfoEditar
	        	if(oStringBufferArchivo.length()>0) new Archivos().escribirArchivo(rutaPaqueteDestino+Constantes.FILE_SEPARATOR+"acciones"+Constantes.FILE_SEPARATOR+nombreEnBeanTabla.toLowerCase(), "AccionInfoEditar"+nombreEnBeanTabla+".java", oStringBufferArchivo.toString());
			}
        	
			{
				//Crea la AccionActualizar:
	        	oStringBufferArchivo = new StringBuffer();
	        	if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_JAVA)){
	        		FormatearEnJavaAcciones oFormatearEnJavaAcciones = new FormatearEnJavaAcciones(nombreTabla,nombreEnBeanTabla,mapCamposDeLaTabla,paqueteDestino+".acciones."+nombreEnBeanTabla.toLowerCase(),paqueteDestino+".beans",paqueteDestino+".daos",paqueteDestino+".basedatos",paqueteDestino+".secuenciasincrementos",metodoCapturaUltimoIdInsertado,false);
	        		oStringBufferArchivo = oFormatearEnJavaAcciones.getFormatoAccionActualizar();
	        	}
	        	else throw new ExcepcionNegocio("El formato de generación no es válido");
	        	// Crea el archivo de la AccionActualizar
	        	if(oStringBufferArchivo.length()>0) new Archivos().escribirArchivo(rutaPaqueteDestino+Constantes.FILE_SEPARATOR+"acciones"+Constantes.FILE_SEPARATOR+nombreEnBeanTabla.toLowerCase(), "AccionActualizar"+nombreEnBeanTabla+".java", oStringBufferArchivo.toString());
			}

			{
				//Crea la AccionEliminar:
	        	oStringBufferArchivo = new StringBuffer();
	        	if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_JAVA)){
	        		FormatearEnJavaAcciones oFormatearEnJavaAcciones = new FormatearEnJavaAcciones(nombreTabla,nombreEnBeanTabla,mapCamposDeLaTabla,paqueteDestino+".acciones."+nombreEnBeanTabla.toLowerCase(),paqueteDestino+".beans",paqueteDestino+".daos",paqueteDestino+".basedatos",paqueteDestino+".secuenciasincrementos",metodoCapturaUltimoIdInsertado,false);
	        		oStringBufferArchivo = oFormatearEnJavaAcciones.getFormatoAccionEliminar();
	        	}
	        	else throw new ExcepcionNegocio("El formato de generación no es válido");
	        	// Crea el archivo de la AccionEliminar
	        	if(oStringBufferArchivo.length()>0) new Archivos().escribirArchivo(rutaPaqueteDestino+Constantes.FILE_SEPARATOR+"acciones"+Constantes.FILE_SEPARATOR+nombreEnBeanTabla.toLowerCase(), "AccionEliminar"+nombreEnBeanTabla+".java", oStringBufferArchivo.toString());
			}

			{
				//Crea la AccionInsertar:
	        	oStringBufferArchivo = new StringBuffer();
	        	if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_JAVA)){
	        		FormatearEnJavaAcciones oFormatearEnJavaAcciones = new FormatearEnJavaAcciones(nombreTabla,nombreEnBeanTabla,mapCamposDeLaTabla,paqueteDestino+".acciones."+nombreEnBeanTabla.toLowerCase(),paqueteDestino+".beans",paqueteDestino+".daos",paqueteDestino+".basedatos",paqueteDestino+".secuenciasincrementos",metodoCapturaUltimoIdInsertado,false);
	        		oStringBufferArchivo = oFormatearEnJavaAcciones.getFormatoAccionInsertar();
	        	}
	        	else throw new ExcepcionNegocio("El formato de generación no es válido");
	        	// Crea el archivo de la AccionInsertar
	        	if(oStringBufferArchivo.length()>0) new Archivos().escribirArchivo(rutaPaqueteDestino+Constantes.FILE_SEPARATOR+"acciones"+Constantes.FILE_SEPARATOR+nombreEnBeanTabla.toLowerCase(), "AccionInsertar"+nombreEnBeanTabla+".java", oStringBufferArchivo.toString());
			}
        	
			{
				//Crea la AccionInsertarAInc:
	        	oStringBufferArchivo = new StringBuffer();
	        	if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_JAVA)){
	        		FormatearEnJavaAcciones oFormatearEnJavaAcciones = new FormatearEnJavaAcciones(nombreTabla,nombreEnBeanTabla,mapCamposDeLaTabla,paqueteDestino+".acciones."+nombreEnBeanTabla.toLowerCase(),paqueteDestino+".beans",paqueteDestino+".daos",paqueteDestino+".basedatos",paqueteDestino+".secuenciasincrementos",metodoCapturaUltimoIdInsertado,false);
	        		oStringBufferArchivo = oFormatearEnJavaAcciones.getFormatoAccionInsertarAInc();
	        	}
	        	else throw new ExcepcionNegocio("El formato de generación no es válido");
	        	// Crea el archivo de la AccionInsertarAInc
	        	if(oStringBufferArchivo.length()>0) new Archivos().escribirArchivo(rutaPaqueteDestino+Constantes.FILE_SEPARATOR+"acciones"+Constantes.FILE_SEPARATOR+nombreEnBeanTabla.toLowerCase(), "AccionInsertar"+nombreEnBeanTabla+"AInc.java", oStringBufferArchivo.toString());
			}

			{
				//Crea la AccionInsertarSec:
	        	oStringBufferArchivo = new StringBuffer();
	        	if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_JAVA)){
	        		FormatearEnJavaAcciones oFormatearEnJavaAcciones = new FormatearEnJavaAcciones(nombreTabla,nombreEnBeanTabla,mapCamposDeLaTabla,paqueteDestino+".acciones."+nombreEnBeanTabla.toLowerCase(),paqueteDestino+".beans",paqueteDestino+".daos",paqueteDestino+".basedatos",paqueteDestino+".secuenciasincrementos",metodoCapturaUltimoIdInsertado,false);
	        		oStringBufferArchivo = oFormatearEnJavaAcciones.getFormatoAccionInsertarSec();
	        	}
	        	else throw new ExcepcionNegocio("El formato de generación no es válido");
	        	// Crea el archivo de la AccionInsertarSec
	        	if(oStringBufferArchivo.length()>0) new Archivos().escribirArchivo(rutaPaqueteDestino+Constantes.FILE_SEPARATOR+"acciones"+Constantes.FILE_SEPARATOR+nombreEnBeanTabla.toLowerCase(), "AccionInsertar"+nombreEnBeanTabla+"Sec.java", oStringBufferArchivo.toString());
			}
			
		}		
		
	}

}
