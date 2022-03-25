package modelo.cliente;

import lombok.Getter;
import modelo.Clone;
import modelo.producto.Ingrediente;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
public class ClienteNormal extends Cliente implements Clone<ClienteNormal> {

    public ClienteNormal(){
        super();
        this.type="ClienteNormal";
    }

    public ClienteNormal(String dni) {
        super(dni);
        this.type="ClienteNormal";
    }

    public ClienteNormal(String dni, String nombre, Set<Monedero> monederos, List<Ingrediente> alergenos, List<LineaCompra> carrito, List<List<LineaCompra>> buyHistory) {
        super(dni, nombre, monederos, alergenos, carrito, buyHistory);
        this.type="ClienteNormal";
    }

    public ClienteNormal(String dni, String nombre) {
        super(dni, nombre);
        this.type="ClienteNormal";
    }

    public ClienteNormal(String dni, String nombre, Set<Monedero> monederos, List<LineaCompra> carrito, List<List<LineaCompra>> buyHistory) {
        super(dni, nombre, monederos, carrito, buyHistory);
        this.type="ClienteNormal";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ClienteNormal that = (ClienteNormal) o;
        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }

    @Override
    public String toString() {

        return super.toString();
    }

    @Override
    public ClienteNormal clone() {
        return new ClienteNormal(this.getDni(), this.getNombre(), this.getMonederos(), this.getAlergenos(),
                this.getCarrito(), this.getBuyHistory());
    }
}
