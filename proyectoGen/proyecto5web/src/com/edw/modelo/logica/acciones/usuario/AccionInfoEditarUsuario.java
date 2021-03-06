
package com.edw.modelo.logica.acciones.usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.edw.modelo.basedatos.generados.beans.Usuario;
import com.edw.modelo.basedatos.generados.daos.DAOGrupo;
import com.edw.modelo.basedatos.generados.daos.DAOUsuario;
import com.edw.gen.apigen.*;
import com.edw.gen.basedatos.ExecuteNoTransaccional;

/* @author Edward */

public class AccionInfoEditarUsuario extends ExecuteNoTransaccional {

    private java.lang.Long codigo;

    public AccionInfoEditarUsuario(java.lang.Long codigo) {
        this.codigo = codigo;
    }

    public Map execute(Connection conn) throws Exception{

        if(codigo==null) throw new ExcepcionNegocio("No ha ingresado el/la codigo de Usuario");

        Map oMap = new HashMap();
        oMap.put("usuario",new DAOUsuario().selectPK(conn,codigo));
        return oMap;
    }

}
