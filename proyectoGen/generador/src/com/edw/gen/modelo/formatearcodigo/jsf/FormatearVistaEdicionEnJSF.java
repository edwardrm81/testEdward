/**
 * 
 */
package com.edw.gen.modelo.formatearcodigo.jsf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.edw.gen.apigen.ExcepcionNegocio;
import com.edw.gen.modelo.util.ConversorDeClase;
import com.edw.gen.modelo.util.OrdenarListaDeObjetos;

/**
 * @author edward
 *
 */
public class FormatearVistaEdicionEnJSF {
	
	private String nombreEnBeanTabla;
	private Map mapCamposDeLaTabla;
	
	public FormatearVistaEdicionEnJSF(String nombreEnBeanTabla,Map mapCamposDeLaTabla) {
		this.nombreEnBeanTabla = nombreEnBeanTabla;
		this.mapCamposDeLaTabla = mapCamposDeLaTabla;
	}
	
	public StringBuffer getFormatoVista() throws Exception {
		if(nombreEnBeanTabla==null || nombreEnBeanTabla.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el nombre de la tabla");
		if(mapCamposDeLaTabla==null || mapCamposDeLaTabla.size()<1) throw new ExcepcionNegocio("No ha ingresado la información de los campos de la tabla");
		
		// mapCamposDeLaTabla tiene los siguientes atributos:
		// "nombre" 			String,		"nombreEnBean"		String,			"tipoDatoJDBC"		Integer,
		// "tipoDatoJDBCCadena" String,		"tipoDatoLenguaje"	String,			"esNulable"			Boolean,
		// "comentario"			String,		"secuenciaPK"		Integer
		
		// Lista Completa de campos ordenados por secuenciaPK y por nombre:
		List listaCampos = new ArrayList();
		// Lista de campos sin aquellos que pertenecen a la clave primaria ordenados por secuenciaPK y por nombre:
		List listaCamposSinPK = new ArrayList();
		// Lista de campos de la clave primaria ordenados por secuenciaPK y por nombre: 
		List listaCamposPK = new ArrayList();
		
		//Se llenan la lista:
		Iterator itMapCamposDeLaTabla = mapCamposDeLaTabla.entrySet().iterator();		
		while(itMapCamposDeLaTabla.hasNext()) {
			Map.Entry oMapEntryCampos = (Map.Entry) itMapCamposDeLaTabla.next();
			//String nombreCampo = (String)oMapEntryCampos.getKey();
			Map oMapCampo = (Map)oMapEntryCampos.getValue();
			listaCampos.add(oMapCampo);
			
			if(((Integer)oMapCampo.get("secuenciaPK"))==null) {
				listaCamposSinPK.add(oMapCampo);
			}
			else {
				listaCamposPK.add(oMapCampo);
			}
		}
		// Ordenar la listas de campos, campos sin PK y camposPK por secuenciaPK y por nombre:
		new OrdenarListaDeObjetos().ordenarPorPropiedades(listaCampos,"secuenciaPK","nombre");
		new OrdenarListaDeObjetos().ordenarPorPropiedades(listaCamposSinPK,"secuenciaPK","nombre");
		new OrdenarListaDeObjetos().ordenarPorPropiedades(listaCamposPK,"secuenciaPK","nombre");
		
		StringBuffer cadena = new StringBuffer();
		
	    cadena.append("<%@include file=\"/vistaIncludes/preheadComun.inc\" %>"+"\n");
	    cadena.append("<f:view>"+"\n");
	    cadena.append("<html>"+"\n");
	    cadena.append("<head>"+"\n");
	    cadena.append("<%@include file=\"/vistaIncludes/headComun.inc\" %>"+"\n");
	    cadena.append("<script type=\"text/javascript\" src=\"<%=request.getContextPath()%>/vistas/"+nombreEnBeanTabla.substring(0,1).toLowerCase()+nombreEnBeanTabla.substring(1)+"Edicion.js"+"\"></script>"+"\n");
	    cadena.append("</head>"+"\n");
	    cadena.append("<body onload=\"onLoadComun();\">"+"\n");
	    cadena.append("<h:form id=\"form1\">"+"\n");
	    cadena.append("<%@include file=\"/vistaIncludes/menu.jsp\" %>"+"\n");
	    cadena.append("\n");
	    
	    cadena.append("    <h:outputText id=\"mensaje\" value=\"#{control"+nombreEnBeanTabla.substring(0,1).toUpperCase()+nombreEnBeanTabla.substring(1).toLowerCase()+".mensaje}\"/>"+"\n");
	    cadena.append("\n");
	    
	    cadena.append("    <table>"+"\n");
    	for (int i=0; i<listaCamposPK.size(); i++) {
    		String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
    		
    		cadena.append("        <tr>"+"\n");
    		cadena.append("            <td><h:outputText value=\""+nombreEnBean.substring(0,1).toUpperCase()+nombreEnBean.substring(1).toLowerCase()+"\"/></td>"+"\n");
    		cadena.append("            <td><h:outputText id=\""+nombreEnBean+"\" value=\"#{control"+nombreEnBeanTabla.substring(0,1).toUpperCase()+nombreEnBeanTabla.substring(1)+"Edicion.o"+nombreEnBeanTabla.substring(0,1).toUpperCase()+nombreEnBeanTabla.substring(1)+"."+nombreEnBean+"}\" /></td>"+"\n");
    		cadena.append("        </tr>"+"\n");
    	}
    	
    	for (int i=0; i<listaCamposSinPK.size(); i++) {
    		String nombreEnBean = (String)((Map)listaCamposSinPK.get(i)).get("nombreEnBean");
    		
    		cadena.append("        <tr>"+"\n");
    		cadena.append("            <td><h:outputText value=\""+nombreEnBean.substring(0,1).toUpperCase()+nombreEnBean.substring(1).toLowerCase()+"\"/></td>"+"\n");
    		cadena.append("            <td><h:inputText id=\""+nombreEnBean+"\" value=\"#{control"+nombreEnBeanTabla.substring(0,1).toUpperCase()+nombreEnBeanTabla.substring(1)+"Edicion.o"+nombreEnBeanTabla.substring(0,1).toUpperCase()+nombreEnBeanTabla.substring(1)+"."+nombreEnBean+"}\" /></td>"+"\n");
    		cadena.append("        </tr>"+"\n");
    	}
    	cadena.append("    </table>"+"\n");	    	    
	    cadena.append("\n");
	    
	    cadena.append("    <br clear=\"left\"/>"+"\n");
	    cadena.append("\n");
	    
	    cadena.append("    <a4j:commandButton id=\"guardar\" value=\"Guardar\" actionListener=\"#{control"+nombreEnBeanTabla.substring(0,1).toUpperCase()+nombreEnBeanTabla.substring(1)+"Edicion.guardarClic}\""+"\n");
	    cadena.append("        onclick=\"if(!validacionGuardar()) return false; document.getElementById('form1:guardar').disable=true;\""+"\n");
	    cadena.append("        oncomplete=\"document.getElementById('form1:guardar').disable=false\""+"\n");
	    cadena.append("        reRender=\"mensaje,guardar\""+"\n");
	    cadena.append("    />"+"\n");
	    cadena.append("    &nbsp;"+"\n");
	    
	    cadena.append("    <a4j:commandButton  id=\"eliminar\" value=\"Eliminar\" actionListener=\"#{control"+nombreEnBeanTabla.substring(0,1).toUpperCase()+nombreEnBeanTabla.substring(1)+"Edicion.eliminarClic}\""+"\n");
	    cadena.append("        onclick=\"document.getElementById('form1:eliminar').disable=true\""+"\n");
	    cadena.append("        oncomplete=\"document.getElementById('form1:eliminar').disable=false\""+"\n");
	    cadena.append("        reRender=\"mensaje,eliminar");
    	for (int i=0; i<listaCamposPK.size(); i++) {
    		String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
    		cadena.append(","+nombreEnBean);
    	}
    	for (int i=0; i<listaCamposSinPK.size(); i++) {
    		String nombreEnBean = (String)((Map)listaCamposSinPK.get(i)).get("nombreEnBean");
    		cadena.append(","+nombreEnBean);
    	}    	
    	cadena.append("\""+"\n");
	    
	    cadena.append("    />"+"\n");	
	    cadena.append("    &nbsp;"+"\n");
		
	    cadena.append("    <a4j:commandButton  id=\"nuevo\" value=\"Nuevo\" actionListener=\"#{control"+nombreEnBeanTabla.substring(0,1).toUpperCase()+nombreEnBeanTabla.substring(1)+"Edicion.nuevoClic}"+"\n");
	    cadena.append("        onclick=\"document.getElementById('form1:nuevo').disable=true\""+"\n");
	    cadena.append("        oncomplete=\"document.getElementById('form1:nuevo').disable=false\""+"\n");
	    cadena.append("        reRender=\"mensaje,eliminar");
    	for (int i=0; i<listaCamposPK.size(); i++) {
    		String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
    		cadena.append(","+nombreEnBean);
    	}
    	for (int i=0; i<listaCamposSinPK.size(); i++) {
    		String nombreEnBean = (String)((Map)listaCamposSinPK.get(i)).get("nombreEnBean");
    		cadena.append(","+nombreEnBean);
    	}    	
    	cadena.append("\""+"\n");
	    cadena.append("    />"+"\n");
	    cadena.append("    &nbsp;"+"\n");
	    
	    cadena.append("    <h:commandButton id=\"volver\" value=\"Volver\" action=\"#{control"+nombreEnBeanTabla.substring(0,1).toUpperCase()+nombreEnBeanTabla.substring(1)+"Edicion.volverClic}\" />"+"\n");
	    cadena.append("\n");
	    
	    cadena.append("</h:form>"+"\n");
	    cadena.append("<%@include file=\"/vistaIncludes/footerComun.inc\" %>"+"\n");
	    cadena.append("</html>"+"\n");
	    cadena.append("</f:view>"+"\n");

	    
		return cadena;
	}
	
}
