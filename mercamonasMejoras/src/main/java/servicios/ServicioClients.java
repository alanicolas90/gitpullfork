package servicios;

import dao.DaoClientes;
import dao.DaoMonedero;
import dao.DaoProducto;
import modelo.cliente.Cliente;
import modelo.cliente.LineaCompra;
import modelo.cliente.Monedero;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ServicioClients {

  private DaoClientes dao;

  public ServicioClients() {
    this.dao = new DaoClientes();
  }

  public boolean addClient(Cliente newClient) {

    boolean canBeAdded = !dao.existClient(newClient.getDni());
    if (canBeAdded) {
       return dao.addClient(newClient);
    }
    return false;
  }

  public boolean removeClient(String dniClient) {
    boolean exists = dao.existClient(dniClient);
    if (exists) {
      return dao.removeClient(dniClient);
    }
    return false;
  }

  public boolean swapNameClient(Cliente c, String nuevoNombreCliente) {
    boolean exists = dao.existClient(c.getDni());
    if (exists) {
      return dao.swapNameClient(c, nuevoNombreCliente);
    }
    return false;
  }


  public boolean swapDni(String dniClient, String nuevoDniCliente) {
    boolean exists = dao.existClient(dniClient);
    if (exists) {
      return dao.swapDni(dniClient, nuevoDniCliente);
    }
    return false;
  }

  public boolean existeCliente(String dniClient) {
    return dao.existClient(dniClient);
  }

  public Map<String, Cliente> showListClients() {
    return dao.verListaClientes();
  }

  public boolean addMoney(String dniCliente, String nombreTarjeta, double dineroAgregar) {
    DaoMonedero daoMonedero = new DaoMonedero();
    boolean exists = daoMonedero.monederoExists(nombreTarjeta, dniCliente);
    if (exists)
      return daoMonedero.addMoneyMonedero(dniCliente, nombreTarjeta, dineroAgregar);
    return false;
  }

  public boolean addMonedero(Monedero monedero, String dniClient) {
    DaoMonedero daoMonedero = new DaoMonedero();
    boolean canBeAdded = !daoMonedero.monederoExists(monedero.getName(), dniClient);
    if (canBeAdded) {
      return daoMonedero.addMonedero(monedero, dniClient);
    }
    return false;
  }

  public boolean removeMonedero(String nombreMonedero, String dniClient) {
    DaoMonedero daoMonedero = new DaoMonedero();
    boolean exists = daoMonedero.monederoExists(nombreMonedero, dniClient);
    if (exists) {
      return daoMonedero.removeMonedero(dniClient, nombreMonedero);
    }
    return false;
  }

  public Set<Monedero> showTarjetasCliente(String dniClient) {
    DaoMonedero daoMonedero = new DaoMonedero();
    return daoMonedero.showTarjetasCliente(dniClient);
  }

  public Cliente verDataCliente(String dniClient) {
    boolean esClienteConDescuento = dao.clienteTieneDescuento(dniClient);
    if (esClienteConDescuento) {
      return dao.seeSpecificClientDescuento(dniClient);
    } else {
      return dao.seeSpecificClient(dniClient);
    }
  }

  public List<LineaCompra> showCarrito(String dniClient) {
    return dao.dameCarrito(dniClient);
  }

  public double getTotalPrice(String dniClient) {
    DaoProducto daoProducto = new DaoProducto();
    boolean esClienteConDescuento = dao.clienteTieneDescuento(dniClient);
    double precioTotalCarrito = 0;

    List<LineaCompra> carrito = dao.dameCarrito(dniClient);
    for (LineaCompra lineaCompra : carrito) {
      double precioProducto = daoProducto.getPriceProducto(lineaCompra.getProducto().getName());
      double cantidadProducto = lineaCompra.getQuantity();
      precioTotalCarrito = precioTotalCarrito + (cantidadProducto * precioProducto);
    }

    if (esClienteConDescuento) {
      precioTotalCarrito =
          (precioTotalCarrito * (1 - (dao.getDescuentoCliente(dniClient) / 100)));
    }
    return precioTotalCarrito;
  }


  public List<Cliente> showListClientsSotedDni() {
    return dao.showListaClientesOrdenadaDni();
  }

  public boolean addIngredienteAlergia(String dniClient, String ingrediente) {
    boolean ingredienteExisteCliente =
        !dao.ingredienteExisteCliente(dniClient, ingrediente);
    if (ingredienteExisteCliente) {
      dao.addIngredienteAlergia(dniClient, ingrediente);
    }
    return ingredienteExisteCliente;
  }

  public boolean tieneComprasAnteriores(String dniClient) {
    return dao.tieneComprasAnteriores(dniClient);
  }

  public double dineroTotalGastado(String dniClient) {
    DaoProducto daoProducto = new DaoProducto();
    boolean esClienteConDescuento = dao.clienteTieneDescuento(dniClient);

    double precioTotalCarrito = 0;
    List<List<LineaCompra>> historial = dao.showBuyHistory(dniClient);
    for (int i = 0; i < historial.size(); i++) {
      List<LineaCompra> carrito = dao.getLineaCompra(dniClient, i);
      for (LineaCompra lineaCompra : carrito) {
        double precioProducto = daoProducto.getPriceProducto(lineaCompra.getProducto().getName());
        double cantidadProducto = carrito.get(i).getQuantity();
        precioTotalCarrito += (cantidadProducto * precioProducto);
      }
    }
    if (esClienteConDescuento) {
      precioTotalCarrito =
          (precioTotalCarrito * (1 - (dao.getDescuentoCliente(dniClient) / 100)));
    }
    return precioTotalCarrito;
  }

  public List<Cliente> showClientesSortedDineroGastado() {
    return dao.showListaClientesSortedDineroGastado();
  }
}
