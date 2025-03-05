import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.util.Scanner;

public class JAASExample {
    public static void main(String[] args) {
        System.setProperty("java.security.auth.login.config", "jaas.config");

        try {
            // Pedir credenciales
            Scanner scanner = new Scanner(System.in);
            System.out.print("Usuario: ");
            String usuario = scanner.nextLine();
            System.out.print("Contraseña: ");
            String clave = scanner.nextLine();
            
            // Iniciar autenticación con JAAS
            LoginContext loginContext = new LoginContext("JAASExample", new SimpleCallbackHandler(usuario, clave));
            loginContext.login();
            
            System.out.println("Autenticación exitosa.");
        } catch (LoginException e) {
            System.out.println("Error en autenticación: " + e.getMessage());
        }
    }
}