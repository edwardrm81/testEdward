package co.com.gco.tarjetas.conciliador.ejb.business.ejb.impl;
 
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import co.com.gco.tarjetas.conciliador.ejb.business.ejb.interfac.ConciliadorManagerLocal;
import co.com.gco.tarjetas.conciliador.ejb.business.ejb.interfac.NotificationManagerLocal;
import co.com.gco.tarjetas.conciliador.ejb.business.ejb.interfac.ParametroSistemaManagerLocal;
import co.com.gco.tarjetas.conciliador.ejb.business.validate.Validator;
import co.com.gco.tarjetas.conciliador.ejb.common.builder.DTOBuilderDTOToDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.builder.DTOBuilderGeoposResponseRestToDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.constants.ConstantsCommons;
import co.com.gco.tarjetas.conciliador.ejb.common.constants.EnumsCommons;
import co.com.gco.tarjetas.conciliador.ejb.common.constants.MessageCodesI18N;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraConsolidadosDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraConsolidadosDiffDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraConsolidadosDiffFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraConsolidadosFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraDetallesDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraDetallesFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosConsolidadosDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosConsolidadosDiffDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosConsolidadosDiffFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosConsolidadosFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosDetallesDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosDetallesFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.geopos.GeoposRestConsolidadosFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.geopos.GeoposRestPrimeraCompraConsolidadosDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.geopos.GeoposRestRecaudosConsolidadosDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.exception.ExceptionBusiness;
import co.com.gco.tarjetas.conciliador.ejb.common.exception.ExceptionPersistence;
import co.com.gco.tarjetas.conciliador.ejb.common.util.PropertiesLoad;
import co.com.gco.tarjetas.conciliador.ejb.common.util.Utils;
import co.com.gco.tarjetas.conciliador.ejb.persistence.dao.ConciliadorDAO;
import co.com.gco.tarjetas.conciliador.ejb.persistence.entities.TtarConciliadorGeopos;
import co.com.gco.tarjetas.conciliador.ejb.persistence.entities.TtarConciliadorGeoposHead;
import co.com.gco.tarjetas.conciliador.ejb.persistence.entities.TtarRazonsocial;
import co.com.gco.tarjetas.conciliador.ejb.persistence.entities.TtarTienda;



