import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class JAASExample {
  public static void main(String[] args) {
    try {
      // Crear un contexto de autenticación con el nombre del módulo de login configurado en jaas.config
      LoginContext lc = new LoginContext("MyLoginModule");

      // Intentar autenticar al usuario
      lc.login();
      System.out.println("Autenticación exitosa.");
    } catch (LoginException e) {
      System.out.println("Autenticación fallida: " + e.getMessage());
    }
  }
}

