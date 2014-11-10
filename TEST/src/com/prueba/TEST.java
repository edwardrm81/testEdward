package com.prueba;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.core.MediaType;



import oma.xml.rest.netapi.payment._1.TransactionOperationStatus;
import sv.com.tigo.web.utils.Utils;

enum Apple {  
	  A, B, C, D, E 
	}

public class TEST {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			
			String o = Utils.getDepureDistictStringList("Ed4,Ed5,Ed2,Ed1,Ed2,Ed3,Ed2,Ed2,Ed4,Ed3,Ed1", ",");
			System.out.println("Salida2:"+o);
			
			/*
			boolean isBreak = false;
			for (int i = 0; i < 10; i++) {
				
				for (int j = 0; j < 5; j++) {
					
					if(i== 5 && j ==3) {
						System.out.println("******************************************i:"+i+"j:"+j);
						isBreak = true;
						if(isBreak) break;
					}
					System.out.println("i:"+i+"j:"+j);
					
				}
				if(isBreak) break;
				System.out.println("i:"+i);
				
			}
			*/
			
			/*
			Long numberNewNextDataExchange = System.currentTimeMillis();
			Long hola = 10000*(numberNewNextDataExchange % 10);
			System.out.println("hola:" + hola);
			
			Long hola2 = 12L % 12;
			System.out.println("hol2:" + hola2);
			*/
			
			/*
			LinkedHashMap<String,Integer> queueDataExchange = new LinkedHashMap<String,Integer>();
			Integer entero = queueDataExchange.get("Edw");
			
			System.out.println("entero:" + entero);
			
			queueDataExchange.put("Edw", 2);

			Integer entero2 = queueDataExchange.get("Edw");
			System.out.println("entero2:" + entero2);
			*/
			
			/*
			LinkedHashMap<String,Edward> stateDataExchanges = new LinkedHashMap<String,Edward>();
			
			Edward ed1 = new Edward();
			ed1.apellido = "Rod";
			ed1.nombre = "Ed1";
			
			stateDataExchanges.put("11", ed1);
			
			Edward ed2 = new Edward();
			ed2.apellido = "Mor";
			ed2.nombre = "Ed2";
			
			stateDataExchanges.put("22", null);
			
			
			System.out.println(stateDataExchanges);
			*/
			
			
			/*
			java.util.Random r = new java.util.Random();
			int sleepTimeMilliseconds = 60000 + r.nextInt(120000);  // result 1 and 2 minutes
			int sleepTimeMilliseconds2 = 60000 + r.nextInt(120000);  // result 1 and 2 minutes
			int i = 0;
			while(i<20) {
				sleepTimeMilliseconds = 60000 + r.nextInt(120000); 
				System.out.println("sleepTimeMilliseconds: " + sleepTimeMilliseconds);
				sleepTimeMilliseconds2 = 60000 + r.nextInt(120000); 
				System.out.println("sleepTimeMilliseconds2: " + sleepTimeMilliseconds2);				
				i++;
			}
			*/
			
			/*
			Float valueNew = 3.5F;
			
			if(valueNew.getClass().isAssignableFrom(Float.class)) {
				System.out.println("");
			}
			
			//String tmp = valueNew.getClass().isInstance(Float);
		
			System.out.println("");
			*/
			
			
			/*
			Calendar calMaturityDate = Calendar.getInstance();
			calMaturityDate.set(3000, 11, 1);
			
			Date fecha = calMaturityDate.getTime();
			System.out.println("FECHA:"+fecha);
			System.out.println("FIN");
			*/
			
			/*
			String [] hoursArray = "03".split(",");
			System.out.println("");
			*/
			
			
			/*
			String value = "-6789123456789.49";
			String numberFormatValue = "#,###,##0.00";
			
			DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
			decimalFormatSymbols.setDecimalSeparator(".".charAt(0));
			decimalFormatSymbols.setGroupingSeparator(",".charAt(0));
			
			java.text.NumberFormat numberFormat = new java.text.DecimalFormat(numberFormatValue, decimalFormatSymbols);
			//numberFormat.setMinimumIntegerDigits(1);
			
			
			
			String valReturn = numberFormat.format(new Double(value.toString())) + "%";
			Double d = new Double(value.toString());
			System.out.println(numberFormat);
			String tot = numberFormat.format(new Double(value.toString()));
			System.out.println(tot);
			*/
			
