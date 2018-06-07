/**
 * 
 */
package com.edw.gen.apigen;

import java.util.ArrayList;
import java.util.List;

/**
 * @author edward
 *
 */
public class FiltroGrupo {
	
	public static String OPERADOR_AND = "AND";
	public static String OPERADOR_OR = "OR ";
	
	public List listaFiltros = new ArrayList();
	public String operadorGrupo;
	
	public FiltroGrupo() {
		
	}
	
	public FiltroGrupo(List listaFiltro) {
		this.operadorGrupo = OPERADOR_AND;
		this.listaFiltros = listaFiltro;
	}
	
	public FiltroGrupo(List listaFiltro, String operador) {
		this.operadorGrupo = operador;
		this.listaFiltros = listaFiltro;
	}
	
}
