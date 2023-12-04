package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import uniandes.dpoo.taller2.modelo.*;

class PedidoTest
{

	@Test
	public void testGenerarTextoFactura() throws Exception {
        Pedido pedido = new Pedido("Cliente Test", "Dirección Test");

        ProductoMenu producto1 = new ProductoMenu("Hamburguesa", 10000);
        ProductoMenu producto2 = new ProductoMenu("Papas Fritas", 5000);

        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);

        String textoFactura = pedido.generarTextoFactura();

        assertTrue(textoFactura.contains("Id:"));
        assertTrue(textoFactura.contains("Cliente Test"));
        assertTrue(textoFactura.contains("Hamburguesa"));
        assertTrue(textoFactura.contains("Papas Fritas"));
        assertTrue(textoFactura.contains("Precio neto: 15000$"));
        assertTrue(textoFactura.contains("Precio Iva: 2850$")); 
        assertTrue(textoFactura.contains("Precio Total: 17850$")); 
    }

    @Test
    public void testGuardarFactura() throws Exception {
        Pedido pedido = new Pedido("Cliente Test", "Dirección Test");

        ProductoMenu producto1 = new ProductoMenu("Hamburguesa", 10000);
        ProductoMenu producto2 = new ProductoMenu("Papas Fritas", 5000);

        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);

        File tempFile = File.createTempFile("factura", ".txt");
        tempFile.deleteOnExit();

        pedido.guardarFactura(tempFile);

        String contenidoArchivo = Files.readString(Path.of(tempFile.getAbsolutePath()));

        String textoFactura = pedido.generarTextoFactura();
        assertEquals(textoFactura, contenidoArchivo);
    }

    @Test
    public void testExcepcionValorTotalPedido() {
        Pedido pedido = new Pedido("Cliente Test", "Dirección Test");

        ProductoMenu producto1 = new ProductoMenu("Hamburguesa", 100000);

        assertThrows(Exception.class, () -> pedido.agregarProducto(producto1));
    }
}
