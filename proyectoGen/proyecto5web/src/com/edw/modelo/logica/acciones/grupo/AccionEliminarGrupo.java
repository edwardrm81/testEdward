
package com.edw.modelo.logica.acciones.grupo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.edw.gen.apigen.*;
import com.edw.gen.basedatos.ExecuteTransaccional;
import com.edw.modelo.basedatos.generados.daos.DAOGrupo;

/* @author Edward */

public class AccionEliminarGrupo extends ExecuteTransaccional {

    private java.lang.Long codigo;

    public AccionEliminarGrupo(java.lang.Long codigo) {
        this.codigo = codigo;
    }

    public Map execute(Connection conn) throws Exception{

        if(codigo==null) throw new ExcepcionNegocio("No ha ingresado el/la codigo de Grupo");

        new DAOGrupo().eliminar(conn,codigo);

        Map oMap = new HashMap();
        return oMap;
    }

}
