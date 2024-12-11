public class ua2ex2 {

    public static void main(String[] args) {
        Buffer buffer = new Buffer(10); //creamos un buffer de tamaño 10
        Productor productor = new Productor(buffer); //creamos un productor con el buffer
        Consumidor consumidor = new Consumidor(buffer); //creamos un consumidor con el buffer

        // Crear y empezar los hilos
        Thread hiloProductor = new Thread(productor);
        Thread hiloConsumidor = new Thread(consumidor);

        hiloProductor.start();
        hiloConsumidor.start();
    }
}

class Buffer {

    //declaramos los atributos
    private char[] buffer;
    private int indiceEscritura;
    private int indiceLectura;
    private int capacidad;

    //inicializamos el buffer con tamaño a 0
    public Buffer(int size) {
        buffer = new char[size]; //creamos el buffer con el tamaño pasado por parametro
        indiceEscritura = 0; //inicializamos el indice de escritura a 0
        indiceLectura = 0; //inicializamos el indice de lectura a 0
        capacidad = 0; //inicializamos la capacidad a 0
    }

    //metodo para producir un caracter en el buffer y notificar a los hilos que esten esperando
    public synchronized void producir(char c) {
        while (capacidad == buffer.length) { //mientras el buffer este lleno esperamos
            try {
                wait();
            } catch (InterruptedException e) { //capturamos la excepcion
                e.printStackTrace();
            }
        }
        buffer[indiceEscritura] = c; //escribimos el caracter en el buffer
        indiceEscritura = (indiceEscritura + 1) % buffer.length; //actualizamos el indice de escritura
        capacidad++; //aumentamos la capacidad
        notifyAll(); //notificamos a los hilos
    }

    //metodo para consumir un caracter del buffer y notificar a los hilos que esten esperando
    public synchronized char consumir() {
        while (capacidad == 0) { //mientras no haya caracteres en el buffer esperamos
            try {
                wait();
            } catch (InterruptedException e) { //capturamos la excepcion
                e.printStackTrace();
            }
        }
        char c = buffer[indiceLectura]; //leemos el caracter del buffer
        indiceLectura = (indiceLectura + 1) % buffer.length; //actualizamos el indice de lectura
        capacidad--; //disminuimos la capacidad
        notifyAll(); //notificamos a los hilos
        return c; //devolvemos el caracter leido
    }
}

//clase productor que implementa la interfaz Runnable para poder ser un hilo y producir caracteres en el buffer de forma aleatoria
class Productor implements Runnable {
    //declaramos el buffer
    private Buffer buffer;
    //constructor de la clase
    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }
    //metodo run que se ejecuta al iniciar
    @Override
    public void run() {
        while (true) {
            char c = (char) (Math.random() * 26 + 'A');  //generamos un caracter aleatorio
            buffer.producir(c); //producimos un caracter en el buffer
            System.out.println("Producido: " + c); //imprimimos el caracter producido
            try {
                Thread.sleep((long) (Math.random() * 300 + 200)); //dormimos el hilo
            } catch (InterruptedException e) {  //capturamos la excepcion
                e.printStackTrace();
            }
        }
    }
}

//clase consumidor que implementa la interfaz Runnable para poder ser un hilo y consumir caracteres del buffer de forma aleatoria

class Consumidor implements Runnable {

    private Buffer buffer; //declaramos el buffer
    //constructor de la clase
    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }
    //metodo run que se ejecuta al iniciar
    @Override
    public void run() {
        while (true) {
            char c = buffer.consumir(); //consumimos un caracter del buffer
            System.out.println("Consumido: " + c);//imprimimos el caracter consumido
            try {
                Thread.sleep((long) (Math.random() * 300 + 200));//dormimos el hilo
            } catch (InterruptedException e) { //capturamos la excepcion
                e.printStackTrace();
            }
        }
    }
}
