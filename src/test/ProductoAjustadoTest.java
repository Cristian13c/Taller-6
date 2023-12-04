package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import uniandes.dpoo.taller2.modelo.Ingrediente;
import uniandes.dpoo.taller2.modelo.ProductoAjustado;
import uniandes.dpoo.taller2.modelo.ProductoMenu;

class ProductoAjustadoTest
{

	@Test
	void testProductoAjustado()
	{
		ProductoMenu base = new ProductoMenu("Hamburguesa", 10000);
        ProductoAjustado productoAjustado = new ProductoAjustado(base);

        assertNotNull(productoAjustado);
        assertEquals("Hamburguesa", productoAjustado.getNombre());
        assertEquals(10000, productoAjustado.getPrecio());
    }


    @Test
    public void testGetPrecio() {
        ProductoMenu base = new ProductoMenu("Hamburguesa", 10000);
        ProductoAjustado productoAjustado = new ProductoAjustado(base);

        Ingrediente ingrediente1 = new Ingrediente("Queso", 2000);
        Ingrediente ingrediente2 = new Ingrediente("Tomate", 1000);

        productoAjustado.agregarOEliminarIngrediente(ingrediente1);
        productoAjustado.agregarOEliminarIngrediente(ingrediente2);

        assertEquals(13000, productoAjustado.getPrecio());
    }
    @Test
    public void testGenerarTextoFactura() {
        ProductoMenu base = new ProductoMenu("Hamburguesa", 10000);
        ProductoAjustado productoAjustado = new ProductoAjustado(base);
        Ingrediente ingrediente1 = new Ingrediente("Queso", 2000);
        Ingrediente ingrediente2 = new Ingrediente("Tomate", 1000);
        productoAjustado.agregarOEliminarIngrediente(ingrediente1);
        productoAjustado.agregarOEliminarIngrediente(ingrediente2);
        String textoFactura = productoAjustado.generarTextoFactura();
        assertTrue(textoFactura.contains("Hamburguesa"));
        assertTrue(textoFactura.contains("Queso"));
        assertTrue(textoFactura.contains("Tomate"));
        assertTrue(textoFactura.contains("2000"));
        assertTrue(textoFactura.contains("1000"));
        assertTrue(textoFactura.contains("13000"));
    }
}
