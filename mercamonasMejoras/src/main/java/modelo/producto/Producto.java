package modelo.producto;

import lombok.Data;
import modelo.Clone;
import modelo.constantes.Constantes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Data
public abstract class Producto implements Clone<Producto>,Comparable<Producto> {

  private String name;
  private double price;
  public String type;

  private int stock;
  private List<Ingrediente> ingredientes;



  public Producto(String name, double price, int stock, List<Ingrediente> ingredientes) {
    this.name = name;
    this.price = price;
    this.stock = stock;
    this.ingredientes = ingredientes;
  }

  public Producto(String name) {
    this.name = name;
  }

  public Producto(String name, double price, int stock) {
    this();
    this.name = name;
    this.price = price;
    this.stock = stock;
  }

  public Producto() {
    ingredientes = new ArrayList<>();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Producto))
      return false;
    Producto producto = (Producto) o;
    return name.equalsIgnoreCase(producto.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name.toLowerCase());
  }

  @Override

  public String toString() {
    return Constantes.PRODUCTOCONNEWLINE + Constantes.NAMESINSALTO + name + Constantes.CHAR
            + Constantes.PRICECONSALTO + price + Constantes.STOCKCONSALTO + stock
        + Constantes.INGREDIENTESCONSALTO + ingredientes + Constantes.STRINGFINALTOSTRING;
  }

  @Override
  public int compareTo(Producto producto) {
    return this.getName().toLowerCase().compareTo(producto.getName().toLowerCase());
  }

  @Override
  public Producto clone() {
    return null;
  }
}
