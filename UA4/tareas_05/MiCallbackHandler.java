import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;

public class MiCallbackHandler implements CallbackHandler {
    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (Callback callback : callbacks) {
            if (callback instanceof NameCallback) {
                ((NameCallback) callback).setName("usuario");
            } else if (callback instanceof PasswordCallback) {
                ((PasswordCallback) callback).setPassword("contraseña".toCharArray());
            } else {
                throw new UnsupportedCallbackException(callback, "Callback no soportado");
            }
        }
    }
}