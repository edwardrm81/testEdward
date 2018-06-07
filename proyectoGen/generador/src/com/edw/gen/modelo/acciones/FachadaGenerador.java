/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edw.gen.modelo.acciones;

import java.util.Map;

import com.edw.gen.modelo.datos.beans.ParametrosGenerador;

/**
 *
 * @author Edward
 */
public class FachadaGenerador {

 

    public static Map generarCodigo(ParametrosGenerador oParametrosGenerador) throws Exception{
        AccionGenerarCodigo oAccionGenerarCodigo = new AccionGenerarCodigo(oParametrosGenerador);
        return oAccionGenerarCodigo.execute();
    }

}
