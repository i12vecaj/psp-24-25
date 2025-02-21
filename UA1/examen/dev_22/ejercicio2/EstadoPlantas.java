public class EstadoPlantas extends Thread{
    private int detector;
    public EstadoPlantas() {
        detector = (int) (Math.random() * 5);
    }

    @Override
    public void run() {
        try {
            Thread.sleep((long) (Math.random() * 3000 + 1000)); // Esperar un tiempo aleatorio entre 1 y 3 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double medicion = Math.random() * 100; // Generar una medición aleatoria entre 0 y 100
        long tiempo = System.currentTimeMillis(); // Obtener el tiempo actual en milisegundos
        System.out.printf("Sensor Estado de las plantas nº %d: Medición %.2f, Tiempo: %d%n", detector, medicion, tiempo);
    }



}
