package uniandes.dpoo.taller2.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Restaurante
{

	// Atributos
	private ArrayList<Combo> combos;
	private ArrayList<ProductoMenu> menuBase;
	private ArrayList<Ingrediente> ingredientes;
	private Pedido pedidoEnCurso;
	private ArrayList<Pedido> pedidos;
	private HashMap<String, ProductoMenu> tablaHashMenu = new HashMap<String, ProductoMenu>();

	// Constructor
	public Restaurante()
	{
		this.combos = new ArrayList<Combo>();
		this.menuBase = new ArrayList<ProductoMenu>();
		this.ingredientes = new ArrayList<Ingrediente>();
		this.pedidos = new ArrayList<Pedido>();
	}

	// Metodos
	public void iniciarPedido(String nombreCliente, String direccionCliente)
	{
		Pedido pedido = new Pedido(nombreCliente, direccionCliente);
		this.pedidoEnCurso = pedido;

	}

	public void cerrarYGuardarPedido()
	{
		File documento = new File("Factura");
		pedidoEnCurso.guardarFactura(documento);
	}

	public Pedido getPedidoEnCurso()
	{

		return pedidoEnCurso;
	}

	public ArrayList<ProductoMenu> getMenuBase()
	{

		return menuBase;
	}

	public ArrayList<Ingrediente> getIngredientes()
	{

		return ingredientes;
	}

	public ArrayList<Combo> getCombos()
	{

		return combos;
	}

	public void cargarInformacionRestaurante(File archivoIngredientes,
			File archivoMenu, File archivoCombos)
			throws IOException, HamburguesaException
	{
		// Carga la informacion de productos del restaurante
		cargarIngredientes(archivoIngredientes);
		cargarMenu(archivoMenu);
		cargarCombos(archivoCombos);
	}

	private void cargarIngredientes(File archivoIngredientes)
			throws IOException, IngredienteRepetidoException
	{
		// Carga los ingredientes
		FileReader fr = new FileReader(archivoIngredientes);
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(fr);

		String linea;

		while ((linea = br.readLine()) != null)
		{
			String[] informacion = linea.split(";");
			String nombre = informacion[0];
			int costoAdicional = Integer.valueOf(informacion[1]);
			if (ingredientes.stream()
					.anyMatch(ing -> ing.getNombre().equals(nombre)))
			{
				throw new IngredienteRepetidoException(nombre);
			}
			Ingrediente ingrediente = new Ingrediente(nombre, costoAdicional);
			ingredientes.add(ingrediente);
		}
		br.close();

	}

	private void cargarMenu(File archivoMenu)
			throws IOException, ProductoRepetidoException
	{
		// Carga los productos base del menu
		FileReader fr = new FileReader(archivoMenu);
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(fr);

		String linea;

		while ((linea = br.readLine()) != null)
		{
			String[] informacion = linea.split(";");
			String nombre = informacion[0];
			int precioBase = Integer.valueOf(informacion[1]);
			if (menuBase.stream()
					.anyMatch(ing -> ing.getNombre().equals(nombre)))
			{
				throw new ProductoRepetidoException(nombre);
			}
			ProductoMenu productoMenu = new ProductoMenu(nombre, precioBase);
			menuBase.add(productoMenu);
			tablaHashMenu.put(nombre, productoMenu);

		}

		br.close();
	}

	private void cargarCombos(File archivoCombos)
			throws NumberFormatException, IOException, ProductoRepetidoException
	{
		// Carga los combos
		FileReader fr = new FileReader(archivoCombos);
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(fr);

		String linea;

		while ((linea = br.readLine()) != null)
		{

			String[] informacion = linea.split(";");
			String nombre = informacion[0];
			String descuentoStr = informacion[1].replace("%", "");
			double descuento = Double.parseDouble(descuentoStr);
			Combo combo = new Combo(descuento, nombre);
			if (combos.stream().anyMatch(ing -> ing.getNombre().equals(nombre)))
			{
				throw new ProductoRepetidoException(nombre);
			}
			int i = 2;

			while (i < informacion.length)
			{
				ProductoMenu productoMenu = tablaHashMenu.get(informacion[i]);
				combo.agregarItemACombo(productoMenu);

				i++;

			}

			combos.add(combo);

		}

		br.close();
	}

	public void agregarProducto(int numeroProducto, int opcion, ArrayList<Integer> listaIngredientes)
	{
		if (opcion == 1)
		{
			try
			{
				pedidoEnCurso.agregarProducto(menuBase.get(numeroProducto - 1));
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		} else if (opcion == 2)
		{
			try
			{
				pedidoEnCurso.agregarProducto(combos.get(numeroProducto - 1));
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		} else if (opcion == 3)
		{
			ProductoAjustado producto = new ProductoAjustado(menuBase.get(numeroProducto - 1));
			for (int ingrediente : listaIngredientes)
			{
				producto.agregarOEliminarIngrediente(ingredientes.get(ingrediente));
				
			}
		}

	}
}
