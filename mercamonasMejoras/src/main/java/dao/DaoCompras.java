package dao;

import modelo.cliente.Cliente;
import modelo.cliente.LineaCompra;
import modelo.cliente.Monedero;
import modelo.producto.Producto;
import modelo.producto.ProductoNormal;

import java.util.List;
import java.util.Map;


public class DaoCompras {

    private final BBDD db;

    public DaoCompras() {
        this.db = new BBDD();
    }


    public boolean alreadyInCart(String nombreProducto, String dniClient) {
        Map<String, Cliente> clientes = db.loadClientes();
        Cliente clienteQueBuscamos = clientes.values().stream().filter(cliente -> cliente.getNombre().equals(dniClient)).findFirst().orElse(null);
        if (clienteQueBuscamos != null) {
            LineaCompra productoQueBuscamos = clienteQueBuscamos.getCarrito().stream().filter(lineaCompra -> lineaCompra.getProducto().equals(nombreProducto)).findFirst().orElse(null);
            return productoQueBuscamos != null;
        }
        return false;
    }

    public void addCart(String dniClient, LineaCompra productoNuevoCarrito) {
        Map<String, Cliente> clientes = db.loadClientes();
        clientes.get(dniClient).getCarrito().add(productoNuevoCarrito);
        db.saveClientes(clientes);
    }

    public void deleteProductCart(String dniClient, String nombreProductoBorrar) {
        Map<String, Cliente> clientes = db.loadClientes();
        clientes.get(dniClient).getCarrito()
                .remove(new LineaCompra(new ProductoNormal(nombreProductoBorrar)));
        db.saveClientes(clientes);
    }

    public void guardarCompra(String dniClient, List<LineaCompra> lineaCompras) {
        Map<String, Cliente> clientes = db.loadClientes();
        clientes.get(dniClient).getBuyHistory().add(lineaCompras);
        db.saveClientes(clientes);
    }

    public void cambiarStockComprar(String nombreProducto, int cantidadStockRestar) {
        List<Producto> productos = db.loadProducto();
        int positionProductoBusco = productos.indexOf(new ProductoNormal(nombreProducto));
        productos.get(positionProductoBusco)
                .setStock((productos.get(positionProductoBusco).getStock()) - cantidadStockRestar);
        db.saveProducto(productos);
    }

    public void ajustarDineroMonederoAfterCompra(String dniClient, String nombreMonedero, double dineroRestante) {
        Map<String, Cliente> clientes = db.loadClientes();
        clientes.get(dniClient).getMonederos().forEach(monedero -> {
            if (monedero.equals(new Monedero(nombreMonedero))) {
                monedero.setMoney(dineroRestante);
            }
        });
        db.saveClientes(clientes);
    }


}
