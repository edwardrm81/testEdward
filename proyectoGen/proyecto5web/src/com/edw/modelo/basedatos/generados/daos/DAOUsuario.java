
package com.edw.modelo.basedatos.generados.daos;

import com.edw.modelo.basedatos.generados.beans.Usuario;

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
public class DAOUsuario {

    public  static String SQLSELECTBASE = "SELECT A.codigo,A.clave,A.codigogrupo,A.estado,A.nombre,A.usuario ";
    private static String SQLSELECTALL = "SELECT A.codigo,A.clave,A.codigogrupo,A.estado,A.nombre,A.usuario FROM usuario A ";
    private static String SQLSELECTBYPRIMARYKEY = "SELECT A.codigo,A.clave,A.codigogrupo,A.estado,A.nombre,A.usuario FROM usuario A WHERE  A.codigo = ?  ";
    private static String SQLUPDATE = "UPDATE usuario SET  codigo = ?, clave = ?, codigogrupo = ?, estado = ?, nombre = ?, usuario = ? WHERE  codigo = ?  ";
    private static String SQLDELETE = "DELETE FROM usuario WHERE  codigo = ?  ";
    private static String SQLINSERT = "INSERT INTO usuario(codigo,clave,codigogrupo,estado,nombre,usuario) VALUES (?,?,?,?,?,?) ";
    private static String SQLINSERTCONAUTOINCREMENTO = "INSERT INTO usuario(clave,codigogrupo,estado,nombre,usuario) VALUES (?,?,?,?,?) ";
    private static String SQLSECUENCIA_USUARIO = "SELECT SEC_USUARIO.NEXTVAL FROM usuario ";
    private static String SQLULTIMOIDINSERTADO = "SELECT LAST_INSERT_ID() FROM usuario ";
    private static String SQLSELECTCOUNT = "SELECT COUNT(1) FROM usuario ";

    public DAOUsuario() {

    }

    public Usuario selectPK(Connection conn, java.lang.Long codigo) throws Exception {

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
                throw new SQLException("No encontró el registro en la tabla 'usuario'");
            };

            if(listaObjeto.size()>1) {
                throw new SQLException("Se encontraron registros duplicados por clave primaria en la tabla 'usuario'");
            };

            return (Usuario)listaObjeto.get(0);

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
            if(conteo==null) throw new SQLException("No encontró la cantidad de registros en'usuario'");

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
        Usuario oUsuario = null;
        int i = 1;
        while(resultSet.next()){
            i = 1;
            oUsuario = new Usuario();
            Object oObject = null;

            oObject = resultSet.getObject(i++);
            oUsuario.codigo = oObject==null? null : new java.lang.Long(oObject.toString());

            oObject = resultSet.getObject(i++);
            oUsuario.clave = oObject==null? null : new java.lang.Integer(oObject.toString());

            oObject = resultSet.getObject(i++);
            oUsuario.codigogrupo = oObject==null? null : new java.lang.Long(oObject.toString());

            oObject = resultSet.getObject(i++);
            oUsuario.estado = oObject==null? null : (java.lang.Boolean)oObject;

            oObject = resultSet.getObject(i++);
            oUsuario.nombre = oObject==null? null : new java.lang.String(oObject.toString());

            oObject = resultSet.getObject(i++);
            oUsuario.usuario = oObject==null? null : new java.lang.Integer(oObject.toString());

            listaObjeto.add(oUsuario);
        }

