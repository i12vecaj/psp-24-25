package org.example;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class JAASExample {
    public static void main(String[] args) {
        try {
            // Definir el contexto de autenticación con el nombre del login
            LoginContext loginContext = new LoginContext("MyLoginModule");

            // Intentar autenticar
            loginContext.login();
            System.out.println("Autenticacion realizada con exito");

            // Realizar alguna acción después de la autenticación exitosa
            // ...

            loginContext.logout();  // Cerrar sesión
        } catch (LoginException e) {
            System.out.println("La autenticacion ha fallado: " + e.getMessage());
        }
    }
}
