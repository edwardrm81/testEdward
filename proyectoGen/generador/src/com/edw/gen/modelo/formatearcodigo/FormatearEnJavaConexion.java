/**
 * 
 */
package com.edw.gen.modelo.formatearcodigo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.edw.gen.apigen.ExcepcionNegocio;

/**
 * @author edward
 *
 */
public class FormatearEnJavaConexion {
	
	private String nombreBaseDatos;
	private String url;
	private String driver; 
	private String login;
	private String password;
	private String paqueteConexion;
	
	public StringBuffer getFormato(String nombreBaseDatos,String url,String driver, String login, String password, String paqueteConexion) {

		StringBuffer cadena = new StringBuffer();
		
	    cadena.append("\n");
	    cadena.append("package "+paqueteConexion +";\n");
	    cadena.append("\n");
	    cadena.append("import java.sql.Connection;"+"\n");
	    cadena.append("import java.sql.DriverManager;"+"\n");
	    cadena.append("import java.sql.SQLException;"+"\n");
	    cadena.append("\n");
	    cadena.append("/* @author Edward */"+"\n");
	    cadena.append("\n");
	    cadena.append("public class Conexion {"+"\n");
	    cadena.append("\n");
	    cadena.append("    private final static String basedatos = \""+nombreBaseDatos+"\";"+"\n");
	    cadena.append("    private final static String url = \""+url+"\";"+"\n");
	    cadena.append("    private final static String driver = \""+driver+"\";"+"\n");
	    cadena.append("    private static String login = \""+login+"\";"+"\n");
	    cadena.append("    private static String password = \""+password+"\";"+"\n");
	    cadena.append("    private static Connection conn = null;"+"\n");	    
	    cadena.append("\n");
	    cadena.append("    public Connection getConexion() throws Exception{"+"\n");
	    cadena.append("        try {"+"\n");
	    cadena.append("            Class.forName(driver).newInstance();"+"\n");
	    cadena.append("            conn = DriverManager.getConnection(url,login,password);"+"\n");
	    cadena.append("            return conn;"+"\n");
	    cadena.append("        }"+"\n");
	    cadena.append("        catch(SQLException ex) {"+"\n");
	    cadena.append("            ex.printStackTrace();"+"\n");
	    cadena.append("            throw new SQLException(\"No logro conectarse a la Base de Datos \"+basedatos);"+"\n");
	    cadena.append("        }"+"\n");
	    cadena.append("        catch(Exception e) {"+"\n");
	    cadena.append("            e.printStackTrace();"+"\n");
	    cadena.append("            throw new Exception(\"No logro conectarse a la Base de Datos \"+basedatos);"+"\n");
	    cadena.append("        }"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    cadena.append("}"+"\n");
		return cadena;
	}
	

}
