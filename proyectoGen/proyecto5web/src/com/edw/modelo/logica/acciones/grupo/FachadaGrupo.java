
package com.edw.modelo.logica.acciones.grupo;

import java.util.Map;

import com.edw.modelo.basedatos.generados.beans.Grupo;


/* @author Edward */

public class FachadaGrupo {

    public static Map infoListarGrupo(Map oMapFiltros) throws Exception{
        AccionInfoListarGrupo oAccionInfoListarGrupo = new AccionInfoListarGrupo(oMapFiltros);
        return oAccionInfoListarGrupo.execute();
    }

    public static Map infoEditarGrupo(java.lang.Long codigo) throws Exception{
        AccionInfoEditarGrupo oAccionInfoEditarGrupo = new AccionInfoEditarGrupo(codigo);
        return oAccionInfoEditarGrupo.execute();
    }

    public static void actualizarGrupo(java.lang.Long codigo,Grupo oGrupo) throws Exception{
        AccionActualizarGrupo oAccionActualizarGrupo = new AccionActualizarGrupo(codigo, oGrupo);
        oAccionActualizarGrupo.execute();
    }

    public static void eliminarGrupo(java.lang.Long codigo) throws Exception{
        AccionEliminarGrupo oAccionEliminarGrupo = new AccionEliminarGrupo(codigo);
        oAccionEliminarGrupo.execute();
    }

    public static Map insertarGrupo(Grupo oGrupo) throws Exception{
        AccionInsertarGrupo oAccionInsertarGrupo = new AccionInsertarGrupo(oGrupo);
        return oAccionInsertarGrupo.execute();
    }

    public static Map insertarGrupoAInc(Grupo oGrupo) throws Exception{
        AccionInsertarGrupoAInc oAccionInsertarGrupoAInc = new AccionInsertarGrupoAInc(oGrupo);
        return oAccionInsertarGrupoAInc.execute();
    }

    public static Map insertarGrupoSec(Grupo oGrupo) throws Exception{
        AccionInsertarGrupoSec oAccionInsertarGrupoSec = new AccionInsertarGrupoSec(oGrupo);
        return oAccionInsertarGrupoSec.execute();
    }

}
