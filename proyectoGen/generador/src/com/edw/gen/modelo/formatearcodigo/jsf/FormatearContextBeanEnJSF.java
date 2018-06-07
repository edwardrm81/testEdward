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
public class FormatearContextBeanEnJSF {
	
	private Map mapInfoBaseDatos;
	private String paqueteContextBean;
	
	public FormatearContextBeanEnJSF(Map mapInfoBaseDatos, String paqueteContextBean) {
		this.mapInfoBaseDatos = mapInfoBaseDatos;
		this.paqueteContextBean = paqueteContextBean;
	}
	
	public StringBuffer getFormatoContextBean() throws Exception {
		if(mapInfoBaseDatos==null || mapInfoBaseDatos.size()<1) throw new ExcepcionNegocio("No ha ingresado los información de la Base de Datos");
		if(paqueteContextBean==null || paqueteContextBean.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino del ContextBean");
		
		//Recupera info de Vistas y de Tablas
		Map oMapVistas = (Map)mapInfoBaseDatos.get("vistas");
		Map oMapTablas = (Map)mapInfoBaseDatos.get("tablas");
		
		//Los unifico en un solo Map
		Map oMapTablasVistas = new HashMap();
		oMapTablasVistas.putAll(oMapVistas);
		oMapTablasVistas.putAll(oMapTablas);
		
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
		
	    cadena.append("\n");
	    cadena.append("package "+paqueteContextBean +";\n");
	    cadena.append("\n");
	    cadena.append("import java.util.ArrayList;"+"\n");
	    cadena.append("import java.util.List;"+"\n");
	    cadena.append("\n");
	    cadena.append("import javax.faces.context.ExternalContext;"+"\n");
	    cadena.append("import javax.faces.context.FacesContext;"+"\n");
	    cadena.append("import javax.servlet.http.HttpServletRequest;"+"\n");
	    cadena.append("import javax.servlet.http.HttpSession;"+"\n");
	    cadena.append("\n");
	    cadena.append("/* @author Edward */"+"\n");
	    cadena.append("\n");
	    cadena.append("public class ContextBean {"+"\n");
	    cadena.append("\n");
	    
	    cadena.append("    public ContextBean() {"+"\n");
	    cadena.append("\n");
	    cadena.append("    }");
	    cadena.append("\n");
	    
	    cadena.append("    public FacesContext getFacesContext() {"+"\n");
	    cadena.append("        return FacesContext.getCurrentInstance();"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    
	    cadena.append("    public ExternalContext getExternalContext() {"+"\n");
	    cadena.append("        return this.getFacesContext().getExternalContext();"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    
	    cadena.append("    public HttpServletRequest getHttpServletRequest() {"+"\n");
	    cadena.append("        return (HttpServletRequest)this.getExternalContext().getRequest();"+"\n");
	    cadena.append("    }"+"\n");	    

	    cadena.append("    public HttpSession getHttpSession() {"+"\n");
	    cadena.append("        return this.getHttpServletRequest().getSession();"+"\n");
	    cadena.append("    }"+"\n");

	    cadena.append("    public void limpiarBeansDeSesion(String nombreBean1) {"+"\n");
	    cadena.append("        List lista = new ArrayList();"+"\n");
	    cadena.append("        lista.add(nombreBean1);"+"\n");
	    cadena.append("        this.limpiarBeansDeSesion(lista);"+"\n");
	    cadena.append("    }"+"\n");

	    cadena.append("    public void limpiarBeansDeSesion(String nombreBean1,String nombreBean2) {"+"\n");
	    cadena.append("        List lista = new ArrayList();"+"\n");
	    cadena.append("        lista.add(nombreBean1);"+"\n");
	    cadena.append("        lista.add(nombreBean2);"+"\n");
	    cadena.append("        this.limpiarBeansDeSesion(lista);"+"\n");
	    cadena.append("    }"+"\n");

	    cadena.append("    public void limpiarBeansDeSesion(String nombreBean1,String nombreBean2,String nombreBean3) {"+"\n");
	    cadena.append("        List lista = new ArrayList();"+"\n");
	    cadena.append("        lista.add(nombreBean1);"+"\n");
	    cadena.append("        lista.add(nombreBean2);"+"\n");
	    cadena.append("        lista.add(nombreBean3);"+"\n");
	    cadena.append("        this.limpiarBeansDeSesion(lista);"+"\n");
	    cadena.append("    }"+"\n");
	    
	    cadena.append("    public void limpiarBeansDeSesion(List lista) {"+"\n");
	    cadena.append("        ExternalContext oExternalContext = this.getExternalContext();"+"\n");
	    cadena.append("\n");
		for (int i=0; i<listaMapsTablasYVistas.size(); i++) {
			Map oMapTablaVista = (Map) listaMapsTablasYVistas.get(i);
			
			if(!((String)oMapTablaVista.get("tipoTabla")).equalsIgnoreCase("vista")) {
				cadena.append("        if(!lista.contains(\""+"control"+(String)oMapTablaVista.get("nombreEnBean")+"Edicion"+"\")) oExternalContext.getSessionMap().remove(\""+"control"+(String)oMapTablaVista.get("nombreEnBean")+"Edicion"+"\");"+"\n");
			}
			cadena.append("        if(!lista.contains(\""+"control"+(String)oMapTablaVista.get("nombreEnBean")+"Lista"+"\")) oExternalContext.getSessionMap().remove(\""+"control"+(String)oMapTablaVista.get("nombreEnBean")+"Lista"+"\");"+"\n");
		}
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    
	    cadena.append("\n");
	    cadena.append("}"+"\n");
		
		return cadena;
	}

}
