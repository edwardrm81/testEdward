package co.com.gco.tarjetas.conciliador.ejb.business.ejb.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.com.gco.tarjetas.conciliador.ejb.business.ejb.interfac.ParametroSistemaManagerLocal;
import co.com.gco.tarjetas.conciliador.ejb.common.constants.ConstantsCommons;
import co.com.gco.tarjetas.conciliador.ejb.common.exception.ExceptionBusiness;
import co.com.gco.tarjetas.conciliador.ejb.persistence.entities.TtarParametroSistema;

/**
* ParametroSistemaManagerBean
* @author edward.rodriguez@ingeneo.com.co
* 
*/
@Stateless()
public class ParametroSistemaManagerBean implements ParametroSistemaManagerLocal {
	
	@PersistenceContext(unitName = ConstantsCommons.PERSISTENCE_UNIT_NAME/*, name = ConstantsPersistence.PERSISTENCE_UNIT_NAME*/)
	private EntityManager em;
	
	
    /**
     * Metodo que consulta el valor de una parametro de la tabla parametros de sistema segun el codigo de parametro ingresado
     * @author edward.rodriguez@ingeneo.com.co
     * @param String cdparametro
     * @return String
     */  
	public String consultarValorParametro(String cdparametro) {
		TtarParametroSistema parametroSistema = consultarParametro(cdparametro);
		return parametroSistema == null ? null : parametroSistema.getDsvalor();
	}
	
    /**
     * Metodo que consulta un registro (Entidad) de la tabla parametros de sistema segun el codigo de parametro ingresado
     * @author edward.rodriguez@ingeneo.com.co
     * @param String cdparametro
     * @return TtarParametroSistema
     */  
	public TtarParametroSistema consultarParametro(String cdparametro) {
		TypedQuery<TtarParametroSistema> query = em.createNamedQuery(TtarParametroSistema.Queries.CONSULTAR_POR_CODIGO, TtarParametroSistema.class);
		query.setParameter("cdparametro", cdparametro);
		List<TtarParametroSistema> resultList = query.getResultList();
		return resultList.isEmpty() ? null : resultList.get(0);
	}

}
