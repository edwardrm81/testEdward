/**
 * 
 */
package com.edw.gen.modelo.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringReader;

/**
 * @author edward
 *
 */
public class Archivos {

	/**
	 * 
	 */
	public Archivos() {
		
	}
	
    public void escribirArchivo(String direccionDestinoDelArchivo,String nombreArchivo, String contenidoArchivo)
    {	
        try {
        	
            File file = new File(direccionDestinoDelArchivo);
            if (!file.exists()) file.mkdirs();
            file = new File(file,nombreArchivo);
            
		    BufferedReader leer = new BufferedReader(new StringReader(contenidoArchivo));
		    PrintWriter escribir = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		    while ((contenidoArchivo = leer.readLine()) != null) escribir.println(contenidoArchivo);
		    escribir.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
    }
}
