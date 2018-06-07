package co.com.gco.tarjetas.conciliador.ejb.persistence.dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import co.com.gco.tarjetas.conciliador.ejb.common.builder.DTOBuilderObjectToDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.constants.ConstantsCommons;
import co.com.gco.tarjetas.conciliador.ejb.common.constants.EnumsCommons;
import co.com.gco.tarjetas.conciliador.ejb.common.constants.MessageCodesI18N;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraConsolidadosDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraConsolidadosFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraDetallesDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraDetallesFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosConsolidadosDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosConsolidadosFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosDetallesDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosDetallesFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.exception.ExceptionPersistence;
import co.com.gco.tarjetas.conciliador.ejb.common.util.PropertiesLoad;

/**
 * *
 * Clase DAO por la cual se obtendra acceso a la informacion de la conexionde la tabla SCT_PARAMETROS.
 *
 * @author edward.rodriguez
 *
 */
public class ConciliadorDAO {
	
	private EntityManager em;
    
    /**
     * constructor
     * @param EntityManager em
     */	
	public ConciliadorDAO(EntityManager em) {
        this.em = em;

    }
    
    /**
     * Metodo que consulta la lista de los Recaudos Consolidados segun los filtros ingresados
     * @author edward.rodriguez@ingeneo.com.co
     * @param RecaudosConsolidadosFilterDTO filterDTO
     * @return List<RecaudosConsolidadosDTO>
     * @throws ExceptionPersistence
     */    
    public List<RecaudosConsolidadosDTO> getRecaudosConsolidados(RecaudosConsolidadosFilterDTO filterDTO) throws ExceptionPersistence {
    	
    	List<RecaudosConsolidadosDTO> dtoList = new ArrayList<RecaudosConsolidadosDTO>();
        try {
        	
            Query query = this.em.createNativeQuery(QueryHelper.getSqlRecaudosConsolidados(filterDTO));
            
            if(filterDTO.getCdrazonsocial() != null) {
            	query.setParameter("cdrazonsocial", filterDTO.getCdrazonsocial());
            }
            
            if(filterDTO.getCdtienda() != null) {
            	query.setParameter("cdtienda", filterDTO.getCdtienda());
            }
            
            if(filterDTO.getFevoucherInicial() != null) {
            	query.setParameter("fevoucherInicial", filterDTO.getFevoucherInicial());
            }
            
            if(filterDTO.getFevoucherFinal() != null) {
            	query.setParameter("fevoucherFinal", filterDTO.getFevoucherFinal());
            }
            
            List<Object[]> rows = (List<Object[]>)query.getResultList();
            dtoList = DTOBuilderObjectToDTO.toRecaudosConsolidadosDTOList(rows);
            
        } catch (PersistenceException e) {
            ConstantsCommons.log.error("PersistenceException: " + e.getMessage(), e);
            throw new ExceptionPersistence(e.getMessage(), e);
        //} catch (ExceptionPersistence e) {
        //    //ConstantsCommons.log.error("ExceptionPersistence: " + e.getMessage(), e);
        //    throw new ExceptionPersistence(e.getMessage(), e);
        } catch (Exception e) {
        	ConstantsCommons.log.error("Exception: " + e.getMessage(), e);
            throw new ExceptionPersistence(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_UNEXPECTED_EXCEPTION), e);
        }
        
