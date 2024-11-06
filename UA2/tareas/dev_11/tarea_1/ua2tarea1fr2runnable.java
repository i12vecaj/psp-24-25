public class ua2tarea1fr2runnable {
    public static void main(String[] args) { //Es el mismo programa, y con la sincronizacion, pero con otra forma de implementarlo

        class Contador{
            int valor;
            int valorIncrementoHilos;

            public Contador() {
                this.valor = 0;
                valorIncrementoHilos = 1000;
            }

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

        //Se crea el hilo de manera que se implementa la interfaz Runnable
        class Hilo implements Runnable {
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

        //Aqui se inicializa el hilo de la manera correspondiente
        for (int i = 0; i < 5; i++) {
            Hilo hilo = new Hilo(contador);
            Thread h1 = new Thread(hilo);
            h1.start();
        }
    }
}

//El resultado es similar, sin embargo, se implementa con una vision mas abstracta.