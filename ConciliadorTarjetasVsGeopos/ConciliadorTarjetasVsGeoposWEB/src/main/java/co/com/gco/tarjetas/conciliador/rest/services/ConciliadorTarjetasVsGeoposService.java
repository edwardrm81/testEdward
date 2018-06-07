package co.com.gco.tarjetas.conciliador.rest.services;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.com.gco.tarjetas.conciliador.ejb.business.ejb.interfac.ConciliadorManagerLocal;
import co.com.gco.tarjetas.conciliador.ejb.common.builder.DTOBuilderParamsToDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.constants.ConstantsCommons;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraConsolidadosDiffDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraConsolidadosDiffFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraDetallesDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraDetallesFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosConsolidadosDiffDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosConsolidadosDiffFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosDetallesDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosDetallesFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.exception.ExceptionBusiness;
import co.com.gco.tarjetas.conciliador.ejb.common.exception.ExceptionPersistence;
import co.com.gco.tarjetas.conciliador.ejb.persistence.entities.TtarRazonsocial;
import co.com.gco.tarjetas.conciliador.ejb.persistence.entities.TtarTienda;

@RequestScoped
@Path(ConstantsCommons.REST_APP_PATH_CLASS)
public class ConciliadorTarjetasVsGeoposService {
	
	@Inject
	private ConciliadorManagerLocal conciliadorBean;
	
