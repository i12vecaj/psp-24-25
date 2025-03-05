import javax.security.auth.callback.*;

public class SimpleCallbackHandler implements CallbackHandler {
    private final String usuario;
    private final String clave;

    public SimpleCallbackHandler(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    @Override
    public void handle(Callback[] callbacks) throws UnsupportedCallbackException {
        for (Callback callback : callbacks) {
            if (callback instanceof NameCallback) {
                ((NameCallback) callback).setName(usuario);
            } else if (callback instanceof PasswordCallback) {
                ((PasswordCallback) callback).setPassword(clave.toCharArray());
            } else {
                throw new UnsupportedCallbackException(callback, "Callback no soportado");
            }
        }
    }
}