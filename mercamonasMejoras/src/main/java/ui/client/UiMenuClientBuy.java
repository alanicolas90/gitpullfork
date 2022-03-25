package ui.client;

import common.Common;
import constantes.Constantes;
import modelo.cliente.LineaCompra;
import modelo.producto.Producto;
import servicios.ServicioClients;
import servicios.ServicioCompras;
import servicios.ServicioProductos;
import java.util.Scanner;

public class UiMenuClientBuy {

  public void buyClient(Scanner sc, String dniClient) {
    Common common = new Common();
    int clientDecision;

    do {
      System.out.println(Constantes.MENU_CLIENTE_COMPRA);
      clientDecision = common.giveInt();

      switch (clientDecision) {
        case 1:
          addProductCart(sc, dniClient);
          break;
        case 2:
          deleteProductFromCart(sc, dniClient);
          break;
        case 3:
          pagarElCarrito(sc, dniClient);
          break;
        case 4:
          showAllProductsSinAlergiasCliente(dniClient);
          break;
        case 0:
          System.out.println(Constantes.MUCHAS_GRACIAS_Y_HASTA_LUEGO);
          break;
        default:
          System.out.println(Constantes.ERROR);

      }

    } while (clientDecision != 0);

  }

  private void showAllProductsSinAlergiasCliente(String dniClient) {
    ServicioProductos servicioProductos = new ServicioProductos();
    System.out.println(servicioProductos.showAllProductsSinAlergiasCliente(dniClient));
  }

  private void pagarElCarrito(Scanner sc, String dniClient) {
    ServicioClients servicioClients = new ServicioClients();
    ServicioCompras servicioCompras = new ServicioCompras();
    // pagar el carrito
    System.out.print(Constantes.EL_PRECIO_DE_TU_CARRITO_ES);
    System.out.println(servicioClients.getTotalPrice(dniClient));
    // elegir que tarjeta con la que pagar
    System.out.println(Constantes.CON_QUE_TARJETA_DESEA_PAGAR);
    System.out.println(servicioClients.showTarjetasCliente(dniClient));

    System.out.println(Constantes.PONGA_EL_NOMBRE_DE_LA_TARJETA);
    String nombreTarjetaPagar = sc.nextLine();

    // mensaje que bien o mal
    // hacer las deducciones
    if (servicioCompras.pagarCarrito(dniClient, nombreTarjetaPagar)) {
      System.out.println(Constantes.HA_SIDO_UN_EXITO);
    } else {
      System.out.println(Constantes.ERROR);
    }
    System.out.println(servicioClients.verDataCliente(dniClient));
  }

  private void deleteProductFromCart(Scanner sc, String dniClient) {
    ServicioCompras servicioCompras = new ServicioCompras();
    ServicioClients servicioClients = new ServicioClients();

    // imprimir solo el carrito del cliente
    System.out.println(servicioClients.showCarrito(dniClient));
    System.out.println(Constantes.DIME_EL_PRODUCTO_QUE_DESEA_ELIMINAR_DEL_CARRITO);
    String nombreProductoBorrar = sc.nextLine();

    if (servicioCompras.removeProductCart(dniClient, nombreProductoBorrar)) {
      System.out.println(Constantes.HA_SIDO_UN_EXITO);
      System.out.println(servicioClients.showCarrito(dniClient));
    } else {
      System.out.println(Constantes.ERROR);
    }
  }

  private void addProductCart(Scanner sc, String dniClient) {
    Common common = new Common();
    ServicioCompras servicioCompras = new ServicioCompras();
    ServicioProductos servicioProductos = new ServicioProductos();
    ServicioClients servicioClients = new ServicioClients();

    System.out.println(Constantes.ELIJA_UN_PRODUCTO_DE_ESTA_LISTA);
    System.out.println(servicioProductos.showAllProductsSinCaducados());

    System.out.println(Constantes.QUE_PRODUCTO_DESEA_AGREGAR_AL_CARRITO);
    String nombreProducto = sc.nextLine();

    if (servicioProductos.existProduct(nombreProducto)) {

      Producto productoAddCart = servicioProductos.getProductoLista(nombreProducto);
      System.out.println(Constantes.CUANTA_CANTIDAD_QUIERE);
      int quantity = common.giveInt();

      LineaCompra carritoAdd = new LineaCompra(productoAddCart, quantity);

      if (servicioCompras.addToCart(dniClient, carritoAdd)) {
        System.out.println(Constantes.HA_SIDO_UN_EXITO);
      } else {
        System.out.println(Constantes.ERROR);
      }
    } else {
      System.out.println(Constantes.EL_PRODUCTO_NO_EXISTE);
    }

    System.out.println(servicioClients.verDataCliente(dniClient));
    System.out.println(servicioClients.showCarrito(dniClient));
  }
}
