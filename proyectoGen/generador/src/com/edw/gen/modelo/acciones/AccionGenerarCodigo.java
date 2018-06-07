package com.edw.gen.modelo.acciones;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.edw.gen.apigen.ExcepcionNegocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edw.gen.modelo.acciones.generarcodigo.CrearAcciones;
import com.edw.gen.modelo.acciones.generarcodigo.CrearAdicionales;
import com.edw.gen.modelo.acciones.generarcodigo.CrearBeans;
import com.edw.gen.modelo.acciones.generarcodigo.CrearConexion;
import com.edw.gen.modelo.acciones.generarcodigo.CrearControladores;
import com.edw.gen.modelo.acciones.generarcodigo.CrearDaos;
import com.edw.gen.modelo.acciones.generarcodigo.CrearFachadas;
import com.edw.gen.modelo.acciones.generarcodigo.CrearSecuencias;
import com.edw.gen.modelo.acciones.generarcodigo.CrearVistas;
import com.edw.gen.modelo.acciones.generarcodigo.InfoBaseDatos;
import com.edw.gen.modelo.datos.beans.ParametrosGenerador;
import com.edw.gen.modelo.formatearcodigo.FormatearInfoBaseDatos;


public class AccionGenerarCodigo {

	public ParametrosGenerador oParametrosGenerador;
	
	public AccionGenerarCodigo(ParametrosGenerador oParametrosGenerador) throws Exception{
		this.oParametrosGenerador = oParametrosGenerador;
	}
	
