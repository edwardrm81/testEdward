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
public class OrderBys {
	
	public List listaOrderBy;
	
	/*
	 */
	public OrderBys(OrderBy orderBy) {
		this.listaOrderBy = new ArrayList();
		this.listaOrderBy.add(orderBy);
	}

	public OrderBys(OrderBy orderBy1, OrderBy orderBy2) {
		this.listaOrderBy = new ArrayList();
		this.listaOrderBy.add(orderBy1);
		this.listaOrderBy.add(orderBy2);
	}

	public OrderBys(OrderBy orderBy1, OrderBy orderBy2, OrderBy orderBy3) {
		this.listaOrderBy = new ArrayList();
		this.listaOrderBy.add(orderBy1);
		this.listaOrderBy.add(orderBy2);
		this.listaOrderBy.add(orderBy3);
	}
	
	public OrderBys(List listaOrderBy) {
		this.listaOrderBy = listaOrderBy;
	}

}
