import java.io.Serializable;

public class Token implements Serializable {
    int id;
    String usuarioActual;
    String usuarioAnterior;

    public Token(int id, String usuarioActual, String usuarioAnterior) {
        this.id = id;
        this.usuarioActual = usuarioActual;
        this.usuarioAnterior = usuarioAnterior;
    }
}
