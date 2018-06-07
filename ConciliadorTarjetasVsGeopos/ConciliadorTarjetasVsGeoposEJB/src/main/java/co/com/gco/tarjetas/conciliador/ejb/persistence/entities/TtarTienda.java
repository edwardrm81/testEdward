package co.com.gco.tarjetas.conciliador.ejb.persistence.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the TTAR_TIENDA database table.
 * 
 */
@Entity
@Table(name="TTAR_TIENDA")
@NamedQueries({
	@NamedQuery(name=TtarTienda.Queries.FIND_BY_NMRAZONSOCIAL_CONCILIADORGP, query="SELECT t FROM TtarTienda t WHERE t.nmrazonsocialConciliadorGP = :nmrazonsocialConciliadorGP ORDER BY t.dsnombre"),
	@NamedQuery(name=TtarTienda.Queries.FIND_ALL, query="SELECT t FROM TtarTienda t ORDER BY t.dsnombre")
})

public class TtarTienda implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public interface Queries {
		String FIND_BY_NMRAZONSOCIAL_CONCILIADORGP = "TtarTienda.findBynmrazonsocialConciliadorGP";
		String FIND_ALL = "TtarTienda.findAll";
	}

	private String cdcredibanco;

	private String cdguid;

	private String cdtelefono;

	private String cdtienda;

	private String cdunico;

	private String cdusuario;

	private String dsdireccion;

	private String dsnombre;

	@Temporal(TemporalType.DATE)
	private Date febaja;

	@Temporal(TemporalType.DATE)
	private Date fecreacion;

	private BigDecimal nmciudad;

	private BigDecimal nmmarca;

	private String nmterminal;

	@Id
	private BigDecimal nmtienda;

	//bi-directional many-to-one association to TtarRazonsocial
	//@ManyToOne
	//@JoinColumn(name="NMRAZONSOCIAL")
	//private TtarRazonsocial ttarRazonsocial;
	private BigDecimal nmrazonsocial;
	
	@Column(name="NMRAZONSOCIAL_CONCILIADORGP")//NMRAZONSOCIAL_CONCILIADORGP
	private BigDecimal nmrazonsocialConciliadorGP;

	public TtarTienda() {
	}

	public String getCdcredibanco() {
		return this.cdcredibanco;
	}

	public void setCdcredibanco(String cdcredibanco) {
		this.cdcredibanco = cdcredibanco;
	}

	public String getCdguid() {
		return this.cdguid;
	}

	public void setCdguid(String cdguid) {
		this.cdguid = cdguid;
	}

	public String getCdtelefono() {
		return this.cdtelefono;
	}

	public void setCdtelefono(String cdtelefono) {
		this.cdtelefono = cdtelefono;
	}

	public String getCdtienda() {
		return this.cdtienda;
	}

	public void setCdtienda(String cdtienda) {
		this.cdtienda = cdtienda;
	}

	public String getCdunico() {
		return this.cdunico;
	}

	public void setCdunico(String cdunico) {
		this.cdunico = cdunico;
	}

	public String getCdusuario() {
		return this.cdusuario;
	}

	public void setCdusuario(String cdusuario) {
		this.cdusuario = cdusuario;
	}

	public String getDsdireccion() {
		return this.dsdireccion;
	}

	public void setDsdireccion(String dsdireccion) {
		this.dsdireccion = dsdireccion;
	}

	public String getDsnombre() {
		return this.dsnombre;
	}

	public void setDsnombre(String dsnombre) {
		this.dsnombre = dsnombre;
	}

	public Date getFebaja() {
		return this.febaja;
	}

	public void setFebaja(Date febaja) {
		this.febaja = febaja;
	}

	public Date getFecreacion() {
		return this.fecreacion;
	}

	public void setFecreacion(Date fecreacion) {
		this.fecreacion = fecreacion;
	}

	public BigDecimal getNmciudad() {
		return this.nmciudad;
	}

	public void setNmciudad(BigDecimal nmciudad) {
		this.nmciudad = nmciudad;
	}

	public BigDecimal getNmmarca() {
		return this.nmmarca;
	}

	public void setNmmarca(BigDecimal nmmarca) {
		this.nmmarca = nmmarca;
	}

	public String getNmterminal() {
		return this.nmterminal;
	}

	public void setNmterminal(String nmterminal) {
		this.nmterminal = nmterminal;
	}

	public BigDecimal getNmtienda() {
		return this.nmtienda;
	}

	public void setNmtienda(BigDecimal nmtienda) {
		this.nmtienda = nmtienda;
	}

	//public TtarRazonsocial getTtarRazonsocial() {
	//	return this.ttarRazonsocial;
	//}
	//
	//public void setTtarRazonsocial(TtarRazonsocial ttarRazonsocial) {
	//	this.ttarRazonsocial = ttarRazonsocial;
	//}
	
	public BigDecimal getNmrazonsocial() {
		return nmrazonsocial;
	}
	
	public void setNmrazonsocial(BigDecimal nmrazonsocial) {
		this.nmrazonsocial = nmrazonsocial;
	}
	
	public BigDecimal getNmrazonsocialConciliadorGP() {
		return nmrazonsocialConciliadorGP;
	}
	
	public void setNmrazonsocialConciliadorGP(BigDecimal nmrazonsocialConciliadorGP) {
		this.nmrazonsocialConciliadorGP = nmrazonsocialConciliadorGP;
	}
	
	
	

}