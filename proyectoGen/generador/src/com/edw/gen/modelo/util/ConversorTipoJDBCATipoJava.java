package com.edw.gen.modelo.util;

public class ConversorTipoJDBCATipoJava {

	public ConversorTipoJDBCATipoJava() {
		
	}

    public String getTipoDatoJava(Integer tipoDatoJDBC) {
        //Esta info está en: http://java.sun.com/j2se/1.3/docs/guide/jdbc/getstart/mapping.html  (Numeral 8.9.6)
        String tipoDatoJava = null;
        	
        switch (tipoDatoJDBC) {
     // SELECT * FROM usuario u limit 1 <-- BUSCA UN SOLO REGISTRO
    		case java.sql.Types.TINYINT: tipoDatoJava = "java.lang.Byte"; break;
    		case java.sql.Types.SMALLINT: tipoDatoJava = "java.lang.Short"; break;
    		case java.sql.Types.INTEGER: tipoDatoJava = "java.lang.Integer"; break;
    		case java.sql.Types.BIGINT: tipoDatoJava = "java.lang.Long"; break;
    		case java.sql.Types.REAL: tipoDatoJava = "java.lang.Float"; break;
    		case java.sql.Types.FLOAT: tipoDatoJava = "java.lang.Double"; break;
    		case java.sql.Types.DOUBLE: tipoDatoJava = "java.lang.Double"; break;
    		case java.sql.Types.DECIMAL: tipoDatoJava = "java.math.BigDecimal"; break;
    		case java.sql.Types.NUMERIC: tipoDatoJava = "java.math.BigDecimal"; break;
    		case java.sql.Types.BIT: tipoDatoJava = "java.lang.Boolean"; break;
    		case java.sql.Types.CHAR: tipoDatoJava = "java.lang.String"; break;
    		case java.sql.Types.VARCHAR: tipoDatoJava = "java.lang.String"; break;
    		case java.sql.Types.LONGVARCHAR: tipoDatoJava = "java.lang.String"; break;
    		case java.sql.Types.BINARY: tipoDatoJava = "java.lang.Byte"; break;
    		case java.sql.Types.VARBINARY: tipoDatoJava = "java.lang.Byte"; break;
    		case java.sql.Types.LONGVARBINARY: tipoDatoJava = "java.lang.Byte"; break;
    		case java.sql.Types.DATE: tipoDatoJava = "java.sql.Date"; break;
    		case java.sql.Types.TIME: tipoDatoJava = "java.sql.Time"; break;
    		case java.sql.Types.TIMESTAMP: tipoDatoJava = "java.sql.Timestamp"; break;
    		case java.sql.Types.CLOB: tipoDatoJava = "java.sql.Clob"; break;
    		case java.sql.Types.BLOB: tipoDatoJava = "java.sql.Blob"; break;
    		case java.sql.Types.ARRAY: tipoDatoJava = "java.sql.Array"; break;
    		case java.sql.Types.REF: tipoDatoJava = "java.sql.Ref"; break;
    		case java.sql.Types.STRUCT: tipoDatoJava = "java.lang.Object"; break;
    		case java.sql.Types.JAVA_OBJECT: tipoDatoJava = "java.lang.Object"; break;
    		default: tipoDatoJava = "java.lang.Object"; break;
    	}
        
        return tipoDatoJava;
    }
}
