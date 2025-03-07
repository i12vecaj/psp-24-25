package barcerveza;

import java.util.Random;

/**
 * Representa a un cliente que consume cerveza de forma concurrente.
 */
public class HilosClientes extends Thread {
    private Camarero camarero; ///< Referencia al camarero que sirve la cerveza.
    private String nombre; ///< Nombre del cliente.
    private double cantidadConsumida; ///< Cantidad total de cerveza bebida en litros.
    private Random random; ///< Generador de números aleatorios.

    /**
     * Constructor de la clase HilosClientes.
     * @param nombre Nombre del cliente.
     * @param camarero Referencia al camarero.
     */
    public HilosClientes(String nombre, Camarero camarero) {
        this.nombre = nombre;
        this.camarero = camarero;
        this.cantidadConsumida = 0;
        this.random = new Random();
    }

    /**
     * Método que ejecuta la simulación de un cliente bebiendo cerveza.
     */
    @Override
    public void run() {
        System.out.println(nombre + " ha llegado al bar y quiere una cerveza.");

        while (true) {
            // Pedir un vaso de cerveza
            VasoCerveza vaso = camarero.servirCerveza();

            if (vaso != null) {
                // Beber la cerveza
                double cantidad = (vaso.getTipo() == 0) ? 0.25 : 0.5; // Media pinta = 0.25L, Pinta = 0.5L
                cantidadConsumida += cantidad;
                System.out.println(nombre + " ha bebido " + cantidad + "L. Total: " + cantidadConsumida + "L.");

                // Esperar un tiempo aleatorio entre 250ms y 1000ms antes de pedir otra
                try {
                    Thread.sleep(random.nextInt(751) + 250);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                // Devolver el vaso
                camarero.devolverCerveza(vaso);
            }
        }
    }
}
