/**
 * Clase principal que ejecuta el productor y el consumidor.
 */
public class Main {
    /**
     * Método principal donde se inicializan los hilos productor y consumidor.
     * 
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        // Crear un buffer con capacidad para 10 caracteres.
        Buffer buffer = new Buffer(10);

        // Crear el hilo productor y el hilo consumidor.
        Productor productor = new Productor(buffer);
        Consumidor consumidor = new Consumidor(buffer);

        // Iniciar ambos hilos.
        productor.start();
        consumidor.start();
    }
}

/**
 * Clase Buffer que simula una zona de memoria compartida entre el productor y el consumidor.
 * 
 * El buffer tiene una capacidad fija y permite la producción y consumo de caracteres de manera sincronizada.
 */
public class Buffer {
    private char[] buffer; /**< Buffer que almacena los caracteres producidos. */
    private int capacidad; /**< Capacidad máxima del buffer. */
    private int indiceEscritura; /**< Índice donde se escribe el siguiente carácter. */
    private int indiceLectura; /**< Índice desde donde se lee el siguiente carácter. */

    /**
     * Constructor de la clase Buffer.
     * 
     * @param capacidad La capacidad máxima del buffer.
     */
    public Buffer(int capacidad) {
        this.capacidad = capacidad;
        this.buffer = new char[capacidad];
        this.indiceEscritura = 0;
        this.indiceLectura = 0;
    }

    /**
     * Método para producir un carácter y almacenarlo en el buffer.
     * Si el buffer está lleno, el hilo productor espera hasta que haya espacio.
     * 
     * @param c El carácter a producir.
     */
    public synchronized void producir(char c) {
        // Si el buffer está lleno, espera hasta que haya espacio.
        while (indiceEscritura == capacidad) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Añadir el carácter al buffer y actualizar el índice de escritura.
        buffer[indiceEscritura] = c;
        indiceEscritura++;

        // Notificar a todos los hilos esperando (en este caso el consumidor).
        notifyAll();
        
        // Mostrar el estado del buffer después de producir un carácter.
        mostrarEstado();
    }

    /**
     * Método para consumir un carácter del buffer.
     * Si el buffer está vacío, el hilo consumidor espera hasta que haya algo que consumir.
     * 
     * @return El carácter consumido.
     */
    public synchronized char consumir() {
        // Si el buffer está vacío, espera hasta que haya datos.
        while (indiceLectura == indiceEscritura) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Obtener el carácter del buffer y actualizar el índice de lectura.
        char c = buffer[indiceLectura];
        indiceLectura++;

        // Notificar a todos los hilos esperando (en este caso el productor).
        notifyAll();
        
        // Mostrar el estado del buffer después de consumir un carácter.
        mostrarEstado();

        return c;
    }

    /**
     * Método para mostrar el estado del buffer (cantidad de elementos en el buffer).
     */
    private void mostrarEstado() {
        System.out.println("Estado del buffer: " + (indiceEscritura - indiceLectura) + " elementos en el buffer.");
    }
}

/**
 * Clase Productor que simula un hilo productor que agrega caracteres al buffer.
 */
public class Productor extends Thread {
    private Buffer buffer; /**< Buffer compartido donde el productor añade los caracteres. */

    /**
     * Constructor de la clase Productor.
     * 
     * @param buffer El buffer compartido donde se agregarán los caracteres.
     */
    public Productor(Buffer buffer){
        this.buffer = buffer;
    }

    /**
     * Método que ejecuta el hilo productor.
     * En cada iteración produce un carácter aleatorio y lo agrega al buffer.
     * Luego, el hilo duerme un tiempo aleatorio entre 300 y 500 ms.
     */
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) { // Limitar a 10 caracteres producidos para simular fin.
            // Generar un carácter aleatorio.
            char c = (char) (Math.random() * 26 + 'A');
            System.out.println("Se ha producido el caracter: " + c);
            
            // Producir el carácter y agregarlo al buffer.
            buffer.producir(c);
            
            // Hacer que el hilo duerma entre 300 y 500 ms para simular el tiempo de producción.
            try {
                Thread.sleep((int) (Math.random() * 200 + 300));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("El productor ha terminado de producir.");
    }
}

/**
 * Clase Consumidor que simula un hilo consumidor que extrae caracteres del buffer.
 */
public class Consumidor extends Thread {
    private Buffer buffer; /**< Buffer compartido desde el cual el consumidor consume los caracteres. */

    /**
     * Constructor de la clase Consumidor.
     * 
     * @param buffer El buffer compartido desde el cual se consumirán los caracteres.
     */
    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    /**
     * Método que ejecuta el hilo consumidor.
     * En cada iteración consume un carácter del buffer y lo imprime.
     * Luego, el hilo duerme un tiempo aleatorio entre 700 y 1000 ms.
     */
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) { // Limitar a 10 caracteres consumidos para simular fin.
            // Consumir un carácter del buffer.
            char c = buffer.consumir();
            System.out.println("Se ha consumido el caracter: " + c);
            
            // Hacer que el hilo duerma entre 700 y 1000 ms para simular el tiempo de consumo.
            try {
                Thread.sleep((int) (Math.random() * 300 + 700));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("El consumidor ha terminado de consumir.");
    }
}
