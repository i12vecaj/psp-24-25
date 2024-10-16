import java.io.IOException;

public class EjecutarProceso {
    public static void main(String[] args) {
        try {
            String[] comando = new String[args.length + 2];
            comando[0] = "java";
            comando[1] = "ProcesoPrincipal";
            System.arraycopy(args, 0, comando, 2, args.length);
            

            Process proceso = Runtime.getRuntime().exec(comando);
            int codigoSalida = proceso.waitFor();

            switch (codigoSalida) {
                case 0:
                    System.out.println("El argumento es un número entero válido.");
                    break;
                case 1:
                    System.out.println("No se proporcionaron argumentos.");
                    break;
                case 2:
                    System.out.println("El argumento es una cadena.");
                    break;
                case 3:
                    System.out.println("El argumento es un número entero menor que 0.");
                    break;
                default:
                    System.out.println("Código de salida desconocido: " + codigoSalida);
                    break;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
