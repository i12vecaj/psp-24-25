import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

public class EjemploJCE {
    public static void main(String[] args) throws Exception {

        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey claveSecreta = keyGen.generateKey();


        byte[] iv = new byte[16];
        IvParameterSpec ivSpec = new IvParameterSpec(iv);


        String textoOriginal = "Texto a cifrar";


        Cipher cifrador = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cifrador.init(Cipher.ENCRYPT_MODE, claveSecreta, ivSpec);
        byte[] textoCifrado = cifrador.doFinal(textoOriginal.getBytes());
        String textoCifradoBase64 = Base64.getEncoder().encodeToString(textoCifrado);
        System.out.println("Texto cifrado: " + textoCifradoBase64);


        cifrador.init(Cipher.DECRYPT_MODE, claveSecreta, ivSpec);
        byte[] textoDescifrado = cifrador.doFinal(Base64.getDecoder().decode(textoCifradoBase64));
        String textoDescifradoStr = new String(textoDescifrado);
        System.out.println("Texto descifrado: " + textoDescifradoStr);
    }
}
