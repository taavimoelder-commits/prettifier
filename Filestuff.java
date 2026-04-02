package prettifier;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filestuff {
									// order of these bastards matters
	public static void replace(String lookupPath, String inputPath) throws IllegalArgumentException, IOException {
		
		try (BufferedReader reader = new BufferedReader(new FileReader(lookupPath))) {
		String headerLine = reader.readLine(); // only the first row from file (header).
		String[] headers = headerLine.split(",");
		String[] headersRequired = {"name", "iso_country", "municipality", "icao_code", "iata_code", "coordinates"};
		
		for (String c : headersRequired) {  //check if header is valid
			boolean found = false;		
			for (String h : headers) {
			if (h.trim().equals(c)) {
			found = true;
			break;
			}
			}
		if (!found) {
		throw new IllegalArgumentException("Error: airport-lookup mahttps://marketplace.eclipse.org/marketplace-client-intro?mpc_install=4254856lformed");
		}
		
		}

		//looking for empty cells here
		String line; //declaring a variable (instead of doing it in the loop (also valid)
		while ((line = reader.readLine()) != null) {
		for (String cell : line.split(",", -1)) { //-1 to get the trailing empty one
		if (cell.trim().isEmpty()) {
		throw new IllegalArgumentException("Error: airport-lookup malformed");
		}
		
		}
		}
		}
		
		Map<String, String> icaoMap = new HashMap<>();
		Map<String, String> iataMap = new HashMap<>();
		Map<String, String> icaoCity = new HashMap<>();
		Map<String, String> iataCity = new HashMap<>();
		
		
		try (BufferedReader reader = new BufferedReader(
		new InputStreamReader(new FileInputStream(lookupPath), StandardCharsets.UTF_8))) {
	  // different reader because I'm rying to read the question marks here with this ^^
		String headerLine2 = reader.readLine();
		String[] headers2 = headerLine2.split(",");

		int icaoIndex = Arrays.asList(headers2).indexOf("icao_code");
		int iataIndex = Arrays.asList(headers2).indexOf("iata_code");
		int nameIndex = Arrays.asList(headers2).indexOf("name");
		int cityIndex = Arrays.asList(headers2).indexOf("municipality");

		String line;
		while ((line = reader.readLine()) != null) {
			String[] cols = line.split(",");
			String icaoText = cols[icaoIndex];
			String iataText = cols[iataIndex];
			String airpName = cols[nameIndex];
			String cityName = cols[cityIndex];

			icaoMap.put(icaoText, airpName);
			iataMap.put(iataText, airpName);
			icaoCity.put(icaoText, cityName);
			iataCity.put(iataText, cityName);
		}
		}
	
		

	    // inputfile comes to play, no need for BufferedReader
		String ugly = Files.readString(Paths.get(inputPath));
		String pretty  = "the nicer text";
						
		ugly = ugly.replaceAll("[\r\f\u000B]", "\n"); //java does not support \v (\u000B instead)
		ugly = ugly.replaceAll("\n\n\n+", "\n\n"); 	
		
		Pattern pattern = Pattern.compile("(\\*##[A-Z]{4}|\\*#[A-Z]{3}|##[A-Z]{4}|#[A-Z]{3})(?![A-Z])");										
		Matcher matcher = pattern.matcher(ugly);
		
		StringBuffer result = new StringBuffer(); // does not need closing

		while (matcher.find()) {
		String token = matcher.group();
	    String replacement = token;  // replaces with itself if no match found

	    if (token.startsWith("*##")) { //from longer to shorter matches (*## first instead of #)
	String code = token.substring(3);
		replacement = icaoCity.getOrDefault(code, token);
		} else if (token.startsWith("*#")) {
		String code = token.substring(2);
		replacement = iataCity.getOrDefault(code, token);

		} else if (token.startsWith("##")) {
		String code = token.substring(2);
		replacement = icaoMap.getOrDefault(code, token);

		} else if (token.startsWith("#")) {
		String code = token.substring(1);
		replacement = iataMap.getOrDefault(code, token);
		}
		
		matcher.appendReplacement(result, replacement);
		}

		matcher.appendTail(result);
		String nicer = result.toString();
		System.out.println(nicer);
		
		
		
		// year 		\d{4}-\d{2}-\d{2}		Matches 2025-01-20
		// time 		T\d{2}:\d{2}			Matches T10:30
		// timezone		(Z|[+-]\d{2}:\d{2}) 	Matches Z or +01:00
		// group 1 		(D|T12|T24)				D, T12, T24
		// group 2		((\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}  full iso datetime
		// group 3		(Z|[+-]\d{2}:\d{2}) 	Z or -01:00
		Pattern pattern1 = Pattern.compile("(D|T12|T24)\\((\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}(Z|[+-]\\d{2}:\\d{2}))\\)");
		Matcher matcher1 = pattern1.matcher(ugly);
		
		StringBuffer result1 = new StringBuffer();
		
		
		
	//		System.out.println(ugly);



//			if (!Files.exists(Paths.get(inputPath))) { System.out.println("Input not found"); return; }
//			if (!Files.exists(Paths.get(lookupPath))) { System.out.println("Airport lookup not found"); return; }
//			// Only create output file after Filestuff.replace completes successfully
			
	}
	
}
	
	
	
	

