
package com.edw.modelo.basedatos.generados.daos;

import com.edw.modelo.basedatos.generados.beans.Grupo;

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
public class DAOGrupo {

    public  static String SQLSELECTBASE = "SELECT A.codigo,A.miCodigo2,A.nombre,A.valor ";
    private static String SQLSELECTALL = "SELECT A.codigo,A.miCodigo2,A.nombre,A.valor FROM grupo A ";
    private static String SQLSELECTBYPRIMARYKEY = "SELECT A.codigo,A.miCodigo2,A.nombre,A.valor FROM grupo A WHERE  A.codigo = ?  ";
    private static String SQLUPDATE = "UPDATE grupo SET  codigo = ?, miCodigo2 = ?, nombre = ?, valor = ? WHERE  codigo = ?  ";
    private static String SQLDELETE = "DELETE FROM grupo WHERE  codigo = ?  ";
    private static String SQLINSERT = "INSERT INTO grupo(codigo,miCodigo2,nombre,valor) VALUES (?,?,?,?) ";
    private static String SQLINSERTCONAUTOINCREMENTO = "INSERT INTO grupo(miCodigo2,nombre,valor) VALUES (?,?,?) ";
    private static String SQLSECUENCIA_GRUPO = "SELECT SEC_GRUPO.NEXTVAL FROM grupo ";
    private static String SQLULTIMOIDINSERTADO = "SELECT LAST_INSERT_ID() FROM grupo ";
    private static String SQLSELECTCOUNT = "SELECT COUNT(1) FROM grupo ";

    public DAOGrupo() {

    }

    public Grupo selectPK(Connection conn, java.lang.Long codigo) throws Exception {

        if(codigo==null) throw new SQLException("No ha ingresado el valor de codigo");

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = conn.prepareStatement(SQLSELECTBYPRIMARYKEY);

            int i=1;
            preparedStatement.setObject(i++,codigo,java.sql.Types.BIGINT);

            resultSet = preparedStatement.executeQuery();

            List listaObjeto = this.getListaObjeto(resultSet);
            if(listaObjeto.size()==0) {
                throw new SQLException("No encontró el registro en la tabla 'grupo'");
            };

            if(listaObjeto.size()>1) {
                throw new SQLException("Se encontraron registros duplicados por clave primaria en la tabla 'grupo'");
            };

            return (Grupo)listaObjeto.get(0);

        } catch(SQLException e) {
            throw new SQLException(e);
        } finally {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
        }
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
            if(conteo==null) throw new SQLException("No encontró la cantidad de registros en'grupo'");

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
        Grupo oGrupo = null;
        int i = 1;
        while(resultSet.next()){
            i = 1;
            oGrupo = new Grupo();
            Object oObject = null;

            oObject = resultSet.getObject(i++);
            oGrupo.codigo = oObject==null? null : new java.lang.Long(oObject.toString());

            oObject = resultSet.getObject(i++);
            oGrupo.miCodigo2 = oObject==null? null : (java.lang.Object)oObject;

            oObject = resultSet.getObject(i++);
            oGrupo.nombre = oObject==null? null : new java.lang.String(oObject.toString());

            oObject = resultSet.getObject(i++);
            oGrupo.valor = oObject==null? null : new java.math.BigDecimal(oObject.toString());

            listaObjeto.add(oGrupo);
        }

