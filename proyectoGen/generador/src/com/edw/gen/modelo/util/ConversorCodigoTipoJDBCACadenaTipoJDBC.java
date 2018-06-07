/**
 * 
 */
package com.edw.gen.modelo.util;

/**
 * @author edward
 *
 */
public class ConversorCodigoTipoJDBCACadenaTipoJDBC {

	/**
	 * 
	 */
	public ConversorCodigoTipoJDBCACadenaTipoJDBC() {
		
	}

    public String getCadenaTipoDatoJDBC(Integer tipoDatoJDBC) {
        //Esta info está en: http://java.sun.com/j2se/1.3/docs/guide/jdbc/getstart/mapping.html  (Numeral 8.9.6)
        String tipoDatoJDBCEnCadena = null;
        	
        switch (tipoDatoJDBC) {
        	
    		case java.sql.Types.TINYINT: tipoDatoJDBCEnCadena = "java.sql.Types.TINYINT"; break;
    		case java.sql.Types.SMALLINT: tipoDatoJDBCEnCadena = "java.sql.Types.SMALLINT"; break;
    		case java.sql.Types.INTEGER: tipoDatoJDBCEnCadena = "java.sql.Types.INTEGER"; break;
    		case java.sql.Types.BIGINT: tipoDatoJDBCEnCadena = "java.sql.Types.BIGINT"; break;
    		case java.sql.Types.REAL: tipoDatoJDBCEnCadena = "java.sql.Types.REAL"; break;
    		case java.sql.Types.FLOAT: tipoDatoJDBCEnCadena = "java.sql.Types.FLOAT"; break;
    		case java.sql.Types.DOUBLE: tipoDatoJDBCEnCadena = "java.sql.Types.DOUBLE"; break;
    		case java.sql.Types.DECIMAL: tipoDatoJDBCEnCadena = "java.sql.Types.DECIMAL"; break;
    		case java.sql.Types.NUMERIC: tipoDatoJDBCEnCadena = "java.sql.Types.NUMERIC"; break;
    		case java.sql.Types.BIT: tipoDatoJDBCEnCadena = "java.sql.Types.BIT"; break;
    		case java.sql.Types.CHAR: tipoDatoJDBCEnCadena = "java.sql.Types.CHAR"; break;
    		case java.sql.Types.VARCHAR: tipoDatoJDBCEnCadena = "java.sql.Types.VARCHAR"; break;
    		case java.sql.Types.LONGVARCHAR: tipoDatoJDBCEnCadena = "java.sql.Types.LONGVARCHAR"; break;
    		case java.sql.Types.BINARY: tipoDatoJDBCEnCadena = "java.sql.Types.BINARY"; break;
    		case java.sql.Types.VARBINARY: tipoDatoJDBCEnCadena = "java.sql.Types.VARBINARY"; break;
    		case java.sql.Types.LONGVARBINARY: tipoDatoJDBCEnCadena = "java.sql.Types.LONGVARBINARY"; break;
    		case java.sql.Types.DATE: tipoDatoJDBCEnCadena = "java.sql.Types.DATE"; break;
    		case java.sql.Types.TIME: tipoDatoJDBCEnCadena = "java.sql.Types.TIME"; break;
    		case java.sql.Types.TIMESTAMP: tipoDatoJDBCEnCadena = "java.sql.Types.TIMESTAMP"; break;
    		case java.sql.Types.CLOB: tipoDatoJDBCEnCadena = "java.sql.Types.CLOB"; break;
    		case java.sql.Types.BLOB: tipoDatoJDBCEnCadena = "java.sql.Types.BLOB"; break;
    		case java.sql.Types.ARRAY: tipoDatoJDBCEnCadena = "java.sql.Types.ARRAY"; break;
    		case java.sql.Types.REF: tipoDatoJDBCEnCadena = "java.sql.Types.REF"; break;
    		case java.sql.Types.STRUCT: tipoDatoJDBCEnCadena = "java.sql.Types.STRUCT"; break;
    		case java.sql.Types.JAVA_OBJECT: tipoDatoJDBCEnCadena = "java.sql.Types.JAVA_OBJECT"; break;
    		default: tipoDatoJDBCEnCadena = "java.sql.Types.JAVA_OBJECT"; break;
    			
    	}
     
        return tipoDatoJDBCEnCadena;
    }

	
}
