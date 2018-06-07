package co.com.gco.tarjetas.conciliador.ejb.common.dto;

import java.io.Serializable;


/**
 * Recaudos Consolidados Diff DTO.
 * @author edward.rodriguez@ingeneo.com.co
 */

public class PrimeraCompraDetallesFilterDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String cdtienda;
	private String feaprobacion;
	private String nmcaja;
	
    /**
     * Default constructor
     */	
	public PrimeraCompraDetallesFilterDTO() {
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
	 * @return the feaprobacion
	 */
	public String getFeaprobacion() {
		return feaprobacion;
	}

	/**
	 * @param feaprobacion the feaprobacion to set
	 */
	public void setFeaprobacion(String feaprobacion) {
		this.feaprobacion = feaprobacion;
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