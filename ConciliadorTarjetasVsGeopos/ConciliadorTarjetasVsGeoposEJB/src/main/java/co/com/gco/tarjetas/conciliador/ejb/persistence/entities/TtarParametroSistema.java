package co.com.gco.tarjetas.conciliador.ejb.persistence.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="ttar_parametrosistema")
@NamedQueries({
	@NamedQuery(name=TtarParametroSistema.Queries.CONSULTAR_POR_CODIGO, query="SELECT t FROM TtarParametroSistema t WHERE t.cdparametro = :cdparametro")
})
public class TtarParametroSistema implements Serializable {
	private static final long serialVersionUID = 1L;

	public interface Queries {
		String CONSULTAR_POR_CODIGO = "TtarParametroSistema.findByCdParametro";
	}

	@Id
	private long nmparametrosistema;

	private String cdparametro;

	private String dsparametro;

	private String dsvalor;

	private String dsdescripcion;

	public long getNmparametrosistema() {
		return nmparametrosistema;
	}

	public void setNmparametrosistema(long nmparametrosistema) {
		this.nmparametrosistema = nmparametrosistema;
	}

	public String getCdparametro() {
		return cdparametro;
	}

	public void setCdparametro(String cdparametro) {
		this.cdparametro = cdparametro;
	}

	public String getDsparametro() {
		return dsparametro;
	}

	public void setDsparametro(String dsparametro) {
		this.dsparametro = dsparametro;
	}

	public String getDsvalor() {
		return dsvalor;
	}

	public void setDsvalor(String dsvalor) {
		this.dsvalor = dsvalor;
	}

	public String getDsdescripcion() {
		return dsdescripcion;
	}

	public void setDsdescripcion(String dsdescripcion) {
		this.dsdescripcion = dsdescripcion;
	}

}
