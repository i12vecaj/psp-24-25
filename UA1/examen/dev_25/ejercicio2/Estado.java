public class Estado implements Runnable {

    @Override
    public void run() {
        int n = 100;

        //Bucle para que se repita 10 veces
        for (int i = 0; i < 10; i++) {

            int estado = (int) (Math.random() * n) + 1;
            System.out.println("El porcentaje de vitalidad de la planta es de: " + estado + "% ");
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
