package modelo.producto;

import java.util.List;
import java.util.stream.Collectors;

public class ProductoNormal extends Producto {

    public ProductoNormal() {
        super();
        this.type = "ProductoNormal";
    }

    public ProductoNormal(String name, double price, int stock, List<Ingrediente> ingredientes) {
        super(name, price, stock, ingredientes);
        this.type = "ProductoNormal";
    }

    public ProductoNormal(String name) {
        super(name);
        this.type = "ProductoNormal";
    }

    public ProductoNormal(String name, double price, int stock) {
        super(name, price, stock);
        this.type = "ProductoNormal";
    }

    @Override
    public Producto clone() {
        return new ProductoNormal(this.getName(), this.getPrice(), this.getStock(),
                this.getIngredientes().stream().collect(Collectors.toUnmodifiableList()));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
