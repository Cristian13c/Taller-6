package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import uniandes.dpoo.taller2.modelo.Combo;
import uniandes.dpoo.taller2.modelo.Producto;
import uniandes.dpoo.taller2.modelo.ProductoMenu;

class ComboTest
{

	@Test
	void testCombo()
	{
		Combo combo = new Combo(10.0, "Combo Hamburguesa");
		assertEquals("Combo Hamburguesa", combo.getNombre());
	}

	@Test
	void testAgregarItemACombo()
	{
		Combo combo = new Combo(10.0, "Combo Hamburguesa");
		Producto producto1 = new ProductoMenu("Hamburguesa", 10000);
		Producto producto2 = new ProductoMenu("Gaseosa", 3000);
		combo.agregarItemACombo(producto1);
		combo.agregarItemACombo(producto2);
		int precioEsperado = (int) ((10000 + 3000) * (1 - 0.10));
		assertEquals(precioEsperado, combo.getPrecio());
	}

	@Test
	void testGenerarTextoFactura()
	{
		Combo combo = new Combo(10.0, "Combo Hamburguesa");
		Producto producto1 = new ProductoMenu("Hamburguesa", 10000); // P
		combo.agregarItemACombo(producto1);
		Producto producto2 = new ProductoMenu("Gaseosa", 3000);
		combo.agregarItemACombo(producto2);
		int precioEsperado = (int) (13000 * (1 - 0.10));

		String textoEsperado = "Combo Hamburguesa " + precioEsperado + "$";
		textoEsperado = textoEsperado + "\r" + "  -"
				+ " Hamburguesa, con un precio de: 10000$\r\n"
				+ "  - Gaseosa, con un precio de: 3000$";
		assertEquals(textoEsperado, combo.generarTextoFactura());
	}

}
