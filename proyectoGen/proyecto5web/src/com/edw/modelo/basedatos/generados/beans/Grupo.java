
package com.edw.modelo.basedatos.generados.beans;

import java.io.Serializable;

/* @author Edward */

/** Comentario:  */
public class Grupo implements Serializable {

    public Grupo() {
    }

    /** Código del grupo, no es autonumérico */
    public java.lang.Long codigo;

    /** Nombre completo del usuario */
    public java.lang.String nombre;

    /** Valor en plata que ha acumulado el grupo */
    public java.math.BigDecimal valor;

    /**  */
    public java.lang.Object miCodigo2;

    public java.lang.Long getCodigo() {
        return codigo;
    }
    public void setCodigo(java.lang.Long codigo) {
        this.codigo = codigo;
    }

    public java.lang.String getNombre() {
        return nombre;
    }
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }

    public java.math.BigDecimal getValor() {
        return valor;
    }
    public void setValor(java.math.BigDecimal valor) {
        this.valor = valor;
    }

    public java.lang.Object getMiCodigo2() {
        return miCodigo2;
    }
    public void setMiCodigo2(java.lang.Object miCodigo2) {
        this.miCodigo2 = miCodigo2;
    }

}
