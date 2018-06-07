package co.com.gco.tarjetas.conciliador.ejb.persistence.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import co.com.gco.tarjetas.conciliador.ejb.common.constants.ConstantsCommons;


/**
 * The persistent class for the TTAR_CONCILIADOR_GEOPOS database table.
 * 
 */
@Entity
@Table(name="TTAR_CONCILIADOR_GEOPOS", schema=ConstantsCommons.SCHEMA_TARJETAS)
@SequenceGenerator(name="SeqTtarConciliadorGeoposId", sequenceName="SEQ_TTAR_CONCILIADOR_GEOPOS_ID", schema=ConstantsCommons.SCHEMA_TARJETAS, allocationSize=1)
@NamedQuery(name="TtarConciliadorGeopos.findAll", query="SELECT t FROM TtarConciliadorGeopos t")
public class TtarConciliadorGeopos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SeqTtarConciliadorGeoposId")
	private Long id;
	
	@Column(name="ID_HEAD")
	private Long idHead;

	@Column(name="FECHA_VENTA")
	private String fechaVenta;
	
	private String cdtienda;
	
	@Column(name="NOMBRE_TIENDA")
	private String nombreTienda;
	
	private String nmcaja;
	
	@Column(name="VALOR_TARJETAS")
	private BigDecimal valorTarjetas;

	@Column(name="VALOR_GEOPOS")
	private BigDecimal valorGeopos;
	
	@Column(name="VALOR_DIFERENCIA")
	private BigDecimal valorDiferencia;

	public TtarConciliadorGeopos() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdHead() {
		return idHead;
	}

	public void setIdHead(Long idHead) {
		this.idHead = idHead;
	}

	public String getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(String fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public String getCdtienda() {
		return cdtienda;
	}

	public void setCdtienda(String cdtienda) {
		this.cdtienda = cdtienda;
	}

	public String getNombreTienda() {
		return nombreTienda;
	}

	public void setNombreTienda(String nombreTienda) {
		this.nombreTienda = nombreTienda;
	}

	public String getNmcaja() {
		return nmcaja;
	}

	public void setNmcaja(String nmcaja) {
		this.nmcaja = nmcaja;
	}

	public BigDecimal getValorTarjetas() {
		return valorTarjetas;
	}

	public void setValorTarjetas(BigDecimal valorTarjetas) {
		this.valorTarjetas = valorTarjetas;
	}

	public BigDecimal getValorGeopos() {
		return valorGeopos;
	}

	public void setValorGeopos(BigDecimal valorGeopos) {
		this.valorGeopos = valorGeopos;
	}

	public BigDecimal getValorDiferencia() {
		return valorDiferencia;
	}

	public void setValorDiferencia(BigDecimal valorDiferencia) {
		this.valorDiferencia = valorDiferencia;
	}
	
	
}