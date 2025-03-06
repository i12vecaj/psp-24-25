package org.example;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class JCAJCEEjemplo {
    public static void main(String[] args) throws Exception {
        // Generar una clave secreta AES de 128 bits
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // tamaño de la clave en bits
        SecretKey secretKey = keyGen.generateKey();

        // Mensaje a cifrar
        String message = "Este es un mensaje secreto";

        // Cifrado
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedMessage = cipher.doFinal(message.getBytes());

        // Mostrar mensaje cifrado
        System.out.println("Mensaje cifrado: " + Base64.getEncoder().encodeToString(encryptedMessage));

        // Descifrado
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedMessage = cipher.doFinal(encryptedMessage);

        // Mostrar mensaje descifrado
        System.out.println("Mensaje descifrado: " + new String(decryptedMessage));
    }
}