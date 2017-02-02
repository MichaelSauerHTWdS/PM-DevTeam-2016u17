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
            final int PROGRAMMENDE=0;
            final int MICHAEL_SAUER =1;
            final int MIKE_SOUR=2;
            boolean isEnde=false;

            String[] textfeld={"Ciao",
                                "Michael Sauer",
                                "Mike Sour"};

            while(!isEnde){
                    System.out.println("Test");
                    Scanner scanner = new Scanner(System.in);
                    int wahl = scanner.nextInt();
                    switch (wahl) {
                            case PROGRAMMENDE:
                                    System.out.println(textfeld[0]);
                                    isEnde=true;
                            break;
                            case MICHAEL_SAUER:
                                    System.out.println(textfeld[1]);
                            break;
                            case MIKE_SOUR:
                                    System.out.println(textfeld[2]);
                            break;
                            default:
                                    System.out.println("Fehler: Kenne ich nicht!");
                            }
                    }
            }
    }

