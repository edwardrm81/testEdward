package co.com.gco.tarjetas.conciliador.ejb.common.dto.geopos;

import java.io.Serializable;

/**
 * Geopos Rest Recaudos Consolidados DTO
 *   example response:
 *   [
 *   		["689","1","2015-05-21",212532.0],
 *   		["689","2","2015-05-22",212533.0],
 *   		["689","3","2015-05-23",212534.0]
 *   ]
 * @author edward.rodriguez@ingeneo.com.co
 */

public class GeoposRestRecaudosConsolidadosDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String localId;
	private String caja;
	private String fecMov;
	private Double total;
	
	/**
	 * @return the caja
	 */
	public String getCaja() {
		return caja;
	}
	/**
	 * @param caja the caja to set
	 */
	public void setCaja(String caja) {
		this.caja = caja;
	}
	/**
	 * @return the localId
	 */
	public String getLocalId() {
		return localId;
	}
	/**
	 * @param localId the localId to set
	 */
	public void setLocalId(String localId) {
		this.localId = localId;
	}
	/**
	 * @return the fecMov
	 */
	public String getFecMov() {
		return fecMov;
	}
	/**
	 * @param fecMov the fecMov to set
	 */
	public void setFecMov(String fecMov) {
		this.fecMov = fecMov;
	}
	/**
	 * @return the total
	 */
	public Double getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(Double total) {
		this.total = total;
	}	

}