        return dtoList;
    }
    
    /**
     * Metodo que consulta la lista de los Detalle de Recaudos segun los filtros ingresados
     * @author edward.rodriguez@ingeneo.com.co
     * @param RecaudosDetallesFilterDTO filterDTO
     * @return List<RecaudosDetallesDTO>
     * @throws ExceptionPersistence
     */    
    public List<RecaudosDetallesDTO> getRecaudosDetalles(RecaudosDetallesFilterDTO filterDTO) throws ExceptionPersistence {
    	
    	List<RecaudosDetallesDTO> dtoList = new ArrayList<RecaudosDetallesDTO>();
        try {
        	
            Query query = this.em.createNativeQuery(QueryHelper.getSqlRecaudosDetalles(filterDTO));
            
            if(filterDTO.getCdtienda() != null) {
            	query.setParameter("cdtienda", filterDTO.getCdtienda());
            }
            
            if(filterDTO.getFevoucher() != null) {
            	query.setParameter("fevoucher", filterDTO.getFevoucher());
            }
            
            if(filterDTO.getNmcaja() != null) {
            	query.setParameter("nmcaja", filterDTO.getNmcaja());
            }
            
            List<Object[]> rows = (List<Object[]>)query.getResultList();
            dtoList = DTOBuilderObjectToDTO.toRecaudosDetallesDTOList(rows);
            
        } catch (PersistenceException e) {
        	ConstantsCommons.log.error("PersistenceException: " + e.getMessage(), e);
            throw new ExceptionPersistence(e.getMessage(), e);
        //} catch (ExceptionPersistence e) {
        //    //ConstantsCommons.log.error("ExceptionPersistence: " + e.getMessage(), e);
        //    throw new ExceptionPersistence(e.getMessage(), e);
        } catch (Exception e) {
        	ConstantsCommons.log.error("Exception: " + e.getMessage(), e);
            throw new ExceptionPersistence(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_UNEXPECTED_EXCEPTION), e);
        }
        
        return dtoList;
    }
    
    /**
     * Metodo que consulta la lista de las Primeras Compras Consolidadas segun los filtros ingresados
     * @author edward.rodriguez@ingeneo.com.co
     * @param PrimeraCompraConsolidadosFilterDTO filterDTO
     * @return List<PrimeraCompraConsolidadosDTO>
     * @throws ExceptionPersistence
     */    
    public List<PrimeraCompraConsolidadosDTO> getPrimeraCompraConsolidados(PrimeraCompraConsolidadosFilterDTO filterDTO) throws ExceptionPersistence {
    	
    	List<PrimeraCompraConsolidadosDTO> dtoList = new ArrayList<PrimeraCompraConsolidadosDTO>();
        try {
        	
            Query query = this.em.createNativeQuery(QueryHelper.getSqlPrimeraCompraConsolidados(filterDTO));
            
            query.setParameter("snaprobado", EnumsCommons.TTAR_APROBACION_SNAPROBADO_SI.getValue());
            query.setParameter("nmestado", EnumsCommons.TTAR_APROBACION_NMESTADO_APROBADO_PRIMERA_COMPRA.getValue());
    		
            if(filterDTO.getCdrazonsocial() != null) {
            	query.setParameter("cdrazonsocial", filterDTO.getCdrazonsocial());
            }
            
            if(filterDTO.getCdtienda() != null) {
            	query.setParameter("cdtienda", filterDTO.getCdtienda());
            }
            
            if(filterDTO.getFeaprobacionInicial() != null) {
            	query.setParameter("feaprobacionInicial", filterDTO.getFeaprobacionInicial());
            }
            
            if(filterDTO.getFeaprobacionFinal() != null) {
            	query.setParameter("feaprobacionFinal", filterDTO.getFeaprobacionFinal());
            }
            
            List<Object[]> rows = (List<Object[]>)query.getResultList();
            dtoList = DTOBuilderObjectToDTO.toPrimeraCompraConsolidadosDTOList(rows);
            
        } catch (PersistenceException e) {
        	ConstantsCommons.log.error("PersistenceException: " + e.getMessage(), e);
            throw new ExceptionPersistence(e.getMessage(), e);
        //} catch (ExceptionPersistence e) {
        //    //ConstantsCommons.log.error("ExceptionPersistence: " + e.getMessage(), e);
        //    throw new ExceptionPersistence(e.getMessage(), e);
        } catch (Exception e) {
        	ConstantsCommons.log.error("Exception: " + e.getMessage(), e);
            throw new ExceptionPersistence(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_UNEXPECTED_EXCEPTION), e);
        }
        
        return dtoList;
    }
    
    /**
     * Metodo que consulta la lista de los Detalles de Primeras Compras segun los filtros ingresados
     * @author edward.rodriguez@ingeneo.com.co
     * @param PrimeraCompraDetallesFilterDTO filterDTO
     * @return List<PrimeraCompraDetallesDTO>
     * @throws ExceptionPersistence
     */    
    public List<PrimeraCompraDetallesDTO> getPrimeraCompraDetalles(PrimeraCompraDetallesFilterDTO filterDTO) throws ExceptionPersistence {
    	
    	List<PrimeraCompraDetallesDTO> dtoList = new ArrayList<PrimeraCompraDetallesDTO>();
        try {
        	
            Query query = this.em.createNativeQuery(QueryHelper.getSqlPrimeraCompraDetalles(filterDTO));
            
            query.setParameter("snaprobado", EnumsCommons.TTAR_APROBACION_SNAPROBADO_SI.getValue());
            query.setParameter("nmestado", EnumsCommons.TTAR_APROBACION_NMESTADO_APROBADO_PRIMERA_COMPRA.getValue());
            
            if(filterDTO.getCdtienda() != null) {
            	query.setParameter("cdtienda", filterDTO.getCdtienda());
            }
            
            if(filterDTO.getFeaprobacion() != null) {
            	query.setParameter("feaprobacion", filterDTO.getFeaprobacion());
            }
           
            if(filterDTO.getNmcaja() != null) {
            	query.setParameter("nmcaja", filterDTO.getNmcaja());
            }
            
            
            List<Object[]> rows = (List<Object[]>)query.getResultList();
            dtoList = DTOBuilderObjectToDTO.toPrimeraCompraDetallesDTOList(rows);
            
        } catch (PersistenceException e) {
        	ConstantsCommons.log.error("PersistenceException: " + e.getMessage(), e);
            throw new ExceptionPersistence(e.getMessage(), e);
        //} catch (ExceptionPersistence e) {
        //    //ConstantsCommons.log.error("ExceptionPersistence: " + e.getMessage(), e);
        //    throw new ExceptionPersistence(e.getMessage(), e);
        } catch (Exception e) {
        	ConstantsCommons.log.error("Exception: " + e.getMessage(), e);
            throw new ExceptionPersistence(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_UNEXPECTED_EXCEPTION), e);
        }
        
        return dtoList;
    }
    
}