    /**
     * Servicio REST que consulta la lista de los Recaudos Consolidados y sus diferencias con Geopos segun los filtros ingresados.
     * Example: http://localhost:8080/ConciliadorTarjetasVsGeopos/rest/conciliador/recaudosConsolidadosDiff?cdtienda=689&fechaInicial=2015-05-21&fechaFinal=2015-05-21&mostrarSoloDiferencia=false
     * @author edward.rodriguez@ingeneo.com.co
     * @param String cdrazonsocial
     * @param String cdtienda
     * @param String fechaInicial
     * @param String fechaFinal
     * @param Boolean mostrarSoloDiferencia
     * @return List<RecaudosConsolidadosDTO>
     * @throws ExceptionBusiness
     */   
	@Path(ConstantsCommons.REST_APP_PATH_METHOD_RECAUDOS_CONSOLIDADOS_DIFF)
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<RecaudosConsolidadosDiffDTO> recaudosConsolidadosDiff(@QueryParam("cdrazonsocial") String cdrazonsocial, @QueryParam("cdtienda") String cdtienda, @QueryParam("fechaInicial") String fechaInicial, @QueryParam("fechaFinal") String fechaFinal, @QueryParam("mostrarSoloDiferencia") Boolean mostrarSoloDiferencia) throws ExceptionBusiness {
		
		RecaudosConsolidadosDiffFilterDTO dto = DTOBuilderParamsToDTO.toRecaudosConsolidadosDiffFilterDTO(cdrazonsocial, cdtienda, fechaInicial, fechaFinal, mostrarSoloDiferencia);
		return conciliadorBean.getRecaudosConsolidadosDiff(dto);
	}
	
	
    /**
     * Servicio REST que consulta la lista de los Recaudos Consolidados y sus diferencias con Geopos segun los filtros ingresados, envia un correo con la informacion consultada y guarda en una tabla de auditoria la informacion.
     * Example: http://localhost:8080/ConciliadorTarjetasVsGeopos/rest/conciliador/recaudosConsolidadosDiffAndSendEmail?cdtienda=689&fechaInicial=2015-05-21&fechaFinal=2015-05-21&mostrarSoloDiferencia=false
     * @author edward.rodriguez@ingeneo.com.co
     * @param String cdrazonsocial
     * @param String cdtienda
     * @param String fechaInicial
     * @param String fechaFinal
     * @param Boolean mostrarSoloDiferencia
     * @return List<RecaudosConsolidadosDiffDTO>
     * @throws ExceptionPersistence
     */
	@Path(ConstantsCommons.REST_APP_PATH_METHOD_RECAUDOS_CONSOLIDADOS_DIFF_AND_SEND_EMAIL)
	@GET
	@Produces({MediaType.APPLICATION_JSON})
    public List<RecaudosConsolidadosDiffDTO>  recaudosConsolidadosDiffAndSendEmail(@QueryParam("cdrazonsocial") String cdrazonsocial, @QueryParam("cdtienda") String cdtienda, @QueryParam("fechaInicial") String fechaInicial, @QueryParam("fechaFinal") String fechaFinal, @QueryParam("mostrarSoloDiferencia") Boolean mostrarSoloDiferencia) throws ExceptionBusiness {
		
    	RecaudosConsolidadosDiffFilterDTO dto = DTOBuilderParamsToDTO.toRecaudosConsolidadosDiffFilterDTO(cdrazonsocial, cdtienda, fechaInicial, fechaFinal, mostrarSoloDiferencia);
		return conciliadorBean.getRecaudosConsolidadosDiffAndSendEmail(dto);
    }
    
	
    /**
     * Servicio REST que consulta la lista de los Detalle de Recaudos segun los filtros ingresados.
     * Example: http://localhost:8080/ConciliadorTarjetasVsGeopos/rest/conciliador/recaudosDetalles?cdtienda=689&fecha=2015-05-21&nmcaja=1
     * @author edward.rodriguez@ingeneo.com.co
     * @param String cdtienda
     * @param String fecha
     * @param String nmcaja
     * @return List<RecaudosDetallesDTO>
     * @throws ExceptionBusiness
     */ 
	@Path(ConstantsCommons.REST_APP_PATH_METHOD_RECAUDOS_DETALLES)
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<RecaudosDetallesDTO> recaudosDetalles(@QueryParam("cdtienda") String cdtienda, @QueryParam("fecha") String fecha, @QueryParam("nmcaja") String nmcaja) throws ExceptionBusiness {
		
		RecaudosDetallesFilterDTO dto = DTOBuilderParamsToDTO.toRecaudosDetallesFilterDTO(cdtienda, fecha, nmcaja);
		return conciliadorBean.getRecaudosDetalles(dto);
		
	}
	
	
    /**
     * Servicio REST que consulta la lista de las Primeras Compras Consolidadas y sus diferencias con Geopos segun los filtros ingresados.
     * Example: http://localhost:8080/ConciliadorTarjetasVsGeopos/rest/conciliador/primeraCompraConsolidadosDiff?cdtienda=532&fechaInicial=2015-05-21&fechaFinal=2015-05-21&mostrarSoloDiferencia=false
     * @author edward.rodriguez@ingeneo.com.co
     * @param String cdrazonsocial 
     * @param String cdtienda
     * @param String fechaInicial
     * @param String fechaFinal
     * @param Boolean mostrarSoloDiferencia
     * @return List<PrimeraCompraConsolidadosDiffDTO>
     * @throws ExceptionBusiness
     */ 
	@Path(ConstantsCommons.REST_APP_PATH_METHOD_PRIMERA_COMPRA_CONSOLIDADOS_DIFF)
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<PrimeraCompraConsolidadosDiffDTO> primeraCompraConsolidadosDiff(@QueryParam("cdrazonsocial") String cdrazonsocial, @QueryParam("cdtienda") String cdtienda, @QueryParam("fechaInicial") String fechaInicial, @QueryParam("fechaFinal") String fechaFinal, @QueryParam("mostrarSoloDiferencia") Boolean mostrarSoloDiferencia) throws ExceptionBusiness {
		
		PrimeraCompraConsolidadosDiffFilterDTO dto = DTOBuilderParamsToDTO.toPrimeraCompraConsolidadosDiffFilterDTO(cdrazonsocial, cdtienda, fechaInicial, fechaFinal, mostrarSoloDiferencia);
		return conciliadorBean.getPrimeraCompraConsolidadosDiff(dto);
	}
	

