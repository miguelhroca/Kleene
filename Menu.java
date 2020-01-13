import java.util.Scanner;

public class Menu {
  public void promptMenu() {
    String cont = "s";
    
    Kleene k = new Kleene();
    Automata a = new Automata();
    Scanner sc = new Scanner(System.in);
    int option = 1;
    String output = "";

    System.out.println("\n¿Qué matriz desea convertir?:");
    System.out.println(" 0. Automata de ejemplo 0 (Extraido de Wikipedia)");
    System.out.println(" 1. Automata de ejemplo 1");
    System.out.println(" 2. Automata de ejemplo 2");
    System.out.println(" 3. Otro (Introducir Automata)");
    System.out.print("\n>> ");

    option = sc.nextInt(); 
    switch(option) {
      case 0:
        output = k.transform(a.getExample0(), a.getExample0f());
        break;
      case 1:
        output = k.transform(a.getExample1(), a.getExample1f());
        break;
      case 2:
        output = k.transform(a.getExample2(), a.getExample2f());
        break;
      case 3:
        output = k.transform(a.inputAutomata(), a.getExample2f());
        break;
    }
    
    System.out.println("\n\nExpresión Regular: " + output);
    System.out.println("\n\nExpresión Regular Simplificada: " + k.simplify(output));

    System.out.print("\n¿Desea continuar? (s/n): ");
    cont = sc.next();
    
    if (cont.equals("s"))
      this.promptMenu();
  }
}