        return listaObjeto;
    }

    public void actualizar(Connection conn, java.lang.Long codigo, Usuario oUsuario) throws Exception {

        if(codigo==null) throw new SQLException("No ha ingresado el valor de codigo");

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(SQLUPDATE);

            int i = 1;

            {
                if(oUsuario.codigo!=null) preparedStatement.setObject(i++, oUsuario.codigo,java.sql.Types.BIGINT);
                else preparedStatement.setNull(i++, java.sql.Types.BIGINT);
            }
            {
                if(oUsuario.clave!=null) preparedStatement.setObject(i++, oUsuario.clave,java.sql.Types.INTEGER);
                else preparedStatement.setNull(i++, java.sql.Types.INTEGER);
            }
            {
                if(oUsuario.codigogrupo!=null) preparedStatement.setObject(i++, oUsuario.codigogrupo,java.sql.Types.BIGINT);
                else preparedStatement.setNull(i++, java.sql.Types.BIGINT);
            }
            {
                if(oUsuario.estado!=null) preparedStatement.setObject(i++, oUsuario.estado,java.sql.Types.BIT);
                else preparedStatement.setNull(i++, java.sql.Types.BIT);
            }
            {
                if(oUsuario.nombre!=null) preparedStatement.setObject(i++, oUsuario.nombre,java.sql.Types.VARCHAR);
                else preparedStatement.setNull(i++, java.sql.Types.VARCHAR);
            }
            {
                if(oUsuario.usuario!=null) preparedStatement.setObject(i++, oUsuario.usuario,java.sql.Types.INTEGER);
                else preparedStatement.setNull(i++, java.sql.Types.INTEGER);
            }
            preparedStatement.setObject(i++,codigo,java.sql.Types.BIGINT);

            int updatedRows = preparedStatement.executeUpdate();

            if (updatedRows == 0) {
                throw new SQLException("No se actualizó el registro en la tabla 'Usuario'");
            }

            if (updatedRows > 1) {
                throw new SQLException("Registro duplicado por clave primaria en la tabla 'Usuario'");
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
                throw new SQLException("No se eliminó ningún registro en la tabla 'Usuario'");
            }

            if (removedRows > 1) {
                throw new SQLException("Registro duplicado por clave primaria en la tabla 'Usuario'");
            }

        } catch(SQLException e) {
            throw new SQLException(e);
        } finally {
            if (preparedStatement != null) preparedStatement.close();
        }
    }

    public void insertar(Connection conn, Usuario oUsuario) throws Exception {

        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = conn.prepareStatement(SQLINSERT);

            int i = 1;
            {
                if(oUsuario.codigo!=null) preparedStatement.setObject(i++, oUsuario.codigo,java.sql.Types.BIGINT);
                else preparedStatement.setNull(i++, java.sql.Types.BIGINT);
            }
            {
                if(oUsuario.clave!=null) preparedStatement.setObject(i++, oUsuario.clave,java.sql.Types.INTEGER);
                else preparedStatement.setNull(i++, java.sql.Types.INTEGER);
            }
            {
                if(oUsuario.codigogrupo!=null) preparedStatement.setObject(i++, oUsuario.codigogrupo,java.sql.Types.BIGINT);
                else preparedStatement.setNull(i++, java.sql.Types.BIGINT);
            }
            {
                if(oUsuario.estado!=null) preparedStatement.setObject(i++, oUsuario.estado,java.sql.Types.BIT);
                else preparedStatement.setNull(i++, java.sql.Types.BIT);
            }
            {
                if(oUsuario.nombre!=null) preparedStatement.setObject(i++, oUsuario.nombre,java.sql.Types.VARCHAR);
                else preparedStatement.setNull(i++, java.sql.Types.VARCHAR);
            }
            {
                if(oUsuario.usuario!=null) preparedStatement.setObject(i++, oUsuario.usuario,java.sql.Types.INTEGER);
                else preparedStatement.setNull(i++, java.sql.Types.INTEGER);
            }

            int insertedRows = preparedStatement.executeUpdate();

            if (insertedRows == 0) {
                throw new SQLException("No pudo insertar el registro en la tabla 'Usuario'");
            }

            if (insertedRows > 1) {
                throw new SQLException("Intentó duplicar el nuevo registro en la tabla 'Usuario'");
            }

        } catch(SQLException e) {
            throw new SQLException(e);
        } finally {
            if (preparedStatement != null) preparedStatement.close();
        }
    }

    public void insertarAInc(Connection conn, Usuario oUsuario) throws Exception {

        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = conn.prepareStatement(SQLINSERTCONAUTOINCREMENTO);

            int i = 1;
            {
                if(oUsuario.clave!=null) preparedStatement.setObject(i++, oUsuario.clave,java.sql.Types.INTEGER);
                else preparedStatement.setNull(i++, java.sql.Types.INTEGER);
            }
            {
                if(oUsuario.codigogrupo!=null) preparedStatement.setObject(i++, oUsuario.codigogrupo,java.sql.Types.BIGINT);
                else preparedStatement.setNull(i++, java.sql.Types.BIGINT);
            }
            {
                if(oUsuario.estado!=null) preparedStatement.setObject(i++, oUsuario.estado,java.sql.Types.BIT);
                else preparedStatement.setNull(i++, java.sql.Types.BIT);
            }
            {
                if(oUsuario.nombre!=null) preparedStatement.setObject(i++, oUsuario.nombre,java.sql.Types.VARCHAR);
                else preparedStatement.setNull(i++, java.sql.Types.VARCHAR);
            }
            {
                if(oUsuario.usuario!=null) preparedStatement.setObject(i++, oUsuario.usuario,java.sql.Types.INTEGER);
                else preparedStatement.setNull(i++, java.sql.Types.INTEGER);
            }

            int insertedRows = preparedStatement.executeUpdate();

            if (insertedRows == 0) {
                throw new SQLException("No pudo insertar el registro en la tabla 'Usuario'");
            }

            if (insertedRows > 1) {
                throw new SQLException("Intentó duplicar el nuevo registro en la tabla 'Usuario'");
            }

        } catch(SQLException e) {
            throw new SQLException(e);
        } finally {
            if (preparedStatement != null) preparedStatement.close();
        }
    }

    public Long insertarSec(Connection conn, Usuario oUsuario) throws Exception {

        Long valorIdInsercion = null;

        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = conn.prepareStatement(SQLINSERT);

            valorIdInsercion = getValorSecuencia(conn);
            oUsuario.codigo = valorIdInsercion;

            int i = 1;
            preparedStatement.setLong(i++,valorIdInsercion);
            {
                if(oUsuario.codigo!=null) preparedStatement.setObject(i++, oUsuario.codigo,java.sql.Types.BIGINT);
                else preparedStatement.setNull(i++, java.sql.Types.BIGINT);
            }
            {
                if(oUsuario.clave!=null) preparedStatement.setObject(i++, oUsuario.clave,java.sql.Types.INTEGER);
                else preparedStatement.setNull(i++, java.sql.Types.INTEGER);
            }
            {
                if(oUsuario.codigogrupo!=null) preparedStatement.setObject(i++, oUsuario.codigogrupo,java.sql.Types.BIGINT);
                else preparedStatement.setNull(i++, java.sql.Types.BIGINT);
            }
            {
                if(oUsuario.estado!=null) preparedStatement.setObject(i++, oUsuario.estado,java.sql.Types.BIT);
                else preparedStatement.setNull(i++, java.sql.Types.BIT);
            }
            {
                if(oUsuario.nombre!=null) preparedStatement.setObject(i++, oUsuario.nombre,java.sql.Types.VARCHAR);
                else preparedStatement.setNull(i++, java.sql.Types.VARCHAR);
            }
            {
                if(oUsuario.usuario!=null) preparedStatement.setObject(i++, oUsuario.usuario,java.sql.Types.INTEGER);
                else preparedStatement.setNull(i++, java.sql.Types.INTEGER);
            }

            int insertedRows = preparedStatement.executeUpdate();

            if (insertedRows == 0) {
                throw new SQLException("No pudo insertar el registro en la tabla 'Usuario'");
            }

            if (insertedRows > 1) {
                throw new SQLException("Intentó duplicar el nuevo registro en la tabla 'Usuario'");
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

            preparedStatement = conn.prepareStatement(SQLSECUENCIA_USUARIO);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                valorSecuencia = new Long(resultSet.getLong(1));
            }
            else {
                throw new SQLException("No encontró el valor de la secuencia de 'Usuario'");
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
                throw new SQLException("No encontró el identificador del último registro ingresado en la tabla 'Usuario'");
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
