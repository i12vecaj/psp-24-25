import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class JCEExample {
    public static void main(String[] args) throws Exception {
        // Generar una clave AES
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256); // Tamaño de clave
        SecretKey secretKey = keyGen.generateKey();

        // Mensaje a cifrar
        String mensaje = "Mensaje Secreto";

        // Cifrado
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] mensajeCifrado = cipher.doFinal(mensaje.getBytes());

        // Convertir a Base64 para mostrar
        String mensajeCifradoBase64 = Base64.getEncoder().encodeToString(mensajeCifrado);
        System.out.println("Mensaje cifrado: " + mensajeCifradoBase64);

        // Descifrado
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] mensajeDescifrado = cipher.doFinal(Base64.getDecoder().decode(mensajeCifradoBase64));
        System.out.println("Mensaje descifrado: " + new String(mensajeDescifrado));
    }
}

