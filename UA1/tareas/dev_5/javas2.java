import java.io.IOException;

public class javas2 {
    public static void main(String[] args) {
        ejecutarPrograma1();
    }


    public static void ejecutarPrograma1() {
        try {
            //creando este proceso podemos ejecutar un programa externo en Java
            ProcessBuilder proceso = new ProcessBuilder("java", "javas1");
            proceso.inheritIO();
            Process startwait = proceso.start(); // Iniciar el proceso

            int codigo = startwait.waitFor(); // Esperar a que el proceso termine
            System.out.println("javas1 se ejecutó con eficacioa. Código de salida: " + codigo);

        } catch (IOException error ){
            System.out.println("Error al intentar ejecutar el primer programa: " + error.getMessage());
        } catch (InterruptedException interrupted) {
            System.out.println("El proceso fue interrumpido: " + interrupted.getMessage());
        }
    }
}
