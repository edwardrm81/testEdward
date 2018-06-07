
package com.edw.modelo.logica.acciones.grupo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


import com.edw.gen.apigen.*;
import com.edw.gen.basedatos.ExecuteTransaccional;
import com.edw.modelo.basedatos.generados.beans.Grupo;
import com.edw.modelo.basedatos.generados.daos.DAOGrupo;

/* @author Edward */

public class AccionInsertarGrupo extends ExecuteTransaccional {

    private Grupo oGrupo;

    public AccionInsertarGrupo(Grupo oGrupo) {
        this.oGrupo = oGrupo;
    }

    public Map execute(Connection conn) throws Exception {

        if(oGrupo==null) throw new ExcepcionNegocio("No ha ingresado el/la Grupo");

        Grupo oGrupoBD = new Grupo();
        oGrupoBD.codigo = oGrupo.codigo;
        oGrupoBD.miCodigo2 = oGrupo.miCodigo2;
        oGrupoBD.nombre = oGrupo.nombre;
        oGrupoBD.valor = oGrupo.valor;

        new DAOGrupo().insertar(conn, oGrupoBD);

        Map oMap = new HashMap();
        oMap.put("grupo",oGrupoBD);
        return oMap;
    }

}
