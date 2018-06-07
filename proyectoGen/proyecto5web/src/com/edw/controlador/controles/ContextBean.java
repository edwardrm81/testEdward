

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edw.controlador.controles;


//import com.edw.beans.Usuario;


import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Edward
 */
public class ContextBean {
		
	
	public ContextBean(){
		
	}
	
	public FacesContext getFacesContext(){
		return FacesContext.getCurrentInstance();
	}

	public ExternalContext getExternalContext(){
		return this.getFacesContext().getExternalContext();
	}
	
	
	public HttpServletRequest getHttpServletRequest(){
		return (HttpServletRequest)this.getExternalContext().getRequest();
	}	
	
	public HttpSession getHttpSession(){
		return this.getHttpServletRequest().getSession();
	}	

	
	public void limpiarBeansDeSesion(String nombreBean1){
		List lista = new ArrayList();
		lista.add(nombreBean1);
		this.limpiarBeansDeSesion(lista);
	}
	
	public void limpiarBeansDeSesion(String nombreBean1,String nombreBean2){
		List lista = new ArrayList();
		lista.add(nombreBean1);
		lista.add(nombreBean2);
		this.limpiarBeansDeSesion(lista);
	}	

	public void limpiarBeansDeSesion(String nombreBean1,String nombreBean2,String nombreBean3){
		List lista = new ArrayList();
		lista.add(nombreBean1);
		lista.add(nombreBean2);
		lista.add(nombreBean3);
		this.limpiarBeansDeSesion(lista);
	}
	
	public void limpiarBeansDeSesion(List lista){
		ExternalContext oExternalContext = this.getExternalContext();
		
		if(!lista.contains("controlUsuarioEditar")) oExternalContext.getSessionMap().remove("controlUsuarioEditar");
		if(!lista.contains("controlUsuarioLista")) oExternalContext.getSessionMap().remove("controlUsuarioLista");
		
	}	

	
}
  