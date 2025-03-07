package barcerveza;

/**
 * Representa un vaso de cerveza que los clientes pueden consumir.
 */
public class VasoCerveza {
    private int id;  ///< Identificador único del vaso.
    private int tipo; ///< Tipo de vaso: 0 = Media Pinta, 1 = Pinta.

    /**
     * Constructor de la clase VasoCerveza.
     * @param id Identificador del vaso.
     * @param tipo Tipo de vaso (0 = Media Pinta, 1 = Pinta).
     */
    public VasoCerveza(int id, int tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    /**
     * Obtiene el identificador del vaso.
     * @return ID del vaso.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el tipo de vaso.
     * @return Tipo de vaso (0 = Media Pinta, 1 = Pinta).
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * Representación en cadena del vaso de cerveza.
     * @return Información del vaso en formato legible.
     */
    @Override
    public String toString() {
        return "Vaso " + id + " (" + (tipo == 0 ? "Media Pinta" : "Pinta") + ")";
    }
}
