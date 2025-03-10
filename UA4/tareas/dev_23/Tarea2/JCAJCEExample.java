import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class JCAJCEExample {
  public static void main(String[] args) throws Exception {
    // Generar una clave secreta AES
    KeyGenerator keyGen = KeyGenerator.getInstance("AES");
    keyGen.init(256);
    SecretKey secretKey = keyGen.generateKey();

    // Mensaje a cifrar
    String mensaje = "Hola, este es un mensaje seguro!";

    // Cifrado
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
    byte[] mensajeCifrado = cipher.doFinal(mensaje.getBytes());

    System.out.println("Mensaje Cifrado: " + Base64.getEncoder().encodeToString(mensajeCifrado));

    // Descifrado
    cipher.init(Cipher.DECRYPT_MODE, secretKey);
    byte[] mensajeDescifrado = cipher.doFinal(mensajeCifrado);

    System.out.println("Mensaje Descifrado: " + new String(mensajeDescifrado));
  }
}
