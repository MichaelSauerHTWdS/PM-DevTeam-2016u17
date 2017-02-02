import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Einfache Menü-class
 */
public class Menue
{
  protected PersonenLager personenLager;
  protected Scanner scanner = new Scanner(System.in);

  public static final int NEW = 1;
  public static final int ADDPERSON = 2;
  public static final int REMOVEPERSON = 3;



  /**
   * Standard-Konstruktor
   */
  Menue() {
    personenLager = null;
    scanner.useDelimiter("\n");
  }


  /**
   * Start input loop
   */
  public void start() {
    int selector = -1;
    do {
      try {
        selector = getSelector();
        execute(selector);
      } catch (IllegalArgumentException e) {
        System.out.print("Fehler: " + e + "\n");
      } catch (InputMismatchException e) {
        scanner.next();
        System.out.print("Fehler: " + e + "\n");
      } catch (Exception e) {
        System.out.print("Fehler: " + e + "\n");
        e.printStackTrace(System.out);
      }
    } while (selector != END);
  }

  public static void main(String[] args) {
    new ArticleDialog().start();
  }

  public PersonenLager getPersonenLager() {
    return personenLager;
  }

  protected String getSelectorInfo() {
    return NEW +        ": Personenlager erstellen\n" +
           ADD +        ": Person hinzufuegen\n" +
           DELETE +     ": Person loeschen\n";
  }

  /**
   * Display help text
   */
  private int getSelector() {
    System.out.print(  getSelectorInfo() +
                       END +          ": beenden\n" +
                       "-> ");
    if (!scanner.hasNextInt()) throw new InputMismatchException("Expected Int value");
    return scanner.nextInt();
  }


  protected void executeRemove() {

  }

  protected void executeAdd() {

  }

  protected void executeNew() {

  }

  /**
   * Interact with article based on user input
   *
   * @param selector Operation to execute
   */
  public boolean execute(int selector) {
    switch (selector) {
      case ADD:
        executeAdd();
        break;
      case REMOVE:
        executeRemove();
        break;
      case NEW:
        executeNew();
      case END:
        break;
    }
    if (selector <= 7) {
      System.out.print("\n");
      return true;
    }
    return false;
  }
}
