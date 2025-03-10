import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class JCA_JCEExample {
    public static void main(String[] args) throws Exception {
        // Generar una clave simétrica AES
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // Usamos una clave de 128 bits
        SecretKey secretKey = keyGenerator.generateKey();

        // Cifrar un texto
        String originalText = "Texto confidencial";
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedText = cipher.doFinal(originalText.getBytes());

        // Mostrar el texto cifrado en Base64
        String encryptedBase64 = Base64.getEncoder().encodeToString(encryptedText);
        System.out.println("Texto cifrado (Base64): " + encryptedBase64);

        // Descifrar el texto
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedText = cipher.doFinal(Base64.getDecoder().decode(encryptedBase64));
        System.out.println("Texto descifrado: " + new String(decryptedText));
    }
}
