package co.com.gco.tarjetas.conciliador.ejb.business.ejb;

import static org.mockito.Mockito.when;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import co.com.gco.tarjetas.conciliador.ejb.business.ejb.impl.NotificationManagerBean;
import co.com.gco.tarjetas.conciliador.ejb.business.ejb.interfac.ParametroSistemaManagerLocal;
import co.com.gco.tarjetas.conciliador.ejb.common.constants.EnumsCommons;
import co.com.gco.tarjetas.conciliador.ejb.common.exception.ExceptionBusiness;
 
@RunWith(MockitoJUnitRunner.class)
public class NotificationManagerBeanTest {
	
	@InjectMocks
	NotificationManagerBean notificationManagerBean;
	
	@Mock
	ParametroSistemaManagerLocal parametroSistemaBean;
	
	
	@Before
    public void prepare() throws Exception{
		
	}
	
	
	@Test
    public void debeEnviarSendHtmlText() throws ExceptionBusiness{
		
		////////////////////////INICIO preparacion valores de los Mocks //////////////////////////////////
		{
			when(parametroSistemaBean.consultarValorParametro(EnumsCommons.SMTP_USER.getValue())).thenReturn("andresol@gco.com.co");
			when(parametroSistemaBean.consultarValorParametro(EnumsCommons.SMTP_PASSWORD.getValue())).thenReturn("alopezlu");
			when(parametroSistemaBean.consultarValorParametro(EnumsCommons.SMTP_SERVER.getValue())).thenReturn("smtp.gco.com.co");
			when(parametroSistemaBean.consultarValorParametro(EnumsCommons.SMTP_PORT.getValue())).thenReturn("587");
		}
		////////////////////////FIN preparacion valores de los Mocks //////////////////////////////////
		
		String mailText = "Cuerpo del correo";
		String subject = "Asunto del correo";
		String receivers = "edwardm81@gmail.com";
		
//		notificationManagerBean.sendHtmlText(mailText, subject, receivers);
		
	}
	
	
	@Test
    public void debeEnviarSendAttachment() throws ExceptionBusiness{
		
		////////////////////////INICIO preparacion valores de los Mocks //////////////////////////////////
		{
			when(parametroSistemaBean.consultarValorParametro(EnumsCommons.SMTP_USER.getValue())).thenReturn("andresol@gco.com.co");
			when(parametroSistemaBean.consultarValorParametro(EnumsCommons.SMTP_PASSWORD.getValue())).thenReturn("alopezlu");
			when(parametroSistemaBean.consultarValorParametro(EnumsCommons.SMTP_SERVER.getValue())).thenReturn("smtp.gco.com.co");
			when(parametroSistemaBean.consultarValorParametro(EnumsCommons.SMTP_PORT.getValue())).thenReturn("587");
		}
		////////////////////////FIN preparacion valores de los Mocks //////////////////////////////////
		
		String subject = "Asunto del correo";
		String receivers = "edwardr81@gmail.com.x.x2";
		
		String ruta = "C:/Windows/win.ini";
		File file = new File(ruta);
		
//		notificationManagerBean.sendAttachment(ruta, file, subject, receivers);
		
	}
	
	

}