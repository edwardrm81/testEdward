
package com.edw.modelo.basedatos.generados.vdaos;

import com.edw.modelo.basedatos.generados.vbeans.UsuarioVista;

import com.edw.gen.apigen.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* @author Edward */

// Comentario: 
public class DAOUsuarioVista {

    public  static String SQLSELECTBASE = "SELECT A.clave,A.codigo,A.codigoGrupo,A.estado,A.nombre,A.nombregrupo,A.usuario,A.valorgrupo ";
    private static String SQLSELECTALL = "SELECT A.clave,A.codigo,A.codigoGrupo,A.estado,A.nombre,A.nombregrupo,A.usuario,A.valorgrupo FROM usuarioVista A ";
    private static String SQLSELECTCOUNT = "SELECT COUNT(1) FROM usuarioVista ";

    public DAOUsuarioVista() {

    }

    public java.lang.Long selectCount(Connection conn) throws Exception {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = conn.prepareStatement(SQLSELECTCOUNT);
            resultSet = preparedStatement.executeQuery();

            java.lang.Long conteo = null;

            if(resultSet.next()){
                Object oObject = resultSet.getObject(1);
                conteo = oObject==null? null : new java.lang.Long(oObject.toString());
            }
            if(conteo==null) throw new SQLException("No encontró la cantidad de registros en'usuarioVista'");

            return (conteo);

        } catch(SQLException e) {
            throw new SQLException(e);
        } finally {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
        }
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
        UsuarioVista oUsuarioVista = null;
        int i = 1;
        while(resultSet.next()){
            i = 1;
            oUsuarioVista = new UsuarioVista();
            Object oObject = null;

            oObject = resultSet.getObject(i++);
            oUsuarioVista.clave = oObject==null? null : new java.lang.Integer(oObject.toString());

            oObject = resultSet.getObject(i++);
            oUsuarioVista.codigo = oObject==null? null : new java.lang.Long(oObject.toString());

            oObject = resultSet.getObject(i++);
            oUsuarioVista.codigoGrupo = oObject==null? null : new java.lang.Long(oObject.toString());

            oObject = resultSet.getObject(i++);
            oUsuarioVista.estado = oObject==null? null : (java.lang.Boolean)oObject;

            oObject = resultSet.getObject(i++);
            oUsuarioVista.nombre = oObject==null? null : new java.lang.String(oObject.toString());

            oObject = resultSet.getObject(i++);
            oUsuarioVista.nombregrupo = oObject==null? null : new java.lang.String(oObject.toString());

            oObject = resultSet.getObject(i++);
            oUsuarioVista.usuario = oObject==null? null : new java.lang.Integer(oObject.toString());

            oObject = resultSet.getObject(i++);
            oUsuarioVista.valorgrupo = oObject==null? null : new java.math.BigDecimal(oObject.toString());

            listaObjeto.add(oUsuarioVista);
        }

        return listaObjeto;
    }

}
