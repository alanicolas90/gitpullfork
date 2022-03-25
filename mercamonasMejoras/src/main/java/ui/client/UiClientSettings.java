package ui.client;

import common.Common;
import constantes.Constantes;
import modelo.cliente.Cliente;
import modelo.cliente.ClienteNormal;
import modelo.cliente.Monedero;
import servicios.ServicioClients;
import servicios.ServicioCompras;
import java.util.Scanner;

public class UiClientSettings {

  public void settingsCliente(Scanner sc, String dniClient) {
    Common common = new Common();
    int settingsClient;



    do {
      System.out.println(Constantes.MENUCLIENTEAJUSTES);
      settingsClient = common.giveInt();

      switch (settingsClient) {
        case 1:
          changeName(sc, dniClient);
          break;
        case 2:
          addMonedero(sc, dniClient);
          break;
        case 3:
          deleteMonedero(sc, dniClient);
          break;
        case 4:
          agregarDineroMonederoExistente(sc, dniClient);
          break;
        case 5:
          showDataCliente(dniClient);
          break;
        case 6:
          showHistorialCompras(dniClient);
          break;
        case 7:
          addAlergiaCliente(sc, dniClient);
          break;
        case 8:
          dineroTotalGastadoTienda(dniClient);
          break;
        case 0:
          System.out.println(Constantes.BYE_BYE);
          break;
        default:
          System.out.println(Constantes.ERROR);
          break;
      }
    } while (settingsClient != 0);
  }

  private void dineroTotalGastadoTienda(String dniClient) {
    ServicioClients servicioClients = new ServicioClients();
    if (servicioClients.tieneComprasAnteriores(dniClient)) {
      System.out.println(Constantes.USTED_NO_HA_COMPRADO_NUNCA_EN_LA_TIENDA);
    } else {
      System.out.println(servicioClients.dineroTotalGastado(dniClient));
    }
  }

  private void addAlergiaCliente(Scanner sc, String dniClient) {
    ServicioClients servicioClients = new ServicioClients();
    System.out.println(Constantes.DIME_EL_INGREDIENTE_AL_QUE_TIENES_ALERGIA);
    String ingrediente = sc.nextLine();
    if (servicioClients.addIngredienteAlergia(dniClient, ingrediente)) {
      System.out.println(Constantes.HA_SIDO_UN_EXITO);
    } else {
      System.out.println(Constantes.ERROR);
    }
  }

  private void showHistorialCompras(String dniClient) {
    ServicioCompras servicioCompras = new ServicioCompras();
    System.out.println(servicioCompras.showBuyHistory(dniClient));
  }

  private void deleteMonedero(Scanner sc, String dniClient) {
    ServicioClients servicioClients = new ServicioClients();
    System.out.println(Constantes.COMO_SE_LLAMA_EL_MONEDERO);
    String nombreMonedero = sc.nextLine();

    if (servicioClients.removeMonedero(nombreMonedero, dniClient)) {
      System.out.println(Constantes.ELIMINADO_CON_EXITO);
      System.out.println(servicioClients.verDataCliente(dniClient));
    } else {
      System.out.println(Constantes.ERROR);
    }
  }

  private void addMonedero(Scanner sc, String dniClient) {
    Common common = new Common();
    ServicioClients servicioClients = new ServicioClients();
    System.out.println(Constantes.ADD_MONEDERO);
    System.out.println(Constantes.COMO_DESEA_QUE_SE_LLAME_EL_MONEDERO);
    String nombreMonedero = sc.nextLine();

    System.out.println(Constantes.CUANTO_DINERO_DESEA_INTRODUCIR_AL_MONEDERO);
    double dineroAgregarTarjeta = common.giveDouble();

    Monedero monedero = new Monedero(nombreMonedero, dineroAgregarTarjeta);

    if (servicioClients.addMonedero(monedero, dniClient)) {
      System.out.println(Constantes.HA_SIDO_UN_EXITO);
      System.out.println(servicioClients.verDataCliente(dniClient));
    } else {
      System.out.println(Constantes.ERROR);
    }
  }

  private void agregarDineroMonederoExistente(Scanner sc, String dniClient) {
    Common common = new Common();
    ServicioClients servicioClients = new ServicioClients();

    System.out.println(Constantes.A_QUE_TARJETA_DESEA_AGREGARLE_DINERO);
    // mostrar tarjetas IMPORTANTE
    String nombreTarjeta = sc.nextLine();

    System.out.println(Constantes.CUANTO_DINERO_DESEA_AGREGARLE);
    double dineroAgregar = common.giveDouble();

    if (servicioClients.addMoney(dniClient, nombreTarjeta, dineroAgregar)) {
      System.out.println(Constantes.LOGRADO_CON_EXITO);
      System.out.println(servicioClients.verDataCliente(dniClient));
    } else {
      System.out.println(Constantes.ERROR);
    }
  }

  private void showDataCliente(String dniClient) {
    ServicioClients servicioClients = new ServicioClients();
    System.out.println(servicioClients.verDataCliente(dniClient));
  }


  private void changeName(Scanner sc, String dniCliente) {
    ServicioClients servicioClients = new ServicioClients();

    System.out.println(Constantes.DIME_EL_NUEVO_NOMBRE_DEL_CLIENTE);
    String newNameClient = sc.nextLine();

    Cliente c = new ClienteNormal(dniCliente);

    if (servicioClients.swapNameClient(c, newNameClient)) {
      System.out.println(Constantes.EL_NOMBRE_HA_SIDO_CAMBIADO_CON_SUCCESS);
    } else {
      System.out.println(Constantes.ERROR_NO_SE_HA_PODIDO_HACER_LOS_CAMBIOS);
    }
  }
}
