package dao;

import modelo.ProductoCaducable;
import static dao.BBDD.productos;

public class DaoProductoCaducable {

  public void addProductoCaducable(ProductoCaducable productoCaducable) {
    productos.add(new ProductoCaducable(productoCaducable.getName(), productoCaducable.getPrice(),
        productoCaducable.getStock(), productoCaducable.getCaducidad()));
  }
}
