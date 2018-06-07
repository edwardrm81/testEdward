/**
 * 
 */
package com.edw.gen.modelo.formatearcodigo;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.edw.gen.apigen.ExcepcionNegocio;
import com.edw.gen.modelo.util.Constantes;
import com.edw.gen.modelo.util.ConversorCodigoTipoJDBCACadenaTipoJDBC;
import com.edw.gen.modelo.util.ConversorTipoJDBCATipoJava;

/**
 * @author edward
 *
 */
public class FormatearInfoBaseDatos {

	Map oMapInfoBaseDatos;
	String formatoGeneracionDeArchivos;
	
	public FormatearInfoBaseDatos(Map oMapInfoBaseDatos, String formatoGeneracionDeArchivos) {
		this.oMapInfoBaseDatos = oMapInfoBaseDatos;
		this.formatoGeneracionDeArchivos = formatoGeneracionDeArchivos;
	}

	public Map getDatosTablas() throws Exception {
		
		if(this.oMapInfoBaseDatos==null) throw new ExcepcionNegocio("No ha ingresado la información de la Base de Datos");
		if(this.formatoGeneracionDeArchivos==null) throw new ExcepcionNegocio("No ha ingresado el formato de generación del archivo");
		
        Map oMapVistas = (Map)oMapInfoBaseDatos.get("vistas");
        Map oMapTablas = (Map)oMapInfoBaseDatos.get("tablas");
        
        Map mapVistasFormateadas = new LinkedHashMap();
        Map mapTablasFormateadas = new LinkedHashMap();        
        
		Iterator itVistas = oMapVistas.entrySet().iterator();
		while(itVistas.hasNext()) {
			Map.Entry oMapEntryVista = (Map.Entry) itVistas.next();
			
			//String nombreVista = (String)oMapEntryVista.getKey();
			Map oMapVista = (Map)oMapEntryVista.getValue();
    		
			String nombreVista = "";
			if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_JAVA)) {
				nombreVista = new FormateadoresBD().getNombreSQLEnJDBC((String) oMapVista.get("nombre"));
			}
			else if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_PHP)) {
				throw new ExcepcionNegocio("No se ha definido el formato para PHP");
			}
			else throw new ExcepcionNegocio("El formato de generación no es válido");
			
			String nombreEnBeanVista =  new FormateadoresBD().getNombreEnBeanTabla((String) oMapVista.get("nombre"));
			String esquemaVista = (String) oMapVista.get("esquema");
			String comentarioVista = new FormateadoresBD().getDepurarCaracteresComentario((String) oMapVista.get("comentario")) ;
			Map mapCamposDeLaVista = (Map)oMapVista.get("campos");						
			Iterator itCampos = mapCamposDeLaVista.entrySet().iterator();
			Map mapCamposFormateadosDeLaVista = new LinkedHashMap();
			
			while(itCampos.hasNext()) {
				Map.Entry oMapEntryCampo = (Map.Entry) itCampos.next();
				
				//String nombre = (String)oMapCampo.getKey();
				Map oMapCampo = (Map)oMapEntryCampo.getValue();

				String nombreCampo = "";
				if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_JAVA)) {
					nombreCampo = new FormateadoresBD().getNombreSQLEnJDBC((String)oMapCampo.get("nombre"));
				}
				else if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_PHP)) {
					throw new ExcepcionNegocio("No se ha definido el formato para PHP");
				}
				else throw new ExcepcionNegocio("El formato de generación no es válido");
				
				String nombreCampoEnBean = new FormateadoresBD().getNombreEnBeanCampo((String)oMapCampo.get("nombre"));
				Integer tipoDatoJDBCCampo = (Integer)oMapCampo.get("tipoDatoJDBC");
				String tipoDatoJDBCCadenaCampo = new ConversorCodigoTipoJDBCACadenaTipoJDBC().getCadenaTipoDatoJDBC(tipoDatoJDBCCampo);
				
				String tipoDatoLenguajeCampo = "";
				if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_JAVA)) {
					tipoDatoLenguajeCampo = new ConversorTipoJDBCATipoJava().getTipoDatoJava(tipoDatoJDBCCampo);
				}
				else if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_PHP)) {
					throw new ExcepcionNegocio("No se ha definido el formato para PHP");
				}
				else throw new ExcepcionNegocio("El formato de generación no es válido");
				
				Boolean esNulableCampo = (Boolean)oMapCampo.get("esNulable");
				String comentarioCampo = new FormateadoresBD().getDepurarCaracteresComentario((String)oMapCampo.get("comentario"));
				Integer secuenciaPKCampo = (Integer)oMapCampo.get("secuenciaPK");
				
				Map oMapCampoFormateado = new HashMap();
				oMapCampoFormateado.put("nombre", nombreCampo);
				oMapCampoFormateado.put("nombreEnBean", nombreCampoEnBean);
				oMapCampoFormateado.put("tipoDatoJDBC", tipoDatoJDBCCampo);
				oMapCampoFormateado.put("tipoDatoJDBCCadena", tipoDatoJDBCCadenaCampo);
				oMapCampoFormateado.put("tipoDatoLenguaje", tipoDatoLenguajeCampo);
				oMapCampoFormateado.put("esNulable", esNulableCampo);
				oMapCampoFormateado.put("comentario", comentarioCampo);
				oMapCampoFormateado.put("secuenciaPK", secuenciaPKCampo);				
				
				mapCamposFormateadosDeLaVista.put(nombreCampo, oMapCampoFormateado);
			}
			
			Map oMapVistaFormateada = new HashMap();
			
			oMapVistaFormateada.put("nombre", nombreVista);
			oMapVistaFormateada.put("nombreEnBean", nombreEnBeanVista);
			oMapVistaFormateada.put("esquema", esquemaVista);
			oMapVistaFormateada.put("comentario", comentarioVista);
			oMapVistaFormateada.put("campos", mapCamposFormateadosDeLaVista);
			
			mapVistasFormateadas.put(nombreVista, oMapVistaFormateada);
		}

		
		Iterator itTablas = oMapTablas.entrySet().iterator();
		while(itTablas.hasNext()) {
			Map.Entry oMapEntryTabla = (Map.Entry) itTablas.next();
			
			//String nombreTabla = (String)oMapEntryTabla.getKey();
			Map oMapTabla = (Map)oMapEntryTabla.getValue();

			String nombreTabla = "";
			if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_JAVA)) {
				nombreTabla = new FormateadoresBD().getNombreSQLEnJDBC((String) oMapTabla.get("nombre"));
			}
			else if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_PHP)) {
				throw new ExcepcionNegocio("No se ha definido el formato para PHP");
			}
			else throw new ExcepcionNegocio("El formato de generación no es válido");
			
			String nombreEnBeanTabla = new FormateadoresBD().getNombreEnBeanTabla((String) oMapTabla.get("nombre"));
			String esquemaTabla = (String) oMapTabla.get("esquema");
			String comentarioTabla = new FormateadoresBD().getDepurarCaracteresComentario((String) oMapTabla.get("comentario"));
			Map mapCamposDeLaTabla = (Map)oMapTabla.get("campos");
			Iterator itCampos = mapCamposDeLaTabla.entrySet().iterator();
			Map mapCamposFormateadosDeLaTabla = new LinkedHashMap();
			
			while(itCampos.hasNext()) {
				Map.Entry oMapEntryCampo = (Map.Entry) itCampos.next();
				
				//String nombre = (String)oMapCampo.getKey();
				Map oMapCampo = (Map)oMapEntryCampo.getValue();
				
				
				String nombreCampo = "";
				if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_JAVA)) {
					nombreCampo = new FormateadoresBD().getNombreSQLEnJDBC((String)oMapCampo.get("nombre"));
				}
				else if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_PHP)) {
					throw new ExcepcionNegocio("No se ha definido el formato para PHP");
				}
				else throw new ExcepcionNegocio("El formato de generación no es válido");
				
				String nombreCampoEnBean = new FormateadoresBD().getNombreEnBeanCampo((String)oMapCampo.get("nombre"));
				Integer tipoDatoJDBCCampo = (Integer)oMapCampo.get("tipoDatoJDBC");
				String tipoDatoJDBCCadenaCampo = new ConversorCodigoTipoJDBCACadenaTipoJDBC().getCadenaTipoDatoJDBC(tipoDatoJDBCCampo);
				
				String tipoDatoLenguajeCampo = "";
				if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_JAVA)) {
					tipoDatoLenguajeCampo = new ConversorTipoJDBCATipoJava().getTipoDatoJava(tipoDatoJDBCCampo);
				}
				else if(formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_PHP)) {
					throw new ExcepcionNegocio("No se ha definido el formato para PHP");
				}
				else throw new ExcepcionNegocio("El formato de generación no es válido");
				
				Boolean esNulableCampo = (Boolean)oMapCampo.get("esNulable");
				String comentarioCampo = new FormateadoresBD().getDepurarCaracteresComentario((String)oMapCampo.get("comentario"));
				Integer secuenciaPKCampo = (Integer)oMapCampo.get("secuenciaPK");
				
				Map oMapCampoFormateado = new HashMap();
				
				oMapCampoFormateado.put("nombre", nombreCampo);
				oMapCampoFormateado.put("nombreEnBean", nombreCampoEnBean);
				oMapCampoFormateado.put("tipoDatoJDBC", tipoDatoJDBCCampo);
				oMapCampoFormateado.put("tipoDatoJDBCCadena", tipoDatoJDBCCadenaCampo);
				oMapCampoFormateado.put("tipoDatoLenguaje", tipoDatoLenguajeCampo);
				oMapCampoFormateado.put("esNulable", esNulableCampo);
				oMapCampoFormateado.put("comentario", comentarioCampo);
				oMapCampoFormateado.put("secuenciaPK", secuenciaPKCampo);				
				
				mapCamposFormateadosDeLaTabla.put(nombreCampo, oMapCampoFormateado);				
			}
			
			Map oMapTablaFormateada = new HashMap();
			
			oMapTablaFormateada.put("nombre", nombreTabla);
			oMapTablaFormateada.put("nombreEnBean", nombreEnBeanTabla);
			oMapTablaFormateada.put("esquema", esquemaTabla);
			oMapTablaFormateada.put("comentario", comentarioTabla);
			oMapTablaFormateada.put("campos", mapCamposFormateadosDeLaTabla);	
			
			mapTablasFormateadas.put(nombreTabla, oMapTablaFormateada);
		}
		
        Map oMapInfoBaseDatosFormateada = new HashMap();
        
        oMapInfoBaseDatosFormateada.put("tablas", mapTablasFormateadas);
        oMapInfoBaseDatosFormateada.put("vistas", mapVistasFormateadas);
        
        return oMapInfoBaseDatosFormateada;
	}

}
