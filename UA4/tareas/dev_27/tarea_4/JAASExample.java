import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class JAASExample {
    public static void main(String[] args) {
        System.setProperty("java.security.auth.login.config", "jaas.config"); // Ruta al archivo de configuración

        try {
            LoginContext loginContext = new LoginContext("Login");
            loginContext.login();
            System.out.println("Autenticación exitosa");
        } catch (LoginException e) {
            System.out.println("Error de autenticación: " + e.getMessage());
        }
    }
}
