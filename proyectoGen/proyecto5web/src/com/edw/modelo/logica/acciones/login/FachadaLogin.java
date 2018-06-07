/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edw.modelo.logica.acciones.login;


import java.util.Map;

import com.edw.modelo.basedatos.generados.beans.Usuario;

/**
 *
 * @author Edward
 */
public class FachadaLogin {

    public static Map validarUsuario(Integer login, Integer password) throws Exception{
        AccionValidarUsuario oAccionValidarUsuario = new AccionValidarUsuario(login,password);
        return oAccionValidarUsuario.execute();
    }
    
}
