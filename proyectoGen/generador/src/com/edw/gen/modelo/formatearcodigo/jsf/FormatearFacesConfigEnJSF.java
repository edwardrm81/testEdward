/**
 * 
 */
package com.edw.gen.modelo.formatearcodigo.jsf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.edw.gen.apigen.ExcepcionNegocio;
import com.edw.gen.modelo.util.OrdenarListaDeObjetos;

/**
 * @author edward
 *
 */
public class FormatearFacesConfigEnJSF {
	
	private Map mapInfoBaseDatos;
	private String paqueteControladores;
	
	public FormatearFacesConfigEnJSF(Map mapInfoBaseDatos, String paqueteControladores) {
		this.mapInfoBaseDatos = mapInfoBaseDatos;
		this.paqueteControladores = paqueteControladores;
	}
	
	public StringBuffer getFormatoFacesConfig() throws Exception {
		if(mapInfoBaseDatos==null || mapInfoBaseDatos.size()<1) throw new ExcepcionNegocio("No ha ingresado los información de la Base de Datos");
		if(paqueteControladores==null || paqueteControladores.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete de los controladores");
		
		//Recupera info de Vistas y de Tablas
		Map oMapVistas = (Map)mapInfoBaseDatos.get("vistas");
		Map oMapTablas = (Map)mapInfoBaseDatos.get("tablas");
		
		// Ingreso cada uno de los Maps en una lista
		List listaMapsTablasYVistas= new ArrayList();

		Iterator itVistas = oMapVistas.entrySet().iterator();
		while(itVistas.hasNext()) {
			Map.Entry oMapEntryVista = (Map.Entry) itVistas.next();
			Map oMapVista = (Map)oMapEntryVista.getValue();
			oMapVista.put("tipoTabla", "vista");
			listaMapsTablasYVistas.add(oMapVista);
		}
		
		Iterator itTablas = oMapTablas.entrySet().iterator();
		while(itTablas.hasNext()) {
			Map.Entry oMapEntryTabla = (Map.Entry) itTablas.next();
			Map oMapTabla = (Map)oMapEntryTabla.getValue();
			oMapTabla.put("tipoTabla", "tabla");
			listaMapsTablasYVistas.add(oMapTabla);
		}

		//Ordeno la lista en orden alfabetico
		new OrdenarListaDeObjetos().ordenarPorPropiedades(listaMapsTablasYVistas,"nombreEnBean");
		
		StringBuffer cadena = new StringBuffer();
		
	    cadena.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+"\n");
	    cadena.append("\n");
	    cadena.append("<faces-config "+"\n");
	    cadena.append("    xmlns=\"http://java.sun.com/xml/ns/javaee\""+"\n");
	    cadena.append("    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""+"\n");
	    cadena.append("    xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-facesconfig_1_2.xsd\""+"\n");
	    cadena.append("    version=\"1.2\""+"\n");
	    cadena.append(">"+"\n");	    
	    cadena.append("\n");
	    cadena.append("\n");
	    
		for (int i=0; i<listaMapsTablasYVistas.size(); i++) {
			Map oMapTablaVista = (Map) listaMapsTablasYVistas.get(i);
			String nombreEnBean = (String)oMapTablaVista.get("nombreEnBean");
			String tipoTabla = (String)oMapTablaVista.get("tipoTabla");
			
			if(!tipoTabla.equalsIgnoreCase("vista")) {
				
				cadena.append("    <!--"+nombreEnBean+"Edicion"+"-->"+"\n");
				cadena.append("    <managed-bean>"+"\n");
				cadena.append("        <managed-bean-name>"+"control"+nombreEnBean+"Edicion"+"</managed-bean-name>"+"\n");
				cadena.append("        <managed-bean-class>"+paqueteControladores+".Control"+nombreEnBean+"Edicion"+"</managed-bean-class>"+"\n");
				cadena.append("        <managed-bean-scope>session</managed-bean-scope>"+"\n");
				cadena.append("    </managed-bean>"+"\n");
				cadena.append("\n");
				
				cadena.append("    <navigation-rule>"+"\n");
				cadena.append("        <from-view-id>"+"/vistas/"+nombreEnBean.substring(0,1).toLowerCase()+nombreEnBean.substring(1)+"Edicion.jsp"+"</from-view-id>"+"\n");
				cadena.append("        <navigation-case>"+"\n");
				cadena.append("            <from-outcome>volver</from-outcome>"+"\n");
				cadena.append("            <to-view-id>"+"/vistas/"+nombreEnBean.substring(0,1).toLowerCase()+nombreEnBean.substring(1)+"Lista.jsp"+"</to-view-id>"+"\n");
				cadena.append("        </navigation-case>"+"\n");
				cadena.append("    </navigation-rule>"+"\n");
				cadena.append("\n");
				cadena.append("\n");
			}
			
			cadena.append("    <!--"+nombreEnBean+"Lista"+"-->"+"\n");
			cadena.append("    <managed-bean>"+"\n");
			cadena.append("        <managed-bean-name>"+"control"+nombreEnBean+"Lista"+"</managed-bean-name>"+"\n");
			cadena.append("        <managed-bean-class>"+paqueteControladores+".Control"+nombreEnBean+"Lista"+"</managed-bean-class>"+"\n");
			cadena.append("        <managed-bean-scope>session</managed-bean-scope>"+"\n");
			cadena.append("    </managed-bean>"+"\n");
			cadena.append("\n");
			
			cadena.append("    <navigation-rule>"+"\n");
			cadena.append("        <from-view-id>"+"/vistas/"+nombreEnBean.substring(0,1).toLowerCase()+nombreEnBean.substring(1)+"Lista.jsp"+"</from-view-id>"+"\n");
			cadena.append("        <navigation-case>"+"\n");
			cadena.append("            <from-outcome>editar</from-outcome>"+"\n");
			cadena.append("            <to-view-id>"+"/vistas/"+nombreEnBean.substring(0,1).toLowerCase()+nombreEnBean.substring(1)+"Edicion.jsp"+"</to-view-id>"+"\n");
			cadena.append("        </navigation-case>"+"\n");
			cadena.append("    </navigation-rule>"+"\n");
		    cadena.append("\n");
		    cadena.append("\n");			
		}
		

	    
	    cadena.append("</faces-config>"+"\n");
	    		
		return cadena;
	}

}
