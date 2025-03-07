package UA5_T1;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class JAASExample {
    public static void main(String[] args) {
        // Configuración del contexto de autenticación
        LoginContext loginContext = null;
        try {
            // Usar el nombre de la configuración JAAS
            loginContext = new LoginContext("MyLoginModule");
            loginContext.login(); // Realiza la autenticación
            System.out.println("Autenticación exitosa");
        } catch (LoginException e) {
            System.out.println("Autenticación fallida: " + e.getMessage());
        }
    }
}
