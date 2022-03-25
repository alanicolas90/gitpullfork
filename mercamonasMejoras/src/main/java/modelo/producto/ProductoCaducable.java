package modelo.producto;

import lombok.Data;
import modelo.constantes.Constantes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class ProductoCaducable extends Producto {

  private LocalDateTime caducidad;

  public ProductoCaducable() {
    super();
    this.type = "ProductoNormal";
  }

  public ProductoCaducable(String name, double price, int stock, List<Ingrediente> ingredientes,
      LocalDateTime caducidad) {
    super(name, price, stock, ingredientes);
    this.caducidad = caducidad;
    this.type = "ProductoCaducable";
  }

  public ProductoCaducable(String name, LocalDateTime caducidad) {
    super(name);
    this.caducidad = caducidad;
    this.type = "ProductoCaducable";
  }

  public ProductoCaducable(String name, double price, int stock, LocalDateTime caducidad) {
    super(name, price, stock);
    this.caducidad = caducidad;
    this.type = "ProductoCaducable";
  }


  public LocalDateTime getCaducidad() {
    return caducidad;
  }

  @Override
  public Producto clone() {
    return new ProductoCaducable(this.getName(), this.getPrice(), this.getStock(),
        this.getIngredientes().stream().collect(Collectors.toUnmodifiableList()), this.caducidad);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    ProductoCaducable that = (ProductoCaducable) o;
    return caducidad.equals(that.caducidad);
  }



  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), caducidad);
  }

  @Override
  public String toString() {
    return super.toString() + Constantes.CADUCIDAD + caducidad + Constantes.STRINGFINALTOSTRING;
  }


}
