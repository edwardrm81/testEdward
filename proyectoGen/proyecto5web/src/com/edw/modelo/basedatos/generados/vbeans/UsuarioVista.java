
package com.edw.modelo.basedatos.generados.vbeans;

import java.io.Serializable;

/* @author Edward */

/** Comentario:  */
public class UsuarioVista implements Serializable {

    public UsuarioVista() {
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
    public java.lang.Long codigoGrupo;

    /** Nombre completo del usuario */
    public java.lang.String nombregrupo;

    /** Valor en plata que ha acumulado el grupo */
    public java.math.BigDecimal valorgrupo;

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

    public java.lang.Long getCodigoGrupo() {
        return codigoGrupo;
    }
    public void setCodigoGrupo(java.lang.Long codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
    }

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
