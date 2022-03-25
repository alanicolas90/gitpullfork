package servicios;

import dao.DaoClientes;
import dao.DaoMonedero;
import dao.DaoProducto;
import modelo.Cliente;
import modelo.LineaCompra;
import modelo.Monedero;
import java.util.List;
import java.util.Set;


public class ServicioClients {

  public boolean addClient(Cliente newClient) {
    DaoClientes daoClientesAdmin = new DaoClientes();
    boolean canBeAdded = !daoClientesAdmin.existClient(newClient.getDni());
    if (canBeAdded) {
      daoClientesAdmin.addClient(newClient);
    }
    return canBeAdded;
  }

  public boolean removeClient(String dniClient) {
    DaoClientes daoClientesAdmin = new DaoClientes();
    boolean exists = daoClientesAdmin.existClient(dniClient);

    if (exists) {
      daoClientesAdmin.removeClient(dniClient);
    }
    return exists;
  }

  public boolean swapNameClient(String dniClient, String nuevoNombreCliente) {
    DaoClientes daoClientesAdmin = new DaoClientes();
    boolean exists = daoClientesAdmin.existClient(dniClient);
    if (exists) {
      daoClientesAdmin.swapNameClient(dniClient, nuevoNombreCliente);
    }
    return exists;
  }


  public boolean swapDni(String dniClient, String nuevoDniCliente) {
    DaoClientes daoClientesAdmin = new DaoClientes();
    boolean exists = daoClientesAdmin.existClient(dniClient);
    if (exists) {
      daoClientesAdmin.swapDni(dniClient, nuevoDniCliente);
    }
    return exists;
  }

  public boolean existeCliente(String dniClient) {
    DaoClientes daoClientesAdmin = new DaoClientes();
    return daoClientesAdmin.existClient(dniClient);
  }

  public List<Cliente> showListClients() {
    DaoClientes daoClientes = new DaoClientes();
    return daoClientes.verListaClientes();
  }

  public boolean addMoney(String dniCliente, String nombreTarjeta, double dineroAgregar) {
    DaoMonedero daoMonedero = new DaoMonedero();
    boolean exists = daoMonedero.monederoExists(nombreTarjeta, dniCliente);
    if (exists)
      daoMonedero.addMoneyMonedero(dniCliente, nombreTarjeta, dineroAgregar);
    return exists;
  }

  public boolean addMonedero(Monedero monedero, String dniClient) {
    DaoMonedero daoMonedero = new DaoMonedero();
    boolean canBeAdded = !daoMonedero.monederoExists(monedero.getName(), dniClient);
    if (canBeAdded) {
      daoMonedero.addMonedero(monedero, dniClient);
    }
    return canBeAdded;
  }

  public boolean removeMonedero(String nombreMonedero, String dniClient) {
    DaoMonedero daoMonedero = new DaoMonedero();
    boolean exists = daoMonedero.monederoExists(nombreMonedero, dniClient);
    if (exists) {
      daoMonedero.removeMonedero(dniClient, nombreMonedero);
    }
    return exists;
  }

  public Set<Monedero> showTarjetasCliente(String dniClient) {
    DaoMonedero daoMonedero = new DaoMonedero();
    return daoMonedero.showTarjetasCliente(dniClient);
  }

  public Cliente verDataCliente(String dniClient) {
    DaoClientes daoClientes = new DaoClientes();
    boolean esClienteConDescuento = daoClientes.clienteTieneDescuento(dniClient);
    if (esClienteConDescuento) {
      return daoClientes.seeSpecificClientDescuento(dniClient);
    } else {
      return daoClientes.seeSpecificClient(dniClient);
    }
  }

  public List<LineaCompra> showCarrito(String dniClient) {
    DaoClientes daoClientes = new DaoClientes();
    return daoClientes.dameCarrito(dniClient);
  }

  public double getTotalPrice(String dniClient) {
    DaoClientes daoClientes = new DaoClientes();
    DaoProducto daoProducto = new DaoProducto();
    boolean esClienteConDescuento = daoClientes.clienteTieneDescuento(dniClient);
    double precioTotalCarrito = 0;

    List<LineaCompra> carrito = daoClientes.dameCarrito(dniClient);
    for (LineaCompra lineaCompra : carrito) {
      double precioProducto = daoProducto.getPriceProducto(lineaCompra.getProducto().getName());
      double cantidadProducto = lineaCompra.getQuantity();
      precioTotalCarrito = precioTotalCarrito + (cantidadProducto * precioProducto);
    }

    if (esClienteConDescuento) {
      precioTotalCarrito =
          (precioTotalCarrito * (1 - (daoClientes.getDescuentoCliente(dniClient) / 100)));
    }
    return precioTotalCarrito;
  }


  public List<Cliente> showListClientsSotedDni() {
    DaoClientes daoClientes = new DaoClientes();
    return daoClientes.showListaClientesOrdenadaDni();
  }

  public boolean addIngredienteAlergia(String dniClient, String ingrediente) {
    DaoClientes daoClientes = new DaoClientes();
    boolean ingredienteExisteCliente =
        !daoClientes.ingredienteExisteCliente(dniClient, ingrediente);
    if (ingredienteExisteCliente) {
      daoClientes.addIngredienteAlergia(dniClient, ingrediente);
    }
    return ingredienteExisteCliente;
  }

  public boolean tieneComprasAnteriores(String dniClient) {
    DaoClientes daoClientes = new DaoClientes();
    return daoClientes.tieneComprasAnteriores(dniClient);
  }

  public double dineroTotalGastado(String dniClient) {
    DaoClientes daoClientes = new DaoClientes();
    DaoProducto daoProducto = new DaoProducto();
    boolean esClienteConDescuento = daoClientes.clienteTieneDescuento(dniClient);

    double precioTotalCarrito = 0;
    List<List<LineaCompra>> historial = daoClientes.dameHistorialCompra(dniClient);
    for (int i = 0; i < historial.size(); i++) {
      List<LineaCompra> carrito = daoClientes.getLineaCompra(dniClient, i);
      for (LineaCompra lineaCompra : carrito) {
        double precioProducto = daoProducto.getPriceProducto(lineaCompra.getProducto().getName());
        double cantidadProducto = carrito.get(i).getQuantity();
        precioTotalCarrito += (cantidadProducto * precioProducto);
      }
    }
    if (esClienteConDescuento) {
      precioTotalCarrito =
          (precioTotalCarrito * (1 - (daoClientes.getDescuentoCliente(dniClient) / 100)));
    }
    return precioTotalCarrito;
  }

  public List<Cliente> showClientesSortedDineroGastado() {
    DaoClientes daoClientes = new DaoClientes();
    return daoClientes.showListaClientesSortedDineroGastado();
  }
}
