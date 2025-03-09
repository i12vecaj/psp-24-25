import javax.security.auth.callback.*;
import java.io.*;

public class SimpleCallbackHandler implements CallbackHandler {
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (Callback callback : callbacks) {
            if (callback instanceof NameCallback) {
                System.out.print("Usuario: ");
                ((NameCallback) callback).setName(new BufferedReader(new InputStreamReader(System.in)).readLine());
            } else if (callback instanceof PasswordCallback) {
                System.out.print("Contraseña: ");
                ((PasswordCallback) callback).setPassword(new BufferedReader(new InputStreamReader(System.in)).readLine().toCharArray());
            } else {
                throw new UnsupportedCallbackException(callback);
            }
        }
    }
}
