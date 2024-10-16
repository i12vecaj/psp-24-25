public class Temperatura implements Runnable {

    @Override
    public void run() {

        int n = 30;

        //Bucle para que se repita 10 veces
        for (int i = 0; i < 10; i++) {
            //Genera un numero aleatorio del 1 al 30
            int temperatura = (int) (Math.random() * n) + 1;
            System.out.println("La temperatura es: " + temperatura + "ºC ");
            //Con el Thread.sleep() hacemos que despues de cada bucle haya una pausa.
            long pausa = System.currentTimeMillis();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("El sensor hizo la lectura: " + pausa);
        }





    }
}
