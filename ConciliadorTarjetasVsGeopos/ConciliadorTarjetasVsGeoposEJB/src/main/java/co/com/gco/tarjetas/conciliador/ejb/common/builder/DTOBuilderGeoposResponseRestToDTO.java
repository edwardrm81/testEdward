/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.gco.tarjetas.conciliador.ejb.common.builder;

import java.util.ArrayList;
import java.util.List;

import co.com.gco.tarjetas.conciliador.ejb.common.dto.geopos.GeoposRestRecaudosConsolidadosDTO;

/**
 * DTOBuilderGeoposResponseRestToDTO
 * @author edward.rodriguez@ingeneo.com.co
 */
public class DTOBuilderGeoposResponseRestToDTO {

    /**
     * toGeoposRestRecaudosConsolidadosDTOList
     * @author edward.rodriguez@ingeneo.com.co
     * @param List<List<Object>> responseRest
     * @return List<GeoposRestRecaudosConsolidadosDTO>
     */
    public static List<GeoposRestRecaudosConsolidadosDTO> toGeoposRestRecaudosConsolidadosDTOList(List<List<Object>> responseRest) {
    	
    	List<GeoposRestRecaudosConsolidadosDTO> list = new ArrayList<GeoposRestRecaudosConsolidadosDTO>();        
        
        for (List<Object> objList : responseRest) {
            list.add(DTOBuilderGeoposResponseRestToDTO.toGeoposRestRecaudosConsolidadosDTO(objList));
        }
        return list;
        
    }
    
    /**
     * toGeoposRestRecaudosConsolidadosDTO
     * @author edward.rodriguez@ingeneo.com.co
     * @param List<Object> objList
     * @return GeoposRestRecaudosConsolidadosDTO
     */       
    public static GeoposRestRecaudosConsolidadosDTO toGeoposRestRecaudosConsolidadosDTO(List<Object> objList) {
    	
    	GeoposRestRecaudosConsolidadosDTO dto = new GeoposRestRecaudosConsolidadosDTO();
    	
    	dto.setLocalId(objList.get(0)!=null? objList.get(0).toString() : null);
    	dto.setCaja(objList.get(1)!=null? objList.get(1).toString() : null);
    	dto.setFecMov(objList.get(2)!=null? objList.get(2).toString() : null);
    	dto.setTotal(objList.get(3)!=null? Double.valueOf(objList.get(3).toString()) : null);
    	
        return dto;
    }
    
    
}
