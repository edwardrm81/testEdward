/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edw.gen.modelo.datos.beans;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Edward
 */
public class ParametrosGenerador implements Serializable {

    public ParametrosGenerador() {
    }

    public String nombreBaseDatos;
    public String url;
    public String driver;
    public String login;
    public String password;
    public String paqueteDestino;
    public String rutaPaqueteDestino;
    public String rutaVistaDestino;
    public String rutaDriver;
    public String formatoGeneracionDeArchivos;
    public String framework;
    public String metodoCapturaUltimoIdInsertado;
    public List<String> listaNombresTablasYVistas; 
    
}
