package modelo.cliente;

import modelo.producto.Ingrediente;
import modelo.constantes.Constantes;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ClienteDescuento extends Cliente {

  private double descuento;

  public ClienteDescuento() {
    super();
    this.type = "ClienteDescuento";
  }

  public ClienteDescuento(String dni, String nombre, Set<Monedero> monederos, List<Ingrediente> alergenos, List<LineaCompra> carrito, List<List<LineaCompra>> buyHistory, double descuento) {
    super(dni, nombre, monederos, alergenos, carrito, buyHistory);
    this.descuento = descuento;
    type= "ClienteDescuento";
  }

  public ClienteDescuento(String dni, String nombre, double descuento) {
    super(dni, nombre);
    this.descuento = descuento;
    type= "ClienteDescuento";
  }

  public double getDescuento() {
    return descuento;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    ClienteDescuento that = (ClienteDescuento) o;
    return Double.compare(that.descuento, descuento) == 0;
  }

  @Override
  public Cliente clone() {
    return new ClienteDescuento(this.getDni(), this.getNombre(), this.getMonederos(), this.getAlergenos(),
            this.getCarrito(), this.getBuyHistory(), this.descuento);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), descuento);
  }

  @Override
  public String toString() {
    return super.toString() + Constantes.CLIENTE_DESCUENTO
            + Constantes.DESCUENTO + descuento
            + Constantes.STRINGPERCENTAGE + Constantes.STRINGFINALTOSTRING;
  }
}
