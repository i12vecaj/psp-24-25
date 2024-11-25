import java.util.Scanner; // Importar la clase Scanner

public class LeerProcesos implements Runnable {
    public LeerProcesos() { // Constructor sin parámetros
        // Inicializaciones si son necesarias
    }

    public void run() {
        Scanner scanner = new Scanner(System.in); // Crear un objeto Scanner
        System.out.println("Pido al usuario un número:");

        try {
            int numero = scanner.nextInt(); // Leer un número entero del usuario
            if (numero < 0) { // Comprobar si es menor que cero
                System.out.println("El argumento es un número menor que cero: " + numero);
                System.exit(3);
            }
            System.out.println("El número ingresado es válido: " + numero);
        } catch (Exception e) { // Manejar la excepción por entrada no válida
            System.out.println("El argumento es una cadena o un número inválido.");
            System.exit(2);
        } finally {
            scanner.close(); // Cerrar el scanner
        }

        System.exit(0); // Salir con código 0 si todo es correcto
    }
}