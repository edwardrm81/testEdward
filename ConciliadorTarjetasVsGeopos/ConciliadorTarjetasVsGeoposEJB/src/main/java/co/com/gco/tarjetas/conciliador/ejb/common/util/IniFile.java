package co.com.gco.tarjetas.conciliador.ejb.common.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Vector;

import co.com.gco.tarjetas.conciliador.ejb.common.constants.ConstantsCommons;


/**
 * Auxiliar class for file management
 * @author edward.rodriguez
 *
 */
public class IniFile {

    /**
     * file name for reading or to saving
     */
    private String inifile = "";

    /**
     * file OutputStream to read
     */
    private InputStream file;

    /**
     * Vector that contain the sections of the ini file
     */
    private Vector vectorSecciones = new Vector();

    /**
     * Default Constructor
     */
    public IniFile(String inifile) {
        this.inifile = inifile;
    }

    /**
     * Constructor with InputStream param
     * @param file input
     */
    public IniFile(InputStream file) {
        this.file = file;

    }

    
    /**
     * method for loading the file
     */
    public void loadIni() {
        vectorSecciones = new Vector();

        String l = "";

        try {
            vectorSecciones.addElement(new InitialSection(l));
            BufferedReader f = null;

            if(file != null){
                f = new BufferedReader(new InputStreamReader(file));
            } else{
                f = new BufferedReader(new InputStreamReader(new FileInputStream(inifile)));
            }

            do {

                l = f.readLine();

                if (l != null) {
                    l = l.trim();

                    if (!"".equals(l) &&!l.startsWith(";") &&!l.startsWith("#")) {

                        if (l.startsWith("[")) {
                            // seccionIni !
                            l = subStringDer(l, "[");
                            l = subStringIzq(l, "]");

                            InitialSection seccion = new InitialSection(l);

                            vectorSecciones.addElement(seccion);
                        } else {    
                            String b = subStringIzq(l, "=");
                            b = b.trim();
                            String e = subStringDer(l, "=");
                            e = e.trim();
                            e = getValorConTilde(e);
                            SectionInput es = new SectionInput(b, e);

                            ((InitialSection) vectorSecciones.lastElement()).vectorEntradas.addElement(es);
                        }
                    } else if (!"".equals(l.trim())) {
                        SectionInput es = new SectionInput(l, null);

                        ((InitialSection) vectorSecciones.lastElement()).vectorEntradas.addElement(es);
                    }
                }
            } while (l != null);

            f.close();
        } catch (IOException ioe) {
        	ConstantsCommons.log.error("IOException: " + ioe.getMessage(), ioe);
        }
    }

    /**
     * Method for getting the value E wiht sign
     * @param E
     * @return param with the sign 
     */
    public String getValorConTilde (String e){

        if (e != null){
          //a tildada
            e = e.replaceAll("\\\\u00E1", "\u00E1");
          //e tildada
            e = e.replaceAll("\\\\u00E9", "\u00E9");
          //i tildada
            e = e.replaceAll("\\\\u00ED", "\u00ED"); 
          //o tildada
            e = e.replaceAll("\\\\u00F3", "\u00F3"); 
          //u tildada
            e = e.replaceAll("\\\\u00FA", "\u00FA"); 
          //A tildada
            e = e.replaceAll("\\\\u00C1", "\u00C1"); 
          //E tildada
            e = e.replaceAll("\\\\u00C9", "\u00C9"); 
          //I tildada
            e = e.replaceAll("\\\\u00CD", "\u00CD");
          //O tildada
            e = e.replaceAll("\\\\u00D3", "\u00D3"); 
          //U tildada
            e = e.replaceAll("\\\\u00DA", "\u00DA"); 
          //n enie
            e = e.replaceAll("\\\\u00F1", "\u00F1"); 
          //N eNie mayuscula
            e = e.replaceAll("\\\\u00D1", "\u00D1");
          //?
            e = e.replaceAll("\\\\u003F", "\u003F");
          //¿
            e = e.replaceAll("\\\\u00BF", "\u00BF"); 
        }

        return e;
    }

    

    /**
     * Method load receiving a string param 
     * @param f
     */
    public void loadIni(String f) {
        inifile = f;
        loadIni();
    }

    /**
     * Save method
     */
    public void saveIni() {
        try {
            PrintStream f = new PrintStream(new FileOutputStream(inifile));

            for (int i = 0; i < vectorSecciones.size(); i++) {
                InitialSection seccion = (InitialSection) vectorSecciones.elementAt(i);

                if (!"".equals(seccion.titulo)) {
                    f.print("[" + seccion.titulo + "]" + (char) 13 + (char) 10);
                }

                for (int j = 0; j < seccion.vectorEntradas.size(); j++) {
                    SectionInput es = (SectionInput) seccion.vectorEntradas.elementAt(j);

                    if (es.valor != null) {
                        f.print(es.titulo + "=" + es.valor + (char) 13 + (char) 10);
                    } else {
                        f.print(es.titulo + (char) 13 + (char) 10);
                    }
                }

                f.print("" + (char) 13 + (char) 10);
            }

            f.close();
        } catch (IOException ioe) {
        	ConstantsCommons.log.error("IOException: " + ioe.getMessage(), ioe);
        }
    }

    /**
     * This metod call the saveIni() method but before set the inifile with the file input param
     * @param file
     */
    public void saveIni(String file) {
        inifile = file;
        saveIni();
    }

   
    /**
     * Method for getting the right side of sub string
     * @param s
     * @param w
     * @return the string right side 
     */
    private String subStringDer(String s, String w) {
        if (s.indexOf(w) != -1) {
            return s.substring(s.indexOf(w) + w.length());
        } else {
            return "";
        }
    }

  
    
