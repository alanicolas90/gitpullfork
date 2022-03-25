package constantes;

public class Constantes {

    private Constantes() {
    }


    // MENU PRINCIPAL
    public static final String HASTA_LA_PROXIMA = "Hasta la proxima";
    public static final String MENU_ELEGIR_USUARIO =
            "Elija como desea entrar: \n" + "1) Administrador\n" + "2) Cliente\n\n" + "0) Exit";
    public static final String MENU_ADMIN = "Bienvenido, ahora elija lo que quiera gestionar ADMIN.\n"
            + "1) Administrar productos\n" + "2) Administrar clientes\n\n" + "0) Exit";
    public static final String BIENVENIDA = "Welcome to MERCAMOÑAS\n";

    // CLIENTES ADMINISTRADOR
    public static final String ERROR = "ERROR";
    public static final String DIME_EL_DNI_DEL_USUARIO = "Dime el DNI del usuario";
    public static final String DIME_EL_NUEVO_DNI_DEL_CLIENTE = "Dime el nuevo DNI del cliente";
    public static final String EL_DNI_SE_HA_CAMBIADO_CORRECTAMENTE =
            "El DNI se ha cambiado correctamente";
    public static final String ERROR_NO_SE_HA_PODIDO_HACER_LOS_CAMBIOS =
            "Error, no se ha podido hacer los cambios";
    public static final String DIME_EL_NUEVO_NOMBRE_DEL_CLIENTE = "Dime el nuevo nombre del cliente";
    public static final String EL_NOMBRE_HA_SIDO_CAMBIADO_CON_SUCCESS =
            "El nombre ha sido cambiado con éxito";
    public static final String DIME_EL_NUEVO_APELLIDO_DEL_CLIENTE =
            "Dime el nuevo apellido del cliente";
    public static final String EL_APELLIDO_SE_HA_CAMBIADO_CON_SUCCESS =
            "El apellido se ha cambiado con éxito.";
    public static final String ERROR_NO_SE_HA_PODIDO_ELIMINAR_AL_CLIENTE =
            "Error, no se ha podido eliminar al cliente.";
    public static final String EL_USUARIO_HA_SIDO_ELIMINADO_CON_SUCCESS =
            "El usuario ha sido eliminado con éxito";
    public static final String DIME_EL_DNI_DEL_CLIENTE_QUE_DESEA_BORRAR =
            "Dime el DNI del cliente que desea borrar";
    public static final String LO_SENTIMOS_NO_SE_HA_PODIDO_AGREGAR_AL_CLIENTE =
            "Lo sentimos, no se ha podido agregar al cliente.";
    public static final String SE_HA_AGREGADO_EL_CLIENTE_CON_SUCCESS =
            "Se ha agregado el cliente con éxito";
    public static final String DIME_EL_DNI_DEL_CLIENTE = "Dime el DNI del cliente";
    public static final String DIME_EL_NOMBRE_DEL_CLIENTE = "Dime el nombre del cliente";
    public static final String MENU_CLIENTE_ADMIN =
            "Seleccione lo que quieres hacer:\n" + "1) Crear cliente nuevo\n"
                    + "2) Crear cliente nuevo pero con descuento\n" + "3) Eliminar cliente\n"
                    + "4) Cambiar dni cliente\n" + "5) cambiar nombre cliente\n"
                    + "6) ver todos los clientes \n" + "7) Ver todos los clientes ordenados por dni\n"
                    + "8) Ver todos los clientes ordenados por el dinero gastado en total\n" + "0) Salir \n";


    // PRODUCTOS ADMINISTRADOR
    public static final String HASTA_LA_NEXT = "Hasta la próxima.";
    public static final String OPTIONS_PRODUCTS =
            "1) Añadir producto nuevo\n" + "2) Añadir producto caducable\n" + "3) Eliminar producto\n"
                    + "4) Cambiar nombre de un producto\n" + "5) Cambiar precio de un producto\n"
                    + "6) Cambiar stock de un producto\n" + "7) Añadir ingrediente a un producto\n"
                    + "8) Ver todos los productos\n" + "9) Ver todos los productos ordenados por nombre\n"
                    + "10) Ver todos los productos mas comprado ordenado por cantidad\n"
                    + "11) Ver solo los productos que tienen ingredientes\n" + "0) Salir";

