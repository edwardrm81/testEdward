package co.com.gco.tarjetas.conciliador.ejb.common.dto;

import java.io.Serializable;


/**
 * Recaudos Consolidados Diff DTO.
 * @author edward.rodriguez@ingeneo.com.co
 */

public class RecaudosDetallesFilterDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String cdtienda;
	private String fevoucher;
	private String nmcaja;
	
    /**
     * Default constructor
     */	
	public RecaudosDetallesFilterDTO() {
	}

	/**
	 * @return the cdtienda
	 */
	public String getCdtienda() {
		return cdtienda;
	}

	/**
	 * @param cdtienda the cdtienda to set
	 */
	public void setCdtienda(String cdtienda) {
		this.cdtienda = cdtienda;
	}

	/**
	 * @return the fevoucher
	 */
	public String getFevoucher() {
		return fevoucher;
	}

	/**
	 * @param fevoucher the fevoucher to set
	 */
	public void setFevoucher(String fevoucher) {
		this.fevoucher = fevoucher;
	}

	/**
	 * @return the nmcaja
	 */
	public String getNmcaja() {
		return nmcaja;
	}

	/**
	 * @param nmcaja the nmcaja to set
	 */
	public void setNmcaja(String nmcaja) {
		this.nmcaja = nmcaja;
	}	
	

}