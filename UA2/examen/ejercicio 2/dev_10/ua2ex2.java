import java.util.Random;

public class ua2ex2 {
    public class Aplicación_Principal {
        public static void main(String[] args) {
            try {
                Buffer buffer = new Buffer(10);
                Thread hiloProductor = new Thread(new Productor(buffer));
                Thread hiloConsumidor = new Thread(new Consumidor(buffer));

                hiloProductor.start();
                hiloConsumidor.start();

                Thread.sleep(10000);

                hiloProductor.interrupt();
                hiloConsumidor.interrupt();
                hiloProductor.join();
                hiloConsumidor.join();

            } catch (Exception error) {
                System.out.println("Error al ejecutar el programa: " + error);
            }
        }
        public static class Buffer {
            private char[] buffer;
            private int capacidad;
            private int indiceEscritura;
            private int indiceLectura;
            private int contador;

            public Buffer(int capacidad) {
                this.capacidad = capacidad;
                this.buffer = new char[capacidad];
                this.indiceEscritura = 0;
                this.indiceLectura = 0;
                this.contador = 0;
            }

            /**
             * @param char los parametros de entrada son el caracter a producir.
             * @return no returna nada
             * @brief Metodo producir un caracter para el buffer
             * <p>
             * El metodo produce un caracter para buffer.
             * @retval Los valores de retorno son:
             */

            public synchronized void producir(char caracter) throws InterruptedException {
                while (contador == capacidad) {
                    wait();
                }
                buffer[indiceEscritura] = caracter;
                indiceEscritura = (indiceEscritura + 1) % capacidad;
                contador++;
                System.out.println("Producido: " + caracter + ". Buffer: " + String.valueOf(buffer));
                notifyAll();
            }

            /**
             * @param void No hay parametros de entrada.
             * @return Return caracter            Caracter consumido del buffer.
             * @brief Metodo consumir un caracter del buffer
             * <p>
             * El metodo consume un caracter del buffer y lo retorna.
             * @retval Los valores de retorno son: Caracter consumido del buffer.(no se que mas poner la vrdad :))
             */
            public synchronized char consumir() throws InterruptedException {
                while (contador == 0) {
                    wait();
                }
                char caracter = buffer[indiceLectura];
                indiceLectura = (indiceLectura + 1) % capacidad;
                contador--;
                System.out.println("Consumido: " + caracter + ". Buffer: " + String.valueOf(buffer));
                notifyAll();
                return caracter;
            }
        }
        public static class Productor implements Runnable {
            private Buffer buffer;
            private Random numAleatorio;

            public Productor(Buffer buffer) {
                this.buffer = buffer;
                this.numAleatorio = new Random();
            }

            @Override
            public void run() {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        char caracter = (char) (numAleatorio.nextInt(26) + 'A');
                        System.out.println("Producido: " + caracter);
                        buffer.producir(caracter);
                        Thread.sleep(numAleatorio.nextInt(301) + 200);
                    }
                } catch (InterruptedException error) {
                    System.out.println("Productor interrumpido" + error);
                } finally {
                    System.out.println("Productor finalizado");
                }
            }
        }
        public static class Consumidor implements Runnable {
            private Buffer buffer;
            private Random numAleatorio;

            public Consumidor(Buffer buffer) {
                this.buffer = buffer;
                this.numAleatorio = new Random();
            }

            @Override
            public void run() {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        char caracter = buffer.consumir();
                        System.out.println("Procesando: " + caracter);
                        Thread.sleep(numAleatorio.nextInt(401) + 300);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Consumidor interrumpido");
                } finally {
                    System.out.println("Consumidor finalizado");
                }
            }
        }
    }
}
