
package com.edw.modelo.logica.acciones.usuario;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edw.modelo.basedatos.generados.vbeans.UsuarioVista;
import com.edw.modelo.basedatos.generados.vdaos.DAOUsuarioVista;
import com.edw.modelo.basedatos.personalizados.daosext.DAOUsuarioExt;
import com.edw.gen.apigen.*;
import com.edw.gen.basedatos.ExecuteNoTransaccional;

/* @author Edward */

public class AccionInfoListarUsuarioVista extends ExecuteNoTransaccional {

    private Map oMapFiltros;

    public AccionInfoListarUsuarioVista(Map oMapFiltros) {
        this.oMapFiltros = oMapFiltros;
    }

    public Map execute(Connection conn) throws Exception{

    	OrderBys oOrderBys = new OrderBys(new OrderBy("A.codigogrupo"),new OrderBy("B.valor"));
    	
    	Filtro oFiltro1 = new Filtro("B.valor", new BigDecimal("1000000"),Filtro.OPERADOR_MAYOR_IGUAL);
    	Filtro oFiltro2 = new Filtro("B.valor", new BigDecimal("1000001"),Filtro.OPERADOR_MENOR_IGUAL);
    	
    	List oListaFiltro = new ArrayList();
    	oListaFiltro.add(oFiltro1);
    	oListaFiltro.add(oFiltro2);
    	
    	
    	FiltroGrupo oFiltroGrupo = new FiltroGrupo(oListaFiltro,FiltroGrupo.OPERADOR_OR);
    	List oListaFiltroFinal = new ArrayList();
    	oListaFiltroFinal.add(oFiltroGrupo);
    	//oListaFiltroFinal.add(oFiltro2);
    	
        List listaUsuarioVista = new DAOUsuarioExt().select(conn,oListaFiltroFinal,oOrderBys,new Long(0),new Long(20));

        Map oMap = new HashMap();
        oMap.put("listaUsuarioVista",listaUsuarioVista);
        return oMap;
    }

}
