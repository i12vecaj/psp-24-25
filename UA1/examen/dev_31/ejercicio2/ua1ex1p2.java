import java.util.Random;

class Sensor extends Thread {
    private String nombreSensor;
    private int ciclos;

    public Sensor(String nombreSensor, int ciclos) {
        this.nombreSensor = nombreSensor;
        this.ciclos = ciclos;
    }


    private double generarValor() {
        Random random = new Random();
        switch (nombreSensor) {
            case "Temperatura":

                return -10 + random.nextDouble() * 50;
            case "Humedad":

                return random.nextDouble() * 100;
            case "Estado de las Plantas":

                return 1 + random.nextInt(5);
            default:
                return 0;
        }
    }

    @Override
    public void run() {
        Random random = new Random();

        for (int i = 0; i < ciclos; i++) {
            // Generar el valor aleatorio para la medición
            double valorMedido = generarValor();

            // Obtener el tiempo actual en milisegundos
            long tiempoActual = System.currentTimeMillis();

            // Imprimir el valor con la marca de tiempo
            System.out.printf("Sensor: %s | Valor: %.2f | Timestamp: %d ms\n", nombreSensor, valorMedido, tiempoActual);

            // Dormir el hilo por un tiempo aleatorio entre 1 y 3 segundos
            int tiempoEspera = 1000 + random.nextInt(2000); // de 1000 ms a 3000 ms
            try {
                Thread.sleep(tiempoEspera);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
