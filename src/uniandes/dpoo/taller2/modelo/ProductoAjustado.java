package uniandes.dpoo.taller2.modelo;

import java.util.ArrayList;

public class ProductoAjustado implements Producto
{
	// Atributos
	private ProductoMenu base;
	private ArrayList<Ingrediente> agregados = new ArrayList<Ingrediente>();
	private ArrayList<Ingrediente> eliminados = new ArrayList<Ingrediente>();

	// Constructor
	public ProductoAjustado(ProductoMenu base)
	{
		this.base = base;
	}

	// Metodos
	public void agregarOEliminarIngrediente(Ingrediente ingrediente)
	{
		agregados.add(ingrediente);
	}

	@Override
	public int getPrecio()
	{
		int precio = base.getPrecio();
		if (agregados.size() > 0)
		{
			for (Ingrediente ingrediente : agregados)
			{
				precio += ingrediente.getCostoAdicional();
			}
		}
		return precio;
	}

	@Override
	public String getNombre()
	{
		return base.getNombre();
	}

	@Override
	public String generarTextoFactura()
	{

		String textoFactura = getNombre() + " " + Integer.toString(getPrecio())
				+ "$";
		if (agregados.size() != 0)
		{
			for (Ingrediente agregado : agregados)
			{
				textoFactura = textoFactura + "\r"
						+ "  -Ingredientes Agregados:\r" + "   -"
						+ agregado.getNombre() + " "
						+ Integer.toString(agregado.getCostoAdicional()) + "$";

			}
		}
		return textoFactura;
	}
}
