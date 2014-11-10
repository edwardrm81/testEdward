package sv.com.tigo.web.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Utils {
	
	public static String getDepureDistictStringList(String listString, String delimiterList) {
		
		String stringListReturn = ""; 
		
		String[] listStringArray = listString.split(delimiterList);
		
		Map<String,String> map = new LinkedHashMap<String, String>();
		if(listStringArray.length>0) {
			for (String str : listStringArray) {
				map.put(str, str);
			}
		}
		
		StringBuilder stringBlist = new StringBuilder("");
		for (Map.Entry<String, String> entry : map.entrySet())
		{
			stringBlist.append(entry.getKey()+delimiterList); 
		}
		
		if(stringBlist.length()>0) {
			stringListReturn = stringBlist.substring(0, stringBlist.length()-1);
		}
		
		return stringListReturn;
	}

}
