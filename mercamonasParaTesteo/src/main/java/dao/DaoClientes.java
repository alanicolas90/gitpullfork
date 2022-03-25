package dao;

import modelo.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static dao.BBDD.clientes;

public class DaoClientes extends DaoBase {


    //ADD REMOVE Y ELIMINAR DE LO QUE SEA
    //-------------------------------------------------------------------------------------------------

    public void addClient(Cliente newClient) {
        clientes.put(newClient.getDni(), newClient);
    }

    public void removeClient(String dniClient) {
        clientes.remove(dniClient);
    }

    public void swapNameClient(String dniClient, String nuevoNombreCliente) {
        clientes.get(dniClient).setNombre(nuevoNombreCliente);
    }

    public void swapDni(String dniClient, String nuevoDniCliente) {
        clientes.get(dniClient).setDni(nuevoDniCliente);
        clientes.put(nuevoDniCliente, clientes.get(dniClient));
        clientes.remove(dniClient);
    }

    public double getTodoDineroMonedero(String dniClient, String nombreMonedero) {
        AtomicReference<Double> dineroTarjeta = new AtomicReference<>((double) 0);
        clientes.get(dniClient).getMonederos().forEach(monedero -> {
            if (monedero.equals(new Monedero(nombreMonedero))) {
                dineroTarjeta.set(monedero.getMoney());
            }
        });
        return dineroTarjeta.get();
    }

    public double getDescuentoCliente(String dniClient) {
        return ((ClienteDescuento) dameElementoClonado(clientes.get(dniClient))).getDescuento();
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
        return clientes.get(dniClient) instanceof ClienteDescuento;
    }

    public boolean existClient(String clientDNI) {
        return clientes.containsKey(clientDNI);
    }

    public void emptyCart(String dniClient) {
        clientes.get(dniClient).setCarrito(new ArrayList<>());
    }

    public void addIngredienteAlergia(String dniClient, String ingrediente) {
        clientes.get(dniClient).getAlergenos().add(new Ingrediente(ingrediente));
    }

    public boolean ingredienteExisteCliente(String dniClient, String ingrediente) {
        return clientes.get(dniClient).getAlergenos().contains(new Ingrediente(ingrediente));
    }

    public boolean tieneComprasAnteriores(String dniClient) {
        return clientes.get(dniClient).getBuyHistory().isEmpty();
    }


    //VER COSAS
    //-----------------------------------------------------------------------------------------------

    public List<Cliente> showListaClientesSortedDineroGastado() {
        return clientes.values()
                .stream()
                .sorted((o1, o2) -> getCosteCompras(o2).compareTo(getCosteCompras(o1)))
                .map(Cliente::clone)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<LineaCompra> getLineaCompra(String dniClient, int index) {
        return clientes.get(dniClient).getBuyHistory().get(index);
    }

    public List<List<LineaCompra>> dameHistorialCompra(String dniClient) {
        return dameElementoClonado(clientes.get(dniClient)).getBuyHistory();
    }

    public List<Cliente> showListaClientesOrdenadaDni() {
        return clientes.values().stream().sorted(Comparator.comparing(Cliente::getDni))
                .map(Cliente::clone).collect(Collectors.toUnmodifiableList());
    }

    public List<Cliente> verListaClientes() {
        return dameListaClonadaInmutable(clientes.values());
    }

    public List<LineaCompra> dameCarrito(String dniClient) {
        return dameElementoClonado(clientes.get(dniClient)).getCarrito();
    }

    public Cliente seeSpecificClient(String dniClient) {
        return dameElementoClonado(clientes.get(dniClient));
    }

    public List<List<LineaCompra>> showBuyHistory(String dniClient) {
        return dameElementoClonado(clientes.get(dniClient)).getBuyHistory();
    }

    public ClienteDescuento seeSpecificClientDescuento(String dniClient) {
        return (ClienteDescuento) dameElementoClonado(clientes.get(dniClient));
    }

}