			/*
			String ed = "Edw";
			System.out.println(ed.charAt(0));
			
			
			Double doble = 23450999.45;
			Object value = doble;
			String exportThousandSeparator = ",";
			String exportDecimalSeparator = ".";
			
			//String moneyFormatValue = "#"+exportThousandSeparator+"###"+exportThousandSeparator+"###"+exportDecimalSeparator+"00";
			//String numberFormatValue = "#"+exportThousandSeparator+"###"+exportThousandSeparator+"###"+exportDecimalSeparator+"00";
			String numberFormatValue = "#,###,###.00";
			
			
			DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
			otherSymbols.setGroupingSeparator(',');
			otherSymbols.setDecimalSeparator('.');
			
			
			//java.text.NumberFormat moneyFormat = new java.text.DecimalFormat(moneyFormatValue);
			java.text.NumberFormat numberFormat = new java.text.DecimalFormat(numberFormatValue, otherSymbols);

			
			String valReturn = numberFormat.format(new Double(value.toString()));
			System.out.println("valReturn:" + valReturn);
			*/
			
			/*
			TimeZone timeZone = TimeZone.getDefault(); 
			int offsetMilliseconds = timeZone.getOffset( System.currentTimeMillis() );
			BigDecimal offsetHours = new BigDecimal(offsetMilliseconds).divide(new BigDecimal(3600000), 2, BigDecimal.ROUND_HALF_UP);
			
			String idGMT = "GMT";
			if(offsetHours.signum() != 0) {
				
				String hours = offsetHours.toBigInteger().abs().toString();
				if(offsetHours.signum()>0) {
					hours = "+" + hours;
				} else {
					hours = "-" + hours;
				}
				
				BigDecimal fraction = offsetHours.remainder(BigDecimal.ONE);
				BigDecimal minutes = fraction.multiply(new BigDecimal(60)).setScale(0, BigDecimal.ROUND_HALF_UP).abs();
				if(minutes.intValue()<10) {
					idGMT += hours + ":" + "0"+minutes;	
				} else {
					idGMT += hours + ":" + minutes;
				}
				  				
			}
			
			System.out.println("idGMT:"+idGMT);
			
			*/
			
			/*
			int[] formJobHoursArrayTemp = new int[3+1];
			
			
			int[] myIntArray = {1,2,3};
			
			for (int i = 0; i < myIntArray.length; i++) {
				System.out.println("myIntArray_["+i+"]"+myIntArray[i]);
			}
			*/
			
//			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		    SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss.SS");
//		    fmt.setTimeZone(TimeZone.getTimeZone("GMT-5:00"));
//		    System.out.println(fmt.format(timestamp));
//		    String[] str = TimeZone.getAvailableIDs();
//		    
//		    for (String cosa : str) {
//				//System.out.println("STR:"+cosa);
//			}
		    //TimeZone str2 = TimeZone.getTimeZone("GMT-4:00");
			
			
			//LinkedHashMap<String, String> jobUTCMap = new LinkedHashMap<String, String>();
			
			/*
			Calendar cal2 = Calendar.getInstance(TimeZone.getTimeZone("GMT-2"));
			
			System.out.println(cal2.getTime());
			System.out.println(cal2.getTimeZone());
			System.out.println(cal2.getTimeZone().getID());
			*/
			//String str[] =TimeZone.getAvailableIDs(Calendar.getInstance().getTimeZone().getRawOffset()).;
			
			//System.out.println("GMT:"+Calendar.getInstance(TimeZone.getTimeZone("GMT-2:00")).getTimeZone().getDisplayName());
			
			
			//System.out.println("ID:"+TimeZone.getDefault().getID());
			
			//System.out.println(new Date().getTimezoneOffset());
			//System.out.println(Calendar.getInstance().getTimeZone().getOfgetID().);
			
			
			/*
			List<String> list = new ArrayList<String>();
			
			float numero = -12;
			for (int i = 0; i<=48; i++) {
				
				BigDecimal bd = new BigDecimal(numero + (i*0.5)).setScale(2);
				
				if(bd.signum()==0) {
					list.add("GMT");
				} else {
					String gmt = "GMT"+ (bd.signum()>0? "+" : "") + bd;
					list.add(gmt);
				}
			}
			
			for(String gmt : list) {
				System.out.println(gmt);
			}
			*/
			
