package co.com.gco.tarjetas.conciliador.ejb.business.ejb.interfac;


import java.io.File;

import javax.ejb.Local;

import co.com.gco.tarjetas.conciliador.ejb.common.exception.ExceptionBusiness;

/**
* ParametrosManagerLocal
* @author edward.rodriguez@ingeneo.com.co
*/
@Local
public interface NotificationManagerLocal {
	
    
    /**
     * Metodo que envia un email solo con texto html en el cuerpo del correo
     * @param String mailText
     * @param String subject
     * @param String receivers
     * @throws ExceptionBusiness
     */ 	
	public void sendHtmlText(String mailText, String subject, String receivers) throws ExceptionBusiness;
	
    /**
     * Metodo que envia un email con un archivo adjunto
     * @param String ruta
     * @param File file
     * @param String subject
     * @param String receivers
     * @throws ExceptionBusiness
     */
	public void sendAttachment(String ruta, File file, String subject, String receivers) throws ExceptionBusiness ;

    
    
}
