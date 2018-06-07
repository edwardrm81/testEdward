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
public class FormatearControladorListaEnJSF {
	
	private String nombreEnBeanTabla;
	private Map mapCamposDeLaTabla;
	private String paqueteControladores;
	private String paqueteBeans;
	private String paqueteFachada;
	
	public FormatearControladorListaEnJSF(String nombreEnBeanTabla,Map mapCamposDeLaTabla,String paqueteControladores,String paqueteBeans,String paqueteFachada) {
		this.nombreEnBeanTabla = nombreEnBeanTabla;
		this.mapCamposDeLaTabla = mapCamposDeLaTabla;
		this.paqueteControladores = paqueteControladores;
		this.paqueteBeans = paqueteBeans;
		this.paqueteFachada = paqueteFachada;
	}

	public StringBuffer getFormatoControlador() throws Exception {
		if(paqueteControladores==null || paqueteControladores.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino");
		if(paqueteBeans==null || paqueteBeans.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino de los Beans");
		if(paqueteFachada==null || paqueteFachada.trim().length()<1) throw new ExcepcionNegocio("No ha ingresado el paquete destino de la Fachada");
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
		
	    cadena.append("\n");
	    cadena.append("package "+paqueteControladores +";\n");
	    cadena.append("\n");
	    cadena.append("import java.util.ArrayList;"+"\n");
	    cadena.append("import java.util.List;"+"\n");
	    cadena.append("import java.util.Map;"+"\n");
	    cadena.append("\n");
	    cadena.append("import javax.faces.component.UIParameter;"+"\n");
	    cadena.append("import javax.faces.event.ActionEvent;"+"\n");
	    cadena.append("\n");
	    cadena.append("import com.edw.gen.apigen.ExcepcionNegocio;"+"\n");
	    cadena.append("import "+paqueteBeans+"."+nombreEnBeanTabla+";"+"\n");
	    cadena.append("import "+paqueteFachada+".Fachada"+nombreEnBeanTabla+";"+"\n");
	    cadena.append("\n");
	    cadena.append("/* @author Edward */"+"\n");
	    cadena.append("\n");
	    cadena.append("public class Control"+nombreEnBeanTabla+"Lista extends ContextBean {"+"\n");
	    cadena.append("\n");
	    cadena.append("    private String mensaje;"+"\n");
	    cadena.append("    private List lista"+nombreEnBeanTabla+"CVO = new ArrayList();"+"\n");
	    cadena.append("\n");
	    cadena.append("    public Control"+nombreEnBeanTabla+"Lista() {"+"\n");
	    cadena.append("        try{"+"\n");
	    cadena.append("            super.limpiarBeansDeSesion(\"control"+nombreEnBeanTabla+"Lista\");"+"\n");
	    cadena.append("            //if(super.getHttpSession().getAttribute(\"usuarioSession\")==null) throw new ExcepcionNegocio(\"No se ha logueado en el sistema\");"+"\n");
	    cadena.append("\n");
	    cadena.append("            Map oMapLista"+nombreEnBeanTabla+" = Fachada"+nombreEnBeanTabla+".infoListar"+nombreEnBeanTabla+"(null);"+"\n");
	    cadena.append("            lista"+nombreEnBeanTabla+"CVO = (List)oMapLista"+nombreEnBeanTabla+".get(\"lista"+nombreEnBeanTabla+"\");"+"\n");
	    cadena.append("        }"+"\n");
	    cadena.append("        catch(Exception e){"+"\n");
	    cadena.append("            mensaje = e.getMessage();"+"\n");
	    cadena.append("            e.printStackTrace();"+"\n");
	    cadena.append("        }"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");	    
	    
	    cadena.append("    public String nuevoClic() {"+"\n");
	    cadena.append("        return \"nuevo\";"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    
	    cadena.append("    public String editarClic(ActionEvent ev) {"+"\n");
	    cadena.append("        try{"+"\n");
	    cadena.append("            "+nombreEnBeanTabla+" "+nombreEnBeanTabla.substring(0,1).toLowerCase()+nombreEnBeanTabla.substring(1)+"Param = ("+nombreEnBeanTabla+")((UIParameter)ev.getComponent().findComponent(\""+nombreEnBeanTabla.substring(0,1).toLowerCase()+nombreEnBeanTabla.substring(1)+"Param\")).getValue();"+"\n");	    
	    //cadena.append("            "+nombreEnBeanTabla+" "+nombreEnBeanTabla.substring(0,1).toLowerCase()+nombreEnBeanTabla.substring(1)+"Param = ("+nombreEnBeanTabla+")super.getExternalContext().getRequestParameterMap().get(\""+nombreEnBeanTabla.substring(0,1).toLowerCase()+nombreEnBeanTabla.substring(1)+"Param\");"+"\n");
	    cadena.append("            super.getHttpServletRequest().setAttribute(\""+nombreEnBeanTabla.substring(0,1).toLowerCase()+nombreEnBeanTabla.substring(1)+"Param\","+nombreEnBeanTabla.substring(0,1).toLowerCase()+nombreEnBeanTabla.substring(1)+"Param);"+"\n");
	    
	    
	    for (int i=0; i<listaCamposPK.size(); i++) {
			String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
			String tipoDatoLenguaje = (String)((Map)listaCamposPK.get(i)).get("tipoDatoLenguaje");
			cadena.append("            //"+tipoDatoLenguaje+" "+nombreEnBean+"Param = " +
					new ConversorDeClase().getCastObjeto(tipoDatoLenguaje, "super.getExternalContext().getRequestParameterMap().get(\""+nombreEnBean+"Param\")")+
					";"+
			"\n");
		}
	    
	    for (int i=0; i<listaCamposPK.size(); i++) {
			String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
			cadena.append("            //super.getHttpServletRequest().setAttribute(\""+nombreEnBean+"Param\","+nombreEnBean+"Param);"+"\n");
		}
	    
	    cadena.append("            return \"editar\";"+"\n");
	    cadena.append("        }"+"\n");
	    cadena.append("        catch(Exception e) {"+"\n");
	    cadena.append("            mensaje = e.getMessage();"+"\n");
	    cadena.append("            e.printStackTrace();"+"\n");
	    cadena.append("        }"+"\n");
	    cadena.append("        return \"\";"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    
	    cadena.append("    public List getLista"+nombreEnBeanTabla+"CVO() {"+"\n");
	    cadena.append("        return lista"+nombreEnBeanTabla+"CVO;"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    
	    cadena.append("    public void setLista"+nombreEnBeanTabla+"CVO(List lista"+nombreEnBeanTabla+"CVO) {"+"\n");
	    cadena.append("        this.lista"+nombreEnBeanTabla+"CVO = lista"+nombreEnBeanTabla+"CVO;"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");

	    cadena.append("    public String getMensaje() {"+"\n");
	    cadena.append("        return mensaje;"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    
	    cadena.append("    public void setMensaje(String mensaje) {"+"\n");
	    cadena.append("        this.mensaje = mensaje;"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    
	    cadena.append("\n");
	    cadena.append("}"+"\n");
		
		return cadena;
	}

}
