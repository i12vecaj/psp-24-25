package org.example;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class JAASEjemplo {
    public static void main(String[] args) {
        try {
            // Usamos un LoginContext para manejar la autenticación
            LoginContext loginContext = new LoginContext("LoginModuleName");

            // Intentamos autenticar al usuario
            loginContext.login();

            // Si la autenticación es exitosa, se puede continuar
            System.out.println("Autenticación exitosa");

        } catch (LoginException e) {
            // En caso de que falle la autenticación
            System.out.println("Fallo en la autenticación: " + e.getMessage());
        }
    }
}