import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class EjemploJAAS {
    public static void main(String[] args) {
        // Nombre del archivo de configuración de JAAS
        System.setProperty("java.security.auth.login.config", "jaas.config");

        // Crear un contexto de inicio de sesión
        LoginContext loginContext;
        try {
            loginContext = new LoginContext("MiAplicacion", new MiCallbackHandler());
        } catch (LoginException e) {
            e.printStackTrace();
            return;
        }

        // Intentar autenticar al usuario
        try {
            loginContext.login();
            System.out.println("Autenticación exitosa!");
        } catch (LoginException e) {
            System.out.println("Autenticación fallida: " + e.getMessage());
        }
    }
}
