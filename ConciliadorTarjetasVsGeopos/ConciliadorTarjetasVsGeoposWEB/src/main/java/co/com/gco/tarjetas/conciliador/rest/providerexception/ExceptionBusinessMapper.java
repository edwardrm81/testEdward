package co.com.gco.tarjetas.conciliador.rest.providerexception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import co.com.gco.tarjetas.conciliador.ejb.common.constants.EnumsCommons;
import co.com.gco.tarjetas.conciliador.ejb.common.exception.ExceptionBusiness;


@Provider
public class ExceptionBusinessMapper implements ExceptionMapper<ExceptionBusiness>{
	
	public static final String HEADER_NAME = EnumsCommons.INTERNAL_ERROR_MESSAGE.getValue();
	
	@Override
	public Response toResponse(ExceptionBusiness exception) {
		return Response.serverError().header(HEADER_NAME, exception.getMessage()).build();
	}
	
}
