package servicios;

import dao.DaoProducto;
import dao.DaoProductoCaducable;
import modelo.producto.Ingrediente;
import modelo.producto.Producto;
import modelo.producto.ProductoCaducable;

import java.time.LocalDateTime;
import java.util.List;

public class ServicioProductos {

    public boolean addProduct(Producto newProduct) {
        DaoProducto daoProducto = new DaoProducto();
        boolean contains = !daoProducto.productExists(newProduct.getName());
        if (contains && newProduct.getPrice() > 0 && newProduct.getStock() >= 0) {
            return daoProducto.addProducto(newProduct);
        }
        return false;
    }

    public boolean removeProduct(String nombreProducto) {
        DaoProducto daoProducto = new DaoProducto();
        boolean exists = daoProducto.productExists(nombreProducto);
        if (exists) {
            return daoProducto.removeProducto(nombreProducto);
        }
        return false;
    }

    public boolean updateNameProduct(String nombreProducto, String newNombreProducto) {
        DaoProducto daoProducto = new DaoProducto();
        boolean exists = daoProducto.productExists(nombreProducto);
        if (exists) {
            return daoProducto.updateNameProducto(nombreProducto, newNombreProducto);
        }
        return false;
    }

    public boolean updatePriceProduct(String nombreProducto, double newPriceProducto) {
        DaoProducto daoProducto = new DaoProducto();
        boolean exists = daoProducto.productExists(nombreProducto);
        if (exists && newPriceProducto > 0) {
            return daoProducto.updatePriceProducto(nombreProducto, newPriceProducto);
        }
        return false;
    }

    public boolean updateStockProduct(String nombreProducto, int newStockProducto) {
        DaoProducto daoProducto = new DaoProducto();
        boolean exists = daoProducto.productExists(nombreProducto);
        if (exists && newStockProducto >= 0) {
            return daoProducto.updateStockProducto(nombreProducto, newStockProducto);
        }
        return false;
    }

    public List<Producto> showAllProducts() {
        DaoProducto daoProducto = new DaoProducto();
        return daoProducto.showAllProducts();
    }

    public Producto getProductoLista(String productoAgregarCarrito) {
        DaoProducto daoProducto = new DaoProducto();
        return daoProducto.getProductoLista(productoAgregarCarrito);
    }

    public boolean existProduct(String nombreProducto) {
        DaoProducto daoProducto = new DaoProducto();
        return daoProducto.productExists(nombreProducto);
    }

    public boolean addProductCaducable(ProductoCaducable productoCaducable) {
        DaoProductoCaducable daoProductoCaducable = new DaoProductoCaducable();
        boolean exist = !existProduct(productoCaducable.getName());
        boolean caducidad = LocalDateTime.parse(productoCaducable.getCaducidad().toString())
                .isAfter(LocalDateTime.now());
        boolean sePuede = false;
        if (exist && caducidad) {
            return daoProductoCaducable.addProductoCaducable(productoCaducable);
        }
        return false;
    }


    public List<Producto> showAllProductsSinCaducados() {
        DaoProducto daoProducto = new DaoProducto();
        return daoProducto.showAllProductosSinCaducables();
    }

    public List<Producto> showAllProductsOrdenadosName() {
        DaoProducto daoProducto = new DaoProducto();
        return daoProducto.showAllProductsSortedName();
    }

    public List<Producto> showAllProductsConIngredientes() {
        DaoProducto daoProducto = new DaoProducto();
        return daoProducto.showAllProductsConIngrediente();
    }

    public boolean addIngredienteAlProducto(String nombreProducto, Ingrediente nuevoIngrediente) {
        DaoProducto daoProducto = new DaoProducto();
        int indexProduct = daoProducto.indexProduct(nombreProducto);
        boolean productoTieneEseIngrediente = false;
        if (indexProduct >= 0) {
            productoTieneEseIngrediente =
                    !daoProducto.ingredienteExisteEnProducto(nuevoIngrediente, nombreProducto);
            if (productoTieneEseIngrediente) {
                return daoProducto.addIngredienteAlProducto(nuevoIngrediente, nombreProducto);
            }
        }
        return false;
    }

    public List<Producto> showAllProductsSinAlergiasCliente(String dniClient) {
        DaoProducto daoProducto = new DaoProducto();
        return daoProducto.showAllProductsSinAlergiasCliente(dniClient);
    }

    public List<String> showAllProductsSortedCantidadComprada() {
        DaoProducto daoProducto = new DaoProducto();
        return daoProducto.showCantidadProductosOrdenadaPorLaMasComprada();
    }
}
