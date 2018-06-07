/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.gco.tarjetas.conciliador.ejb.persistence.dao;

import co.com.gco.tarjetas.conciliador.ejb.common.constants.ConstantsCommons;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraConsolidadosFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraDetallesFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosConsolidadosFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosDetallesFilterDTO;

/**
 * QueryHelper
 * @author edward.rodriguez
 */
public class QueryHelper {
	
	
    /**
     * Metodo que consulta el SQL para obtener la lista de los Recaudos Consolidados segun los filtros ingresados
     * @author edward.rodriguez@ingeneo.com.co
     * @param RecaudosConsolidadosFilterDTO filterDTO
     * @return String getSqlRecaudosConsolidados
     */ 
	public static String getSqlRecaudosConsolidados(RecaudosConsolidadosFilterDTO filterDto) {
		
		StringBuffer query = new StringBuffer("");
		
		query.append("SELECT ");
		query.append("TAB.FEVOUCHER, TAB.DSNOMBRE, TAB.CDTIENDA, TAB.NMCAJA, SUM(TAB.VRPAGADO) ");
		query.append("FROM ");
		query.append("( ");
		
		query.append("	SELECT ");
		query.append("	TO_CHAR(REC.FEVOUCHER, 'YYYY-MM-DD') FEVOUCHER, TIE.DSNOMBRE DSNOMBRE, TIE.CDTIENDA CDTIENDA, REC.NMCAJA NMCAJA, REC.VRPAGADO VRPAGADO ");
		query.append("	FROM TREC_RECAUDOS REC ");
		query.append("	LEFT OUTER JOIN TTAR_TIENDA TIE ON (TIE.NMTIENDA = REC.NMTIENDA) ");
		query.append("	LEFT OUTER JOIN TTAR_RAZONSOCIAL RSO ON (RSO.NMRAZONSOCIAL = TIE.NMRAZONSOCIAL_CONCILIADORGP) ");//TIE.NMRAZONSOCIAL_CONCILIADORGP
		
		StringBuffer filters = new StringBuffer("");
		
		if(filterDto.getCdrazonsocial() != null) {
			filters.append("	RSO.CDRAZONSOCIAL = :cdrazonsocial AND ");
		}
		
		if(filterDto.getCdtienda() != null) {
			filters.append("	TIE.CDTIENDA = :cdtienda AND ");
		}
		
		if(filterDto.getFevoucherInicial() != null) {
			filters.append("	TO_CHAR(REC.FEVOUCHER, 'YYYY-MM-DD' ) >= :fevoucherInicial AND ");
		}
		
		if(filterDto.getFevoucherFinal() != null) {
			filters.append("	TO_CHAR(REC.FEVOUCHER, 'YYYY-MM-DD' ) <= :fevoucherFinal AND ");
		}
		
		if(filters.length()>0) {
			query.append("	WHERE " + filters.substring(0, filters.length()-4));
		}
		
		query.append(") TAB ");
		query.append("GROUP BY TAB.FEVOUCHER, TAB.DSNOMBRE, TAB.CDTIENDA, TAB.NMCAJA ");
		query.append("ORDER BY TAB.DSNOMBRE, TAB.NMCAJA, TAB.FEVOUCHER DESC ");
		
		return query.toString();
	}

	
    /**
     * Metodo que consulta el SQL para obtener la lista de los Detalle de Recaudos segun los filtros ingresados
     * @author edward.rodriguez@ingeneo.com.co
     * @param RecaudosDetallesFilterDTO filterDTO
     * @return String getSqlRecaudosDetalles
     */    
	public static String getSqlRecaudosDetalles(RecaudosDetallesFilterDTO filterDto) {
		
		StringBuffer query = new StringBuffer("");
		
		query.append("SELECT ");
		query.append("REC.VRPAGADO, REC.NMVOUCHERPAGO, TAR.CDDOCUMENTO, TAR.DSPAPELLIDO || ' ' || TAR.DSSAPELLIDO || ' ' || TAR.DSPNOMBRE || ' ' || TAR.DSSNOMBRE ");
		query.append("FROM ");
		query.append("TREC_RECAUDOS REC ");
		query.append("LEFT OUTER JOIN TTAR_TIENDA TIE ON (TIE.NMTIENDA = REC.NMTIENDA) ");
		query.append("LEFT OUTER JOIN TTAR_TARJETAS TAR ON (TAR.NMCUENTA = REC.NMCONTRATO) ");
		
		StringBuffer filters = new StringBuffer("");
		
		if(filterDto.getCdtienda() != null) {
			filters.append("TIE.CDTIENDA = :cdtienda AND ");
		}
		
		if(filterDto.getFevoucher() != null) {
			filters.append("TO_CHAR(REC.FEVOUCHER, 'YYYY-MM-DD' ) = :fevoucher AND ");
		}
		
		if(filterDto.getNmcaja() != null) {
			filters.append("REC.NMCAJA = :nmcaja AND ");
		}
		
		if(filters.length()>0) {
			query.append("	WHERE " + filters.substring(0, filters.length()-4));
		}
		
		query.append("ORDER BY TAR.CDDOCUMENTO ");
		
		return query.toString();
	}
	
	
    /**
     * Metodo que consulta el SQL para obtener la lista de las Primeras Compras Consolidadas segun los filtros ingresados
     * @author edward.rodriguez@ingeneo.com.co
     * @param PrimeraCompraConsolidadosFilterDTO filterDTO
     * @return String getSqlPrimeraCompraConsolidados
     */    
	public static String getSqlPrimeraCompraConsolidados(PrimeraCompraConsolidadosFilterDTO filterDto) {
		
		StringBuffer query = new StringBuffer("");
		
		query.append("SELECT ");
		query.append("TAB.FEAPROBACION, TAB.DSNOMBRE, TAB.CDTIENDA, TAB.NMCAJA, SUM(TAB.NMMONTO) ");
		query.append("FROM ");
		query.append("( ");
		
		query.append("	SELECT ");
		query.append("	TO_CHAR(APR.FEAPROBACION, 'YYYY-MM-DD') FEAPROBACION, TIE.DSNOMBRE DSNOMBRE, TIE.CDTIENDA CDTIENDA, APR.NMCAJA NMCAJA, APR.NMMONTO NMMONTO ");
		query.append("	FROM "+ConstantsCommons.SCHEMA_TARJETAS+".TTAR_APROBACION APR ");
		query.append("	LEFT OUTER JOIN TTAR_TIENDA TIE ON (TIE.CDTIENDA = APR.NMTIENDA) ");
		query.append("	LEFT OUTER JOIN TTAR_RAZONSOCIAL RSO ON (RSO.NMRAZONSOCIAL = TIE.NMRAZONSOCIAL_CONCILIADORGP) ");//TIE.NMRAZONSOCIAL_CONCILIADORGP
		
		StringBuffer filters = new StringBuffer("");

		filters.append("	APR.SNAPROBADO = :snaprobado AND ");
		filters.append("	APR.NMESTADO = :nmestado AND ");
		
		if(filterDto.getCdrazonsocial() != null) {
			filters.append("	RSO.CDRAZONSOCIAL = :cdrazonsocial AND ");
		}
		
		if(filterDto.getCdtienda() != null) {
			filters.append("	TIE.CDTIENDA = :cdtienda AND ");
		}
		
		if(filterDto.getFeaprobacionInicial() != null) {
			filters.append("	TO_CHAR(APR.FEAPROBACION, 'YYYY-MM-DD' ) >= :feaprobacionInicial AND ");
		}
		
		if(filterDto.getFeaprobacionFinal() != null) {
			filters.append("	TO_CHAR(APR.FEAPROBACION, 'YYYY-MM-DD' ) <= :feaprobacionFinal AND ");
		}
		
		if(filters.length()>0) {
			query.append("	WHERE " + filters.substring(0, filters.length()-4));
		}
		
		query.append(") TAB ");
		query.append("GROUP BY TAB.FEAPROBACION, TAB.DSNOMBRE, TAB.CDTIENDA, TAB.NMCAJA ");
		query.append("ORDER BY TAB.DSNOMBRE, TAB.NMCAJA, TAB.FEAPROBACION DESC ");
		
		return query.toString();
	}
	
