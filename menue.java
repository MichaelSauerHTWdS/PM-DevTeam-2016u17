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

    public static void menu(){
        System.out.println("Wähle einen Menuepunkt aus");
        System.out.println("[1] Michael Sauer ");
        System.out.println("[2] Mike Sour");
        System.out.println("[0] Ciao");
        
    }
    
    
    public static void main(String[] args) {
        	final int PROGRAMMENDE=0;
        	final int MICHAEL_SAUER=1;
		final int MIKE_SOUR=2;
        	boolean isEnde=false;

        	String[] textfeld={"Ciao",
                                "Michael Sauer",
                                "Mike Sour"};

                
                menu();
        	while(!isEnde){
                	Scanner scanner = new Scanner(System.in);
                	int wahl = scanner.nextInt();
                	menu();
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
                        	default:
                                	System.out.println("Fehler: Kenne ich nicht!");
                        	}
                	}
        	}
	}

