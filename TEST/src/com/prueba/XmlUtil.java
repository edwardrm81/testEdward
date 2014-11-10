package com.prueba;

import java.util.ArrayList;
import java.util.List;

public class XmlUtil {
	
	public String geLabelValue(String xml, String label1, String label2) {
		
		if(xml==null || xml.trim().length()<1 || label1 == null || label1.trim().length()<1 || label2 == null || label2.trim().length()<1)  {
			return "";
		}
		int[] indexInit = {0};
		return this.getLabelValue(xml, label1, label2, indexInit);
	}
	
	public List<String> getLabelValueList(String xml, String label1, String label2) {
		
		List<String> labelValueList = new ArrayList<String>();
		
		if(xml==null || xml.trim().length()<1 || label1 == null || label1.trim().length()<1 || label2 == null || label2.trim().length()<1)  {
			return labelValueList;
		}
		
		String xmlLabelValue = "";
		int indexInit[] = {0};
		while(indexInit[0]>-1) {
			xmlLabelValue = this.getLabelValue(xml, label1, label2, indexInit);
			if(indexInit[0]>-1) {
				labelValueList.add(xmlLabelValue);
			}
		}
		
		return labelValueList;
	}
	
	private String getLabelValue(String xml, String label1, String label2, int[] indexInit) {		
		
		String value = "";
		int index1 = xml.indexOf(label1, indexInit[0]);
		if(index1 >= 0) {
			index1  +=  label1.length();
		}
		
		int index2 = xml.indexOf(label2, indexInit[0]);
		if(index1 > -1 && index2 > -1) {
			value = xml.substring(index1, index2);
			indexInit[0] = index2 + label2.length();
		} else {
			indexInit[0] = -1;
		}
		
		return value;
	}	
}
