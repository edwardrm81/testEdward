/**
 * 
 */
package com.edw.gen.modelo.acciones.generarcodigo;

import java.util.Map;

import com.edw.gen.apigen.ExcepcionNegocio;
import com.edw.gen.modelo.datos.beans.ParametrosGenerador;
import com.edw.gen.modelo.formatearcodigo.FormatearEnJavaBeans;
import com.edw.gen.modelo.formatearcodigo.FormatearEnJavaConexion;
import com.edw.gen.modelo.util.Archivos;
import com.edw.gen.modelo.util.Constantes;

/**
 * @author edward
 *
 */
public class CrearConexion {

	private ParametrosGenerador oParametrosGenerador;
	
	public CrearConexion(ParametrosGenerador oParametrosGenerador) {
		this.oParametrosGenerador = oParametrosGenerador;
	}
	
	public void crear() throws Exception{
		if(this.oParametrosGenerador==null) throw new ExcepcionNegocio("Debe ingresar los parámetros de entrada del generador");		
		if(this.oParametrosGenerador.nombreBaseDatos.trim().length()<1) throw new ExcepcionNegocio("Debe ingresar el nombre de la Base de Datos");
		if(this.oParametrosGenerador.url.trim().length()<1) throw new ExcepcionNegocio("Debe ingresar la url de conexión");
		if(this.oParametrosGenerador.driver.trim().length()<1) throw new ExcepcionNegocio("Debe ingresar el Driver");
		if(this.oParametrosGenerador.login.trim().length()<1) throw new ExcepcionNegocio("Debe ingresar el usuario de la Base de Datos");
		if(this.oParametrosGenerador.password.trim().length()<1) throw new ExcepcionNegocio("Debe ingresar el password del usuario de la Base de Datos");
		if(this.oParametrosGenerador.paqueteDestino.trim().length()<1) throw new ExcepcionNegocio("Debe ingresar el paquete destino");
		if(this.oParametrosGenerador.rutaPaqueteDestino.trim().length()<1) throw new ExcepcionNegocio("Debe ingresar la ruta del directorio destino");
		if(this.oParametrosGenerador.rutaDriver.trim().length()<1) throw new ExcepcionNegocio("Debe ingresar la ruta del driver");
		if(this.oParametrosGenerador.formatoGeneracionDeArchivos.trim().length()<1) throw new ExcepcionNegocio("Debe ingresar el formato de generación de archivos");
		
    	StringBuffer oStringBufferArchivo = new StringBuffer();
    	if(oParametrosGenerador.formatoGeneracionDeArchivos.equalsIgnoreCase(Constantes.FORMATO_GENERACION_JAVA)){
    		oStringBufferArchivo = new FormatearEnJavaConexion().getFormato(oParametrosGenerador.nombreBaseDatos,oParametrosGenerador.url,oParametrosGenerador.driver,oParametrosGenerador.login,oParametrosGenerador.password,oParametrosGenerador.paqueteDestino+".basedatos");
    	}
    	else throw new ExcepcionNegocio("El formato de generación no es válido");
    	
    	// Crea el archivo de Conexión
    	if(oStringBufferArchivo.length()>0) new Archivos().escribirArchivo(oParametrosGenerador.rutaPaqueteDestino+Constantes.FILE_SEPARATOR+"basedatos", "Conexion.java", oStringBufferArchivo.toString());
		
	}

}
