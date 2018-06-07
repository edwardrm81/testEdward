
package com.edw.modelo.logica.acciones.usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.edw.modelo.basedatos.generados.beans.Usuario;
import com.edw.modelo.basedatos.generados.daos.DAOUsuario;
import com.edw.gen.apigen.*;
import com.edw.gen.basedatos.ExecuteTransaccional;

/* @author Edward */

public class AccionInsertarUsuarioSec extends ExecuteTransaccional {

    private Usuario oUsuario;

    public AccionInsertarUsuarioSec(Usuario oUsuario) {
        this.oUsuario = oUsuario;
    }

    public Map execute(Connection conn) throws Exception {

        if(oUsuario==null) throw new ExcepcionNegocio("No ha ingresado el/la Usuario");

        Usuario oUsuarioBD = new Usuario();
        oUsuarioBD.codigo = oUsuario.codigo;
        oUsuarioBD.clave = oUsuario.clave;
        oUsuarioBD.codigogrupo = oUsuario.codigogrupo;
        oUsuarioBD.estado = oUsuario.estado;
        oUsuarioBD.nombre = oUsuario.nombre;
        oUsuarioBD.usuario = oUsuario.usuario;

        Long id = new DAOUsuario().insertarSec(conn, oUsuarioBD);

        Usuario oUsuario = new DAOUsuario().selectPK(conn, id);

        Map oMap = new HashMap();
        oMap.put("usuario", oUsuario);
        return oMap;
    }

}
