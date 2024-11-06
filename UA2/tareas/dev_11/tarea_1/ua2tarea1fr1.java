public class ua2tarea1fr1 {
    public static void main(String[] args) {

        // Clase que representa un contador compartido por varios hilos
        class Contador {
            int valor; // Valor del contador
            int valorIncrementoHilos; // Incremento que cada hilo agrega al contador

            // Constructor que inicializa el contador con valor 0 y establece el incremento a 1000
            public Contador() {
                this.valor = 0;
                valorIncrementoHilos = 1000;
            }

            // Metodo que incrementa el valor del contador y muestra el valor actual
            public synchronized void setValor() {
                this.valor += valorIncrementoHilos; // Incrementa el valor del contador
                System.out.println("El valor actual es: " + valor);

                // Pausa la ejecucion del hilo durante 1 segundo
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        // Clase hilo que modifica el valor del contador
        class Hilo extends Thread {
            private Contador contador; // Referencia al contador compartido

            // Constructor que asigna el numero del hilo y el contador compartido
            public Hilo(Contador contador) {
                this.contador = contador;
            }

            // Metodo que se ejecuta cuando el hilo empieza su ejecucion
            @Override
            public void run() {
                contador.setValor(); // Llama al metodo que incrementa el valor del contador
            }
        }

        // Creo una instancia del contador compartido
        Contador contador = new Contador();

        // Inicializo 5 objetos hilo en el contador
        for (int i = 0; i < 5; i++) {
            Hilo hilo = new Hilo(contador);
            hilo.start(); // Inicia la ejecucion del hilo
        }
    }
}

//El resultado no es correcto puesto que se genera condicion de carrera, y va aumentando el contador de manera desordenada.