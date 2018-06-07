package co.com.gco.tarjetas.conciliador.ejb.common.util;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;

import co.com.gco.tarjetas.conciliador.ejb.common.constants.ConstantsCommons;
import co.com.gco.tarjetas.conciliador.ejb.common.constants.EnumsCommons;
import co.com.gco.tarjetas.conciliador.ejb.common.constants.MessageCodesI18N;


/**
 * Class for getting information from a properties load 
 * @author edward.rodriguez
 *
 */
public class PropertiesLoad {
    /**
     * Properties location
     */
    public static String PROPERTIES_FILE_LOCATION = EnumsCommons.PROPERTIES_FILE_LOCATION.getValue();
    
    /**
     * Properties message section
     */
    public static String PROPERTIES_FILE_SECTION_NAME = EnumsCommons.MESSAGES_SECTION_PROPERTIES.getValue();

     /**
     * Propeties by filter
     */
    private static HashMap<String,HashMap<String,HashMap<String,String>>> propertiesTypes;
    
    
    /**
     * get all sections's properties
     */
    static{
        propertiesTypes = new HashMap<String,HashMap<String,HashMap<String,String>>>();
    
    
    }
    
    
    /**
     * Method for get  properties by type
     * @param type
     * @return
     * @throws FileNotFoundException
     */
    private static synchronized HashMap<String,HashMap<String,String>>  loadPropertieType(String type) throws FileNotFoundException{
    	IniFile ini = new IniFile(PropertiesLoad.class.getClassLoader().getResourceAsStream(type));
        ini.loadIni();
        HashMap<String,HashMap<String,String>>  propertiesType = ini.getPropiedadesSecciones();
        propertiesTypes.put(type, propertiesType);
        return propertiesType;
    }
    
    
    /**
     * Method for getting the properties by section.
     * @param type 
     * @param sectionName 
     * @return Section's properties Object
     */
    public static HashMap<String,String> getSectionProperties(String type,String sectionName) throws FileNotFoundException{
    
        /**
         * getting the section
         */
        HashMap<String,HashMap<String,String>> sectionsType = propertiesTypes.get(type);
        if(sectionsType == null){
            sectionsType = loadPropertieType(type);
        }
    
        /**
         * getting properties by section
         */
        HashMap<String,String> properties = sectionsType.get(sectionName);
        if(properties==null){
            throw new RuntimeException("The properties are not found in the section "+sectionName);
        }
    
        fix(properties);
    
        return properties;
    }
    
    
    /**
     * Method for solving accent's mark troubles
     * @param properties
     */
    public static void fix(HashMap<String,String> properties){
        if(properties == null){
            Iterator<String> ite = properties.keySet().iterator();
            while(ite.hasNext()){
                String key = ite.next();
                String msg = properties.get(key);
                try {
                    msg = msg.replaceAll("\\\\", "\\");
                } catch (Exception e) {
                	ConstantsCommons.log.error("Exception: " + e.getMessage(), e);
                    break;
                }
                properties.put(key, msg);
            }
        }
    }
    
    
    
    
    /**
     * Method for getting a specific section's property 
     * @param language : file location
     * @param sectionName section name
     * @param MessageCodesI18N propertieName
     * @return : the property value
     */
    private static String getSectionPropertie(String language, String sectionName, MessageCodesI18N propertieName){
        String type = PROPERTIES_FILE_LOCATION;
//        try {
        	//ResourceBundle rb = ResourceBundle.getBundle("co.com.gco.tarjetas.conciliador.ejb.common.i18n.messages");
            type = type.replace("%", language);
            HashMap<String, String> properties = null;
            try {
            	properties = getSectionProperties(type, sectionName);
            } catch(Exception e) {
            	properties = null;
            }
            
            if (properties != null) {
            	String msg = properties.get(propertieName.getValue());
                if (msg == null) {
                    return propertieName.getValue()+" not found in the section : " + sectionName;
                } else {
                    return msg;
                }
            } else {
            	/* TODO  PENDIENTE : esto antes estaba retornando la excepcion en lugar de retornar el nombre de la propiedad  */
            	return propertieName.name();
                //throw new IllegalArgumentException("Messages are not found in the section:" + sectionName);
            }
//        } catch (FileNotFoundException ex) {
//            throw new RuntimeException("File location not found  : "+type,ex);
//        }
    
    }
    
    /**
     * Method for getting a specific section's property with params
     * @param language : file location
     * @param sectionName section name 
     * @param MessageCodesI18N propertieName
     * @param Params vector of params
     * @return : the property value
     */
    public static String getSectionPropertieParam(String language, MessageCodesI18N propertieName, String... params){
        String property  = getSectionPropertie(language,propertieName);
        return String.format(property, params);
    }
    
    /**
     * Method for getting a specific section's property with params
     * @param MessageCodesI18N propertieName
     * @param Params vector of params
     * @return : the property value
     */
    public static String getSectionPropertieParam(MessageCodesI18N propertieName, String... params){
        String property  = getSectionPropertie(EnumsCommons.LANGUAGE_DEFAULT.getValue(),propertieName);
        return String.format(property, params);
    }
    
    /**
     * Method for getting a specific section's property 
     * @param String language
     * @param MessageCodesI18N propertieName
     * @return : the property value
     */
    public static String getSectionPropertie(String language, MessageCodesI18N propertieName){
        return getSectionPropertie(language, EnumsCommons.MESSAGES_SECTION_PROPERTIES.getValue(), propertieName);
    }
    
    /**
     * Method for getting a specific section's property 
     * @param @param MessageCodesI18N propertieName
     * @return : the property value
     */
    public static String getSectionPropertie(MessageCodesI18N propertieName){
        return getSectionPropertie(EnumsCommons.LANGUAGE_DEFAULT.getValue(), EnumsCommons.MESSAGES_SECTION_PROPERTIES.getValue(), propertieName);
    }
    

}

