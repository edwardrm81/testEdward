package co.com.gco.tarjetas.conciliador.ejb.common.dto.geopos;

import java.io.Serializable;


/**
 * Geopos Rest Consolidados Filter DTO
 * @author edward.rodriguez@ingeneo.com.co
 */

public class GeoposRestConsolidadosFilterDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String type;
	private String code;
	private String nit;
	private String localid;
	private String datIni;
	private String datEnd;
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the nit
	 */
	public String getNit() {
		return nit;
	}
	/**
	 * @param nit the nit to set
	 */
	public void setNit(String nit) {
		this.nit = nit;
	}
	/**
	 * @return the localid
	 */
	public String getLocalid() {
		return localid;
	}
	/**
	 * @param localid the localid to set
	 */
	public void setLocalid(String localid) {
		this.localid = localid;
	}
	/**
	 * @return the datIni
	 */
	public String getDatIni() {
		return datIni;
	}
	/**
	 * @param datIni the datIni to set
	 */
	public void setDatIni(String datIni) {
		this.datIni = datIni;
	}
	/**
	 * @return the datEnd
	 */
	public String getDatEnd() {
		return datEnd;
	}
	/**
	 * @param datEnd the datEnd to set
	 */
	public void setDatEnd(String datEnd) {
		this.datEnd = datEnd;
	}	
	
	

}