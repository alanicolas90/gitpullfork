package modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Objects;

@Data
@AllArgsConstructor
public class Ingrediente implements Clone {


  private String nombre;

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Ingrediente that = (Ingrediente) o;
    return nombre.equalsIgnoreCase(that.nombre);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nombre);
  }

  @Override
  public Ingrediente clone() {
    return new Ingrediente(this.nombre);
  }

}
