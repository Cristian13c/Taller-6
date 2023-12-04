package uniandes.dpoo.taller2.modelo;

import java.util.ArrayList;

public class Combo implements Producto
{
	// Atributos
	private double descuento;
	private String nombreCombo;
	private ArrayList<ProductoMenu> itemsCombo = new ArrayList<ProductoMenu>();

	// Constructor
	public Combo(double descuentos, String nombreCombo)
	{
		this.descuento = descuentos;
		this.nombreCombo = nombreCombo;

	}

	// Metodos
	public void agregarItemACombo(Producto itemCombo)
	{

		itemsCombo.add((ProductoMenu) itemCombo);
	}

	@Override
	public int getPrecio()
	{
		int precio = 0;
		ProductoMenu item = null;
		int i = 0;
		while (i < itemsCombo.size())
		{
			i++;
			item = itemsCombo.get(i-1);
			precio += item.getPrecio();
		}
		precio = (int) (precio - (precio * (descuento / 100)));
		return precio;
	}

	@Override
	public String getNombre()
	{
		// TODO Auto-generated method stub
		return nombreCombo;
	}

	@Override
	public String generarTextoFactura()
	{
		// TODO Auto-generated method stub

		return null;
	}

}