    /**
     * Method for getting the left side of sub string
     * @param s
     * @param w
     * @return the string left side 
     */
    private String subStringIzq(String s, String w) {
        if (s.indexOf(w) != -1) {
            return s.substring(0, s.indexOf(w));
        } else {
            return "";
        }
    }


    /**
     * Method for checking if a section exist or not
     * @param tituloSeccion
     * @param titulo
     * @return if the section exist the return true else false
     */
    public boolean getBoolValue(String tituloSeccion, String titulo) {
        String e = getValue(tituloSeccion, titulo);
        return "TRUE".equals(e);
    }

    /**
     * Method for getting the  integer value from a section
     * @param tituloSeccion
     * @param titulo
     * @return the integer value
     */
    public int getIntValue(String tituloSeccion, String titulo) {
        String e = getValue(tituloSeccion, titulo);

        if ("".equals(e)) {
            e = "-1";
        }

        return Integer.valueOf(e);
    }

    /**
     * Method for getting the long value from a section
     * @param tituloSeccion
     * @param titulo
     * @return the long value
     */
    public long getLongValue(String tituloSeccion, String titulo) {
        String e = getValue(tituloSeccion, titulo);

        if ("".equals(e)) {
            e = "-1";
        }

        return (new Long(e)).longValue();
    }

   
    /**
     * Method for getting the messages for all sections
     * @return
     */
    public HashMap<String,HashMap<String,String>> getPropiedadesSecciones(){
        HashMap<String,HashMap<String,String>> mensajesSecciones = new HashMap<String,HashMap<String,String>>() ;
        for (int i = 0; i < vectorSecciones.size(); i++) {
            InitialSection seccion = (InitialSection) vectorSecciones.elementAt(i);
            HashMap<String,String> mensajes = new HashMap<String,String>();
            mensajesSecciones.put(seccion.titulo, mensajes);
            for (int j = 0; j < seccion.vectorEntradas.size(); j++) {
                    SectionInput es = (SectionInput) seccion.vectorEntradas.elementAt(j);
                    mensajes.put(es.titulo, es.valor);
            }
        }
        return mensajesSecciones;
    }

    /**
     * Method for getting the value from the section given
     * @param tituloSeccion
     * @param titulo
     * @return the value from the section
     */
    public String getValue(String tituloSeccion, String titulo) {
        for (int i = 0; i < vectorSecciones.size(); i++) {
            InitialSection seccion = (InitialSection) vectorSecciones.elementAt(i);

            if (seccion.titulo.equals(tituloSeccion)) {
                for (int j = 0; j < seccion.vectorEntradas.size(); j++) {
                    SectionInput es = (SectionInput) seccion.vectorEntradas.elementAt(j);

                    if (es.titulo.equals(titulo)) {
                        return es.valor;
                    }
                }

                return "";
            }
        }

        return "";
    }


    /**
     * Set a value for a section 
     * @param tituloSeccion
     * @param titulo
     * @param valor
     */
    public void setValue(String tituloSeccion, String titulo, boolean valor) {
        String tf = "FALSE";

        if (valor) {
            tf = "TRUE";
        }

        setValue(tituloSeccion, titulo, tf);
    }

    /**
     * set a value for the section
     * @param tituloSeccion
     * @param titulo
     * @param valor
     */
    public void setValue(String tituloSeccion, String titulo, int valor) {
        setValue(tituloSeccion, titulo, "" + valor);
    }

    /**
     * set a value for the section
     * @param tituloSeccion
     * @param titulo
     * @param valor
     */
    public void setValue(String tituloSeccion, String titulo, long valor) {
        setValue(tituloSeccion, titulo, "" + valor);
    }

    /**
     * set a value for the section
     * @param tituloSeccion
     * @param titulo
     * @param valor
     */
    public void setValue(String tituloSeccion, String titulo, String valor) {
        for (int i = 0; i < vectorSecciones.size(); i++) {
            InitialSection seccion = (InitialSection) vectorSecciones.elementAt(i);

            if (seccion.titulo.equals(tituloSeccion)) {
                for (int j = 0; j < seccion.vectorEntradas.size(); j++) {
                    SectionInput es = (SectionInput) seccion.vectorEntradas.elementAt(j);

                    if (es.titulo.equals(titulo)) {
                        es.valor = valor;

                        return;
                    }
                }

                SectionInput es = new SectionInput(titulo, valor);

                seccion.vectorEntradas.addElement(es);

                return;
            }
        }

        InitialSection seccion = new InitialSection(tituloSeccion);
        SectionInput es = new SectionInput(titulo, valor);

        seccion.vectorEntradas.addElement(es);
        vectorSecciones.addElement(seccion);
    }

   
    /**
     * Private class for saving the inputs
     * @author edward.rodriguez@ingeneo.com.co
     *
     */
    private class SectionInput {
        String titulo = "";
        String valor = "";

   
        /**
         * section input
         * @param t
         * @param v
         */
        public SectionInput(String t, String v) {
            titulo = t;
            valor = v;
            try {
                valor = valor.replaceAll("\\\\", "\\");
            } catch (Exception e) {
            	ConstantsCommons.log.error("Exception: " + e.getMessage());
            }
        }
    }


   
    /**
     * private class for saving the sections
     * @author edward.rodriguez@ingeneo.com.co
     *
     */
    private class InitialSection {
        String titulo = "";
        Vector vectorEntradas = new Vector();

        /**
         * section initial
         * @param t
         */
        public InitialSection(String t) {
            titulo = t;
        }
    }
}
