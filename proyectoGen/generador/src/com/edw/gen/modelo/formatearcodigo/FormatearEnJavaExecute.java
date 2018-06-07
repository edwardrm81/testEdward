/**
 * 
 */
package com.edw.gen.modelo.formatearcodigo;

/**
 * @author edward
 *
 */
public class FormatearEnJavaExecute {
	
	private String paqueteDestino;
	
	private static boolean ES_TRANSACCIONAL = true;
	private static boolean NO_ES_TRANSACCIONAL = false;
	
	public FormatearEnJavaExecute(String paqueteDestino) {
		this.paqueteDestino = paqueteDestino;
	}
	
	public StringBuffer getFormatoExecuteTransaccional() {
		return this.getStringExecuteCreaConexion(ES_TRANSACCIONAL);
	}

	public StringBuffer getFormatoExecuteNoTransaccional() {
		return this.getStringExecuteCreaConexion(NO_ES_TRANSACCIONAL);
	}
	
	
	private StringBuffer getStringExecuteCreaConexion(boolean esTransaccional) {
		
		StringBuffer cadena = new StringBuffer();
		
	    cadena.append("\n");
	    cadena.append("package "+paqueteDestino +";\n");
	    cadena.append("\n");
	    cadena.append("import java.sql.Connection;"+"\n");
	    cadena.append("import java.sql.SQLException;"+"\n");
	    cadena.append("import java.util.Map;"+"\n");
	    cadena.append("\n");
	    cadena.append("import com.edw.gen.apigen.ExcepcionNegocio;"+"\n");
	    cadena.append("\n");
	    cadena.append("/* @author Edward */"+"\n");
	    cadena.append("\n");
	    if(!esTransaccional) cadena.append("public abstract class ExecuteNoTransaccional {"+"\n");
	    else cadena.append("public abstract class ExecuteTransaccional {"+"\n");
	    cadena.append("\n");
	    cadena.append("    protected abstract Map execute(Connection conn) throws Exception;"+"\n");
	    cadena.append("\n");
	    cadena.append("\n");
		cadena.append("    public Map execute() throws Exception{"+"\n");
		cadena.append("        Connection conn = null;"+"\n");
		if(esTransaccional) cadena.append("        boolean commited = false;"+"\n");
		cadena.append("        try{"+"\n");
		cadena.append("            conn = new Conexion().getConexion();"+"\n");
		if(esTransaccional) cadena.append("            conn.setAutoCommit(false);"+"\n");
		else cadena.append("            conn.setAutoCommit(true);"+"\n"); 
		cadena.append("            Map oMap = execute(conn);"+"\n");
		if(esTransaccional) cadena.append("            conn.commit();"+"\n");
		if(esTransaccional) cadena.append("            commited = true;"+"\n");
		cadena.append("            return oMap;"+"\n");
		cadena.append("        }"+"\n");
		cadena.append("        catch (ExcepcionNegocio e) {"+"\n");
		cadena.append("            e.printStackTrace();"+"\n");
		cadena.append("            throw e;"+"\n");
		cadena.append("        }"+"\n");
		cadena.append("        catch (SQLException e) {"+"\n");
		cadena.append("            e.printStackTrace();"+"\n");
		cadena.append("            throw new SQLException(\"Ocurrio un error de Base de Datos\");"+"\n");
		//cadena.append("            throw e;"+"\n");
		cadena.append("        }"+"\n");
		cadena.append("        catch(Exception e) {"+"\n");
		cadena.append("            e.printStackTrace();"+"\n");
		cadena.append("            throw new Exception(\"Ocurrio un error inesperado del sistema\");"+"\n");
		//cadena.append("            throw e;"+"\n");
		cadena.append("        }"+"\n");
		cadena.append("        finally {"+"\n");
		cadena.append("            try {"+"\n");
		cadena.append("                if (conn != null) {"+"\n");
		if(esTransaccional) cadena.append("                    if (!commited) { conn.rollback(); }"+"\n");
		cadena.append("                    conn.close();"+"\n");
		cadena.append("                }"+"\n");
		cadena.append("            } catch (SQLException e) {"+"\n");
		cadena.append("                e.printStackTrace();"+"\n");
		cadena.append("            }"+"\n");
		cadena.append("        }"+"\n");
		cadena.append("    }"+"\n");
	    
		cadena.append("}"+"\n");
		return cadena;
	}
	

}
