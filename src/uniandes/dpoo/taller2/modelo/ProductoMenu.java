package uniandes.dpoo.taller2.modelo;

public class ProductoMenu implements Producto
{
	// Atributos
	private String nombre;
	private int precioBase;

	// Constructor
	public ProductoMenu(String nombre, int precioBase)
	{
		this.nombre = nombre;
		this.precioBase = precioBase;
	}

	@Override
	public int getPrecio()
	{
		// Retorna el precio del producto
		return this.precioBase;
	}

	@Override
	public String getNombre()
	{
		// Retorna el nombre del producto
		return this.nombre;
	}

	@Override
	public String generarTextoFactura()
	{
		String textoFactura= getNombre()+ " "+ Integer.toString(precioBase)+ "$";
		return textoFactura;
	}
	
}