    /**
     * Servicio REST que consulta la lista de las Primeras Compras Consolidadas y sus diferencias con Geopos segun los filtros ingresados, envia un correo con la informacion consultada y guarda en una tabla de auditoria la informacion.
     * Example: http://localhost:8080/ConciliadorTarjetasVsGeopos/rest/conciliador/primeraCompraConsolidadosDiffAndSendEmail?cdtienda=532&fechaInicial=2015-05-21&fechaFinal=2015-05-21&mostrarSoloDiferencia=false
     * @author edward.rodriguez@ingeneo.com.co
     * @param String cdrazonsocial 
     * @param String cdtienda
     * @param String fechaInicial
     * @param String fechaFinal
     * @param Boolean mostrarSoloDiferencia
     * @return List<RecaudosConsolidadosDiffDTO>
     * @throws ExceptionPersistence
     */
	@Path(ConstantsCommons.REST_APP_PATH_METHOD_PRIMERA_COMPRA_CONSOLIDADOS_DIFF_AND_SEND_EMAIL)
	@GET
	@Produces({MediaType.APPLICATION_JSON})
    public List<PrimeraCompraConsolidadosDiffDTO> primeraCompraConsolidadosDiffAndSendEmail(@QueryParam("cdrazonsocial") String cdrazonsocial, @QueryParam("cdtienda") String cdtienda, @QueryParam("fechaInicial") String fechaInicial, @QueryParam("fechaFinal") String fechaFinal, @QueryParam("mostrarSoloDiferencia") Boolean mostrarSoloDiferencia) throws ExceptionBusiness {
		
		PrimeraCompraConsolidadosDiffFilterDTO dto = DTOBuilderParamsToDTO.toPrimeraCompraConsolidadosDiffFilterDTO(cdrazonsocial, cdtienda, fechaInicial, fechaFinal, mostrarSoloDiferencia);
		return conciliadorBean.getPrimeraCompraConsolidadosDiffAndSendEmail(dto);
    }
	
    /**
     * Servicio REST que consulta la lista de los Detalles de Primeras Compras segun los filtros ingresados.
     * Example: http://localhost:8080/ConciliadorTarjetasVsGeopos/rest/conciliador/primeraCompraDetalles?cdtienda=532&fecha=2015-05-21&nmcaja=1
     * @author edward.rodriguez@ingeneo.com.co
     * @param String cdtienda
     * @param String fecha
     * @param String nmcaja
     * @return List<PrimeraCompraDetallesDTO>
     * @throws ExceptionBusiness
     */
	@Path(ConstantsCommons.REST_APP_PATH_METHOD_PRIMERA_COMPRA_DETALLES)
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<PrimeraCompraDetallesDTO> primeraCompraDetalles(@QueryParam("cdtienda") String cdtienda, @QueryParam("fecha") String fecha, @QueryParam("nmcaja") String nmcaja) throws ExceptionBusiness {
		
		PrimeraCompraDetallesFilterDTO dto = DTOBuilderParamsToDTO.toPrimeraCompraDetallesFilterDTO(cdtienda, fecha, nmcaja);
		return conciliadorBean.getPrimeraCompraDetalles(dto);
	}
	
	
    /**
     * Servicio REST que consulta la lista de todas las razones sociales (esta es la empresa cuyo nit es usado para enviar al servicio de geopos) las cuales paracen corresponder a la razon social de la empresa facturadora de cada tienda.
     * Example: http://localhost:8080/ConciliadorTarjetasVsGeopos/rest/conciliador/razonesSociales
     * @author edward.rodriguez@ingeneo.com.co
     * @return List<TtarRazonsocial>
     * @throws ExceptionPersistence
     */
	@Path(ConstantsCommons.REST_APP_PATH_METHOD_RAZONES_SOCIALES)
	@GET
	@Produces({MediaType.APPLICATION_JSON})
    public List<TtarRazonsocial> getTtarRazonsocialList() throws ExceptionBusiness {
    	
    	return conciliadorBean.getTtarRazonsocialList();
    }

    /**
     * Servicio REST que consulta la lista de las tiendas (pos) dado un nmrazonsocial (esta es la clave primaria PK de la tabla de razones sociales, el nit usado para enviar al servicio de geopos sale de la tabla de razones sociales del campo nmrazonsocialConciliadorGP) la cual parece corresponder a la empresa facturadora de esa tienda, si no se ingresa una razón social, se consultan todas las tiendas
     * Example: nmrazonsocial=7 corresponde a Naftalina SAS: http://localhost:8080/ConciliadorTarjetasVsGeopos/rest/conciliador/tiendas?nmrazonsocialConciliadorGP=7
     * @author edward.rodriguez@ingeneo.com.co
     * @param String nmrazonsocialConciliadorGP is optional (nullable) 
     * @return List<TtarTienda> TtarTiendaList
     * @throws ExceptionPersistence
     */
	@Path(ConstantsCommons.REST_APP_PATH_METHOD_TIENDAS)
	@GET
	@Produces({MediaType.APPLICATION_JSON})
    public List<TtarTienda> getTtarTiendaList(@QueryParam("nmrazonsocialConciliadorGP") String nmrazonsocialConciliadorGP) throws ExceptionBusiness {
    	
    	return conciliadorBean.getTtarTiendaList(nmrazonsocialConciliadorGP);
    }
    
	
	

}
