package org.example;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class JCAJCEExample {
    public static void main(String[] args) throws Exception {
        // Generar clave AES
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);  // Tamaño de la clave en bits
        SecretKey secretKey = keyGenerator.generateKey();

        // Texto a cifrar
        String originalText = "Mensaje SuperSecreto";

        // Cifrar el texto
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(originalText.getBytes());

        // Mostrar el texto cifrado en base64
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("El mensaje cifrado es: " + encryptedText);

        // Descifrar el texto
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        String decryptedText = new String(decryptedBytes);

        // Mostrar el texto descifrado
        System.out.println("El mensaje descifrado es: " + decryptedText);
    }
}
