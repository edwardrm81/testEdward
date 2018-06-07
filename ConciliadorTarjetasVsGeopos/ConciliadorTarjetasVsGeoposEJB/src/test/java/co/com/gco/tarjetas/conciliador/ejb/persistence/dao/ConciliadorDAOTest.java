package co.com.gco.tarjetas.conciliador.ejb.persistence.dao;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import co.com.gco.tarjetas.conciliador.ejb.common.builder.DTOBuilderParamsToDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraConsolidadosDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraConsolidadosFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraDetallesDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraDetallesFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosConsolidadosDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosConsolidadosFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosDetallesDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosDetallesFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.exception.ExceptionBusiness;
import co.com.gco.tarjetas.conciliador.ejb.common.exception.ExceptionPersistence;
 
@RunWith(MockitoJUnitRunner.class)
public class ConciliadorDAOTest {
	
	@InjectMocks
	ConciliadorDAO conciliadorDAO;
	
	@Mock
	EntityManager em;
	
	@Mock
	Query querySqlRecaudosConsolidados;
	
	@Mock
	Query querySqlPrimeraCompraConsolidados;
	
	@Mock
	Query querySqlRecaudosDetalles;
	
	@Mock
	Query querySqlPrimeraCompraDetalles;
	
	
	@Before
    public void prepare() throws Exception{
		
	}
	
	
	@Test
    public void debeObtenerGetRecaudosConsolidados() throws ExceptionBusiness, ExceptionPersistence {
		
		////////////////////////INICIO preparacion valores de los Mocks //////////////////////////////////
		{
			
	    	RecaudosConsolidadosFilterDTO recaudosConsolidadosFilterDTO1 = new RecaudosConsolidadosFilterDTO();
	    	
			recaudosConsolidadosFilterDTO1.setCdrazonsocial(null);
			recaudosConsolidadosFilterDTO1.setCdtienda("689");
			recaudosConsolidadosFilterDTO1.setFevoucherInicial("2015-05-21");
			recaudosConsolidadosFilterDTO1.setFevoucherFinal("2015-05-21");
			
			when(em.createNativeQuery(QueryHelper.getSqlRecaudosConsolidados(recaudosConsolidadosFilterDTO1))).thenReturn(querySqlRecaudosConsolidados);
			
			List<Object[]> objArrayList1 = new ArrayList<Object[]>();
			Object[] obj1 = {"2015-05-21", "AMERICANINO UNICENTRO", "689", "1", "264760.0"};
			objArrayList1.add(obj1);
			
			when(querySqlRecaudosConsolidados.getResultList()).thenReturn(objArrayList1);

		}
		////////////////////////FIN preparacion valores de los Mocks //////////////////////////////////
		
    	RecaudosConsolidadosFilterDTO recaudosConsolidadosFilterDTO1 = new RecaudosConsolidadosFilterDTO();
    	
		recaudosConsolidadosFilterDTO1.setCdrazonsocial(null);
		recaudosConsolidadosFilterDTO1.setCdtienda("689");
		recaudosConsolidadosFilterDTO1.setFevoucherInicial("2015-05-21");
		recaudosConsolidadosFilterDTO1.setFevoucherFinal("2015-05-21");
		
		List<RecaudosConsolidadosDTO> list = conciliadorDAO.getRecaudosConsolidados(recaudosConsolidadosFilterDTO1);
		
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()>0);
		Assert.assertEquals(list.get(0).getDsnombre(), "AMERICANINO UNICENTRO");
		Assert.assertEquals(list.get(0).getVrpagado(), "264760.0");
		
	}

	@Test
    public void debeObtenerGetPrimeraCompraConsolidados() throws ExceptionBusiness, ExceptionPersistence {
		
		////////////////////////INICIO preparacion valores de los Mocks //////////////////////////////////
		{
			
	    	PrimeraCompraConsolidadosFilterDTO primeraCompraConsolidadosFilterDTO1 = new PrimeraCompraConsolidadosFilterDTO();
	    	
			primeraCompraConsolidadosFilterDTO1.setCdrazonsocial(null);
			primeraCompraConsolidadosFilterDTO1.setCdtienda("532");
			primeraCompraConsolidadosFilterDTO1.setFeaprobacionInicial("2015-05-21");
			primeraCompraConsolidadosFilterDTO1.setFeaprobacionFinal("2015-05-21");
			
			when(em.createNativeQuery(QueryHelper.getSqlPrimeraCompraConsolidados(primeraCompraConsolidadosFilterDTO1))).thenReturn(querySqlPrimeraCompraConsolidados);
	    	
			List<Object[]> objArrayList1 = new ArrayList<Object[]>();
			Object[] obj1 = {"2015-05-21", "ESPRIT PALATINO", "532", "1", "769820.0"};
			objArrayList1.add(obj1);
			
			when(querySqlPrimeraCompraConsolidados.getResultList()).thenReturn(objArrayList1);

		}
		////////////////////////FIN preparacion valores de los Mocks //////////////////////////////////
		
    	PrimeraCompraConsolidadosFilterDTO primeraCompraConsolidadosFilterDTO1 = new PrimeraCompraConsolidadosFilterDTO();
    	
		primeraCompraConsolidadosFilterDTO1.setCdrazonsocial(null);
		primeraCompraConsolidadosFilterDTO1.setCdtienda("532");
		primeraCompraConsolidadosFilterDTO1.setFeaprobacionInicial("2015-05-21");
		primeraCompraConsolidadosFilterDTO1.setFeaprobacionFinal("2015-05-21");
		
		List<PrimeraCompraConsolidadosDTO> list = conciliadorDAO.getPrimeraCompraConsolidados(primeraCompraConsolidadosFilterDTO1);
		
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()>0);
		Assert.assertEquals(list.get(0).getDsnombre(), "ESPRIT PALATINO");
		Assert.assertEquals(list.get(0).getNmmonto(), Double.valueOf("769820.0"));
		
	}
	
	
	@Test
    public void debeObtenerGetRecaudosDetalles() throws ExceptionBusiness, ExceptionPersistence {
		
		////////////////////////INICIO preparacion valores de los Mocks //////////////////////////////////
		{
			
			RecaudosDetallesFilterDTO recaudosDetallesFilterDTO1 = new RecaudosDetallesFilterDTO();
			recaudosDetallesFilterDTO1.setCdtienda("689");
			recaudosDetallesFilterDTO1.setFevoucher("2015-05-21");
			recaudosDetallesFilterDTO1.setNmcaja("1");
			
			when(em.createNativeQuery(QueryHelper.getSqlRecaudosDetalles(recaudosDetallesFilterDTO1))).thenReturn(querySqlRecaudosDetalles);

			List<Object[]> objArrayList1 = new ArrayList<Object[]>();
			Object[] obj1 = {"25000", "678910", "71370000", "MiNombreCompleto"};
			objArrayList1.add(obj1);
			
			when(querySqlRecaudosDetalles.getResultList()).thenReturn(objArrayList1);

		}
		////////////////////////FIN preparacion valores de los Mocks //////////////////////////////////
    	
		String cdtienda = "689";
		String fecha = "2015-05-21";
		String nmcaja = "1";
		
		RecaudosDetallesFilterDTO recaudosDetallesFilterDTO = DTOBuilderParamsToDTO.toRecaudosDetallesFilterDTO(cdtienda, fecha, nmcaja);
		
		List<RecaudosDetallesDTO> list = conciliadorDAO.getRecaudosDetalles(recaudosDetallesFilterDTO);
		
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()>0);
		Assert.assertEquals(list.get(0).getVrpagado(), "25000");
		
	}
	

	@Test
    public void debeObtenerGetPrimeraCompraDetalles() throws ExceptionBusiness, ExceptionPersistence {
		
		////////////////////////INICIO preparacion valores de los Mocks //////////////////////////////////
		{
			
			PrimeraCompraDetallesFilterDTO primeraCompraDetallesFilterDTO1 = new PrimeraCompraDetallesFilterDTO();
			primeraCompraDetallesFilterDTO1.setCdtienda("532");
			primeraCompraDetallesFilterDTO1.setFeaprobacion("2015-05-21");
			primeraCompraDetallesFilterDTO1.setNmcaja("1");
			
			when(em.createNativeQuery(QueryHelper.getSqlPrimeraCompraDetalles(primeraCompraDetallesFilterDTO1))).thenReturn(querySqlPrimeraCompraDetalles);
			
			List<Object[]> objArrayList1 = new ArrayList<Object[]>();
			Object[] obj1 = {"769820.0", "12345", "678910", "CC71999888", "MiNombreCompleto"};
			objArrayList1.add(obj1);
			
			when(querySqlPrimeraCompraDetalles.getResultList()).thenReturn(objArrayList1);

		}
		////////////////////////FIN preparacion valores de los Mocks //////////////////////////////////
    	
		String cdtienda = "532";
		String fecha = "2015-05-21";
		String nmcaja = "1";
		
		PrimeraCompraDetallesFilterDTO primeraCompraDetallesFilterDTO = DTOBuilderParamsToDTO.toPrimeraCompraDetallesFilterDTO(cdtienda, fecha, nmcaja);
		
		List<PrimeraCompraDetallesDTO> list = conciliadorDAO.getPrimeraCompraDetalles(primeraCompraDetallesFilterDTO);
		
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()>0);
		Assert.assertEquals(list.get(0).getNmmonto(), BigDecimal.valueOf(769820.0));
		
	}
	
	
	
}