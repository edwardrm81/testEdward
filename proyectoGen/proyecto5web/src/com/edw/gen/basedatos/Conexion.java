
package com.edw.gen.basedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* @author Edward */

public class Conexion {

    private final static String basedatos = "bdproyecto1";
    private final static String url = "jdbc:mysql://localhost:3306/bdproyecto1";
    private final static String driver = "com.mysql.jdbc.Driver";
    private static String login = "admin";
    private static String password = "admin";
    private static Connection conn = null;

    public Connection getConexion() throws Exception{
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url,login,password);
            return conn;
        }
        catch(SQLException ex) {
            ex.printStackTrace();
            throw new SQLException("No logró conectarse a la Base de Datos "+basedatos);
        }
        catch(Exception e) {
            e.printStackTrace();
            throw new Exception("No logró conectarse a la Base de Datos "+basedatos);
        }
    }

}
