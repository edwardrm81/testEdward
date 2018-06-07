<%@include file="/jspIncludes/preheadComun.jsp" %>
<f:view>
<html>
<head>
<%@include file="/jspIncludes/headComun.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/jsp/usuarioLista.js"></script>


<script language="javascript">
	var conta=0;
	
	function consecutivo() {
		return conta++;
	}
	
</script>

<script language="javascript">
	
	function replace(object) {
		return(object.id);
	}
	
</script>

<script language="javascript">
	var contador = 0;
	var pagina = document.getElementById('form1:pagina').value;
	var registrosXPagina = document.getElementById('form1:registrosXPagina').value;
	
	function returnNumId() {
		return pagina * registrosXPagina + conta++;
	}
</script>

</head>
<body onload="onLoadComun();">
<h:form id="form1">
<%@include file="/jspIncludes/menu.jsp" %>	
	<h:commandButton id="nuevo" value="Nuevo" action="#{controlUsuarioLista.nuevoClic}" />
	<h:outputText value="#{controlUsuarioLista.mensaje}"/>
	<br clear="left"/>	
	
    <h:panelGroup id="tabla">
    	
        <t:dataTable id="data"
                styleClass="scrollerTable"
                headerClass="standardTable_Header"
                footerClass="standardTable_Header"
                rowClasses="standardTable_Row1,standardTable_Row2"
                columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
                var="usuarioVista"
                value="#{controlUsuarioLista.listaUsuariosVista}"
                preserveDataModel="false"
                rows="5"
                border="1" cellpadding="1" cellspacing="0"
           >
           <h:column>
               <f:facet name="header"><h:outputText value="Editar" /></f:facet>
               <h:commandLink value="editar" action="#{controlUsuarioLista.editarClic}">
               		<f:param id="codigoParam" name="codigoParam" value="#{usuarioVista.codigo}" />
               </h:commandLink>
           </h:column>
           <h:column>
           		<h:commandButton id="botonX" value="#{usuarioVista.codigo}" onclick="alert(replace(this));">
           		</h:commandButton>
           </h:column>
           <h:column>
           		<script language="javascript">
           		
           		document.write("<br>"+document.getElementById('form1:data:'+consecutivo()+':botonX').value);
           		/*
           			document.write("<br>"+document.getElementById('form1:data:'+consecutivo()+':botonX').value);
           			document.write(consecutivo());
           		*/
           		</script>
           </h:column>
           <h:column>
               <f:facet name="header"><h:outputText value="codigo" /></f:facet>
               <h:outputText value="#{usuarioVista.codigo}" />
           </h:column>
           <h:column>
               <f:facet name="header"><h:outputText value="usuario" /></f:facet>
               <h:outputText value="#{usuarioVista.usuario}" />
           </h:column>           
           <h:column>
               <f:facet name="header"><h:outputText value="clave" /></f:facet>
               <h:outputText value="#{usuarioVista.clave}" />
           </h:column>
           <h:column>
               <f:facet name="header"><h:outputText value="nombre" /></f:facet>
               <h:outputText value="#{usuarioVista.nombre}" />
           </h:column>
           <h:column>
               <f:facet name="header"><h:outputText value="habilitado" /></f:facet>
               <h:outputText value="#{usuarioVista.estado? 'si':'no'}" />
           </h:column>
           <h:column>
               <f:facet name="header"><h:outputText value="codigo grupo" /></f:facet>
               <h:outputText value="#{usuarioVista.codigogrupo}" />
           </h:column>
		   <h:column>
               <f:facet name="header"><h:outputText value="nombregrupo" /></f:facet>
               <h:outputText value="#{usuarioVista.nombregrupo}" />
           </h:column>
		   <h:column>
               <f:facet name="header"><h:outputText value="valorgrupo" /></f:facet>
               <h:outputText value="#{usuarioVista.valorgrupo}" />
           </h:column>
        </t:dataTable>
		
        <h:panelGrid columns="1" styleClass="scrollerTable2" columnClasses="standardTable_ColumnCentered" >
        
            <t:dataScroller id="scroll_1"
                    for="data"
                    fastStep="2"
                    pageCountVar="pageCount"
                    pageIndexVar="pageIndex"
                    styleClass="scroller"
                    paginator="true"
                    paginatorMaxPages="100"
                    paginatorTableClass="paginator"                    
                    immediate="true" 
                    paginatorColumnStyle="font-size:12px"
                    paginatorActiveColumnStyle="font-weight:bold;font-size:18px"
                    >
                <f:facet name="first" >
                    <t:graphicImage url="/images/arrow-first.gif" border="0" />
                </f:facet>
                <f:facet name="last">
                    <t:graphicImage url="/images/arrow-last.gif" border="0" />
                </f:facet>
                <f:facet name="previous">
                    <t:graphicImage url="/images/arrow-previous.gif" border="0" />
                </f:facet>
                <f:facet name="next">
                    <t:graphicImage url="/images/arrow-next.gif" border="0" />
                </f:facet>
                <f:facet name="fastforward">
                    <t:graphicImage url="/images/arrow-ff.gif" border="0" />
                </f:facet>
                <f:facet name="fastrewind">
                    <t:graphicImage url="/images/arrow-fr.gif" border="0" />
                </f:facet>
            </t:dataScroller>
            <t:dataScroller id="scroll_2"
                    for="data"
                    rowsCountVar="rowsCount"
                    displayedRowsCountVar="displayedRowsCountVar"
                    firstRowIndexVar="firstRowIndex"
                    lastRowIndexVar="lastRowIndex"
                    pageCountVar="pageCount"
                    immediate="true"
                    pageIndexVar="pageIndex"                    
                    >
            </t:dataScroller>
        </h:panelGrid>
		
    </h:panelGroup>
    
    <br clear="left"/>

</h:form>
<%@include file="/jspIncludes/footerComun.jsp" %>
</html>
</f:view>
