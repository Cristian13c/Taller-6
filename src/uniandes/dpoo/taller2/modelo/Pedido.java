package uniandes.dpoo.taller2.modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Pedido
{
	// Atributos
	static private int numeroPedidos = 0;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> itemsPedido = new ArrayList<Producto>();

	// Constructor
	public Pedido(String nombreCliente, String direccionCliente)
	{
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;

		Pedido.numeroPedidos++;
		idPedido = this.numeroPedidos;
	}

	// Metodos
	public int getIdPedido()
	{
		return idPedido;
	}

	public void agregarProducto(Producto nuevoItem) throws Exception
	{

		if (nuevoItem.getPrecio() + getPrecioNetoPedido() > 150000)
			throw new Exception(
					"Error: El valor total del pedido no puede superar 150.000 pesos.");
		itemsPedido.add(nuevoItem);

	}

	private int getPrecioNetoPedido()
	{
		int precioNeto = 0;
		if (itemsPedido.size() != 0)
		{
			for (Producto producto : itemsPedido)
			{
				precioNeto += producto.getPrecio();
			}
		}
		return precioNeto;
	}

	private int getPrecioTotalPedido()
	{

		int precioTotal = 0;
		for (Producto producto : itemsPedido)
		{
			precioTotal += producto.getPrecio();
		}
		return precioTotal;
	}

	private int getPrecioIVAPedido()
	{

		double precioIva = getPrecioNetoPedido() * (19.0 / 100.0);

		return (int) (precioIva);
	}

	public String generarTextoFactura()
	{
		String textofactura = "";
		textofactura = "Id: " + Integer.toString(idPedido) + "\r" + //
				"Nombre: " + nombreCliente + "\r" + "Dirección: "
				+ direccionCliente + "\r";
		if (itemsPedido.size() != 0)
		{
			for (Producto producto : itemsPedido)
			{
				textofactura += "\r" + "" + producto.generarTextoFactura();
			}
		}

		textofactura += "\r" + //
				"Precio neto: " + Integer.toString(getPrecioNetoPedido())
				+ "$\r" + "Precio Iva: "
				+ Integer.toString(getPrecioIVAPedido()) + "$\r" + //
				"Precio Total: " + Integer.toString(getPrecioTotalPedido())
				+ "$";

		return textofactura;
	}

	public void guardarFactura(File archivo)
	{
		try {
			FileWriter fw = new FileWriter(archivo);
			fw.write(generarTextoFactura());			

			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
