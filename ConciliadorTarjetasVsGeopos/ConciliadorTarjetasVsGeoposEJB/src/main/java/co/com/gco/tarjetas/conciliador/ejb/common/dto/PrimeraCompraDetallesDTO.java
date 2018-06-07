package co.com.gco.tarjetas.conciliador.ejb.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * Primera Compra Detalles DTO.
 * @author edward.rodriguez@ingeneo.com.co
 */

public class PrimeraCompraDetallesDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private BigDecimal nmmonto;
	private String nmticket;
	private String cdbarras;
	private String cddocumento;
	private String nombreCompletoCliente;
	
    /**
     * Default constructor
     */	
	public PrimeraCompraDetallesDTO() {
	
	}

	/**
	 * @return the nmmonto
	 */
	public BigDecimal getNmmonto() {
		return nmmonto;
	}

	/**
	 * @param nmmonto the nmmonto to set
	 */
	public void setNmmonto(BigDecimal nmmonto) {
		this.nmmonto = nmmonto;
	}

	/**
	 * @return the nmticket
	 */
	public String getNmticket() {
		return nmticket;
	}

	/**
	 * @param nmticket the nmticket to set
	 */
	public void setNmticket(String nmticket) {
		this.nmticket = nmticket;
	}

	/**
	 * @return the cdbarras
	 */
	public String getCdbarras() {
		return cdbarras;
	}

	/**
	 * @param cdbarras the cdbarras to set
	 */
	public void setCdbarras(String cdbarras) {
		this.cdbarras = cdbarras;
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