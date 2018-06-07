
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

public class AccionActualizarUsuario extends ExecuteTransaccional {

    private java.lang.Long codigo;
    private Usuario oUsuario;

    public AccionActualizarUsuario(java.lang.Long codigo,Usuario oUsuario) {
        this.codigo = codigo;
        this.oUsuario = oUsuario;
    }

    public Map execute(Connection conn) throws Exception{

        if(codigo==null) throw new ExcepcionNegocio("No ha ingresado el/la codigo de Usuario");
        if(oUsuario==null) throw new ExcepcionNegocio("No ha ingresado el/la Usuario");

        Usuario oUsuarioBD = new DAOUsuario().selectPK(conn,codigo);
        oUsuarioBD.codigo = oUsuario.codigo;
        oUsuarioBD.clave = oUsuario.clave;
        oUsuarioBD.codigogrupo = oUsuario.codigogrupo;
        oUsuarioBD.estado = oUsuario.estado;
        oUsuarioBD.nombre = oUsuario.nombre;
        oUsuarioBD.usuario = oUsuario.usuario;

        new DAOUsuario().actualizar(conn,codigo,oUsuarioBD);

        Map oMap = new HashMap();
        oMap.put("usuario", oUsuario);
        return oMap;
    }

}
