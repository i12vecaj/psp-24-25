import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class JCAC {
    public static void main(String[] args) throws Exception {
        // Generar una clave AES
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // Tamaño de la clave
        SecretKey secretKey = keyGen.generateKey();

        // Crear un cifrador
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Texto a cifrar
        String textoOriginal = "Hola, mundo!";
        byte[] textoCifrado = cipher.doFinal(textoOriginal.getBytes());

        // Mostrar el texto cifrado en Base64
        String textoCifradoBase64 = Base64.getEncoder().encodeToString(textoCifrado);
        System.out.println("Texto cifrado: " + textoCifradoBase64);

        // Descifrar el texto
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] textoDescifrado = cipher.doFinal(Base64.getDecoder().decode(textoCifradoBase64));
        System.out.println("Texto descifrado: " + new String(textoDescifrado));
    }
}