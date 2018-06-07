package com.edw.gen.modelo.formatearcodigo;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.edw.gen.apigen.ConversorTipoJavaATipoJDBC;
import com.edw.gen.apigen.FormateaFiltrosDaos;
import com.edw.gen.apigen.OrderBy;
import com.edw.gen.apigen.OrderBys;
import com.edw.gen.modelo.util.Constantes;
import com.edw.gen.modelo.util.ConversorDeClase;
import com.edw.gen.modelo.util.OrdenarListaDeObjetos;

public class FormatearEnJavaDAOs {

	public FormatearEnJavaDAOs() {
		
	}
	
	public StringBuffer getFormato(String nombreTabla,String nombreEnBeanTabla,String esquemaTabla,String comentarioTabla,Map mapCamposDeLaTabla,String paqueteDaos,String paqueteBeans,String paqueteSecuenciasIncrementos,boolean esVista) {
		
		// mapCamposDeLaTabla tiene los siguientes atributos:
		// "nombre" 			String,		"nombreEnBean"		String,			"tipoDatoJDBC"		Integer,
		// "tipoDatoJDBCCadena" String,		"tipoDatoLenguaje"	String,			"esNulable"			Boolean,
		// "comentario"			String,		"secuenciaPK"		Integer
		
		// Lista Completa de campos ordenados por secuenciaPK y por nombre:
		List listaCampos = new ArrayList();
		// Lista de campos sin aquellos que pertenecen a la clave primaria ordenados por secuenciaPK y por nombre:
		List listaCamposSinPK = new ArrayList();
		// Lista de campos de la clave primaria ordenados por secuenciaPK y por nombre: 
		List listaCamposPK = new ArrayList();
		
		//Se llenan la lista:
		Iterator itMapCamposDeLaTabla = mapCamposDeLaTabla.entrySet().iterator();		
		while(itMapCamposDeLaTabla.hasNext()) {
			Map.Entry oMapEntryCampos = (Map.Entry) itMapCamposDeLaTabla.next();
			//String nombreCampo = (String)oMapEntryCampos.getKey();
			Map oMapCampo = (Map)oMapEntryCampos.getValue();
			listaCampos.add(oMapCampo);
			
			if(((Integer)oMapCampo.get("secuenciaPK"))==null) {
				listaCamposSinPK.add(oMapCampo);
			}
			else {
				listaCamposPK.add(oMapCampo);
			}
		}
		// Ordenar la listas de campos, campos sin PK y camposPK por secuenciaPK y por nombre:
		new OrdenarListaDeObjetos().ordenarPorPropiedades(listaCampos,"secuenciaPK","nombre");
		new OrdenarListaDeObjetos().ordenarPorPropiedades(listaCamposSinPK,"secuenciaPK","nombre");
		new OrdenarListaDeObjetos().ordenarPorPropiedades(listaCamposPK,"secuenciaPK","nombre");
		
		StringBuffer cadena = new StringBuffer();
		
	    cadena.append("\n");
	    cadena.append("package "+paqueteDaos+";\n");
	    cadena.append("\n");
	    cadena.append("import "+paqueteBeans+"."+nombreEnBeanTabla+";"+"\n");
	    cadena.append("\n");
	    cadena.append("import "+paqueteSecuenciasIncrementos+".SecuenciasIncrementos;"+"\n");
	    cadena.append("import com.edw.gen.apigen.*;"+"\n");
	    cadena.append("import java.sql.Connection;"+"\n");
	    cadena.append("import java.sql.PreparedStatement;"+"\n");
	    cadena.append("import java.sql.ResultSet;"+"\n");
	    cadena.append("import java.sql.SQLException;"+"\n");
	    cadena.append("import java.util.ArrayList;"+"\n");
	    cadena.append("import java.util.List;"+"\n");
	    cadena.append("import java.util.Map;"+"\n");
	    cadena.append("\n");
	    cadena.append("/* @author Edward */"+"\n");
	    cadena.append("\n");
	    cadena.append("// Comentario: "+comentarioTabla+"\n");
	    cadena.append("public class DAO"+nombreEnBeanTabla+" {"+"\n");
	    cadena.append("\n");
	    
	    ////////// Querys: //////////////////////////////////////////////////
	    cadena.append("    public  static String SQLSELECTBASE = \"SELECT ");	    
	    for (int i=0; i<listaCampos.size(); i++) {
			String nombre = (String)((Map)listaCampos.get(i)).get("nombre");
			cadena.append("A."+nombre+",");
		}
	    cadena.delete(cadena.length()-1, cadena.length()); cadena.append(" \";"+"\n");
	    
	    cadena.append("    private static String SQLSELECTALL = \"SELECT ");	    
	    for (int i=0; i<listaCampos.size(); i++) {
			String nombre = (String)((Map)listaCampos.get(i)).get("nombre");
			cadena.append("A."+nombre+",");
		}
	    cadena.delete(cadena.length()-1, cadena.length()); 
	    cadena.append(" FROM "+nombreTabla+" A \";"+"\n");
	    
	    if(!esVista && listaCamposPK.size()>0) {
		    cadena.append("    private static String SQLSELECTBYPRIMARYKEY = \"SELECT ");
		    for (int i=0; i<listaCampos.size(); i++) {
				String nombre = (String)((Map)listaCampos.get(i)).get("nombre");
				cadena.append("A."+nombre+",");
			}
		    cadena.delete(cadena.length()-1, cadena.length());
		    cadena.append(" FROM "+nombreTabla+" A WHERE ");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombre = (String)((Map)listaCamposPK.get(i)).get("nombre");
				cadena.append(" A."+nombre+" = ? AND");
			}
		    cadena.delete(cadena.length()-3, cadena.length()); cadena.append(" \";"+"\n");
	    }
	    
