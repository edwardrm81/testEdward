package co.com.gco.tarjetas.conciliador.ejb.common.constants;


/**
 * Class with all Enums in the system
 * @author edward.rodriguez
 *
 */
public enum EnumsCommons {
    
	LANGUAGE_DEFAULT("es"),
    PROPERTIES_FILE_LOCATION("/co/com/gco/tarjetas/conciliador/ejb/common/i18n/messages_%.properties"),
    MESSAGES_SECTION_PROPERTIES("MESSAGES_SECTION_PROPERTIES"),
    LOGGER_NAME("CONCILIADOR_LOG"),
    MAX_NUMBER_DAYS_RANGE_DATES("31"),
    TTAR_APROBACION_SNAPROBADO_SI("S"),
    TTAR_APROBACION_NMESTADO_APROBADO_PRIMERA_COMPRA("57"),
    INTERNAL_ERROR_MESSAGE("INTERNAL_ERROR_MESSAGE"), 
    SEPARATOR_FORMAT_DATE("-"),
    GEOPOS_RESTSERVICE("http://arkano.corp.gco.com.co:8080/arkano-broker/restservice/geopos/documenttotal"),
	SMTP_SERVER("mail.smtp.host"),
	SMTP_PORT("mail.smtp.port"),
	SMTP_USER("mail.smtp.user"),
	SMTP_PASSWORD("mail.smtp.password"),
	SMTP_AUTH("mail.smtp.auth"),
	SMTP_AUTH_TRUE("true"),
	SMTP_STARTTLS_ENABLE("mail.smtp.starttls.enable"),
	SMTP_STARTTLS_ENABLE_FALSE("false"),
	EMAIL_CONTENT_TYPE_TEXT_PLAIN("text/plain"),
	EMAIL_CONTENT_TYPE_TEXT_HTML("text/html"),
	EMAIL_TRANSPORT_PROTOCOL_SMTP("smtp"),
	EMAILS_LIST_CONCILIADOR_TARJETAS_GEOPOS("EMAIL_CONCILIADOR_GP"),
	HTML_BR("<br>"),
	HTML_TABLE("<TABLE>"),
	HTML_TABLE_C("</TABLE>"),
	HTML_TR("<TR>"),
	HTML_TR_C("</TR>"),
	HTML_TD("<TD>"),
	HTML_TD_C("</TD>")	
    //FORMA_PAGO_RECAUDOS_TARJETA("FORMA_PAGO_RECAUDOS_TARJETA"),
    //FORMA_PAGO_PRIMERA_COMPRA("FORMA_PAGO_PRIMERA_COMPRA")
    ;
	
	/**
	 * Constant value
	 */
	private String value;
	
	/**
	 * Constructor
	 * @param value Constant value
	 */
	private EnumsCommons(String value){
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	
}
