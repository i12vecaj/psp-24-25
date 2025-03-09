import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class JAAS {
    public static void main(String[] args) {
        try {

            LoginContext loginContext = new LoginContext("MiConfiguracionJAAS", new MiCallbackHandler());


            loginContext.login();
            System.out.println("Autenticación exitosa!");

            Subject subject = loginContext.getSubject();
            System.out.println("Sujeto autenticado: " + subject);

        } catch (LoginException e) {
            System.out.println("Autenticación fallida: " + e.getMessage());
        }
    }
}