    public static final String COMO_QUIERES_QUE_SE_LLAME_EL_NUEVO_PRODUCTO =
            "Como quieres que se llame el nuevo producto? ";
    public static final String CUANTO_STOCK_TIENES_DEL_PRODUCTO =
            "Cuanto stock tienes del producto? ";
    public static final String DIME_EL_PRECIO_DEL_PRODUCTO = "Dime el precio del producto: ";
    public static final String PRODUCT_ADD_SUCCESS = "El producto ha sido agregado con éxito";
    public static final String ERROR_TRY_AGAIN =
            "Lo sentimos ha ocurrido un error, por favor inténtelo de nuevo.";
    public static final String QUE_PRODUCTO_DESEA_ELIMINAR = "Que producto desea eliminar? ";
    public static final String SUCCESS_DELETE = "El producto ha sido eliminado con éxito.";
    public static final String A_QUE_PRODUCTO_DESEA_CAMBIARLE_EL_NOMBRE =
            "A que producto desea cambiarle el nombre? ";
    public static final String CUAL_ES_EL_NUEVO_NOMBRE_DEL_PRODUCTO =
            "Cual es el nuevo nombre del producto? ";
    public static final String NAME_CHANGE_SUCCESS = "El nombre ha sido cambiado con éxito.";
    public static final String A_QUE_PRODUCTO_DESEA_CAMBIARLE_EL_PRECIO =
            "A que producto desea cambiarle el precio? ";
    public static final String CUAL_ES_EL_NUEVO_PRECIO_DEL_PRODUCTO =
            "Cual es el nuevo precio del producto? ";
    public static final String PRICE_SUCCESS_CHANGE = "El precio ha sido cambiado con éxito.";
    public static final String CUAL_ES_EL_NUEVO_STOCK_DEL_PRODUCTO =
            "Cual es el nuevo stock del producto?";
    public static final String STOCK_CHANGE_SUCCESS = "El stock ha sido cambiado con éxito";
    public static final String BYE_BYE = "Hasta la próxima.";
    public static final String A_QUE_PRODUCTO_DESEA_CAMBIARLE_EL_STOCK =
            "A que producto desea cambiarle el stock? ";
    public static final String ERROR_POR_FAVOR_TRY_AGAIN = "Error, por favor inténtelo de nuevo.";
    public static final String DIME_EL_DESCUENTO_QUE_TIENE_NUMEROS_ENTEROS = "Dime el descuento que tiene (numeros enteros)";
    public static final String A_QUE_PRODUCTO_LE_QUIERES_INCLUIR_INGREDIENTE = "A que producto le quieres incluir ingrediente";
    public static final String QUE_INGREDIENTE_LE_QUIERE_INTRODUCIR = "Que ingrediente le quiere introducir";
    public static final String SE_HA_AGREGADO_CON_EXITO = "Se ha agregado con éxito";
    public static final String SE_HA_ANADIDO_CORRECTAMENTE = "Se ha añadido correctamente";
    public static final String DIME_EL_NOMBRE_DEL_PRODUCTO = "Dime el nombre del producto";
    public static final String DIME_EL_PRECIO_DE = "Dime el precio de ";
    public static final String DIME_AHORA_LA_CANTIDAD_DEL_STOCK = "Dime ahora la cantidad del stock";
    public static final String DIME_LA_FECHA_DE_CADUCIDAD_YYYY_MM_DD_THH_MM_SS = "Dime la fecha de caducidad (yyyy-mm-ddThh:mm:ss)";


    // CLIENTES CLIENTE AJUSTES
    public static final String MENUCLIENTEAJUSTES = "Que desea hacer?\n" + "1) Cambiar nombre.\n"
            + "2) Agregar tarjeta\n" + "3) Eliminar tarjeta\n" + "4) Agregar money\n"
            + "5) Mostrar datos del usuario\n" + "6) Mostrar compras antiguas\n"
            + "7) Añadir alergeno\n" + "8) Gasto total en la tienda\n" + "0) Exit";

    public static final String USTED_NO_HA_COMPRADO_NUNCA_EN_LA_TIENDA = "Usted no ha comprado nunca en la tienda";
    public static final String DIME_EL_INGREDIENTE_AL_QUE_TIENES_ALERGIA = "Dime el ingrediente al que tienes alergia";
    public static final String HA_SIDO_UN_EXITO = "Ha sido un éxito";
    public static final String COMO_SE_LLAMA_EL_MONEDERO = "Como se llama el monedero?";
    public static final String ELIMINADO_CON_EXITO = "Eliminado con éxito";
    public static final String ADD_MONEDERO = "Añadir monedero";
    public static final String COMO_DESEA_QUE_SE_LLAME_EL_MONEDERO = "Como desea que se llame el monedero?";
    public static final String CUANTO_DINERO_DESEA_INTRODUCIR_AL_MONEDERO = "Cuanto dinero desea introducir al monedero?";
    public static final String A_QUE_TARJETA_DESEA_AGREGARLE_DINERO = "A que tarjeta desea agregarle dinero?";
    public static final String CUANTO_DINERO_DESEA_AGREGARLE = "Cuanto dinero desea agregarle?";
    public static final String LOGRADO_CON_EXITO = "Logrado con éxito";


    //CLIENTE CLIENTE MENU


    public static final String DIGAME_SU_DNI = "Dígame su dni";
    public static final String BIENVENIDO = "BIENVENIDO";
    public static final String ERROR_INTENTELO_DE_NUEVO = "Error inténtelo de nuevo";
    public static final String MENUCLIENTE = "Que desea hacer?\n" + "1) Ajustes de usuario\n" + "2) Comprar\n\n" + "0) Exit";


    //CLIENTE CLIENTE COMPRA

    public static final String MENU_CLIENTE_COMPRA = "Que deseas hacer\n" + "1) Añadir producto al carrito\n"
            + "2) Eliminar un producto del carrito\n" + "3) Pagar por el carrito\n"
            + "4) Mostrar listado de productos sin mis alergias\n\n" + "0) Exit";
    public static final String MUCHAS_GRACIAS_Y_HASTA_LUEGO = "Muchas gracias y hasta luego.";
    public static final String EL_PRECIO_DE_TU_CARRITO_ES = "El precio de tu carrito es:";
    public static final String CON_QUE_TARJETA_DESEA_PAGAR = "Con que tarjeta desea pagar?";
    public static final String PONGA_EL_NOMBRE_DE_LA_TARJETA = "Ponga el nombre de la tarjeta:";
    public static final String DIME_EL_PRODUCTO_QUE_DESEA_ELIMINAR_DEL_CARRITO = "Dime el producto que desea eliminar del carrito";
    public static final String ELIJA_UN_PRODUCTO_DE_ESTA_LISTA = "Elija un producto de esta lista:";
    public static final String QUE_PRODUCTO_DESEA_AGREGAR_AL_CARRITO = "que producto desea agregar al carrito?";
    public static final String CUANTA_CANTIDAD_QUIERE = "cuanta cantidad quiere?";
    public static final String EL_PRODUCTO_NO_EXISTE = "El producto no existe";


}
