package co.com.gco.tarjetas.conciliador.ejb.business.ejb;

import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import co.com.gco.tarjetas.conciliador.ejb.business.ejb.impl.ConciliadorManagerBean;
import co.com.gco.tarjetas.conciliador.ejb.business.ejb.impl.ParametroSistemaManagerBean;
import co.com.gco.tarjetas.conciliador.ejb.business.ejb.interfac.NotificationManagerLocal;
import co.com.gco.tarjetas.conciliador.ejb.common.builder.DTOBuilderParamsToDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.constants.EnumsCommons;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraConsolidadosDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraConsolidadosDiffDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraConsolidadosDiffFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraConsolidadosFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraDetallesDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.PrimeraCompraDetallesFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosConsolidadosDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosConsolidadosDiffDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosConsolidadosDiffFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosConsolidadosFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosDetallesDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.RecaudosDetallesFilterDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.dto.geopos.GeoposRestPrimeraCompraConsolidadosDTO;
import co.com.gco.tarjetas.conciliador.ejb.common.exception.ExceptionBusiness;
import co.com.gco.tarjetas.conciliador.ejb.common.exception.ExceptionPersistence;
import co.com.gco.tarjetas.conciliador.ejb.persistence.dao.ConciliadorDAO;
import co.com.gco.tarjetas.conciliador.ejb.persistence.entities.TtarRazonsocial;
import co.com.gco.tarjetas.conciliador.ejb.persistence.entities.TtarTienda;
 
@RunWith(MockitoJUnitRunner.class)
public class ConciliadorManagerBeanTest {
	
	@InjectMocks
	ConciliadorManagerBean conciliadorManagerBean;
	
	@Mock
	ParametroSistemaManagerBean parametroSistemaManagerBean;
	
	@Mock
	private NotificationManagerLocal notificationBean;
	
	@Mock
	ConciliadorDAO dao;
	
	@Mock
	EntityManager em;
	
	@Mock
	ClientRequest clientRequest;

	@Mock
	TypedQuery<TtarRazonsocial> queryTtarRazonsocial;
	
