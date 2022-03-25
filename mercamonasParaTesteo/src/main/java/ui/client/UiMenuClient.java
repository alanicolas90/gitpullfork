package ui.client;

import common.Common;
import constantes.Constantes;
import servicios.ServicioClients;
import java.util.Scanner;

public class UiMenuClient {



  public void menuSwitchClient(Scanner sc) {
    ServicioClients servicioClients = new ServicioClients();
    Common common = new Common();
    int clientOption;
    String dniClient;
    boolean dniExiste;

    do {
      System.out.println(Constantes.DIGAME_SU_DNI);
      dniClient = sc.nextLine();

      if (servicioClients.existeCliente(dniClient)) {
        System.out.println(Constantes.BIENVENIDO);
        dniExiste = true;
      } else {
        System.out.println(Constantes.ERROR_INTENTELO_DE_NUEVO);
        dniExiste = false;
      }
    } while (!dniExiste);

    do {

      System.out
          .println(Constantes.MENUCLIENTE);
      clientOption = common.giveInt();

      switch (clientOption) {
        case 1:
          UiClientSettings uiClientSettings = new UiClientSettings();
          uiClientSettings.settingsCliente(sc, dniClient);
          break;
        case 2:
          UiMenuClientBuy uiMenuClientBuy = new UiMenuClientBuy();
          uiMenuClientBuy.buyClient(sc, dniClient);
          break;
        case 0:
          System.out.println(Constantes.HASTA_LA_NEXT);
          break;
        default:
          System.out.println(Constantes.ERROR);
      }

    } while (clientOption != 0);
  }


}
