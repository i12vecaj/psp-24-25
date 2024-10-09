import java.io.*;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class ua1tarea2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ua1tarea2.class);

    public static void main(String[] args) throws IOException {

        Scanner entrada = new Scanner(System.in);
        final String dir = System.getProperty("user.dir");
        LOGGER.info("Disco actual = {}", dir);

        File directorio = new File("build\\classes");
        ProcessBuilder pb = new ProcessBuilder("java", "ua1tarea2.cadenaCaracteres");
        pb.directory(directorio);

        Process p = pb.start();

        OutputStream os = p.getOutputStream();

        String cadenaMandada;

        do {
            LOGGER.info("Introduce una cadena de caracteres\n Para finalizar el bucle es '*'\n");
            cadenaMandada = entrada.nextLine();
            cadenaMandada += "\n";
            os.write(cadenaMandada.getBytes());
            os.flush();
        } while (!cadenaMandada.contains("*"));

        InputStream is = p.getInputStream();
        int c;
        while ((c = is.read()) != -1) {
            LOGGER.info("{}", (char) c);
        }
        is.close();

        int exitVal;
        try {
            exitVal = p.waitFor();
            LOGGER.info("Valor de Salida: {}", exitVal);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.error("Interrupted while waiting for process to finish", e);
        }

        try {
            InputStream er = p.getErrorStream();
            BufferedReader brer = new BufferedReader(new InputStreamReader(er));
            String liner;
            while ((liner = brer.readLine()) != null) {
                LOGGER.error("ERROR >{}", liner);
            }
        } catch (IOException ioe) {
            LOGGER.error("Error reading error stream", ioe);
        }
    }
}
