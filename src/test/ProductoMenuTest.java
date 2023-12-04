package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import uniandes.dpoo.taller2.modelo.ProductoMenu;

class ProductoMenuTest
{

	@Test
	void testProductoMenu()
	{
		ProductoMenu producto = new ProductoMenu("Hamburguesa", 10000);

        assertEquals("Hamburguesa", producto.getNombre());
        assertEquals(10000, producto.getPrecio());
	}
	@Test
    public void testConstructorConPrecioNegativo() {
        assertThrows(IllegalArgumentException.class, () -> new ProductoMenu("Hamburguesa", -10000));
    }
	@Test
	void testGenerarTextoFactura()
	{
		ProductoMenu producto = new ProductoMenu("Hamburguesa", 10000);
        String textoFactura = producto.generarTextoFactura();
        assertEquals("Hamburguesa 10000$", textoFactura);
	}

}