/**
* ConciliadorManagerBean
* @author edward.rodriguez@ingeneo.com.co
* 
*/
@Stateless()
//@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ConciliadorManagerBean implements ConciliadorManagerLocal {
    
	@PersistenceContext(unitName = ConstantsCommons.PERSISTENCE_UNIT_NAME)
    private EntityManager em;
	
	@Inject
	private NotificationManagerLocal notificationBean;
	
	@Inject
	private ParametroSistemaManagerLocal parametroSistemaBean;
    
    private ConciliadorDAO dao;
    
    private ClientRequest clientRequest;
    
    /**
    * init
    * @author edward.rodriguez@ingeneo.com.co
    * 
    */
    @PostConstruct
    private void init() {
        dao = new ConciliadorDAO(this.em);
        clientRequest = new ClientRequest(EnumsCommons.GEOPOS_RESTSERVICE.getValue());
    }
    
    /**
     * Metodo que consulta la lista de los Recaudos Consolidados del servicio de Geopos segun los filtros ingresados
     * @author edward.rodriguez@ingeneo.com.co
     * @param GeoposRestConsolidadosFilterDTO filterDTO
     * @return List<GeoposRestRecaudosConsolidadosDTO>
     * @throws ExceptionBusiness
     */  
    private List<GeoposRestRecaudosConsolidadosDTO> getRecaudosConsolidadosGeopos(GeoposRestConsolidadosFilterDTO filterDTO) throws ExceptionBusiness {
    	
    	try {
    		Validator.isValidInputGetGeoposRestConsolidados(filterDTO);
    		
    		//Recaudos con tarjeta obligatoriamente deben llevar estos dos valores:
			filterDTO.setType("casheable");
			filterDTO.setCode("92");
			
			clientRequest.clear();
			clientRequest.queryParameter("type", filterDTO.getType());
			clientRequest.queryParameter("code", filterDTO.getCode());
			
			
			if(filterDTO.getNit()!=null) {
				clientRequest.queryParameter("nit", filterDTO.getNit());
			}
			
			if(filterDTO.getLocalid()!=null) {
				clientRequest.queryParameter("localid", filterDTO.getLocalid());
			}
			
			if(filterDTO.getDatIni()!=null) {
				clientRequest.queryParameter("datIni", filterDTO.getDatIni());
			}
			
			if(filterDTO.getDatEnd()!=null) {
				clientRequest.queryParameter("datEnd", filterDTO.getDatEnd());
			}
			
			ClientResponse<List> response = clientRequest.get(List.class);
			
			List<GeoposRestRecaudosConsolidadosDTO> responseRestTX = null;
			
			if(response != null) {
				List<List<Object>> responseRest = (List<List<Object>>) response.getEntity();
				responseRestTX = DTOBuilderGeoposResponseRestToDTO.toGeoposRestRecaudosConsolidadosDTOList(responseRest);
			}
			
			return responseRestTX;
    		
		} catch (PersistenceException e) {
			ConstantsCommons.log.error("PersistenceException: " + e.getMessage(), e);
			throw new ExceptionBusiness(e.getMessage(), e);
			
		} catch (ExceptionBusiness e) {
			ConstantsCommons.log.error("ExceptionBusiness: " + e.getMessage(), e);        	
			throw e;
			
		} catch (Exception e) {
			ConstantsCommons.log.error("Exception: " + e.getMessage(), e);
			throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_UNEXPECTED_EXCEPTION), e);
		}
    }
    
    /**
     * Metodo que consulta la lista de las Primeras Compras Consolidados del servicio de Geopos segun los filtros ingresados
     * @author edward.rodriguez@ingeneo.com.co
     * @param GeoposRestConsolidadosFilterDTO filterDTO
     * @return List<GeoposRestPrimeraCompraConsolidadosDTO>
     * @throws ExceptionBusiness
     */  
    private List<GeoposRestPrimeraCompraConsolidadosDTO> getPrimeraCompraConsolidadosGeopos(GeoposRestConsolidadosFilterDTO filterDTO) throws ExceptionBusiness {
    	
    	try {
    		Validator.isValidInputGetGeoposRestConsolidados(filterDTO);
    		
    		//Primeras compras obligatoriamente deben llevar estos dos valores:
			filterDTO.setType("payment");
			filterDTO.setCode("61");
			
			clientRequest.clear();
			clientRequest.queryParameter("type", filterDTO.getType());
			clientRequest.queryParameter("code", filterDTO.getCode());
			
			
			if(filterDTO.getNit()!=null) {
				clientRequest.queryParameter("nit", filterDTO.getNit());
			}
			
			if(filterDTO.getLocalid()!=null) {
				clientRequest.queryParameter("localid", filterDTO.getLocalid());
			}
			
			if(filterDTO.getDatIni()!=null) {
				clientRequest.queryParameter("datIni", filterDTO.getDatIni());
			}
			
			if(filterDTO.getDatEnd()!=null) {
				clientRequest.queryParameter("datEnd", filterDTO.getDatEnd());
			}
			
			ClientResponse<GeoposRestPrimeraCompraConsolidadosDTO[]> response;
			response = clientRequest.get(GeoposRestPrimeraCompraConsolidadosDTO[].class);
			
			List<GeoposRestPrimeraCompraConsolidadosDTO> responseRest = null;
			
			if(response!=null) {
				responseRest = Arrays.asList((GeoposRestPrimeraCompraConsolidadosDTO[]) response.getEntity());
			}
			
			return responseRest;
    		
		} catch (PersistenceException e) {
			ConstantsCommons.log.error("PersistenceException: " + e.getMessage(), e);
			throw new ExceptionBusiness(e.getMessage(), e);
			
		} catch (ExceptionBusiness e) {
			ConstantsCommons.log.error("ExceptionBusiness: " + e.getMessage(), e);        	
			throw e;
			
		} catch (Exception e) {
			ConstantsCommons.log.error("Exception: " + e.getMessage(), e);
			throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_UNEXPECTED_EXCEPTION), e);
		}
    }    
    
    
    /**
     * Metodo que consulta la lista de los Recaudos Consolidados y sus diferencias con Geopos segun los filtros ingresados
     * @author edward.rodriguez@ingeneo.com.co
     * @param RecaudosConsolidadosDiffFilterDTO filterDTO
     * @return List<RecaudosConsolidadosDiffDTO>
     * @throws ExceptionPersistence
     */    
    public List<RecaudosConsolidadosDiffDTO> getRecaudosConsolidadosDiff(RecaudosConsolidadosDiffFilterDTO filterDTO) throws ExceptionBusiness {

    	try {
    		Validator.isValidInputGetRecaudosConsolidadosDiff(filterDTO);
    		
    		GeoposRestConsolidadosFilterDTO filterGeoposDTO = DTOBuilderDTOToDTO.recaudosConsolidadosDiffFilterDTOtoGeoposRestConsolidadosFilterDTO(filterDTO);
    		List<GeoposRestRecaudosConsolidadosDTO> recaudosGeoposDTOList = getRecaudosConsolidadosGeopos(filterGeoposDTO);
    		
    		RecaudosConsolidadosFilterDTO filterTarjetasDTO = DTOBuilderDTOToDTO.recaudosConsolidadosDiffFilterDTOtoRecaudosConsolidadosFilterDTO(filterDTO);
    		List<RecaudosConsolidadosDTO> recaudosDTOList = dao.getRecaudosConsolidados(filterTarjetasDTO);
    		
    		Map<String, TtarTienda> ttarTiendaListMap = this.getTtarTiendaListMap(this.getTtarTiendaList(null));
    		
    		List<RecaudosConsolidadosDiffDTO> recaudosConsolidadosDiffDTOList = getRecaudosConsolidadosMatch(filterDTO, recaudosGeoposDTOList, recaudosDTOList, ttarTiendaListMap);    		
    		return recaudosConsolidadosDiffDTOList;
    		
    	} catch (PersistenceException e) {
    		ConstantsCommons.log.error("PersistenceException: " + e.getMessage(), e);
    		throw new ExceptionBusiness(e.getMessage(), e);
    		
    	} catch (ExceptionBusiness e) {
    		ConstantsCommons.log.error("ExceptionBusiness: " + e.getMessage(), e);
    		throw e;
    		
    	} catch (Exception e) {
    		ConstantsCommons.log.error("Exception: " + e.getMessage(), e);
    		throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_UNEXPECTED_EXCEPTION), e);
    	}
    	
    }    
    
    /**
     * Metodo que consulta la lista de los Detalle de Recaudos segun los filtros ingresados
     * @author edward.rodriguez@ingeneo.com.co
     * @param RecaudosDetallesFilterDTO filterDTO
     * @return List<RecaudosDetallesDTO>
     * @throws ExceptionPersistence
     */    
    public List<RecaudosDetallesDTO> getRecaudosDetalles(RecaudosDetallesFilterDTO filterDTO) throws ExceptionBusiness {
        
    	try {
    		Validator.isValidInputGetRecaudosDetalles(filterDTO);
    		return dao.getRecaudosDetalles(filterDTO);
        
        } catch (PersistenceException e) {
        	ConstantsCommons.log.error("PersistenceException: " + e.getMessage(), e);
        	throw new ExceptionBusiness(e.getMessage(), e);
      	  
        } catch (ExceptionBusiness e) {
        	ConstantsCommons.log.error("ExceptionBusiness: " + e.getMessage(), e);        	
        	throw e;
      	  
        } catch (Exception e) {
        	ConstantsCommons.log.error("Exception: " + e.getMessage(), e);
        	throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_UNEXPECTED_EXCEPTION), e);
        }
        
    }
    
    /**
     * Metodo que consulta la lista de las Primeras Compras Consolidadas y sus diferencias con Geopos segun los filtros ingresados
     * @author edward.rodriguez@ingeneo.com.co
     * @param PrimeraCompraConsolidadosDiffFilterDTO filterDTO
     * @return List<PrimeraCompraConsolidadosDiffDTO>
     * @throws ExceptionPersistence
     */
    public List<PrimeraCompraConsolidadosDiffDTO> getPrimeraCompraConsolidadosDiff(PrimeraCompraConsolidadosDiffFilterDTO filterDTO) throws ExceptionBusiness {
    	
    	try {
    		Validator.isValidInputGetPrimeraCompraConsolidadosDiff(filterDTO);
    		
    		GeoposRestConsolidadosFilterDTO filterGeoposDTO = DTOBuilderDTOToDTO.primeraCompraConsolidadosDiffFilterDTOtoGeoposRestConsolidadosFilterDTO(filterDTO);    		
    		List<GeoposRestPrimeraCompraConsolidadosDTO> primeraCompraGeoposDTOList = getPrimeraCompraConsolidadosGeopos(filterGeoposDTO);
    		
    		PrimeraCompraConsolidadosFilterDTO filterTarjetasDTO = DTOBuilderDTOToDTO.primeraCompraConsolidadosDiffFilterDTOtoPrimeraCompraConsolidadosFilterDTO(filterDTO);
    		List<PrimeraCompraConsolidadosDTO> primeraCompraDTOList = dao.getPrimeraCompraConsolidados(filterTarjetasDTO);
    		
    		Map<String, TtarTienda> ttarTiendaListMap = this.getTtarTiendaListMap(this.getTtarTiendaList(null));
    		
    		List<PrimeraCompraConsolidadosDiffDTO> primeraCompraConsolidadosDiffDTOList = getPrimeraCompraConsolidadosMatch(filterDTO, primeraCompraGeoposDTOList, primeraCompraDTOList, ttarTiendaListMap);    		
    		return primeraCompraConsolidadosDiffDTOList;
    		
        } catch (PersistenceException e) {
        	ConstantsCommons.log.error("PersistenceException: " + e.getMessage(), e);
        	throw new ExceptionBusiness(e.getMessage(), e);
      	  
        } catch (ExceptionBusiness e) {
        	ConstantsCommons.log.error("ExceptionBusiness: " + e.getMessage(), e);        	
        	throw e;
      	  
        } catch (Exception e) {
        	ConstantsCommons.log.error("Exception: " + e.getMessage(), e);
        	throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_UNEXPECTED_EXCEPTION), e);
        }
    }
    
    /**
     * Metodo que consulta la lista de los Detalles de Primeras Compras segun los filtros ingresados
     * @author edward.rodriguez@ingeneo.com.co
     * @param PrimeraCompraDetallesFilterDTO filterDTO
     * @return List<PrimeraCompraDetallesDTO>
     * @throws ExceptionPersistence
     */    
    public List<PrimeraCompraDetallesDTO> getPrimeraCompraDetalles(PrimeraCompraDetallesFilterDTO filterDTO) throws ExceptionBusiness {

    	try {
    		Validator.isValidInputGetPrimeraCompraDetalles(filterDTO);
    		return dao.getPrimeraCompraDetalles(filterDTO);
        
        } catch (PersistenceException e) {
        	ConstantsCommons.log.error("PersistenceException: " + e.getMessage(), e);
        	throw new ExceptionBusiness(e.getMessage(), e);
      	  
        } catch (ExceptionBusiness e) {
        	ConstantsCommons.log.error("ExceptionBusiness: " + e.getMessage(), e);        	
        	throw e;
      	  
        } catch (Exception e) {
        	ConstantsCommons.log.error("Exception: " + e.getMessage(), e);
        	throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_UNEXPECTED_EXCEPTION), e);
        }
    	
    }
    
    
    /**
     * Metodo que hace match y obtiene las diferencias de las listas de GeoposRestRecaudosConsolidadosDTOList y RecaudosConsolidadosDTOList 
     * @author edward.rodriguez@ingeneo.com.co
     * @param RecaudosConsolidadosDiffFilterDTO filterDTO
     * @param List<GeoposRestRecaudosConsolidadosDTO> geoposRestRecaudosConsolidadosDTOList
     * @param List<RecaudosConsolidadosDTO> recaudosConsolidadosDTOList
     * @param Map<String, TtarTienda> ttarTiendaListMap
     * @return List<RecaudosConsolidadosDiffDTO>
     * @throws ExceptionBusiness
     */  
    private List<RecaudosConsolidadosDiffDTO> getRecaudosConsolidadosMatch(RecaudosConsolidadosDiffFilterDTO filterDTO, List<GeoposRestRecaudosConsolidadosDTO> geoposRestRecaudosConsolidadosDTOList, List<RecaudosConsolidadosDTO> recaudosConsolidadosDTOList, Map<String, TtarTienda> ttarTiendaListMap) throws ExceptionBusiness {
    	
    	List<RecaudosConsolidadosDiffDTO> diffReturnList = new ArrayList<RecaudosConsolidadosDiffDTO>();
    	Map<String, RecaudosConsolidadosDiffDTO> match = new LinkedHashMap<String, RecaudosConsolidadosDiffDTO>();
    	Map<String, RecaudosConsolidadosDiffDTO> matchReturn = new LinkedHashMap<String, RecaudosConsolidadosDiffDTO>();
    	
    	try {
    		Validator.isValidInputRecaudosConsolidadosMatch(filterDTO, geoposRestRecaudosConsolidadosDTOList, recaudosConsolidadosDTOList);
    		
    		for (RecaudosConsolidadosDTO recaudosConsolidadosDTO : recaudosConsolidadosDTOList) {
				
    			String key =	recaudosConsolidadosDTO.getCdtienda() + "_" +
    							recaudosConsolidadosDTO.getFevoucher() + "_" +
    							recaudosConsolidadosDTO.getNmcaja();
    			
    			RecaudosConsolidadosDiffDTO recaudosConsolidadosDiffDTO = DTOBuilderDTOToDTO.recaudosConsolidadosDTOtoRecaudosConsolidadosDiffDTO(recaudosConsolidadosDTO);
    			recaudosConsolidadosDiffDTO.setVrpagadoDiferencia(recaudosConsolidadosDiffDTO.getVrpagadoTarjetas());
    			
    			match.put(key, recaudosConsolidadosDiffDTO);
			}
    		
    		for (GeoposRestRecaudosConsolidadosDTO geoposRestRecaudosConsolidadosDTO : geoposRestRecaudosConsolidadosDTOList) {
				
    			String key =	geoposRestRecaudosConsolidadosDTO.getLocalId() + "_" +
    							geoposRestRecaudosConsolidadosDTO.getFecMov() + "_" +
    							geoposRestRecaudosConsolidadosDTO.getCaja();
    			
    			if(match.containsKey(key)) {
    				RecaudosConsolidadosDiffDTO recaudosConsolidadosDiffDTO = match.get(key);
    				recaudosConsolidadosDiffDTO.setVrpagadoGeopos(geoposRestRecaudosConsolidadosDTO.getTotal());
    				Double diff = ( recaudosConsolidadosDiffDTO.getVrpagadoTarjetas()!=null? Double.valueOf(recaudosConsolidadosDiffDTO.getVrpagadoTarjetas()) : 0 ) - ( geoposRestRecaudosConsolidadosDTO.getTotal()!=null? geoposRestRecaudosConsolidadosDTO.getTotal() : 0 );
    				recaudosConsolidadosDiffDTO.setVrpagadoDiferencia(diff);
    				
    			} else {
    				
    				RecaudosConsolidadosDiffDTO recaudosConsolidadosDiffDTO = new RecaudosConsolidadosDiffDTO();
    				
    				recaudosConsolidadosDiffDTO.setCdtienda(geoposRestRecaudosConsolidadosDTO.getLocalId());
			    	recaudosConsolidadosDiffDTO.setDsnombre( ttarTiendaListMap.containsKey(geoposRestRecaudosConsolidadosDTO.getLocalId()) ? ttarTiendaListMap.get(geoposRestRecaudosConsolidadosDTO.getLocalId()).getDsnombre() : PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_TARJETAS_VALUE_VS_GEOPOS_NOT_FOUND) );
    				recaudosConsolidadosDiffDTO.setFecha(geoposRestRecaudosConsolidadosDTO.getFecMov());
    				recaudosConsolidadosDiffDTO.setNmcaja(geoposRestRecaudosConsolidadosDTO.getCaja());
			    	recaudosConsolidadosDiffDTO.setVrpagadoTarjetas(null);
    				recaudosConsolidadosDiffDTO.setVrpagadoGeopos(geoposRestRecaudosConsolidadosDTO.getTotal());
    				recaudosConsolidadosDiffDTO.setVrpagadoDiferencia(geoposRestRecaudosConsolidadosDTO.getTotal()!=null? -Double.valueOf(geoposRestRecaudosConsolidadosDTO.getTotal()) : 0);
    				
    				match.put(key, recaudosConsolidadosDiffDTO);
    				
    			}
    			
			}
    		
    		if(filterDTO.getMostrarSoloDiferencia() == null || !filterDTO.getMostrarSoloDiferencia()) {
    			matchReturn.putAll(match);
    		} else {
    			
    	    	for (Map.Entry<String, RecaudosConsolidadosDiffDTO> entry : match.entrySet()) {
    	    		
    	    		if(entry.getValue().getVrpagadoTarjetas() == null ||  entry.getValue().getVrpagadoGeopos() == null || entry.getValue().getVrpagadoDiferencia() == null || entry.getValue().getVrpagadoDiferencia()!=0) {
    	    			matchReturn.put(entry.getKey(), entry.getValue());
    	    		}
    	    		
    			}
    			
    		}
			
    		diffReturnList = new ArrayList<RecaudosConsolidadosDiffDTO>( matchReturn.values() );
    		
			return diffReturnList;
    		
		} catch (PersistenceException e) {
			ConstantsCommons.log.error("PersistenceException: " + e.getMessage(), e);
			throw new ExceptionBusiness(e.getMessage(), e);
			
		} catch (ExceptionBusiness e) {
			ConstantsCommons.log.error("ExceptionBusiness: " + e.getMessage(), e);        	
			throw e;
			
		} catch (Exception e) {
			ConstantsCommons.log.error("Exception: " + e.getMessage(), e);
			throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_UNEXPECTED_EXCEPTION), e);
		}
    }

    
    /**
     * Metodo que hace match y obtiene las diferencias de las listas de GeoposRestPrimeraCompraConsolidadosDTOList y PrimeraCompraConsolidadosDTOList 
     * @author edward.rodriguez@ingeneo.com.co
     * @param PrimeraCompraConsolidadosDiffFilterDTO filterDTO
     * @param List<GeoposRestPrimeraCompraConsolidadosDTO> geoposRestPrimeraCompraConsolidadosDTOList
     * @param List<PrimeraCompraConsolidadosDTO> primeraCompraConsolidadosDTOList
     * @param Map<String, TtarTienda> ttarTiendaListMap
     * @return List<PrimeraCompraConsolidadosDiffDTO>
     * @throws ExceptionBusiness
     */  
    private List<PrimeraCompraConsolidadosDiffDTO> getPrimeraCompraConsolidadosMatch(PrimeraCompraConsolidadosDiffFilterDTO filterDTO, List<GeoposRestPrimeraCompraConsolidadosDTO> geoposRestPrimeraCompraConsolidadosDTOList, List<PrimeraCompraConsolidadosDTO> primeraCompraConsolidadosDTOList, Map<String, TtarTienda> ttarTiendaListMap) throws ExceptionBusiness {
    	
    	List<PrimeraCompraConsolidadosDiffDTO> diffReturnList = new ArrayList<PrimeraCompraConsolidadosDiffDTO>();
    	Map<String, PrimeraCompraConsolidadosDiffDTO> match = new LinkedHashMap<String, PrimeraCompraConsolidadosDiffDTO>();
    	Map<String, PrimeraCompraConsolidadosDiffDTO> matchReturn = new LinkedHashMap<String, PrimeraCompraConsolidadosDiffDTO>();
    	
    	try {
    		Validator.isValidInputPrimeraCompraConsolidadosMatch(filterDTO, geoposRestPrimeraCompraConsolidadosDTOList, primeraCompraConsolidadosDTOList);
    		
    		for (PrimeraCompraConsolidadosDTO primeraCompraConsolidadosDTO : primeraCompraConsolidadosDTOList) {
				
    			String key =	primeraCompraConsolidadosDTO.getCdtienda() + "_" +
    							primeraCompraConsolidadosDTO.getFeaprobacion() + "_" +
    							primeraCompraConsolidadosDTO.getNmcaja();
    			
    			PrimeraCompraConsolidadosDiffDTO primeraCompraConsolidadosDiffDTO = DTOBuilderDTOToDTO.primeraCompraConsolidadosDTOtoPrimeraCompraConsolidadosDiffDTO(primeraCompraConsolidadosDTO);
    			primeraCompraConsolidadosDiffDTO.setVrpagadoDiferencia(primeraCompraConsolidadosDiffDTO.getVrpagadoTarjetas());
    			
    			match.put(key, primeraCompraConsolidadosDiffDTO);
			}
    		
    		for (GeoposRestPrimeraCompraConsolidadosDTO geoposRestPrimeraCompraConsolidadosDTO : geoposRestPrimeraCompraConsolidadosDTOList) {
    			
    			String key =	geoposRestPrimeraCompraConsolidadosDTO.getLocalId() + "_" +
    							geoposRestPrimeraCompraConsolidadosDTO.getFecMov() + "_" +
    							geoposRestPrimeraCompraConsolidadosDTO.getCaja();
    			
    			if(match.containsKey(key)) {
    				PrimeraCompraConsolidadosDiffDTO primeraCompraConsolidadosDiffDTO = match.get(key);
    				primeraCompraConsolidadosDiffDTO.setVrpagadoGeopos(geoposRestPrimeraCompraConsolidadosDTO.getTotal());
    				Double diff = ( primeraCompraConsolidadosDiffDTO.getVrpagadoTarjetas()!=null? Double.valueOf(primeraCompraConsolidadosDiffDTO.getVrpagadoTarjetas()) : 0 ) - ( geoposRestPrimeraCompraConsolidadosDTO.getTotal()!=null? geoposRestPrimeraCompraConsolidadosDTO.getTotal() : 0 );
    				primeraCompraConsolidadosDiffDTO.setVrpagadoDiferencia(diff);
    				
    			} else {
    				
    				PrimeraCompraConsolidadosDiffDTO primeraCompraConsolidadosDiffDTO = new PrimeraCompraConsolidadosDiffDTO();
    				
    				primeraCompraConsolidadosDiffDTO.setCdtienda(geoposRestPrimeraCompraConsolidadosDTO.getLocalId());
    				primeraCompraConsolidadosDiffDTO.setDsnombre( ttarTiendaListMap.containsKey(geoposRestPrimeraCompraConsolidadosDTO.getLocalId()) ? ttarTiendaListMap.get(geoposRestPrimeraCompraConsolidadosDTO.getLocalId()).getDsnombre() : PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_TARJETAS_VALUE_VS_GEOPOS_NOT_FOUND) );
    				primeraCompraConsolidadosDiffDTO.setFecha(geoposRestPrimeraCompraConsolidadosDTO.getFecMov());
    				primeraCompraConsolidadosDiffDTO.setNmcaja(geoposRestPrimeraCompraConsolidadosDTO.getCaja());
			    	primeraCompraConsolidadosDiffDTO.setVrpagadoTarjetas(null);
    				primeraCompraConsolidadosDiffDTO.setVrpagadoGeopos(geoposRestPrimeraCompraConsolidadosDTO.getTotal());
    				primeraCompraConsolidadosDiffDTO.setVrpagadoDiferencia(geoposRestPrimeraCompraConsolidadosDTO.getTotal()!=null? -Double.valueOf(geoposRestPrimeraCompraConsolidadosDTO.getTotal()) : 0);
    				
    				match.put(key, primeraCompraConsolidadosDiffDTO);
    				
    			}
    			
			}
    		
    		if(filterDTO.getMostrarSoloDiferencia() == null || !filterDTO.getMostrarSoloDiferencia()) {
    			matchReturn.putAll(match);
    		} else {
    			
    	    	for (Map.Entry<String, PrimeraCompraConsolidadosDiffDTO> entry : match.entrySet()) {
    	    		
    	    		if(entry.getValue().getVrpagadoTarjetas() == null ||  entry.getValue().getVrpagadoGeopos() == null || entry.getValue().getVrpagadoDiferencia() == null || entry.getValue().getVrpagadoDiferencia()!=0) {
    	    			matchReturn.put(entry.getKey(), entry.getValue());
    	    		}
    	    		
    			}
    			
    		}
			
    		diffReturnList = new ArrayList<PrimeraCompraConsolidadosDiffDTO>( matchReturn.values() );
    		
			return diffReturnList;
    		
		} catch (PersistenceException e) {
			ConstantsCommons.log.error("PersistenceException: " + e.getMessage(), e);
			throw new ExceptionBusiness(e.getMessage(), e);
			
		} catch (ExceptionBusiness e) {
			ConstantsCommons.log.error("ExceptionBusiness: " + e.getMessage(), e);        	
			throw e;
			
		} catch (Exception e) {
			ConstantsCommons.log.error("Exception: " + e.getMessage(), e);
			throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_UNEXPECTED_EXCEPTION), e);
		}
    }
    
    
    /**
     * Metodo que retorna map con todas las tiendas de la tabla TTAR_TIENDA retornando en key el cdtienda (es el mismo locationId del servicio de Geopos) y en value el objeto ttarTienda
     * @author edward.rodriguez@ingeneo.com.co
     * @param List<TtarTienda> ttarTiendaList
     * @return Map<String, TtarTienda> siendo key cdtienda y el value el objeto TtarTienda
     */  
    private Map<String, TtarTienda> getTtarTiendaListMap(List<TtarTienda> ttarTiendaList) {
    	
		Map<String, TtarTienda> ttarTiendaListmap = new HashMap<String, TtarTienda>();
		for (TtarTienda ttarTienda : ttarTiendaList) {
			ttarTiendaListmap.put(ttarTienda.getCdtienda(), ttarTienda);
		}
		
		return ttarTiendaListmap;
    }
    
    /**
     * Metodo que consulta la lista de los Recaudos Consolidados y sus diferencias con Geopos segun los filtros ingresados, envia un correo con la informacion consultada y guarda en una tabla de auditoria la informacion
     * @author edward.rodriguez@ingeneo.com.co
     * @param RecaudosConsolidadosDiffFilterDTO filterDTO
     * @return List<RecaudosConsolidadosDiffDTO>
     * @throws ExceptionPersistence
     */    
    public List<RecaudosConsolidadosDiffDTO> getRecaudosConsolidadosDiffAndSendEmail(RecaudosConsolidadosDiffFilterDTO filterDTO) throws ExceptionBusiness {
    	
    	try {
    		List<RecaudosConsolidadosDiffDTO> recaudosConsolidadosDiffDTOList = this.getRecaudosConsolidadosDiff(filterDTO);
    		
    		String emailsReceiversList = parametroSistemaBean.consultarValorParametro(EnumsCommons.EMAILS_LIST_CONCILIADOR_TARJETAS_GEOPOS.getValue());
    		String mailText = Utils.getRecaudosConsolidadosDiffDTOListToHtml(recaudosConsolidadosDiffDTOList, filterDTO);
    		notificationBean.sendHtmlText(mailText, PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_EMAIL_CONCILIADOR_SUBJECT), emailsReceiversList);
    		this.saveAuditRecordsRecaudosConsolidadosDiff(filterDTO, recaudosConsolidadosDiffDTOList);
    		
    		return recaudosConsolidadosDiffDTOList;
    		
    	} catch (PersistenceException e) {
    		ConstantsCommons.log.error("PersistenceException: " + e.getMessage(), e);
    		throw new ExceptionBusiness(e.getMessage(), e);
    		
    	} catch (ExceptionBusiness e) {
    		ConstantsCommons.log.error("ExceptionBusiness: " + e.getMessage(), e);
    		throw e;
    		
    	} catch (Exception e) {
    		ConstantsCommons.log.error("Exception: " + e.getMessage(), e);
    		throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_UNEXPECTED_EXCEPTION), e);
    	}
    	
    }

    
    /**
     * Metodo que consulta la lista de las Primeras Compras Consolidados y sus diferencias con Geopos segun los filtros ingresados, envia un correo con la informacion consultada y guarda en una tabla de auditoria la informacion
     * @author edward.rodriguez@ingeneo.com.co
     * @param PrimeraCompraConsolidadosDiffFilterDTO filterDTO
     * @return List<PrimeraCompraConsolidadosDiffDTO>
     * @throws ExceptionPersistence
     */    
    public List<PrimeraCompraConsolidadosDiffDTO> getPrimeraCompraConsolidadosDiffAndSendEmail(PrimeraCompraConsolidadosDiffFilterDTO filterDTO) throws ExceptionBusiness {
    	
    	try {
    		List<PrimeraCompraConsolidadosDiffDTO> primeraCompraConsolidadosDiffDTOList = this.getPrimeraCompraConsolidadosDiff(filterDTO);
    		
    		String emailsReceiversList = parametroSistemaBean.consultarValorParametro(EnumsCommons.EMAILS_LIST_CONCILIADOR_TARJETAS_GEOPOS.getValue());
    		String mailText = Utils.getPrimeraCompraConsolidadosDiffDTOListToHtml(primeraCompraConsolidadosDiffDTOList, filterDTO);
    		notificationBean.sendHtmlText(mailText, PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_EMAIL_CONCILIADOR_SUBJECT), emailsReceiversList);
    		this.saveAuditRecordsPrimeraCompraConsolidadosDiff(filterDTO, primeraCompraConsolidadosDiffDTOList);
    		
    		return primeraCompraConsolidadosDiffDTOList;
    		
    	} catch (PersistenceException e) {
    		ConstantsCommons.log.error("PersistenceException: " + e.getMessage(), e);
    		throw new ExceptionBusiness(e.getMessage(), e);
    		
    	} catch (ExceptionBusiness e) {
    		ConstantsCommons.log.error("ExceptionBusiness: " + e.getMessage(), e);
    		throw e;
    		
    	} catch (Exception e) {
    		ConstantsCommons.log.error("Exception: " + e.getMessage(), e);
    		throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_UNEXPECTED_EXCEPTION), e);
    	}
    	
    }    
    
    
    /**
     * Save Audit Records: recaudos consolidados diff information send by email
     * @author edward.rodriguez@ingeneo.com.co
     * @param RecaudosConsolidadosDiffFilterDTO filterDTO
     * @param List<RecaudosConsolidadosDiffDTO> recaudosConsolidadosDiffDTOList
     * @throws ExceptionPersistence
     */    
    private void saveAuditRecordsRecaudosConsolidadosDiff(RecaudosConsolidadosDiffFilterDTO filterDTO, List<RecaudosConsolidadosDiffDTO> recaudosConsolidadosDiffDTOList) throws ExceptionBusiness {
    	
    	try {
    		Validator.isValidInputSaveAuditRecordsRecaudosConsolidadosDiff(filterDTO, recaudosConsolidadosDiffDTOList);
    		
    		Date fechaYHoraAuditoria = new Date();
    		
    		TtarConciliadorGeoposHead entityHead =  new TtarConciliadorGeoposHead();
    		
    		entityHead.setFechaHoraAuditoria(fechaYHoraAuditoria);
    		entityHead.setCdrazonsocial(filterDTO.getCdrazonsocial());
    		entityHead.setCdtienda(filterDTO.getCdtienda());
    		entityHead.setFechaInicial(filterDTO.getFevoucherInicial());
    		entityHead.setFechaFinal(filterDTO.getFevoucherFinal());
    		entityHead.setFormaPago(PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_FORMA_PAGO_RECAUDOS_TARJETA));
    		entityHead.setMostrarSoloDiferencia( (filterDTO.getMostrarSoloDiferencia()==null || !filterDTO.getMostrarSoloDiferencia()) ? PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_NO) : PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_YES) );
    		
    		this.em.persist(entityHead);
    		Long idHead = entityHead.getId();
    		
    		for (RecaudosConsolidadosDiffDTO dto : recaudosConsolidadosDiffDTOList) {
    			
        		TtarConciliadorGeopos entity =  new TtarConciliadorGeopos();
        		
        		entity.setIdHead(idHead);
        		entity.setFechaVenta(dto.getFecha());
        		entity.setCdtienda(dto.getCdtienda());
        		entity.setNombreTienda(dto.getDsnombre());
        		entity.setNmcaja(dto.getNmcaja());
        		entity.setValorTarjetas( dto.getVrpagadoTarjetas()!=null? BigDecimal.valueOf(dto.getVrpagadoTarjetas()).setScale(2, BigDecimal.ROUND_HALF_UP) : null );
        		entity.setValorGeopos( dto.getVrpagadoGeopos()!=null? BigDecimal.valueOf(dto.getVrpagadoGeopos()).setScale(2, BigDecimal.ROUND_HALF_UP) : null );
        		entity.setValorDiferencia( dto.getVrpagadoDiferencia()!=null? BigDecimal.valueOf(dto.getVrpagadoDiferencia()).setScale(2, BigDecimal.ROUND_HALF_UP) : null );
        		
        		this.em.persist(entity);
			}    		
    		
    		
    	} catch (PersistenceException e) {
    		ConstantsCommons.log.error("PersistenceException: " + e.getMessage(), e);
    		throw new ExceptionBusiness(e.getMessage(), e);
    		
    	} catch (ExceptionBusiness e) {
    		ConstantsCommons.log.error("ExceptionBusiness: " + e.getMessage(), e);
    		throw e;
    		
    	} catch (Exception e) {
    		ConstantsCommons.log.error("Exception: " + e.getMessage(), e);
    		throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_UNEXPECTED_EXCEPTION), e);
    	}
    	
    } 
    
    
    /**
     * Save Audit Records: primera compra consolidados diff information send by email
     * @author edward.rodriguez@ingeneo.com.co
     * @param PrimeraCompraConsolidadosDiffFilterDTO filterDTO
     * @param List<RecaudosConsolidadosDiffDTO> recaudosConsolidadosDiffDTOList
     * @throws ExceptionPersistence
     */    
    private void saveAuditRecordsPrimeraCompraConsolidadosDiff(PrimeraCompraConsolidadosDiffFilterDTO filterDTO, List<PrimeraCompraConsolidadosDiffDTO> primeraCompraConsolidadosDiffDTOList) throws ExceptionBusiness {

    	try {
    		Validator.isValidInputSaveAuditRecordsPrimeraCompraConsolidadosDiff(filterDTO, primeraCompraConsolidadosDiffDTOList);
    		
    		Date fechaYHoraAuditoria = new Date();
    		
    		TtarConciliadorGeoposHead entityHead =  new TtarConciliadorGeoposHead();
    		
    		entityHead.setFechaHoraAuditoria(fechaYHoraAuditoria);
    		entityHead.setCdrazonsocial(filterDTO.getCdrazonsocial());
    		entityHead.setCdtienda(filterDTO.getCdtienda());
    		entityHead.setFechaInicial(filterDTO.getFeaprobacionInicial());
    		entityHead.setFechaFinal(filterDTO.getFeaprobacionFinal());
    		entityHead.setFormaPago(PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_FORMA_PAGO_PRIMERA_COMPRA));
    		entityHead.setMostrarSoloDiferencia( (filterDTO.getMostrarSoloDiferencia()==null || !filterDTO.getMostrarSoloDiferencia()) ? PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_NO) : PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_YES) );
    		
    		this.em.persist(entityHead);
    		Long idHead = entityHead.getId();
    		
    		for (PrimeraCompraConsolidadosDiffDTO dto : primeraCompraConsolidadosDiffDTOList) {
    			
        		TtarConciliadorGeopos entity =  new TtarConciliadorGeopos();
        		
        		entity.setIdHead(idHead);
        		entity.setFechaVenta(dto.getFecha());
        		entity.setCdtienda(dto.getCdtienda());
        		entity.setNombreTienda(dto.getDsnombre());
        		entity.setNmcaja(dto.getNmcaja());
        		entity.setValorTarjetas( dto.getVrpagadoTarjetas()!=null? BigDecimal.valueOf(dto.getVrpagadoTarjetas()).setScale(2, BigDecimal.ROUND_HALF_UP) : null );
        		entity.setValorGeopos( dto.getVrpagadoGeopos()!=null? BigDecimal.valueOf(dto.getVrpagadoGeopos()).setScale(2, BigDecimal.ROUND_HALF_UP) : null );
        		entity.setValorDiferencia( dto.getVrpagadoDiferencia()!=null? BigDecimal.valueOf(dto.getVrpagadoDiferencia()).setScale(2, BigDecimal.ROUND_HALF_UP) : null );
        		
        		this.em.persist(entity);
			}    		
    		
    	} catch (PersistenceException e) {
    		ConstantsCommons.log.error("PersistenceException: " + e.getMessage(), e);
    		throw new ExceptionBusiness(e.getMessage(), e);
    		
    	} catch (ExceptionBusiness e) {
    		ConstantsCommons.log.error("ExceptionBusiness: " + e.getMessage(), e);
    		throw e;
    		
    	} catch (Exception e) {
    		ConstantsCommons.log.error("Exception: " + e.getMessage(), e);
    		throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_UNEXPECTED_EXCEPTION), e);
    	}
    	
    } 
    
    
    /**
     * Metodo que consulta la lista de todas las razones sociales (esta es la empresa cuyo nit es usado para enviar al servicio de geopos) las cuales paracen corresponder a la razon social de la empresa facturadora de cada tienda
     * @author edward.rodriguez@ingeneo.com.co
     * @return List<TtarRazonsocial>
     * @throws ExceptionPersistence
     */    
    public List<TtarRazonsocial> getTtarRazonsocialList() throws ExceptionBusiness {
    	
    	try {
    		TypedQuery<TtarRazonsocial> query = em.createNamedQuery(TtarRazonsocial.Queries.FIND_ALL, TtarRazonsocial.class);			
    		
    		List<TtarRazonsocial> resultList = query.getResultList();
    		List<TtarRazonsocial> ttarRazonsocialList = resultList.isEmpty() ? new ArrayList<TtarRazonsocial>() : resultList;
    		
    		return ttarRazonsocialList;
    		
    	} catch (PersistenceException e) {
    		ConstantsCommons.log.error("PersistenceException: " + e.getMessage(), e);
    		throw new ExceptionBusiness(e.getMessage(), e);
    		
    	//} catch (ExceptionBusiness e) {
    	//	//ConstantsCommons.log.error("ExceptionBusiness: " + e.getMessage(), e);
    	//	throw e;
    		
    	} catch (Exception e) {
    		ConstantsCommons.log.error("Exception: " + e.getMessage(), e);
    		throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_UNEXPECTED_EXCEPTION), e);
    	}
    	
    }
    
    
    /**
     * Metodo que consulta la lista de las tiendas (pos) dado un nmrazonsocial (esta es la clave primaria PK de la tabla de razones sociales, el nit usado para enviar al servicio de geopos sale de la tabla de razones sociales del campo nmrazonsocialConciliadorGP) la cual parece corresponder a la empresa facturadora de esa tienda, si no se ingresa una razón social, se consultan todas las tiendas
     * @author edward.rodriguez@ingeneo.com.co
     * @param String nmrazonsocialConciliadorGP
     * @return List<TtarTienda> TtarTiendaList
     * @throws ExceptionPersistence
     */    
    public List<TtarTienda> getTtarTiendaList(String nmrazonsocialConciliadorGP) throws ExceptionBusiness {
    	
    	try {
    		
    		Validator.isValidInputGetTtarTiendaList(nmrazonsocialConciliadorGP);
    		
    		TypedQuery<TtarTienda> query = null;
    		
    		if(nmrazonsocialConciliadorGP != null) {
	    		query = em.createNamedQuery(TtarTienda.Queries.FIND_BY_NMRAZONSOCIAL_CONCILIADORGP, TtarTienda.class);
	    		query.setParameter("nmrazonsocialConciliadorGP", BigDecimal.valueOf(Long.valueOf(nmrazonsocialConciliadorGP)));
    		} else {
	    		query = em.createNamedQuery(TtarTienda.Queries.FIND_ALL, TtarTienda.class);			
    		}
    		
    		List<TtarTienda> resultList = query.getResultList();
    		List<TtarTienda> ttarTiendaList = resultList.isEmpty() ? new ArrayList<TtarTienda>() : resultList;
    		
    		return ttarTiendaList;
    		
    	} catch (PersistenceException e) {
    		ConstantsCommons.log.error("PersistenceException: " + e.getMessage(), e);
    		throw new ExceptionBusiness(e.getMessage(), e);
    		
    	} catch (ExceptionBusiness e) {
    		ConstantsCommons.log.error("ExceptionBusiness: " + e.getMessage(), e);
    		throw e;
    		
    	} catch (Exception e) {
    		ConstantsCommons.log.error("Exception: " + e.getMessage(), e);
    		throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_UNEXPECTED_EXCEPTION), e);
    	}
    	
    }
    
    
	
    
    
}