

/*
 * 
 * @author Edward
 */

package com.edw.gen.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.edw.gen.modelo.acciones.FachadaGenerador;
import com.edw.gen.modelo.datos.beans.ParametrosGenerador;
import com.edw.gen.modelo.util.Constantes;

/**
 *
 * @author Edward
 */

public class ControlGenerador {
	
    public static void main(String [] args)
	{
        try{
        	/*
        	List listaFiltrosGrupo = new ArrayList();
        	listaFiltrosGrupo.add(new Filtro("A.codigo", new Integer(1),Filtro.OPERADOR_DIFERENTE));
        	listaFiltrosGrupo.add(new Filtro("A.nombre", Filtro.OPERADOR_IS_NULL));
        	
        	List listaFiltro = new ArrayList();
        	listaFiltro.add(new Filtro("A.estado",new Boolean("true")));
        	
        	List listaFiltros = new ArrayList();
        	listaFiltros.add(new Filtro("A.apellido1",(Object)"Rodriguez"));
        	listaFiltros.add(new Filtro("A.apellido2",(Object)"Moreno",Filtro.OPERADOR_NOT_NULL));
        	FiltroGrupo oFiltroGrupo1= new FiltroGrupo(listaFiltros,FiltroGrupo.OPERADOR_OR);
        	listaFiltro.add(oFiltroGrupo1);
        	listaFiltro.add(new Filtro("A.valor",new Long("10000")));
        	FiltroGrupo oFiltroGrupo= new FiltroGrupo(listaFiltro, FiltroGrupo.OPERADOR_OR);
        	
        	listaFiltrosGrupo.add(oFiltroGrupo);
        	
        	new FormateaFiltrosDaos().getFiltros(listaFiltrosGrupo);
        	if(true) return;
        	*/
        	
        	System.out.println("**** INICIO *****");
        	//Parametros de entrada de la interfaz:
            String nombreBaseDatos = "dbTCCdev";
            String url = "jdbc:oracle:thin:@DBGalileo2.dev.galileo.tcc.com.co:1523:"+nombreBaseDatos; //"jdbc:mysql://localhost:3306/"+nombreBaseDatos; //"jdbc:postgresql://localhost:5432/"+nombreBaseDatos; // "jdbc:oracle:thin:@localhost:1521:"+nombreBaseDatos; // "jdbc:sqlserver://localhost;databaseName="+nombreBaseDatos;
            String driver = "oracle.jdbc.driver.OracleDriver"; //"com.mysql.jdbc.Driver"; //"org.postgresql.Driver"; //"oracle.jdbc.driver.OracleDriver"; //"com.microsoft.sqlserver.jdbc.SQLServerDriver";
            //driver = "oracle.jdbc.xa.client.OracleXADataSource";
            String login = "repositorio_prod"; //"postgres"; 
            String password = "uno92014";//"adminpostgresql";
            String nombreProyecto = "edw";
            String paqueteDestino = "com."+nombreProyecto+".gen";
            
            //Nota: en windows la ruta se define así, Ejemplo: "c:\\temporal\\edward";
            String rutaDestino = "c:\\temporal\\edward"; //"/home/edward/generados"; //<-- Aca lo puedo ingresar asi / o \\ sin importar sistema operativo
            //Las rutas las puedo hacer relativos al directorio del usuario: Constantes.USER_HOME
            rutaDestino = rutaDestino.replace("\\", Constantes.FILE_SEPARATOR).replace("/", Constantes.FILE_SEPARATOR);
            String rutaPaqueteDestino = rutaDestino+Constantes.FILE_SEPARATOR+paqueteDestino.replace(".",Constantes.FILE_SEPARATOR);
            
            String rutaVistaDestino = rutaDestino+Constantes.FILE_SEPARATOR+"Vista";
            
            String rutaDriver = "//"; //<-- Ruta del Driver (no lo estoy usando)
            String formatoGeneracionDeArchivos = Constantes.FORMATO_GENERACION_JAVA;
            String framework = Constantes.FRAMEWORK_JSF;
            String metodoCapturaUltimoIdInsertado = Constantes.LAST_INSERT_ID;
            
            //Listado de nombres de tablas y vistas a ser generados (puede ser en mayusculas o minusculas),
            //si esta vacío genera todas
            List<String> listaNombresTablasYVistas = new ArrayList<String>();
            {
            	listaNombresTablasYVistas.add("SCT_PARAMETROS");
            }
                        
            ParametrosGenerador oParametrosGenerador = new ParametrosGenerador();
            
            oParametrosGenerador.nombreBaseDatos = nombreBaseDatos;
            oParametrosGenerador.url = url;
            oParametrosGenerador.driver = driver;
            oParametrosGenerador.login = login;
            oParametrosGenerador.password = password;
            oParametrosGenerador.paqueteDestino = paqueteDestino;
            oParametrosGenerador.rutaPaqueteDestino = rutaPaqueteDestino;
            oParametrosGenerador.rutaVistaDestino = rutaVistaDestino;
            oParametrosGenerador.rutaDriver = rutaDriver;
            oParametrosGenerador.formatoGeneracionDeArchivos = formatoGeneracionDeArchivos;
            oParametrosGenerador.framework = framework;
            oParametrosGenerador.metodoCapturaUltimoIdInsertado = metodoCapturaUltimoIdInsertado;
            oParametrosGenerador.listaNombresTablasYVistas = listaNombresTablasYVistas;
            
        	Map oMap = FachadaGenerador.generarCodigo(oParametrosGenerador);
        	System.out.println("**** TERMINO *****");

        }
        catch(Exception e){
        	System.out.println("Mensaje: "+e.getMessage());
            e.printStackTrace();
        }
    }
}
  