public class ua2tarea1fr2runnable implements Runnable {
    //introducimos la variable contador
    int contador=0;

    @Override
    public void run() {
        //declaramos los hilos
        Thread hilo1 = new Thread(new Hilo());
        Thread hilo2 = new Thread(new Hilo2());
        Thread hilo3 = new Thread(new Hilo3());
        Thread hilo4 = new Thread(new Hilo4());
        Thread hilo5 = new Thread(new Hilo5());
//inicializo los hilos con un join para que los otros hilos no empiezen hasta que uno termine.
//aqui se puede apreciar el principal cambio con respecto al anterior ya que en el primero no ponemos un control.
        hilo1.start();
        try {
            hilo1.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        hilo2.start();
        try {
            hilo2.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        hilo3.start();
        try {
            hilo3.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        hilo4.start();
        try {
            hilo4.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        hilo5.start();
        try {
            hilo5.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

//creamos cinco hilos que incrementa cada uno la variable contador
    class Hilo extends Thread {
        @Override
        public void run() {
            contador+=1000;
            System.out.println(contador);
        }
    }

    class Hilo2 extends Thread {
        @Override
        public void run() {
            contador+=1000;
            System.out.println(contador);
        }
    }

    class Hilo3 extends Thread {
        @Override
        public void run() {
            contador+=1000;
            System.out.println(contador);
        }
    }

    class Hilo4 extends Thread {
        @Override
        public void run() {
            contador+=1000;
            System.out.println(contador);
        }
    }

    class Hilo5 extends Thread {
        @Override
        public void run() {
            contador+=1000;
            System.out.println(contador);
        }
    }
}