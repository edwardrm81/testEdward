
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

public class AccionEliminarUsuario extends ExecuteTransaccional {

    private java.lang.Long codigo;

    public AccionEliminarUsuario(java.lang.Long codigo) {
        this.codigo = codigo;
    }

    public Map execute(Connection conn) throws Exception{

        if(codigo==null) throw new ExcepcionNegocio("No ha ingresado el/la codigo de Usuario");

        new DAOUsuario().eliminar(conn,codigo);

        Map oMap = new HashMap();
        return oMap;
    }

}