	    if(!esVista && listaCamposPK.size()>0) {
		    cadena.append("    private static String SQLUPDATE = \"UPDATE "+nombreTabla+" SET ");
		    for (int i=0; i<listaCampos.size(); i++) {
				String nombre = (String)((Map)listaCampos.get(i)).get("nombre");
				cadena.append(" "+nombre+" = ?,");
			}
		    cadena.delete(cadena.length()-1, cadena.length()); cadena.append(" WHERE ");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombre = (String)((Map)listaCamposPK.get(i)).get("nombre");
				cadena.append(" "+nombre+" = ? AND");
			}
		    cadena.delete(cadena.length()-3, cadena.length()); cadena.append(" \";"+"\n");
	    }
	    
	    if(!esVista && listaCamposPK.size()>0) {
		    cadena.append("    private static String SQLDELETE = \"DELETE FROM "+nombreTabla+" WHERE ");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombre = (String)((Map)listaCamposPK.get(i)).get("nombre");
				cadena.append(" "+nombre+" = ? AND");
			}
		    cadena.delete(cadena.length()-3, cadena.length()); cadena.append(" \";"+"\n");
	    }

	    if(!esVista) {
		    cadena.append("    private static String SQLINSERT = \"INSERT INTO "+nombreTabla+"(");
		    for (int i=0; i<listaCampos.size(); i++) {
				String nombre = (String)((Map)listaCampos.get(i)).get("nombre");
				cadena.append(nombre+",");
			}
		    cadena.delete(cadena.length()-1, cadena.length()); cadena.append(") VALUES (");
		    for (int i=0; i<listaCampos.size(); i++) {
				cadena.append("?,");
			}
		    cadena.delete(cadena.length()-1, cadena.length()); cadena.append(") \";"+"\n");
	    }
	    
	    if(!esVista) {
		    cadena.append("    private static String SQLINSERTCONAUTOINCREMENTO = \"INSERT INTO "+nombreTabla+"(");
		    for (int i=0; i<listaCamposSinPK.size(); i++) {
				String nombre = (String)((Map)listaCamposSinPK.get(i)).get("nombre");
				cadena.append(nombre+",");
			}
		    cadena.delete(cadena.length()-1, cadena.length()); cadena.append(") VALUES (");
		    for (int i=0; i<listaCamposSinPK.size(); i++) {
				cadena.append("?,");
			}
		    cadena.delete(cadena.length()-1, cadena.length()); cadena.append(") \";"+"\n");
	    }
	    
	    cadena.append("    private static String SQLSELECTCOUNT = \"SELECT COUNT(1) FROM "+nombreTabla+" \";"+"\n");
	    cadena.append("\n");
	    
	    cadena.append("    public DAO"+nombreEnBeanTabla+"() {"+"\n");
	    cadena.append("\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    
	    ////////// selectPK: //////////////////////////////////////////////////////////////////
	    if(!esVista && listaCamposPK.size()>0) {
		    cadena.append("    public "+nombreEnBeanTabla+" selectPK(Connection conn,");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				String tipoDatoLenguaje = (String)((Map)listaCamposPK.get(i)).get("tipoDatoLenguaje");
				
				cadena.append(" "+tipoDatoLenguaje+" "+nombreEnBean+",");
			}
		    cadena.delete(cadena.length()-1, cadena.length()); cadena.append(") throws Exception {"+"\n");
		    cadena.append("\n");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				cadena.append("        if("+nombreEnBean+"==null) throw new SQLException(\"No ha ingresado el valor de "+nombreEnBean+"\");"+"\n");
		    }
		    cadena.append("\n");
		    cadena.append("        PreparedStatement preparedStatement = null;"+"\n");
		    cadena.append("        ResultSet resultSet = null;"+"\n");
		    cadena.append("\n");
		    cadena.append("        try {"+"\n");
		    cadena.append("            preparedStatement = conn.prepareStatement(SQLSELECTBYPRIMARYKEY);"+"\n");
		    cadena.append("\n");
		    cadena.append("            int i=1;"+"\n");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				String tipoDatoJDBCCadena = (String)((Map)listaCamposPK.get(i)).get("tipoDatoJDBCCadena");
				
				cadena.append("            preparedStatement.setObject(i++,"+nombreEnBean+","+tipoDatoJDBCCadena+");"+"\n");
			}
		    cadena.append("\n");
		    cadena.append("            resultSet = preparedStatement.executeQuery();"+"\n");
		    cadena.append("\n");
		    cadena.append("            List listaObjeto = this.getListaObjeto(resultSet);"+"\n");
		    cadena.append("            if(listaObjeto.size()==0) {"+"\n");
		    cadena.append("                throw new SQLException(\"No encontro el registro en la tabla '"+nombreTabla+"'\");"+"\n");
		    cadena.append("            };"+"\n");
		    cadena.append("\n");
		    cadena.append("            if(listaObjeto.size()>1) {"+"\n");
		    cadena.append("                throw new SQLException(\"Se encontraron registros duplicados por clave primaria en la tabla '"+nombreTabla+"'\");"+"\n");
		    cadena.append("            };"+"\n");	    
		    cadena.append("\n");
		    cadena.append("            return ("+nombreEnBeanTabla+")listaObjeto.get(0);"+"\n");
		    cadena.append("\n");
		    cadena.append("        } catch(SQLException e) {"+"\n");
		    cadena.append("            throw new SQLException(e);"+"\n");
		    cadena.append("        } finally {"+"\n");
		    cadena.append("            if (resultSet != null) resultSet.close();"+"\n");
		    cadena.append("            if (preparedStatement != null) preparedStatement.close();"+"\n");
		    cadena.append("        }"+"\n");
		    cadena.append("    }"+"\n");
		    cadena.append("\n");
	    }
	    
	    //////////select count: //////////////////////////////////////////////////////////////////
	    cadena.append("    public java.lang.Long selectCount(Connection conn) throws Exception {"+"\n");
	    cadena.append("        return this.selectCount(conn,null,null,null);"+"\n");	
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    
	    
	    ////////// select count con Filtros y Limits: //////////////////////////////////////////////////////////////////
	    cadena.append("    public java.lang.Long selectCount(Connection conn, List listaFiltros, java.lang.Long posicionInicialLimit, java.lang.Long cantidadDeFilasLimit ) throws Exception {"+"\n");
	    cadena.append("        return (Long) this.select(conn, true, listaFiltros, null, posicionInicialLimit, cantidadDeFilasLimit );"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");	    
	    
	    
	    //////////select todo: //////////////////////////////////////////////////////////////////
	    cadena.append("    public List select(Connection conn) throws Exception {"+"\n");
	    cadena.append("        return this.select(conn,null,null,null,null);"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");

	    //////////select con Filtros sin OrderBys: //////////////////////////////////////////////////////////////////
	    cadena.append("    public List select(Connection conn,List listaFiltros) throws Exception {"+"\n");
	    cadena.append("        return this.select(conn,listaFiltros,null,null,null);"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    
	    //////////select All con OrderBys: //////////////////////////////////////////////////////////////////
	    cadena.append("    public List select(Connection conn, OrderBys orderBys) throws Exception {"+"\n");
	    cadena.append("        return this.select(conn,null,orderBys,null,null);"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    
	    //////////select con Filtros y OrderBys: //////////////////////////////////////////////////////////////////
	    cadena.append("    public List select(Connection conn, List listaFiltros, OrderBys orderBys) throws Exception {"+"\n");
	    cadena.append("        return this.select(conn,listaFiltros,orderBys,null,null);"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");

	    ////////// select con Filtros, OrderBys y Limits: //////////////////////////////////////////////////////////////////
	    cadena.append("    public List select(Connection conn, List listaFiltros, OrderBys orderBys, java.lang.Long posicionInicialLimit, java.lang.Long cantidadDeFilasLimit ) throws Exception {"+"\n");
	    cadena.append("        return (List)this.select(conn,false,listaFiltros,orderBys,posicionInicialLimit, cantidadDeFilasLimit);"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    
	    ////////// select Generico para Count y Select: //////////////////////////////////////////////////////////////////
	    cadena.append("    public Object select(Connection conn, boolean esCount, List listaFiltros, OrderBys orderBys, java.lang.Long posicionInicialLimit, java.lang.Long cantidadDeFilasLimit ) throws Exception {"+"\n");
	    cadena.append("\n");
	    cadena.append("        PreparedStatement preparedStatement = null;"+"\n");
	    cadena.append("        ResultSet resultSet = null;"+"\n");
	    cadena.append("\n");
	    cadena.append("        try {"+"\n");
	    cadena.append("\n");
	    cadena.append("            Map oMap = new FormateaFiltrosDaos().getFiltros(listaFiltros);"+"\n");
	    cadena.append("            String strFiltros = (String)oMap.get(\"sqlWhereFiltros\");"+"\n");
	    cadena.append("            if(strFiltros.trim().length()>0 && SQLSELECTALL.contains(\"WHERE\")) {"+"\n");	    
	    cadena.append("                strFiltros = \" AND \"+strFiltros;"+"\n");
	    cadena.append("            }"+"\n");
	    cadena.append("\n");
	    cadena.append("            List listaObjetosCamposFiltros = (List) oMap.get(\"listaObjetosCamposFiltros\");"+"\n");
	    cadena.append("\n");
	    cadena.append("            StringBuffer strOrderBys = new StringBuffer(\"\");"+"\n");
	    cadena.append("            List listaOrderBys = new ArrayList();"+"\n");
	    cadena.append("            if(orderBys!=null && orderBys.listaOrderBy.size()>0) {"+"\n");
	    cadena.append("                listaOrderBys = orderBys.listaOrderBy;"+"\n");
	    cadena.append("                strOrderBys.append(\" ORDER BY \");"+"\n");
	    cadena.append("            }"+"\n"); 
	    cadena.append("\n");
	    cadena.append("            for (int i=0; i<listaOrderBys.size(); i++) {"+"\n");
	    cadena.append("                OrderBy oOrderBy = (OrderBy)listaOrderBys.get(i);"+"\n");
	    cadena.append("                strOrderBys.append(oOrderBy.campo+\" \"+oOrderBy.sentido+\",\");"+"\n");
	    cadena.append("            }"+"\n");
	    cadena.append("            if(listaOrderBys!=null && listaOrderBys.size()>0) strOrderBys.delete(strOrderBys.length()-1, strOrderBys.length());"+"\n");
	    cadena.append("            strOrderBys.append(\" \");"+"\n");
	    cadena.append("\n");
	    cadena.append("            String limitSQL = \" \";"+"\n");
	    cadena.append("            if(posicionInicialLimit!=null && cantidadDeFilasLimit!=null) limitSQL =\" LIMIT \"+posicionInicialLimit+\",\"+cantidadDeFilasLimit+\" \";"+"\n");
	    cadena.append("\n");
	    cadena.append("            if(esCount) preparedStatement = conn.prepareStatement(SQLSELECTALL+strFiltros+strOrderBys.toString()+limitSQL);"+"\n");
	    cadena.append("            else preparedStatement = conn.prepareStatement(SQLSELECTCOUNT+strFiltros+strOrderBys.toString()+limitSQL);"+"\n");
	    cadena.append("\n");
	    cadena.append("            int i=1;"+"\n");
	    cadena.append("            for(int k=0; k<listaObjetosCamposFiltros.size(); k++) {"+"\n");
	    cadena.append("                Object oObject = (Object)listaObjetosCamposFiltros.get(k);"+"\n");
	    cadena.append("                preparedStatement.setObject(i++,oObject,new ConversorTipoJavaATipoJDBC().getTipoDatoJDBC(oObject.getClass().getName()));"+"\n");
	    cadena.append("            }"+"\n");
	    cadena.append("\n");
	    cadena.append("            resultSet = preparedStatement.executeQuery();"+"\n");
	    cadena.append("\n");
	    cadena.append("            if(esCount) {"+"\n");
	    cadena.append("                java.lang.Long conteo = null;"+"\n");
	    cadena.append("                if(resultSet.next()){"+"\n");
	    cadena.append("                    Object oObject = resultSet.getObject(1);"+"\n");
	    cadena.append("                    conteo = oObject==null? null : new java.lang.Long(oObject.toString());"+"\n");
	    cadena.append("                }"+"\n");
	    cadena.append("                if(conteo==null) throw new SQLException(\"No encontro la cantidad de registros en '"+nombreTabla+"'\");"+"\n");
	    cadena.append("                return (conteo);"+"\n");
	    cadena.append("            }"+"\n");
	    cadena.append("            else return this.getListaObjeto(resultSet);"+"\n");
	    cadena.append("\n");
	    cadena.append("        } catch(SQLException e) {"+"\n");
	    cadena.append("            throw new SQLException(e);"+"\n");
	    cadena.append("        } finally {"+"\n");
	    cadena.append("            if (resultSet != null) resultSet.close();"+"\n");
	    cadena.append("            if (preparedStatement != null) preparedStatement.close();"+"\n");
	    cadena.append("        }"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    
	    ////////// getListaObjeto: //////////////////////////////////////////////////////////////////
	    cadena.append("    private List getListaObjeto(ResultSet resultSet) throws Exception {"+"\n");
	    cadena.append("\n");
	    cadena.append("        List listaObjeto = new ArrayList();"+"\n");
	    cadena.append("        "+nombreEnBeanTabla+" o"+nombreEnBeanTabla+" = null;"+"\n");
	    cadena.append("        int i = 1;"+"\n");
	    cadena.append("        while(resultSet.next()){"+"\n");
	    cadena.append("            i = 1;"+"\n");
	    cadena.append("            o"+nombreEnBeanTabla+" = new "+nombreEnBeanTabla+"();"+"\n");
	    cadena.append("            Object oObject = null;"+"\n");
	    cadena.append("\n");
	    
	    for (int i=0; i<listaCampos.size(); i++) {
			String nombreEnBean = (String)((Map)listaCampos.get(i)).get("nombreEnBean");
			String tipoDatoLenguaje = (String)((Map)listaCampos.get(i)).get("tipoDatoLenguaje");
			
			cadena.append("            oObject = resultSet.getObject(i++);"+"\n");
			cadena.append("            o"+nombreEnBeanTabla+"."+nombreEnBean+" = "+"oObject==null? null : "+new ConversorDeClase().getCastObjeto(tipoDatoLenguaje, "oObject")+";"+"\n");
			//cadena.append("            o"+nombreEnBeanTabla+"."+nombreEnBean+" = "+"oObject==null? null : ("+tipoDatoLenguaje+")oObject;"+"\n");
			//cadena.append("            o"+nombreEnBeanTabla+"."+nombreEnBean+" = "+"oObject==null? null : new "+tipoDatoLenguaje+"(oObject.toString());"+"\n");
			cadena.append("\n");
		}
	    
	    cadena.append("            listaObjeto.add(o"+nombreEnBeanTabla+");"+"\n");
	    cadena.append("        }"+"\n");
	    cadena.append("\n");
	    cadena.append("        return listaObjeto;"+"\n");
	    cadena.append("    }"+"\n");
	    cadena.append("\n");
	    	    
	    ////////// actualizar: //////////////////////////////////////////////////////////////////
	    if(!esVista && listaCamposPK.size()>0) {
		    cadena.append("    public void actualizar(Connection conn,");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				String tipoDatoLenguaje = (String)((Map)listaCamposPK.get(i)).get("tipoDatoLenguaje");
				
				cadena.append(" "+tipoDatoLenguaje+" "+nombreEnBean+",");
			}
		    cadena.append(" "+nombreEnBeanTabla+" o"+nombreEnBeanTabla+") throws Exception {"+"\n");
		    cadena.append("\n");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				cadena.append("        if("+nombreEnBean+"==null) throw new SQLException(\"No ha ingresado el valor de "+nombreEnBean+"\");"+"\n");
		    }
		    cadena.append("\n");
		    cadena.append("        PreparedStatement preparedStatement = null;"+"\n");
		    cadena.append("\n");
		    cadena.append("        try {"+"\n");
		    cadena.append("            preparedStatement = conn.prepareStatement(SQLUPDATE);"+"\n");
		    cadena.append("\n");
		    cadena.append("            int i = 1;"+"\n");
		    cadena.append("\n");
		    for (int i=0; i<listaCampos.size(); i++) {
				String nombreEnBean = (String)((Map)listaCampos.get(i)).get("nombreEnBean");
				String tipoDatoJDBCCadena = (String)((Map)listaCampos.get(i)).get("tipoDatoJDBCCadena");
		    
			    cadena.append("            {"+"\n");
			    cadena.append("                if(o"+nombreEnBeanTabla+"."+nombreEnBean+"!=null) preparedStatement.setObject(i++, o"+nombreEnBeanTabla+"."+nombreEnBean+","+tipoDatoJDBCCadena+");"+"\n");
			    cadena.append("                else preparedStatement.setNull(i++, "+tipoDatoJDBCCadena+");"+"\n");
			    cadena.append("            }"+"\n");
		    }
	
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				String tipoDatoJDBCCadena = (String)((Map)listaCamposPK.get(i)).get("tipoDatoJDBCCadena");
				
			    cadena.append("            preparedStatement.setObject(i++,"+nombreEnBean+","+tipoDatoJDBCCadena+");"+"\n");
		    }
		    cadena.append("\n");
		    cadena.append("            int updatedRows = preparedStatement.executeUpdate();"+"\n");
		    cadena.append("\n");
		    cadena.append("            if (updatedRows == 0) {"+"\n");
		    cadena.append("                throw new SQLException(\"No se actualizo el registro en la tabla '"+nombreEnBeanTabla+"'\");"+"\n");
		    cadena.append("            }"+"\n");
		    cadena.append("\n");
		    cadena.append("            if (updatedRows > 1) {"+"\n");
		    cadena.append("                throw new SQLException(\"Registro duplicado por clave primaria en la tabla '"+nombreEnBeanTabla+"'\");"+"\n");
		    cadena.append("            }"+"\n");
		    cadena.append("\n");
		    cadena.append("        } catch(SQLException e) {"+"\n");
		    cadena.append("            throw new SQLException(e);"+"\n");
		    cadena.append("        } finally {"+"\n");
		    cadena.append("            if (preparedStatement != null) preparedStatement.close();"+"\n");
		    cadena.append("        }"+"\n");
		    cadena.append("    }"+"\n");
		    cadena.append("\n");
	    }
	    
	    ////////// eliminar: //////////////////////////////////////////////////////////////////
	    if(!esVista && listaCamposPK.size()>0) {
		    cadena.append("    public void eliminar(Connection conn, ");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				String tipoDatoLenguaje = (String)((Map)listaCamposPK.get(i)).get("tipoDatoLenguaje");
				
				cadena.append(tipoDatoLenguaje+" "+nombreEnBean+",");
			}
		    cadena.delete(cadena.length()-1, cadena.length()); cadena.append(") throws Exception {"+"\n");
		    cadena.append("\n");
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				cadena.append("        if("+nombreEnBean+"==null) throw new SQLException(\"No ha ingresado el valor de "+nombreEnBean+"\");"+"\n");
		    }
		    cadena.append("\n");
		    cadena.append("        PreparedStatement preparedStatement = null;"+"\n");
		    cadena.append("\n");
		    cadena.append("        try {"+"\n");
		    cadena.append("            preparedStatement = conn.prepareStatement(SQLDELETE);"+"\n");
		    cadena.append("\n");
		    cadena.append("            int i = 1;"+"\n");
		    cadena.append("\n");
		    
		    for (int i=0; i<listaCamposPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
				String tipoDatoJDBCCadena = (String)((Map)listaCamposPK.get(i)).get("tipoDatoJDBCCadena");
				
			    cadena.append("            preparedStatement.setObject(i++,"+nombreEnBean+","+tipoDatoJDBCCadena+");"+"\n");
		    }
		    cadena.append("\n");
		    cadena.append("            int removedRows = preparedStatement.executeUpdate();"+"\n");
		    cadena.append("\n");
		    cadena.append("            if (removedRows == 0) {"+"\n");
		    cadena.append("                throw new SQLException(\"No se elimino ningun registro en la tabla '"+nombreEnBeanTabla+"'\");"+"\n");
		    cadena.append("            }"+"\n");
		    cadena.append("\n");
		    cadena.append("            if (removedRows > 1) {"+"\n");
		    cadena.append("                throw new SQLException(\"Registro duplicado por clave primaria en la tabla '"+nombreEnBeanTabla+"'\");"+"\n");
		    cadena.append("            }"+"\n");
		    cadena.append("\n");
		    cadena.append("        } catch(SQLException e) {"+"\n");
		    cadena.append("            throw new SQLException(e);"+"\n");
		    cadena.append("        } finally {"+"\n");
		    cadena.append("            if (preparedStatement != null) preparedStatement.close();"+"\n");
		    cadena.append("        }"+"\n");
		    cadena.append("    }"+"\n");
		    cadena.append("\n");
	    }
	    
	    ////////// insertar: //////////////////////////////////////////////////////////////////
	    if(!esVista) {
		    cadena.append("    public void insertar(Connection conn,");
		    cadena.append(" "+nombreEnBeanTabla+" o"+nombreEnBeanTabla+") throws Exception {"+"\n");
		    cadena.append("\n");
		    cadena.append("        PreparedStatement preparedStatement = null;"+"\n");
		    cadena.append("\n");
		    cadena.append("        try {"+"\n");
		    cadena.append("\n");
		    cadena.append("            preparedStatement = conn.prepareStatement(SQLINSERT);"+"\n");
		    cadena.append("\n");
		    cadena.append("            int i = 1;"+"\n");
		    for (int i=0; i<listaCampos.size(); i++) {
				String nombreEnBean = (String)((Map)listaCampos.get(i)).get("nombreEnBean");
				String tipoDatoJDBCCadena = (String)((Map)listaCampos.get(i)).get("tipoDatoJDBCCadena");
		    
			    cadena.append("            {"+"\n");
			    cadena.append("                if(o"+nombreEnBeanTabla+"."+nombreEnBean+"!=null) preparedStatement.setObject(i++, o"+nombreEnBeanTabla+"."+nombreEnBean+","+tipoDatoJDBCCadena+");"+"\n");
			    cadena.append("                else preparedStatement.setNull(i++, "+tipoDatoJDBCCadena+");"+"\n");
			    cadena.append("            }"+"\n");
		    }
		    cadena.append("\n");
		    cadena.append("            int insertedRows = preparedStatement.executeUpdate();"+"\n");
		    cadena.append("\n");
		    cadena.append("            if (insertedRows == 0) {"+"\n");
		    cadena.append("                throw new SQLException(\"No pudo insertar el registro en la tabla '"+nombreEnBeanTabla+"'\");"+"\n");
		    cadena.append("            }"+"\n");
		    cadena.append("\n");
		    cadena.append("            if (insertedRows > 1) {"+"\n");
		    cadena.append("                throw new SQLException(\"Intento duplicar el nuevo registro en la tabla '"+nombreEnBeanTabla+"'\");"+"\n");
		    cadena.append("            }"+"\n");
		    cadena.append("\n");
		    cadena.append("        } catch(SQLException e) {"+"\n");
		    cadena.append("            throw new SQLException(e);"+"\n");
		    cadena.append("        } finally {"+"\n");
		    cadena.append("            if (preparedStatement != null) preparedStatement.close();"+"\n");
		    cadena.append("        }"+"\n");
		    cadena.append("    }"+"\n");
		    cadena.append("\n");
	    }
	    ////////// insertar con PK con Autoincremento: //////////////////////////////////////////////////////////////////
	    if(!esVista) {
		    cadena.append("    public void insertarAInc(Connection conn,");
		    cadena.append(" "+nombreEnBeanTabla+" o"+nombreEnBeanTabla+") throws Exception {"+"\n");
		    cadena.append("\n");
		    cadena.append("        PreparedStatement preparedStatement = null;"+"\n");
		    cadena.append("\n");
		    cadena.append("        try {"+"\n");
		    cadena.append("\n");
		    cadena.append("            preparedStatement = conn.prepareStatement(SQLINSERTCONAUTOINCREMENTO);"+"\n");
		    cadena.append("\n");
		    cadena.append("            int i = 1;"+"\n");
		    for (int i=0; i<listaCamposSinPK.size(); i++) {
				String nombreEnBean = (String)((Map)listaCamposSinPK.get(i)).get("nombreEnBean");
				String tipoDatoJDBCCadena = (String)((Map)listaCamposSinPK.get(i)).get("tipoDatoJDBCCadena");
		    
			    cadena.append("            {"+"\n");
			    cadena.append("                if(o"+nombreEnBeanTabla+"."+nombreEnBean+"!=null) preparedStatement.setObject(i++, o"+nombreEnBeanTabla+"."+nombreEnBean+","+tipoDatoJDBCCadena+");"+"\n");
			    cadena.append("                else preparedStatement.setNull(i++, "+tipoDatoJDBCCadena+");"+"\n");
			    cadena.append("            }"+"\n");
		    }
		    cadena.append("\n");
		    cadena.append("            int insertedRows = preparedStatement.executeUpdate();"+"\n");
		    cadena.append("\n");
		    cadena.append("            if (insertedRows == 0) {"+"\n");
		    cadena.append("                throw new SQLException(\"No pudo insertar el registro en la tabla '"+nombreEnBeanTabla+"'\");"+"\n");
		    cadena.append("            }"+"\n");
		    cadena.append("\n");
		    cadena.append("            if (insertedRows > 1) {"+"\n");
		    cadena.append("                throw new SQLException(\"Intento duplicar el nuevo registro en la tabla '"+nombreEnBeanTabla+"'\");"+"\n");
		    cadena.append("            }"+"\n");
		    cadena.append("\n");
		    cadena.append("        } catch(SQLException e) {"+"\n");
		    cadena.append("            throw new SQLException(e);"+"\n");
		    cadena.append("        } finally {"+"\n");
		    cadena.append("            if (preparedStatement != null) preparedStatement.close();"+"\n");
		    cadena.append("        }"+"\n");
		    cadena.append("    }"+"\n");
		    cadena.append("\n");
	    }
	    ////////// insertar con Secuencia: //////////////////////////////////////////////////////////////////
	    if(!esVista) {
		    cadena.append("    public Object insertarSec(Connection conn,");
		    cadena.append(" "+nombreEnBeanTabla+" o"+nombreEnBeanTabla+",String SQLSECUENCIA_"+nombreEnBeanTabla.toUpperCase()+") throws Exception {"+"\n");
		    cadena.append("\n");
		    cadena.append("        Object valorIdInsercion = null;"+"\n");
		    cadena.append("\n");
		    cadena.append("        PreparedStatement preparedStatement = null;"+"\n");
		    cadena.append("\n");
		    cadena.append("        try {"+"\n");
		    cadena.append("\n");
		    cadena.append("            preparedStatement = conn.prepareStatement(SQLINSERT);"+"\n");
		    cadena.append("\n");
		    cadena.append("            int i = 1;"+"\n");
		    cadena.append("            valorIdInsercion = getValorSecuencia(conn,SQLSECUENCIA_"+nombreEnBeanTabla.toUpperCase()+");"+"\n");
		    if(listaCamposPK.size()==1){
		    	String nombreEnBean = (String)((Map)listaCamposPK.get(0)).get("nombreEnBean");
				String tipoDatoLenguaje = (String)((Map)listaCamposPK.get(0)).get("tipoDatoLenguaje");
				cadena.append("            o"+nombreEnBeanTabla+"."+nombreEnBean+" = "+new ConversorDeClase().getCastObjeto(tipoDatoLenguaje, "valorIdInsercion")+";"+"\n");
		    }
		    else { // Si entra aca es porque la clave primaria no correponde a una secuencia
			    for (int i=0; i<listaCamposPK.size(); i++) {
					String nombreEnBean = (String)((Map)listaCamposPK.get(i)).get("nombreEnBean");
					cadena.append("            //o"+nombreEnBeanTabla+"."+nombreEnBean+" = valorIdInsercion;"+"\n");
			    }
			    cadena.append("            if(true) throw new Exception(\"No esta definida la insercion de "+nombreEnBeanTabla+" en la Base de Datos como secuencia\");"+"\n");			    
		    }
		    cadena.append("\n");		    
		    for (int i=0; i<listaCampos.size(); i++) {
				String nombreEnBean = (String)((Map)listaCampos.get(i)).get("nombreEnBean");
				String tipoDatoJDBCCadena = (String)((Map)listaCampos.get(i)).get("tipoDatoJDBCCadena");
		    
			    cadena.append("            {"+"\n");
			    cadena.append("                if(o"+nombreEnBeanTabla+"."+nombreEnBean+"!=null) preparedStatement.setObject(i++, o"+nombreEnBeanTabla+"."+nombreEnBean+","+tipoDatoJDBCCadena+");"+"\n");
			    cadena.append("                else preparedStatement.setNull(i++, "+tipoDatoJDBCCadena+");"+"\n");
			    cadena.append("            }"+"\n");
		    }
		    cadena.append("\n");
		    cadena.append("            int insertedRows = preparedStatement.executeUpdate();"+"\n");
		    cadena.append("\n");
		    cadena.append("            if (insertedRows == 0) {"+"\n");
		    cadena.append("                throw new SQLException(\"No pudo insertar el registro en la tabla '"+nombreEnBeanTabla+"'\");"+"\n");
		    cadena.append("            }"+"\n");
		    cadena.append("\n");
		    cadena.append("            if (insertedRows > 1) {"+"\n");
		    cadena.append("                throw new SQLException(\"Intento duplicar el nuevo registro en la tabla '"+nombreEnBeanTabla+"'\");"+"\n");
		    cadena.append("            }"+"\n");
		    cadena.append("\n");
		    cadena.append("        } catch(SQLException e) {"+"\n");
		    cadena.append("            throw new SQLException(e);"+"\n");
		    cadena.append("        } finally {"+"\n");
		    cadena.append("            if (preparedStatement != null) preparedStatement.close();"+"\n");
		    cadena.append("        }"+"\n");
		    cadena.append("        return valorIdInsercion;"+"\n");
		    cadena.append("    }"+"\n");
		    cadena.append("\n");
	    }	    
	    ////////// getValorSecuencia: //////////////////////////////////////////////////////////////////
	    if(!esVista) {
		    cadena.append("    private Object getValorSecuencia(Connection conn,String SQLSECUENCIA_"+nombreEnBeanTabla.toUpperCase()+") throws Exception {"+"\n");
		    cadena.append("        Object valorSecuencia = null;"+"\n");
		    cadena.append("\n");
		    cadena.append("        PreparedStatement preparedStatement = null;"+"\n");
		    cadena.append("        ResultSet resultSet = null;"+"\n");
		    cadena.append("\n");
		    cadena.append("        try {"+"\n");
		    cadena.append("\n");
		    cadena.append("            preparedStatement = conn.prepareStatement(SQLSECUENCIA_"+nombreEnBeanTabla.toUpperCase()+");"+"\n");
		    cadena.append("            resultSet = preparedStatement.executeQuery();"+"\n");
		    cadena.append("\n");
		    cadena.append("            if(resultSet.next()){"+"\n");
		    cadena.append("                valorSecuencia = resultSet.getObject(1);"+"\n");
		    cadena.append("            }"+"\n");
		    cadena.append("            else {"+"\n");
		    cadena.append("                throw new SQLException(\"No encontro el valor de la secuencia de '"+nombreEnBeanTabla+"'\");"+"\n");
		    cadena.append("            }"+"\n");
		    cadena.append("        } catch(SQLException e) {"+"\n");
		    cadena.append("            throw new SQLException(e);"+"\n");
		    cadena.append("        } finally {"+"\n");
		    cadena.append("            if (resultSet != null) resultSet.close();"+"\n");
		    cadena.append("            if (preparedStatement != null) preparedStatement.close();"+"\n");
		    cadena.append("        }"+"\n");
		    cadena.append("        return valorSecuencia;"+"\n");
		    cadena.append("    }"+"\n");
		    cadena.append("\n");	    
	    }	    
	    ////////// getUltimoIdInsertado: //////////////////////////////////////////////////////////////////
	    if(!esVista) {
		    cadena.append("    public Object getUltimoIdInsertado(Connection conn,String ULTIMOIDINSERTADO) throws Exception {"+"\n");
		    cadena.append("        Object ultimoIdInsertado = null;"+"\n");
		    cadena.append("        String SQLULTIMOIDINSERTADO = \"\";"+"\n");
		    cadena.append("        if(ULTIMOIDINSERTADO.equalsIgnoreCase(SecuenciasIncrementos.MAX_ID)) SQLULTIMOIDINSERTADO = \"SELECT \"+\"MAX(A."+ (String)((Map)listaCamposPK.get(0)).get("nombre") +")\"+\" FROM "+nombreTabla+" A \";"+"\n");
		    cadena.append("        else SQLULTIMOIDINSERTADO = \"SELECT \"+ULTIMOIDINSERTADO+\" FROM "+nombreTabla+" A \";"+"\n");
		    cadena.append("\n");
		    cadena.append("        PreparedStatement preparedStatement = null;"+"\n");
		    cadena.append("        ResultSet resultSet = null;"+"\n");
		    cadena.append("\n");
		    cadena.append("        try {"+"\n");
		    cadena.append("\n");
		    cadena.append("            preparedStatement = conn.prepareStatement(SQLULTIMOIDINSERTADO);"+"\n");
		    cadena.append("            resultSet = preparedStatement.executeQuery();"+"\n");
		    cadena.append("\n");
		    cadena.append("            if(resultSet.next()){"+"\n");
		    cadena.append("                ultimoIdInsertado = resultSet.getObject(1);"+"\n");
		    cadena.append("            }"+"\n");
		    cadena.append("            else {"+"\n");
		    cadena.append("                throw new SQLException(\"No encontro el identificador del ultimo registro ingresado en la tabla '"+nombreEnBeanTabla+"'\");"+"\n");
		    cadena.append("            }"+"\n");
		    cadena.append("        } catch(SQLException e) {"+"\n");
		    cadena.append("            throw new SQLException(e);"+"\n");
		    cadena.append("        } finally {"+"\n");
		    cadena.append("            if (resultSet != null) resultSet.close();"+"\n");
		    cadena.append("            if (preparedStatement != null) preparedStatement.close();"+"\n");
		    cadena.append("        }"+"\n");
		    cadena.append("        return ultimoIdInsertado;"+"\n");
		    cadena.append("    }"+"\n");
		    cadena.append("\n");			
	    }
	    cadena.append("}"+"\n");
	    
		return cadena;
	}

}
