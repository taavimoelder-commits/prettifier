/**
 * 
 */
package prettifier;
import java.io.IOException;
import java.nio.file.*;
/**
 * 
 */
public class prettifier {
    public static void usage() {
        System.out.println("itinerary usage:");
        System.out.println("$ java Prettifier.java ./input.txt ./output.txt ./airport-lookup.csv");
    }
	/**
	 * @param args
	 * @throws IOException 
	 */
    
    
    
	public static void main(String[] args) throws IOException {
		
		Logo.getLogo(); // prints the logo of our company
		
		// file locations:
		String inputPath = "./input.txt";			//default values
		String outputPath = "./output.txt";
		String lookupPath = "./airport-lookup.csv";
		
		if (args.length == 0) System.out.println("Files at default location used");
		if (args.length > 0) inputPath = args[0];
		if (args.length > 1) outputPath = args[1];
		if (args.length > 2) lookupPath = args[2];
		
		for (String arg : args) {					//-h flag
			if (arg.equals("-h")) {
				Logo.getHelp();
				return; 							//exits main
			}
		}
		       String PINK="\033[38;5;213m";
		       String RESET="\033[0m";
		boolean filesExist = true;
		if(Files.exists(Paths.get(inputPath))) {				// checking for existence of files
			System.out.println("\ninput at: " + Paths.get(inputPath).toAbsolutePath().normalize());
			} else {													//full path 
				System.out.println("inputfile not found");
				filesExist = false;
			}
		
		Path outputFilePath = Paths.get(outputPath);

		try {
		    if (!Files.exists(outputFilePath)) {			// no outputfile
		        Files.createFile(outputFilePath);			//creates an outputfile if there is none
		        System.out.println("Outputfile created: " + Paths.get(outputPath).toAbsolutePath().normalize());
		    } else {
		        System.out.println("Output at: " + Paths.get(outputPath).toAbsolutePath().normalize());
		    }
		} catch (IOException e) {
		    System.out.println("Error handling output file"); //some generic error message
		    e.printStackTrace();
		}
		if(Files.exists(Paths.get(lookupPath))) {
			System.out.println("airport-lookup at: " + Paths.get(lookupPath).toAbsolutePath().normalize());
			} else {
				System.out.println("airport-lookup file (the csv) not found");
				filesExist = false;
			}
		if (!filesExist) {
			System.out.println("Missing file input and/or airport-lookup file(s) \nMaking a " + PINK + "pretty" + RESET + " exit");
			return;
		}
		// ^^up there is just some messy text about how to check if all required files are present.
		
		
		Filestuff.replace(lookupPath, inputPath);  // pass the path to another class
		
	}
	
		

}


