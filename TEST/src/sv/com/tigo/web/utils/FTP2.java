package sv.com.tigo.web.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class FTP2 {

	public static boolean downloadFile(String server, String user, String pass, String localPath, String remotePath) {
		
		BufferedWriter bw = null;
		InputStream is = null;
		boolean retorno = false; 
		  try {
		         URL url = new URL("ftp://" + user + ":" + pass + "@" + server + remotePath + ";type=i");
		         URLConnection urlc = url.openConnection();
		         is = urlc.getInputStream();
		         bw = new BufferedWriter(new FileWriter(localPath));
		         int c;
		         while ((c = is.read()) != -1) {
	                  bw.write(c);
		         }
		         retorno = true;
		} catch (Exception ex) {
		         ex.printStackTrace();
		         System.out.println(ex.getMessage());
		         retorno = false;
		}
		finally {
			try {
		         if(is!=null) is.close();
		         if(bw!=null) bw.flush();
		         if(bw!=null) bw.close();				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	    return retorno; 
	 }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FTP2.downloadFile("192.168.73.172","ftp","prueba","D:/01392.jpg","/images/01392.jpg");
	}

}
