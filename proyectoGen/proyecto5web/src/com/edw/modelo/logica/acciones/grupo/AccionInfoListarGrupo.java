
package com.edw.modelo.logica.acciones.grupo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.edw.gen.apigen.*;
import com.edw.gen.basedatos.ExecuteNoTransaccional;
import com.edw.modelo.basedatos.generados.daos.DAOGrupo;

/* @author Edward */

public class AccionInfoListarGrupo extends ExecuteNoTransaccional {

    private Map oMapFiltros;

    public AccionInfoListarGrupo(Map oMapFiltros) {
        this.oMapFiltros = oMapFiltros;
    }

    public Map execute(Connection conn) throws Exception{

        List listaGrupo = new DAOGrupo().select(conn);

        Map oMap = new HashMap();
        oMap.put("listaGrupo",listaGrupo);
        return oMap;
    }

}
