/**
Feedback JD - 12//12/2024
Fortalezas: Código bien estructurado y funcional; separación de responsabilidades adecuada; uso correcto de synchronized.
Áreas de mejora: Implementación incorrecta del patrón productor-consumidor; falta de uso de wait() y notify() provoca bloqueos; documentación excesivamente subjetiva; métodos y atributos innecesarios (indiceLectura, indiceEscritura).
Recomendaciones: Corregir concurrencia con wait()/notify(), simplificar la documentación con un tono profesional, y eliminar elementos no utilizados.
**/

/**
 * @mainpage                    Programa que representa la vida misma con la metáfora del patrón Productor-Consumidor
 *                              Existe una clase que representa un almacén y sirve para guardar las existencias y controlar que no se llene más
 *                              de lo que puede, clase que representa un trabajador exclavizado por el sistema necesita producir 24/7 para poder pagar
 *                              todo lo que se ha comprado que realmente no sabe que no necesita. Consumidor que representa una víctima del sistema que a base
 *                              de anuncios hasta en la sopa psicologicamente estudiados por expertos de la mente solo sabe llenar su mísera vida a base de compras
 *                              innecesarias.
 *
 *
 */


package ua2ex2;

import java.util.ArrayList;
import java.util.List;


/**
 * @brief              Clase buffer
 *
 *                     Almacén de caracteres
 * @class              BufferClass
 */

class BufferClass {
    private List<Character> charBuffer;
    private int size;
    private int indiceLectura;

    private int indiceEscritura;

    public BufferClass(int sizeBuffer) {
        this.size = sizeBuffer;
        this.charBuffer = new ArrayList<Character>(sizeBuffer);
    }

    /**
     *@brief              add char
     *
     *                    Metodo que recibe la producción y si esta lleno se descarta pero si hay hueco lo guarda
     *
     *@param              c caracter a producir
     *
     *@return             void
     *@retval             void, no avisa para que el productor nunca se de cuenta de que no tiene que trabajar tanto
     */
    public synchronized boolean addChar(char c) {
        while (charBuffer.size() < size) {
            this.charBuffer.add(c);
        }
        return true;
    }

    /**
     *@brief              sub char
     *
     *                    Metodo que recibe la petición de que alguien necesita consumir y si hay existencias lo suminsitra si no
     *                    le devuelve lo que exista
     *
     *@param              void
     *
     *@return             charConsumido
     *@retval             dosis necesaria por el consumidor
     */
    public synchronized char subChar() {
        char charConsumido = ' ';
        if(this.charBuffer.size() > 0) {
            charConsumido = this.charBuffer.get(this.charBuffer.size() - 1);
            this.charBuffer.remove(this.charBuffer.size() - 1);
        }
        return charConsumido;
    }

    public int getIndiceLectura() {
        return indiceLectura;
    }

    public void setIndiceLectura(int indiceLectura) {
        this.indiceLectura = indiceLectura;
    }

    public int getIndiceEscritura() {
        return indiceEscritura;
    }

    public void setIndiceEscritura(int indiceEscritura) {
        this.indiceEscritura = indiceEscritura;
    }
}

/**
 * @brief              Clase Productor
 *
 *                     Esclavo del sistema, solo tiene como finalidad en su vida producir 24/7 para abastecer de caracteres
 *                     a los consumidores impulsivos
 *
 * @class              Productor
 */
class Productor implements Runnable {

    private BufferClass buffer;

    public Productor(BufferClass buffer) {
        this.buffer = buffer;
    }

    /**
     *@brief              run
     *
     *                    Maquinaria del productor que fabrica caracteres y los guarda, produce cada 250ms
     *
     *
     */
    @Override
    public void run() {
        while(true) {
            char charProducido = (char) (Math.random() * 26 + 'A');
            boolean result = this.buffer.addChar(charProducido);
            System.out.println("Se ha producido el caracter: " + charProducido);
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

/**
 * @brief              Clase Consumidor
 *
 *                     Clase consumidor, en pleno apogeo del capitalismo la sociedad esta llena de estos individuos
 *                     solo se dedican a consumir sin pensar en las consecuencias.
 *
 * @class              Consumidor
 */
class Consumidor implements Runnable {
    private BufferClass buffer;
    private String nombre;
    public Consumidor(BufferClass buffer, String nombre) {
        this.buffer = buffer;
        this.nombre = nombre;
    }

    /**
     *@brief              run
     *
     *                    Consumidores pidiendo algo para consumir, no para nunca y piden cada 400ms
     *
     *
     */
    @Override
    public void run() {
        while(true) {
            char charConsumido = this.buffer.subChar();
            if(charConsumido != ' ') {
                System.out.println("Se ha consumido el caracter: " + charConsumido + " por el consumidor: " + nombre);
            }
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

/**
 * @brief              Clase ua2ex2
 *
 *                     Clase principal cuya función solo es almacenar el main
 *
 * @class              ua2ex2
 */
public class ua2ex2 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Inicio de la producción");

        BufferClass buffer = new BufferClass(10);
        Thread t1 = new Thread(new Productor(buffer), "Productor");
        Thread c1 = new Thread(new Consumidor(buffer, "Consumidor1"));
        Thread c2 = new Thread(new Consumidor(buffer, "Consumidor2"));

        t1.start();
        c1.start();
        c2.start();

        c1.join();
        c2.join();

        System.out.println("Fin de la producción");
    }
}

