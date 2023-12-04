package uniandes.dpoo.taller2.modelo;

@SuppressWarnings("serial")
public class IngredienteRepetidoException extends HamburguesaException {
    public IngredienteRepetidoException(String ingrediente) {
        super("Error: Ingrediente repetido - " + ingrediente);
    }
}
