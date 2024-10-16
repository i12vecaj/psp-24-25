public class Humedad extends Thread {
    private double humedad;

    public Humedad() {
        this.humedad = Math.random() * 100; // Valor aleatorio recogido por el sensor
    }

    @Override
    public void run() {
        try {
            Thread.sleep((long) (Math.random() * 2000) + 1000); // Duerme entre 1 y 3 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Sensor de humedad: " + humedad + " % - Lectura realizada en " + System.currentTimeMillis());
    }


}
