package modelo.cliente;

import modelo.constantes.Constantes;

import java.util.Objects;

public class Monedero {

  private final String name;
  private double money;


  public Monedero(String name) {
    this.name = name;
  }


  public Monedero(String name, double money) {
    this.name = name;
    this.money = money;
  }

  public String getName() {
    return name;
  }

  public double getMoney() {
    return money;
  }

  public void setMoney(double money) {
    this.money = money;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Monedero monedero = (Monedero) o;
    return name.equals(monedero.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {
    return Constantes.MONEDERO + Constantes.NAMESINSALTO + name
            + Constantes.CHAR + Constantes.MONEYSINSALTO + money + Constantes.CHAR2;
  }
}
