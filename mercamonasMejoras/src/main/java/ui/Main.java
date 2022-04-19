package ui;

import common.Common;
import constantes.Constantes;
import servicios.ficheros.ServiciosFicheros;
import ui.admin.UiMenuAdmin;
import ui.client.UiMenuClient;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Common common = new Common();

    UiMenuAdmin menuSwitchAdmin = new UiMenuAdmin();
    UiMenuClient menuSwitchClient = new UiMenuClient();

    System.out.println(Constantes.BIENVENIDA);
    int optionUser;

    do {
      System.out.println(Constantes.MENU_ELEGIR_USUARIO);
      optionUser = common.giveInt();

      switch (optionUser) {
        case 1:
          menuSwitchAdmin.menuSwitchAdmin(sc);
          break;
        case 2:
          menuSwitchClient.menuSwitchClient(sc);
          break;
        case 0:
          System.out.println(Constantes.HASTA_LA_PROXIMA);
          break;
        default:
          System.out.println(Constantes.ERROR_POR_FAVOR_TRY_AGAIN);
          break;
      }
    } while (optionUser != 0);
  }

}
