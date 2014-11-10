package com.prueba;

public class Cambie {
	
	public void cambie(Edward edward) {
		edward.nombre = "Nombre2";
		edward.apellido = "Apellido2";
		edward.country = CountryCode.BOLIVIA;
	}
	
	public void cambieCountry(CountryCode[] country) {
		
		country[0] = CountryCode.GUATEMALA;
	}

}
