
// Tarea 2 - Álvaro Fernández Amaro
public class MainProgram {
    public static void main(String[] args) {

        try {
            Hilo h1 = new Hilo();
            h1.start();
            h1.join();

            System.out.println("\nPrograma finalizado correctamente.\n");
        } catch (Exception e) {
            System.err.println("Error al ejecutar el programa: " + e.getMessage());
        }

    }
}
