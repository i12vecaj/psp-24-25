public class HiloEstadoSuelo implements Runnable{

    @Override
    public void run(){
        System.out.println("---Sensor estado del suelo---");
        float estadoSuelo = (float) Math.random() * 100;
        int duerme = (int) (Math.random() * 3) + 1;
        try {
            Thread.sleep(duerme * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("El sensor realizó la lectura en " + System.currentTimeMillis() + " segundos");
        System.out.println("La estado actual del suelo  es: " + estadoSuelo + " ");
    }
}
