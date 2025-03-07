package barcerveza;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Representa a un camarero que sirve y recibe vasos de cerveza.
 */
public class Camarero {
    private String nombre; ///< Nombre del camarero.
    private List<VasoCerveza> listaVasos; ///< Lista de vasos disponibles.
    private Random random; ///< Generador de números aleatorios.

    /**
     * Constructor de la clase Camarero.
     * @param nombre Nombre del camarero.
     */
    public Camarero(String nombre) {
        this.nombre = nombre;
        this.listaVasos = new ArrayList<>();
        this.random = new Random();

        // Crear 3 vasos con tipo aleatorio (0 = Media Pinta, 1 = Pinta)
        for (int i = 0; i < 3; i++) {
            listaVasos.add(new VasoCerveza(i, random.nextInt(2)));
        }
    }

    /**
     * Sirve un vaso de cerveza a un cliente.
     * @return Un vaso de cerveza o null si no hay disponibles.
     */
    public synchronized VasoCerveza servirCerveza() {
        while (listaVasos.isEmpty()) {
            try {
                System.out.println(nombre + ": No hay vasos disponibles. Esperando...");
                wait(); // Espera hasta que haya vasos disponibles
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        VasoCerveza vaso = listaVasos.remove(0);
        System.out.println(nombre + " ha servido " + vaso);
        return vaso;
    }

    /**
     * Recibe de vuelta un vaso de cerveza de un cliente.
     * @param vaso Vaso que se devuelve.
     */
    public synchronized void devolverCerveza(VasoCerveza vaso) {
        listaVasos.add(vaso);
        System.out.println(nombre + " ha recibido de vuelta " + vaso);
        notifyAll(); // Despierta a los clientes que están esperando vasos
    }

    /**
     * Muestra la cantidad de vasos disponibles en el bar.
     */
    public synchronized void contarVasos() {
        System.out.println("Vasos disponibles: " + listaVasos.size());
    }
}
