
package com.edw.gen.modelo.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* 
 * @author Edward y http://xyzdev.blogspot.com/2009/01/ordenar-listas-en-java.html
 * @description Ordena una lista de Beans o Maps para las propiedades que se le indican
 */
public class OrdenarListaDeObjetos {

	public OrdenarListaDeObjetos() {
		
	}
	
	public void ordenarPorPropiedades(List listaObjetos, String propiedad1) {
		List listaPropiedades = new ArrayList();
		listaPropiedades.add(propiedad1);
		
		Collections.sort(listaObjetos,new Comparacion(listaPropiedades));
	}

	public void ordenarPorPropiedades(List listaObjetos, String propiedad1, String propiedad2) {
		List listaPropiedades = new ArrayList();
		listaPropiedades.add(propiedad1);
		listaPropiedades.add(propiedad2);
		
		Collections.sort(listaObjetos,new Comparacion(listaPropiedades));
	}

	public void ordenarPorPropiedades(List listaObjetos, String propiedad1, String propiedad2, String propiedad3) {
		List listaPropiedades = new ArrayList();
		listaPropiedades.add(propiedad1);
		listaPropiedades.add(propiedad2);
		listaPropiedades.add(propiedad3);
		
		Collections.sort(listaObjetos,new Comparacion(listaPropiedades));
	}
	
	public void ordenarPorPropiedades(List listaObjetos, List listaPropiedades) {		
		Collections.sort(listaObjetos,new Comparacion(listaPropiedades));
	}
	
	/**************************************************************************************************/
	
	public class Comparacion implements Comparator {
			
		private List listaPropiedades = new ArrayList();
		private Object [] objeto1Propiedad;
		private Object [] objeto2Propiedad;
		
		public Comparacion(List listaPropiedades) {
			this.listaPropiedades = listaPropiedades;
		}
		
		public int compare(Object obj1, Object obj2) {
			
			objeto1Propiedad = new Object[listaPropiedades.size()];
			objeto2Propiedad = new Object[listaPropiedades.size()];
			
			Class claseObjeto = obj1.getClass();
			if(claseObjeto.getName().equals("java.util.HashMap") || claseObjeto.getName().equals("java.util.LinkedHashMap")) {
				
				for (int i=0; i<listaPropiedades.size(); i++) {
					objeto1Propiedad[i] = ((Map) obj1).get((String)listaPropiedades.get(i));				
					objeto2Propiedad[i] = ((Map) obj2).get((String)listaPropiedades.get(i));
				}
			}
			else {
				
				try {
					String [] getters = new String[listaPropiedades.size()];
					for (int i=0; i<listaPropiedades.size(); i++) {
						
						getters[i] = "get"+Character.toUpperCase(((String)listaPropiedades.get(0)).charAt(0)) + ((String)listaPropiedades.get(0)).substring(1);
						Method getPropiedad = claseObjeto.getMethod(getters[i]);
						objeto1Propiedad[i] = getPropiedad;
						objeto2Propiedad[i] = getPropiedad;						
					}
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			return this.comparacionRecursiva(0);
		}
		
		private int comparacionRecursiva(int iteracion) {
			
			if(objeto1Propiedad[iteracion]==null && objeto2Propiedad[iteracion]==null) {
				objeto1Propiedad[iteracion] = "";
				objeto2Propiedad[iteracion] = "";
			}
			else if(objeto1Propiedad[iteracion]==null) return 1;
			else if(objeto2Propiedad[iteracion]==null) return 0;
			
			Comparable objetoComparable1 = (Comparable)objeto1Propiedad[iteracion];
			Comparable objetoComparable2 = (Comparable)objeto2Propiedad[iteracion];

			
			if(objetoComparable1.compareTo(objetoComparable2)==0) {
				if(iteracion+1 >= objeto1Propiedad.length) return 0;
				else return this.comparacionRecursiva(++iteracion);
			}
			else return objetoComparable1.compareTo(objetoComparable2);
		}
		
	}

}

