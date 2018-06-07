/**
 * 
 */
package com.edw.gen.apigen;

/**
 * @author edward
 *
 */
public class OrderBy {
	
	public static String ASC = "ASC";
	public static String DESC = "DESC";
	
	public String campo;
	public String sentido;
	
	/*
	 */
	public OrderBy(String campo) {
		this.campo = campo;
		this.sentido = this.ASC;
	}
	
	/*
	 */
	public OrderBy(String campo, String sentido) {
		this.campo = campo;
		this.sentido = sentido;
	}

}
