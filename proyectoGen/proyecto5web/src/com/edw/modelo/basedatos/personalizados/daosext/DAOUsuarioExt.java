/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edw.modelo.basedatos.personalizados.daosext;

import com.edw.gen.apigen.ConversorTipoJavaATipoJDBC;
import com.edw.gen.apigen.FormateaFiltrosDaos;
import com.edw.gen.apigen.OrderBy;
import com.edw.gen.apigen.OrderBys;
import com.edw.modelo.basedatos.personalizados.beansext.UsuarioExt;
import com.edw.modelo.basedatos.generados.beans.Usuario;
import com.edw.modelo.basedatos.generados.daos.DAOUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Edward
 */
public class DAOUsuarioExt extends DAOUsuario {
	
	private static String SQLSELECTBASE = DAOUsuario.SQLSELECTBASE+",B.nombre AS nombregrupo,B.valor AS valorgrupo ";
	private static String SQLSELECTALL = SQLSELECTBASE+"FROM usuario A "+
	"LEFT OUTER JOIN grupo B ON (A.codigogrupo = B.codigo) " +
	"WHERE A.codigogrupo = B.codigo ";
	
    public DAOUsuarioExt() {
    	
    }

    public List select(Connection conn) throws Exception {
        return this.select(conn,null,null,null,null);
    }

    public List select(Connection conn,List listaFiltros) throws Exception {
        return this.select(conn,listaFiltros,null,null,null);
    }

    public List select(Connection conn, OrderBys orderBys) throws Exception {
        return this.select(conn,null,orderBys,null,null);
    }

    public List select(Connection conn, List listaFiltros, OrderBys orderBys) throws Exception {
        return this.select(conn,listaFiltros,orderBys,null,null);
    }

    public List select(Connection conn, List listaFiltros, OrderBys orderBys, java.lang.Long posicionInicialLimit, java.lang.Long cantidadDeFilasLimit ) throws Exception {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            Map oMap = new FormateaFiltrosDaos().getFiltros(listaFiltros);
            String strFiltros = (String)oMap.get("sqlWhereFiltros");
            if(strFiltros.trim().length()>0) strFiltros = " WHERE "+strFiltros;

            if(SQLSELECTALL.contains("WHERE") && strFiltros.contains("WHERE")) {
                strFiltros = strFiltros.replace("WHERE", "");
                strFiltros = " AND "+strFiltros;
            }

            List listaObjetosCamposFiltros = (List) oMap.get("listaObjetosCamposFiltros");

            StringBuffer strOrderBys = new StringBuffer("");
            List listaOrderBys = new ArrayList();
            if(orderBys!=null && orderBys.listaOrderBy.size()>0) {
                listaOrderBys = orderBys.listaOrderBy;
                strOrderBys.append(" ORDER BY ");
            }

            for (int i=0; i<listaOrderBys.size(); i++) {
                OrderBy oOrderBy = (OrderBy)listaOrderBys.get(i);
                strOrderBys.append(oOrderBy.campo+" "+oOrderBy.sentido+",");
            }
            if(listaOrderBys!=null && listaOrderBys.size()>0) strOrderBys.delete(strOrderBys.length()-1, strOrderBys.length());
            strOrderBys.append(" ");

            String limitSQL = " ";
            if(posicionInicialLimit!=null && cantidadDeFilasLimit!=null) limitSQL =" LIMIT "+posicionInicialLimit+","+cantidadDeFilasLimit+" ";

            preparedStatement = conn.prepareStatement(SQLSELECTALL+strFiltros+strOrderBys.toString()+limitSQL);

            int i=1;
            for(int k=0; k<listaObjetosCamposFiltros.size(); k++) {
                Object oObject = (Object)listaObjetosCamposFiltros.get(k);
                preparedStatement.setObject(i++,oObject,new ConversorTipoJavaATipoJDBC().getTipoDatoJDBC(oObject.getClass().getName()));
            }

            resultSet = preparedStatement.executeQuery();

            List listaObjeto = this.getListaObjeto(resultSet);

            return listaObjeto;

        } catch(SQLException e) {
            throw new SQLException(e);
        } finally {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
        }
    }

    private List getListaObjeto(ResultSet resultSet) throws Exception {

        List listaObjeto = new ArrayList();
        UsuarioExt oUsuarioExt = null;
        int i = 1;
        while(resultSet.next()){
            i = 1;
            oUsuarioExt = new UsuarioExt();
            Object oObject = null;

            oObject = resultSet.getObject(i++);
            oUsuarioExt.codigo = oObject==null? null : new java.lang.Long(oObject.toString());

            oObject = resultSet.getObject(i++);
            oUsuarioExt.clave = oObject==null? null : new java.lang.Integer(oObject.toString());

            oObject = resultSet.getObject(i++);
            oUsuarioExt.codigogrupo = oObject==null? null : new java.lang.Long(oObject.toString());

            oObject = resultSet.getObject(i++);
            oUsuarioExt.estado = oObject==null? null : (java.lang.Boolean)oObject;

            oObject = resultSet.getObject(i++);
            oUsuarioExt.nombre = oObject==null? null : new java.lang.String(oObject.toString());

            oObject = resultSet.getObject(i++);
            oUsuarioExt.usuario = oObject==null? null : new java.lang.Integer(oObject.toString());
            
            oObject = resultSet.getObject(i++);
            oUsuarioExt.nombregrupo = oObject==null? null : new java.lang.String(oObject.toString());

            oObject = resultSet.getObject(i++);
            oUsuarioExt.valorgrupo = oObject==null? null : new java.math.BigDecimal(oObject.toString());            

            listaObjeto.add(oUsuarioExt);
        }

        return listaObjeto;
    }
    
}
