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
public class FormatearEnJavaSecuenciasIncrementos {
	
	private Map oMapTablas;
	private String paqueteSecuenciasIncrementos;

	public FormatearEnJavaSecuenciasIncrementos(Map oMapTablas,String paqueteSecuenciasIncrementos) {
		this.oMapTablas = oMapTablas;
		this.paqueteSecuenciasIncrementos = paqueteSecuenciasIncrementos;
	}

	public StringBuffer getFormatoSecuenciasIncrementos() throws Exception {
		//if(mapTablas==null) throw new ExcepcionNegocio("No ha ingresado las tablas");
		if(paqueteSecuenciasIncrementos==null || paqueteSecuenciasIncrementos.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino de las Secuencias e Incrementos");
		
		StringBuffer cadena = new StringBuffer();
		
	    cadena.append("\n");
	    cadena.append("package "+paqueteSecuenciasIncrementos +";\n");
	    cadena.append("\n");
	    cadena.append("/* @author Edward */"+"\n");
	    cadena.append("\n");
	    cadena.append("public class SecuenciasIncrementos {"+"\n");
	    cadena.append("\n");
	    cadena.append("    public static final String LAST_INSERT_ID = \"LAST_INSERT_ID()\"; //para MySql"+"\n");
	    cadena.append("    public static final String CUR_VAL = \"CUR_VAL()\"; // Para Oracle y PostgreSql"+"\n");
	    cadena.append("    public static final String MAX_ID = \"MAX(ID)\";"+"\n");
	    cadena.append("\n");
		//Por cada Tabla se inserta una secuencia en el archivo de secuencias
		Iterator itTablas = oMapTablas.entrySet().iterator();
		while(itTablas.hasNext()) {
			Map.Entry oMapEntryTabla = (Map.Entry) itTablas.next();
			
			Map oMapTabla = (Map)oMapEntryTabla.getValue();
			
			String nombreTabla = (String)oMapTabla.get("nombre");
			String nombreEnBeanTabla = (String)oMapTabla.get("nombreEnBean");
			String esquemaTabla = (String) oMapTabla.get("esquema");
			
			cadena.append("    public static final String SQLSECUENCIA_"+nombreEnBeanTabla.toUpperCase()+" = \"SELECT SEC_"+nombreEnBeanTabla.toUpperCase()+".NEXTVAL FROM "+nombreTabla+" \";"+"\n");
		}
	    cadena.append("}"+"\n");
		
		return cadena;
	}

}
