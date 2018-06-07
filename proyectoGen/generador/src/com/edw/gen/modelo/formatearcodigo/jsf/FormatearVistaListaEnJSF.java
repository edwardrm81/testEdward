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
public class FormatearVistaListaEnJSF {
	
	private String nombreEnBeanTabla;
	private Map mapCamposDeLaTabla;
	
	public FormatearVistaListaEnJSF(String nombreEnBeanTabla,Map mapCamposDeLaTabla) {
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
	    cadena.append("<script type=\"text/javascript\" src=\"<%=request.getContextPath()%>/vistas/"+nombreEnBeanTabla.substring(0,1).toLowerCase()+nombreEnBeanTabla.substring(1)+"Lista.js"+"\"></script>"+"\n");
	    cadena.append("</head>"+"\n");
	    cadena.append("<body onload=\"onLoadComun();\">"+"\n");
	    cadena.append("<h:form id=\"form1\">"+"\n");
	    cadena.append("<%@include file=\"/vistaIncludes/menu.jsp\" %>"+"\n");
	    cadena.append("    <h:commandButton id=\"nuevo\" value=\"Nuevo\" action=\"#{control"+nombreEnBeanTabla+"Lista.nuevoClic}\" />"+"\n");
	    cadena.append("    <h:outputText value=\"#{control"+nombreEnBeanTabla+"Lista.mensaje}\"/>"+"\n");
	    cadena.append("    <br clear=\"left\"/>"+"\n");
	    cadena.append("\n");
	    cadena.append("    <h:panelGroup id=\"tabla\">"+"\n");
	    cadena.append("\n");
	    cadena.append("        <t:dataTable id=\"data\""+"\n");
	    cadena.append("            styleClass=\"scrollerTable\""+"\n");
	    cadena.append("            headerClass=\"standardTable_Header\""+"\n");
	    cadena.append("            footerClass=\"standardTable_Header\""+"\n");    
	    cadena.append("            rowClasses=\"standardTable_Row1,standardTable_Row2\""+"\n");
	    cadena.append("            columnClasses=\"standardTable_Column,standardTable_ColumnCentered,standardTable_Column\""+"\n");
	    cadena.append("            var=\""+nombreEnBeanTabla.substring(0,1).toLowerCase()+nombreEnBeanTabla.substring(1,nombreEnBeanTabla.length())+"\""+"\n");
	    cadena.append("            value=\"#{control"+nombreEnBeanTabla+"Lista.lista"+nombreEnBeanTabla+"}\""+"\n");
	    cadena.append("            preserveDataModel=\"false\""+"\n");
	    cadena.append("            var=\""+nombreEnBeanTabla+"\""+"\n");    
	    cadena.append("            rows=\"5\""+"\n");
	    cadena.append("            border=\"1\" cellpadding=\"1\" cellspacing=\"0\""+"\n");
	    cadena.append("        >"+"\n");

    	cadena.append("            <h:column>"+"\n");
    	cadena.append("                <f:facet name=\"header\"><h:outputText value=\"Editar\" /></f:facet>"+"\n");
    	cadena.append("                <h:commandLink value=\"editar\" action=\"#{control"+nombreEnBeanTabla+"Lista.editarClic}\">"+"\n");
    	for (int i=0; i<listaCamposPK.size(); i++) {
    		String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
    		cadena.append("                    <f:param id=\""+nombreEnBean+"Param\" name=\""+nombreEnBean+"Param\" value=\"#{"+nombreEnBeanTabla+"."+nombreEnBean+"}\" />"+"\n");
    	}
    	cadena.append("                </h:commandLink>"+"\n");
    	cadena.append("            </h:column>"+"\n");
    	
	    for (int i=0; i<listaCamposPK.size(); i++) {
	    	String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
	    	
	    	cadena.append("            <h:column>"+"\n");
	    	cadena.append("                <f:facet name=\"header\"><h:outputText value=\""+nombreEnBean+"\" /></f:facet>"+"\n");
	    	cadena.append("                <h:outputText value=\"#{"+nombreEnBeanTabla.substring(0,1).toLowerCase()+nombreEnBeanTabla.substring(1,nombreEnBeanTabla.length())+"."+nombreEnBean+"}\" />"+"\n");
	    	cadena.append("            </h:column>"+"\n");
		}
	    
	    for (int i=0; i<listaCamposSinPK.size(); i++) {
	    	String nombreEnBean = (String)((Map)listaCamposSinPK.get(i)).get("nombreEnBean");
	    	
	    	cadena.append("            <h:column>"+"\n");
	    	cadena.append("                <f:facet name=\"header\"><h:outputText value=\""+nombreEnBean+"\" /></f:facet>"+"\n");
	    	cadena.append("                <h:outputText value=\"#{"+nombreEnBeanTabla.substring(0,1).toLowerCase()+nombreEnBeanTabla.substring(1,nombreEnBeanTabla.length())+"."+nombreEnBean+"}\" />"+"\n");
	    	cadena.append("            </h:column>"+"\n");
		}
	    cadena.append("\n");
	    
	    cadena.append("        </t:dataTable>"+"\n");
	    cadena.append("\n");
	    
	    cadena.append("        <h:panelGrid columns=\"1\" styleClass=\"scrollerTable2\" columnClasses=\"standardTable_ColumnCentered\" >"+"\n");
	    cadena.append("            <t:dataScroller id=\"scroll_1\""+"\n");
	    cadena.append("                for=\"data\""+"\n");
	    cadena.append("                fastStep=\"2\""+"\n");
	    cadena.append("                pageCountVar=\"pageCount\""+"\n");
	    cadena.append("                pageIndexVar=\"pageIndex\""+"\n");
	    cadena.append("                styleClass=\"scroller\""+"\n");        
	    cadena.append("                paginator=\"true\""+"\n");
	    cadena.append("                paginatorMaxPages=\"100\""+"\n");                
	    cadena.append("                paginatorTableClass=\"paginator\""+"\n");        
	    cadena.append("                immediate=\"true\" "+"\n");        
	    cadena.append("                paginatorColumnStyle=\"font-size:12px\""+"\n");
	    cadena.append("                paginatorActiveColumnStyle=\"font-weight:bold;font-size:18px\""+"\n");
	    cadena.append("            >"+"\n");
	    cadena.append("                <f:facet name=\"first\" >"+"\n");
	    cadena.append("                    <t:graphicImage url=\"/images/arrow-first.gif\" border=\"0\" />"+"\n");
	    cadena.append("                </f:facet>"+"\n");    
	    cadena.append("                <f:facet name=\"last\">"+"\n");        
	    cadena.append("                    <t:graphicImage url=\"/images/arrow-last.gif\" border=\"0\" />"+"\n");    
	    cadena.append("                </f:facet>"+"\n");    
	    cadena.append("                <f:facet name=\"previous\">"+"\n");
	    cadena.append("                    <t:graphicImage url=\"/images/arrow-previous.gif\" border=\"0\" />"+"\n");
	    cadena.append("                </f:facet>"+"\n");    
	    cadena.append("                <f:facet name=\"next\">"+"\n");    
	    cadena.append("                    <t:graphicImage url=\"/images/arrow-next.gif\" border=\"0\" />"+"\n");        
	    cadena.append("                </f:facet>"+"\n");                
	    cadena.append("                <f:facet name=\"fastforward\">"+"\n");    
	    cadena.append("                    <t:graphicImage url=\"/images/arrow-ff.gif\" border=\"0\" />"+"\n");        
	    cadena.append("                </f:facet>"+"\n");    
	    cadena.append("                <f:facet name=\"fastrewind\">"+"\n");    
	    cadena.append("                    <t:graphicImage url=\"/images/arrow-fr.gif\" border=\"0\" />"+"\n");        
	    cadena.append("                </f:facet>"+"\n");
	    cadena.append("            </t:dataScroller>"+"\n");
	    cadena.append("            <t:dataScroller id=\"scroll_2\""+"\n");
	    cadena.append("                for=\"data\""+"\n");        
	    cadena.append("                rowsCountVar=\"rowsCount\""+"\n");    
	    cadena.append("                displayedRowsCountVar=\"displayedRowsCountVar\""+"\n");
	    cadena.append("                firstRowIndexVar=\"firstRowIndex\""+"\n");
	    cadena.append("                lastRowIndexVar=\"lastRowIndex\""+"\n");        
	    cadena.append("                pageCountVar=\"pageCount\""+"\n");        
	    cadena.append("                immediate=\"true\""+"\n");        
	    cadena.append("                pageIndexVar=\"pageIndex\""+"\n");        
	    cadena.append("                pageIndexVar=\"pageIndex\""+"\n");        
	    cadena.append("            >"+"\n");        
	    cadena.append("            </t:dataScroller>"+"\n");        
	    cadena.append("        </h:panelGrid>"+"\n");                            
	    cadena.append("\n");
	    
	    cadena.append("    </h:panelGroup>"+"\n");
	    cadena.append("\n");
	    cadena.append("    <br clear=\"left\"/>"+"\n");
	    cadena.append("\n");
	    cadena.append("</h:form>"+"\n");
	    cadena.append("<%@include file=\"/vistaIncludes/footerComun.inc\" %>"+"\n");
	    cadena.append("</html>"+"\n");
	    cadena.append("</f:view>"+"\n");
		
		return cadena;
	}

}
