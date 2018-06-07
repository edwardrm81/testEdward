<%@include file="/jspIncludes/preheadComun.jsp" %>
<f:view>
<html>
<head>
<%@include file="/jspIncludes/headComun.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/index.js"></script>

<script type="text/javascript">

function get_IFRAME_doc(sIFRAME_ref)
{
	var oIframe = document.getElementById(sIFRAME_ref) || top.frames[sIFRAME_ref];
	if (typeof oIframe != 'undefined' && oIframe != null)
	{
		if (oIframe.contentDocument) 
			return oIframe.contentDocument; 
		else if (oIframe.contentWindow)
			return oIframe.contentWindow.document;
		else if (oIframe.document)
			return oIframe.document;
		else return null;
	}
}

</script>
</head>
<body onload="onLoadComun();">
<h:form id="form1">

	<div>
		<table>
			<tr>
				<td><h:outputText value="Login"/></td>
				<td><h:inputText value="#{controlLogin.login}" /></td>
			</tr>
			<tr>
				<td><h:outputText value="Password"/></td>
				<td><h:inputText value="#{controlLogin.password}" /></td>
			</tr>
		</table>
		
		<br clear="left"/>
		
		<h:outputText value="#{controlLogin.mensaje}"/>
		
		<br clear="left"/>
		
		<h:commandButton id="ingresar" value="Ingresar" action="#{controlLogin.ingresarClic}" />
		&nbsp;
		<h:commandButton id="cancelar" value="Cancelar" action="#{controlLogin.cancelarClic}" />
	</div>
	<%--
	<iframe src="http://www.canalcapital.gov.co/index.php/franja-opinion/veredicto.html" id="iframe1" width="100%" height="80%"  >
	</iframe>
	--%>
	<a4j:commandButton id="boton1" value="FRAME"  onclick="alert(get_IFRAME_doc('iframe1').getElementsById('mod_search_searchword'));" />
	
</h:form>
</body>
<%@include file="/jspIncludes/footerComun.jsp" %>
</html>
</f:view>

