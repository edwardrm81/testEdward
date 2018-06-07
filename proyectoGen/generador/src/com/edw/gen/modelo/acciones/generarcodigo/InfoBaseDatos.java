package com.edw.gen.modelo.acciones.generarcodigo;



import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.edw.gen.apigen.ExcepcionNegocio;

    /*
     * Edward Rodriguez M.
     */
	 
public class InfoBaseDatos {
	
	
	public InfoBaseDatos() {
		
	}
	
    /*
     * Captura los datos de las tablas a través del Metadata de la conexión
     */
    public Map getInfoBaseDatos(Connection conn,List<String> listaNombresTablasYVistas) throws Exception {
    	
    	if(listaNombresTablasYVistas==null) throw new ExcepcionNegocio("La lista de nombres de tablas y vistas puede estar vacia pero no puede ser nula");
    	Map oMapTablas = new LinkedHashMap();
    	Map oMapVistas = new LinkedHashMap();
    	
		//Obtiene el Metadata de la conexión
		DatabaseMetaData oDataBaseMetaData = conn.getMetaData();
		if(oDataBaseMetaData==null) throw new ExcepcionNegocio("No pudo obtener el metadata de la Base de Datos");
		
        ResultSet oResultSetTables = null;
        ResultSet oResultSetColumns = null;
        ResultSet oResultSetPrimaryKeys = null;
        
        //PreparedStatement preparedStatementTablas = null;
        //ResultSet resultSetTablas = null;        
        
        try {
    		//oDataBaseMetaData.getCatalogs();
    		//oDataBaseMetaData.getSchemas();
        	
        	//Obtiene del metadata un resultset con los nombres de las tablas 
        	//Lista con la información de las tablas
        												//(catalog,schemaPattern,tableNamePattern,types)
        	oResultSetTables = oDataBaseMetaData.getTables(null,null,null,new String[]{"TABLE","VIEW"});
        	
        	//Convierte el listado de nombres de tablas a minusculas
        	if(listaNombresTablasYVistas.size()>0) {
        		for (int i = 0; i < listaNombresTablasYVistas.size(); i++) {
        			listaNombresTablasYVistas.set(i, listaNombresTablasYVistas.get(i).toLowerCase());
				}
        	}
        		
        	//Por cada tabla
        	//Si la lista de nombres de tablas y vistas esta vacia se generan todas las tablas y vistas, sino, solo se generan las que estén en la lista
        	while(oResultSetTables.next()) {
        	
        		if(
        			listaNombresTablasYVistas.size()<1 
        			||
        			(
        					listaNombresTablasYVistas.size()>0 
                			&& 
                			listaNombresTablasYVistas.contains(oResultSetTables.getString("TABLE_NAME").toLowerCase())
                	)
        		) 
        		{
        		
	        		//Capturar nombre de tabla
	        		String nombreTabla = oResultSetTables.getString("TABLE_NAME");
	        		String tipoTabla = oResultSetTables.getString("TABLE_TYPE"); // TABLE ó VIEW
	        		String esquemaTabla = oResultSetTables.getString("TABLE_SCHEM");
	        		String comentarioTabla = oResultSetTables.getString("REMARKS");
	        		                
	        		//Capturar los campos que pertenecen a la Clave Primaria de la tabla
	        															//(catalog, schema, table)
	            	oResultSetPrimaryKeys = oDataBaseMetaData.getPrimaryKeys(null,null,nombreTabla);
	            	//List listaCamposPK = new ArrayList();
	            	Map mapCamposPK = new HashMap();
	            	while(oResultSetPrimaryKeys.next()) {
	            		String nombreColumnaPK = oResultSetPrimaryKeys.getString("COLUMN_NAME");
	            		Integer secuenciaPK = new Integer(oResultSetPrimaryKeys.getString("KEY_SEQ"));
	            		
	            		mapCamposPK.put(nombreColumnaPK,secuenciaPK);
	            		//mapCamposPK.put("nombre", nombreColumnaPK);
	            		//mapCamposPK.put("secuenciaPK", secuenciaPK);
	            		//listaCamposPK.add(mapCamposPK);
	            	}
	        		//Ordenar la lista de campos de la llave primaria por secuenciaPK y luego por nombre
	            	//new OrdenarListaDeObjetos().ordenarPorPropiedad(listaCamposPK,"secuenciaPK","nombre");
	            	
	            	//Capturar los campos que pertenecen a la tabla
	            	Map mapCamposDeLaTabla = new LinkedHashMap();
	            	
													//(catalog, schemaPattern, tableNamePattern, columnNamePattern)
	            	oResultSetColumns = oDataBaseMetaData.getColumns(null,null,nombreTabla,null);
	            	//List listaCamposDeLaTabla = new ArrayList();            	
	            	while(oResultSetColumns.next()) {
	            		String nombreColumna = oResultSetColumns.getString("COLUMN_NAME");
	            		Integer tipoDatoJDBCColumna = new Integer(oResultSetColumns.getString("DATA_TYPE")); //código numérico del tipo de dato
	            		Boolean columnaNulable = oResultSetColumns.getString("NULLABLE").equals("0")? new Boolean(false) : oResultSetColumns.getString("NULLABLE").equals("1")? new Boolean(true) : null;
	            		String comentarioColumna = oResultSetColumns.getString("REMARKS");
	            		//String nombreTipoColumna = oResultSetColumns.getString("TYPE_NAME");
	            		//String esNulableColumna = oResultSetColumns.getString("IS_NULLABLE");
	            		//String tamanoColumna = oResultSetColumns.getString("COLUMN_SIZE");
	                
	            		Map mapCampo = new HashMap();
	            		mapCampo.put("nombre", nombreColumna);
	            		mapCampo.put("tipoDatoJDBC", tipoDatoJDBCColumna);
	            		mapCampo.put("esNulable", columnaNulable);
	            		mapCampo.put("comentario", comentarioColumna);
	            		
	            		//Si el campo pertenece a la llave primaria, entonces se agregar el número de secuencia de este en la clave primaria
	            		if(mapCamposPK.containsKey(nombreColumna)) {
	            			mapCampo.put("secuenciaPK", (Integer) mapCamposPK.get(nombreColumna));
	            		}
	            		//sino, se establece en secuencia PK el valor de NULL
	            		else {
	            			mapCampo.put("secuenciaPK",null);
	            		}
	            		
	            		mapCamposDeLaTabla.put(nombreColumna, mapCampo);
	            		//listaCamposDeLaTabla.add(mapCampo);            		
	            	}                
	
	        		//Ordenar la lista de campos por secuenciaPK y luego por nombre
	            	//new OrdenarListaDeObjetos().ordenarPorPropiedad(listaCamposDeLaTabla,"secuenciaPK","nombre");
	
	            	
	        		Map oMapTabla = new HashMap();
	        		oMapTabla.put("nombre", nombreTabla);
	        		oMapTabla.put("esquema", esquemaTabla);        		
	        		oMapTabla.put("comentario", comentarioTabla);
	        		oMapTabla.put("campos", mapCamposDeLaTabla);
	        		//oMapTabla.put("campos", listaCamposDeLaTabla);
	            	//oMapTabla.put("primaryKey", listaNombreColumnasPK);
	            	
	            	if(tipoTabla.equalsIgnoreCase("TABLE")) oMapTablas.put(nombreTabla,oMapTabla);
	            	else if(tipoTabla.equalsIgnoreCase("VIEW")) oMapVistas.put(nombreTabla,oMapTabla);
        		}
        	}

        } catch (Exception e) {
            throw new Exception(e);
        } finally {       	
            if (oResultSetTables != null) oResultSetTables.close();
            if (oResultSetColumns != null) oResultSetColumns.close();
            if (oResultSetPrimaryKeys != null) oResultSetPrimaryKeys.close();
        } 
        
        Map oMapInfoBaseDatos = new HashMap();
        
        oMapInfoBaseDatos.put("tablas", oMapTablas);
        oMapInfoBaseDatos.put("vistas", oMapVistas);
        
        return oMapInfoBaseDatos;
    }
    

    
}
