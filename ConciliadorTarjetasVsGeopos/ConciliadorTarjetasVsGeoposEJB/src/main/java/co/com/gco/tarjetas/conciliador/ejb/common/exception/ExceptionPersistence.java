/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.gco.tarjetas.conciliador.ejb.common.exception;

/**
 * ExceptionPersistence
 * @author edward.rodriguez@ingeneo.com.co
 */
public class ExceptionPersistence extends ExceptionBase{
    
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */	
    public ExceptionPersistence() {
    }

    /**
     * constructor
     * @author edward.rodriguez@ingeneo.com.co
     * @param String message
     */
    public ExceptionPersistence(String message) {
        super(message);
    }

    /**
     * constructor
     * @author edward.rodriguez@ingeneo.com.co
     * @param String message
     * @param Throwable cause
     */
    public ExceptionPersistence(String message, Throwable cause) {
        super(message, cause);
    }       
    
}
