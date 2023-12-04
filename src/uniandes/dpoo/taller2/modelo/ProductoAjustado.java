package uniandes.dpoo.taller2.modelo;

import java.util.ArrayList;

public class ProductoAjustado implements Producto
{
	// Atributos
	ProductoMenu base;
	ArrayList<Ingrediente> agregados = new ArrayList<Ingrediente>();
	ArrayList<Ingrediente> eliminados = new ArrayList<Ingrediente>();
	

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
		for (Ingrediente ingrediente:agregados)
		{
			precio += ingrediente.getCostoAdicional();
		}
		return precio;
	}

	@Override
	public String getNombre()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generarTextoFactura()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
