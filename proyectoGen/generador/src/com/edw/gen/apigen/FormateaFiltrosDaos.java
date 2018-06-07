/**
 * 
 */
package com.edw.gen.apigen;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author edward
 *
 */
public class FormateaFiltrosDaos {
	
	private List listaFiltrosGrupos;	
	
	private StringBuffer sqlWhere = new StringBuffer("");
	private List listaObjetosCampos = new ArrayList();
	
	private static String Nombre_Clase_Filtro = "Filtro";
	
		
	public FormateaFiltrosDaos() {
		
	}
	
	public Map getFiltros(List listaFiltrosGrupo) throws Exception {

		listaFiltrosGrupos = new ArrayList();
		
		sqlWhere = new StringBuffer("");
		listaObjetosCampos = new ArrayList();
		
		if(listaFiltrosGrupo!=null && listaFiltrosGrupo.size()>0) {
			this.listaFiltrosGrupos = listaFiltrosGrupo;
			this.getFiltrosRecursivo(new FiltroGrupo(this.listaFiltrosGrupos), "");
		}
		
		Map oMapReturn = new HashMap();
		oMapReturn.put("sqlWhereFiltros", sqlWhere.toString());
		oMapReturn.put("listaObjetosCamposFiltros",listaObjetosCampos);
    	return oMapReturn;
	}
	
	private void getFiltrosRecursivo(FiltroGrupo oFiltroGrupo, String operadorGrupoTrasAnterior) {
		if(oFiltroGrupo.listaFiltros.size()>0) sqlWhere.append(" (");
		for(int i=0; i< oFiltroGrupo.listaFiltros.size(); i++) {
			FiltroGrupo oFiltroGrupoSiguiente = new FiltroGrupo();
			
			if(oFiltroGrupo.listaFiltros.get(i).getClass().getSimpleName().equals(Nombre_Clase_Filtro)) {
				Filtro oFiltro = (Filtro) oFiltroGrupo.listaFiltros.get(i);
				if(oFiltro.operador.equals(new Filtro().getFiltroEnString(Filtro.OPERADOR_BETWEEN))) sqlWhere.append(" "+oFiltro.campo+" "+oFiltro.operador+" "+"? AND ?"+" "+oFiltroGrupo.operadorGrupo);
				else sqlWhere.append(" "+oFiltro.campo+" "+oFiltro.operador+" "+(oFiltro.operador.equals(new Filtro().getFiltroEnString(Filtro.OPERADOR_IS_NULL)) || oFiltro.operador.equals(new Filtro().getFiltroEnString(Filtro.OPERADOR_IS_NOT_NULL))? "":"?")+" "+oFiltroGrupo.operadorGrupo);
				
				if(oFiltro.operador.equals(new Filtro().getFiltroEnString(Filtro.OPERADOR_BETWEEN))) {
					listaObjetosCampos.add(oFiltro.valor1);
					listaObjetosCampos.add(oFiltro.valor2);
				}
				else if(!oFiltro.operador.equals(new Filtro().getFiltroEnString(Filtro.OPERADOR_IS_NULL)) && !oFiltro.operador.equals(new Filtro().getFiltroEnString(Filtro.OPERADOR_IS_NOT_NULL))) {
					listaObjetosCampos.add(oFiltro.valor1);
				}
			}
			else {
				oFiltroGrupoSiguiente = (FiltroGrupo) oFiltroGrupo.listaFiltros.get(i);
			}
			
			////////////////////////////////
			if(oFiltroGrupoSiguiente.listaFiltros.size()>0) sqlWhere.append(" (");
			for(int j=0; j<oFiltroGrupoSiguiente.listaFiltros.size(); j++) {
				FiltroGrupo oFiltroGrupoSiguiente2 = new FiltroGrupo();
				
				if(oFiltroGrupoSiguiente.listaFiltros.get(j).getClass().getSimpleName().equals(Nombre_Clase_Filtro)) {
					Filtro oFiltro2 = (Filtro) oFiltroGrupoSiguiente.listaFiltros.get(j);
					if(oFiltro2.operador.equals(new Filtro().getFiltroEnString(Filtro.OPERADOR_BETWEEN))) sqlWhere.append(" "+oFiltro2.campo+" "+oFiltro2.operador+" "+"? AND ?"+" "+oFiltroGrupoSiguiente.operadorGrupo);
					else sqlWhere.append(" "+oFiltro2.campo+" "+oFiltro2.operador+" "+(oFiltro2.operador.equals(new Filtro().getFiltroEnString(Filtro.OPERADOR_IS_NULL)) || oFiltro2.operador.equals(new Filtro().getFiltroEnString(Filtro.OPERADOR_IS_NOT_NULL))? "":"?")+" "+oFiltroGrupoSiguiente.operadorGrupo);
					
					if(oFiltro2.operador.equals(new Filtro().getFiltroEnString(Filtro.OPERADOR_BETWEEN))) {
						listaObjetosCampos.add(oFiltro2.valor1);
						listaObjetosCampos.add(oFiltro2.valor2);
					}
					else if(!oFiltro2.operador.equals(new Filtro().getFiltroEnString(Filtro.OPERADOR_IS_NULL)) && !oFiltro2.operador.equals(new Filtro().getFiltroEnString(Filtro.OPERADOR_IS_NOT_NULL))) {
						listaObjetosCampos.add(oFiltro2.valor1);
					}
				}
				else {
					oFiltroGrupoSiguiente2 = (FiltroGrupo) oFiltroGrupoSiguiente.listaFiltros.get(j);
				}
				
				this.getFiltrosRecursivo(oFiltroGrupoSiguiente2, oFiltroGrupoSiguiente.operadorGrupo);
			}
			if(oFiltroGrupoSiguiente.listaFiltros.size()>0) {
				sqlWhere.delete(sqlWhere.length()-3, sqlWhere.length());
				sqlWhere.append(")"+" "+oFiltroGrupo.operadorGrupo);
			}
			//////////////////////////////
		}
		
		if(oFiltroGrupo.listaFiltros.size()>0) {
			sqlWhere.delete(sqlWhere.length()-3, sqlWhere.length());
			sqlWhere.append(")"+" "+operadorGrupoTrasAnterior);
		}

	}
	
}
