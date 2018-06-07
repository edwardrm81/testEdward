/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edw.modelo.logica.acciones.login;

import com.edw.gen.apigen.ConversorTipoJavaATipoJDBC;
import com.edw.gen.apigen.ExcepcionNegocio;
import com.edw.gen.apigen.Filtro;
import com.edw.gen.apigen.FiltroGrupo;
import com.edw.gen.apigen.OrderBy;
import com.edw.gen.basedatos.Conexion;
import com.edw.gen.basedatos.ExecuteNoTransaccional;
import com.edw.modelo.basedatos.generados.beans.Usuario;
import com.edw.modelo.basedatos.generados.daos.DAOGrupo;
import com.edw.modelo.basedatos.generados.daos.DAOUsuario;
import com.edw.modelo.basedatos.personalizados.beansext.UsuarioExt;
import com.edw.modelo.basedatos.personalizados.daosext.DAOUsuarioExt;



import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Edward
 */
public class AccionValidarUsuario extends ExecuteNoTransaccional {
	
    private Integer usuario;
    private Integer clave;


    public AccionValidarUsuario(Integer usuario,Integer clave) {
        this.usuario = usuario;
        this.clave= clave;
    }
    
    public Map execute(Connection conn) throws Exception{
    	
        if(usuario==null) throw new ExcepcionNegocio("No ha ingresado el usuario");
        if(clave==null) throw new ExcepcionNegocio("No ha ingresado la clave");
        
        List listaFiltros = new ArrayList();
        
        listaFiltros.add(new Filtro("A.usuario", usuario));
        listaFiltros.add(new Filtro("A.clave", clave,Filtro.OPERADOR_IGUAL));
        
        List listaUsuario = new DAOUsuarioExt().select(conn, listaFiltros);
        if(listaUsuario.size()==0) throw new ExcepcionNegocio("No encontró el usuario");
        if(listaUsuario.size()>1) throw new ExcepcionNegocio("Error, encontró varios usuarios");
        
        //Usuario oUsuario = (Usuario)listaUsuario.get(0);        
        UsuarioExt oUsuarioExt = (UsuarioExt)listaUsuario.get(0);

        Map oMap = new HashMap();
        oMap.put("usuario", oUsuarioExt);
        return oMap;
    }

    /*
    public Map execute() throws Exception{
        Connection conn = null;
        //boolean commited = false;
        try{
            conn = new Conexion().getConexion();
            //conn.setAutoCommit(false);
            Map oMap = execute(conn);
            //conn.commit();
            //commited = true;
            return oMap;
        }
        catch (ExcepcionNegocio e) {
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
	*/



}
