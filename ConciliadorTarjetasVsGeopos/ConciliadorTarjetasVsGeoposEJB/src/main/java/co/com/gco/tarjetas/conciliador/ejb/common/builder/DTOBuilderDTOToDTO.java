/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.gco.tarjetas.conciliador.ejb.common.builder;

import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraConsolidadosDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraConsolidadosDiffDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraConsolidadosDiffFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraConsolidadosFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosConsolidadosDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosConsolidadosDiffDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosConsolidadosDiffFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosConsolidadosFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.geopos.GeoposRestConsolidadosFilterDTO;


/**
 * DTOBuilderDTOToDTO
 * @author edward.rodriguez@ingeneo.com.co
 */
public class DTOBuilderDTOToDTO {
        
    /**
     * tranform RecaudosConsolidadosDiffFilterDTO to GeoposRestConsolidadosFilterDTO
     * @author edward.rodriguez@ingeneo.com.co
     * @param RecaudosConsolidadosDiffFilterDTO dtoInput
     * @return GeoposRestConsolidadosFilterDTO
     */
    public static GeoposRestConsolidadosFilterDTO recaudosConsolidadosDiffFilterDTOtoGeoposRestConsolidadosFilterDTO(RecaudosConsolidadosDiffFilterDTO dtoInput) {
    	
    	GeoposRestConsolidadosFilterDTO dtoOutput = new GeoposRestConsolidadosFilterDTO();
    	
    	//dtoOutput.setType(?);
    	//dtoOutput.setCode(?);
    	dtoOutput.setNit(dtoInput.getCdrazonsocial());
    	dtoOutput.setLocalid(dtoInput.getCdtienda());
    	dtoOutput.setDatIni(dtoInput.getFevoucherInicial());
    	dtoOutput.setDatEnd(dtoInput.getFevoucherFinal());
    	
        return dtoOutput;
    }
    
    
    /**
     * tranform RecaudosConsolidadosDiffFilterDTO to RecaudosConsolidadosFilterDTO
     * @author edward.rodriguez@ingeneo.com.co
     * @param RecaudosConsolidadosDiffFilterDTO dtoInput
     * @return RecaudosConsolidadosFilterDTO
     */       
    public static RecaudosConsolidadosFilterDTO recaudosConsolidadosDiffFilterDTOtoRecaudosConsolidadosFilterDTO(RecaudosConsolidadosDiffFilterDTO dtoInput) {
    	
    	RecaudosConsolidadosFilterDTO dtoOutput = new RecaudosConsolidadosFilterDTO();
    	
    	dtoOutput.setCdrazonsocial(dtoInput.getCdrazonsocial());
    	dtoOutput.setCdtienda(dtoInput.getCdtienda());
    	dtoOutput.setFevoucherInicial(dtoInput.getFevoucherInicial());
    	dtoOutput.setFevoucherFinal(dtoInput.getFevoucherFinal());
    	
        return dtoOutput;
    }
    
    /**
     * tranform PrimeraCompraConsolidadosDiffFilterDTO to PrimeraCompraConsolidadosFilterDTO
     * @author edward.rodriguez@ingeneo.com.co
     * @param PrimeraCompraConsolidadosDiffFilterDTO dtoInput
     * @return PrimeraCompraConsolidadosFilterDTO
     */       
    public static PrimeraCompraConsolidadosFilterDTO primeraCompraConsolidadosDiffFilterDTOtoPrimeraCompraConsolidadosFilterDTO(PrimeraCompraConsolidadosDiffFilterDTO dtoInput) {
    	
    	PrimeraCompraConsolidadosFilterDTO dtoOutput = new PrimeraCompraConsolidadosFilterDTO();
    	
    	dtoOutput.setCdrazonsocial(dtoInput.getCdrazonsocial());
    	dtoOutput.setCdtienda(dtoInput.getCdtienda());
    	dtoOutput.setFeaprobacionInicial(dtoInput.getFeaprobacionInicial());
    	dtoOutput.setFeaprobacionFinal(dtoInput.getFeaprobacionFinal());
    	
        return dtoOutput;
    }
    
    
    /**
     * tranform PrimeraCompraConsolidadosDiffFilterDTO to GeoposRestConsolidadosFilterDTO
     * @author edward.rodriguez@ingeneo.com.co
     * @param PrimeraCompraConsolidadosDiffFilterDTO dtoInput
     * @return GeoposRestConsolidadosFilterDTO
     */
    public static GeoposRestConsolidadosFilterDTO primeraCompraConsolidadosDiffFilterDTOtoGeoposRestConsolidadosFilterDTO(PrimeraCompraConsolidadosDiffFilterDTO dtoInput) {
    	
    	GeoposRestConsolidadosFilterDTO dtoOutput = new GeoposRestConsolidadosFilterDTO();
    	
    	//dtoOutput.setType(?);
    	//dtoOutput.setCode(?);
    	dtoOutput.setNit(dtoInput.getCdrazonsocial());
    	dtoOutput.setLocalid(dtoInput.getCdtienda());
    	dtoOutput.setDatIni(dtoInput.getFeaprobacionInicial());
    	dtoOutput.setDatEnd(dtoInput.getFeaprobacionFinal());
    	
        return dtoOutput;
    }
    
    /**
     * tranform RecaudosConsolidadosDTO to RecaudosConsolidadosDiffDTO
     * @author edward.rodriguez@ingeneo.com.co
     * @param RecaudosConsolidadosDTO dtoInput
     * @return RecaudosConsolidadosDiffDTO
     */
    public static RecaudosConsolidadosDiffDTO recaudosConsolidadosDTOtoRecaudosConsolidadosDiffDTO(RecaudosConsolidadosDTO dtoInput) {
    	
    	RecaudosConsolidadosDiffDTO dtoOutput = new RecaudosConsolidadosDiffDTO();
    	
    	dtoOutput.setCdtienda(dtoInput.getCdtienda());
    	dtoOutput.setDsnombre(dtoInput.getDsnombre());	
    	dtoOutput.setFecha(dtoInput.getFevoucher());
    	dtoOutput.setNmcaja(dtoInput.getNmcaja());
    	dtoOutput.setVrpagadoTarjetas( dtoInput.getVrpagado()!=null? Double.valueOf(dtoInput.getVrpagado()) : null );
    	
        return dtoOutput;
    }
    
    /**
     * tranform PrimeraCompraConsolidadosDTO to PrimeraCompraConsolidadosDiffDTO
     * @author edward.rodriguez@ingeneo.com.co
     * @param PrimeraCompraConsolidadosDTO dtoInput
     * @return PrimeraCompraConsolidadosDiffDTO
     */
    public static PrimeraCompraConsolidadosDiffDTO primeraCompraConsolidadosDTOtoPrimeraCompraConsolidadosDiffDTO(PrimeraCompraConsolidadosDTO dtoInput) {
    	
    	PrimeraCompraConsolidadosDiffDTO dtoOutput = new PrimeraCompraConsolidadosDiffDTO();
    	
    	dtoOutput.setCdtienda(dtoInput.getCdtienda());
    	dtoOutput.setDsnombre(dtoInput.getDsnombre());	
    	dtoOutput.setFecha(dtoInput.getFeaprobacion());
    	dtoOutput.setNmcaja(dtoInput.getNmcaja());
    	dtoOutput.setVrpagadoTarjetas(dtoInput.getNmmonto());
    	
        return dtoOutput;
    }
    
    
}
