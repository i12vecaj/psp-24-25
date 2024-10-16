public class Hilo1 implements Runnable {
    private String[] args;

    // Constructor para pasar los argumentos
    public Hilo1(String[] args) {
        this.args = args;
    }

    // El código de la lógica de argumentos irá en el método run()
    @Override
    public void run() {
        // Verificar si no se ha pasado ningún argumento
        if (args.length < 1) {
            System.out.println("Error: Se requiere al menos un argumento.");
            System.exit(1);  // Devuelve 1 si no hay argumentos
        }

        // Verificar si el argumento es un número entero
        try {
            int numero = Integer.parseInt(args[0]);  // Intentar convertir el primer argumento a entero

            // Verificar si el número es menor que 0
            if (numero < 0) {
                System.out.println("Error: El argumento es un número entero menor que 0.");
                System.exit(3);  // Devuelve 3 si el número es menor que 0
            }

            // Caso exitoso: Número válido y mayor o igual a 0
            System.out.println("Argumento válido: " + numero);
            System.exit(0);  // Devuelve 0 en caso exitoso

        } catch (NumberFormatException e) {
            // Si no es un número entero, se trata como cadena
            System.out.println("Error: El argumento es una cadena.");
            System.exit(2);  // Devuelve 2 si el argumento es una cadena
        }
    }
}
