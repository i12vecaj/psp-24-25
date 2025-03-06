import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class EjemploJCE {
    public static void main(String[] args) throws Exception {
        // Generar una clave secreta para AES
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // Tamaño de clave de 128 bits
        SecretKey claveSecreta = keyGen.generateKey();

        // Crear una instancia de Cipher para AES
        Cipher cifrador = Cipher.getInstance("AES");

        // Texto a cifrar
        String textoOriginal = "Texto de ejemplo para cifrar";
        System.out.println("Texto original: " + textoOriginal);

        // Cifrar el texto
        cifrador.init(Cipher.ENCRYPT_MODE, claveSecreta);
        byte[] textoCifrado = cifrador.doFinal(textoOriginal.getBytes());
        String textoCifradoBase64 = Base64.getEncoder().encodeToString(textoCifrado);
        System.out.println("Texto cifrado (Base64): " + textoCifradoBase64);

        // Descifrar el texto
        cifrador.init(Cipher.DECRYPT_MODE, claveSecreta);
        byte[] textoDescifrado = cifrador.doFinal(Base64.getDecoder().decode(textoCifradoBase64));
        System.out.println("Texto descifrado: " + new String(textoDescifrado));
    }
}
