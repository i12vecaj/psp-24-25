public class HiloHumedad implements Runnable{

    @Override
    public void run(){
        System.out.println("---Sensor Humedad---");
        float humedad = nuevaHumedad();
        int duerme = (int) (Math.random() * 3) + 1;
        try {
            Thread.sleep(duerme * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("El sensor realizó la lectura en " + System.currentTimeMillis() + " segundos");
        System.out.println("La humedad actual es: " + humedad + "g / m³");


    }
    public float nuevaHumedad() {
        return (float) Math.random() * 100;
    }
}
