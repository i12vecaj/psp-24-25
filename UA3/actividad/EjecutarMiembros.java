import java.io.IOException;

public class EjecutarMiembros {
    public static void main(String[] args) {
        try {
            String[] comandos = {
                "java MiembroToken 1 10000 yes no",
                "java MiembroToken 2 10001 no no",
                "java MiembroToken 3 10002 no yes"
            };

            for (String comando : comandos) {
                ProcessBuilder builder = new ProcessBuilder("comando");
                builder.start();
                System.out.println("Ejecutando: " + comando);
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}