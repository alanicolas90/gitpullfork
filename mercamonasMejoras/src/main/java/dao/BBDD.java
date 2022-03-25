package dao;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import config.Configuration;
import gsonutils.RuntimeTypeAdapterFactory;
import lombok.extern.log4j.Log4j2;
import modelo.cliente.Cliente;
import modelo.cliente.ClienteDescuento;
import modelo.cliente.ClienteNormal;
import modelo.producto.Producto;
import modelo.producto.ProductoCaducable;
import modelo.producto.ProductoNormal;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Log4j2
public class BBDD {

    private Gson gson;

    private Configuration configuration;

    public BBDD() {

        RuntimeTypeAdapterFactory<Cliente> adapterClient =
                RuntimeTypeAdapterFactory
                        .of(Cliente.class, "type", true)
                        .registerSubtype(ClienteNormal.class)
                        .registerSubtype(ClienteDescuento.class);

        RuntimeTypeAdapterFactory<Producto> adapterProduct =
                RuntimeTypeAdapterFactory
                        .of(Producto.class, "type", true)
                        .registerSubtype(ProductoNormal.class)
                        .registerSubtype(ProductoCaducable.class);


        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class,
                        (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) ->
                                LocalDateTime.parse(json.getAsJsonPrimitive().getAsString()))
                .registerTypeAdapter(LocalDateTime.class,
                        (JsonSerializer<LocalDateTime>) (localDateTime, type, jsonSerializationContext) ->
                                new JsonPrimitive(localDateTime.toString()))
                .registerTypeAdapter(LocalDate.class,
                        (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) ->
                                LocalDate.parse(json.getAsJsonPrimitive().getAsString()))
                .registerTypeAdapter(LocalDate.class,
                        (JsonSerializer<LocalDate>) (localDateTime, type, jsonSerializationContext) ->
                                new JsonPrimitive(localDateTime.toString()))
                .registerTypeAdapterFactory(adapterClient)
                .registerTypeAdapterFactory(adapterProduct)
                .create();


        this.configuration = Configuration.getInstance();
    }

    public BBDD(Gson gson, Configuration configuracion) {
        this.gson = gson;
        this.configuration = configuracion;
    }


//          CLIENTES          CLIENTES          CLIENTES          CLIENTES          CLIENTES

    public Map<String,Cliente> loadClientes() {
        Type userListType = new TypeToken<LinkedHashMap<String, Cliente>>() {
        }.getType();
        LinkedHashMap<String, Cliente> clientes = null;
        try {
            clientes = gson.fromJson(
                    new FileReader(Configuration.getInstance().getClientsSource()),
                    userListType);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
        }
        return clientes;
    }

    public boolean saveClientes(Map<String,Cliente> clientes) {
        try (FileWriter w = new FileWriter(Configuration.getInstance().getClientsSource())) {
            gson.toJson(clientes, w);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

//          PRODUCTOS          PRODUCTOS          PRODUCTOS          PRODUCTOS          PRODUCTOS

    public List<Producto> loadProducto() {
        Type userListType = new TypeToken<ArrayList<Producto>>() {
        }.getType();
        List<Producto> productos = null;
        try {
            productos = gson.fromJson(
                    new FileReader(Configuration.getInstance().getProductsSource()),
                    userListType);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
        }
        return productos;
    }

    public boolean saveProducto(List<Producto> productos) {
        try (FileWriter w = new FileWriter(Configuration.getInstance().getProductsSource())) {
            gson.toJson(productos, w);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }


//    static LinkedHashMap<String, Cliente> clientes = new LinkedHashMap<>();
//    static ArrayList<Producto> productos = new ArrayList<>();
//
//
//
//    static {
//        clientes.put("123", new ClienteNormal("123", "alan"));
//        clientes.put("111", new ClienteNormal("111", "alain"));
//        clientes.put("233", new ClienteNormal("233", "alean"));
//        clientes.put("333", new ClienteNormal("333", "alaan"));
//        clientes.put("223", new ClienteNormal("223", "allan"));
//
//        productos.add(new Producto("acheyese", 2.5, 200));
//        productos.add(new Producto("peperoni", 0.5, 200));
//        productos.add(new Producto("tangadetuabuela", 150, 200));
//        productos.add(new Producto("pimienta", 1.25, 200));
//        productos.add(new Producto("sal", 0.7, 200));
//
//        productos.get(productos.indexOf(new Producto("sal"))).getIngredientes()
//                .add(new Ingrediente("cocaina"));
//
//        productos.add(new ProductoCaducable("pescado", 0.7, 200, LocalDateTime.now().plusSeconds(20)));
//
//        clientes.get("111").getMonederos().add(new Monedero("ale", 1000));
//
//
//        clientes.put("000", new ClienteDescuento("000", "pepe", 25));
//        clientes.get("000").getMonederos().add(new Monedero("ale", 1000));
//
//
//    }
}
