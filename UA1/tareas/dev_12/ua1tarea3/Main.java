public class Main {
    public static void main(String[] args) {
        
        // Creamos y ejecutamos el hilo Hilo1
        Hilo1 hilo = new Hilo1(args);
        hilo.start();
        
        try {
            hilo.join(); // Esperamos a que el hilo termine
        } catch (InterruptedException e) {
            System.out.println("El hilo fue interrumpido.");
        }

        // Obtenemos el código de salida del hilo
        int codigoSalida = hilo.getCodigoSalida();
        interpretarSalida(codigoSalida);

        // Finalizamos el programa y devolvemos el valor representativo
        System.exit(codigoSalida);
    }

    private static void interpretarSalida(int codigoSalida) {
        switch (codigoSalida) {
            case 0:
                System.out.println("0: El argumento es un numero no negativo.");
                break;
            case 1:
                System.out.println("1: Se requiere al menos un argumento.");
                break;
            case 2:
                System.out.println("2: El argumento es una cadena de texto.");
                break;
            case 3:
                System.out.println("3: El argumento es un numero negativo.");
                break;
            default:
                System.out.println("Error desconocido.");
                break;
        }
    }
}
