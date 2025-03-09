import javax.security.auth.login.*;
public class ejemploJAAS {
    public static void main(String[] args) {
        try {
            // Crear un LoginContext con un módulo de autenticación
            LoginContext loginContext = new LoginContext("MiApp", new SimpleCallbackHandler());

            // Intentar autenticación
            loginContext.login();
            System.out.println("Autenticación exitosa ✅");

        } catch (LoginException e) {
            System.out.println("Error de autenticación ❌: " + e.getMessage());
        }
    }
}
