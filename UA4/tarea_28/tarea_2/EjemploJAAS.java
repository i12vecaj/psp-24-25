import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class EjemploJAAS {
    public static void main(String[] args) {

        System.setProperty("java.security.auth.login.config", "jaas.config");

        // Crear contexto de inicio de sesión
        LoginContext loginContext;
        try {
            loginContext = new LoginContext("MiAplicacion", new MiCallbackHandler());
        } catch (LoginException e) {
            e.printStackTrace();
            return;
        }

        // Intentar autenticación
        try {
            loginContext.login();
            System.out.println("Autenticación exitosa");
        } catch (LoginException e) {
            System.out.println("Autenticación fallida: " + e.getMessage());
        }
    }
}
