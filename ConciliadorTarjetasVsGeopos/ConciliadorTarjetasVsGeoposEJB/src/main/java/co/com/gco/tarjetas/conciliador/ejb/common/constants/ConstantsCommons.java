/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.gco.tarjetas.conciliador.ejb.common.constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * ConstantsCommons
 * @author edward.rodriguez@ingeneo.com.co
 */
public interface ConstantsCommons {
	
	public String PERSISTENCE_UNIT_NAME = "ConciliadorTarjetasVsGeoposPU";
	public String SCHEMA_TARJETAS = "TARJETAS"; //"TARJETAS";  //"TARJETAS_DLLO";
    public String REST_APP_PATH_CLASS = "conciliador";
    public String REST_APP_PATH_METHOD_RECAUDOS_CONSOLIDADOS_DIFF = "recaudosConsolidadosDiff";
    public String REST_APP_PATH_METHOD_RECAUDOS_CONSOLIDADOS_DIFF_AND_SEND_EMAIL = "recaudosConsolidadosDiffAndSendEmail";
    public String REST_APP_PATH_METHOD_RECAUDOS_DETALLES = "recaudosDetalles";
    public String REST_APP_PATH_METHOD_PRIMERA_COMPRA_CONSOLIDADOS_DIFF = "primeraCompraConsolidadosDiff";
    public String REST_APP_PATH_METHOD_PRIMERA_COMPRA_CONSOLIDADOS_DIFF_AND_SEND_EMAIL = "primeraCompraConsolidadosDiffAndSendEmail";
    public String REST_APP_PATH_METHOD_PRIMERA_COMPRA_DETALLES = "primeraCompraDetalles";
    public String REST_APP_PATH_METHOD_RAZONES_SOCIALES = "razonesSociales";
    public String REST_APP_PATH_METHOD_TIENDAS = "tiendas";    
    public static Logger log = LoggerFactory.getLogger(EnumsCommons.LOGGER_NAME.getValue()/*MyClass.class*/);
    
}
