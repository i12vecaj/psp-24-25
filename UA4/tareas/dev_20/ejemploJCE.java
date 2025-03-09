import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class ejemploJCE {
    public static void main(String[] args) throws Exception {
        String mensaje = "Texto secreto";

        // Generar clave AES
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();

        // Cifrar el mensaje
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(mensaje.getBytes());

        // Convertir a Base64 para impresión
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("Texto cifrado: " + encryptedText);

        // Descifrar el mensaje
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        System.out.println("Texto descifrado: " + new String(decryptedBytes));
    }
}
