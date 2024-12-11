//en el main creamos el buffer y los hilos para iniciar el proceso del programa
public class Main {
    public static void main(String[] args) {
        //creamos el buffer y le damos la capacidad limitada de 10 elementos
        Buffer buffer = new Buffer(10);
        //creamos los 2 hilos productor y consumidor relacionandolos con el buffer
        Productor productor = new Productor(buffer);
        Consumidor consumidor = new Consumidor(buffer);
        //iniciamos los hilos
        //iniciamos el hilo productor
        productor.start();
        //iniciamos el hilo consumidor
        consumidor.start();
    }
}


import java.util.Random;
//creamos la clase consumidor y la extendemos con el thread para crear el hilo
class Consumidor extends Thread {
    //llamamos a la variable buffer
    private Buffer buffer;

    public Consumidor(Buffer buffer) {
        //añadimos el this.
        this.buffer = buffer;
    }

    //empezamos con los metodos que queremos que realice el hilo consumidor
    //en este caso queremos que extraiga caracteres del buffer y los procesa
    //entre cada consumo queremos que espere  un tiempo aleatorio (entre 300 ms y 700 ms).

    @Override

    public void run() {
        //hacemos el random
        Random random = new Random();
        //bucle infinito para la consumicion de caracteres
        while (true) {
            try {
                //llamamos a la funcion consumir de la clase buffer
                buffer.consumir();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

import java.util.Random;

//creamos la clase consumidor y la extendemos con el Thread para que sea un hilo
class Productor extends Thread {
    private Buffer buffer;
    //llamamos al buffer

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }
    //añadimos el this
    //empezamos con los metodos que queremos que realice el hilo productor
    //en este caso queremos que genere caracteres aleatorios  y los introduce en el buffer.
    //Y que entre cada produccion
    //Espere un tiempo aleatorio (entre 200 ms y 500 ms) entre cada producción
    @Override
    public void run() {
        //hacemos el random
        Random random = new Random();
        //bucle infinito para la produccion de caracteres
        while (true) {
            //generamos un caracter aleatorio entre A y Z(con el ejemplo hay en el enunciado)
            // y lo añadimos al buffer.
            try {
                char c = (char) (random.nextInt(26) + 'A');
                //llamamos a la funcion producir de la clase buffer
                buffer.producir(c);//tengo que poner c dentro para hacer la funcion con el caracter nuevo
                //hacemos esperar un tiempo aleatorio entre 200 ms y 500 ms entre produccion y producción.
                Thread.sleep(random.nextInt(301) + 200);

            } catch (InterruptedException e) {
                //si se interrumpe el hilo lo manteamos y  mostramos error
                e.printStackTrace();
            }

        }
    }

    }
public class Buffer {
    //creamos los atributos
    private char[] buffer;
    private int capacity;
    private int indiceEscritura;
    private int indiceLectura;

    //creamos el array para guardar los datos
    public Buffer(int capacity) {
        this.buffer = new char[capacity];
        this.capacity = capacity;
        this.indiceEscritura = 0;
        this.indiceLectura = 0;

    }
    //creamos los metodos
    //creamos el meotodo producir
    public synchronized void producir(char c){
        for (int indiceEscritura = 0; indiceEscritura <10;)indiceEscritura++;{
            buffer[indiceEscritura] = c;
            System.out.println("hemos producido el medicamento: " + c);

        }

    }
    //creamos el metodo consumir
    public synchronized void consumir(){
        for (int indiceLectura = 0; indiceLectura <10;)indiceLectura++;{
            char c = buffer[indiceLectura];
            System.out.println("hemos producido el medicamento: " + c);

        }

    }

}








