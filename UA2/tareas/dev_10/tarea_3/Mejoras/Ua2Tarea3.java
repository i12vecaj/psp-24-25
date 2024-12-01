package Mejoras;
public class Ua2Tarea3 {
    public static void main(String[] args) {
        String nombreArchivo = "AulaDeSoftwareLibreFPCordoba.txt"; // Asegúrate de que el archivo existe
        HiloContadorCaracteres contadorDeCaracteres = new HiloContadorCaracteres(nombreArchivo);
        Thread hiloQueCuenta = new Thread(contadorDeCaracteres);
        hiloQueCuenta.start();
        try {
            hiloQueCuenta.join(); // Esperamos a que el hilo termine
        } catch (InterruptedException e) {
            System.err.println("El hilo fue interrumpido: " + e.getMessage());
        }
    }
}
