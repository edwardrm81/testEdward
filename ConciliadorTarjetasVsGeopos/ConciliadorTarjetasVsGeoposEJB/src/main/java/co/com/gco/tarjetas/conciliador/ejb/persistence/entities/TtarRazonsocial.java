package co.com.gco.tarjetas.conciliador.ejb.persistence.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the TTAR_RAZONSOCIAL database table.
 * 
 */
@Entity
@Table(name="TTAR_RAZONSOCIAL")
@NamedQueries({
	@NamedQuery(name=TtarRazonsocial.Queries.FIND_ALL, query="SELECT t FROM TtarRazonsocial t ORDER BY t.dsrazonsocial")
})
public class TtarRazonsocial implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public interface Queries {
		String FIND_ALL = "TtarRazonsocial.findAll";
	}

	@Id
	private long nmrazonsocial;

	private String cdguid;

	private String cdrazonsocial;

	private String cdusuario;

	private String dsrazonsocial;

	@Temporal(TemporalType.DATE)
	private Date febaja;

	@Temporal(TemporalType.DATE)
	private Date fecreacion;

	//bi-directional many-to-one association to TtarTienda
	//@OneToMany(mappedBy="ttarRazonsocial")
	//private List<TtarTienda> ttarTiendas;

	public TtarRazonsocial() {
	}

	public long getNmrazonsocial() {
		return this.nmrazonsocial;
	}

	public void setNmrazonsocial(long nmrazonsocial) {
		this.nmrazonsocial = nmrazonsocial;
	}

	public String getCdguid() {
		return this.cdguid;
	}

	public void setCdguid(String cdguid) {
		this.cdguid = cdguid;
	}

	public String getCdrazonsocial() {
		return this.cdrazonsocial;
	}

	public void setCdrazonsocial(String cdrazonsocial) {
		this.cdrazonsocial = cdrazonsocial;
	}

	public String getCdusuario() {
		return this.cdusuario;
	}

	public void setCdusuario(String cdusuario) {
		this.cdusuario = cdusuario;
	}

	public String getDsrazonsocial() {
		return this.dsrazonsocial;
	}

	public void setDsrazonsocial(String dsrazonsocial) {
		this.dsrazonsocial = dsrazonsocial;
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

//	public List<TtarTienda> getTtarTiendas() {
//		return this.ttarTiendas;
//	}
//
//	public void setTtarTiendas(List<TtarTienda> ttarTiendas) {
//		this.ttarTiendas = ttarTiendas;
//	}
//
//	public TtarTienda addTtarTienda(TtarTienda ttarTienda) {
//		getTtarTiendas().add(ttarTienda);
//		ttarTienda.setTtarRazonsocial(this);
//
//		return ttarTienda;
//	}
//
//	public TtarTienda removeTtarTienda(TtarTienda ttarTienda) {
//		getTtarTiendas().remove(ttarTienda);
//		ttarTienda.setTtarRazonsocial(null);
//
//		return ttarTienda;
//	}

}