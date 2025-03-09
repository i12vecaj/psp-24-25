import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class EjercicioCon_JCA_JCE {
    public static void main(String[] args) throws Exception {
        //Genero una key secreta
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();

        Cipher cipher = Cipher.getInstance("AES");
        //mensaje que voy a cifrar
        String mensajeOriginal = "Hola, esto es un mensaje cifrado mediente JCE y JCA";

        //Aqui ciframos el texto
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] mensajeCifrado = cipher.doFinal(mensajeOriginal.getBytes());
        System.out.println("Mensaje cifrado: " + Base64.getEncoder().encodeToString(mensajeCifrado));

        //Aqui desciframos el texto
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] mensajeDescifrado = cipher.doFinal(mensajeCifrado);
        System.out.println("Mensaje descifrado: " + new String(mensajeDescifrado));
    }
}
