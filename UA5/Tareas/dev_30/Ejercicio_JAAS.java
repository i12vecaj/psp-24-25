import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.io.IOException;

public class Ejercicio_JAAS {

    public static void main(String[] args) {
        try {
            // Crear un contexto de autenticación
            LoginContext loginContext = new LoginContext("SampleLoginModule");

            // intento que se autentifique al usuario
            loginContext.login();
            System.out.println("Autenticación exitosa.");

            Subject subject = loginContext.getSubject();
            System.out.println("Sujeto autenticado: " + subject);

        } catch (LoginException e) {
            System.out.println("Autenticación fallida: " + e.getMessage());
        }
    }

}

