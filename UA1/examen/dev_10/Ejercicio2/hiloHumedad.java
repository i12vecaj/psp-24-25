class hiloHumedad implements Runnable {
    private int humedad;
    

    // Constructor le pasa los argumentos al hilo
    public hiloHumedad(int humedad) {
        this.humedad = 0;

    }

    @Override
    public void run() {
        if (humedad < 0 || humedad > 100) {
            System.out.println("La Humedad no es un valor real(Entre 0 y 100)");
            return;
        }

        try {

        } catch (NumberFormatException e) {
            System.out.println("El argumento no es un número");
            return;
        }
    }
}