    /**
     * Metodo que consulta la lista de los Detalles de Primeras Compras segun los filtros ingresados
     * @author edward.rodriguez@ingeneo.com.co
     * @param PrimeraCompraDetallesFilterDTO filterDTO
     * @return String getSqlPrimeraCompraDetalles
     */ 
	public static String getSqlPrimeraCompraDetalles(PrimeraCompraDetallesFilterDTO filterDto) {
		
		StringBuffer query = new StringBuffer("");
		
		query.append("SELECT ");
		query.append("APR.NMMONTO, APR.NMTICKET, APR.CDBARRAS, CLI.CDDOCUMENTO, CLI.DSPAPELLIDO || ' ' || CLI.DSSAPELLIDO  || ' ' || CLI.DSPNOMBRE  || ' ' || CLI.DSSNOMBRE ");
		query.append("FROM ");
		query.append(""+ConstantsCommons.SCHEMA_TARJETAS+".TTAR_APROBACION APR ");
		query.append("LEFT OUTER JOIN TTAR_TIENDA TIE ON (TIE.CDTIENDA = APR.NMTIENDA) ");
		query.append("LEFT OUTER JOIN "+ConstantsCommons.SCHEMA_TARJETAS+".TTAR_CLIENTES CLI ON (CLI.NMCLIENTE = APR.NMCLIENTE) ");
		
		StringBuffer filters = new StringBuffer("");
		
		filters.append("APR.SNAPROBADO = :snaprobado AND ");
		filters.append("APR.NMESTADO = :nmestado AND ");
		
		if(filterDto.getCdtienda() != null) {
			filters.append("TIE.CDTIENDA = :cdtienda AND ");
		}
		
		if(filterDto.getFeaprobacion() != null) {
			filters.append("TO_CHAR(APR.FEAPROBACION, 'YYYY-MM-DD' ) = :feaprobacion AND ");
		}
		
		if(filterDto.getNmcaja() != null) {
			filters.append("APR.NMCAJA = :nmcaja AND ");
		}
		
		if(filters.length()>0) {
			query.append("	WHERE " + filters.substring(0, filters.length()-4));
		}
		
		query.append("ORDER BY CLI.CDDOCUMENTO ");
		
		return query.toString();
	}
	
}