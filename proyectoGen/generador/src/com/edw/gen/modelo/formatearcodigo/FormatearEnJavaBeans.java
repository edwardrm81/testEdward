package com.edw.gen.modelo.formatearcodigo;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.edw.gen.modelo.util.ConversorTipoJDBCATipoJava;

public class FormatearEnJavaBeans {

	public FormatearEnJavaBeans() {
		
	}

	public StringBuffer getFormato(String nombreEnBeanTabla,String esquemaTabla,String comentarioTabla,Map mapCamposDeLaTabla,String paqueteBeans) {
		StringBuffer cadena = new StringBuffer();
		
	    cadena.append("\n");
	    cadena.append("package "+paqueteBeans +";\n");
	    cadena.append("\n");
	    cadena.append("import java.io.Serializable;"+"\n");
	    cadena.append("\n");
	    cadena.append("/* @author Edward */"+"\n");
	    cadena.append("\n");
	    cadena.append("/** Comentario: "+comentarioTabla+" */"+"\n");
	    cadena.append("public class "+nombreEnBeanTabla+" implements Serializable {"+"\n");
	    cadena.append("\n");
	    cadena.append("    public "+nombreEnBeanTabla+"() {"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    
		Iterator itCampos = mapCamposDeLaTabla.entrySet().iterator();
		while(itCampos.hasNext()) {
			Map.Entry oMapEntryCampos = (Map.Entry) itCampos.next();
			//String nombreCampo = (String)oMapEntryCampos.getKey();
			Map oMapCampos = (Map)oMapEntryCampos.getValue();
    		
			String nombreEnBeanCampo = (String) oMapCampos.get("nombreEnBean");
			String tipoDatoLenguajeCampo = (String) oMapCampos.get("tipoDatoLenguaje");
			String comentarioCampo = (String) oMapCampos.get("comentario");
			
			cadena.append("    /** "+comentarioCampo+" */"+"\n");
			cadena.append("    public "+tipoDatoLenguajeCampo+" "+nombreEnBeanCampo+";"+"\n");
			cadena.append("\n");
		}
		
		itCampos = mapCamposDeLaTabla.entrySet().iterator();
		while(itCampos.hasNext()) {
			Map.Entry oMapEntryCampos = (Map.Entry) itCampos.next();
			//String nombreCampo = (String)oMapEntryCampos.getKey();
			Map oMapCampos = (Map)oMapEntryCampos.getValue();
						
			String nombreEnBeanCampo = (String) oMapCampos.get("nombreEnBean");
			String tipoDatoLenguajeCampo = (String) oMapCampos.get("tipoDatoLenguaje");
			String comentarioCampo = (String) oMapCampos.get("comentario");
			
			cadena.append("    public "+tipoDatoLenguajeCampo+" get"+nombreEnBeanCampo.substring(0,1).toUpperCase()+nombreEnBeanCampo.substring(1)+"() {"+"\n");
			cadena.append("        return "+nombreEnBeanCampo+";"+"\n");
			cadena.append("    }"+"\n");
			cadena.append("    public void"+" set"+nombreEnBeanCampo.substring(0,1).toUpperCase()+nombreEnBeanCampo.substring(1)+"("+tipoDatoLenguajeCampo+" "+nombreEnBeanCampo+") {"+"\n");
			cadena.append("        this."+nombreEnBeanCampo+" = "+nombreEnBeanCampo+";\n");
			cadena.append("    }"+"\n");			
			cadena.append("\n");
			
		}	    
		
		cadena.append("}"+"\n");
		return cadena;
	}

}
