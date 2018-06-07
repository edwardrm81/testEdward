/**
 * 
 */
package com.edw.gen.apigen;

/**
 * @author edward
 *
 */
public class Filtro {

	public static int OPERADOR_IGUAL = 1;				private static String OPERADOR_IGUAL_STRING = "=";
	public static int OPERADOR_MAYOR_IGUAL = 2;			private static String OPERADOR_MAYOR_IGUAL_STRING = ">=";
	public static int OPERADOR_MAYOR = 3;				private static String OPERADOR_MAYOR_STRING = ">";
	public static int OPERADOR_MENOR_IGUAL = 4;			private static String OPERADOR_MENOR_IGUAL_STRING = "<=";
	public static int OPERADOR_MENOR = 5;				private static String OPERADOR_MENOR_STRING = "<";
	public static int OPERADOR_DIFERENTE = 6;			private static String OPERADOR_DIFERENTE_STRING = "<>";
	public static int OPERADOR_IS_NULL = 7;				private static String OPERADOR_IS_NULL_STRING = "IS NULL";
	public static int OPERADOR_IS_NOT_NULL = 8;			private static String OPERADOR_IS_NOT_NULL_STRING = "IS NOT NULL";
	public static int OPERADOR_LIKE = 9;				private static String OPERADOR_LIKE_STRING = "LIKE";
	public static int OPERADOR_BETWEEN = 10;			private static String OPERADOR_BETWEEN_STRING = "BETWEEN";
	
	
	public String campo;
	public Object valor1;
	public Object valor2;
	public String operador;
	
	public Filtro() {
		
	}
	
	public String getFiltroEnString (int codigoOperador) {
		if(codigoOperador==OPERADOR_IGUAL) 				return OPERADOR_IGUAL_STRING;			
		else if(codigoOperador==OPERADOR_MAYOR_IGUAL) 	return OPERADOR_MAYOR_IGUAL_STRING;
		else if(codigoOperador==OPERADOR_MAYOR) 		return OPERADOR_MAYOR_STRING;
		else if(codigoOperador==OPERADOR_MENOR_IGUAL) 	return OPERADOR_MENOR_IGUAL_STRING;
		else if(codigoOperador==OPERADOR_MENOR) 		return OPERADOR_MENOR_STRING;
		else if(codigoOperador==OPERADOR_DIFERENTE) 	return OPERADOR_DIFERENTE_STRING;
		else if(codigoOperador==OPERADOR_IS_NULL) 		return OPERADOR_IS_NULL_STRING;
		else if(codigoOperador==OPERADOR_IS_NOT_NULL) 	return OPERADOR_IS_NOT_NULL_STRING;
		else if(codigoOperador==OPERADOR_LIKE) 			return OPERADOR_LIKE_STRING;
		else if(codigoOperador==OPERADOR_BETWEEN) 		return OPERADOR_BETWEEN_STRING;
		else 											return ""; 
	}
	
	public Filtro(String campo, Object valor) {
		this.campo = campo;
		this.valor1 = valor;
		this.operador = OPERADOR_IGUAL_STRING;
	}
	
	public Filtro(String campo, Object valor, int operador) {
		this.campo = campo;
		this.valor1 = valor;
		this.operador = this.getFiltroEnString(operador);
	}
	
	public Filtro(String campo, int operador) {
		this.campo = campo;
		this.valor1 = null;
		this.operador = this.getFiltroEnString(operador);
	}
	
	public Filtro(String campo, Object valor1, Object valor2, int operador) {
		this.campo = campo;
		this.valor1 = valor1;
		this.valor2 = valor2;
		this.operador = this.getFiltroEnString(operador);
	}	

}
