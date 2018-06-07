/**
 * 
 */
package com.edw.gen.modelo.formatearcodigo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.edw.gen.apigen.ExcepcionNegocio;
import com.edw.gen.modelo.util.Constantes;
import com.edw.gen.modelo.util.ConversorDeClase;
import com.edw.gen.modelo.util.OrdenarListaDeObjetos;

/**
 * @author edward
 *
 */
public class FormatearEnJavaAcciones {
	
	private String nombreTabla;
	private String nombreEnBeanTabla;
	private String paqueteAccion;
	private String paqueteBeans;
	private String paqueteDaos;
	private String paqueteExecute;
	private String paqueteSecuenciasIncrementos;
	private String metodoCapturaUltimoIdInsertado;
	private boolean esVista;
	
	private static boolean ES_Transaccional = true;
	private static boolean NO_ES_Transaccional = false;
	
	private List listaCampos;
	private List listaCamposPK;
	private StringBuffer cadenaTiposCamposPK = new StringBuffer();
	private StringBuffer cadenaCamposPK = new StringBuffer();
	
	public FormatearEnJavaAcciones(String nombreTabla, String nombreEnBeanTabla, Map mapCamposDeLaTabla, String paqueteAccion,String paqueteBeans,String paqueteDaos,String paqueteExecute,String paqueteSecuenciasIncrementos,String metodoCapturaUltimoIdInsertado,boolean esVista) {
		this.nombreTabla = nombreTabla;
		this.nombreEnBeanTabla = nombreEnBeanTabla;
		this.paqueteAccion = paqueteAccion;
		this.paqueteBeans = paqueteBeans;
		this.paqueteDaos = paqueteDaos;
		this.paqueteExecute = paqueteExecute;
		this.paqueteSecuenciasIncrementos = paqueteSecuenciasIncrementos;
		this.metodoCapturaUltimoIdInsertado = metodoCapturaUltimoIdInsertado;
		
		// mapCamposDeLaTabla tiene los siguientes atributos:
		// "nombre" 			String,		"nombreEnBean"		String,			"tipoDatoJDBC"		Integer,
		// "tipoDatoJDBCCadena" String,		"tipoDatoLenguaje"	String,			"esNulable"			Boolean,
		// "comentario"			String,		"secuenciaPK"		Integer
		
		// Lista Completa de campos ordenados por secuenciaPK y por nombre:
		this.listaCampos = new ArrayList();
		// Lista de campos de la clave primaria ordenados por secuenciaPK y por nombre: 
		this.listaCamposPK = new ArrayList();
		
		//Se llenan la lista:
		Iterator itMapCamposDeLaTabla = mapCamposDeLaTabla.entrySet().iterator();		
		while(itMapCamposDeLaTabla.hasNext()) {
			Map.Entry oMapEntryCampos = (Map.Entry) itMapCamposDeLaTabla.next();
			//String nombreCampo = (String)oMapEntryCampos.getKey();
			Map oMapCampo = (Map)oMapEntryCampos.getValue();
			
			listaCampos.add(oMapCampo);
			if((Integer)oMapCampo.get("secuenciaPK")!=null) {
				this.listaCamposPK.add(oMapCampo);
			}
		}
		new OrdenarListaDeObjetos().ordenarPorPropiedades(this.listaCampos,"secuenciaPK","nombre");
		new OrdenarListaDeObjetos().ordenarPorPropiedades(this.listaCamposPK,"secuenciaPK","nombre");
		
		this.cadenaTiposCamposPK = new StringBuffer();
	    for (int i=0; i<listaCamposPK.size(); i++) {
			String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
			String tipoDatoLenguaje = (String)((Map)listaCamposPK.get(i)).get("tipoDatoLenguaje");
			cadenaTiposCamposPK.append(tipoDatoLenguaje+" "+nombreEnBean+",");
		}
	    if(cadenaTiposCamposPK.length()>0) cadenaTiposCamposPK.delete(cadenaTiposCamposPK.length()-1, cadenaTiposCamposPK.length());
	    
	    this.cadenaCamposPK = new StringBuffer();
	    for (int i=0; i<listaCamposPK.size(); i++) {
			String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
			cadenaCamposPK.append(nombreEnBean+",");
		}
	    if(cadenaCamposPK.length()>0) cadenaCamposPK.delete(cadenaCamposPK.length()-1, cadenaCamposPK.length());

	}
	
