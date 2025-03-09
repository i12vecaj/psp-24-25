import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class JCACipherEjemplo {
    public static void main(String[] args) throws Exception {
        // Generar una clave AES
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // Tamaño de la clave
        SecretKey secretKey = keyGen.generateKey();

        // Datos a cifrar
        String datos = "Texto a cifrar";
        System.out.println("Texto original: " + datos);

        // Crear una instancia de Cipher para cifrar
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] datosCifrados = cipher.doFinal(datos.getBytes());
        String datosCifradosBase64 = Base64.getEncoder().encodeToString(datosCifrados);
        System.out.println("Texto cifrado: " + datosCifradosBase64);

        // Crear una instancia de Cipher para descifrar
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] datosDescifrados = cipher.doFinal(Base64.getDecoder().decode(datosCifradosBase64));
        String textoDescifrado = new String(datosDescifrados);
        System.out.println("Texto descifrado: " + textoDescifrado);
    }
}
