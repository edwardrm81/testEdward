package co.com.gco.tarjetas.conciliador.ejb.common.dto.geopos;

import java.io.Serializable;

/**
 * Geopos Rest Primera Compra Consolidados DTO
 * 	example response:
 *	 [
 *	 		{"caja":"1","localId":"532","fecMov":"2015-05-21","total":769820.0},
 *	 		{"caja":"2","localId":"533","fecMov":"2015-05-22","total":769821.0},
 *	 		{"caja":"3","localId":"534","fecMov":"2015-05-23","total":769822.0}
 *	 ]
 * @author edward.rodriguez@ingeneo.com.co
 */
public class GeoposRestPrimeraCompraConsolidadosDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String caja;
	private String localId;
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