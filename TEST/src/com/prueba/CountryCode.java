package com.prueba;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum CountryCode {
	//Was required to adjust to 3 digits:
	COLOMBIA("057"),//COLOMBIA("57"), 
	GUATEMALA("502"),//GUATEMALA("502"),
	EL_SALVADOR("503"),//EL_SALVADOR("503"),
	HONDURAS("504"),//HONDURAS("504"),
	BOLIVIA("591"),//BOLIVIA("591"),
	PARAGUAY("595"),//PARAGUAY("595"),
	NONE("");//NONE("");

	private String code;

	private CountryCode(final String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	private static final Map<String, CountryCode> lookup = new HashMap<String, CountryCode>();

	static {
		for (CountryCode s : EnumSet.allOf(CountryCode.class))
			lookup.put(s.getCode(), s);
	}

	public static CountryCode get(String code) {
		if (lookup.containsKey(code)) {
			return lookup.get(code);
		}
		
		return NONE;
	}
	
	public static CountryCode getCountryCode(final String senderAddress) {
		
		if(senderAddress!=null && senderAddress.trim().length()>=8) {
			return CountryCode.get(senderAddress.substring(5, 8));
		} else {
			return CountryCode.NONE;
		}
	}

}
