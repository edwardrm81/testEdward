

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edw.controlador.controles;


//import com.edw.beans.Usuario;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.management.InstanceNotFoundException;


import com.edw.gen.apigen.ExcepcionNegocio;
import com.edw.modelo.basedatos.generados.beans.Grupo;
import com.edw.modelo.basedatos.generados.beans.Usuario;
import com.edw.modelo.basedatos.generados.vbeans.UsuarioVista;
import com.edw.modelo.logica.acciones.login.FachadaLogin;
import com.edw.modelo.logica.acciones.usuario.FachadaUsuario;

/**
 *
 * @author Edward
 */
public class ControlLogin extends ContextBean{
		
	private String mensaje;
	private Integer login;
	private Integer password;
	
	public ControlLogin(){
		super.limpiarBeansDeSesion("");
	}
	
	
	public String ingresarClic() {
		try{
			Map oMapLogin = FachadaLogin.validarUsuario(login, password);
			Usuario oUsuario = (Usuario)oMapLogin.get("usuario");			
			
			super.getHttpSession().setAttribute("Usuario", oUsuario);
			
			return "loginok";
			
		}
		catch(ExcepcionNegocio e){
			mensaje = e.getMessage();
			e.printStackTrace();
		}
		catch(SQLException e){
			mensaje = e.getMessage();
			e.printStackTrace();
		}		
		catch(Exception e){
			mensaje = e.getMessage();
			e.printStackTrace();
		}
		return "";
	}
	
	
	public String cancelarClic() {
		try{
			login = null;
			password = null;
			mensaje = "";
		}
		catch(Exception e){
			mensaje = e.getMessage();
			e.printStackTrace();
		}
		return "";
	}

	
	public final String getMensaje() {
		return mensaje;
	}

	public final void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	public Integer getLogin() {
		return login;
	}


	public void setLogin(Integer login) {
		this.login = login;
	}


	public Integer getPassword() {
		return password;
	}


	public void setPassword(Integer password) {
		this.password = password;
	}

	
}
  