import java.security.*;

public class ejemploJCA {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        // Genero las claves RSA pública (verificar firmas) y privadas (firmar datos)
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();

        // Crear la firma digital con la clave privada
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(keyPair.getPrivate());

        String message = "Mensaje a firmar";
        signature.update(message.getBytes());
        byte[] signedData = signature.sign();

        // Verificación de la firma con la clave pública
        signature.initVerify(keyPair.getPublic());
        signature.update(message.getBytes());
        boolean isValid = signature.verify(signedData);

        System.out.println("Firma válida: " + isValid);
    }
}
