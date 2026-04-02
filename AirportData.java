package prettifier;

import java.util.Map;


public record AirportData(
		    Map<String, String> icaoMap,
		    Map<String, String> iataMap,
		    Map<String, String> icaoCity,
		    Map<String, String> iataCity
		) {}

//totally not cool.