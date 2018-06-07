package co.com.gco.tarjetas.conciliador.ejb.common.dto;

import java.io.Serializable;


/**
 * Primera Compra Consolidados DTO.
 * @author edward.rodriguez@ingeneo.com.co
 */

public class PrimeraCompraConsolidadosDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String cdtienda;
	private String dsnombre;	
	private String feaprobacion;
	private String nmcaja;
	private Double nmmonto;
	
    /**
     * Default constructor
     */	
	public PrimeraCompraConsolidadosDTO() {
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
	 * @return the dsnombre
	 */
	public String getDsnombre() {
		return dsnombre;
	}

	/**
	 * @param dsnombre the dsnombre to set
	 */
	public void setDsnombre(String dsnombre) {
		this.dsnombre = dsnombre;
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

	/**
	 * @return the nmmonto
	 */
	public Double getNmmonto() {
		return nmmonto;
	}

	/**
	 * @param nmmonto the nmmonto to set
	 */
	public void setNmmonto(Double nmmonto) {
		this.nmmonto = nmmonto;
	}	

}