package dao;

import modelo.Monedero;
import java.util.Set;
import static dao.BBDD.clientes;

public class DaoMonedero {

  public boolean monederoExists(String nombreMonedero, String dniClient) {
    return clientes.get(dniClient).getMonederos().contains(new Monedero(nombreMonedero));
  }

  public void addMonedero(Monedero monedero, String dniClient) {
    clientes.get(dniClient).getMonederos().add(monedero);
  }

  public void addMoneyMonedero(String dniCliente, String nombreMonedero, double quantity) {
    clientes.get(dniCliente).getMonederos().forEach(monedero -> {
      if (monedero.getName().equals(nombreMonedero)) {
        monedero.setMoney((monedero.getMoney() + quantity));
      }
    });
  }

  public void removeMonedero(String dniClient, String nombreMonedero) {
    clientes.get(dniClient).getMonederos().remove(new Monedero(nombreMonedero));
  }

  public Set<Monedero> showTarjetasCliente(String dniClient) {
    return clientes.get(dniClient).clone().getMonederos();
  }



}
