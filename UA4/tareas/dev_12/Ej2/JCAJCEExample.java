package UA5_T1;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class JCAJCEExample {
    public static void main(String[] args) throws Exception {
        // Generación de clave secreta para AES
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);  // Usando una clave de 128 bits
        SecretKey secretKey = keyGenerator.generateKey();
        
        // Mensaje a cifrar
        String mensajeOriginal = "Este es un mensaje secreto";
        
        // Cifrado con AES
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedMessage = cipher.doFinal(mensajeOriginal.getBytes());
        String encryptedMessageBase64 = Base64.getEncoder().encodeToString(encryptedMessage);
        
        System.out.println("Mensaje cifrado: " + encryptedMessageBase64);
        
        // Descifrado con AES
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedMessage = cipher.doFinal(Base64.getDecoder().decode(encryptedMessageBase64));
        String decryptedMessageString = new String(decryptedMessage);
        
        System.out.println("Mensaje descifrado: " + decryptedMessageString);
    }
}
