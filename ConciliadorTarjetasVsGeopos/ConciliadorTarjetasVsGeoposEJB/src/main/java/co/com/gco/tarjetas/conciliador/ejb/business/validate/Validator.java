/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.gco.tarjetas.conciliador.ejb.business.validate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import co.com.gco.tarjetas.conciliador.ejb.common.constants.EnumsCommons;
import co.com.gco.tarjetas.conciliador.ejb.common.constants.MessageCodesI18N;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraConsolidadosDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraConsolidadosDiffDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraConsolidadosDiffFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraDetallesFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosConsolidadosDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosConsolidadosDiffDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosConsolidadosDiffFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosDetallesFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.geopos.GeoposRestConsolidadosFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.geopos.GeoposRestPrimeraCompraConsolidadosDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.geopos.GeoposRestRecaudosConsolidadosDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.exception.ExceptionBusiness;
import co.com.gco.tarjetas.conciliador.ejb.common.util.PropertiesLoad;
import co.com.gco.tarjetas.conciliador.ejb.common.util.UtilDate;

/**
 * Validator
 * @author edward.rodriguez@ingeneo.com.co
 */
public class Validator {
	
    /**
     * Metodo que valida el filtro RecaudosConsolidadosDiffFilterDTO
     * @author edward.rodriguez@ingeneo.com.co
     * @param RecaudosConsolidadosDiffFilterDTO filterDTO
     * @throws ExceptionBusiness
     */  
    public static void isValidInputGetRecaudosConsolidadosDiff(RecaudosConsolidadosDiffFilterDTO filterDTO) throws ExceptionBusiness {
    	
        if(filterDTO==null) {
            throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_INVALID_INPUT_FILTERS));
        }
        
    	Date fechaInicial = UtilDate.getDate(EnumsCommons.SEPARATOR_FORMAT_DATE.getValue(), filterDTO.getFevoucherInicial());
    	Date fechaFinal = UtilDate.getDate(EnumsCommons.SEPARATOR_FORMAT_DATE.getValue(), filterDTO.getFevoucherFinal());
    	
        if(fechaInicial==null || fechaFinal==null) {
            throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_INVALID_RANGE_DATES));
        }
        
        if(fechaFinal.compareTo(fechaInicial)<0) {
            throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_FINAL_DATE_LT_START_DATE));
        }
        
        if(UtilDate.getNumberDaysTwoDates(fechaInicial, fechaFinal) > Integer.parseInt(EnumsCommons.MAX_NUMBER_DAYS_RANGE_DATES.getValue())) {
            throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_MAX_NUMBER_DAYS_RANGE_DATES));
        }
        
    }
    
    /**
     * Metodo que valida el filtro RecaudosDetallesFilterDTO
     * @author edward.rodriguez@ingeneo.com.co
     * @param RecaudosDetallesFilterDTO filterDTO
     * @throws ExceptionBusiness
     */
    public static void isValidInputGetRecaudosDetalles(RecaudosDetallesFilterDTO filterDTO) throws ExceptionBusiness {
    	
        if(filterDTO==null) {
            throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_INVALID_INPUT_FILTERS));
        }
        
        Date fecha = UtilDate.getDate(EnumsCommons.SEPARATOR_FORMAT_DATE.getValue(), filterDTO.getFevoucher());
        
        if(fecha==null) {
            throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_INVALID_DATE));
        }
    }
    
    /**
     * Metodo que valida el filtro PrimeraCompraConsolidadosDiffFilterDTO 
     * @author edward.rodriguez@ingeneo.com.co
     * @param PrimeraCompraConsolidadosDiffFilterDTO  filterDTO
     * @throws ExceptionBusiness
     */  
    public static void isValidInputGetPrimeraCompraConsolidadosDiff(PrimeraCompraConsolidadosDiffFilterDTO filterDTO) throws ExceptionBusiness {
    	
        if(filterDTO==null) {
            throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_INVALID_INPUT_FILTERS));
        }
        
    	Date fechaInicial = UtilDate.getDate(EnumsCommons.SEPARATOR_FORMAT_DATE.getValue(), filterDTO.getFeaprobacionInicial());
    	Date fechaFinal = UtilDate.getDate(EnumsCommons.SEPARATOR_FORMAT_DATE.getValue(), filterDTO.getFeaprobacionFinal());
    	
        if(fechaInicial==null || fechaFinal==null) {
            throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_INVALID_RANGE_DATES));
        }
        
        if(fechaFinal.compareTo(fechaInicial)<0) {
            throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_FINAL_DATE_LT_START_DATE));
        }
        
        if(UtilDate.getNumberDaysTwoDates(fechaInicial, fechaFinal) > Integer.parseInt(EnumsCommons.MAX_NUMBER_DAYS_RANGE_DATES.getValue())) {
            throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_MAX_NUMBER_DAYS_RANGE_DATES));
        }
        
    }
    
    /**
     * Metodo que valida el filtro PrimeraCompraDetallesFilterDTO
     * @author edward.rodriguez@ingeneo.com.co
     * @param PrimeraCompraDetallesFilterDTO filterDTO
     * @throws ExceptionBusiness
     */  
    public static void isValidInputGetPrimeraCompraDetalles(PrimeraCompraDetallesFilterDTO filterDTO) throws ExceptionBusiness {
    	
        if(filterDTO==null) {
            throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_INVALID_INPUT_FILTERS));
        }
        
        Date fecha = UtilDate.getDate(EnumsCommons.SEPARATOR_FORMAT_DATE.getValue(), filterDTO.getFeaprobacion());
        
        if(fecha==null) {
            throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_INVALID_DATE));
        }
        
    }

    /**
     * Metodo que valida el filtro GeoposRestConsolidadosFilterDTO
     * @author edward.rodriguez@ingeneo.com.co
     * @param GeoposRestConsolidadosFilterDTO filterDTO
     * @throws ExceptionBusiness
     */  
    public static void isValidInputGetGeoposRestConsolidados(GeoposRestConsolidadosFilterDTO filterDTO) throws ExceptionBusiness {
        
    	if(filterDTO==null) {
            throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_INVALID_INPUT_GEOPOS_REST_FILTERS));
        }
    	
	}
    
    /**
     * Metodo que valida los parametros de entrada de isValidInputRecaudosConsolidadosMatch
     * @author edward.rodriguez@ingeneo.com.co
     * @param RecaudosConsolidadosDiffFilterDTO filterDTO
     * @param List<GeoposRestRecaudosConsolidadosDTO> geoposRestRecaudosConsolidadosDTOList
     * @param List<RecaudosConsolidadosDTO> recaudosConsolidadosDTOList
     * @throws ExceptionBusiness
     */  
    public static void isValidInputRecaudosConsolidadosMatch(RecaudosConsolidadosDiffFilterDTO filterDTO, List<GeoposRestRecaudosConsolidadosDTO> geoposRestRecaudosConsolidadosDTOList, List<RecaudosConsolidadosDTO> recaudosConsolidadosDTOList) throws ExceptionBusiness {
        
    	if(geoposRestRecaudosConsolidadosDTOList==null) {
            throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_INVALID_INPUT_MATCH_GEOPOS_LIST_NULL));
        }

    	if(recaudosConsolidadosDTOList==null) {
            throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_INVALID_INPUT_MATCH_TARJETAS_LIST_NULL));
        }
    	
    	if(filterDTO==null) {
    		throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_INVALID_INPUT_FILTERS));
    	}
    	
    	
	}
    
    /** 
     * Metodo que valida los parametros de entrada de isValidInputMatchPrimeraCompraConsolidados
     * @author edward.rodriguez@ingeneo.com.co
     * @param PrimeraCompraConsolidadosDiffFilterDTO filterDTO
     * @param List<GeoposRestPrimeraCompraConsolidadosDTO> geoposRestPrimeraCompraConsolidadosDTOList
     * @param List<PrimeraCompraConsolidadosDTO> primeraCompraConsolidadosDTOList
     * @throws ExceptionBusiness
     */  
    public static void isValidInputPrimeraCompraConsolidadosMatch(PrimeraCompraConsolidadosDiffFilterDTO filterDTO, List<GeoposRestPrimeraCompraConsolidadosDTO> geoposRestPrimeraCompraConsolidadosDTOList, List<PrimeraCompraConsolidadosDTO> primeraCompraConsolidadosDTOList) throws ExceptionBusiness {
        
    	if(geoposRestPrimeraCompraConsolidadosDTOList==null) {
            throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_INVALID_INPUT_MATCH_GEOPOS_LIST_NULL));
        }

    	if(primeraCompraConsolidadosDTOList==null) {
            throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_INVALID_INPUT_MATCH_TARJETAS_LIST_NULL));
        }
    	
    	if(filterDTO==null) {
    		throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_INVALID_INPUT_FILTERS));
    	}
    	
    	
	}        
    
    /** 
     * Metodo que valida los parametros de entrada de isValidInputSaveAuditRecordsRecaudosConsolidadosDiff
     * @author edward.rodriguez@ingeneo.com.co
     * @param RecaudosConsolidadosDiffFilterDTO filterDTO
     * @param List<RecaudosConsolidadosDiffDTO> recaudosConsolidadosDiffDTOList
     * @throws ExceptionBusiness
     */  
    public static void isValidInputSaveAuditRecordsRecaudosConsolidadosDiff(RecaudosConsolidadosDiffFilterDTO filterDTO, List<RecaudosConsolidadosDiffDTO> recaudosConsolidadosDiffDTOList) throws ExceptionBusiness {

    	if(filterDTO==null) {
            throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_INVALID_INPUT_FILTERS));
        }
    	
    	if(recaudosConsolidadosDiffDTOList==null) {
            throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_INVALID_INPUT_LIST_TO_AUDIT_IS_NULL));
        }
    	
	}
    
    /** 
     * Metodo que valida los parametros de entrada de isValidInputSaveAuditRecordsPrimeraCompraConsolidadosDiff
     * @author edward.rodriguez@ingeneo.com.co
     * @param PrimeraCompraConsolidadosDiffFilterDTO filterDTO
     * @param List<PrimeraCompraConsolidadosDiffDTO> primeraCompraConsolidadosDiffDTOList
     * @throws ExceptionBusiness
     */  
    public static void isValidInputSaveAuditRecordsPrimeraCompraConsolidadosDiff(PrimeraCompraConsolidadosDiffFilterDTO filterDTO, List<PrimeraCompraConsolidadosDiffDTO> primeraCompraConsolidadosDiffDTOList) throws ExceptionBusiness {
        
    	if(filterDTO==null) {
            throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_INVALID_INPUT_FILTERS));
        }
    	
    	if(primeraCompraConsolidadosDiffDTOList==null) {
            throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_INVALID_INPUT_LIST_TO_AUDIT_IS_NULL));
        }
    	
	}
    
    /** 
     * Metodo que valida los parametros de entrada de isValidInputGetTtarTiendaList
     * @author edward.rodriguez@ingeneo.com.co
     * @param String nmrazonsocialConciliadorGP
     * @throws ExceptionBusiness
     */  
    public static void isValidInputGetTtarTiendaList(String nmrazonsocialConciliadorGP) throws ExceptionBusiness {
        
    	if(nmrazonsocialConciliadorGP!=null) {

    		try {
    			BigDecimal.valueOf(Long.valueOf(nmrazonsocialConciliadorGP));
			} catch (Exception e) {
				throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_INVALID_INPUT_NMRAZONSOCIAL_CONCILIADORGP));
			} 
        }
    	
	}
    
    
}
