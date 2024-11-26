import java.util.Random;
public class Temperatura extends Thread {

    public Temperatura() {

    }

    public void run() {

        for (int i = 0; i < 10 ; i++) { //Metemos un for para realizar los 10 ciclos

            int temperatura = (int)Math.floor(Math.random()*40+1); //Declaramos la variable dentro para que en cada ciclo cambie
            System.out.println("La temperatura del huerto es de "+temperatura+" grados.\n");

            long start = System.currentTimeMillis(); //Declaramos la variable start para poder mostrar el tiempo que se para el hilo
            try { //Metemos un try and catch para la prueba de errores
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("El hilo esta en pausa durante "+(System.currentTimeMillis()-start)+"milisegundos.\n");
        }

    }

}
