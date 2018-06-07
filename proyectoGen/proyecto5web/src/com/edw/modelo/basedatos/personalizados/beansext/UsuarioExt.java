/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edw.modelo.basedatos.personalizados.beansext;

import java.io.Serializable;

import com.edw.modelo.basedatos.generados.beans.Usuario;



/**
 *
 * @author Edward
 */
public class UsuarioExt extends Usuario implements Serializable {

    public UsuarioExt() {
    }
    
    public java.lang.String nombregrupo; //Nombre completo del usuario
    public java.math.BigDecimal valorgrupo; //Valor en plata que ha acumulado el grupo
    
	public java.lang.String getNombregrupo() {
		return nombregrupo;
	}
	
	public void setNombregrupo(java.lang.String nombregrupo) {
		this.nombregrupo = nombregrupo;
	}
	
	public java.math.BigDecimal getValorgrupo() {
		return valorgrupo;
	}
	
	public void setValorgrupo(java.math.BigDecimal valorgrupo) {
		this.valorgrupo = valorgrupo;
	}

}
