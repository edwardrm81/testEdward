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
public class FormatearControladorEdicionEnJSF {
	
	private String nombreEnBeanTabla;
	private Map mapCamposDeLaTabla;
	private String paqueteControladores;
	private String paqueteBeans;
	private String paqueteFachada;
	
	public FormatearControladorEdicionEnJSF(String nombreEnBeanTabla,Map mapCamposDeLaTabla,String paqueteControladores,String paqueteBeans,String paqueteFachada) {
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
	    cadena.append("import javax.faces.event.ActionEvent;"+"\n");
	    cadena.append("import javax.faces.model.SelectItem;"+"\n");
	    cadena.append("\n");
	    cadena.append("import com.edw.gen.apigen.ExcepcionNegocio;"+"\n");
	    cadena.append("import "+paqueteBeans+"."+nombreEnBeanTabla+";"+"\n");
	    cadena.append("import "+paqueteFachada+".Fachada"+nombreEnBeanTabla+";"+"\n");
	    cadena.append("\n");
	    cadena.append("/* @author Edward */"+"\n");
	    cadena.append("\n");
	    cadena.append("public class Control"+nombreEnBeanTabla+"Edicion extends ContextBean {"+"\n");
	    cadena.append("\n");
	    cadena.append("    private "+nombreEnBeanTabla+" o"+nombreEnBeanTabla+";"+"\n");
	    cadena.append("    private String mensaje;"+"\n");
	    cadena.append("    private List selectListaXX = new ArrayList();"+"\n");
	    cadena.append("    private Boolean esNuevo = true;"+"\n");
	    cadena.append("\n");
	    cadena.append("    public Control"+nombreEnBeanTabla+"Edicion() {"+"\n");
	    cadena.append("        try{"+"\n");
	    cadena.append("            super.limpiarBeansDeSesion(\"control"+nombreEnBeanTabla+"Edicion\");"+"\n");
	    cadena.append("            //if(super.getHttpSession().getAttribute(\"usuarioSession\")==null) throw new ExcepcionNegocio(\"No se ha logueado en el sistema\");"+"\n");
	    cadena.append("\n");
	    cadena.append("            o"+nombreEnBeanTabla+" = new "+nombreEnBeanTabla+"();"+"\n");
	    cadena.append("\n");
	    
	    for (int i=0; i<listaCamposPK.size(); i++) {
			String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
			String tipoDatoLenguaje = (String)((Map)listaCamposPK.get(i)).get("tipoDatoLenguaje");
			cadena.append("            "+tipoDatoLenguaje+" "+nombreEnBean+"Param = super.getHttpServletRequest().getAttribute(\""+nombreEnBeanTabla.substring(0,1).toLowerCase()+nombreEnBeanTabla.substring(1)+"Param\") != null? "+
					"(("+nombreEnBeanTabla+")"+"super.getHttpServletRequest().getAttribute(\""+nombreEnBeanTabla.substring(0,1).toLowerCase()+nombreEnBeanTabla.substring(1)+"Param\"))."+nombreEnBean+" : "+
					"null/*"+new ConversorDeClase().getCastObjeto(tipoDatoLenguaje, "super.getHttpServletRequest().getParameter(\""+nombreEnBean+"Param\")")+"*/"+";"+
			"\n");
		}
	    cadena.append("\n");
	    for (int i=0; i<listaCamposPK.size(); i++) {
			String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
			cadena.append("            if("+nombreEnBean+"Param == null) throw new ExcepcionNegocio(\"No ha encontrado el valor de "+nombreEnBean+"\");"+"\n");
	    }
	    cadena.append("\n");
	    cadena.append("            Map oMapInfoEditar"+nombreEnBeanTabla+" = Fachada"+nombreEnBeanTabla+".infoEditar"+nombreEnBeanTabla+"(");
	    for (int i=0; i<listaCamposPK.size(); i++) {
			String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
			cadena.append(nombreEnBean+"Param,");
		}
	    if(listaCamposPK.size()>0) cadena.delete(cadena.length()-1, cadena.length());
	    cadena.append(");"+"\n");
	    cadena.append("            o"+nombreEnBeanTabla+" = ("+nombreEnBeanTabla+")oMapInfoEditar"+nombreEnBeanTabla+".get(\""+nombreEnBeanTabla.substring(0,1).toLowerCase()+nombreEnBeanTabla.substring(1)+"\");"+"\n");
	    cadena.append("\n");
	    cadena.append("            Map oMapXX = Fachada"+nombreEnBeanTabla+".infoListar"+nombreEnBeanTabla+"(null);"+"\n");
	    cadena.append("            List listaMapXX = (List)oMapXX.get(\"listaXX\");"+"\n");
	    cadena.append("            for (int i = 0; i < listaMapXX.size(); i++) {"+"\n");
	    cadena.append("                //XX oXX = (XX) listaMapXX.get(i);"+"\n");
	    cadena.append("                //selectListaXX.add(new SelectItem(oXX.codigo, oXX.descripcion));"+"\n");
	    cadena.append("            }"+"\n");
	    cadena.append("        }"+"\n");
	    cadena.append("        catch(Exception e){"+"\n");
	    cadena.append("            mensaje = e.getMessage();"+"\n");
	    cadena.append("            e.printStackTrace();"+"\n");
	    cadena.append("        }"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    cadena.append("\n");
	    
	    cadena.append("    public void guardarClic(ActionEvent ev) {"+"\n");
	    cadena.append("        try{"+"\n");
	    cadena.append("            mensaje = \"\";"+"\n");
	    cadena.append("            if(esNuevo) {"+"\n");
	    cadena.append("                Map oMap"+nombreEnBeanTabla+" = Fachada"+nombreEnBeanTabla+".insertar"+nombreEnBeanTabla+"AInc(o"+nombreEnBeanTabla+");"+"\n");
	    cadena.append("                o"+nombreEnBeanTabla+" = ("+nombreEnBeanTabla+")oMap"+nombreEnBeanTabla+".get(\""+nombreEnBeanTabla.substring(0,1).toLowerCase()+nombreEnBeanTabla.substring(1)+"\");"+"\n");
	    cadena.append("                esNuevo = false;"+"\n");
	    cadena.append("            }"+"\n");
	    cadena.append("            else {"+"\n");
	    cadena.append("                Fachada"+nombreEnBeanTabla+".actualizar"+nombreEnBeanTabla+"(");
	    //for (int i=0; i<listaCamposPK.size(); i++) {
		//	String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
		//	cadena.append("o"+nombreEnBeanTabla+"."+nombreEnBean+",");
		//}
	    //if(listaCamposPK.size()>0) cadena.delete(cadena.length()-1, cadena.length());
	    //cadena.append(","+" o"+nombreEnBeanTabla+");"+"\n");	    
	    cadena.append("o"+nombreEnBeanTabla+");"+"\n");
	    cadena.append("            }"+"\n");
	    cadena.append("            mensaje = \"Se guardo exitosamente\";"+"\n");
	    cadena.append("        }"+"\n");
	    cadena.append("        catch(Exception e) {"+"\n");
	    cadena.append("            mensaje = e.getMessage();"+"\n");
	    cadena.append("            e.printStackTrace();"+"\n");
	    cadena.append("        }"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    cadena.append("\n");
	    
	    cadena.append("    public void eliminarClic(ActionEvent ev) {"+"\n");
	    cadena.append("        try{"+"\n");
	    cadena.append("            mensaje = \"\";"+"\n");
	    cadena.append("            Fachada"+nombreEnBeanTabla+".eliminar"+nombreEnBeanTabla+"(");
	    for (int i=0; i<listaCamposPK.size(); i++) {
			String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
			cadena.append("o"+nombreEnBeanTabla+"."+nombreEnBean+",");
		}
	    if(listaCamposPK.size()>0) cadena.delete(cadena.length()-1, cadena.length());
	    cadena.append(");"+"\n");
	    cadena.append("            o"+nombreEnBeanTabla+" = new "+nombreEnBeanTabla+"();"+"\n");
	    cadena.append("            esNuevo = true;"+"\n");
	    cadena.append("            mensaje = \"Se elimino exitosamente\";"+"\n");
	    cadena.append("        }"+"\n");
	    cadena.append("        catch(Exception e) {"+"\n");
	    cadena.append("            mensaje = e.getMessage();"+"\n");
	    cadena.append("            e.printStackTrace();"+"\n");
	    cadena.append("        }"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    cadena.append("\n");
	    
	    cadena.append("    public void nuevoClic(ActionEvent ev) {"+"\n");
	    cadena.append("        try{"+"\n");
	    cadena.append("            o"+nombreEnBeanTabla+" = new "+nombreEnBeanTabla+"();"+"\n");
	    cadena.append("            esNuevo = true;"+"\n");
	    cadena.append("            mensaje = \"\";"+"\n");
	    cadena.append("        }"+"\n");
	    cadena.append("        catch(Exception e) {"+"\n");
	    cadena.append("            mensaje = e.getMessage();"+"\n");
	    cadena.append("            e.printStackTrace();"+"\n");
	    cadena.append("        }"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    cadena.append("\n");
	    
	    cadena.append("    public String volverClic() {"+"\n");
	    cadena.append("        return \"volver\";"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    cadena.append("\n");

	    cadena.append("    public String getMensaje() {"+"\n");
	    cadena.append("        return mensaje;"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    
	    cadena.append("    public void setMensaje(String mensaje) {"+"\n");
	    cadena.append("        this.mensaje = mensaje;"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");	    
	    
	    cadena.append("    public "+nombreEnBeanTabla+" geto"+nombreEnBeanTabla+"() {"+"\n");
	    cadena.append("        return o"+nombreEnBeanTabla+";"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    
	    cadena.append("    public void seto"+nombreEnBeanTabla+"("+nombreEnBeanTabla+" o"+nombreEnBeanTabla+") {"+"\n");
	    cadena.append("        this.o"+nombreEnBeanTabla+" = o"+nombreEnBeanTabla+";"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    
	    cadena.append("    public Boolean getEsNuevo() {"+"\n");
	    cadena.append("        return esNuevo;"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    
	    cadena.append("    public void setEsNuevo(Boolean esNuevo) {"+"\n");
	    cadena.append("        this.esNuevo = esNuevo;"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    
	    cadena.append("\n");
	    cadena.append("}"+"\n");
		
		return cadena;
	}
	
}