        return listaObjeto;
    }

    public void actualizar(Connection conn, java.lang.Long codigo, Grupo oGrupo) throws Exception {

        if(codigo==null) throw new SQLException("No ha ingresado el valor de codigo");

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(SQLUPDATE);

            int i = 1;

            {
                if(oGrupo.codigo!=null) preparedStatement.setObject(i++, oGrupo.codigo,java.sql.Types.BIGINT);
                else preparedStatement.setNull(i++, java.sql.Types.BIGINT);
            }
            {
                if(oGrupo.miCodigo2!=null) preparedStatement.setObject(i++, oGrupo.miCodigo2,java.sql.Types.JAVA_OBJECT);
                else preparedStatement.setNull(i++, java.sql.Types.JAVA_OBJECT);
            }
            {
                if(oGrupo.nombre!=null) preparedStatement.setObject(i++, oGrupo.nombre,java.sql.Types.VARCHAR);
                else preparedStatement.setNull(i++, java.sql.Types.VARCHAR);
            }
            {
                if(oGrupo.valor!=null) preparedStatement.setObject(i++, oGrupo.valor,java.sql.Types.DECIMAL);
                else preparedStatement.setNull(i++, java.sql.Types.DECIMAL);
            }
            preparedStatement.setObject(i++,codigo,java.sql.Types.BIGINT);

            int updatedRows = preparedStatement.executeUpdate();

            if (updatedRows == 0) {
                throw new SQLException("No se actualizó el registro en la tabla 'Grupo'");
            }

            if (updatedRows > 1) {
                throw new SQLException("Registro duplicado por clave primaria en la tabla 'Grupo'");
            }

        } catch(SQLException e) {
            throw new SQLException(e);
        } finally {
            if (preparedStatement != null) preparedStatement.close();
        }
    }

    public void eliminar(Connection conn, java.lang.Long codigo) throws Exception {

        if(codigo==null) throw new SQLException("No ha ingresado el valor de codigo");

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(SQLDELETE);

            int i = 1;

            preparedStatement.setObject(i++,codigo,java.sql.Types.BIGINT);

            int removedRows = preparedStatement.executeUpdate();

            if (removedRows == 0) {
                throw new SQLException("No se eliminó ningún registro en la tabla 'Grupo'");
            }

            if (removedRows > 1) {
                throw new SQLException("Registro duplicado por clave primaria en la tabla 'Grupo'");
            }

        } catch(SQLException e) {
            throw new SQLException(e);
        } finally {
            if (preparedStatement != null) preparedStatement.close();
        }
    }

    public void insertar(Connection conn, Grupo oGrupo) throws Exception {

        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = conn.prepareStatement(SQLINSERT);

            int i = 1;
            {
                if(oGrupo.codigo!=null) preparedStatement.setObject(i++, oGrupo.codigo,java.sql.Types.BIGINT);
                else preparedStatement.setNull(i++, java.sql.Types.BIGINT);
            }
            {
                if(oGrupo.miCodigo2!=null) preparedStatement.setObject(i++, oGrupo.miCodigo2,java.sql.Types.JAVA_OBJECT);
                else preparedStatement.setNull(i++, java.sql.Types.JAVA_OBJECT);
            }
            {
                if(oGrupo.nombre!=null) preparedStatement.setObject(i++, oGrupo.nombre,java.sql.Types.VARCHAR);
                else preparedStatement.setNull(i++, java.sql.Types.VARCHAR);
            }
            {
                if(oGrupo.valor!=null) preparedStatement.setObject(i++, oGrupo.valor,java.sql.Types.DECIMAL);
                else preparedStatement.setNull(i++, java.sql.Types.DECIMAL);
            }

            int insertedRows = preparedStatement.executeUpdate();

            if (insertedRows == 0) {
                throw new SQLException("No pudo insertar el registro en la tabla 'Grupo'");
            }

            if (insertedRows > 1) {
                throw new SQLException("Intentó duplicar el nuevo registro en la tabla 'Grupo'");
            }

        } catch(SQLException e) {
            throw new SQLException(e);
        } finally {
            if (preparedStatement != null) preparedStatement.close();
        }
    }

    public void insertarAInc(Connection conn, Grupo oGrupo) throws Exception {

        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = conn.prepareStatement(SQLINSERTCONAUTOINCREMENTO);

            int i = 1;
            {
                if(oGrupo.miCodigo2!=null) preparedStatement.setObject(i++, oGrupo.miCodigo2,java.sql.Types.JAVA_OBJECT);
                else preparedStatement.setNull(i++, java.sql.Types.JAVA_OBJECT);
            }
            {
                if(oGrupo.nombre!=null) preparedStatement.setObject(i++, oGrupo.nombre,java.sql.Types.VARCHAR);
                else preparedStatement.setNull(i++, java.sql.Types.VARCHAR);
            }
            {
                if(oGrupo.valor!=null) preparedStatement.setObject(i++, oGrupo.valor,java.sql.Types.DECIMAL);
                else preparedStatement.setNull(i++, java.sql.Types.DECIMAL);
            }

            int insertedRows = preparedStatement.executeUpdate();

            if (insertedRows == 0) {
                throw new SQLException("No pudo insertar el registro en la tabla 'Grupo'");
            }

            if (insertedRows > 1) {
                throw new SQLException("Intentó duplicar el nuevo registro en la tabla 'Grupo'");
            }

        } catch(SQLException e) {
            throw new SQLException(e);
        } finally {
            if (preparedStatement != null) preparedStatement.close();
        }
    }

    public Long insertarSec(Connection conn, Grupo oGrupo) throws Exception {

        Long valorIdInsercion = null;

        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = conn.prepareStatement(SQLINSERT);

            valorIdInsercion = getValorSecuencia(conn);
            oGrupo.codigo = valorIdInsercion;

            int i = 1;
            preparedStatement.setLong(i++,valorIdInsercion);
            {
                if(oGrupo.codigo!=null) preparedStatement.setObject(i++, oGrupo.codigo,java.sql.Types.BIGINT);
                else preparedStatement.setNull(i++, java.sql.Types.BIGINT);
            }
            {
                if(oGrupo.miCodigo2!=null) preparedStatement.setObject(i++, oGrupo.miCodigo2,java.sql.Types.JAVA_OBJECT);
                else preparedStatement.setNull(i++, java.sql.Types.JAVA_OBJECT);
            }
            {
                if(oGrupo.nombre!=null) preparedStatement.setObject(i++, oGrupo.nombre,java.sql.Types.VARCHAR);
                else preparedStatement.setNull(i++, java.sql.Types.VARCHAR);
            }
            {
                if(oGrupo.valor!=null) preparedStatement.setObject(i++, oGrupo.valor,java.sql.Types.DECIMAL);
                else preparedStatement.setNull(i++, java.sql.Types.DECIMAL);
            }

            int insertedRows = preparedStatement.executeUpdate();

            if (insertedRows == 0) {
                throw new SQLException("No pudo insertar el registro en la tabla 'Grupo'");
            }

            if (insertedRows > 1) {
                throw new SQLException("Intentó duplicar el nuevo registro en la tabla 'Grupo'");
            }

        } catch(SQLException e) {
            throw new SQLException(e);
        } finally {
            if (preparedStatement != null) preparedStatement.close();
        }
        return valorIdInsercion;
    }

    private Long getValorSecuencia(Connection conn) throws Exception {
        Long valorSecuencia = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            preparedStatement = conn.prepareStatement(SQLSECUENCIA_GRUPO);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                valorSecuencia = new Long(resultSet.getLong(1));
            }
            else {
                throw new SQLException("No encontró el valor de la secuencia de 'Grupo'");
            }
        } catch(SQLException e) {
            throw new SQLException(e);
        } finally {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
        }
        return valorSecuencia;
    }

    public Long getUltimoIdInsertado(Connection conn) throws Exception {
        Long ultimoIdInsertado = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            preparedStatement = conn.prepareStatement(SQLULTIMOIDINSERTADO);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                ultimoIdInsertado = new Long(resultSet.getLong(1));
            }
            else {
                throw new SQLException("No encontró el identificador del último registro ingresado en la tabla 'Grupo'");
            }
        } catch(SQLException e) {
            throw new SQLException(e);
        } finally {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
        }
        return ultimoIdInsertado;
    }

}
