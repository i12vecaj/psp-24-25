//Examen de Alberto Mármol Bello con fecha 11/12/2024 para la asignatura de Procesos y Servicios.

//Si no compila bien pruebe en un compilador online.
import java.util.Random;

//Esta clase llamada buffer sirve para manejar la producción y consumo de letras.
class Buffer {
    char[] buffer;
    int capacidad;
    int indiceEscritura = 0, indiceLectura = 0, elementos = 0;

    public Buffer(int capacidad) {
        this.capacidad = capacidad;
        this.buffer = new char[capacidad];
    }

    //Este método que he creado sirve para sincronizar (con synchronized) los hilos para producir una letra.
    public synchronized void producir(char c) throws InterruptedException {
        while (elementos == capacidad) {
            wait();
        }
        buffer[indiceEscritura] = c;
        indiceEscritura = (indiceEscritura + 1) % capacidad;
        elementos++;

        //Muestro un mensaje para ver la letra que se ha producido y como está en el buffer.
        System.out.println("Lo que se PRODUCE: " + c + " | El buffer: " + mostrarBuffer());
        
        //Notifico a los hilos que están en espera.
        notifyAll();
    }

    //Este método que también lo he sincronizado (con synchronized) sirve para consumir una letra.
    public synchronized char consumir() throws InterruptedException {
        while (elementos == 0) {
            wait();
        }
        char c = buffer[indiceLectura];
        indiceLectura = (indiceLectura + 1) % capacidad;
        elementos--;
        
        //Vuelvo a mostrar como se consume una letra y como está en el buffer.
        System.out.println("Lo que se CONSUME: " + c + " | El buffer: " + mostrarBuffer());
        
        //Vuelvo a notificar a los hilos que están en espera.
        notifyAll();
        return c;
    }

    public String mostrarBuffer() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < capacidad; i++) {
            sb.append(i < elementos ? buffer[(indiceLectura + i) % capacidad] : " ");
            if (i < capacidad - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}

//A continuación creo la clase productor, para generar las letras aleatorias.
class Productor extends Thread {
    Buffer buffer;
    Random random = new Random();

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) { 
                //Aquí se genera la letra.
                char c = (char) (random.nextInt(26) + 'A');
                
                //Justo abajo la guardo en el buffer.
                buffer.producir(c);
                
                //Y marco un tiempo para los procesos.
                Thread.sleep(random.nextInt(301) + 200);
            }
        } catch (InterruptedException e) {
            //Si algo va mal muestro un mensaje.
            System.out.println("El productor se a interrumpido :(");
        }
    }
}

//LLegamos a la clase consumidor para que este consuma las letras del buffer.
class Consumidor extends Thread {
    Buffer buffer;
    Random random = new Random();

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char c = buffer.consumir();
                
                //El planteamiento es el mismo que el de crear pero esta vez es consumiendo. 
                System.out.println("Procesando: " + c);

                //Vuelvo a poner un tiempo para los procesos.
                Thread.sleep(random.nextInt(401) + 300);
            }
        } catch (InterruptedException e) {
            //Mensajito de error por si algo falla.
            System.out.println("El consumidor se a interrumpido :(");
        }
    }
}

//Por último creo la clase principal del programa el cual inicia y ejecuta los hilos.
public class ua2ex2 {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(10);
        new Productor(buffer).start();
        new Consumidor(buffer).start();
    }
}