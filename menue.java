/*
 * Dieses kleine Java-Programm erzeugt ein sehr einfaches
 * Konsolen-Menü und dient dazu die Arbeitsweise eines RCS
 * zu erklären.
 * Autor(en): Michael Sauer
 * Datum:     03.01.2014
 * Datum:     28.11.2014 //fuer Veraenderung zu demonstrieren
 * Compile:   javac menue.java
 * Execute:   java menu
 */
// bla
import java.util.Scanner;

public class menue{
        public static void main(String[] args) {
        	final int PROGRAMMENDE=0;
        	final int MICHAEL_SAUER=1;
		      final int MIKE_SOUR=2;
	      	final int MATHIAS_W=3;
	      	final int BANENE=4;
      		final int BANANE=4;
      		final int APFEL=5;
      		final int BIRNE=6;
          	final int DITCH_JONES=7;
        	boolean isEnde=false;

        	String[] textfeld={"Ciao",
                                "Michael Sauer",
                                "Mike Sour",
				"Mathias W."};

        	while(!isEnde){
                	Scanner scanner = new Scanner(System.in);
                	int wahl = scanner.nextInt();
                	switch (wahl) {
                        	case PROGRAMMENDE:
                                	System.out.println(textfeld[0]);
                                	isEnde=true;
                        	break;
                        	case MICHAEL_SAUER:
                                	System.out.println("Michael Sauer");
                        	break;
                        	case MIKE_SOUR:
                                	System.out.println("Mike Sour");
                        	break;
				                  case MATHIAS_W:
					                         System.out.println("Mathias Wittling");
			                  	break;
				                  case BANANE:
					                         System.out.println("Mac ist zu umständlich!");
				                  break;
                      
				                  case DITCH_JONES:
					                        System.out.pritnln("This is Ditch Jones");
			                  	break;

				                  case APFEL:
				                    	System.out.println("Banane hat recht!!!");
			                  	break;
				                  case BIRNE:
					                    System.out.println("Ich bin eine Birne.");
                          break;

                        	default:
                                	System.out.println("Fehler: Kenne ich nicht!");
                        	}
                	}
        	}
	}

