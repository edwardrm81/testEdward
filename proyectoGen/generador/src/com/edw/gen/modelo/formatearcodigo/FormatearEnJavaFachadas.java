/**
 * 
 */
package com.edw.gen.modelo.formatearcodigo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.edw.gen.apigen.ExcepcionNegocio;
import com.edw.gen.modelo.util.OrdenarListaDeObjetos;

/**
 * @author edward
 *
 */
public class FormatearEnJavaFachadas {
	
	private String nombreEnBeanTabla;
	private Map mapCamposDeLaTabla;
	private String paqueteFachada;
	private String paqueteBeans;
	private boolean esVista;

	public FormatearEnJavaFachadas(String nombreEnBeanTabla,Map mapCamposDeLaTabla,String paqueteFachada,String paqueteBeans,boolean esVista) {
		this.nombreEnBeanTabla = nombreEnBeanTabla;
		this.mapCamposDeLaTabla = mapCamposDeLaTabla;
		this.paqueteFachada = paqueteFachada;
		this.paqueteBeans = paqueteBeans;
		this.esVista = esVista;
	}

	public StringBuffer getFormatoFachada() throws Exception {
		if(paqueteFachada==null || paqueteFachada.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino");
		if(paqueteBeans==null || paqueteBeans.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino de los Beans");
		if(nombreEnBeanTabla==null || nombreEnBeanTabla.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el nombre de la tabla");
		if(mapCamposDeLaTabla==null || mapCamposDeLaTabla.size()<1) throw new ExcepcionNegocio("No ha ingresado la información de los campos de la tabla");

		// mapCamposDeLaTabla tiene los siguientes atributos:
		// "nombre" 			String,		"nombreEnBean"		String,			"tipoDatoJDBC"		Integer,
		// "tipoDatoJDBCCadena" String,		"tipoDatoLenguaje"	String,			"esNulable"			Boolean,
		// "comentario"			String,		"secuenciaPK"		Integer
		
		// Lista de campos de la clave primaria ordenados por secuenciaPK y por nombre: 
		List listaCamposPK = new ArrayList();
		
		//Se llenan la lista:
		Iterator itMapCamposDeLaTabla = mapCamposDeLaTabla.entrySet().iterator();		
		while(itMapCamposDeLaTabla.hasNext()) {
			Map.Entry oMapEntryCampos = (Map.Entry) itMapCamposDeLaTabla.next();
			//String nombreCampo = (String)oMapEntryCampos.getKey();
			Map oMapCampo = (Map)oMapEntryCampos.getValue();
			
			if((Integer)oMapCampo.get("secuenciaPK")!=null) {
				listaCamposPK.add(oMapCampo);
			}
		}
		new OrdenarListaDeObjetos().ordenarPorPropiedades(listaCamposPK,"secuenciaPK","nombre");
		
		StringBuffer cadena = new StringBuffer();
		
	    cadena.append("\n");
	    cadena.append("package "+paqueteFachada +";\n");
	    cadena.append("\n");
	    cadena.append("import java.util.Map;"+"\n");
	    cadena.append("\n");
	    cadena.append("import "+paqueteBeans+"."+nombreEnBeanTabla+";"+"\n");
	    cadena.append("\n");
	    cadena.append("/* @author Edward */"+"\n");
	    cadena.append("\n");
	    cadena.append("public class Fachada"+nombreEnBeanTabla+" {"+"\n");
	    cadena.append("\n");
	    cadena.append("    public static Map infoListar"+nombreEnBeanTabla+"(Map oMapFiltros) throws Exception{"+"\n");
	    cadena.append("        AccionInfoListar"+nombreEnBeanTabla+" oAccionInfoListar"+nombreEnBeanTabla+" = new AccionInfoListar"+nombreEnBeanTabla+"(oMapFiltros);"+"\n");
	    cadena.append("        return oAccionInfoListar"+nombreEnBeanTabla+".execute();"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    
	    if(!esVista && listaCamposPK.size()>0) {
		    cadena.append("    public static Map infoEditar"+nombreEnBeanTabla+"(");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				String tipoDatoLenguaje = (String)((Map)listaCamposPK.get(i)).get("tipoDatoLenguaje");
				cadena.append(tipoDatoLenguaje+" "+nombreEnBean+",");
			}
		    cadena.delete(cadena.length()-1, cadena.length());
		    cadena.append(") throws Exception{"+"\n");
		    cadena.append("        AccionInfoEditar"+nombreEnBeanTabla+" oAccionInfoEditar"+nombreEnBeanTabla+" = new AccionInfoEditar"+nombreEnBeanTabla+"(");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				cadena.append(nombreEnBean+",");
			}
		    cadena.delete(cadena.length()-1, cadena.length());
		    cadena.append(");"+"\n");
		    cadena.append("        return oAccionInfoEditar"+nombreEnBeanTabla+".execute();"+"\n");
		    cadena.append("    }"+"\n");
		    cadena.append("\n");
	    }
	    
	    
	    if(!esVista && listaCamposPK.size()>0) {
		    cadena.append("    public static Map actualizar"+nombreEnBeanTabla+"("+nombreEnBeanTabla+" o"+nombreEnBeanTabla+") throws Exception {"+"\n");
		    cadena.append("        return actualizar"+nombreEnBeanTabla+"(");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				cadena.append("o"+nombreEnBeanTabla+"."+nombreEnBean+",");
			}
		    cadena.delete(cadena.length()-1, cadena.length());
		    cadena.append(","+" o"+nombreEnBeanTabla+");"+"\n");
		    cadena.append("    }"+"\n");
	    }	    
	    
	    if(!esVista && listaCamposPK.size()>0) {
		    cadena.append("    public static Map actualizar"+nombreEnBeanTabla+"(");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				String tipoDatoLenguaje = (String)((Map)listaCamposPK.get(i)).get("tipoDatoLenguaje");
				cadena.append(tipoDatoLenguaje+" "+nombreEnBean+",");
			}
		    cadena.delete(cadena.length()-1, cadena.length());
		    cadena.append(","+nombreEnBeanTabla+" o"+nombreEnBeanTabla+") throws Exception{"+"\n");
		    cadena.append("        AccionActualizar"+nombreEnBeanTabla+" oAccionActualizar"+nombreEnBeanTabla+" = new AccionActualizar"+nombreEnBeanTabla+"(");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				cadena.append(nombreEnBean+",");
			}
		    cadena.delete(cadena.length()-1, cadena.length());
		    cadena.append(","+" o"+nombreEnBeanTabla+");"+"\n");
		    cadena.append("        return oAccionActualizar"+nombreEnBeanTabla+".execute();"+"\n");
		    cadena.append("    }"+"\n");
		    cadena.append("\n");
	    }
	    
