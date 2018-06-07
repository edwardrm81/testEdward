package co.com.gco.tarjetas.conciliador.ejb.business.ejb.impl;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import co.com.gco.tarjetas.conciliador.ejb.business.ejb.interfac.NotificationManagerLocal;
import co.com.gco.tarjetas.conciliador.ejb.business.ejb.interfac.ParametroSistemaManagerLocal;
import co.com.gco.tarjetas.conciliador.ejb.common.constants.ConstantsCommons;
import co.com.gco.tarjetas.conciliador.ejb.common.constants.EnumsCommons;
import co.com.gco.tarjetas.conciliador.ejb.common.constants.MessageCodesI18N;
import co.com.gco.tarjetas.conciliador.ejb.common.exception.ExceptionBusiness;
import co.com.gco.tarjetas.conciliador.ejb.common.util.PropertiesLoad;

/**
* NotificationManagerBean
* @author edward.rodriguez@ingeneo.com.co
* 
*/
@Stateless()
public class NotificationManagerBean implements NotificationManagerLocal {
	
	@Inject
	private ParametroSistemaManagerLocal parametroSistemaBean;

    /**
     * Metodo que envia un email solo con texto html en el cuerpo del correo
     * @param String mailText
     * @param String subject
     * @param String receivers
     * @throws ExceptionBusiness
     */ 	
	@Asynchronous
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void sendHtmlText(String mailText, String subject, String receivers) throws ExceptionBusiness {
		
		String eUser = parametroSistemaBean.consultarValorParametro(EnumsCommons.SMTP_USER.getValue());
		String ePass = parametroSistemaBean.consultarValorParametro(EnumsCommons.SMTP_PASSWORD.getValue());
		
		String[] dir_mail = receivers.split(",");
		try {
			Session session = Session.getInstance(getMailProperties());
			MimeMessage message = new MimeMessage(session);
			message.setContent(mailText, EnumsCommons.EMAIL_CONTENT_TYPE_TEXT_HTML.getValue());
			message.setFrom(new InternetAddress(eUser));

			for( int r=0; r < dir_mail.length;  r++ ) {
				message.addRecipient(Message.RecipientType.TO,new InternetAddress(dir_mail[r].trim()));
			}

			message.setSubject(subject);

			Transport t = session.getTransport(EnumsCommons.EMAIL_TRANSPORT_PROTOCOL_SMTP.getValue());
			t.connect(eUser, ePass);
			t.sendMessage(message, message.getAllRecipients());
			t.close();
		} catch (MessagingException e) {
			ConstantsCommons.log.error("MessagingException: " + e.getMessage(), e);
			throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_SEND_MAIL), e);
		} catch (Exception e) {
			ConstantsCommons.log.error("Exception: " + e.getMessage(), e);
			throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_SEND_MAIL), e);
		}
	}

    /**
     * Metodo que envia un email con un archivo adjunto
     * @param String ruta
     * @param File file
     * @param String subject
     * @param String receivers
     * @throws ExceptionBusiness
     */
	public void sendAttachment(String ruta, File file, String subject, String receivers) throws ExceptionBusiness {
		
		try {
			String eUser = parametroSistemaBean.consultarValorParametro(EnumsCommons.SMTP_USER.getValue());
			String ePass = parametroSistemaBean.consultarValorParametro(EnumsCommons.SMTP_PASSWORD.getValue());


			String[] dir_mail = receivers.split(",");
		
			Session session = Session.getInstance(getMailProperties());
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(eUser));

			for( int r=0; r < dir_mail.length;  r++ ) {
				message.addRecipient(Message.RecipientType.TO,new InternetAddress(dir_mail[r].trim()));
			}

			message.setSubject(subject);

			BodyPart adjunto = new MimeBodyPart();
			adjunto.setDataHandler(new DataHandler(new FileDataSource(ruta)));
			adjunto.setFileName(file.getName());
			MimeMultipart multiParte = new MimeMultipart();
			multiParte.addBodyPart(adjunto);

			message.setContent(multiParte);

			Transport t = session.getTransport(EnumsCommons.EMAIL_TRANSPORT_PROTOCOL_SMTP.getValue());
			t.connect(eUser, ePass);
			t.sendMessage(message, message.getAllRecipients());
			t.close();

		} catch (MessagingException e) {
			ConstantsCommons.log.error("MessagingException: " + e.getMessage(), e);
			throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_SEND_MAIL), e);
		} catch (Exception e) {
			ConstantsCommons.log.error("Exception: " + e.getMessage(), e);
			throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_SEND_MAIL), e);
		}
	}

    /**
     * Metodo que recupera las propiedades para envio de mail
     * @throws ExceptionBusiness
     */	
	private Properties getMailProperties() throws ExceptionBusiness {
		try {
			Properties props = new Properties();
			props.setProperty(EnumsCommons.SMTP_SERVER.getValue(), parametroSistemaBean.consultarValorParametro(EnumsCommons.SMTP_SERVER.getValue()));
			props.setProperty(EnumsCommons.SMTP_STARTTLS_ENABLE.getValue(), EnumsCommons.SMTP_STARTTLS_ENABLE_FALSE.getValue());
			props.setProperty(EnumsCommons.SMTP_PORT.getValue(), parametroSistemaBean.consultarValorParametro(EnumsCommons.SMTP_PORT.getValue()));
			props.setProperty(EnumsCommons.SMTP_USER.getValue(), parametroSistemaBean.consultarValorParametro(EnumsCommons.SMTP_USER.getValue()));
			props.setProperty(EnumsCommons.SMTP_AUTH.getValue(), EnumsCommons.SMTP_AUTH_TRUE.getValue());
			return props;
		} catch (Exception e) {
			ConstantsCommons.log.error("Exception: " + e.getMessage(), e);
			throw new ExceptionBusiness(PropertiesLoad.getSectionPropertie(MessageCodesI18N.ERROR_SEND_MAIL), e);
		}
	}
}