	@Mock
	TypedQuery<TtarTienda> queryTtarTienda;
	
	
	@Before
    public void prepare() throws Exception{
		
	}
	
	
	@Test
    public void debeObtenerGetRecaudosConsolidadosDiff() throws Exception {
		
		////////////////////////INICIO preparacion valores de los Mocks de debeObtenerGetRecaudosConsolidadosDiff //////////////////////////////////
		{
			
			RecaudosConsolidadosFilterDTO recaudosConsolidadosFilterDTO1 = new RecaudosConsolidadosFilterDTO();
			
			recaudosConsolidadosFilterDTO1.setCdrazonsocial(null);
			recaudosConsolidadosFilterDTO1.setCdtienda("689");
			recaudosConsolidadosFilterDTO1.setFevoucherInicial("2015-05-21");
			recaudosConsolidadosFilterDTO1.setFevoucherFinal("2015-05-21");
			
			List<RecaudosConsolidadosDTO> recaudosConsolidadosDTOList1 = new ArrayList<RecaudosConsolidadosDTO>();
			
			RecaudosConsolidadosDTO recaudosConsolidadosDTO1 = new RecaudosConsolidadosDTO();
			recaudosConsolidadosDTO1.setCdtienda("689");
			recaudosConsolidadosDTO1.setDsnombre("AMERICANINO UNICENTRO");
			recaudosConsolidadosDTO1.setFevoucher("2015-05-21");
			recaudosConsolidadosDTO1.setNmcaja("1");
			recaudosConsolidadosDTO1.setVrpagado("264760.0");
			
			recaudosConsolidadosDTOList1.add(recaudosConsolidadosDTO1);
			
			when(dao.getRecaudosConsolidados(refEq(recaudosConsolidadosFilterDTO1))).thenReturn(recaudosConsolidadosDTOList1);
			
			List<List<Object>> listOfList = new ArrayList<List<Object>>();
			
			List<Object> list = new ArrayList<Object>();
			list.add("689");
			list.add("1");
			list.add("2015-05-21");
			list.add("264760.0");
			
			listOfList.add(list);
			
			ClientResponse clientResponse = mock( ClientResponse.class );
			
			when(clientRequest.get(List.class)).thenReturn(clientResponse);
			
			when( clientResponse.getEntity() ).thenReturn( listOfList );

			when(em.createNamedQuery(TtarTienda.Queries.FIND_ALL, TtarTienda.class)).thenReturn(queryTtarTienda);
			
			List<TtarTienda> ttarTiendaList1 = new ArrayList<TtarTienda>();
			
			TtarTienda ttarTienda1 = new TtarTienda();
			ttarTienda1.setNmrazonsocial(BigDecimal.valueOf(7));
			ttarTienda1.setNmrazonsocialConciliadorGP(BigDecimal.valueOf(4L));
			ttarTienda1.setNmtienda(BigDecimal.valueOf(195));
			ttarTienda1.setDsnombre("NAFNAF MOLINOS");
			ttarTienda1.setCdcredibanco(null);
			ttarTienda1.setCdguid(null);
			ttarTienda1.setCdtelefono(null);
			ttarTienda1.setCdtienda(null);
			ttarTienda1.setCdunico(null);
			ttarTienda1.setCdusuario(null);
			ttarTienda1.setDsdireccion(null);
			ttarTienda1.setFebaja(null);
			ttarTienda1.setFecreacion(null);
			ttarTienda1.setNmciudad(null);
			ttarTienda1.setNmmarca(null);
			ttarTienda1.setNmterminal(null);
			
			ttarTiendaList1.add(ttarTienda1);
			
			when(queryTtarTienda.getResultList()).thenReturn(ttarTiendaList1);
			
		}
		//////////////////////// FIN preparacion valores de los Mocks de debeObtenerGetRecaudosConsolidadosDiff ////////////////////////////////////
		
		String cdrazonsocial = null;
		String cdtienda = "689";
		String fechaInicial = "2015-05-21";
		String fechaFinal = "2015-05-21";
		Boolean mostrarSoloDiferencia = false;
		
		RecaudosConsolidadosDiffFilterDTO recaudosConsolidadosDiffFilterDTO = DTOBuilderParamsToDTO.toRecaudosConsolidadosDiffFilterDTO(cdrazonsocial, cdtienda, fechaInicial, fechaFinal, mostrarSoloDiferencia);
		List<RecaudosConsolidadosDiffDTO> list = conciliadorManagerBean.getRecaudosConsolidadosDiff(recaudosConsolidadosDiffFilterDTO);
		
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()>0);
		Assert.assertEquals(list.get(0).getCdtienda(), "689");
		
	}
	
	
	@Test
    public void debeObtenerGetRecaudosConsolidadosDiffAndSendEmail() throws Exception {
		
		////////////////////////INICIO preparacion valores de los Mocks de debeObtenerGetRecaudosConsolidadosDiffAndSendEmail //////////////////////////////////
		{
			
			RecaudosConsolidadosFilterDTO recaudosConsolidadosFilterDTO1 = new RecaudosConsolidadosFilterDTO();
			
			recaudosConsolidadosFilterDTO1.setCdrazonsocial(null);
			recaudosConsolidadosFilterDTO1.setCdtienda("689");
			recaudosConsolidadosFilterDTO1.setFevoucherInicial("2015-05-21");
			recaudosConsolidadosFilterDTO1.setFevoucherFinal("2015-05-21");
			
			List<RecaudosConsolidadosDTO> recaudosConsolidadosDTOList1 = new ArrayList<RecaudosConsolidadosDTO>();
			
			RecaudosConsolidadosDTO recaudosConsolidadosDTO1 = new RecaudosConsolidadosDTO();
			recaudosConsolidadosDTO1.setCdtienda("689");
			recaudosConsolidadosDTO1.setDsnombre("AMERICANINO UNICENTRO");
			recaudosConsolidadosDTO1.setFevoucher("2015-05-21");
			recaudosConsolidadosDTO1.setNmcaja("1");
			recaudosConsolidadosDTO1.setVrpagado("264760.0");
			
			recaudosConsolidadosDTOList1.add(recaudosConsolidadosDTO1);
			
			when(dao.getRecaudosConsolidados(refEq(recaudosConsolidadosFilterDTO1))).thenReturn(recaudosConsolidadosDTOList1);
			
			List<List<Object>> listOfList = new ArrayList<List<Object>>();
			
			List<Object> list = new ArrayList<Object>();
			list.add("689");
			list.add("1");
			list.add("2015-05-21");
			list.add("264760.0");
			
			listOfList.add(list);
			
			ClientResponse clientResponse = mock( ClientResponse.class );
			
			when(clientRequest.get(List.class)).thenReturn(clientResponse);
			
			when( clientResponse.getEntity() ).thenReturn( listOfList );
			
			when( parametroSistemaManagerBean.consultarValorParametro(EnumsCommons.EMAILS_LIST_CONCILIADOR_TARJETAS_GEOPOS.getValue()) ).thenReturn("edwardr81@gmail.com.co.x.x2");
			
			when(em.createNamedQuery(TtarTienda.Queries.FIND_ALL, TtarTienda.class)).thenReturn(queryTtarTienda);
			
			List<TtarTienda> ttarTiendaList1 = new ArrayList<TtarTienda>();
			
			TtarTienda ttarTienda1 = new TtarTienda();
			ttarTienda1.setNmrazonsocial(BigDecimal.valueOf(7));
			ttarTienda1.setNmrazonsocialConciliadorGP(BigDecimal.valueOf(4L));
			ttarTienda1.setNmtienda(BigDecimal.valueOf(195));
			ttarTienda1.setDsnombre("NAFNAF MOLINOS");
			ttarTienda1.setCdcredibanco(null);
			ttarTienda1.setCdguid(null);
			ttarTienda1.setCdtelefono(null);
			ttarTienda1.setCdtienda(null);
			ttarTienda1.setCdunico(null);
			ttarTienda1.setCdusuario(null);
			ttarTienda1.setDsdireccion(null);
			ttarTienda1.setFebaja(null);
			ttarTienda1.setFecreacion(null);
			ttarTienda1.setNmciudad(null);
			ttarTienda1.setNmmarca(null);
			ttarTienda1.setNmterminal(null);
			
			ttarTiendaList1.add(ttarTienda1);
			
			when(queryTtarTienda.getResultList()).thenReturn(ttarTiendaList1);
			
		}
		//////////////////////// FIN preparacion valores de los Mocks de debeObtenerGetRecaudosConsolidadosDiffAndSendEmail ////////////////////////////////////
		
		String cdrazonsocial = null;
		String cdtienda = "689";
		String fechaInicial = "2015-05-21";
		String fechaFinal = "2015-05-21";
		Boolean mostrarSoloDiferencia = false;
		
		RecaudosConsolidadosDiffFilterDTO recaudosConsolidadosDiffFilterDTO = DTOBuilderParamsToDTO.toRecaudosConsolidadosDiffFilterDTO(cdrazonsocial, cdtienda, fechaInicial, fechaFinal, mostrarSoloDiferencia);
		List<RecaudosConsolidadosDiffDTO> list = conciliadorManagerBean.getRecaudosConsolidadosDiffAndSendEmail(recaudosConsolidadosDiffFilterDTO);
		
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()>0);
		Assert.assertEquals(list.get(0).getCdtienda(), "689");
		
	}	
	
	
	@Test
    public void debeObtenerGetPrimeraCompraConsolidadosDiff() throws Exception {
		
		////////////////////////INICIO preparacion valores de los Mocks de debeObtenerGetPrimeraCompraConsolidadosDiff //////////////////////////////////
		{
			
			PrimeraCompraConsolidadosFilterDTO primeraCompraConsolidadosFilterDTO1 = new PrimeraCompraConsolidadosFilterDTO();
			
			primeraCompraConsolidadosFilterDTO1.setCdrazonsocial(null);
			primeraCompraConsolidadosFilterDTO1.setCdtienda("532");
			primeraCompraConsolidadosFilterDTO1.setFeaprobacionInicial("2015-05-21");
			primeraCompraConsolidadosFilterDTO1.setFeaprobacionFinal("2015-05-21");			
			
			List<PrimeraCompraConsolidadosDTO> primeraCompraConsolidadosDTOList1 = new ArrayList<PrimeraCompraConsolidadosDTO>();
			
			PrimeraCompraConsolidadosDTO primeraCompraConsolidadosDTO1 = new PrimeraCompraConsolidadosDTO();
			primeraCompraConsolidadosDTO1.setCdtienda("532");
			primeraCompraConsolidadosDTO1.setDsnombre("ESPRIT PALATINO");
			primeraCompraConsolidadosDTO1.setFeaprobacion("2015-05-21");
			primeraCompraConsolidadosDTO1.setNmcaja("1");
			primeraCompraConsolidadosDTO1.setNmmonto(769820.0);
			
			primeraCompraConsolidadosDTOList1.add(primeraCompraConsolidadosDTO1);
			
			when(dao.getPrimeraCompraConsolidados(refEq(primeraCompraConsolidadosFilterDTO1))).thenReturn(primeraCompraConsolidadosDTOList1);
			
			GeoposRestPrimeraCompraConsolidadosDTO geoposRestPrimeraCompraConsolidadosDTO1 = new GeoposRestPrimeraCompraConsolidadosDTO();
			geoposRestPrimeraCompraConsolidadosDTO1.setCaja("2");
			geoposRestPrimeraCompraConsolidadosDTO1.setLocalId("532");
			geoposRestPrimeraCompraConsolidadosDTO1.setFecMov("2015-05-25");
			geoposRestPrimeraCompraConsolidadosDTO1.setTotal(Double.valueOf(364338));
			
			GeoposRestPrimeraCompraConsolidadosDTO[] geoposRestPrimeraCompraConsolidadosDTOArray = { geoposRestPrimeraCompraConsolidadosDTO1 };
			
			
			ClientResponse clientResponse = mock( ClientResponse.class );
			
			when(clientRequest.get(GeoposRestPrimeraCompraConsolidadosDTO[].class)).thenReturn(clientResponse);
			
			when( clientResponse.getEntity() ).thenReturn( geoposRestPrimeraCompraConsolidadosDTOArray );
			
			when(em.createNamedQuery(TtarTienda.Queries.FIND_ALL, TtarTienda.class)).thenReturn(queryTtarTienda);
			
			List<TtarTienda> ttarTiendaList1 = new ArrayList<TtarTienda>();
			
			TtarTienda ttarTienda1 = new TtarTienda();
			ttarTienda1.setNmrazonsocial(BigDecimal.valueOf(7));
			ttarTienda1.setNmrazonsocialConciliadorGP(BigDecimal.valueOf(4L));
			ttarTienda1.setNmtienda(BigDecimal.valueOf(195));
			ttarTienda1.setDsnombre("NAFNAF MOLINOS");
			ttarTienda1.setCdcredibanco(null);
			ttarTienda1.setCdguid(null);
			ttarTienda1.setCdtelefono(null);
			ttarTienda1.setCdtienda(null);
			ttarTienda1.setCdunico(null);
			ttarTienda1.setCdusuario(null);
			ttarTienda1.setDsdireccion(null);
			ttarTienda1.setFebaja(null);
			ttarTienda1.setFecreacion(null);
			ttarTienda1.setNmciudad(null);
			ttarTienda1.setNmmarca(null);
			ttarTienda1.setNmterminal(null);
			
			ttarTiendaList1.add(ttarTienda1);
			
			when(queryTtarTienda.getResultList()).thenReturn(ttarTiendaList1);
		}
		//////////////////////// FIN preparacion valores de los Mocks de debeObtenerGetPrimeraCompraConsolidadosDiff ////////////////////////////////////
		
		String cdrazonsocial = null;
		String cdtienda = "532";
		String fechaInicial = "2015-05-21";
		String fechaFinal = "2015-05-21";
		Boolean mostrarSoloDiferencia = false;
		
		PrimeraCompraConsolidadosDiffFilterDTO primeraCompraConsolidadosDiffFilterDTO = DTOBuilderParamsToDTO.toPrimeraCompraConsolidadosDiffFilterDTO(cdrazonsocial, cdtienda, fechaInicial, fechaFinal, mostrarSoloDiferencia);
		List<PrimeraCompraConsolidadosDiffDTO> list = conciliadorManagerBean.getPrimeraCompraConsolidadosDiff(primeraCompraConsolidadosDiffFilterDTO);
		
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()>0);
		Assert.assertEquals(list.get(0).getCdtienda(), "532");
		
	}
	
	
	@Test
    public void debeObtenerGetPrimeraCompraConsolidadosDiffAndSendEmail() throws Exception {
		
		////////////////////////INICIO preparacion valores de los Mocks de debeObtenerGetPrimeraCompraConsolidadosDiffAndSendEmail //////////////////////////////////
		{
			
			PrimeraCompraConsolidadosFilterDTO primeraCompraConsolidadosFilterDTO1 = new PrimeraCompraConsolidadosFilterDTO();
			
			primeraCompraConsolidadosFilterDTO1.setCdrazonsocial(null);
			primeraCompraConsolidadosFilterDTO1.setCdtienda("532");
			primeraCompraConsolidadosFilterDTO1.setFeaprobacionInicial("2015-05-21");
			primeraCompraConsolidadosFilterDTO1.setFeaprobacionFinal("2015-05-21");			
			
			List<PrimeraCompraConsolidadosDTO> primeraCompraConsolidadosDTOList1 = new ArrayList<PrimeraCompraConsolidadosDTO>();
			
			PrimeraCompraConsolidadosDTO primeraCompraConsolidadosDTO1 = new PrimeraCompraConsolidadosDTO();
			primeraCompraConsolidadosDTO1.setCdtienda("532");
			primeraCompraConsolidadosDTO1.setDsnombre("ESPRIT PALATINO");
			primeraCompraConsolidadosDTO1.setFeaprobacion("2015-05-21");
			primeraCompraConsolidadosDTO1.setNmcaja("1");
			primeraCompraConsolidadosDTO1.setNmmonto(769820.0);
			
			primeraCompraConsolidadosDTOList1.add(primeraCompraConsolidadosDTO1);
			
			when(dao.getPrimeraCompraConsolidados(refEq(primeraCompraConsolidadosFilterDTO1))).thenReturn(primeraCompraConsolidadosDTOList1);
			
			GeoposRestPrimeraCompraConsolidadosDTO geoposRestPrimeraCompraConsolidadosDTO1 = new GeoposRestPrimeraCompraConsolidadosDTO();
			geoposRestPrimeraCompraConsolidadosDTO1.setCaja("2");
			geoposRestPrimeraCompraConsolidadosDTO1.setLocalId("532");
			geoposRestPrimeraCompraConsolidadosDTO1.setFecMov("2015-05-25");
			geoposRestPrimeraCompraConsolidadosDTO1.setTotal(Double.valueOf(364338));
			
			GeoposRestPrimeraCompraConsolidadosDTO[] geoposRestPrimeraCompraConsolidadosDTOArray = { geoposRestPrimeraCompraConsolidadosDTO1 };
			
			
			ClientResponse clientResponse = mock( ClientResponse.class );
			
			when(clientRequest.get(GeoposRestPrimeraCompraConsolidadosDTO[].class)).thenReturn(clientResponse);
			
			when( clientResponse.getEntity() ).thenReturn( geoposRestPrimeraCompraConsolidadosDTOArray );
			
			when( parametroSistemaManagerBean.consultarValorParametro(EnumsCommons.EMAILS_LIST_CONCILIADOR_TARJETAS_GEOPOS.getValue()) ).thenReturn("edwardr81@gmail.com.co.x.x2");
			
			when(em.createNamedQuery(TtarTienda.Queries.FIND_ALL, TtarTienda.class)).thenReturn(queryTtarTienda);
			
			List<TtarTienda> ttarTiendaList1 = new ArrayList<TtarTienda>();
			
			TtarTienda ttarTienda1 = new TtarTienda();
			ttarTienda1.setNmrazonsocial(BigDecimal.valueOf(7));
			ttarTienda1.setNmrazonsocialConciliadorGP(BigDecimal.valueOf(4L));
			ttarTienda1.setNmtienda(BigDecimal.valueOf(195));
			ttarTienda1.setDsnombre("NAFNAF MOLINOS");
			ttarTienda1.setCdcredibanco(null);
			ttarTienda1.setCdguid(null);
			ttarTienda1.setCdtelefono(null);
			ttarTienda1.setCdtienda(null);
			ttarTienda1.setCdunico(null);
			ttarTienda1.setCdusuario(null);
			ttarTienda1.setDsdireccion(null);
			ttarTienda1.setFebaja(null);
			ttarTienda1.setFecreacion(null);
			ttarTienda1.setNmciudad(null);
			ttarTienda1.setNmmarca(null);
			ttarTienda1.setNmterminal(null);
			
			ttarTiendaList1.add(ttarTienda1);
			
			when(queryTtarTienda.getResultList()).thenReturn(ttarTiendaList1);
			
		}
		//////////////////////// FIN preparacion valores de los Mocks de debeObtenerGetPrimeraCompraConsolidadosDiffAndSendEmail ////////////////////////////////////

		
		String cdrazonsocial = null;
		String cdtienda = "532";
		String fechaInicial = "2015-05-21";
		String fechaFinal = "2015-05-21";
		Boolean mostrarSoloDiferencia = false;
		
		PrimeraCompraConsolidadosDiffFilterDTO primeraCompraConsolidadosDiffFilterDTO = DTOBuilderParamsToDTO.toPrimeraCompraConsolidadosDiffFilterDTO(cdrazonsocial, cdtienda, fechaInicial, fechaFinal, mostrarSoloDiferencia);
		
		List<PrimeraCompraConsolidadosDiffDTO> list = conciliadorManagerBean.getPrimeraCompraConsolidadosDiffAndSendEmail(primeraCompraConsolidadosDiffFilterDTO);
		
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()>0);
		Assert.assertEquals(list.get(0).getCdtienda(), "532");
		
	}
	
	
	@Test
	public void debeObtenerRecaudosDetalles() throws ExceptionBusiness, ExceptionPersistence {
		
		////////////////////////INICIO preparacion valores de los Mocks de debeObtenerRecaudosDetalles //////////////////////////////////
		{	
			RecaudosDetallesFilterDTO recaudosDetallesFilterDTO1 = new RecaudosDetallesFilterDTO();
			recaudosDetallesFilterDTO1.setCdtienda("689");
			recaudosDetallesFilterDTO1.setFevoucher("2015-05-21");
			recaudosDetallesFilterDTO1.setNmcaja(null);
			
			List<RecaudosDetallesDTO> recaudosDetallesDTOList1 = new ArrayList<RecaudosDetallesDTO>();
			
			RecaudosDetallesDTO recaudosDetallesDTO1 = new RecaudosDetallesDTO();
			recaudosDetallesDTO1.setVrpagado("25000");
			recaudosDetallesDTO1.setNmvoucherpago("678910");
			recaudosDetallesDTO1.setCddocumento("71370000");
			recaudosDetallesDTO1.setNombreCompletoCliente("MiNombreCompleto");
			
			recaudosDetallesDTOList1.add(recaudosDetallesDTO1);
			
			when(dao.getRecaudosDetalles(refEq(recaudosDetallesFilterDTO1))).thenReturn(recaudosDetallesDTOList1);
			
		}
		////////////////////////FIN preparacion valores de los Mocks de debeObtenerRecaudosDetalles //////////////////////////////////
		
		String cdtienda = "689";
		String fecha = "2015-05-21";
		String nmcaja = null;
		
		RecaudosDetallesFilterDTO recaudosDetallesFilterDTO = DTOBuilderParamsToDTO.toRecaudosDetallesFilterDTO(cdtienda, fecha, nmcaja);
		List<RecaudosDetallesDTO> list = conciliadorManagerBean.getRecaudosDetalles(recaudosDetallesFilterDTO);
		
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()>0);
		Assert.assertEquals(list.get(0).getVrpagado(), "25000");
	}
	
	@Test
	public void debeObtenerPrimeraCompraDetalles() throws ExceptionBusiness, ExceptionPersistence {
		
		////////////////////////INICIO preparacion valores de los Mocks de debeObtenerPrimeraCompraDetalles //////////////////////////////////
		{	
			PrimeraCompraDetallesFilterDTO primeraCompraDetallesFilterDTO1 = new PrimeraCompraDetallesFilterDTO();
			primeraCompraDetallesFilterDTO1.setCdtienda("532");
			primeraCompraDetallesFilterDTO1.setFeaprobacion("2015-05-21");
			primeraCompraDetallesFilterDTO1.setNmcaja(null);
			
			List<PrimeraCompraDetallesDTO> primeraCompraDetallesDTOList1 = new ArrayList<PrimeraCompraDetallesDTO>();
			
			PrimeraCompraDetallesDTO primeraCompraDetallesDTO1 = new PrimeraCompraDetallesDTO();
			primeraCompraDetallesDTO1.setNmmonto(BigDecimal.valueOf(769820.0));
			primeraCompraDetallesDTO1.setNmticket("12345");
			primeraCompraDetallesDTO1.setCdbarras("678910L");
			primeraCompraDetallesDTO1.setCddocumento("CC71999888");
			
			primeraCompraDetallesDTOList1.add(primeraCompraDetallesDTO1);
			
			when(dao.getPrimeraCompraDetalles(refEq(primeraCompraDetallesFilterDTO1))).thenReturn(primeraCompraDetallesDTOList1);
			
		}
		////////////////////////FIN preparacion valores de los Mocks de debeObtenerPrimeraCompraDetalles //////////////////////////////////
		
		String cdtienda = "532";
		String fecha = "2015-05-21";
		String nmcaja = null;
		
		PrimeraCompraDetallesFilterDTO primeraCompraDetallesFilterDTO = DTOBuilderParamsToDTO.toPrimeraCompraDetallesFilterDTO(cdtienda, fecha, nmcaja);
		List<PrimeraCompraDetallesDTO> list = conciliadorManagerBean.getPrimeraCompraDetalles(primeraCompraDetallesFilterDTO);
		
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()>0);
		Assert.assertEquals(list.get(0).getNmmonto(), BigDecimal.valueOf(769820.0));
	}

	
	
	@Test
    public void debeObtenerGetTtarRazonsocialList() throws ExceptionBusiness{
		
		////////////////////////INICIO preparacion valores de los Mocks de debeObtenerGetTtarRazonsocialList //////////////////////////////////
		{
			when(em.createNamedQuery(TtarRazonsocial.Queries.FIND_ALL, TtarRazonsocial.class)).thenReturn(queryTtarRazonsocial);
			
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
			
			when(queryTtarRazonsocial.getResultList()).thenReturn(ttarRazonsocialList1);
		}
		////////////////////////FIN preparacion valores de los Mocks de debeObtenerGetTtarRazonsocialList //////////////////////////////////
		
		List<TtarRazonsocial> list = conciliadorManagerBean.getTtarRazonsocialList();
		
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
    public void debeObtenerGetTtarTiendaListFindAll() throws ExceptionBusiness{
		
		////////////////////////INICIO preparacion valores de los Mocks de debeObtenerGetTtarTiendaListFindAll //////////////////////////////////
		{
			when(em.createNamedQuery(TtarTienda.Queries.FIND_ALL, TtarTienda.class)).thenReturn(queryTtarTienda);
			
			List<TtarTienda> ttarTiendaList1 = new ArrayList<TtarTienda>();
			
			TtarTienda ttarTienda1 = new TtarTienda();
			ttarTienda1.setNmrazonsocial(BigDecimal.valueOf(7));
			ttarTienda1.setNmrazonsocialConciliadorGP(BigDecimal.valueOf(4L));
			ttarTienda1.setNmtienda(BigDecimal.valueOf(195));
			ttarTienda1.setDsnombre("NAFNAF MOLINOS");
			ttarTienda1.setCdcredibanco(null);
			ttarTienda1.setCdguid(null);
			ttarTienda1.setCdtelefono(null);
			ttarTienda1.setCdtienda(null);
			ttarTienda1.setCdunico(null);
			ttarTienda1.setCdusuario(null);
			ttarTienda1.setDsdireccion(null);
			ttarTienda1.setFebaja(null);
			ttarTienda1.setFecreacion(null);
			ttarTienda1.setNmciudad(null);
			ttarTienda1.setNmmarca(null);
			ttarTienda1.setNmterminal(null);
			
			ttarTiendaList1.add(ttarTienda1);
			
			when(queryTtarTienda.getResultList()).thenReturn(ttarTiendaList1);
		}
		////////////////////////FIN preparacion valores de los Mocks de debeObtenerGetTtarTiendaListFindAll //////////////////////////////////
		
		List<TtarTienda> list = conciliadorManagerBean.getTtarTiendaList(null);
		
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()>0);
		Assert.assertEquals(list.get(0).getNmrazonsocial(), BigDecimal.valueOf(7));
		Assert.assertEquals(list.get(0).getNmrazonsocialConciliadorGP(), BigDecimal.valueOf(4L));
		Assert.assertEquals(list.get(0).getNmtienda(), BigDecimal.valueOf(195));
		Assert.assertEquals(list.get(0).getDsnombre(), "NAFNAF MOLINOS");
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
	
	
	@Test
    public void debeObtenerGetTtarTiendaListFindByNmrazonsocial() throws ExceptionBusiness{
		
		////////////////////////INICIO preparacion valores de los Mocks de debeObtenerGetTtarTiendaListFindAll //////////////////////////////////
		{
			when(em.createNamedQuery(TtarTienda.Queries.FIND_BY_NMRAZONSOCIAL_CONCILIADORGP, TtarTienda.class)).thenReturn(queryTtarTienda);
			
			List<TtarTienda> ttarTiendaList1 = new ArrayList<TtarTienda>();
			
			TtarTienda ttarTienda1 = new TtarTienda();
			ttarTienda1.setNmrazonsocial(BigDecimal.valueOf(7));
			ttarTienda1.setNmrazonsocialConciliadorGP(BigDecimal.valueOf(4L));
			ttarTienda1.setNmtienda(BigDecimal.valueOf(195));
			ttarTienda1.setDsnombre("NAFNAF MOLINOS");
			
			ttarTiendaList1.add(ttarTienda1);
			
			when(queryTtarTienda.getResultList()).thenReturn(ttarTiendaList1);
		}
		////////////////////////FIN preparacion valores de los Mocks de debeObtenerGetTtarTiendaListFindAll //////////////////////////////////
		
		List<TtarTienda> list = conciliadorManagerBean.getTtarTiendaList("7");
		
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()>0);
		Assert.assertEquals(list.get(0).getDsnombre(), "NAFNAF MOLINOS");
		Assert.assertEquals(list.get(0).getNmrazonsocial(), BigDecimal.valueOf(7));
		Assert.assertEquals(list.get(0).getNmrazonsocialConciliadorGP(), BigDecimal.valueOf(4L));
		
	}
	
	

}