/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edw.modelo.logica.acciones.usuario;


import java.util.Map;

import com.edw.modelo.basedatos.generados.beans.Usuario;

/**
 *
 * @author Edward
 */
public class FachadaUsuario {

    public static Map infoListarUsuario(Map oMapFiltros) throws Exception{
        AccionInfoListarUsuario oAccionInfoListarUsuario = new AccionInfoListarUsuario(oMapFiltros);
        return oAccionInfoListarUsuario.execute();
    }
    
    public static Map infoListarUsuarioVista(Map oMapFiltros) throws Exception{
        AccionInfoListarUsuarioVista oAccionInfoListarUsuarioVista = new AccionInfoListarUsuarioVista(oMapFiltros);
        return oAccionInfoListarUsuarioVista.execute();
    }

    public static Map infoEditarUsuario(Long codigo) throws Exception{
        AccionInfoEditarUsuario oAccionInfoUsuarioPK = new AccionInfoEditarUsuario(codigo);
        return oAccionInfoUsuarioPK.execute();
    }

    public static void actualizarUsuario(Long codigo, Usuario oUsuario) throws Exception{
        AccionActualizarUsuario oAccionActualizarUsuario = new AccionActualizarUsuario(codigo,oUsuario);
        oAccionActualizarUsuario.execute();
    }

    public static void eliminarUsuario(Long codigo) throws Exception{
        AccionEliminarUsuario oLogicaEliminarUsuario = new AccionEliminarUsuario(codigo);
        oLogicaEliminarUsuario.execute();
    }

    public static Map insertarUsuarioAInc(Usuario oUsuario) throws Exception{
        AccionInsertarUsuarioAInc oAccionInsertarUsuarioConAutoincremento = new AccionInsertarUsuarioAInc(oUsuario);
        return oAccionInsertarUsuarioConAutoincremento.execute();
    }
    
}