	    if(!esVista && listaCamposPK.size()>0) {
		    cadena.append("    public static void eliminar"+nombreEnBeanTabla+"(");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				String tipoDatoLenguaje = (String)((Map)listaCamposPK.get(i)).get("tipoDatoLenguaje");
				cadena.append(tipoDatoLenguaje+" "+nombreEnBean+",");
			}
		    cadena.delete(cadena.length()-1, cadena.length());
		    cadena.append(") throws Exception{"+"\n");
		    cadena.append("        AccionEliminar"+nombreEnBeanTabla+" oAccionEliminar"+nombreEnBeanTabla+" = new AccionEliminar"+nombreEnBeanTabla+"(");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				cadena.append(nombreEnBean+",");
			}
		    cadena.delete(cadena.length()-1, cadena.length());
		    cadena.append(");"+"\n");
		    cadena.append("        oAccionEliminar"+nombreEnBeanTabla+".execute();"+"\n");
		    cadena.append("    }"+"\n");
		    cadena.append("\n");
	    }
	    
	    if(!esVista) {
		    cadena.append("    public static Map insertar"+nombreEnBeanTabla+"("+nombreEnBeanTabla+" o"+nombreEnBeanTabla+") throws Exception{"+"\n");
		    cadena.append("        AccionInsertar"+nombreEnBeanTabla+" oAccionInsertar"+nombreEnBeanTabla+" = new AccionInsertar"+nombreEnBeanTabla+"(o"+nombreEnBeanTabla+");"+"\n");
		    cadena.append("        return oAccionInsertar"+nombreEnBeanTabla+".execute();"+"\n");
		    cadena.append("    }"+"\n");
		    cadena.append("\n");
	    }

	    if(!esVista) {
		    cadena.append("    public static Map insertar"+nombreEnBeanTabla+"AInc("+nombreEnBeanTabla+" o"+nombreEnBeanTabla+") throws Exception{"+"\n");
		    cadena.append("        AccionInsertar"+nombreEnBeanTabla+"AInc oAccionInsertar"+nombreEnBeanTabla+"AInc = new AccionInsertar"+nombreEnBeanTabla+"AInc(o"+nombreEnBeanTabla+");"+"\n");
		    cadena.append("        return oAccionInsertar"+nombreEnBeanTabla+"AInc.execute();"+"\n");
		    cadena.append("    }"+"\n");
		    cadena.append("\n");
	    }

	    if(!esVista) {
		    cadena.append("    public static Map insertar"+nombreEnBeanTabla+"Sec("+nombreEnBeanTabla+" o"+nombreEnBeanTabla+") throws Exception{"+"\n");
		    cadena.append("        AccionInsertar"+nombreEnBeanTabla+"Sec oAccionInsertar"+nombreEnBeanTabla+"Sec = new AccionInsertar"+nombreEnBeanTabla+"Sec(o"+nombreEnBeanTabla+");"+"\n");
		    cadena.append("        return oAccionInsertar"+nombreEnBeanTabla+"Sec.execute();"+"\n");
		    cadena.append("    }"+"\n");
		    cadena.append("\n");
	    }
	    
	    cadena.append("}"+"\n");
		
		return cadena;
	}

}
