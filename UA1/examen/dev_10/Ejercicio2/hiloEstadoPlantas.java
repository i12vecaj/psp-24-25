

class hiloEstadoPlantas implements Runnable {
    private int estadoPlantas;
    

    // Constructor le pasa los argumentos al hilo
    public hiloEstadoPlantas(int estadoPlantas) {
        this.estadoPlantas = 0;
 
    }

    @Override
    public void run() {
        if (estadoPlantas < 0 || estadoPlantas > 100) {
            System.out.println("El estado no es un valor real");

            return;
        }

        try {


        } catch (NumberFormatException e) {
            System.out.println("El argumento no es un número");

            return;
        }
    }

}