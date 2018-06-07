package co.com.gco.tarjetas.conciliador.ejb.common.dto;

import java.io.Serializable;


/**
 * Recaudos Consolidados DTO.
 * @author edward.rodriguez@ingeneo.com.co
 */

public class RecaudosConsolidadosDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String cdtienda;
	private String dsnombre;	
	private String fevoucher;
	private String nmcaja;
	private String vrpagado;
	
    /**
     * Default constructor
     */	
	public RecaudosConsolidadosDTO() {
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

	/**
	 * @return the vrpagado
	 */
	public String getVrpagado() {
		return vrpagado;
	}

	/**
	 * @param vrpagado the vrpagado to set
	 */
	public void setVrpagado(String vrpagado) {
		this.vrpagado = vrpagado;
	}

}