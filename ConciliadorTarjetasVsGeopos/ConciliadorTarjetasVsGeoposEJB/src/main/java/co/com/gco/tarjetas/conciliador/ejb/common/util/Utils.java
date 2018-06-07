package co.com.gco.tarjetas.conciliador.ejb.common.util;

import java.util.List;

import co.com.gco.tarjetas.conciliador.ejb.common.constants.EnumsCommons;
import co.com.gco.tarjetas.conciliador.ejb.common.constants.MessageCodesI18N;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraConsolidadosDiffDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraConsolidadosDiffFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosConsolidadosDiffDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosConsolidadosDiffFilterDTO;


/**
 * Utilis 
 * @author edward.rodriguez@ingeneo.com.co
 *
 */
public class Utils {
    	
    /**
     * Method to return recaudosConsolidadosDiffDTOList to HTML format
     * @param List<RecaudosConsolidadosDiffDTO> recaudosConsolidadosDiffDTOList
     * @param RecaudosConsolidadosDiffFilterDTO filterDTO
     * @return String html
     */    
    public static String getRecaudosConsolidadosDiffDTOListToHtml (List<RecaudosConsolidadosDiffDTO> recaudosConsolidadosDiffDTOList, RecaudosConsolidadosDiffFilterDTO filterDTO) { 
    	
    	StringBuffer html = new StringBuffer("");
    	
    	html.append(PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_MAIN_WINDOW_HEAD));
    	html.append(EnumsCommons.HTML_BR.getValue());
    	html.append(EnumsCommons.HTML_BR.getValue());
    	
