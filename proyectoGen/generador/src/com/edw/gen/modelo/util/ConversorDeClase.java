/**
 * 
 */
package com.edw.gen.modelo.util;

/**
 * @author edward
 *
 */
public class ConversorDeClase {

	/**
	 * 
	 */
	public ConversorDeClase() {
		
	}
	
	public String getCastObjeto(String nombreClaseAConvertir, String objetoAConvertir) {
		
        if(nombreClaseAConvertir.equals("java.lang.Byte")) return "(java.lang.Byte)"+objetoAConvertir;
        else if(nombreClaseAConvertir.equals("java.lang.Short")) return "new java.lang.Short("+objetoAConvertir+".toString())";
        else if(nombreClaseAConvertir.equals("java.lang.Integer")) return "new java.lang.Integer("+objetoAConvertir+".toString())";
        else if(nombreClaseAConvertir.equals("java.lang.Long")) return "new java.lang.Long("+objetoAConvertir+".toString())";
        else if(nombreClaseAConvertir.equals("java.lang.Float")) return "new java.lang.Float("+objetoAConvertir+".toString())";
        else if(nombreClaseAConvertir.equals("java.lang.Double")) return "new java.lang.Double("+objetoAConvertir+".toString())"; 
        else if(nombreClaseAConvertir.equals("java.lang.Boolean")) return "(java.lang.Boolean)"+objetoAConvertir;
        else if(nombreClaseAConvertir.equals("java.math.BigDecimal")) return "new java.math.BigDecimal("+objetoAConvertir+".toString())";
        else if(nombreClaseAConvertir.equals("java.lang.String")) return "new java.lang.String("+objetoAConvertir+".toString())";
        else if(nombreClaseAConvertir.equals("java.sql.Date")) return "(java.sql.Date)"+objetoAConvertir;//return "new java.sql.Date(new Long("+objetoAConvertir+".toString()))";
        else if(nombreClaseAConvertir.equals("java.sql.Time")) return "(java.sql.Time)"+objetoAConvertir;//return "new java.sql.Time(new Long("+objetoAConvertir+".toString()))";
        else if(nombreClaseAConvertir.equals("java.sql.Timestamp")) return "(java.sql.Timestamp)"+objetoAConvertir;
        else if(nombreClaseAConvertir.equals("java.sql.Clob")) return "(java.sql.Clob)"+objetoAConvertir;
        else if(nombreClaseAConvertir.equals("java.sql.Blob")) return "(java.sql.Blob)"+objetoAConvertir;
        else if(nombreClaseAConvertir.equals("java.sql.Array")) return "(java.sql.Array)"+objetoAConvertir;	
        else if(nombreClaseAConvertir.equals("java.sql.Ref")) return "(java.sql.Ref)"+objetoAConvertir;
        else if(nombreClaseAConvertir.equals("java.lang.Object")) return "(java.lang.Object)"+objetoAConvertir;
        else return "(java.lang.Object)"+objetoAConvertir;
	}

}
