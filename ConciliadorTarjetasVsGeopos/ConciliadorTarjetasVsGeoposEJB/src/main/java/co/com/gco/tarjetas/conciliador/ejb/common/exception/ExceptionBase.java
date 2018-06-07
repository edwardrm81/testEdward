/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.gco.tarjetas.conciliador.ejb.common.exception;

/**
 * ExceptionBase
 * @author edward.rodriguez@ingeneo.com.co
 */
public class ExceptionBase extends Exception{
    
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */	
	public ExceptionBase() {
    }

    /**
     * constructor
     * @author edward.rodriguez@ingeneo.com.co
     * @param String message
     */
    public ExceptionBase(String message) {
        super(message);
    }

    /**
     * constructor
     * @author edward.rodriguez@ingeneo.com.co
     * @param String message
     * @param Throwable cause
     */
    public ExceptionBase(String message, Throwable cause) {
        super(message, cause);
    }      
    
}
