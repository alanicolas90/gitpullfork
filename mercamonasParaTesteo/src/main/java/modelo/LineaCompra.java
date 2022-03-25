package modelo;

import lombok.Data;
import modelo.constantes.Constantes;

import java.util.Objects;

@Data
public class LineaCompra {

  private Producto producto;
  private int quantity;

  public LineaCompra(Producto producto) {
    this.producto = producto;
  }

  public LineaCompra(Producto producto, int quantity) {
    this.producto = producto;
    this.quantity = quantity;
  }

  public Producto getProducto() {
    return producto;
  }

  public void setProducto(Producto producto) {
    this.producto = producto;
  }

  public int getQuantity() {
    return quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    LineaCompra that = (LineaCompra) o;
    return producto.equals(that.producto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(producto);
  }

  @Override
  public String toString() {
    return Constantes.LINEA_COMPRA + Constantes.PRODUCTODOS + producto.getName() + Constantes.QUANTITY + quantity + Constantes.CHAR2;
  }
}
