/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edw.gen.modelo.util;

/**
 *
 * @author Edward
 */
public class Constantes {
	//FORMATO o LENGUAJE GENERACION:
    public static final String FORMATO_GENERACION_JAVA = "Java";
    public static final String FORMATO_GENERACION_PHP = "PHP";
    
    //FRAMEWORK GENERACION:
    public static final String FRAMEWORK_JSF = "JSF";
    public static final String FRAMEWORK_ZK = "ZK";
    
    //METODO CAPTURA ULTIMO ID INSERTADO:
    public static final String LAST_INSERT_ID = "LAST_INSERT_ID()"; //para MySql
    public static final String CUR_VAL = "CUR_VAL()"; // Para Oracle y PostgreSql
    public static final String MAX_ID = "MAX(ID)"; // Si no aplica ninguno de los dos anteriores
    
    //PROPIEDADES DEL SISTEMA:
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    public static final String USER_DIR = System.getProperty("user.dir");
    public static final String USER_HOME = System.getProperty("user.home");
}
