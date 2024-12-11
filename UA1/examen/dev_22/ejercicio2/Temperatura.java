public class Temperatura extends Thread {
    private String sensor;

    public Temperatura(String sensor) {
        this.sensor = sensor;

    }

    @Override
    public void run() {
        while (true) {
            double temperatura = (Math.random() * 50) + 1;
            try {
                Thread.sleep((long) (Math.random() * 3000 + 1000)); // Esperar un tiempo aleatorio entre 1 y 3 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.printf("%s de temperatura: %.2f grados Celsius, leída en %d ms%n", sensor, temperatura, System.currentTimeMillis());
        }
    }

}
