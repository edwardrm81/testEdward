/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.gco.tarjetas.conciliador.ejb.common.exception;

/**
 * ExceptionBusiness
 * @author edward.rodriguez@ingeneo.com.co
 */
public class ExceptionBusiness extends ExceptionBase{
    
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */	
    public ExceptionBusiness() {
    }

    /**
     * constructor
     * @author edward.rodriguez@ingeneo.com.co
     * @param String message
     */
    public ExceptionBusiness(String message) {
        super(message);
    }

    /**
     * constructor
     * @author edward.rodriguez@ingeneo.com.co
     * @param String message
     * @param Throwable cause
     */
    public ExceptionBusiness(String message, Throwable cause) {
        super(message, cause);
    }    
    
}
