
public class MonitoreoHuerto {
    public static void main(String[] args) {
        int ciclos = 10; 

        // Crear hilos para cada sensor
        Thread sensorTemperatura = new Thread(new Sensores("Temperatura", ciclos));
        Thread sensorHumedad = new Thread(new Sensores("Humedad", ciclos));
        Thread sensorEstadoPlantas = new Thread(new Sensores("Estado de las Plantas", ciclos));

        
        sensorTemperatura.start();
        sensorHumedad.start();
        sensorEstadoPlantas.start();

    
        try {
            sensorTemperatura.join();
            sensorHumedad.join();
            sensorEstadoPlantas.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Monitoreo completado.");
    }
}