package co.com.gco.tarjetas.conciliador.ejb.persistence.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.gco.tarjetas.conciliador.ejb.common.constants.ConstantsCommons;


/**
 * The persistent class for the TTAR_CONCILIADOR_GEOPOS database table.
 * 
 */
@Entity
@Table(name="TTAR_CONCILIADOR_GEOPOS_HEAD", schema=ConstantsCommons.SCHEMA_TARJETAS)
@SequenceGenerator(name="SeqConciliadorGeoposHeadId", sequenceName="SEQ_CONCILIADOR_GEOPOS_HEAD_ID", schema=ConstantsCommons.SCHEMA_TARJETAS, allocationSize=1)
@NamedQuery(name="TtarConciliadorGeoposHead.findAll", query="SELECT t FROM TtarConciliadorGeoposHead t")
public class TtarConciliadorGeoposHead implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SeqConciliadorGeoposHeadId")
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_HORA_AUDITORIA")
	private Date fechaHoraAuditoria;
	
	private String cdrazonsocial;
	
	private String cdtienda;
	
	@Column(name="FECHA_INICIAL")
	private String fechaInicial;
	
	@Column(name="FECHA_FINAL")
	private String fechaFinal;
	
	@Column(name="FORMA_PAGO")
	private String formaPago;
	
	@Column(name="MOSTRAR_SOLO_DIFERENCIA")
	private String mostrarSoloDiferencia;	
	
	public TtarConciliadorGeoposHead() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaHoraAuditoria() {
		return fechaHoraAuditoria;
	}

	public void setFechaHoraAuditoria(Date fechaHoraAuditoria) {
		this.fechaHoraAuditoria = fechaHoraAuditoria;
	}

	public String getCdrazonsocial() {
		return cdrazonsocial;
	}

	public void setCdrazonsocial(String cdrazonsocial) {
		this.cdrazonsocial = cdrazonsocial;
	}

	public String getCdtienda() {
		return cdtienda;
	}

	public void setCdtienda(String cdtienda) {
		this.cdtienda = cdtienda;
	}

	public String getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public String getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getMostrarSoloDiferencia() {
		return mostrarSoloDiferencia;
	}

	public void setMostrarSoloDiferencia(String mostrarSoloDiferencia) {
		this.mostrarSoloDiferencia = mostrarSoloDiferencia;
	}

	

}