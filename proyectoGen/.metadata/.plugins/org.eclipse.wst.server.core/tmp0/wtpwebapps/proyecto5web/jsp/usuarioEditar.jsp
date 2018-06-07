<%@include file="/jspIncludes/preheadComun.jsp" %>
<f:view>
<html>
<head>
<%@include file="/jspIncludes/headComun.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/jsp/usuarioEditar.js"></script>
</head>
<body onload="onLoadComun();">
<h:form id="form1">
<%@include file="/jspIncludes/menu.jsp" %>	
	<table>
		<tr>
			<td><h:outputText value="Código"/></td>
			<td><h:outputText id="codigo" value="#{controlUsuarioEditar.oUsuario.codigo}" /></td>
		</tr>
		<tr>
			<td><h:outputText value="Usuario"/></td>
			<td><h:inputText id="usuario" value="#{controlUsuarioEditar.oUsuario.usuario}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="Clave"/></td>
			<td><h:inputText id="clave" value="#{controlUsuarioEditar.oUsuario.clave}"/></td>
		</tr>
		<tr>
			<td><h:outputText value="Estado"/></td>
			<td>
				<h:selectOneListbox id="estado" size="1" value="#{controlUsuarioEditar.oUsuario.estado}">
					<f:selectItem itemValue="true" itemLabel="Habilitado"/>
					<f:selectItem itemValue="false" itemLabel="Deshabilitado" />
				</h:selectOneListbox>
			</td>
		</tr>
		<tr>
			<td><h:outputText value="Nombre"/></td>
			<td><h:inputText id="nombre" value="#{controlUsuarioEditar.oUsuario.nombre}" /></td>
		</tr>
		<tr>
			<td><h:outputText value="Código Grupo"/></td>
			<td>
				<h:selectOneListbox id="codigogrupo" size="1" value="#{controlUsuarioEditar.oUsuario.codigogrupo}">
					<f:selectItem itemValue="" itemLabel="(-- SELECCIONE GRUPO --)"/>
					<f:selectItems value="#{controlUsuarioEditar.selectListaGrupos}"/>					
				</h:selectOneListbox>
			</td>
		</tr>		
	</table>
	
	<br clear="left"/>
	
	<h:outputText id="mensaje" value="#{controlUsuarioEditar.mensaje}"/>
	
	<br clear="left"/>
	
	<a4j:commandButton id="guardar" value="Guardar" actionListener="#{controlUsuarioEditar.guardarClic}"
		onclick="if(!validacionGuardar()) return false; document.getElementById('form1:guardar').disable=true;"
		oncomplete="document.getElementById('form1:guardar').disable=false"
		reRender="mensaje,guardar"
	/>
	&nbsp;
	<a4j:commandButton  id="eliminar" value="Eliminar" actionListener="#{controlUsuarioEditar.eliminarClic}"
		onclick="document.getElementById('form1:eliminar').disable=true"
		oncomplete="document.getElementById('form1:eliminar').disable=false"
		reRender="mensaje,eliminar,codigo,usuario,clave,estado,nombre,codigogrupo"
	/>
	&nbsp;
	<a4j:commandButton  id="nuevo" value="Nuevo" actionListener="#{controlUsuarioEditar.nuevoClic}"
		onclick="document.getElementById('form1:nuevo').disable=true"
		oncomplete="document.getElementById('form1:nuevo').disable=false"
		reRender="mensaje,nuevo,codigo,usuario,clave,estado,nombre,codigogrupo"
	/>
	&nbsp;
	<h:commandButton id="volver" value="Volver" action="#{controlUsuarioEditar.volverClic}" />
	
	<%--
	<br clear="left">
	<h:outputText value="Fecha1"/>
	<h:inputText id="fecha1">
		<f:convertDateTime pattern="yyyy-MM-dd" timeZone="America/Bogota" />
	</h:inputText>
	--%>
	
	<br clear="left">
	<h:outputText value="Dinero"/>
	<h:inputText id="dinero" />
	
	<br clear="left">
	<h:outputText value="Fecha Incial"/>
	<h:inputText id="campoFecha1" disabled="true"/>    
    <a4j:commandButton id="campoFecha1_trigger" image="/images/help.gif" />
         <script type="text/javascript">
                  new Calendar({
                          inputField: "form1:campoFecha1",
                          dateFormat: "%Y-%m-%d",
                          trigger: "form1:campoFecha1_trigger",
                          bottomBar: false,
                          onSelect: function() {
                                  var date = Calendar.intToDate(this.selection.get());
                                  LEFT_CAL.args.min = date;
                                  LEFT_CAL.redraw();
                                  this.hide();
                          }
                  });
         </script>
	
	<br clear="left">
	<h:outputText value="Fecha Final"/>
	<h:inputText id="campoFecha2" disabled="true"/>    
    <a4j:commandButton id="campoFecha2_trigger" image="/images/help.gif" />
         <script type="text/javascript">
                  new Calendar({
                          inputField: "form1:campoFecha2",
                          dateFormat: "%Y-%m-%d",
                          trigger: "form1:campoFecha2_trigger",
                          bottomBar: false,
                          onSelect: function() {
                                  var date = Calendar.intToDate(this.selection.get());
                                  LEFT_CAL.args.min = date;
                                  LEFT_CAL.redraw();
                                  this.hide();
                          }
                  });
         </script>
	
</h:form>
<%@include file="/jspIncludes/footerComun.jsp" %>
</html>
</f:view>

