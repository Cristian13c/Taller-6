package uniandes.dpoo.taller2.modelo;

@SuppressWarnings("serial")
public class ProductoRepetidoException extends HamburguesaException {
    public ProductoRepetidoException(String producto) {
        super("Error: Producto repetido - " + producto);
    }
}
