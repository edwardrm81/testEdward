
package com.edw.gen.basedatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import com.edw.gen.apigen.ExcepcionNegocio;
import com.edw.gen.basedatos.Conexion;

/* @author Edward */

public abstract class ExecuteNoTransaccional {

    protected abstract Map execute(Connection conn) throws Exception;


    public Map execute() throws Exception{
        Connection conn = null;
        try{
            conn = new Conexion().getConexion();
            conn.setAutoCommit(true);
            Map oMap = execute(conn);
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
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
