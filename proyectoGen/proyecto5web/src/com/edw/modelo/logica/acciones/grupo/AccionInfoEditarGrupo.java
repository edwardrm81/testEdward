
package com.edw.modelo.logica.acciones.grupo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


import com.edw.gen.apigen.*;
import com.edw.gen.basedatos.ExecuteNoTransaccional;
import com.edw.modelo.basedatos.generados.daos.DAOGrupo;


/* @author Edward */

public class AccionInfoEditarGrupo extends ExecuteNoTransaccional {

    private java.lang.Long codigo;

    public AccionInfoEditarGrupo(java.lang.Long codigo) {
        this.codigo = codigo;
    }

    public Map execute(Connection conn) throws Exception{

        if(codigo==null) throw new ExcepcionNegocio("No ha ingresado el/la codigo de Grupo");

        Map oMap = new HashMap();
        oMap.put("grupo",new DAOGrupo().selectPK(conn,codigo));
        return oMap;
    }

}
