import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class JAASAuthenticationEjemplo {
    public static void main(String[] args) {
        System.setProperty("java.security.auth.login.config", "jaas.config");

        try {
            LoginContext loginContext = new LoginContext("Aplicacion", new SimpleCallbackHandler());
            loginContext.login();
            System.out.println("Autenticación exitosa.");
        } catch (LoginException e) {
            System.out.println("Autenticación fallida: " + e.getMessage());
        }
    }
}
