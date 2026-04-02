package prettifier;

public class Logo {   
	public static void getLogo() {
		System.out.println("""
				·······································································
				: █████╗ ███╗   ██╗██╗   ██╗██╗    ██╗██╗  ██╗███████╗██████╗ ███████╗:
				:██╔══██╗████╗  ██║╚██╗ ██╔╝██║    ██║██║  ██║██╔════╝██╔══██╗██╔════╝:
				:███████║██╔██╗ ██║ ╚████╔╝ ██║ █╗ ██║███████║█████╗  ██████╔╝█████╗  :
				:██╔══██║██║╚██╗██║  ╚██╔╝  ██║███╗██║██╔══██║██╔══╝  ██╔══██╗██╔══╝  :
				:██║  ██║██║ ╚████║   ██║   ╚███╔███╔╝██║  ██║███████╗██║  ██║███████╗:
				:╚═╝  ╚═╝╚═╝  ╚═══╝   ╚═╝    ╚══╝╚══╝ ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝╚══════╝:
				:                                                                     :
				:    ██╗  ██╗ ██████╗ ██╗     ██╗██████╗  █████╗ ██╗   ██╗███████╗    :
				:    ██║  ██║██╔═══██╗██║     ██║██╔══██╗██╔══██╗╚██╗ ██╔╝██╔════╝    :
				:    ███████║██║   ██║██║     ██║██║  ██║███████║ ╚████╔╝ ███████╗    :
				:    ██╔══██║██║   ██║██║     ██║██║  ██║██╔══██║  ╚██╔╝  ╚════██║    :
				:    ██║  ██║╚██████╔╝███████╗██║██████╔╝██║  ██║   ██║   ███████║    :
				:    ╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚═╝╚═════╝ ╚═╝  ╚═╝   ╚═╝   ╚══════╝    :
				·······································································
				We are proud to present our (usefullest yet) text-pretty-maker (ver. 0.0.3)
				to bash, type "java -jar Prettifier.jar \033[1;33m-h \033[0m" for help
				"""
				);
		int min = 4; // the progress-bar-thingie below
		int max = 8;
		int random = (int)(Math.random() * (max-min +1)) + min;
		int total = random;
		try { for (int i = 0; i <= total; i++) {
			int percent = i * 100 / total;
			String bar = "[" + "#".repeat(i) + " ".repeat(total - i)  + "]";
			System.out.print("\r" + bar + " " + percent + "%");
			System.out.flush();
			Thread.sleep((long) (Math.random()*10));
			}
		} catch (InterruptedException e) { //not sure, how to interrupt, needs investigation
			System.out.println("Interrupted"); 
		}
		System.out.println("");
		
	}
	
	public static void getHelp() {			//with some "flair"
		
		String GREEN1 = "\033[38;5;22m";  // dark green
		String GREEN2 = "\033[38;5;34m";  // medium green
		String GREEN3 = "\033[38;5;46m";  // bright green
		String RESET = "\033[0m";

		
		System.out.println("Usage:\n"
				+ "run with 3 arguments like so:\n"
				+ "java -jar Prettifier.jar"+GREEN1+" <input>"+RESET+GREEN2+" <output>"+RESET+GREEN3+" <lookup>\n"+RESET
				+ "if no arguments provided, the defaults are as follows:\n"
				+ "$ java -jar Prettifier.jar "+GREEN1+"./input.txt "+RESET+GREEN2+"./output.txt "+RESET+GREEN3+"./airport-lookup.csv "+RESET
				+ "\nWorry not, we (the jolly gremlins) will do the rest");
	}



}
