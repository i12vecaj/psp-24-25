public class SimulacionHuerto {
    public static void main(String[] args) {
        int vecesciclo = 10; // aqui estan los ciclos

        // Crear tres hilos para cada sensor
        Sensor sensorTemperatura = new Sensor("Temperatura", vecesciclo);
        Sensor sensorHumedad = new Sensor("Humedad", vecesciclo);
        Sensor sensorEstadoPlantas = new Sensor("Estado de las Plantas", vecesciclo);

        sensorTemperatura.start();
        sensorHumedad.start();
        sensorEstadoPlantas.start();

        try {
            sensorTemperatura.join();
            sensorHumedad.join();
            sensorEstadoPlantas.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Simulación finalizada.");
    }
}
