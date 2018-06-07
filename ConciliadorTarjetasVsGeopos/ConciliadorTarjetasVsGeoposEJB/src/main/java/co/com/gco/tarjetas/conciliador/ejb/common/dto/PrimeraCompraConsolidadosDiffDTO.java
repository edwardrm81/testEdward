package co.com.gco.tarjetas.conciliador.ejb.common.dto;

import java.io.Serializable;


/**
 * Primera Compra Consolidados DTO.
 * @author edward.rodriguez@ingeneo.com.co
 */

public class PrimeraCompraConsolidadosDiffDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String cdtienda;
	private String dsnombre;
	/* feaprobacion: */
	private String fecha;
	private String nmcaja;
	private Double vrpagadoTarjetas;
	private Double vrpagadoGeopos;
	private Double vrpagadoDiferencia;
	
    /**
     * Default constructor
     */	
	public PrimeraCompraConsolidadosDiffDTO() {
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
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param feaprobacion the feaprobacion to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
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
	 * @return the vrpagadoTarjetas
	 */
	public Double getVrpagadoTarjetas() {
		return vrpagadoTarjetas;
	}

	/**
	 * @param vrpagadoTarjetas the vrpagadoTarjetas to set
	 */
	public void setVrpagadoTarjetas(Double vrpagadoTarjetas) {
		this.vrpagadoTarjetas = vrpagadoTarjetas;
	}

	/**
	 * @return the vrpagadoGeopos
	 */
	public Double getVrpagadoGeopos() {
		return vrpagadoGeopos;
	}

	/**
	 * @param vrpagadoGeopos the vrpagadoGeopos to set
	 */
	public void setVrpagadoGeopos(Double vrpagadoGeopos) {
		this.vrpagadoGeopos = vrpagadoGeopos;
	}

	/**
	 * @return the vrpagadoDiferencia
	 */
	public Double getVrpagadoDiferencia() {
		return vrpagadoDiferencia;
	}

	/**
	 * @param vrpagadoDiferencia the vrpagadoDiferencia to set
	 */
	public void setVrpagadoDiferencia(Double vrpagadoDiferencia) {
		this.vrpagadoDiferencia = vrpagadoDiferencia;
	}
	
	

}