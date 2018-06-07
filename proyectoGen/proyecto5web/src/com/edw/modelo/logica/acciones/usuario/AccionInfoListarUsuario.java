
package com.edw.modelo.logica.acciones.usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edw.modelo.basedatos.generados.beans.Usuario;
import com.edw.modelo.basedatos.generados.daos.DAOUsuario;
import com.edw.gen.apigen.*;
import com.edw.gen.basedatos.ExecuteNoTransaccional;

/* @author Edward */

public class AccionInfoListarUsuario extends ExecuteNoTransaccional {

    private Map oMapFiltros;

    public AccionInfoListarUsuario(Map oMapFiltros) {
        this.oMapFiltros = oMapFiltros;
    }

    public Map execute(Connection conn) throws Exception{

        List listaUsuario = new DAOUsuario().select(conn);

        Map oMap = new HashMap();
        oMap.put("listaUsuario",listaUsuario);
        return oMap;
    }

}
