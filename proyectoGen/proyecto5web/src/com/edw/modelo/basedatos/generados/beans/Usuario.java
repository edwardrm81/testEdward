
package com.edw.modelo.basedatos.generados.beans;

import java.io.Serializable;

/* @author Edward */

/** Comentario:  */
public class Usuario implements Serializable {

    public Usuario() {
    }

    /** codigo autonumerico del+- usuario */
    public java.lang.Long codigo;

    /** Login del usuario, es numérico */
    public java.lang.Integer usuario;

    /** Password del usuario */
    public java.lang.Integer clave;

    /** Nombre completo del usuario */
    public java.lang.String nombre;

    /** habilitado o deshabilitado */
    public java.lang.Boolean estado;

    /** grupo del usuario */
    public java.lang.Long codigogrupo;

    public java.lang.Long getCodigo() {
        return codigo;
    }
    public void setCodigo(java.lang.Long codigo) {
        this.codigo = codigo;
    }

    public java.lang.Integer getUsuario() {
        return usuario;
    }
    public void setUsuario(java.lang.Integer usuario) {
        this.usuario = usuario;
    }

    public java.lang.Integer getClave() {
        return clave;
    }
    public void setClave(java.lang.Integer clave) {
        this.clave = clave;
    }

    public java.lang.String getNombre() {
        return nombre;
    }
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }

    public java.lang.Boolean getEstado() {
        return estado;
    }
    public void setEstado(java.lang.Boolean estado) {
        this.estado = estado;
    }

    public java.lang.Long getCodigogrupo() {
        return codigogrupo;
    }
    public void setCodigogrupo(java.lang.Long codigogrupo) {
        this.codigogrupo = codigogrupo;
    }

}
