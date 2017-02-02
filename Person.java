/*
* Beschreibt eine Person
*/
public class Person {
  private String Name;
  private int Alter;
  private String Wohnort;

  //Basic Constructor + getter/setter
  public void Person(String n,int a,String w) {
    Name = n;
    Alter = a;
    Wohnort = w;
  }

  public String getName() {
    return Name;
  }

  public int getAlter() {
    return Alter;
  }

  public String getWohnort() {
    return Wohnort;
  }

  public void setName(String n) {
    Name = n;
  }

  public void setAlter(int a) {
    Alter = a;
  }

  public void setWohnort (String w) {
    Wohnort = w;
  }

}
