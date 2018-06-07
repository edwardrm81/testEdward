
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

public class AccionActualizarGrupo extends ExecuteTransaccional {

    private java.lang.Long codigo;
    private Grupo oGrupo;

    public AccionActualizarGrupo(java.lang.Long codigo,Grupo oGrupo) {
        this.codigo = codigo;
        this.oGrupo = oGrupo;
    }

    public Map execute(Connection conn) throws Exception{

        if(codigo==null) throw new ExcepcionNegocio("No ha ingresado el/la codigo de Grupo");
        if(oGrupo==null) throw new ExcepcionNegocio("No ha ingresado el/la Grupo");

        Grupo oGrupoBD = new DAOGrupo().selectPK(conn,codigo);
        oGrupoBD.codigo = oGrupo.codigo;
        oGrupoBD.miCodigo2 = oGrupo.miCodigo2;
        oGrupoBD.nombre = oGrupo.nombre;
        oGrupoBD.valor = oGrupo.valor;

        new DAOGrupo().actualizar(conn,codigo,oGrupoBD);

        Map oMap = new HashMap();
        oMap.put("grupo", oGrupo);
        return oMap;
    }

}
