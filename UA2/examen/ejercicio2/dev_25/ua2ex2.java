/**
Feedback JD: 12/12/2024

Buen trabajo Francisco, algunas cosas a mejorar:

Tamaño del buffer: El constructor ignora el parámetro tamanio, lo que limita la reutilización del código.
Nombres de variables: Consistencia en el estilo (por ejemplo, nombres en minúsculas para variables como Consumidor).
Sincronización innecesaria en run: El uso de synchronized en métodos que ya operan sobre métodos sincronizados es redundante.
Optimización del control: Los flags indiceEscritura y indiceLectura son innecesarios; el índice siguiente ya puede manejar la lógica.
Mensajes: Los logs podrían proporcionar información más útil sobre el estado del buffer.

**/


import static java.lang.Thread.sleep;

public class ua2ex2 {
    public static void main(String[] args) {
        //Instanciamos el buffer y los dos hilos productor y consumidor.
        Buffer buffer = new Buffer(10);
        Thread productor = new Thread(new Productor(buffer));
        Thread Consumidor = new Thread(new Consumidor(buffer));

        //Iniciamos los hilos.
        productor.start();
        Consumidor.start();

    }

    //Hilo productor
    public static class Productor implements Runnable {

        //Declaramos la variable de tipo buffer y tambien un string que tenga todas las letras
        //para aleatoriamente seleccionar una.
        private Buffer buffer;
        private final String caracteres = "abcdefghijklmnopqrstuvxyz";

        //Constructor que recibe el buffer que va a utilizar este productor.
        public Productor(Buffer b){
            this.buffer = b;
        }

        //Metodo donde se ejecuta el otro hilo. Aquí de forma aleatoria produce una letra y la mete en el buffer.
        public synchronized void run(){
            while(true){
                try {
                    //En la variable c recorremos toda la cadena caracteres y aleatoriamente almacena en c uno
                    char c = caracteres.charAt((int) (Math.random() * caracteres.length()));
                    buffer.producir(c);
                    System.out.println("Produciendo el caracter " + c + " del buffer");

                    sleep((int) (Math.random() * 500));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //Hilo consumidor
    public static class Consumidor implements Runnable {

        //Declaramos una variable de tipo Buffer.
        private Buffer buffer;

        //Inicializamos el constructor del Consumidor. Recibe el Buffer como un parámetro.
        public Consumidor(Buffer b) {
            this.buffer = b;
        }

        //El método donde se ejecuta un hilo nuevo. Consume caracteres del Buffer y los imprime por pantall.
        public synchronized void run() {
            while (true) {
                try {
                    char c = this.buffer.consumir();
                    System.out.println("Consumiendo el caracter " + c + " del buffer");

                    sleep((int) (Math.random() * 700));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }



    //Clase compartida entre el productor y el consumidor.
    public static class Buffer {
        //Declaramos un vector de caracteres, una variable siguiente como un contador y dos booleanos de
        //escritura y lectura para saber si el buffer está vacio o lleno.
        private char[] buffer;
        private int siguiente;
        private boolean indiceEscritura;
        private boolean indiceLectura;

        //Constructor del buffer que recibe el tamaño deseado.
        public Buffer(int tamanio){
            this.buffer = new char[10];
            this.siguiente = 0;
            this.indiceEscritura = true;
            this.indiceLectura = false;
        }

        //Metodo para consumir los caracteres que se introducen en el buffer.
        public synchronized char consumir(){
            while(this.indiceEscritura){
                try {
                    wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //el -- es para que al consumir se mueva una posicion atras del vector.
            siguiente--;
            this.indiceLectura = false;
            //esto es cuando llegue siguiente a 0 que el indice de escritura que vuelva a añadir caracteres.
            if(siguiente == 0){
                this.indiceEscritura = true;
            }
            notifyAll();
            return this.buffer[this.siguiente];
        }

        //Metodo para introducir los caracteres en el buffer.
        public synchronized void producir(char c){
            while(this.indiceLectura){
                try {
                    wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //el ++ es para que al producir se mueva una posicion adelante del vector.
            buffer[siguiente] = c;
            siguiente++;
            this.indiceEscritura = false;
            //esto es cuando llegue siguiente al tamaño del vector que el indice de lectura que vuelva a consumir caracteres.
            if(siguiente == this.buffer.length){
                this.indiceLectura = true;
            }
            notifyAll();
        }
    }
}