			/*
			for (int i = -12; i<0; i++) {
				String gmt = "GMT" + (i>0? "+"+i : i) +":00";
				String gmt30 = "GMT" + (i>0? "+"+i : i) +":30";
				jobUTCMap.put(gmt, gmt);
				jobUTCMap.put(gmt30, gmt30);
			}
			
			jobUTCMap.put("GMT", "GMT");
			
			for (int i = 0; i<12; i++) {
				String gmt = "GMT" + (i>0? "+"+i : i) +":00";
				String gmt30 = "GMT" + (i>0? "+"+i : i) +":30";
				jobUTCMap.put(gmt, gmt);
				jobUTCMap.put(gmt30, gmt30);
			}
			System.out.println(jobUTCMap);
			*/
			/*
		    TimeZone str2 = TimeZone.getTimeZone("GMT+12:35");
		    Calendar cal = Calendar.getInstance();
		    cal.setTimeZone(str2);
		    System.out.println("cal:"+cal.getTime());
		    
		    DateFormat gmtFormat = new SimpleDateFormat();
		    gmtFormat.setTimeZone(str2);
		    System.out.println("GMT Time: " + gmtFormat.format(new Date()));
		    */
		    
//		    System.out.println("DisplayName:"+str2.getDisplayName());
//		    System.out.println("ID:"+str2.getID());
//    		System.out.println("DisplayName:"+str2.getDisplayName(Locale.getDefault()));
//    		System.out.println("DisplayName:"+str2);
		
		} finally {
			System.out.println("Uno");
		}
		
		
		/*
		System.out.println(BigDecimal.class.getSimpleName());
		System.out.println(BigDecimal.class.getCanonicalName());
		
		
		BigDecimal bd = new BigDecimal("3.0");
		
		
		Object[] row = null;
		//int entero = ((BigDecimal)(obj.toString())).intValue();
		int entero = new Integer(row.toString());
		
		System.out.println("entero:" + entero);
		*/
		
		/*
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				
				if(j==2) {
					break;
				}
				System.out.println("j: " + j);
				
			}
			System.out.println("i: " + i);
		}
		*/
		
		/*
		System.out.println(new Edward() );
		
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		
		Integer indexOmitir = 1;
		list.remove(indexOmitir.intValue());
		
		for (String letra : list) {
			System.out.println(letra);
		}
		*/
		
		/*
		Cambie cambie = null;
		System.out.println("SimpleName: " + cambie.getClass().getSimpleName());
		*/
		/*
		java.util.Date date = new java.util.Date();
		java.util.Date inicio = new UtilDate().dateToDateStartDay(date);
		java.util.Date fin = new UtilDate().dateToDateEndDay(date);
		System.out.println("inicio: " + inicio);
		System.out.println("fin: " + fin);
		*/
		/*
		Calendar cal = Calendar.getInstance();
		cal.setTime(new java.util.Date());
		cal.add(Calendar.DAY_OF_YEAR, 3);
		java.util.Date endDate = cal.getTime();
		System.out.println("endDate: " + endDate);
		*/
		/*
		  Apple ap = Apple.A; 
		  System.out.println(ap.A.ordinal());
		    System.out.println("Here are all Apple constants"); 

		    Apple allapples[] = Apple.values(); 
		    for(Apple a : allapples) 
		      System.out.println(a.ordinal()); 
		 
		    System.out.println(); 
		    System.out.println("MIO: " + Apple.A);
		    ap = Apple.valueOf("C"); 
		    System.out.println("ap contains " + ap);
	    
		*/
		//System.out.println("KeyEvent.VK_AT:"+KeyEvent.VK_AT);
		/*
		try{
			File fileAttach = new File("d:/tmp2/1.wma");
			String contentType = new MimetypesFileTypeMap().getContentType(fileAttach);
			contentType = "pepe";
			Object obj = MediaType.valueOf(contentType);
		    MediaType mediaTypeAttachment = MediaType.valueOf(contentType);
		    System.out.println(mediaTypeAttachment);
		    System.out.println(mediaTypeAttachment.getType());
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		*/
		
		/*
		String xmlAmountTransactionHN =
		"<amountTransaction> "+
		"<senderAddress>tel:+50494929313</senderAddress>"+
		"<paymentAmount>"+
		"	<chargingInformation>"+
		"		<amount>3</amount>"+
		"		<currency>HNL</currency>"+
		"		<description>PAYMENT PRUEBA1</description>"+
		"	</chargingInformation>"+
		"</paymentAmount>"+
		"</amountTransaction>"+
		"<endUserId>"+"HOLA"+"</endUserId><endUserId>"+"MUNDO"+"</endUserId>"
		;
	
		List<String> endUserIdList = new XmlUtil().getLabelValueList(xmlAmountTransactionHN, "<endUserId>", "</endUserId>");
		for (String endUserId : endUserIdList) {
			System.out.println("endUserId***"+endUserId+"***ENDUSERID");	
		}
		*/
		/*
		String valor = "Charged";
		System.out.println("TransactionOperationStatus.fromValue(arg0): "+TransactionOperationStatus.fromValue(valor));
		
		System.out.println("TransactionOperationStatus.fromValue(arg0): "+TransactionOperationStatus.valueOf("CHARGED"));
		*/
		/*
		System.out.println("MediaType.APPLICATION_XML: "+MediaType.APPLICATION_XML);
		System.out.println("MediaType.TEXT_XML: "+MediaType.TEXT_XML);
		System.out.println("MediaType.TEXT_PLAIN: "+MediaType.TEXT_PLAIN);
		
		System.out.println("MediaType.APPLICATION_XML_TYPE: "+MediaType.APPLICATION_XML_TYPE);
		System.out.println("MediaType.TEXT_XML_TYPE: "+MediaType.TEXT_XML_TYPE);
		System.out.println("MediaType.TEXT_PLAIN_TYPE: "+MediaType.TEXT_PLAIN_TYPE);
		*/		
		/*
		CountryCode count = null; 
		if(count == CountryCode.BOLIVIA) {
			System.out.println("count: "+count);
		} else {
			System.out.println("count2: "+count);
		}
		*/
		
		/*
		String chargeDescription = ".";
		if(chargeDescription.length()>=2) {
			chargeDescription = chargeDescription.substring(0, chargeDescription.length()-2);
		}		
		
		System.out.println(chargeDescription);
		*/
		
		/*
		java.io.File temp_file = new java.io.File("D:/tmp/cerrarSession.gif");
		System.out.println("getName:"+temp_file.getName());
		
		String mimeType = new MimetypesFileTypeMap().getContentType(temp_file);
		
		System.out.println("mimeType: "+mimeType);		
		try {
			InputStream is = new FileInputStream(temp_file);
			//String mimeTypeInputStream = new MimetypesFileTypeMap().getContentType(is);
			String mimeTypeInputStream = new MimetypesFileTypeMap(is).toString();
			System.out.println("mimeTypeInputStream: " + mimeTypeInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/	
		
		/*
		String  xml =
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
			"<v1:Error xmlns:v1=\"http://www.tigo.com/Core/Common/Error/V1\">" +
			"	<v1:code>1</v1:code>" +
			"	<v1:reason>Error</v1:reason>" +
			"	<v1:description>No ha ingresado ningun numero telefonico.</v1:description>" +
			"</v1:Error>";
		
		
		String resultado = new TEST().getValorEtiqueta(xml, "<v1:code>", "</v1:code>");
		System.out.println("resultado: "+resultado);
		*/
		/*
		<?xml version="1.0" encoding="UTF-8"?>
		<v1:Error xmlns:v1="http://www.tigo.com/Core/Common/Error/V1">
			<v1:code>1</v1:code>
			<v1:reason>Error</v1:reason>
			<v1:description>No ha ingresado ningun numero telefonico.</v1:description>
		</v1:Error>
		*/
		//responseStr.lastIndexOf(str);
		//responseStr.lastIndexOf(str);
		
		//lo que deberia llegar:
		//
		//<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
		//<ns2:requestError xmlns:ns2="urn:oma:xml:rest:netapi:common:1">    
		//	<serviceException>        
		//		<messageId>SVC0001</messageId>        
		//		<text>A service error occurred. Error code is %1</text>        
		//		<variables>Country COLOMBIA not supported</variables>    
		//	</serviceException>
		//</ns2:requestError>
		
		
		//int entero = 999999999;
		//int entero =1000000000;
		/*
		for (int i = 0; i < 10; i++) {
			if(i == 5) return;
			System.out.println("i: "+i);
		}
		*/
		System.out.println("FIN");
		//String a = null;
		
		//System.out.println("A: "+TEST.getUnescapedString(a));
		/*
		String equipmentId = "tel:+2";
		
		
		if (!equipmentId.startsWith("tel:+")) {
			System.out.println("Si");
		}
		else {
			System.out.println("No");
		}
		*/
		
		/*
		CountryCode countryCode = null;
		
		if(countryCode == CountryCode.COLOMBIA ) {
			System.out.println("Equals2");
		}
		else {
			System.out.println("Differents2");
		}
		*/
		//String[] strArray = {};		
		//System.out.println((strArray!=null? Arrays.toString(strArray) : ""));
		
		// TODO Auto-generated method stub
		//String var1 = "HOLA %1";
		//var1 = var1.replace("%1", "MUNDO");
		//System.out.println(var1);
		/*
		String var1 = "A";
		String var2 = "B";
		var1 = var2;
		var1 = "C";
		System.out.println("var1:"+var1);
		System.out.println("var2:"+var2);
		*/
		
		
		/*
		Edward obj1 = new Edward();
		obj1.nombre = "Nom1";
		obj1.apellido = "Apellido1";
		obj1.country = CountryCode.COLOMBIA;
		
		new Cambie().cambie(obj1);
		
		System.out.println("Nombre: " + obj1.nombre);
		System.out.println("Apellido: " + obj1.apellido);
		System.out.println("Country: " + obj1.country);
		
		CountryCode[] country2 = {CountryCode.COLOMBIA};
		new Cambie().cambieCountry(country2);
		System.out.println("Country: " + country2[0]);
		*/
	}

	
	public static String getValorEtiqueta(String xml, String etiqueta1, String etiqueta2) {
		
		if(xml==null || etiqueta1 == null || etiqueta2 == null) return "";
			
		int index1 = xml.indexOf(etiqueta1);
		if(index1 >= 0) {
			index1 +=  etiqueta1.length();
		}
		
		int index2 = xml.indexOf(etiqueta2);
		
		if(index1 > -1 && index2 > -1) {
			return xml.substring(index1, index2);
		}
		else {
			return "";
		}
	}
	
	
	public static String getUnescapedString(final String str) {
		if(str==null) {
			return "";
		}
		
		StringBuilder unescapedString = new StringBuilder(str);

		replace("%20", " ", unescapedString);
		replace("%21", "!", unescapedString);
		replace("%22", "\"", unescapedString);
		replace("%23", "#", unescapedString);
		replace("%24", "$", unescapedString);
		replace("%25", "%", unescapedString);
		replace("%26", "&", unescapedString);
		replace("%27", "'", unescapedString);
		replace("%28", "(", unescapedString);
		replace("%29", ")", unescapedString);
		replace("%2A", "*", unescapedString);
		replace("%2B", "+", unescapedString);
		replace("%2C", ",", unescapedString);
		replace("%2D", "-", unescapedString);
		replace("%2E", ".", unescapedString);
		replace("%2F", "/", unescapedString);
		replace("%3A", ":", unescapedString);
		replace("%3B", ";", unescapedString);
		replace("%3C", "<", unescapedString);
		replace("%3D", "=", unescapedString);
		replace("%3E", ">", unescapedString);
		replace("%3F", "?", unescapedString);
		replace("%40", "@", unescapedString);
		replace("%5B", "[", unescapedString);
		replace("%5C", "\\", unescapedString);
		replace("%5D", "]", unescapedString);
		replace("%5E", "^", unescapedString);
		replace("%60", "`", unescapedString);
		replace("%7B", "{", unescapedString);
		replace("%7C", "|", unescapedString);
		replace("%7D", "}", unescapedString);
		replace("%7E", "~", unescapedString);
		replace("%E0", "à", unescapedString);
		replace("%E1", "á", unescapedString);
		replace("%E2", "â", unescapedString);
		replace("%E3", "ã", unescapedString);
		replace("%E4", "ä", unescapedString);
		replace("%E5", "å", unescapedString);
		replace("%E6", "æ", unescapedString);
		replace("%E7", "ç", unescapedString);
		replace("%E8", "è", unescapedString);
		replace("%E9", "é", unescapedString);
		replace("%EA", "ê", unescapedString);
		replace("%EB", "ë", unescapedString);
		replace("%EC", "ì", unescapedString);
		replace("%ED", "í", unescapedString);
		replace("%EE", "î", unescapedString);
		replace("%EF", "ï", unescapedString);
		replace("%F0", "ð", unescapedString);
		replace("%F1", "ñ", unescapedString);
		replace("%F2", "ò", unescapedString);
		replace("%F3", "ó", unescapedString);
		replace("%F4", "ô", unescapedString);
		replace("%F5", "õ", unescapedString);
		replace("%F6", "ö", unescapedString);
		replace("%F7", "÷", unescapedString);
		replace("%F8", "ø", unescapedString);
		replace("%F9", "ù", unescapedString);
		replace("%FA", "ú", unescapedString);
		replace("%FB", "û", unescapedString);
		replace("%FC", "ü", unescapedString);
		replace("%FD", "ý", unescapedString);
		replace("%FE", "þ", unescapedString);
		replace("%FF", "ÿ", unescapedString);

		return unescapedString.toString();
	}

	private static void replace(String target, String replacement,
			StringBuilder builder) {
		int indexOfTarget = -1;
		while ((indexOfTarget = builder.indexOf(target)) >= 0) {
			builder.replace(indexOfTarget, indexOfTarget + target.length(),
					replacement);
		}
	}
}
