/**
 * @file         ua2ex2.java
 */




import java.util.Random;

/**
 *          Clase main, esta se encarga de crear los objetos y los hilos que se vana  utilizar ademas de iniciarlos
 */

public class Main {
    public static void main(String[] args) {
        int cantidad = 10;
        String[] buffer = new String[cantidad];

        Buffer bufferMain = new Buffer(cantidad, buffer);

        Productor hilo1 = new Productor(bufferMain,cantidad);

        Consumidor hilo2 = new Consumidor(bufferMain,cantidad);

        hilo1.start();
        hilo2.start();
    }
}

/**
 *          Esta clase es el "intermediario" donde se almacenan los caracteres producidos por los hilos
 *
 *          Buffer
 */

class Buffer{
    String[] Buffer;
    int Capacidad;
    int IndiceLectura;
    int IndiceEscritura;
    boolean disponible=true;

    public Buffer(int capacidad, String[] buffer){
        Capacidad=capacidad;
        Buffer=buffer;
    }

    /**
     *              Este método introduce en el array del buffer el caracter que recibe desde el hilo productor
     */

    public synchronized void producir(String caracter) throws InterruptedException {
        if (!disponible) {
            wait();
        }
        Buffer[IndiceEscritura] = caracter;
        System.out.println("Producida la letra" + caracter);
        IndiceEscritura++;
        notify();
        disponible=false;
    }

    /**
     *              Este método devuelve un caracter del buffer al hilo consumidor
     */
    
    public synchronized String consumir() throws InterruptedException {
        if (disponible) {
            wait();
        }
        String caracterADevolver = Buffer[IndiceLectura];
        System.out.println("Consumida la letra" + caracterADevolver);
        IndiceLectura++;
        disponible=true;
        notify();
        return caracterADevolver;
    }
}

/**
 *              Esta es la clase desde la que se crea el primer hilo y su funcion es producir caracteres que se almacenaran en el buffer
 *
 *              Producotr
 */

class Productor extends Thread {
    Buffer buffer;
    int Cantidad;
    public Productor(Buffer b, int cantidad){
        buffer=b;
        Cantidad=cantidad;
    }


    /**
     *              Ejecuta la funcion de la clase productor, introduciendo en el buffer una nueva letra
     */


    @Override
    public void run() {
        for (int i=0;i<Cantidad;i++){
            char caracter= (char)(Math.random() * 26 + 'A');
            try {
                buffer.producir(String.valueOf(caracter));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                Random random = new Random(301+200);
                int esperaRandom;

                sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Metodo run del productor ejecutado");

    }
}

/**
 *              Esta clase se encarga de extraer los caracteres almacenados en el buffer
 *
 *              Consumidor
 */

class Consumidor extends Thread {
    Buffer buffer;
    int Cantidad;
    public Consumidor(Buffer b, int cantidad){
        buffer=b;
        Cantidad=cantidad;
    }


    /**
     *              Ejecuta la funcion de la clase consumidor, imprimiendo por pantalla la letra extraido dal buffer
     */


    @Override
    public void run() {
        for (int i=0;i<Cantidad;i++){
            try {
                System.out.println(buffer.consumir());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                Random random = new Random(301+200);
                int esperaRandom;

                sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Metodo run del consumidor ejecutado");
    }
}
