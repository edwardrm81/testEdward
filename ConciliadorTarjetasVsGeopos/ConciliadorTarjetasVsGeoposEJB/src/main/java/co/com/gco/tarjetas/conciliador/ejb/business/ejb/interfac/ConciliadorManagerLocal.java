package co.com.gco.tarjetas.conciliador.ejb.business.ejb.interfac;


import java.util.List;

import javax.ejb.Local;

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

/**
* ConciliadorManagerLocal
* @author edward.rodriguez@ingeneo.com.co
*/
@Local
public interface ConciliadorManagerLocal {
	
    
    /**
     * Metodo que consulta la lista de los Recaudos Consolidados y sus diferencias con Geopos segun los filtros ingresados
     * @author edward.rodriguez@ingeneo.com.co
     * @param RecaudosConsolidadosDiffFilterDTO filterDTO
     * @return List<RecaudosConsolidadosDiffDTO>
     * @throws ExceptionBusiness
     */    
    public List<RecaudosConsolidadosDiffDTO> getRecaudosConsolidadosDiff(RecaudosConsolidadosDiffFilterDTO filterDTO) throws ExceptionBusiness;
        
    
    /**
     * Servicio REST que consulta la lista de los Recaudos Consolidados y sus diferencias con Geopos segun los filtros ingresados, envia un correo con la informacion consultada y guarda en una tabla de auditoria la informacion
     * @author edward.rodriguez@ingeneo.com.co
     * @param RecaudosConsolidadosDiffFilterDTO filterDTO
     * @return List<RecaudosConsolidadosDiffDTO>
     * @throws ExceptionPersistence
     */    
    public List<RecaudosConsolidadosDiffDTO>  getRecaudosConsolidadosDiffAndSendEmail(RecaudosConsolidadosDiffFilterDTO filterDTO) throws ExceptionBusiness;
    
    /**
     * Metodo que consulta la lista de los Detalle de Recaudos segun los filtros ingresados
     * @author edward.rodriguez@ingeneo.com.co
     * @param RecaudosDetallesFilterDTO filterDTO
     * @return List<RecaudosDetallesDTO>
     * @throws ExceptionBusiness
     */    
    public List<RecaudosDetallesDTO> getRecaudosDetalles(RecaudosDetallesFilterDTO filterDTO) throws ExceptionBusiness;
    
    /**
     * Metodo que consulta la lista de las Primeras Compras Consolidadas y sus diferencias con Geopos segun los filtros ingresados
     * @author edward.rodriguez@ingeneo.com.co
     * @param PrimeraCompraConsolidadosDiffFilterDTO filterDTO
     * @return List<PrimeraCompraConsolidadosDiffDTO>
     * @throws ExceptionBusiness
     */    
    public List<PrimeraCompraConsolidadosDiffDTO> getPrimeraCompraConsolidadosDiff(PrimeraCompraConsolidadosDiffFilterDTO filterDTO) throws ExceptionBusiness;

    /**
     * Metodo que consulta la lista de las Primeras Compras Consolidados y sus diferencias con Geopos segun los filtros ingresados, envia un correo con la informacion consultada y guarda en una tabla de auditoria la informacion
     * @author edward.rodriguez@ingeneo.com.co
     * @param PrimeraCompraConsolidadosDiffFilterDTO filterDTO
     * @return List<PrimeraCompraConsolidadosDiffDTO>
     * @throws ExceptionPersistence
     */    
    public List<PrimeraCompraConsolidadosDiffDTO> getPrimeraCompraConsolidadosDiffAndSendEmail(PrimeraCompraConsolidadosDiffFilterDTO filterDTO) throws ExceptionBusiness;
    
    /**
     * Metodo que consulta la lista de los Detalles de Primeras Compras segun los filtros ingresados
     * @author edward.rodriguez@ingeneo.com.co
     * @param PrimeraCompraDetallesFilterDTO filterDTO
     * @return List<PrimeraCompraDetallesDTO>
     * @throws ExceptionBusiness
     */    
    public List<PrimeraCompraDetallesDTO> getPrimeraCompraDetalles(PrimeraCompraDetallesFilterDTO filterDTO) throws ExceptionBusiness;

    /**
     * Metodo que consulta la lista de todas las razones sociales (esta es la empresa cuyo nit es usado para enviar al servicio de geopos) las cuales paracen corresponder a la razon social de la empresa facturadora de cada tienda
     * @author edward.rodriguez@ingeneo.com.co
     * @return List<TtarRazonsocial>
     * @throws ExceptionPersistence
     */    
    public List<TtarRazonsocial> getTtarRazonsocialList() throws ExceptionBusiness;

    /**
     * Metodo que consulta la lista de las tiendas (pos) dado un nmrazonsocial (esta es la clave primaria PK de la tabla de razones sociales, el nit usado para enviar al servicio de geopos sale de la tabla de razones sociales del campo nmrazonsocialConciliadorGP) la cual parece corresponder a la empresa facturadora de esa tienda, si no se ingresa una razón social, se consultan todas las tiendas
     * @author edward.rodriguez@ingeneo.com.co
     * @param String nmrazonsocialConciliadorGP
     * @return List<TtarTienda> TtarTiendaList
     * @throws ExceptionPersistence
     */    
    public List<TtarTienda> getTtarTiendaList(String nmrazonsocial) throws ExceptionBusiness;
    
    
}
