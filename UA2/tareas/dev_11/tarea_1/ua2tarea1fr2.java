public class ua2tarea1fr2 {
    public static void main(String[] args) { //El programa es el mismo, sin embargo hay modificaciones

        class Contador{
            int valor;
            int valorIncrementoHilos;

            public Contador() {
                this.valor = 0;
                valorIncrementoHilos = 1000;
            }

            //La modificacion principal es que se añade synchronized con el objetivo de sincronizar la ejecucion de los hilos
            public synchronized void setValor() {
                this.valor += valorIncrementoHilos;
                System.out.println("El valor actual es: " + valor);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        class Hilo extends Thread {
            private Contador contador;


            public Hilo(Contador contador) {
                this.contador = contador;
            }

            @Override
            public void run() {
                contador.setValor();
            }
        }

        Contador contador = new Contador();

        for (int i = 0; i < 5; i++) {
            Hilo hilo = new Hilo(contador);
            hilo.start();
        }
    }
}

//El resultado aqui si es favorable puesto que usando la sincronizacion se ocurre de manera ordenada