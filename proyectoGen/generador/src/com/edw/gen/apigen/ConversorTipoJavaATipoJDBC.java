package com.edw.gen.apigen;

public class ConversorTipoJavaATipoJDBC {

	public ConversorTipoJavaATipoJDBC() {
		
	}

    public int getTipoDatoJDBC(String tipoDatoJava) {
        //Esta info está en: http://java.sun.com/j2se/1.3/docs/guide/jdbc/getstart/mapping.html  (Numeral 8.9.6)
        
    	if(tipoDatoJava.equals("java.lang.Byte")) 				return  java.sql.Types.TINYINT;
    	else if(tipoDatoJava.equals("java.lang.Short")) 		return  java.sql.Types.SMALLINT;
    	else if(tipoDatoJava.equals("java.lang.Integer")) 		return  java.sql.Types.INTEGER;
    	else if(tipoDatoJava.equals("java.lang.Long")) 			return  java.sql.Types.BIGINT;
    	else if(tipoDatoJava.equals("java.lang.Float")) 		return  java.sql.Types.REAL;
    	//else if(tipoDatoJava.equals("java.lang.Double")) 		return  java.sql.Types.FLOAT;
    	else if(tipoDatoJava.equals("java.lang.Double")) 		return  java.sql.Types.DOUBLE;
        //else if(tipoDatoJava.equals("java.math.BigDecimal"))	return  java.sql.Types.DECIMAL;
    	else if(tipoDatoJava.equals("java.math.BigDecimal"))	return  java.sql.Types.NUMERIC;
    	else if(tipoDatoJava.equals("java.lang.Boolean")) 		return  java.sql.Types.BIT;
    	else if(tipoDatoJava.equals("java.lang.String")) 		return  java.sql.Types.VARCHAR;
    	//else if(tipoDatoJava.equals("java.lang.String")) 		return  java.sql.Types.CHAR;    	
    	//else if(tipoDatoJava.equals("java.lang.String")) 		return  java.sql.Types.LONGNVARCHAR;
    	//else if(tipoDatoJava.equals("java.lang.Byte")) 		return  java.sql.Types.BINARY;
    	//else if(tipoDatoJava.equals("java.lang.Byte")) 		return  java.sql.Types.VARBINARY;
    	//else if(tipoDatoJava.equals("java.lang.Byte")) 		return  java.sql.Types.LONGVARBINARY;
    	else if(tipoDatoJava.equals("java.sql.Date")) 			return  java.sql.Types.DATE;
    	else if(tipoDatoJava.equals("java.sql.Time")) 			return  java.sql.Types.TIME;
    	else if(tipoDatoJava.equals("java.sql.Timestamp")) 		return  java.sql.Types.TIMESTAMP;
    	else if(tipoDatoJava.equals("java.sql.Clob")) 			return  java.sql.Types.CLOB;
    	else if(tipoDatoJava.equals("java.sql.Blob")) 			return  java.sql.Types.BLOB;
    	else if(tipoDatoJava.equals("java.sql.Array")) 			return  java.sql.Types.ARRAY;
    	else if(tipoDatoJava.equals("java.sql.Ref")) 			return  java.sql.Types.REF;
    	else if(tipoDatoJava.equals("java.lang.Object")) 		return  java.sql.Types.STRUCT;
    	//else if(tipoDatoJava.equals("java.lang.Object")) 		return  java.sql.Types.JAVA_OBJECT;
		else return  java.sql.Types.JAVA_OBJECT;
    }
}
