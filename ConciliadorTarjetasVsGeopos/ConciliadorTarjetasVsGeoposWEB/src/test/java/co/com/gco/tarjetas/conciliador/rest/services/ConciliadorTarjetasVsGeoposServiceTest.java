package co.com.gco.tarjetas.conciliador.rest.services;

import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import co.com.gco.tarjetas.conciliador.ejb.business.ejb.interfac.ConciliadorManagerLocal;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraConsolidadosDiffDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraConsolidadosDiffFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraDetallesDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraDetallesFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosConsolidadosDiffDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosConsolidadosDiffFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosDetallesDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosDetallesFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.exception.ExceptionBusiness;
import co.com.gco.tarjetas.conciliador.ejb.persistence.entities.TtarRazonsocial;
import co.com.gco.tarjetas.conciliador.ejb.persistence.entities.TtarTienda;

@RunWith(MockitoJUnitRunner.class)
public class ConciliadorTarjetasVsGeoposServiceTest {

	@InjectMocks
	ConciliadorTarjetasVsGeoposService conciliadorTarjetasVsGeoposService;
	
	@Mock
	ConciliadorManagerLocal conciliadorManagerLocal;
	
	@Before
    public void prepare() throws ExceptionBusiness{	
		
	}
	
	@Test
	public void debeObtenerRecaudosConsolidadosDiff() throws ExceptionBusiness {
		
		//////////////////////// INICIO preparacion valores de los Mocks de debeObtenerRecaudosConsolidadosDiff //////////////////////////////////
		{
			RecaudosConsolidadosDiffFilterDTO recaudosConsolidadosDiffFilterDTO1 = new RecaudosConsolidadosDiffFilterDTO();
			
			recaudosConsolidadosDiffFilterDTO1.setCdrazonsocial(null);
			recaudosConsolidadosDiffFilterDTO1.setCdtienda("689");
			recaudosConsolidadosDiffFilterDTO1.setFevoucherInicial("2015-05-21");
			recaudosConsolidadosDiffFilterDTO1.setFevoucherFinal("2015-05-21");
			recaudosConsolidadosDiffFilterDTO1.setMostrarSoloDiferencia(false);
			
			List<RecaudosConsolidadosDiffDTO> recaudosConsolidadosDiffDTOList1 = new ArrayList<RecaudosConsolidadosDiffDTO>();
			
			RecaudosConsolidadosDiffDTO recaudosConsolidadosDiffDTO1 = new RecaudosConsolidadosDiffDTO ();
			recaudosConsolidadosDiffDTO1.setCdtienda("689");
			recaudosConsolidadosDiffDTO1.setDsnombre("AMERICANINO UNICENTRO");
			recaudosConsolidadosDiffDTO1.setFecha("2015-05-21");
			recaudosConsolidadosDiffDTO1.setNmcaja("1");
			recaudosConsolidadosDiffDTO1.setVrpagadoTarjetas(264760.0);
			recaudosConsolidadosDiffDTO1.setVrpagadoGeopos(264760.0);
			recaudosConsolidadosDiffDTO1.setVrpagadoDiferencia(0.0);
			
			recaudosConsolidadosDiffDTOList1.add(recaudosConsolidadosDiffDTO1);
			
			when(conciliadorManagerLocal.getRecaudosConsolidadosDiff(refEq(recaudosConsolidadosDiffFilterDTO1))).thenReturn(recaudosConsolidadosDiffDTOList1);
		}
		//////////////////////// FIN preparacion valores de los Mocks de debeObtenerRecaudosConsolidadosDiff //////////////////////////////////
		
		String cdrazonsocial = null;
		String cdtienda = "689";
		String fechaInicial = "2015-05-21";
		String fechaFinal = "2015-05-21";
		Boolean mostrarSoloDiferencia = false;
		
		List<RecaudosConsolidadosDiffDTO> list = conciliadorTarjetasVsGeoposService.recaudosConsolidadosDiff(cdrazonsocial, cdtienda, fechaInicial, fechaFinal, mostrarSoloDiferencia);
		
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()>0);
		Assert.assertEquals(list.get(0).getCdtienda(), "689");
	}
	
	@Test
	public void debeObtenerRecaudosConsolidadosDiffAndSendEmail() throws ExceptionBusiness {
		
		////////////////////////INICIO preparacion valores de los Mocks de debeObtenerRecaudosConsolidadosDiffAndSendEmail //////////////////////////////////
		{
			RecaudosConsolidadosDiffFilterDTO recaudosConsolidadosDiffFilterDTO1 = new RecaudosConsolidadosDiffFilterDTO();
			
			recaudosConsolidadosDiffFilterDTO1.setCdrazonsocial(null);
			recaudosConsolidadosDiffFilterDTO1.setCdtienda("689");
			recaudosConsolidadosDiffFilterDTO1.setFevoucherInicial("2015-05-21");
			recaudosConsolidadosDiffFilterDTO1.setFevoucherFinal("2015-05-21");
			recaudosConsolidadosDiffFilterDTO1.setMostrarSoloDiferencia(false);
			
			List<RecaudosConsolidadosDiffDTO> recaudosConsolidadosDiffDTOList1 = new ArrayList<RecaudosConsolidadosDiffDTO>();
			
			RecaudosConsolidadosDiffDTO recaudosConsolidadosDiffDTO1 = new RecaudosConsolidadosDiffDTO ();
			recaudosConsolidadosDiffDTO1.setCdtienda("689");
			recaudosConsolidadosDiffDTO1.setDsnombre("AMERICANINO UNICENTRO");
			recaudosConsolidadosDiffDTO1.setFecha("2015-05-21");
			recaudosConsolidadosDiffDTO1.setNmcaja("1");
			recaudosConsolidadosDiffDTO1.setVrpagadoTarjetas(264760.0);
			recaudosConsolidadosDiffDTO1.setVrpagadoGeopos(264760.0);
			recaudosConsolidadosDiffDTO1.setVrpagadoDiferencia(0.0);
			
			recaudosConsolidadosDiffDTOList1.add(recaudosConsolidadosDiffDTO1);
			
			when(conciliadorManagerLocal.getRecaudosConsolidadosDiffAndSendEmail(refEq(recaudosConsolidadosDiffFilterDTO1))).thenReturn(recaudosConsolidadosDiffDTOList1);
		}
		//////////////////////// FIN preparacion valores de los Mocks de debeObtenerRecaudosConsolidadosDiffAndSendEmail //////////////////////////////////
		
		String cdrazonsocial = null;
		String cdtienda = "689";
		String fechaInicial = "2015-05-21";
		String fechaFinal = "2015-05-21";
		Boolean mostrarSoloDiferencia = false;
		
		List<RecaudosConsolidadosDiffDTO> list = conciliadorTarjetasVsGeoposService.recaudosConsolidadosDiffAndSendEmail(cdrazonsocial, cdtienda, fechaInicial, fechaFinal, mostrarSoloDiferencia);
		
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()>0);
		Assert.assertEquals(list.get(0).getCdtienda(), "689");
	}
	
	@Test
	public void debeObtenerRecaudosDetalles() throws ExceptionBusiness {
		
		////////////////////////INICIO preparacion valores de los Mocks de debeObtenerRecaudosDetalles //////////////////////////////////
		{
			List<RecaudosDetallesDTO> recaudosDetallesDTOList1 = new ArrayList<RecaudosDetallesDTO>();
			
			RecaudosDetallesDTO recaudosDetallesDTO1 = new RecaudosDetallesDTO();
			recaudosDetallesDTO1.setVrpagado("25000");
			recaudosDetallesDTO1.setNmvoucherpago("678910");
			recaudosDetallesDTO1.setCddocumento("71370000");
			recaudosDetallesDTO1.setNombreCompletoCliente("MiNombreCompleto");
			
			recaudosDetallesDTOList1.add(recaudosDetallesDTO1);
			
			RecaudosDetallesFilterDTO recaudosDetallesFilterDTO1 = new RecaudosDetallesFilterDTO();
			recaudosDetallesFilterDTO1.setCdtienda("689");
			recaudosDetallesFilterDTO1.setFevoucher("2015-05-21");
			recaudosDetallesFilterDTO1.setNmcaja(null);
			
			when(conciliadorManagerLocal.getRecaudosDetalles(refEq(recaudosDetallesFilterDTO1))).thenReturn(recaudosDetallesDTOList1);
		}
		////////////////////////FIN preparacion valores de los Mocks de debeObtenerRecaudosDetalles //////////////////////////////////
		
		String cdtienda = "689";
		String fecha = "2015-05-21";
		String nmcaja = null;
		
		List<RecaudosDetallesDTO> list = conciliadorTarjetasVsGeoposService.recaudosDetalles(cdtienda, fecha, nmcaja);
		
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()>0);
		Assert.assertEquals(list.get(0).getVrpagado(), "25000");
	}
	
	
	@Test
	public void debeObtenerPrimeraCompraConsolidadosDiff() throws ExceptionBusiness {
		
		////////////////////////INICIO preparacion valores de los Mocks de debeObtenerPrimeraCompraConsolidadosDiff //////////////////////////////////
		{
			PrimeraCompraConsolidadosDiffFilterDTO primeraCompraConsolidadosDiffFilterDTO1 = new PrimeraCompraConsolidadosDiffFilterDTO();
			
			primeraCompraConsolidadosDiffFilterDTO1.setCdrazonsocial(null);
			primeraCompraConsolidadosDiffFilterDTO1.setCdtienda("532");
			primeraCompraConsolidadosDiffFilterDTO1.setFeaprobacionInicial("2015-05-21");
			primeraCompraConsolidadosDiffFilterDTO1.setFeaprobacionFinal("2015-05-21");
			primeraCompraConsolidadosDiffFilterDTO1.setMostrarSoloDiferencia(false);
			
			List<PrimeraCompraConsolidadosDiffDTO> primeraCompraConsolidadosDiffDTOList1 = new ArrayList<PrimeraCompraConsolidadosDiffDTO>();
			
			PrimeraCompraConsolidadosDiffDTO primeraCompraConsolidadosDiffDTO1 = new PrimeraCompraConsolidadosDiffDTO ();
			primeraCompraConsolidadosDiffDTO1.setCdtienda("532");
			primeraCompraConsolidadosDiffDTO1.setDsnombre("ESPRIT PALATINO");
			primeraCompraConsolidadosDiffDTO1.setFecha("2015-05-21");
			primeraCompraConsolidadosDiffDTO1.setNmcaja("1");
			primeraCompraConsolidadosDiffDTO1.setVrpagadoTarjetas(769820.0);
			primeraCompraConsolidadosDiffDTO1.setVrpagadoGeopos(769820.0);
			primeraCompraConsolidadosDiffDTO1.setVrpagadoDiferencia(0.0);
			
			primeraCompraConsolidadosDiffDTOList1.add(primeraCompraConsolidadosDiffDTO1);
			
			when(conciliadorManagerLocal.getPrimeraCompraConsolidadosDiff(refEq(primeraCompraConsolidadosDiffFilterDTO1))).thenReturn(primeraCompraConsolidadosDiffDTOList1);
		}
		//////////////////////// FIN preparacion valores de los Mocks de debeObtenerPrimeraCompraConsolidadosDiff //////////////////////////////////
		
		String cdrazonsocial = null;
		String cdtienda = "532";
		String fechaInicial = "2015-05-21";
		String fechaFinal = "2015-05-21";
		Boolean mostrarSoloDiferencia = false;
		
		List<PrimeraCompraConsolidadosDiffDTO> list = conciliadorTarjetasVsGeoposService.primeraCompraConsolidadosDiff(cdrazonsocial, cdtienda, fechaInicial, fechaFinal, mostrarSoloDiferencia);
		
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()>0);
		Assert.assertEquals(list.get(0).getCdtienda(), "532");
	}

	@Test
	public void debeObtenerPrimeraCompraConsolidadosDiffAndSendEmail() throws ExceptionBusiness {
		
		////////////////////////INICIO preparacion valores de los Mocks de debeObtenerPrimeraCompraConsolidadosDiffAndSendEmail //////////////////////////////////
		{
			PrimeraCompraConsolidadosDiffFilterDTO primeraCompraConsolidadosDiffFilterDTO1 = new PrimeraCompraConsolidadosDiffFilterDTO();
			
			primeraCompraConsolidadosDiffFilterDTO1.setCdrazonsocial(null);
			primeraCompraConsolidadosDiffFilterDTO1.setCdtienda("532");
			primeraCompraConsolidadosDiffFilterDTO1.setFeaprobacionInicial("2015-05-21");
			primeraCompraConsolidadosDiffFilterDTO1.setFeaprobacionFinal("2015-05-21");
			primeraCompraConsolidadosDiffFilterDTO1.setMostrarSoloDiferencia(false);
			
			List<PrimeraCompraConsolidadosDiffDTO> primeraCompraConsolidadosDiffDTOList1 = new ArrayList<PrimeraCompraConsolidadosDiffDTO>();
			
			PrimeraCompraConsolidadosDiffDTO primeraCompraConsolidadosDiffDTO1 = new PrimeraCompraConsolidadosDiffDTO ();
			primeraCompraConsolidadosDiffDTO1.setCdtienda("532");
			primeraCompraConsolidadosDiffDTO1.setDsnombre("ESPRIT PALATINO");
			primeraCompraConsolidadosDiffDTO1.setFecha("2015-05-21");
			primeraCompraConsolidadosDiffDTO1.setNmcaja("1");
			primeraCompraConsolidadosDiffDTO1.setVrpagadoTarjetas(769820.0);
			primeraCompraConsolidadosDiffDTO1.setVrpagadoGeopos(769820.0);
			primeraCompraConsolidadosDiffDTO1.setVrpagadoDiferencia(0.0);
			
			primeraCompraConsolidadosDiffDTOList1.add(primeraCompraConsolidadosDiffDTO1);
			
			when(conciliadorManagerLocal.getPrimeraCompraConsolidadosDiffAndSendEmail(refEq(primeraCompraConsolidadosDiffFilterDTO1))).thenReturn(primeraCompraConsolidadosDiffDTOList1);
		}
		//////////////////////// FIN preparacion valores de los Mocks de debeObtenerPrimeraCompraConsolidadosDiffAndSendEmail //////////////////////////////////
		
		String cdrazonsocial = null;
		String cdtienda = "532";
		String fechaInicial = "2015-05-21";
		String fechaFinal = "2015-05-21";
		Boolean mostrarSoloDiferencia = false;
		
		List<PrimeraCompraConsolidadosDiffDTO> list = conciliadorTarjetasVsGeoposService.primeraCompraConsolidadosDiffAndSendEmail(cdrazonsocial, cdtienda, fechaInicial, fechaFinal, mostrarSoloDiferencia);
		
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()>0);
		Assert.assertEquals(list.get(0).getCdtienda(), "532");
	}
	
	
	@Test
	public void debeObtenerPrimeraCompraDetalles() throws ExceptionBusiness {
		
		////////////////////////INICIO preparacion valores de los Mocks de debeObtenerPrimeraCompraDetalles //////////////////////////////////
		{
			List<PrimeraCompraDetallesDTO> primeraCompraDetallesDTOList1 = new ArrayList<PrimeraCompraDetallesDTO>();
			
			PrimeraCompraDetallesDTO primeraCompraDetallesDTO1 = new PrimeraCompraDetallesDTO();
			primeraCompraDetallesDTO1.setNmmonto(BigDecimal.valueOf(769820.0));
			primeraCompraDetallesDTO1.setNmticket("12345");
			primeraCompraDetallesDTO1.setCdbarras("678910L");
			primeraCompraDetallesDTO1.setCddocumento("CC71999888");
			
			primeraCompraDetallesDTOList1.add(primeraCompraDetallesDTO1);
			
			PrimeraCompraDetallesFilterDTO primeraCompraDetallesFilterDTO1 = new PrimeraCompraDetallesFilterDTO();
			primeraCompraDetallesFilterDTO1.setCdtienda("532");
			primeraCompraDetallesFilterDTO1.setFeaprobacion("2015-05-21");
			primeraCompraDetallesFilterDTO1.setNmcaja(null);
			
			when(conciliadorManagerLocal.getPrimeraCompraDetalles(refEq(primeraCompraDetallesFilterDTO1))).thenReturn(primeraCompraDetallesDTOList1);
		}
		////////////////////////FIN preparacion valores de los Mocks de debeObtenerPrimeraCompraDetalles //////////////////////////////////
		
		String cdtienda = "532";
		String fecha = "2015-05-21";
		String nmcaja = null;
		
		List<PrimeraCompraDetallesDTO> list = conciliadorTarjetasVsGeoposService.primeraCompraDetalles(cdtienda, fecha, nmcaja);
		
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()>0);
		Assert.assertEquals(list.get(0).getNmmonto(), BigDecimal.valueOf(769820.0));
	}
	
	@Test
	public void debeObtenerGetTtarRazonsocialList() throws ExceptionBusiness {
		
		////////////////////////INICIO preparacion valores de los Mocks de debeObtenerGetTtarRazonsocialList //////////////////////////////////
		{
			List<TtarRazonsocial> ttarRazonsocialList1 = new ArrayList<TtarRazonsocial>();
			
			TtarRazonsocial ttarRazonsocial1 = new TtarRazonsocial();
			ttarRazonsocial1.setNmrazonsocial(7L);
			ttarRazonsocial1.setCdrazonsocial("8110141911");
			ttarRazonsocial1.setDsrazonsocial("NAFTALINA S.A.S");
			ttarRazonsocial1.setCdguid(null);
			ttarRazonsocial1.setCdusuario(null);
			ttarRazonsocial1.setFebaja(null);
			ttarRazonsocial1.setFecreacion(null);
			
			
			ttarRazonsocialList1.add(ttarRazonsocial1);
			
			when(conciliadorManagerLocal.getTtarRazonsocialList()).thenReturn(ttarRazonsocialList1);
		}
		////////////////////////FIN preparacion valores de los Mocks de debeObtenerGetTtarRazonsocialList //////////////////////////////////
		
		List<TtarRazonsocial> list = conciliadorTarjetasVsGeoposService.getTtarRazonsocialList();
		
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()>0);
		Assert.assertEquals(list.get(0).getNmrazonsocial(), 7L);
		Assert.assertEquals(list.get(0).getCdrazonsocial(),"8110141911");
		Assert.assertEquals(list.get(0).getDsrazonsocial(), "NAFTALINA S.A.S");
		Assert.assertNull(list.get(0).getCdguid());
		Assert.assertNull(list.get(0).getCdusuario());
		Assert.assertNull(list.get(0).getFebaja());
		Assert.assertNull(list.get(0).getFecreacion());
		
	}
	
	@Test
	public void debeObtenerGetTtarTiendaList() throws ExceptionBusiness {

		////////////////////////INICIO preparacion valores de los Mocks de debeObtenerGetTtarTiendaList //////////////////////////////////
		{
			List<TtarTienda> ttarTiendaList1 = new ArrayList<TtarTienda>();
			
			TtarTienda ttarTiendal1 = new TtarTienda();
			ttarTiendal1.setNmrazonsocial(BigDecimal.valueOf(7L));
			ttarTiendal1.setNmrazonsocialConciliadorGP(BigDecimal.valueOf(4L));
			ttarTiendal1.setNmtienda(BigDecimal.valueOf(365L));
			ttarTiendal1.setDsnombre("NAFNAF  RIONEGRO");
			ttarTiendal1.setCdcredibanco(null);
			ttarTiendal1.setCdguid(null);
			ttarTiendal1.setCdtelefono(null);
			ttarTiendal1.setCdtienda(null);
			ttarTiendal1.setCdunico(null);
			ttarTiendal1.setCdusuario(null);
			ttarTiendal1.setDsdireccion(null);
			ttarTiendal1.setFebaja(null);
			ttarTiendal1.setFecreacion(null);
			ttarTiendal1.setNmciudad(null);
			ttarTiendal1.setNmmarca(null);
			ttarTiendal1.setNmterminal(null);
			
			ttarTiendaList1.add(ttarTiendal1);
			
			when(conciliadorManagerLocal.getTtarTiendaList("7")).thenReturn(ttarTiendaList1);
		}
		////////////////////////FIN preparacion valores de los Mocks de debeObtenerGetTtarTiendaList //////////////////////////////////
		
		List<TtarTienda> list = conciliadorTarjetasVsGeoposService.getTtarTiendaList("7");
		
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()>0);
		Assert.assertEquals(list.get(0).getNmrazonsocial(), BigDecimal.valueOf(7L));
		Assert.assertEquals(list.get(0).getNmrazonsocialConciliadorGP(), BigDecimal.valueOf(4L));
		Assert.assertEquals(list.get(0).getNmtienda(), BigDecimal.valueOf(365L));
		Assert.assertEquals(list.get(0).getDsnombre(), "NAFNAF  RIONEGRO");
		Assert.assertNull(list.get(0).getCdcredibanco());
		Assert.assertNull(list.get(0).getCdguid());
		Assert.assertNull(list.get(0).getCdtelefono());
		Assert.assertNull(list.get(0).getCdtienda());
		Assert.assertNull(list.get(0).getCdunico());
		Assert.assertNull(list.get(0).getCdusuario());
		Assert.assertNull(list.get(0).getDsdireccion());
		Assert.assertNull(list.get(0).getFebaja());
		Assert.assertNull(list.get(0).getFecreacion());
		Assert.assertNull(list.get(0).getNmciudad());
		Assert.assertNull(list.get(0).getNmmarca());
		Assert.assertNull(list.get(0).getNmterminal());
		
	}
	
	
	
}

