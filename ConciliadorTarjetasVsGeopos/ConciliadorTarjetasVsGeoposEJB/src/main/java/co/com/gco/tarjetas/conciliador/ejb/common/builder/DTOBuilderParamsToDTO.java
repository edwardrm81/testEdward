/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.gco.tarjetas.conciliador.ejb.common.builder;

import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraConsolidadosDiffFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraDetallesFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosConsolidadosDiffFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosDetallesFilterDTO;

/**
 * DTOBuilderParamsToDTO
 * @author edward.rodriguez@ingeneo.com.co
 */
public class DTOBuilderParamsToDTO {

    /**
     * toRecaudosConsolidadosDiffFilterDTO
     * @author edward.rodriguez@ingeneo.com.co
     * @param String cdrazonsocial
     * @param String cdtienda
     * @param String fechaInicial
     * @param String fechaFinal
     * @param Boolean mostrarSoloDiferencia
     * @return RecaudosConsolidadosDiffFilterDTO
     */
    public static RecaudosConsolidadosDiffFilterDTO toRecaudosConsolidadosDiffFilterDTO(String cdrazonsocial, String cdtienda, String fechaInicial, String fechaFinal, Boolean mostrarSoloDiferencia) {
    	
    	RecaudosConsolidadosDiffFilterDTO dto = new RecaudosConsolidadosDiffFilterDTO();
    	
    	dto.setCdrazonsocial(cdrazonsocial);
    	dto.setCdtienda(cdtienda);
    	dto.setFevoucherInicial(fechaInicial);
    	dto.setFevoucherFinal(fechaFinal);
    	dto.setMostrarSoloDiferencia(mostrarSoloDiferencia);
    	
        return dto;
    }

    /**
     * toRecaudosDetallesFilterDTO
     * @author edward.rodriguez@ingeneo.com.co
     * @param String cdtienda
     * @param String fecha
     * @param String nmcaja
     * @return RecaudosDetallesFilterDTO
     */
    public static RecaudosDetallesFilterDTO toRecaudosDetallesFilterDTO(String cdtienda, String fecha, String nmcaja) {
    	    	
    	RecaudosDetallesFilterDTO dto = new RecaudosDetallesFilterDTO();
    	
    	dto.setCdtienda(cdtienda);
    	dto.setFevoucher(fecha);
    	dto.setNmcaja(nmcaja);
    	
        return dto;
    }    
    
    /**
     * toPrimeraCompraConsolidadosDiffFilterDTO
     * @author edward.rodriguez@ingeneo.com.co
     * @param String cdrazonsocial
     * @param String cdtienda
     * @param String fechaInicial
     * @param String fechaFinal
     * @param Boolean mostrarSoloDiferencia
     * @return PrimeraCompraConsolidadosDiffFilterDTO
     */
    public static PrimeraCompraConsolidadosDiffFilterDTO toPrimeraCompraConsolidadosDiffFilterDTO(String cdrazonsocial, String cdtienda, String fechaInicial, String fechaFinal, Boolean mostrarSoloDiferencia) {
    	
    	PrimeraCompraConsolidadosDiffFilterDTO dto = new PrimeraCompraConsolidadosDiffFilterDTO();
    	
    	dto.setCdrazonsocial(cdrazonsocial);
    	dto.setCdtienda(cdtienda);
    	dto.setFeaprobacionInicial(fechaInicial);
    	dto.setFeaprobacionFinal(fechaFinal);
    	dto.setMostrarSoloDiferencia(mostrarSoloDiferencia);
    	
        return dto;
    }
    
    /**
     * toPrimeraCompraDetallesFilterDTO
     * @author edward.rodriguez@ingeneo.com.co
     * @param String cdtienda
     * @param String fecha
     * @param String nmcaja
     * @return PrimeraCompraDetallesFilterDTO
     */
    public static PrimeraCompraDetallesFilterDTO toPrimeraCompraDetallesFilterDTO(String cdtienda, String fecha, String nmcaja) {
    	
    	PrimeraCompraDetallesFilterDTO dto = new PrimeraCompraDetallesFilterDTO();
    	
    	dto.setCdtienda(cdtienda);
    	dto.setFeaprobacion(fecha);
    	dto.setNmcaja(nmcaja);
    	
        return dto;
    }
    
    
}
