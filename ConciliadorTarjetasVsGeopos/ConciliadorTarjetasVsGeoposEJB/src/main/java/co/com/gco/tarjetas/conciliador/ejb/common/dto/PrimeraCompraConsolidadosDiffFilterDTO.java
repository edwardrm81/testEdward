package co.com.gco.tarjetas.conciliador.ejb.common.dto;

import java.io.Serializable;


/**
 * Primera Compra Consolidados Diff Filter DTO.
 * @author edward.rodriguez@ingeneo.com.co
 */

public class PrimeraCompraConsolidadosDiffFilterDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cdrazonsocial;
	private String cdtienda;
	private String feaprobacionInicial;
	private String feaprobacionFinal;
	private Boolean mostrarSoloDiferencia;
	
    /**
     * Default constructor
     */	
	public PrimeraCompraConsolidadosDiffFilterDTO() {
	}

	/**
	 * @return the cdrazonsocial
	 */
	public String getCdrazonsocial() {
		return cdrazonsocial;
	}

	/**
	 * @param cdrazonsocial the cdrazonsocial to set
	 */
	public void setCdrazonsocial(String cdrazonsocial) {
		this.cdrazonsocial = cdrazonsocial;
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
	 * @return the feaprobacionInicial
	 */
	public String getFeaprobacionInicial() {
		return feaprobacionInicial;
	}

	/**
	 * @param feaprobacionInicial the feaprobacionInicial to set
	 */
	public void setFeaprobacionInicial(String feaprobacionInicial) {
		this.feaprobacionInicial = feaprobacionInicial;
	}

	/**
	 * @return the feaprobacionFinal
	 */
	public String getFeaprobacionFinal() {
		return feaprobacionFinal;
	}

	/**
	 * @param feaprobacionFinal the feaprobacionFinal to set
	 */
	public void setFeaprobacionFinal(String feaprobacionFinal) {
		this.feaprobacionFinal = feaprobacionFinal;
	}

	/**
	 * @return the mostrarSoloDiferencia
	 */
	public Boolean getMostrarSoloDiferencia() {
		return mostrarSoloDiferencia;
	}

	/**
	 * @param mostrarSoloDiferencia the mostrarSoloDiferencia to set
	 */
	public void setMostrarSoloDiferencia(Boolean mostrarSoloDiferencia) {
		this.mostrarSoloDiferencia = mostrarSoloDiferencia;
	}


	

}