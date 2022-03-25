package modelo;

import modelo.constantes.Constantes;

import java.util.*;

public class Cliente implements Clone<Cliente>, Comparable<Cliente> {

  private String dni;
  private String nombre;
  private final Set<Monedero> monederos;

  private List<Ingrediente> alergenos;
  private List<LineaCompra> carrito;
  private List<List<LineaCompra>> buyHistory;

  public void setAlergenos(List<Ingrediente> alergenos) {
    this.alergenos = alergenos;
  }

  public void setBuyHistory(List<List<LineaCompra>> buyHistory) {
    this.buyHistory = buyHistory;
  }

  public Cliente(String dni, String nombre, Set<Monedero> monederos,
      List<Ingrediente> alergenos, List<LineaCompra> carrito, List<List<LineaCompra>> buyHistory) {
    this.dni = dni;
    this.nombre = nombre;
    this.monederos = monederos;
    this.alergenos = alergenos;
    this.carrito = carrito;
    this.buyHistory = buyHistory;
  }

  private Cliente() {
    alergenos = new ArrayList<>();
    buyHistory = new ArrayList<>();
    monederos = new HashSet<>();
    carrito = new ArrayList<>();
  }

  public Cliente(String dni, String nombre) {
    this();
    this.dni = dni;
    this.nombre = nombre;
  }

  public Cliente(String dni, String nombre, Set<Monedero> monederos,
      List<LineaCompra> carrito, List<List<LineaCompra>> buyHistory) {
    this.dni = dni;
    this.nombre = nombre;
    this.monederos = monederos;
    this.carrito = carrito;
    this.buyHistory = buyHistory;
  }


  public String getNombre() {
    return nombre;
  }

  public Set<Monedero> getMonederos() {
    return monederos;
  }

  public List<LineaCompra> getCarrito() {
    return carrito;
  }

  public List<List<LineaCompra>> getBuyHistory() {
    return buyHistory;
  }

  public void setCarrito(List<LineaCompra> carrito) {
    this.carrito = carrito;
  }

  public String getDni() {
    return dni;
  }

  public List<Ingrediente> getAlergenos() {
    return alergenos;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Cliente cliente = (Cliente) o;
    return dni.equalsIgnoreCase(cliente.dni);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dni, alergenos);
  }

  @Override
  public String toString() {
    return Constantes.CLIENTE + Constantes.DNI + dni + Constantes.CHAR + Constantes.NOMBRE + nombre + Constantes.CHAR + Constantes.CHAR + Constantes.MONEDEROS + monederos + Constantes.ALERGENOS + alergenos
        + Constantes.CARRITO + carrito + Constantes.BUY_HISTORY + buyHistory + Constantes.CHAR2;
  }

  @Override
  public Cliente clone() {
    return new Cliente(this.dni, this.nombre, this.monederos, this.alergenos,
        this.carrito, this.buyHistory);
  }

  @Override
  public int compareTo(Cliente o) {
    return 0;
  }
}