    Map execute(Connection conn) throws Exception{
    	
		if(this.oParametrosGenerador==null) throw new ExcepcionNegocio("Debe ingresar los parámetros de entrada del generador");		
		if(this.oParametrosGenerador.nombreBaseDatos==null || this.oParametrosGenerador.nombreBaseDatos.trim().length()<1) throw new ExcepcionNegocio("Debe ingresar el nombre de la Base de Datos");
		if(this.oParametrosGenerador.url==null || this.oParametrosGenerador.url.trim().length()<1) throw new ExcepcionNegocio("Debe ingresar la url de conexión");
		if(this.oParametrosGenerador.driver==null || this.oParametrosGenerador.driver.trim().length()<1) throw new ExcepcionNegocio("Debe ingresar el Driver");
		if(this.oParametrosGenerador.login==null || this.oParametrosGenerador.login.trim().length()<1) throw new ExcepcionNegocio("Debe ingresar el usuario de la Base de Datos");
		if(this.oParametrosGenerador.password==null || this.oParametrosGenerador.password.trim().length()<1) throw new ExcepcionNegocio("Debe ingresar el password del usuario de la Base de Datos");
		if(this.oParametrosGenerador.paqueteDestino==null || this.oParametrosGenerador.paqueteDestino.trim().length()<1) throw new ExcepcionNegocio("Debe ingresar el paquete destino");
		if(this.oParametrosGenerador.rutaPaqueteDestino==null || this.oParametrosGenerador.rutaPaqueteDestino.trim().length()<1) throw new ExcepcionNegocio("Debe ingresar la ruta del directorio destino");
		if(this.oParametrosGenerador.rutaDriver==null || this.oParametrosGenerador.rutaDriver.trim().length()<1) throw new ExcepcionNegocio("Debe ingresar la ruta del driver");
		if(this.oParametrosGenerador.formatoGeneracionDeArchivos==null || this.oParametrosGenerador.formatoGeneracionDeArchivos.trim().length()<1) throw new ExcepcionNegocio("Debe ingresar el formato de generación de archivos");
		if(this.oParametrosGenerador.framework==null || this.oParametrosGenerador.framework.trim().length()<1) throw new ExcepcionNegocio("Debe ingresar el framework de generación de la vista y controlador");
		if(this.oParametrosGenerador.metodoCapturaUltimoIdInsertado==null || this.oParametrosGenerador.metodoCapturaUltimoIdInsertado.trim().length()<1) throw new ExcepcionNegocio("Debe ingresar el método de captura del último ID insertado");
		
		//Captura los datos de las tablas a través del Metadata de la conexión
		Map oMapInfoBaseDatos = new InfoBaseDatos().getInfoBaseDatos(conn,oParametrosGenerador.listaNombresTablasYVistas);
		
		//Formate la información que se trajo de la Base de Datos corrigiendo nobres con caracteres extraños y de acuerdo al formato de generación seleccionado
		Map oMapInfoFormateadaBaseDatos = new FormatearInfoBaseDatos(oMapInfoBaseDatos,oParametrosGenerador.formatoGeneracionDeArchivos).getDatosTablas();
		
		//Crear Conexión a Base de Datos
		CrearConexion oCrearConexion = new CrearConexion(oParametrosGenerador);
		oCrearConexion.crear();
		
		//Crear los Beans
		CrearBeans oCrearBeans = new CrearBeans(oMapInfoFormateadaBaseDatos,oParametrosGenerador.paqueteDestino,oParametrosGenerador.rutaPaqueteDestino,oParametrosGenerador.formatoGeneracionDeArchivos);
		oCrearBeans.crear();
		
		//Crear los DAOs
		CrearDaos oCrearDaos = new CrearDaos(oMapInfoFormateadaBaseDatos,oParametrosGenerador.paqueteDestino,oParametrosGenerador.rutaPaqueteDestino,oParametrosGenerador.formatoGeneracionDeArchivos);
		oCrearDaos.crear();
		
		//Crear las Secuencias
		CrearSecuencias oCrearSecuencias = new CrearSecuencias(oMapInfoFormateadaBaseDatos,oParametrosGenerador.paqueteDestino,oParametrosGenerador.rutaPaqueteDestino,oParametrosGenerador.formatoGeneracionDeArchivos);
		oCrearSecuencias.crear();		
		
		//Crear Acciones
		CrearAcciones oCrearAcciones = new CrearAcciones(oMapInfoFormateadaBaseDatos,oParametrosGenerador.paqueteDestino,oParametrosGenerador.rutaPaqueteDestino,oParametrosGenerador.formatoGeneracionDeArchivos,oParametrosGenerador.metodoCapturaUltimoIdInsertado); 
		oCrearAcciones.crear();
		
		//Crear Fachadas
		CrearFachadas oCrearFachadas = new CrearFachadas(oMapInfoFormateadaBaseDatos,oParametrosGenerador.paqueteDestino,oParametrosGenerador.rutaPaqueteDestino,oParametrosGenerador.formatoGeneracionDeArchivos); 
		oCrearFachadas.crear();
		
		//Crear Controladores
		CrearControladores oCrearControladores = new CrearControladores(oMapInfoFormateadaBaseDatos,oParametrosGenerador.paqueteDestino,oParametrosGenerador.rutaPaqueteDestino,oParametrosGenerador.formatoGeneracionDeArchivos,oParametrosGenerador.framework);
		oCrearControladores.crear();
		
		//Crear Adicionales requeridos según el Framework elegido
		CrearAdicionales oCrearAdicionales = new CrearAdicionales(oMapInfoFormateadaBaseDatos,oParametrosGenerador.paqueteDestino,oParametrosGenerador.rutaPaqueteDestino,oParametrosGenerador.rutaVistaDestino,oParametrosGenerador.formatoGeneracionDeArchivos,oParametrosGenerador.framework);
		oCrearAdicionales.crear();

		//Crear Vistas
		CrearVistas oCrearVistas = new CrearVistas(oMapInfoFormateadaBaseDatos,oParametrosGenerador.paqueteDestino,oParametrosGenerador.rutaPaqueteDestino,oParametrosGenerador.rutaVistaDestino,oParametrosGenerador.formatoGeneracionDeArchivos,oParametrosGenerador.framework);
		oCrearVistas.crear();

    	Map oMap = new HashMap();    	
    	return oMap;
    }
    
    

	
    public Map execute() throws Exception{
        Connection conn = null;
        //boolean commited = false;
        try{
        	Class.forName(oParametrosGenerador.driver).newInstance();
            conn = DriverManager.getConnection(oParametrosGenerador.url,oParametrosGenerador.login,oParametrosGenerador.password);
            //conn = new Conexion().getConexion();
            //conn.setAutoCommit(false);
            Map oMap = execute(conn);
            //conn.commit();
            //commited = true;
            return oMap;
        }
        catch(ExcepcionNegocio e) {
        	e.printStackTrace();
        	throw e;
        }        
        catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
        finally {
            try {
                if (conn != null) {
                    //if (!commited) { conn.rollback(); }
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }	

}
