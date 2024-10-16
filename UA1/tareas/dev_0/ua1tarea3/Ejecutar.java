public class Ejecutar implements Runnable {
    private String[] args;

    public Ejecutar(String[] args) {
        this.args = args; // Guardamos los argumentos que recibimos en la variable 'args'
    }

    public void run() {
        // Primero, comprobamos si no se han pasado argumentos
        if (args.length < 1) {
            // Si no hay argumentos, mostramos un mensaje y terminamos el programa con el código 1
            System.out.println("No hay argumentos. El valor de salida es: 1");
            System.exit(1);
        }

        // Recorremos todos los argumentos que se han pasado
        for (String arg : args) {
            try {
                // Intentamos convertir el argumento de texto a un número entero
                int numero = Integer.parseInt(arg);

                // Si el número es menor que 0, mostramos un mensaje y terminamos con el código 3
                if (numero < 0) {
                    System.out.println("El argumento es un número menor que cero. El valor de salida es: 3");
                    System.exit(3);
                }
            } catch (NumberFormatException e) {
                // Si no se puede convertir a número, significa que es un texto
                System.out.println("El argumento es una cadena. El valor de salida es: 2");
                System.exit(2);
            }
        }

        // Si todos los argumentos son válidos mostramos un mensaje de éxito
        System.out.println("Todos los argumentos son válidos. El valor de salida es: 0");
        System.exit(0);
    }
}