package dao;

import modelo.LineaCompra;
import modelo.Monedero;
import modelo.Producto;
import java.util.List;
import static dao.BBDD.clientes;
import static dao.BBDD.productos;


public class DaoCompras {

  public boolean alreadyInCart(Producto producto, String dniClient) {
    return clientes.get(dniClient).getCarrito().contains(new LineaCompra(producto));
  }

  public void addCart(String dniClient, LineaCompra productoNuevoCarrito) {
    clientes.get(dniClient).getCarrito().add(productoNuevoCarrito);
  }

  public void deleteProductCart(String dniClient, String nombreProductoBorrar) {
    clientes.get(dniClient).getCarrito()
        .remove(new LineaCompra(new Producto(nombreProductoBorrar)));
  }

  public void guardarCompra(String dniClient, List<LineaCompra> lineaCompras) {
    clientes.get(dniClient).getBuyHistory().add(lineaCompras);
  }

  public void cambiarStockComprar(String nombreProducto, int cantidadStockRestar) {
    int positionProductoBusco = productos.indexOf(new Producto(nombreProducto));
    productos.get(positionProductoBusco)
        .setStock((productos.get(positionProductoBusco).getStock()) - cantidadStockRestar);
  }

  public void ajustarDineroMonederoAfterCompra(String dniClient, String nombreMonedero, double precioRestar) {
    clientes.get(dniClient).getMonederos().forEach(monedero -> {
      if (monedero.equals(new Monedero(nombreMonedero))) {
        monedero.setMoney((monedero.getMoney() - precioRestar));
      }
    });
  }


}
