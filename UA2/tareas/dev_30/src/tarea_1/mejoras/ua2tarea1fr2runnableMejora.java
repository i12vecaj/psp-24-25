package tarea_1.mejoras;

public class ua2tarea1fr2runnableMejora {



    public static void main(String[] args) throws InterruptedException {

        Contador contador = new Contador(0);
        Thread hilo1 = new Thread(new incrementadorContador(contador));
        Thread hilo2 = new Thread(new incrementadorContador(contador));
        Thread hilo3 = new Thread(new incrementadorContador(contador));
        Thread hilo4 = new Thread(new incrementadorContador(contador));
        Thread hilo5 = new Thread(new incrementadorContador(contador));


        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();

        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            hilo5.join();
        }catch (Exception ex){
            System.out.println("Algo no ha salido bien");
        }



    }
    public static class incrementadorContador extends Thread{
        Contador contador;
        public incrementadorContador(Contador contador){

            this.contador = contador;
        }

        @Override
        public void run() {
            synchronized (contador){
                for (int i=0;i<1000;i++){

                    contador.incrementarContador();


                }
                System.out.println("El valor del contador es de: "+contador.getValorContador() );
            }






        }

    }

    public static class Contador{
        private int valor_contador;
        public Contador(int valor_contador){
            this.valor_contador =valor_contador;

        }
        public  void incrementarContador(){

            valor_contador++;
        }

        public int getValorContador(){
            return valor_contador;
        }

    }
}
