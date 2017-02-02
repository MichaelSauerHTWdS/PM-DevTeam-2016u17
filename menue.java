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

import java.util.Scanner;

public class menue{
        public static void main(String[] args) {
        	final int   PROGRAMMENDE    =0;
        	final int   MICHAEL_SAUER   =1;
            final int   MIKE_SOUR       =2;
            final int   MATHIAS_W       =3;
            final int   BANANE          =4;
            final int   APFEL           =5;
	    final int 	MARC		=6;
		
		final int NAME_DER_COOLEN_TYPEN = 19032;
        	boolean isEnde=false;

        	String[] textfeld={"Ciao",
                                "Michael Sauer",
                                "Mike Sour",
                                "Mathias W."};

        	while(!isEnde){
                	Scanner scanner = new Scanner(System.in);
			System.out.println("Bitte geben sie eine Zahl ein");
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
							case NAME_DER_COOLEN_TYPEN:
								System.out.println("Klaus,Andreas,Dennis");
							break;
                            
                            case MATHIAS_W:
                                System.out.println("Mathias Wittling");
				break;
                            
				case BANANE:
					System.out.println("Mac ist zu umständlich!");
                            
				break;
                            
				case APFEL:
					System.out.println("Banane hat recht!!!");
                            
				break;
				case MARC:
					System.out.println("Hier ist Marc");
                        	default:
                                	System.out.println("Fehler: Kenne ich nicht!");
                            
                            break;
                        	}
                	}
        	}
	}

