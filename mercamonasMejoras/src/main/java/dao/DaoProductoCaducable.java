package dao;

import modelo.producto.Producto;
import modelo.producto.ProductoCaducable;

import java.util.List;

public class DaoProductoCaducable {

  private final BBDD db;

  public DaoProductoCaducable() {
    this.db = new BBDD();
  }

  public boolean addProductoCaducable(ProductoCaducable productoCaducable) {

    List<Producto> productos = db.loadProducto();

    productos.add(productoCaducable);

    return db.saveProducto(productos);
  }
}
