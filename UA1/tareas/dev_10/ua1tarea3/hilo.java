package UA1.tareas.dev_10.ua1tarea3;

class hilo implements Runnable {
    public int exit;
    private String[] args;

    // Constructor le pasa los argumentos al hilo
    public hilo(String[] args) {
        this.args = args;
        this.exit = 570;
    }

    @Override
    public void run() {
        if (args.length < 1) {
            System.out.println("No hay argumentos de entrada");
            exit = 1;
            return;
        }

        try {
            // Intentamos convertir el argumento a número
            int numero = Integer.parseInt(args[0]);

            // Verificamos si el número es menor que 0
            if (numero < 0) {
                System.out.println("El argumento es un número entero menor que 0");
                exit = 3;
            } else {
                System.out.println("El argumento es un número válido");
                System.exit(0);
            }

        } catch (NumberFormatException e) {
            System.out.println("El argumento no es un número");
            exit = 2;
            return;
        }
    }
    //Funcion paara enviar datos al testing para comprobar.
    public int getExit(){
        return exit;
    }
}