package co.com.gco.tarjetas.conciliador.ejb.common.dto;

import java.io.Serializable;


/**
 * Recaudos Consolidados Diff Filter DTO.
 * @author edward.rodriguez@ingeneo.com.co
 */

public class RecaudosConsolidadosDiffFilterDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cdrazonsocial;
	private String cdtienda;
	private String fevoucherInicial;
	private String fevoucherFinal;
	private Boolean mostrarSoloDiferencia;
	
    /**
     * Default constructor
     */	
	public RecaudosConsolidadosDiffFilterDTO() {
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
	 * @return the fevoucherInicial
	 */
	public String getFevoucherInicial() {
		return fevoucherInicial;
	}

	/**
	 * @param fevoucherInicial the fevoucherInicial to set
	 */
	public void setFevoucherInicial(String fevoucherInicial) {
		this.fevoucherInicial = fevoucherInicial;
	}

	/**
	 * @return the fevoucherFinal
	 */
	public String getFevoucherFinal() {
		return fevoucherFinal;
	}

	/**
	 * @param fevoucherFinal the fevoucherFinal to set
	 */
	public void setFevoucherFinal(String fevoucherFinal) {
		this.fevoucherFinal = fevoucherFinal;
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