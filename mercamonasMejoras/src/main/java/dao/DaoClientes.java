package dao;

import modelo.cliente.Cliente;
import modelo.cliente.ClienteDescuento;
import modelo.cliente.LineaCompra;
import modelo.cliente.Monedero;
import modelo.producto.Ingrediente;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


public class DaoClientes extends DaoBase {

    private final BBDD db;

    public DaoClientes() {
        this.db = new BBDD();
    }
//ADD REMOVE Y ELIMINAR DE LO QUE SEA
    //-------------------------------------------------------------------------------------------------

    public boolean addClient(Cliente newClient) {
        boolean ok;
        Map<String, Cliente> clientes = db.loadClientes();
        if (clientes == null) {
            clientes = new LinkedHashMap<>();
        }
        clientes.put(newClient.getDni(), newClient);
        ok = db.saveClientes(clientes);

        return ok;
    }

    public boolean removeClient(String dniCliente) {
        boolean ok = false;
        Map<String, Cliente> clientes = db.loadClientes();

        if (clientes != null) {
            clientes.remove(dniCliente);
            ok = db.saveClientes(clientes);
        }
        return ok;
    }

    public boolean swapNameClient(Cliente c, String nuevoNombreCliente) {
        boolean ok;
        Map<String, Cliente> clientes = db.loadClientes();

        clientes.get(c.getDni()).setNombre(nuevoNombreCliente);
        ok = db.saveClientes(clientes);

        return ok;
    }

    public boolean swapDni(String dniClient, String nuevoDniCliente) {
        boolean ok;
        Map<String, Cliente> clientes = db.loadClientes();

        clientes.put(nuevoDniCliente, clientes.get(dniClient));
        clientes.remove(dniClient);
        clientes.get(nuevoDniCliente).setDni(nuevoDniCliente);

        ok = db.saveClientes(clientes);
        return ok;
    }

    public double getTodoDineroMonedero(String dniClient, String nombreMonedero) {
        Map<String, Cliente> clientes = db.loadClientes();

        AtomicReference<Double> dineroTarjeta = new AtomicReference<>((double) 0);
        clientes.get(dniClient).getMonederos().forEach(monedero -> {
            if (monedero.equals(new Monedero(nombreMonedero))) {
                dineroTarjeta.set(monedero.getMoney());
            }
        });
        return dineroTarjeta.get();
    }

    public double getDescuentoCliente(String dniClient) {
        Map<String, Cliente> clientes = db.loadClientes();

        return ((ClienteDescuento)clientes.get(dniClient)).getDescuento();
    }

    public Double getCosteCompras(Cliente cliente) {
        return cliente.getBuyHistory().stream()
                .flatMapToDouble(lineaCompras -> lineaCompras.stream()
                        .mapToDouble(value -> value.getProducto().getPrice() * value.getQuantity()))
                .sum();
    }


//COMPROBACIONES (booleans)
    //-------------------------------------------------------------------------------------------------

    public boolean clienteTieneDescuento(String dniClient) {
        Map<String, Cliente> clientes = db.loadClientes();
        return clientes.get(dniClient) instanceof ClienteDescuento;
    }

    public boolean existClient(String clientDNI) {
        Map<String, Cliente> clientes = db.loadClientes();
        if(clientes!=null)
            return clientes.containsKey(clientDNI);

        return false;
    }

    public void emptyCart(String dniClient) {
        Map<String, Cliente> clientes = db.loadClientes();
        clientes.get(dniClient).setCarrito(new ArrayList<>());
        db.saveClientes(clientes);
    }

    public void addIngredienteAlergia(String dniClient, String ingrediente) {
        Map<String, Cliente> clientes = db.loadClientes();
        clientes.get(dniClient).getAlergenos().add(new Ingrediente(ingrediente));
        db.saveClientes(clientes);
    }

    public boolean ingredienteExisteCliente(String dniClient, String ingrediente) {
        Map<String, Cliente> clientes = db.loadClientes();
        return clientes.get(dniClient).getAlergenos().contains(new Ingrediente(ingrediente));
    }

    public boolean tieneComprasAnteriores(String dniClient) {
        Map<String, Cliente> clientes = db.loadClientes();
        return clientes.get(dniClient).getBuyHistory().isEmpty();
    }


    //VER COSAS
    //-----------------------------------------------------------------------------------------------

    public List<Cliente> showListaClientesSortedDineroGastado() {
        Map<String, Cliente> clientes = db.loadClientes();
        return clientes.values()
                .stream()
                .sorted((o1, o2) -> getCosteCompras(o2).compareTo(getCosteCompras(o1)))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<LineaCompra> getLineaCompra(String dniClient, int index) {
        Map<String, Cliente> clientes = db.loadClientes();
        return clientes.get(dniClient).getBuyHistory().get(index);
    }

    public List<List<LineaCompra>> showBuyHistory(String dniClient) {
        Map<String, Cliente> clientes = db.loadClientes();
        return clientes.get(dniClient).getBuyHistory();
    }

    public List<Cliente> showListaClientesOrdenadaDni() {
        Map<String, Cliente> clientes = db.loadClientes();
        return clientes.values()
                .stream()
                .sorted(Comparator.comparing(Cliente::getDni))
                .collect(Collectors.toUnmodifiableList());
    }

    public Map<String, Cliente> verListaClientes() {
        return db.loadClientes();
    }

    public List<LineaCompra> dameCarrito(String dniClient) {
        Map<String, Cliente> clientes = db.loadClientes();
        return clientes.get(dniClient).getCarrito();
    }

    public Cliente seeSpecificClient(String dniClient) {
        Map<String, Cliente> clientes = db.loadClientes();
        return clientes.get(dniClient);
    }

    public ClienteDescuento seeSpecificClientDescuento(String dniClient) {
        Map<String, Cliente> clientes = db.loadClientes();
        return (ClienteDescuento) clientes.get(dniClient);
    }

}
