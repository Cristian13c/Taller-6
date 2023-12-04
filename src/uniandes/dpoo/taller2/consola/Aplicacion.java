package uniandes.dpoo.taller2.consola;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import uniandes.dpoo.taller2.modelo.Combo;
import uniandes.dpoo.taller2.modelo.HamburguesaException;
import uniandes.dpoo.taller2.modelo.Ingrediente;
import uniandes.dpoo.taller2.modelo.Producto;
import uniandes.dpoo.taller2.modelo.ProductoMenu;
import uniandes.dpoo.taller2.modelo.Restaurante;

public class Aplicacion
{
	// Atributos
	static Restaurante restaurante;

	// Metodos
	public static void main(String[] args) throws IOException, HamburguesaException
	{
		// Metodo main, llama la funcion para cargar la informacion y inicializa el menu
		restaurante = new Restaurante();

		File Ingredientes = new File("data/ingredientes.txt");
		File Menu = new File("data/menu.txt");
		File Combos = new File("data/combos.txt");

		restaurante.cargarInformacionRestaurante(Ingredientes, Menu, Combos);

		boolean continuar = true;

		while (continuar)
		{
			mostrarMenu();
		}
	}

	public static void mostrarMenu() throws IOException
	{
		// Muestra el menu de la aplicacion y recibe una opcion mediante la consola.

		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Mostrar el menu");
		System.out.println("2. Iniciar un nuevo pedido");
		System.out.println("3. Agregar un elemento a un pedido");
		System.out.println("4. Cerrar un pedido y guardad la factura");
		System.out.println("5. Consultar la informacion de un pedido dado su id");

		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
		String entradaStr = entrada.readLine();

		int opcionSeleccionada = Integer.parseInt(entradaStr);
		ejecutarOpcion(opcionSeleccionada);
	}

	public static void ejecutarOpcion(int opcionSeleccionada) throws IOException
	{
		try
		{

			if (opcionSeleccionada == 1)
				menu(1);
			else if (opcionSeleccionada == 2)
				iniciarNuevoPedido();
			else if (opcionSeleccionada == 3)
				agregarElementoPedido();
			else if (opcionSeleccionada == 4)
				restaurante.cerrarYGuardarPedido();
			// else if (opcionSeleccionada == 5 && calculadora != null)

		} catch (NumberFormatException e)
		{
			System.out.println("Debe seleccionar uno de los números de las opciones.");
		}

	}

	private static void menu(int opcionSeleccionada)
	{
		if (opcionSeleccionada == 1)
		{
			ArrayList<ProductoMenu> menuBase = restaurante.getMenuBase();

			System.out.println("///MENU///");
			System.out.println("///Productos base///");

			int i = 0;

			for (Producto producto : menuBase)
			{
				i++;
				String nombre = producto.getNombre();
				int precioBase = producto.getPrecio();
				String numero = Integer.toString(i);
				System.out.println(numero + ". " + nombre + ". precio: " + Integer.toString(precioBase));
			}

		} else if (opcionSeleccionada == 2)
		{
			ArrayList<Combo> combos = restaurante.getCombos();

			System.out.println("///MENU///");
			System.out.println("///Combos///");

			int i = 0;

			for (Producto producto : combos)
			{
				i++;
				String nombre = producto.getNombre();
				int precioBase = producto.getPrecio();
				String numero = Integer.toString(i);
				System.out.println(numero + ". " + nombre + ". precio: " + Integer.toString(precioBase));
			}

		}
	}

	public static void iniciarNuevoPedido() throws IOException
	{
		// Le pregunta al usuario los datos del cliente y inicia un nuevo pedido

		System.out.println("Ingrese el nombre del cliente");
		BufferedReader entradaNombre = new BufferedReader(new InputStreamReader(System.in));
		String nombreCliente = entradaNombre.readLine();

		System.out.println("Ingrese la direccion del cliente");
		BufferedReader entradaDireccion = new BufferedReader(new InputStreamReader(System.in));
		String direccionCliente = entradaDireccion.readLine();

		restaurante.iniciarPedido(nombreCliente, direccionCliente);

		System.out.println("SE HA INICIADO DE FORMA EXITOSA UN PEDIDO");
	}

	public static void agregarElementoPedido() throws IOException
	{
		boolean continuarPedido = true;
		while (continuarPedido)
		{
			ArrayList<Integer> listaIngredientes = new ArrayList<Integer>();
			System.out.println("///OPCIONES DE PRODUCTOS///");
			System.out.println("1. Producto de menu");
			System.out.println("2. Combos");
			System.out.println("0. Atras");

			BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
			String entradaStr = entrada.readLine();
			int opcionMenu = Integer.parseInt(entradaStr);

			menu(opcionMenu);
			System.out.println("---Seleccione el producto---");

			BufferedReader entradaProducto = new BufferedReader(new InputStreamReader(System.in));
			String productuctoStr = entradaProducto.readLine();
			int producto = Integer.parseInt(productuctoStr);

			if (opcionMenu == 1)
			{

				System.out.println("---¿Desea agregar algun ingrediente?---");
				System.out.println("1. si");
				System.out.println("2. no");

				BufferedReader agregar = new BufferedReader(new InputStreamReader(System.in));
				int agregar1 = Integer.parseInt(agregar.readLine());

				if (agregar1 == 2)
				{

					restaurante.agregarProducto(producto, 2, listaIngredientes);
				} else if (agregar1 == 1)
				{
					boolean continuarIngredientes = true;
					while (continuarIngredientes)
					{
						System.out.println("///OPCIONES DE INGREDIENTES///");
						ArrayList<Ingrediente> ingredientes = restaurante.getIngredientes();
						int i = 1;
						for (Ingrediente ingrediente : ingredientes)
						{
							i++;
							String nombre = ingrediente.getNombre();
							int costoAdicional = ingrediente.getCostoAdicional();
							String numero = Integer.toString(i);
							System.out.println(numero + ". " + nombre + ". precio: " + Integer.toString(costoAdicional));
						}
						System.out.println("0. Atras");
						System.out.println("---Seleccione un ingrediente o oprima 0 para regresar---");
						BufferedReader opcionIngrediente = new BufferedReader(new InputStreamReader(System.in));
						String opcionIngredienteStr = opcionIngrediente.readLine();

						int opcionIngredienteInt = Integer.parseInt(opcionIngredienteStr);
						if (opcionIngredienteInt != 0)
						{
							listaIngredientes.add(opcionIngredienteInt);
							
						} else
						{
							restaurante.agregarProducto(producto, 3, listaIngredientes);
							continuarIngredientes = false;
						}

					}
				}
			} else if (opcionMenu == 2)
			{
				menu(opcionMenu);
				restaurante.agregarProducto(producto, opcionMenu, listaIngredientes);
			} else
			{
				continuarPedido = false;
			}

		}
	}
		
		
}
