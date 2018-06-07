/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.gco.tarjetas.conciliador.ejb.common.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraConsolidadosDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraDetallesDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosConsolidadosDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosDetallesDTO;


/**
 * DTOBuilderObjectToDTO
 * @author edward.rodriguez@ingeneo.com.co
 */
public class DTOBuilderObjectToDTO {
    
    /**
     * toRecaudosConsolidadosDTOList
     * @author edward.rodriguez@ingeneo.com.co
     * @param List<Object[]> rows
     * @return List<RecaudosConsolidadosDTO>
     */  	
    public static List<RecaudosConsolidadosDTO> toRecaudosConsolidadosDTOList(List<Object[]> rows) {
    	
    	List<RecaudosConsolidadosDTO> list = new ArrayList<RecaudosConsolidadosDTO>();        
        
        for (Object[] row : rows) {
            list.add(DTOBuilderObjectToDTO.toRecaudosConsolidadosDTO(row));
        }
        return list;
    }
    
    /**
     * toRecaudosConsolidadosDTO
     * @author edward.rodriguez@ingeneo.com.co
     * @param Object[] row
     * @return RecaudosConsolidadosDTO
     */       
    public static RecaudosConsolidadosDTO toRecaudosConsolidadosDTO(Object[] row) {
    	
    	RecaudosConsolidadosDTO dto = new RecaudosConsolidadosDTO();
    	
    	dto.setFevoucher(row[0]!=null? row[0].toString() : null);
    	//dto.setCdrazonsocial(row[x]!=null? row[x].toString() : null);
    	dto.setDsnombre(row[1]!=null? row[1].toString() : null);
    	dto.setCdtienda(row[2]!=null? row[2].toString() : null);
    	dto.setNmcaja(row[3]!=null? row[3].toString() : null);
    	dto.setVrpagado(row[4]!=null? row[4].toString() : null);
    	
        return dto;
    }
    
    /**
     * toRecaudosDetallesDTOList
     * @author edward.rodriguez@ingeneo.com.co
     * @param List<Object[]> rows
     * @return List<RecaudosDetallesDTO>
     */ 
    public static List<RecaudosDetallesDTO> toRecaudosDetallesDTOList(List<Object[]> rows) {
    	
    	List<RecaudosDetallesDTO> list = new ArrayList<RecaudosDetallesDTO>();        
        
        for (Object[] row : rows) {
            list.add(DTOBuilderObjectToDTO.toRecaudosDetallesDTO(row));
        }
        return list;
    }
    
    /**
     * toRecaudosDetallesDTO
     * @author edward.rodriguez@ingeneo.com.co
     * @param Object[] row
     * @return RecaudosDetallesDTO
     */ 
    public static RecaudosDetallesDTO toRecaudosDetallesDTO(Object[] row) {
    	
    	RecaudosDetallesDTO dto = new RecaudosDetallesDTO();
    	
    	dto.setVrpagado(row[0]!=null? row[0].toString() : null);
    	dto.setNmvoucherpago(row[1]!=null? row[1].toString() : null);
    	dto.setCddocumento(row[2]!=null? row[2].toString() : null);
    	dto.setNombreCompletoCliente(row[3]!=null? row[3].toString() : null);
    	
        return dto;
    }
    
    
    /**
     * toPrimeraCompraConsolidadosDTOList
     * @author edward.rodriguez@ingeneo.com.co
     * @param List<Object[]> rows
     * @return List<PrimeraCompraConsolidadosDTO>
     */
    public static List<PrimeraCompraConsolidadosDTO> toPrimeraCompraConsolidadosDTOList(List<Object[]> rows) {
    	
    	List<PrimeraCompraConsolidadosDTO> list = new ArrayList<PrimeraCompraConsolidadosDTO>();        
        
        for (Object[] row : rows) {
            list.add(DTOBuilderObjectToDTO.toPrimeraCompraConsolidadosDTO(row));
        }
        return list;
    }
    
    /**
     * toPrimeraCompraConsolidadosDTO
     * @author edward.rodriguez@ingeneo.com.co
     * @param Object[] row
     * @return PrimeraCompraConsolidadosDTO
     */
    public static PrimeraCompraConsolidadosDTO toPrimeraCompraConsolidadosDTO(Object[] row) {
    	
    	PrimeraCompraConsolidadosDTO dto = new PrimeraCompraConsolidadosDTO();
    	
    	dto.setFeaprobacion(row[0]!=null? row[0].toString() : null);
    	//dto.setCdrazonsocial(row[x]!=null? row[x].toString() : null);
    	dto.setDsnombre(row[1]!=null? row[1].toString() : null);
    	dto.setCdtienda(row[2]!=null? row[2].toString() : null);
    	dto.setNmcaja(row[3]!=null? row[3].toString() : null);
    	dto.setNmmonto(row[4]!=null? new Double(row[4].toString()) : null);
    	
        return dto;
    }
    
    /**
     * toPrimeraCompraDetallesDTOList
     * @author edward.rodriguez@ingeneo.com.co
     * @param List<Object[]> rows
     * @return List<PrimeraCompraDetallesDTO>
     */
    public static List<PrimeraCompraDetallesDTO> toPrimeraCompraDetallesDTOList(List<Object[]> rows) {
    	
    	List<PrimeraCompraDetallesDTO> list = new ArrayList<PrimeraCompraDetallesDTO>();        
        
        for (Object[] row : rows) {
            list.add(DTOBuilderObjectToDTO.toPrimeraCompraDetallesDTO(row));
        }
        return list;
    }
    
    /**
     * toPrimeraCompraDetallesDTO
     * @author edward.rodriguez@ingeneo.com.co
     * @param Object[] row
     * @return PrimeraCompraDetallesDTO
     */
    public static PrimeraCompraDetallesDTO toPrimeraCompraDetallesDTO(Object[] row) {
    	
    	PrimeraCompraDetallesDTO dto = new PrimeraCompraDetallesDTO();
    	
    	dto.setNmmonto(row[0]!=null? new BigDecimal(row[0].toString()) : null);
    	dto.setNmticket(row[1]!=null? row[1].toString() : null);
    	dto.setCdbarras(row[2]!=null? row[2].toString() : null);
    	dto.setCddocumento(row[3]!=null? row[3].toString() : null);
    	dto.setNombreCompletoCliente(row[4]!=null? row[4].toString() : null);
    	
        return dto;
    }
    
    
}
