import javax.security.auth.callback.*;

public class SimpleCallbackHandler implements CallbackHandler {
    @Override
    public void handle(Callback[] callbacks) throws UnsupportedCallbackException {
        for (Callback callback : callbacks) {
            if (callback instanceof NameCallback) {
                NameCallback nameCallback = (NameCallback) callback;
                nameCallback.setName("usuario"); // Nombre de usuario
            } else if (callback instanceof PasswordCallback) {
                PasswordCallback passwordCallback = (PasswordCallback) callback;
                passwordCallback.setPassword("contraseña".toCharArray()); // Contraseña
            } else {
                throw new UnsupportedCallbackException(callback, "Tipo de callback no soportado");
            }
        }
    }
}
