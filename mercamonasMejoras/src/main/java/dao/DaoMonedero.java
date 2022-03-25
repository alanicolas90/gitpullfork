package dao;

import modelo.cliente.Cliente;
import modelo.cliente.Monedero;

import java.util.Map;
import java.util.Set;

public class DaoMonedero {

    private BBDD db;

    public DaoMonedero() {
        this.db = new BBDD();
    }

    public boolean monederoExists(String nombreMonedero, String dniClient) {
        Map<String, Cliente> clientes = db.loadClientes();
        return clientes.get(dniClient).getMonederos().contains(new Monedero(nombreMonedero));
    }

    public boolean addMonedero(Monedero monedero, String dniClient) {
        boolean ok;
        Map<String, Cliente> clientes = db.loadClientes();
        clientes.get(dniClient).getMonederos().add(monedero);
        ok = db.saveClientes(clientes);

        return ok;
    }

    public boolean addMoneyMonedero(String dniCliente, String nombreMonedero, double quantity) {
        boolean ok;
        Map<String, Cliente> clientes = db.loadClientes();

        clientes.get(dniCliente).getMonederos().forEach(monedero -> {
            if (monedero.getName().equals(nombreMonedero)) {
                monedero.setMoney((monedero.getMoney() + quantity));
            }
        });

        ok = db.saveClientes(clientes);
        return ok;
    }

    public boolean removeMonedero(String dniClient, String nombreMonedero) {
        boolean ok;
        Map<String, Cliente> clientes = db.loadClientes();

        clientes.get(dniClient).getMonederos().remove(new Monedero(nombreMonedero));

        ok = db.saveClientes(clientes);
        return ok;
    }

    public Set<Monedero> showTarjetasCliente(String dniClient) {
        Map<String, Cliente> clientes = db.loadClientes();
        return clientes.get(dniClient).getMonederos();
    }


}
