package uniandes.dpoo.taller2.modelo;

public interface Producto
{
	abstract int getPrecio();
	abstract String getNombre();
	abstract String generarTextoFactura();
}
