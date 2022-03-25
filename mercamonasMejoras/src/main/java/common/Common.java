package common;

import constantes.Constantes;
import java.util.Scanner;

public class Common {

  public int giveInt() {
    Scanner sc = new Scanner(System.in);
    boolean ok = false;
    int i = 0;
    do {
      try {
        i = Integer.parseInt(sc.nextLine());
        ok = true;
      } catch (NumberFormatException e) {
        System.out.println(Constantes.ERROR);
      }
    } while (!ok);
    return i;
  }

  public double giveDouble() {
    Scanner sc = new Scanner(System.in);
    boolean ok = false;
    double i = 0;
    do {
      try {
        i = Double.parseDouble(sc.nextLine());
        ok = true;
      } catch (NumberFormatException e) {
        System.out.println(Constantes.ERROR);
      }
    } while (!ok);
    return i;
  }
}
