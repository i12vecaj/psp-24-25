import java.io.Serializable;

public class Token implements Serializable {
    int idToken;
    String usuarioActual;
    String usuarioAnterior;

    public Token(int idToken, String usuarioActual, String usuarioAnterior) {
        this.idToken = idToken;
        this.usuarioActual = usuarioActual;
        this.usuarioAnterior = usuarioAnterior;
    }
}