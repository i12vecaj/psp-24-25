

class hiloTemperatura implements Runnable {
    private int Temperatura;

    // Constructor le pasa los argumentos al hilo
    public hiloTemperatura(int Temperatura) {
        this.Temperatura = 0;
    }

    @Override
    public void run() {
        if (Temperatura < -257 || Temperatura > 100) {
            System.out.println("La temperatura no es un valor real");
            return;
        }

        try {


        } catch (NumberFormatException e) {
            System.out.println("El argumento no es un número");
            return;
        }
    }
}