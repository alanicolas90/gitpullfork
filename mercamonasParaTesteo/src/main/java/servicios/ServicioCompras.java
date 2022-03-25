package servicios;

import dao.DaoClientes;
import dao.DaoCompras;
import dao.DaoProducto;
import modelo.LineaCompra;
import modelo.Producto;
import java.util.List;


public class ServicioCompras {

  public boolean addToCart(String dniClient, LineaCompra productoAddCarrito) {
    DaoCompras daoCompras = new DaoCompras();
    DaoProducto daoProducto = new DaoProducto();
    boolean seHaMetido = false;

    boolean inCart = !daoCompras.alreadyInCart(productoAddCarrito.getProducto(), dniClient);
    int stockTiendaProducto =
        daoProducto.getStockProduct(productoAddCarrito.getProducto().getName());

    if (inCart && productoAddCarrito.getQuantity() <= stockTiendaProducto) {
      daoCompras.addCart(dniClient, productoAddCarrito);
      daoCompras.cambiarStockComprar(productoAddCarrito.getProducto().getName(),
          productoAddCarrito.getQuantity());
      seHaMetido = true;
    }
    return seHaMetido;
  }

  public boolean removeProductCart(String dniClient, String nombreProductoBorrar) {
    DaoCompras daoCompras = new DaoCompras();
    boolean isCart = daoCompras.alreadyInCart(new Producto(nombreProductoBorrar), dniClient);
    if (isCart) {
      daoCompras.deleteProductCart(dniClient, nombreProductoBorrar);
    }
    return isCart;
  }

  public void guardarHistorialCompra(String dniClient) {
    DaoCompras daoCompras = new DaoCompras();
    DaoClientes daoClientes = new DaoClientes();

    List<LineaCompra> lineaCompras = daoClientes.dameCarrito(dniClient);
    daoCompras.guardarCompra(dniClient, lineaCompras);
    daoClientes.emptyCart(dniClient);
  }

  public List<List<LineaCompra>> showBuyHistory(String dniClient) {
    DaoClientes daoClientes = new DaoClientes();
    return daoClientes.showBuyHistory(dniClient);
  }

  public boolean pagarCarrito(String dniClient, String nombreMonedero) {
    ServicioClients servicioClients = new ServicioClients();
    DaoClientes daoClientes = new DaoClientes();
    DaoCompras daoCompras = new DaoCompras();

    boolean seHaPagado = false;
    double precioTotalCarrito = servicioClients.getTotalPrice(dniClient);
    double dineroMonedero = daoClientes.getTodoDineroMonedero(dniClient, nombreMonedero);

    if (dineroMonedero >= precioTotalCarrito) {
      // monedero quito dinero
      double dineroRestanteMonedero = dineroMonedero - precioTotalCarrito;
      daoCompras.ajustarDineroMonederoAfterCompra(dniClient, nombreMonedero,
          dineroRestanteMonedero);
      guardarHistorialCompra(dniClient);

      seHaPagado = true;
    }
    return seHaPagado;
  }


}
