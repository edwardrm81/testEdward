/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.tigo.web.utils;


import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.File;

/**
 * @author kodehelp
 *
 */
public class ImageFTP {
	
    private static String SFTPHOST = "192.168.19.203"; //"192.168.73.172";
    private static String SFTPPORT = "22"; //"1021";
    private static String SFTPOPENCHANNEL = "sftp"; //"ftp";
    private static String SFTPUSER = "ghernandez"; //"prueba";
    private static String SFTPPASS = "goti2k12"; //"tigo";
    private static String SFTPWORKINGDIR = "/soasuit/Oracle/Middleware/home_DEV_11gR1/user_projects/domains/SOA_DEV_domain/TigoMaps/images"+"/"; //"/images"+"/";
    private static String SFTPPATHFINAL = "/soasuit/Oracle/Middleware/home_DEV_11gR1/user_projects/domains/SOA_DEV_domain/TigoMaps/images" + "/" + "tempo_image_ftp";
    
    public boolean getImage(String nameImage) {
        boolean state = false;
        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;
        try {
            System.out.println("1");
            JSch jsch = new JSch();
            System.out.println("2");
            Integer sftpPortInt = new Integer(SFTPPORT);
            System.out.println("3");
            session = jsch.getSession(SFTPUSER, SFTPHOST, sftpPortInt);
            System.out.println("4");
            session.setPassword(SFTPPASS);
            System.out.println("5");
            java.util.Properties config = new java.util.Properties();
            System.out.println("6");
            config.put("StrictHostKeyChecking", "no");
            System.out.println("7");
            session.setConfig(config);
            System.out.println("8");
            session.connect();
            System.out.println("9");
            channel = session.openChannel(SFTPOPENCHANNEL);
            System.out.println("10");
            channel.connect();
            System.out.println("11");
            channelSftp = (ChannelSftp) channel;
            System.out.println("12");
            channelSftp.cd(SFTPWORKINGDIR);
            System.out.println("13");
            //Si no existe el directorio temporal, crearlo
            File fileDestino = new File("/soasuit/Oracle/Middleware/home_DEV_11gR1/user_projects/domains/SOA_DEV_domain/TigoMaps/images" + "/" + "tempo_image_ftp");
            System.out.println("14");
            if(!fileDestino.exists()) {
                System.out.println("15");
                fileDestino.mkdirs();
                System.out.println("16");
            }
            System.out.println("17");
            //Copia la imagen del FTP y la copia en el directorio temporal local que esta en la carpeta de imagenes
            channelSftp.get(nameImage,SFTPPATHFINAL + "/" + nameImage);
            System.out.println("18");
            channelSftp.exit();
            System.out.println("19");
            session.disconnect();
            System.out.println("20");
            state = true;

        } catch (Exception e) {
            System.out.println("21");
            System.out.println("Exception : " + e.getMessage());
        } catch (Throwable t) {
            System.out.println("21.5");
            System.out.println("Exception : " + t.getMessage());
        }
        System.out.println("22");
        return state;
    }
    
    public static void main(String[] args) {
    	try {
    		String nameImage = "01392.jpg";
    		new ImageFTP().getImage(nameImage);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    
    
}
