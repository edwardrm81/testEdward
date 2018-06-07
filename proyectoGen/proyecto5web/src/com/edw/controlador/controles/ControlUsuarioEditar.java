

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edw.controlador.controles;


//import com.edw.beans.Usuario;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;


import com.edw.gen.apigen.ExcepcionNegocio;
import com.edw.modelo.basedatos.generados.beans.Grupo;
import com.edw.modelo.basedatos.generados.beans.Usuario;
import com.edw.modelo.logica.acciones.grupo.FachadaGrupo;
import com.edw.modelo.logica.acciones.usuario.FachadaUsuario;
import com.edw.modelo.util.Constantes;

/**
 *
 * @author Edward
 */
public class ControlUsuarioEditar extends ContextBean{
	
	private Usuario oUsuario;	
	private String mensaje;
	private List selectListaGrupos = new ArrayList();
	private Boolean esNuevo = true;
	
	public ControlUsuarioEditar(){
		try{
			super.limpiarBeansDeSesion("controlUsuarioEditar");
			if(super.getHttpSession().getAttribute("Usuario")==null) throw new ExcepcionNegocio("No encontró el usuario de la sesión");
			
			oUsuario = new Usuario();
			
			Object objetoParam = super.getHttpServletRequest().getAttribute("codigoParam");
			
			if(objetoParam!=null) {
				Long codigoParam = new Long(objetoParam.toString());
				
				Map oMapInfoEditarUsuario = FachadaUsuario.infoEditarUsuario(codigoParam);				
				oUsuario = (Usuario)oMapInfoEditarUsuario.get("usuario");
			}

			Map oMapGrupo = FachadaGrupo.infoListarGrupo(null);				
			List listaGrupos = (List)oMapGrupo.get("listaGrupo");
			
			for (int i = 0; i < listaGrupos.size(); i++) {
				Grupo oGrupo = (Grupo) listaGrupos.get(i);
				selectListaGrupos.add(new SelectItem(oGrupo.codigo, oGrupo.codigo +"-"+oGrupo.nombre));
			}
		}
		catch(Exception e){
			mensaje = Constantes.ERROR_SYSTEMA;
			e.printStackTrace();
		}
	}
	
	
	public void guardarClic(ActionEvent ev) {
		try{
			this.mensaje="";
			if(esNuevo) {
				Map oMapUsuario = FachadaUsuario.insertarUsuarioAInc(oUsuario);
				oUsuario = (Usuario)oMapUsuario.get("usuario");
				esNuevo = false;
			}
			else {
				FachadaUsuario.actualizarUsuario(oUsuario.codigo, oUsuario);
			}
			this.mensaje = "Se guardó exitosamente";
		}
		catch(Exception e){
			this.mensaje = Constantes.ERROR_SYSTEMA;
			e.printStackTrace();
		}
	}
	
	public void eliminarClic(ActionEvent ev) {
		try{
			mensaje = "";
			FachadaUsuario.eliminarUsuario(oUsuario.codigo);
			oUsuario = new Usuario();
			esNuevo = true;
			mensaje = "Se eliminó exitosamente";
		}
		catch(Exception e){
			mensaje = Constantes.ERROR_SYSTEMA;
			e.printStackTrace();
		}
	}
	
	public void nuevoClic(ActionEvent ev) {
		try{			
			oUsuario = new Usuario();
			esNuevo = true;
			mensaje = "";			
		}
		catch(Exception e){
			mensaje = Constantes.ERROR_SYSTEMA;
			e.printStackTrace();
		}
	}

	public String volverClic() {
		return "volver";
	}
	
	public final String getMensaje() {
		return mensaje;
	}

	public final void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public final List getSelectListaGrupos() {
		return selectListaGrupos;
	}

	public final void setSelectListaGrupos(List selectListaGrupos) {
		this.selectListaGrupos = selectListaGrupos;
	}


	public Usuario getoUsuario() {
		return oUsuario;
	}


	public void setoUsuario(Usuario oUsuario) {
		this.oUsuario = oUsuario;
	}


	public Boolean getEsNuevo() {
		return esNuevo;
	}


	public void setEsNuevo(Boolean esNuevo) {
		this.esNuevo = esNuevo;
	}

	
}
  