	public StringBuffer getFormatoAccionInfoListar() throws Exception {
		if(paqueteAccion==null || paqueteAccion.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino");
		if(paqueteBeans==null || paqueteBeans.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino de los Beans");
		if(paqueteDaos==null || paqueteDaos.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino de los Daos");
		if(paqueteExecute==null || paqueteExecute.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino de los Execute");
		if(nombreEnBeanTabla==null || nombreEnBeanTabla.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el nombre de la tabla");
		if(metodoCapturaUltimoIdInsertado==null || metodoCapturaUltimoIdInsertado.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el método de captura del último ID insertado");
		
		StringBuffer cadena = new StringBuffer();
		
	    cadena.append("\n");
	    cadena.append("package "+paqueteAccion +";\n");
	    cadena.append("\n");
	    cadena.append("import java.sql.Connection;"+"\n");
	    cadena.append("import java.sql.SQLException;"+"\n");
	    cadena.append("import java.util.HashMap;"+"\n");
	    cadena.append("import java.util.List;"+"\n");
	    cadena.append("import java.util.Map;"+"\n");
	    cadena.append("\n");
	    cadena.append("import "+paqueteBeans+"."+nombreEnBeanTabla+";"+"\n");
	    cadena.append("import "+paqueteDaos+".DAO"+nombreEnBeanTabla+";"+"\n");
	    cadena.append("import com.edw.gen.apigen.*;"+"\n");
	    cadena.append("import "+paqueteExecute+".ExecuteNoTransaccional;"+"\n");
	    cadena.append("\n");
	    cadena.append("/* @author Edward */"+"\n");
	    cadena.append("\n");
	    cadena.append("public class AccionInfoListar"+nombreEnBeanTabla+" extends ExecuteNoTransaccional {"+"\n");
	    cadena.append("\n");
	    cadena.append("    private Map oMapFiltros;"+"\n");
	    cadena.append("\n");
	    cadena.append("    public AccionInfoListar"+nombreEnBeanTabla+"(Map oMapFiltros) {"+"\n");
	    cadena.append("        this.oMapFiltros = oMapFiltros;"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    cadena.append("    public Map execute(Connection conn) throws Exception{"+"\n");
	    cadena.append("\n");
	    cadena.append("        List lista"+nombreEnBeanTabla+" = new DAO"+nombreEnBeanTabla+"().select(conn);"+"\n");
	    cadena.append("\n");
	    cadena.append("        Map oMap = new HashMap();"+"\n");
	    cadena.append("        oMap.put(\"lista"+nombreEnBeanTabla+"\",lista"+nombreEnBeanTabla+");"+"\n");
	    cadena.append("        return oMap;"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    //this.getStringExecuteCreaConexion(false);
	    //cadena.append("\n");
	    cadena.append("}"+"\n");
	    
		return cadena;
	}
	
	public StringBuffer getFormatoAccionInfoEditar() throws Exception{
		if(paqueteAccion==null || paqueteAccion.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino");
		if(paqueteBeans==null || paqueteBeans.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino de los Beans");
		if(paqueteDaos==null || paqueteDaos.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino delos Daos");
		if(nombreEnBeanTabla==null || nombreEnBeanTabla.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el nombre de la tabla");
		
		StringBuffer cadena = new StringBuffer();
		
		if(!esVista && listaCamposPK.size()>0) {
		    cadena.append("\n");
		    cadena.append("package "+paqueteAccion +";\n");
		    cadena.append("\n");
		    cadena.append("import java.sql.Connection;"+"\n");
		    cadena.append("import java.sql.SQLException;"+"\n");
		    cadena.append("import java.util.HashMap;"+"\n");
		    cadena.append("import java.util.Map;"+"\n");
		    cadena.append("\n");
		    cadena.append("import "+paqueteBeans+"."+nombreEnBeanTabla+";"+"\n");
		    cadena.append("import "+paqueteDaos+".DAO"+nombreEnBeanTabla+";"+"\n");
		    cadena.append("import com.edw.gen.apigen.*;"+"\n");
		    cadena.append("import "+paqueteExecute+".ExecuteNoTransaccional;"+"\n");
		    cadena.append("\n");		    
		    cadena.append("/* @author Edward */"+"\n");
		    cadena.append("\n");
		    cadena.append("public class AccionInfoEditar"+nombreEnBeanTabla+" extends ExecuteNoTransaccional {"+"\n");
		    cadena.append("\n");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				String tipoDatoLenguaje = (String)((Map)listaCamposPK.get(i)).get("tipoDatoLenguaje");
				
				cadena.append("    private "+tipoDatoLenguaje+" "+nombreEnBean+";"+"\n");
			}
		    cadena.append("\n");
		    cadena.append("    public AccionInfoEditar"+nombreEnBeanTabla+"(");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				String tipoDatoLenguaje = (String)((Map)listaCamposPK.get(i)).get("tipoDatoLenguaje");
				cadena.append(tipoDatoLenguaje+" "+nombreEnBean+",");
			}
		    cadena.delete(cadena.length()-1, cadena.length());
		    cadena.append(")"+" {"+"\n");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				cadena.append("        this."+nombreEnBean+" = "+nombreEnBean+";"+"\n");
			}
		    cadena.append("    }"+"\n");
		    cadena.append("\n");
		    cadena.append("    public Map execute(Connection conn) throws Exception{"+"\n");
		    cadena.append("\n");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				cadena.append("        if("+nombreEnBean+"==null) throw new ExcepcionNegocio(\"No ha ingresado el valor de "+nombreEnBean+" de "+nombreEnBeanTabla+"\");"+"\n");
			}
		    cadena.append("\n");
		    cadena.append("        Map oMap = new HashMap();"+"\n");
		    cadena.append("        oMap.put(\""+nombreEnBeanTabla.substring(0,1).toLowerCase()+nombreEnBeanTabla.substring(1)+"\",new DAO"+nombreEnBeanTabla+"().selectPK(conn,");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				cadena.append(nombreEnBean+",");
			}
		    cadena.delete(cadena.length()-1, cadena.length());
		    cadena.append("));"+"\n");
		    cadena.append("        return oMap;"+"\n");
		    cadena.append("    }"+"\n");
		    cadena.append("\n");
		    //this.getStringExecuteCreaConexion(false);
		    //cadena.append("\n");
		    cadena.append("}"+"\n");
		}
		
		return cadena;
	}	
	
	public StringBuffer getFormatoAccionActualizar() throws Exception{
		if(paqueteAccion==null || paqueteAccion.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino");
		if(paqueteBeans==null || paqueteBeans.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino de los Beans");
		if(paqueteDaos==null || paqueteDaos.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino delos Daos");
		if(nombreEnBeanTabla==null || nombreEnBeanTabla.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el nombre de la tabla");
		
		StringBuffer cadena = new StringBuffer();
		
		if(!esVista && listaCamposPK.size()>0) {
			
		    cadena.append("\n");
		    cadena.append("package "+paqueteAccion +";\n");
		    cadena.append("\n");
		    cadena.append("import java.sql.Connection;"+"\n");
		    cadena.append("import java.sql.SQLException;"+"\n");
		    cadena.append("import java.util.HashMap;"+"\n");
		    cadena.append("import java.util.Map;"+"\n");
		    cadena.append("\n");
		    cadena.append("import "+paqueteBeans+"."+nombreEnBeanTabla+";"+"\n");
		    cadena.append("import "+paqueteDaos+".DAO"+nombreEnBeanTabla+";"+"\n");
		    cadena.append("import com.edw.gen.apigen.*;"+"\n");
		    cadena.append("import "+paqueteExecute+".ExecuteTransaccional;"+"\n");
		    cadena.append("\n");		    
		    cadena.append("/* @author Edward */"+"\n");
		    cadena.append("\n");
		    cadena.append("public class AccionActualizar"+nombreEnBeanTabla+" extends ExecuteTransaccional {"+"\n");
		    cadena.append("\n");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				String tipoDatoLenguaje = (String)((Map)listaCamposPK.get(i)).get("tipoDatoLenguaje");
				cadena.append("    private "+tipoDatoLenguaje+" "+nombreEnBean+";"+"\n");
			}
		    cadena.append("    private "+nombreEnBeanTabla+" o"+nombreEnBeanTabla+";"+"\n");
		    cadena.append("    public AccionActualizar"+nombreEnBeanTabla+"(");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				String tipoDatoLenguaje = (String)((Map)listaCamposPK.get(i)).get("tipoDatoLenguaje");
				cadena.append(tipoDatoLenguaje+" "+nombreEnBean+",");
			}
		    cadena.delete(cadena.length()-1, cadena.length());
		    cadena.append(","+nombreEnBeanTabla+" o"+nombreEnBeanTabla+") {"+"\n");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				cadena.append("        this."+nombreEnBean+" = "+nombreEnBean+";"+"\n");
			}
		    cadena.append("        this.o"+nombreEnBeanTabla+" = o"+nombreEnBeanTabla+";"+"\n");
		    cadena.append("    }"+"\n");
		    cadena.append("\n");
		    cadena.append("    public Map execute(Connection conn) throws Exception{"+"\n");
		    cadena.append("\n");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				cadena.append("        if("+nombreEnBean+"==null) throw new ExcepcionNegocio(\"No ha ingresado el valor de "+nombreEnBean+" de "+nombreEnBeanTabla+"\");"+"\n");
			}
		    cadena.append("        if(o"+nombreEnBeanTabla+"==null) throw new ExcepcionNegocio(\"No ha ingresado los valores de "+nombreEnBeanTabla+"\");"+"\n");
		    cadena.append("\n");
		    cadena.append("        "+nombreEnBeanTabla+" o"+nombreEnBeanTabla+"BD"+" = new DAO"+nombreEnBeanTabla+"().selectPK(conn,");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				cadena.append(nombreEnBean+",");
			}
		    cadena.delete(cadena.length()-1, cadena.length());
		    cadena.append(");"+"\n");
		    for (int i=0; i<listaCampos.size(); i++) {
				String nombreEnBean = (String)((Map)listaCampos.get(i)).get("nombreEnBean");
				cadena.append("        o"+nombreEnBeanTabla+"BD."+nombreEnBean+" = o"+nombreEnBeanTabla+"."+nombreEnBean+";"+"\n");
			}
		    cadena.append("\n");
		    cadena.append("        new DAO"+nombreEnBeanTabla+"().actualizar(conn,");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				cadena.append(nombreEnBean+",");
			}
		    cadena.append("o"+nombreEnBeanTabla+"BD);"+"\n");
		    cadena.append("\n");
		    cadena.append("        Map oMap = new HashMap();"+"\n");
		    cadena.append("        oMap.put(\""+nombreEnBeanTabla.substring(0,1).toLowerCase()+nombreEnBeanTabla.substring(1)+"\", o"+nombreEnBeanTabla+");"+"\n");
		    cadena.append("        return oMap;"+"\n");
		    cadena.append("    }"+"\n");
		    cadena.append("\n");
		    //this.getStringExecuteCreaConexion(true);
		    //cadena.append("\n");
		    cadena.append("}"+"\n");
		}
		return cadena;
	}
	
	public StringBuffer getFormatoAccionEliminar() throws Exception{
		if(paqueteAccion==null || paqueteAccion.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino");
		if(paqueteBeans==null || paqueteBeans.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino de los Beans");
		if(paqueteDaos==null || paqueteDaos.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino delos Daos");
		if(nombreEnBeanTabla==null || nombreEnBeanTabla.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el nombre de la tabla");
		
		StringBuffer cadena = new StringBuffer();
		
		if(!esVista && listaCamposPK.size()>0) {
		    cadena.append("\n");
		    cadena.append("package "+paqueteAccion +";\n");
		    cadena.append("\n");
		    cadena.append("import java.sql.Connection;"+"\n");
		    cadena.append("import java.sql.SQLException;"+"\n");
		    cadena.append("import java.util.HashMap;"+"\n");
		    cadena.append("import java.util.Map;"+"\n");
		    cadena.append("\n");
		    cadena.append("import "+paqueteBeans+"."+nombreEnBeanTabla+";"+"\n");
		    cadena.append("import "+paqueteDaos+".DAO"+nombreEnBeanTabla+";"+"\n");
		    cadena.append("import com.edw.gen.apigen.*;"+"\n");
		    cadena.append("import "+paqueteExecute+".ExecuteTransaccional;"+"\n");
		    cadena.append("\n");		    
		    cadena.append("/* @author Edward */"+"\n");
		    cadena.append("\n");
		    cadena.append("public class AccionEliminar"+nombreEnBeanTabla+" extends ExecuteTransaccional {"+"\n");
		    cadena.append("\n");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				String tipoDatoLenguaje = (String)((Map)listaCamposPK.get(i)).get("tipoDatoLenguaje");
				cadena.append("    private "+tipoDatoLenguaje+" "+nombreEnBean+";"+"\n");
			}
		    cadena.append("\n");
		    cadena.append("    public AccionEliminar"+nombreEnBeanTabla+"(");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				String tipoDatoLenguaje = (String)((Map)listaCamposPK.get(i)).get("tipoDatoLenguaje");
				cadena.append(tipoDatoLenguaje+" "+nombreEnBean+",");
			}
		    cadena.delete(cadena.length()-1, cadena.length());
			cadena.append(") {"+"\n");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				cadena.append("        this."+nombreEnBean+" = "+nombreEnBean+";"+"\n");
			}			
		    cadena.append("    }"+"\n");
		    cadena.append("\n");
		    cadena.append("    public Map execute(Connection conn) throws Exception{"+"\n");
		    cadena.append("\n");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				cadena.append("        if("+nombreEnBean+"==null) throw new ExcepcionNegocio(\"No ha ingresado el valor de "+nombreEnBean+" de "+nombreEnBeanTabla+"\");"+"\n");
			}		    
		    cadena.append("\n");
		    cadena.append("        new DAO"+nombreEnBeanTabla+"().eliminar(conn,");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				cadena.append(nombreEnBean+",");
			}
		    cadena.delete(cadena.length()-1, cadena.length()); 
		    cadena.append(");"+"\n");
		    cadena.append("\n");
		    cadena.append("        Map oMap = new HashMap();"+"\n");
		    cadena.append("        return oMap;"+"\n");
		    cadena.append("    }"+"\n");
		    cadena.append("\n");
		    //this.getStringExecuteCreaConexion(true);
		    //cadena.append("\n");		    
		    cadena.append("}"+"\n");
		}
		return cadena;
	}
	
	
	public StringBuffer getFormatoAccionInsertar() throws Exception{
		if(paqueteAccion==null || paqueteAccion.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino");
		if(paqueteBeans==null || paqueteBeans.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino de los Beans");
		if(paqueteDaos==null || paqueteDaos.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino delos Daos");
		if(nombreEnBeanTabla==null || nombreEnBeanTabla.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el nombre de la tabla");
		
		StringBuffer cadena = new StringBuffer();
		
	    cadena.append("\n");
	    cadena.append("package "+paqueteAccion +";\n");
	    cadena.append("\n");
	    cadena.append("import java.sql.Connection;"+"\n");
	    cadena.append("import java.sql.SQLException;"+"\n");
	    cadena.append("import java.util.HashMap;"+"\n");
	    cadena.append("import java.util.Map;"+"\n");
	    cadena.append("\n");
	    cadena.append("import "+paqueteBeans+"."+nombreEnBeanTabla+";"+"\n");
	    cadena.append("import "+paqueteDaos+".DAO"+nombreEnBeanTabla+";"+"\n");
	    cadena.append("import com.edw.gen.apigen.*;"+"\n");
	    cadena.append("import "+paqueteExecute+".ExecuteTransaccional;"+"\n");
	    cadena.append("\n");	    
	    cadena.append("/* @author Edward */"+"\n");
	    cadena.append("\n");
	    cadena.append("public class AccionInsertar"+nombreEnBeanTabla+" extends ExecuteTransaccional {"+"\n");
	    cadena.append("\n");
	    cadena.append("    private "+nombreEnBeanTabla+" o"+nombreEnBeanTabla+";"+"\n");
	    cadena.append("\n");
	    cadena.append("    public AccionInsertar"+nombreEnBeanTabla+"("+nombreEnBeanTabla+" o"+nombreEnBeanTabla+") {"+"\n");
	    cadena.append("        this.o"+nombreEnBeanTabla+" = o"+nombreEnBeanTabla+";"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    cadena.append("    public Map execute(Connection conn) throws Exception {"+"\n");
	    cadena.append("\n");
	    cadena.append("        if(o"+nombreEnBeanTabla+"==null) throw new ExcepcionNegocio(\"No ha ingresado los valores de "+nombreEnBeanTabla+"\");"+"\n");
	    cadena.append("\n");
	    cadena.append("        "+nombreEnBeanTabla+" o"+nombreEnBeanTabla+"BD"+" = new "+nombreEnBeanTabla+"();"+"\n");
	    for (int i=0; i<listaCampos.size(); i++) {
			String nombreEnBean = (String)((Map)listaCampos.get(i)).get("nombreEnBean");
			cadena.append("        o"+nombreEnBeanTabla+"BD."+nombreEnBean+" = o"+nombreEnBeanTabla+"."+nombreEnBean+";"+"\n");
		}
	    cadena.append("\n");
	    cadena.append("        new DAO"+nombreEnBeanTabla+"().insertar(conn, o"+nombreEnBeanTabla+"BD);"+"\n");
	    cadena.append("\n");
	    cadena.append("        Map oMap = new HashMap();"+"\n");
	    cadena.append("        oMap.put(\""+nombreEnBeanTabla.substring(0,1).toLowerCase()+nombreEnBeanTabla.substring(1)+"\",o"+nombreEnBeanTabla+"BD);"+"\n");
	    cadena.append("        return oMap;"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    //this.getStringExecuteCreaConexion(true);
	    //cadena.append("\n");
	    cadena.append("}"+"\n");
	    
		return cadena;
	}
	
	public StringBuffer getFormatoAccionInsertarAInc() throws Exception{
		if(paqueteAccion==null || paqueteAccion.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino");
		if(paqueteBeans==null || paqueteBeans.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino de los Beans");
		if(paqueteDaos==null || paqueteDaos.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino delos Daos");
		if(nombreEnBeanTabla==null || nombreEnBeanTabla.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el nombre de la tabla");
		
		StringBuffer cadena = new StringBuffer();
		
	    cadena.append("\n");
	    cadena.append("package "+paqueteAccion +";\n");
	    cadena.append("\n");
	    cadena.append("import java.sql.Connection;"+"\n");
	    cadena.append("import java.sql.SQLException;"+"\n");
	    cadena.append("import java.util.HashMap;"+"\n");
	    cadena.append("import java.util.Map;"+"\n");
	    cadena.append("\n");
	    cadena.append("import "+paqueteBeans+"."+nombreEnBeanTabla+";"+"\n");
	    cadena.append("import "+paqueteDaos+".DAO"+nombreEnBeanTabla+";"+"\n");
	    cadena.append("import com.edw.gen.apigen.*;"+"\n");
	    cadena.append("import "+paqueteExecute+".ExecuteTransaccional;"+"\n");
	    cadena.append("import "+paqueteSecuenciasIncrementos+".SecuenciasIncrementos;"+"\n");
	    cadena.append("\n");	    
	    cadena.append("/* @author Edward */"+"\n");
	    cadena.append("\n");
	    cadena.append("public class AccionInsertar"+nombreEnBeanTabla+"AInc extends ExecuteTransaccional {"+"\n");
	    cadena.append("\n");
	    cadena.append("    private "+nombreEnBeanTabla+" o"+nombreEnBeanTabla+";"+"\n");
	    cadena.append("\n");
	    cadena.append("    public AccionInsertar"+nombreEnBeanTabla+"AInc("+nombreEnBeanTabla+" o"+nombreEnBeanTabla+") {"+"\n");
	    cadena.append("        this.o"+nombreEnBeanTabla+" = o"+nombreEnBeanTabla+";"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    cadena.append("    public Map execute(Connection conn) throws Exception {"+"\n");
	    cadena.append("\n");
	    cadena.append("        if(o"+nombreEnBeanTabla+"==null) throw new ExcepcionNegocio(\"No ha ingresado los valores de "+nombreEnBeanTabla+"\");"+"\n");
	    cadena.append("\n");
	    cadena.append("        "+nombreEnBeanTabla+" o"+nombreEnBeanTabla+"BD"+" = new "+nombreEnBeanTabla+"();"+"\n");
	    for (int i=0; i<listaCampos.size(); i++) {
			String nombreEnBean = (String)((Map)listaCampos.get(i)).get("nombreEnBean");
			cadena.append("        o"+nombreEnBeanTabla+"BD."+nombreEnBean+" = o"+nombreEnBeanTabla+"."+nombreEnBean+";"+"\n");
		}
	    cadena.append("\n");
	    cadena.append("        new DAO"+nombreEnBeanTabla+"().insertarAInc(conn, o"+nombreEnBeanTabla+"BD);"+"\n");
	    cadena.append("\n");
	    
	    String ultimoIdInsertado = "";
    	if(metodoCapturaUltimoIdInsertado.equalsIgnoreCase(Constantes.LAST_INSERT_ID)) { 
    		ultimoIdInsertado = "SecuenciasIncrementos.LAST_INSERT_ID";
    	}
    	else if(metodoCapturaUltimoIdInsertado.equalsIgnoreCase(Constantes.CUR_VAL)) {
    		ultimoIdInsertado = "SecuenciasIncrementos.CUR_VAL";
    	}
    	else if(metodoCapturaUltimoIdInsertado.equalsIgnoreCase(Constantes.MAX_ID)) {
    		ultimoIdInsertado = "SecuenciasIncrementos.MAX_ID";
    	}
    	
	    cadena.append("        Object id = new DAO"+nombreEnBeanTabla+"().getUltimoIdInsertado(conn,"+ultimoIdInsertado+");"+"\n");
	    
	    if(listaCamposPK.size()==1){
	    	String nombreEnBean = (String)((Map)listaCamposPK.get(0)).get("nombreEnBean");
			String tipoDatoLenguaje = (String)((Map)listaCamposPK.get(0)).get("tipoDatoLenguaje");
			cadena.append("        "+nombreEnBeanTabla+" o"+nombreEnBeanTabla+" = new DAO"+nombreEnBeanTabla+"().selectPK(conn,"+new ConversorDeClase().getCastObjeto(tipoDatoLenguaje, "id")+");"+"\n");
	    }
	    else { // Si entra aca es porque la clave primaria no correponde a una secuencia
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				cadena.append("        //o"+nombreEnBeanTabla+"."+nombreEnBean+" = id;"+"\n");
		    }
		    
		    cadena.append("        if(true) throw new Exception(\"No esta definida la insercion de "+nombreEnBeanTabla+" en la Base de Datos con autoincremento\");"+"\n");
		    cadena.append("        "+nombreEnBeanTabla+" o"+nombreEnBeanTabla+" = null;"+"\n");
	    }
	    
	    cadena.append("\n");
	    cadena.append("        Map oMap = new HashMap();"+"\n");
	    cadena.append("        oMap.put(\""+nombreEnBeanTabla.substring(0,1).toLowerCase()+nombreEnBeanTabla.substring(1)+"\", o"+nombreEnBeanTabla+");"+"\n");
	    cadena.append("        return oMap;"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    //this.getStringExecuteCreaConexion(true);
	    //cadena.append("\n");	    
	    cadena.append("}"+"\n");
	    
		return cadena;
	}
	
	public StringBuffer getFormatoAccionInsertarSec() throws Exception{
		if(paqueteAccion==null || paqueteAccion.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino");
		if(paqueteBeans==null || paqueteBeans.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino de los Beans");
		if(paqueteDaos==null || paqueteDaos.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino delos Daos");
		if(nombreEnBeanTabla==null || nombreEnBeanTabla.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el nombre de la tabla");
		
		StringBuffer cadena = new StringBuffer();
		
	    cadena.append("\n");
	    cadena.append("package "+paqueteAccion +";\n");
	    cadena.append("\n");
	    cadena.append("import java.sql.Connection;"+"\n");
	    cadena.append("import java.sql.SQLException;"+"\n");
	    cadena.append("import java.util.HashMap;"+"\n");
	    cadena.append("import java.util.Map;"+"\n");
	    cadena.append("\n");
	    cadena.append("import "+paqueteBeans+"."+nombreEnBeanTabla+";"+"\n");
	    cadena.append("import "+paqueteDaos+".DAO"+nombreEnBeanTabla+";"+"\n");
	    cadena.append("import com.edw.gen.apigen.*;"+"\n");
	    cadena.append("import "+paqueteExecute+".ExecuteTransaccional;"+"\n");
	    cadena.append("import "+paqueteSecuenciasIncrementos+".SecuenciasIncrementos;"+"\n");
	    cadena.append("\n");	    
	    cadena.append("/* @author Edward */"+"\n");
	    cadena.append("\n");
	    cadena.append("public class AccionInsertar"+nombreEnBeanTabla+"Sec extends ExecuteTransaccional {"+"\n");
	    cadena.append("\n");
	    cadena.append("    private "+nombreEnBeanTabla+" o"+nombreEnBeanTabla+";"+"\n");
	    cadena.append("\n");
	    cadena.append("    public AccionInsertar"+nombreEnBeanTabla+"Sec("+nombreEnBeanTabla+" o"+nombreEnBeanTabla+") {"+"\n");
	    cadena.append("        this.o"+nombreEnBeanTabla+" = o"+nombreEnBeanTabla+";"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    cadena.append("    public Map execute(Connection conn) throws Exception {"+"\n");
	    cadena.append("\n");
	    cadena.append("        if(o"+nombreEnBeanTabla+"==null) throw new ExcepcionNegocio(\"No ha ingresado los valores de "+nombreEnBeanTabla+"\");"+"\n");
	    cadena.append("\n");
	    cadena.append("        "+nombreEnBeanTabla+" o"+nombreEnBeanTabla+"BD"+" = new "+nombreEnBeanTabla+"();"+"\n");
	    for (int i=0; i<listaCampos.size(); i++) {
			String nombreEnBean = (String)((Map)listaCampos.get(i)).get("nombreEnBean");
			cadena.append("        o"+nombreEnBeanTabla+"BD."+nombreEnBean+" = o"+nombreEnBeanTabla+"."+nombreEnBean+";"+"\n");
		}
	    cadena.append("\n");
	    cadena.append("        Object id = new DAO"+nombreEnBeanTabla+"().insertarSec(conn, o"+nombreEnBeanTabla+"BD,SecuenciasIncrementos.SQLSECUENCIA_"+nombreEnBeanTabla.toUpperCase()+");"+"\n");
	    cadena.append("\n");
	    
	    if(listaCamposPK.size()==1){
	    	String nombreEnBean = (String)((Map)listaCamposPK.get(0)).get("nombreEnBean");
			String tipoDatoLenguaje = (String)((Map)listaCamposPK.get(0)).get("tipoDatoLenguaje");
			cadena.append("        "+nombreEnBeanTabla+" o"+nombreEnBeanTabla+" = new DAO"+nombreEnBeanTabla+"().selectPK(conn,"+new ConversorDeClase().getCastObjeto(tipoDatoLenguaje, "id")+");"+"\n");
	    }
	    else { // Si entra aca es porque la clave primaria no correponde a una secuencia
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				cadena.append("        //o"+nombreEnBeanTabla+"."+nombreEnBean+" = id;"+"\n");
		    }
		    
		    cadena.append("        if(true) throw new Exception(\"No esta definida la insercion de "+nombreEnBeanTabla+" en la Base de Datos con una secuencia\");"+"\n");
		    cadena.append("        "+nombreEnBeanTabla+" o"+nombreEnBeanTabla+" = null;"+"\n");
	    }	    
	    
	    cadena.append("\n");
	    cadena.append("        Map oMap = new HashMap();"+"\n");
	    cadena.append("        oMap.put(\""+nombreEnBeanTabla.substring(0,1).toLowerCase()+nombreEnBeanTabla.substring(1)+"\", o"+nombreEnBeanTabla+");"+"\n");
	    cadena.append("        return oMap;"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    //this.getStringExecuteCreaConexion(true);
	    //cadena.append("\n");
	    cadena.append("}"+"\n");
	    
		return cadena;
	}
	
	/*
	private StringBuffer getStringExecuteCreaConexion(boolean esTransaccional) {
		
		StringBuffer cadena = new StringBuffer();
		
		cadena.append("    public Map execute() throws Exception{"+"\n");
		cadena.append("        Connection conn = null;"+"\n");
		if(esTransaccional) cadena.append("        boolean commited = false;"+"\n");
		cadena.append("        try{"+"\n");
		cadena.append("            conn = new Conexion().getConexion();"+"\n");
		if(esTransaccional) cadena.append("            conn.setAutoCommit(false);"+"\n");
		else cadena.append("            conn.setAutoCommit(true);"+"\n"); 
		cadena.append("            Map oMap = execute(conn);"+"\n");
		if(esTransaccional) cadena.append("            conn.commit();"+"\n");
		if(esTransaccional) cadena.append("            commited = true;"+"\n");
		cadena.append("            return oMap;"+"\n");
		cadena.append("        }"+"\n");
		cadena.append("        catch (ExcepcionNegocio e) {"+"\n");
		cadena.append("            e.printStackTrace();"+"\n");
		cadena.append("            throw e;"+"\n");
		cadena.append("        }"+"\n");
		cadena.append("        catch (SQLException e) {"+"\n");
		cadena.append("            e.printStackTrace();"+"\n");
		cadena.append("            throw e;"+"\n");
		cadena.append("        }"+"\n");
		cadena.append("        catch(Exception e) {"+"\n");
		cadena.append("            e.printStackTrace();"+"\n");
		cadena.append("            throw e;"+"\n");
		cadena.append("        }"+"\n");
		cadena.append("        finally {"+"\n");
		cadena.append("            try {"+"\n");
		cadena.append("                if (conn != null) {"+"\n");
		if(esTransaccional) cadena.append("                    if (!commited) { conn.rollback(); }"+"\n");
		cadena.append("                    conn.close();"+"\n");
		cadena.append("                }"+"\n");
		cadena.append("            } catch (SQLException e) {"+"\n");
		cadena.append("                e.printStackTrace();"+"\n");
		cadena.append("            }"+"\n");
		cadena.append("        }"+"\n");
		cadena.append("    }"+"\n");
	    
		return cadena;
	}
	*/

}
