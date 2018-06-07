package co.com.gco.tarjetas.conciliador.ejb.business.ejb;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import co.com.gco.tarjetas.conciliador.ejb.business.ejb.impl.ParametroSistemaManagerBean;
import co.com.gco.tarjetas.conciliador.ejb.common.constants.EnumsCommons;
import co.com.gco.tarjetas.conciliador.ejb.common.exception.ExceptionBusiness;
import co.com.gco.tarjetas.conciliador.ejb.persistence.entities.TtarParametroSistema;
 
@RunWith(MockitoJUnitRunner.class)
public class ParametroSistemaManagerBeanTest {
	
	@InjectMocks
	ParametroSistemaManagerBean parametroSistemaBean;
	
	@Mock
	EntityManager em;
	
	@Mock
	TypedQuery<TtarParametroSistema> queryTtarParametroSistema;
	
	@Before
    public void prepare() throws Exception{
		
	}
	
	
	@Test
    public void debeObtenerConsultarValorParametro() throws ExceptionBusiness{
		
		////////////////////////INICIO preparacion valores de los Mocks //////////////////////////////////
		{
			when(em.createNamedQuery(TtarParametroSistema.Queries.CONSULTAR_POR_CODIGO, TtarParametroSistema.class)).thenReturn(queryTtarParametroSistema);
			
			List<TtarParametroSistema> ttarParametroSistemaList1 = new ArrayList<TtarParametroSistema>();
			
			TtarParametroSistema ttarParametroSistema1 = new TtarParametroSistema();
			ttarParametroSistema1.setDsvalor("andresol@gco.com.co");
			ttarParametroSistema1.setNmparametrosistema(0);
			ttarParametroSistema1.setCdparametro(null);
			ttarParametroSistema1.setDsparametro(null);
			ttarParametroSistema1.setDsdescripcion(null);
			
			
			ttarParametroSistemaList1.add(ttarParametroSistema1);
			
			when(queryTtarParametroSistema.getResultList()).thenReturn(ttarParametroSistemaList1);			
			

		}
		////////////////////////FIN preparacion valores de los Mocks //////////////////////////////////
		
		String valorParametro = parametroSistemaBean.consultarValorParametro(EnumsCommons.SMTP_USER.getValue());
		
		Assert.assertNotNull(valorParametro);
		Assert.assertEquals(valorParametro, "andresol@gco.com.co");
		
	}
	
	@Test
    public void debeObtenerConsultarParametro() throws ExceptionBusiness{
		
		////////////////////////INICIO preparacion valores de los Mocks //////////////////////////////////
		{
			when(em.createNamedQuery(TtarParametroSistema.Queries.CONSULTAR_POR_CODIGO, TtarParametroSistema.class)).thenReturn(queryTtarParametroSistema);
			
			List<TtarParametroSistema> ttarParametroSistemaList1 = new ArrayList<TtarParametroSistema>();
			
			TtarParametroSistema ttarParametroSistema1 = new TtarParametroSistema();
			ttarParametroSistema1.setDsvalor("andresol@gco.com.co");
			ttarParametroSistema1.setNmparametrosistema(0);
			ttarParametroSistema1.setCdparametro(null);
			ttarParametroSistema1.setDsparametro(null);
			ttarParametroSistema1.setDsdescripcion(null);
			
			
			ttarParametroSistemaList1.add(ttarParametroSistema1);
			
			when(queryTtarParametroSistema.getResultList()).thenReturn(ttarParametroSistemaList1);			
			

		}
		////////////////////////FIN preparacion valores de los Mocks //////////////////////////////////
		
		TtarParametroSistema ttarParametroSistema = parametroSistemaBean.consultarParametro(EnumsCommons.SMTP_USER.getValue());
		
		Assert.assertNotNull(ttarParametroSistema);
		Assert.assertEquals(ttarParametroSistema.getDsvalor(), "andresol@gco.com.co");
		Assert.assertEquals(ttarParametroSistema.getNmparametrosistema(), 0L);
		Assert.assertNull(ttarParametroSistema.getCdparametro());
		Assert.assertNull(ttarParametroSistema.getDsparametro());
		Assert.assertNull(ttarParametroSistema.getDsdescripcion());
		
	}
	

}