import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class JCAExample {
    public static void main(String[] args) throws Exception {
        // Generar clave AES
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();

        // Mensaje a cifrar
        String mensaje = "Hola, seguridad en Java!";
        
        // Cifrado
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] mensajeCifrado = cipher.doFinal(mensaje.getBytes());
        String mensajeCifradoBase64 = Base64.getEncoder().encodeToString(mensajeCifrado);
        
        System.out.println("Mensaje Cifrado: " + mensajeCifradoBase64);
        
        // Descifrado
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] mensajeDescifrado = cipher.doFinal(Base64.getDecoder().decode(mensajeCifradoBase64));
        
        System.out.println("Mensaje Descifrado: " + new String(mensajeDescifrado));
    }
}