    	html.append(PropertiesLoad.getSectionPropertieParam(
    															MessageCodesI18N.MESSAGE_LABEL_EMAIL_MAIN_WINDOW_FILTERS,
    															filterDTO.getCdrazonsocial()!=null? filterDTO.getCdrazonsocial() : PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_ALL_COMPANIES), 
    															filterDTO.getCdtienda()!=null? filterDTO.getCdtienda() : PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_NO_SELECTED),
    															filterDTO.getFevoucherInicial()!=null? filterDTO.getFevoucherInicial() : "",
    															filterDTO.getFevoucherFinal()!=null? filterDTO.getFevoucherFinal() : "",
    															PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_FORMA_PAGO_RECAUDOS_TARJETA),
    															(filterDTO.getMostrarSoloDiferencia()==null || !filterDTO.getMostrarSoloDiferencia()) ? PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_NO) : PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_YES)   
    															
    														));
    	html.append(EnumsCommons.HTML_BR.getValue());
    	html.append(EnumsCommons.HTML_BR.getValue());
    	
    	html.append(EnumsCommons.HTML_TABLE.getValue());
    	
    	html.append(
    					EnumsCommons.HTML_TR.getValue() +
							EnumsCommons.HTML_TD.getValue() + PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_DATE) + EnumsCommons.HTML_TD_C.getValue() + 
							EnumsCommons.HTML_TD.getValue() + PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_POS_NAME) + EnumsCommons.HTML_TD_C.getValue() + 
							EnumsCommons.HTML_TD.getValue() + PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_POS_CODE) + EnumsCommons.HTML_TD_C.getValue() + 
							EnumsCommons.HTML_TD.getValue() + PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_CASH_REGISTER) + EnumsCommons.HTML_TD_C.getValue() +
							EnumsCommons.HTML_TD.getValue() + PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_PAID_VALUE_CARD) + EnumsCommons.HTML_TD_C.getValue() +
							EnumsCommons.HTML_TD.getValue() + PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_PAID_VALUE_GEOPOS) + EnumsCommons.HTML_TD_C.getValue() +
							EnumsCommons.HTML_TD.getValue() + PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_PAID_VALUE_DIFFERENCE) + EnumsCommons.HTML_TD_C.getValue() +
						EnumsCommons.HTML_TR_C.getValue()
		);
		
    	for (RecaudosConsolidadosDiffDTO dto : recaudosConsolidadosDiffDTOList) {
		    
    		html.append(
    					EnumsCommons.HTML_TR.getValue() +
				    		EnumsCommons.HTML_TD.getValue() + dto.getFecha() + EnumsCommons.HTML_TD_C.getValue() + 
				    		EnumsCommons.HTML_TD.getValue() + dto.getDsnombre() + EnumsCommons.HTML_TD_C.getValue() + 
				    		EnumsCommons.HTML_TD.getValue() + dto.getCdtienda() + EnumsCommons.HTML_TD_C.getValue() + 
				    		EnumsCommons.HTML_TD.getValue() + dto.getNmcaja() + EnumsCommons.HTML_TD_C.getValue() +
				    		EnumsCommons.HTML_TD.getValue() + dto.getVrpagadoTarjetas() + EnumsCommons.HTML_TD_C.getValue() +
				    		EnumsCommons.HTML_TD.getValue() + dto.getVrpagadoGeopos() + EnumsCommons.HTML_TD_C.getValue() +
				    		EnumsCommons.HTML_TD.getValue() + dto.getVrpagadoDiferencia() + EnumsCommons.HTML_TD_C.getValue() +
				    	EnumsCommons.HTML_TR_C.getValue()
    		);
		}
    	
    	html.append(EnumsCommons.HTML_TABLE_C.getValue());
    	
    	if(recaudosConsolidadosDiffDTOList.size()==0) {
    		html.append(EnumsCommons.HTML_TABLE.getValue());
    			html.append(EnumsCommons.HTML_TR.getValue());
    				html.append(EnumsCommons.HTML_TD.getValue());
    					html.append(PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_NOT_RESULT_TO_SHOW));
    				html.append(EnumsCommons.HTML_TD_C.getValue());
    			html.append(EnumsCommons.HTML_TR_C.getValue());
    		html.append(EnumsCommons.HTML_TABLE_C.getValue());
    	}
    	
        return html.toString();        
    }
    
    /**
     * Method to return primeraCompraConsolidadosDiffDTOList to HTML format
     * @param List<PrimeraCompraConsolidadosDiffDTO> primeraCompraConsolidadosDiffDTOList
     * @param PrimeraCompraConsolidadosDiffFilterDTO filterDTO
     * @return String html
     */    
    public static String getPrimeraCompraConsolidadosDiffDTOListToHtml (List<PrimeraCompraConsolidadosDiffDTO> primeraCompraConsolidadosDiffDTOList, PrimeraCompraConsolidadosDiffFilterDTO filterDTO) {
    	
    	StringBuffer html = new StringBuffer("");
    	
    	html.append(PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_MAIN_WINDOW_HEAD));
    	html.append(EnumsCommons.HTML_BR.getValue());
    	html.append(EnumsCommons.HTML_BR.getValue());
    	
    	html.append(PropertiesLoad.getSectionPropertieParam(
    															MessageCodesI18N.MESSAGE_LABEL_EMAIL_MAIN_WINDOW_FILTERS,
    															filterDTO.getCdrazonsocial()!=null? filterDTO.getCdrazonsocial() : PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_ALL_COMPANIES), 
    															filterDTO.getCdtienda()!=null? filterDTO.getCdtienda() : PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_NO_SELECTED),
    															filterDTO.getFeaprobacionInicial()!=null? filterDTO.getFeaprobacionInicial() : "",
    															filterDTO.getFeaprobacionFinal()!=null? filterDTO.getFeaprobacionFinal() : "",
    															PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_FORMA_PAGO_PRIMERA_COMPRA),
    															(filterDTO.getMostrarSoloDiferencia()==null || !filterDTO.getMostrarSoloDiferencia()) ? PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_NO) : PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_YES)   
    															
    														));
    	html.append(EnumsCommons.HTML_BR.getValue());
    	html.append(EnumsCommons.HTML_BR.getValue());
    	
    	html.append(EnumsCommons.HTML_TABLE.getValue());
    	
    	html.append(
    					EnumsCommons.HTML_TR.getValue() +
							EnumsCommons.HTML_TD.getValue() + PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_DATE) + EnumsCommons.HTML_TD_C.getValue() + 
							EnumsCommons.HTML_TD.getValue() + PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_POS_NAME) + EnumsCommons.HTML_TD_C.getValue() + 
							EnumsCommons.HTML_TD.getValue() + PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_POS_CODE) + EnumsCommons.HTML_TD_C.getValue() + 
							EnumsCommons.HTML_TD.getValue() + PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_CASH_REGISTER) + EnumsCommons.HTML_TD_C.getValue() +
							EnumsCommons.HTML_TD.getValue() + PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_PAID_VALUE_CARD) + EnumsCommons.HTML_TD_C.getValue() +
							EnumsCommons.HTML_TD.getValue() + PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_PAID_VALUE_GEOPOS) + EnumsCommons.HTML_TD_C.getValue() +
							EnumsCommons.HTML_TD.getValue() + PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_PAID_VALUE_DIFFERENCE) + EnumsCommons.HTML_TD_C.getValue() +
						EnumsCommons.HTML_TR_C.getValue()
		);
		
    	for (PrimeraCompraConsolidadosDiffDTO dto : primeraCompraConsolidadosDiffDTOList) {
		    
    		html.append(
    					EnumsCommons.HTML_TR.getValue() +
				    		EnumsCommons.HTML_TD.getValue() + dto.getFecha() + EnumsCommons.HTML_TD_C.getValue() + 
				    		EnumsCommons.HTML_TD.getValue() + dto.getDsnombre() + EnumsCommons.HTML_TD_C.getValue() + 
				    		EnumsCommons.HTML_TD.getValue() + dto.getCdtienda() + EnumsCommons.HTML_TD_C.getValue() + 
				    		EnumsCommons.HTML_TD.getValue() + dto.getNmcaja() + EnumsCommons.HTML_TD_C.getValue() +
				    		EnumsCommons.HTML_TD.getValue() + dto.getVrpagadoTarjetas() + EnumsCommons.HTML_TD_C.getValue() +
				    		EnumsCommons.HTML_TD.getValue() + dto.getVrpagadoGeopos() + EnumsCommons.HTML_TD_C.getValue() +
				    		EnumsCommons.HTML_TD.getValue() + dto.getVrpagadoDiferencia() + EnumsCommons.HTML_TD_C.getValue() +
				    	EnumsCommons.HTML_TR_C.getValue()
    		);
		}
    	
    	html.append(EnumsCommons.HTML_TABLE_C.getValue());
    	
    	if(primeraCompraConsolidadosDiffDTOList.size()==0) {
    		html.append(EnumsCommons.HTML_TABLE.getValue());
    			html.append(EnumsCommons.HTML_TR.getValue());
    				html.append(EnumsCommons.HTML_TD.getValue());
    					html.append(PropertiesLoad.getSectionPropertie(MessageCodesI18N.MESSAGE_LABEL_NOT_RESULT_TO_SHOW));
    				html.append(EnumsCommons.HTML_TD_C.getValue());
    			html.append(EnumsCommons.HTML_TR_C.getValue());
    		html.append(EnumsCommons.HTML_TABLE_C.getValue());
    	}
    	
        return html.toString();        
    }
    
     
}
