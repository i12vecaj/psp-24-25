
public class Hilos extends Thread {
    S_Temperatura sensor1 = new S_Temperatura();
    S_Humedad sensor2 = new S_Humedad();
    S_Vida sensor3 = new S_Vida();

    public void run() {
        // Hilo S_Temperatura
        Thread hilo1 = new Thread(sensor1);
        hilo1.start();

        // Hilo S_Humedad
        Thread hilo2 = new Thread(sensor2);
        hilo2.start();

        // Hilo S_Vida
        Thread hilo3 = new Thread(sensor3);
        hilo3.start();

        System.out.println("Datos recividos de los sensores");
    }
}
