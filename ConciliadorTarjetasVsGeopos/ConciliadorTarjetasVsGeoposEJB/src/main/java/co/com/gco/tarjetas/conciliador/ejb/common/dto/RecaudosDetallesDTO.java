package co.com.gco.tarjetas.conciliador.ejb.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * Recaudos Consolidados DTO.
 * @author edward.rodriguez@ingeneo.com.co
 */
public class RecaudosDetallesDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String vrpagado;
	private String nmvoucherpago;
	private String cddocumento;
	private String nombreCompletoCliente;
	
    /**
     * Default constructor
     */	
	public RecaudosDetallesDTO() {
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
	
	/**
	 * @return the nmvoucherpago
	 */
	public String getNmvoucherpago() {
		return nmvoucherpago;
	}

	/**
	 * @param nmvoucherpago the nmvoucherpago to set
	 */
	public void setNmvoucherpago(String nmvoucherpago) {
		this.nmvoucherpago = nmvoucherpago;
	}

	/**
	 * @return the cddocumento
	 */
	public String getCddocumento() {
		return cddocumento;
	}

	/**
	 * @param cddocumento the cddocumento to set
	 */
	public void setCddocumento(String cddocumento) {
		this.cddocumento = cddocumento;
	}

	/**
	 * @return the nombreCompletoCliente
	 */
	public String getNombreCompletoCliente() {
		return nombreCompletoCliente;
	}

	/**
	 * @param nombreCompletoCliente the nombreCompletoCliente to set
	 */
	public void setNombreCompletoCliente(String nombreCompletoCliente) {
		this.nombreCompletoCliente = nombreCompletoCliente;
	}

}