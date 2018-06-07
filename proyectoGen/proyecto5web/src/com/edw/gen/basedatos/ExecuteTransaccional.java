
package com.edw.gen.basedatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import com.edw.gen.apigen.ExcepcionNegocio;

/* @author Edward */

public abstract class ExecuteTransaccional {

    protected abstract Map execute(Connection conn) throws Exception;


    public Map execute() throws Exception{
        Connection conn = null;
        boolean commited = false;
        try{
            conn = new Conexion().getConexion();
            conn.setAutoCommit(false);
            Map oMap = execute(conn);
            conn.commit();
            commited = true;
            return oMap;
        }
        catch (ExcepcionNegocio e) {
            e.printStackTrace();
            throw e;
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
        finally {
            try {
                if (conn != null) {
                    if (!commited) { conn.rollback(); }
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
