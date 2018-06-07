

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edw.controlador.controles;


//import com.edw.beans.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.edw.gen.apigen.ExcepcionNegocio;
import com.edw.modelo.logica.acciones.usuario.FachadaUsuario;

/**
 *
 * @author Edward
 */
public class ControlUsuarioLista extends ContextBean {
	
	private String mensaje;
	private List listaUsuariosVista = new ArrayList();
	
	public ControlUsuarioLista(){
		try{
			super.limpiarBeansDeSesion("controlUsuarioLista");
			if(super.getHttpSession().getAttribute("Usuario")==null) throw new ExcepcionNegocio("No encontró el usuario de la sesión");
			
			Map oMapListaUsuariosVista = FachadaUsuario.infoListarUsuarioVista(null);
			listaUsuariosVista = (List)oMapListaUsuariosVista.get("listaUsuarioVista");
		}	
		catch(Exception e){
			mensaje = e.getMessage();
			e.printStackTrace();
		}
	}

	public String nuevoClic() {
		try{			
			return "nuevo";
		}
		catch(Exception e){
			mensaje = e.getMessage();
			e.printStackTrace();
		}
		return "";
	}
	
	
	public String editarClic() {
		try{
			Long codigoParam = new Long(super.getExternalContext().getRequestParameterMap().get("codigoParam"));			
			super.getHttpServletRequest().setAttribute("codigoParam", codigoParam);
			
			return "editar";
		}
		catch(Exception e){
			mensaje = e.getMessage();
			e.printStackTrace();
		}
		return "";
	}

	/*
	public void editarClicEvent(ActionEvent event) {
		try{
			Long codigoParam = new Long(((UIParameter)event.getComponent().findComponent("codigoParam")).getValue().toString());
			
			super.getHttpServletRequest().setAttribute("codigoParam", codigoParam);
		}		
		catch(Exception e){
			mensaje = e.getMessage();
			e.printStackTrace();
		}		
	}
	*/
	
	public List getListaUsuariosVista() {
		return listaUsuariosVista;
	}

	public void setListaUsuariosVista(List listaUsuariosVista) {
		this.listaUsuariosVista = listaUsuariosVista;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
  