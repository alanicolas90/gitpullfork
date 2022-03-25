package dao;

import modelo.Ingrediente;
import modelo.LineaCompra;
import modelo.Producto;
import modelo.ProductoCaducable;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static dao.BBDD.clientes;
import static dao.BBDD.productos;

public class DaoProducto extends DaoBase {


    //GET SET Y MODIFICAR DE LO QUE SEA
    //-------------------------------------------------------------------------------------------------

    public void addProducto(Producto newProducto) {
        productos
                .add(new Producto(newProducto.getName(), newProducto.getPrice(), newProducto.getStock()));
    }

    public void removeProducto(String nombreProducto) {
        productos.remove(new Producto(nombreProducto));
    }

    public void updateNameProducto(String nombreProducto, String newNombreProducto) {
        int index = productos.indexOf(new Producto(nombreProducto));
        productos.get(index).setName(newNombreProducto);
    }

    public void updatePriceProducto(String nombreProducto, double newPriceProducto) {
        int index = productos.indexOf(new Producto(nombreProducto));
        productos.get(index).setPrice(newPriceProducto);
    }

    public void updateStockProducto(String nombreProducto, int newStockProducto) {
        int index = productos.indexOf(new Producto(nombreProducto));
        productos.get(index).setStock(newStockProducto);
    }

    public Producto getProductoLista(String productoMeterCarrito) {
        return productos.get(productos.indexOf(new Producto(productoMeterCarrito)));
    }

    public double getPriceProducto(String nameProduct) {
        int productoBusco = productos.indexOf(new Producto(nameProduct));
        return dameElementoClonado(productos.get(productoBusco)).getPrice();
    }

    public int getStockProduct(String nombreProduct) {
        int positionProductoBusco = productos.indexOf(new Producto(nombreProduct));
        return dameElementoClonado(productos.get(positionProductoBusco)).getStock();
    }

    public void addIngredienteAlProducto(Ingrediente nuevoIngrediente, int index) {
        productos.get(index).getIngredientes().add(new Ingrediente(nuevoIngrediente.getNombre()));

    }


    //COMPROBACIONES
    //-------------------------------------------------------------------------------------------------

    public boolean productExists(String nombreProducto) {
        return productos.contains(new Producto(nombreProducto));
    }

    public boolean ingredienteExisteEnProducto(Ingrediente nuevoIngrediente, int index) {
        return dameElementoClonado(productos.get(index)).getIngredientes().contains(nuevoIngrediente);
    }

    // VER PRODUCTOS
    // ---------------------------------------------------------------------------------------------------

    public List<Producto> showAllProducts() {
        return dameListaClonadaInmutable(productos);
    }

    public List<Producto> showAllProductosSinCaducables() {
        return productos.stream()
                .filter(producto -> !(producto instanceof ProductoCaducable) || ((ProductoCaducable) producto).getCaducidad().isAfter(LocalDateTime.now()))
                .map(Producto::clone)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Producto> showAllProductsSortedName() {
        return productos.stream()
                .sorted(Comparator.comparing(Producto::getName))
                .map(Producto::clone)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Producto> showAllProductsConIngrediente() {
        return productos.stream()
                .filter(producto -> !producto.getIngredientes().isEmpty())
                .map(Producto::clone)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Producto> showAllProductsSinAlergiasCliente(String dniClient) {
        return productos.stream()
                .filter(
                        producto -> !producto.getIngredientes().equals(clientes.get(dniClient).getAlergenos()))
                .map(Producto::clone).collect(Collectors.toUnmodifiableList());

    }

    public List<String> showCantidadProductosOrdenadaPorLaMasComprada() {
        Map<String, Double> map = clientes.values().stream()
                .flatMap(cliente -> cliente.getBuyHistory().stream())
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(lineaCompra -> lineaCompra.getProducto().getName(), Collectors.summingDouble(LineaCompra::getQuantity)));

        return map.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .map(stringDoubleEntry -> stringDoubleEntry.getKey() + " " + stringDoubleEntry.getValue())
                .collect(Collectors.toUnmodifiableList());
    }


    // index de un producto por su nombre
    //**************************************************************************************************

    public int indexProduct(String nameProduct) {
        return productos.indexOf(new Producto(nameProduct));
    }
}
