import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class tarea2 {
    private static final Logger LOG = Logger.getLogger(tarea2.class.getName());

    public static void main(String[] args) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            saveKeys(publicKey, privateKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static void saveKeys(PublicKey publicKey, PrivateKey privateKey) {
        String fileNamePublic = "public.key";
        String fileNamePrivate = "private.key";

        Base64.Encoder encoder = Base64.getEncoder();
        try (FileWriter out = new FileWriter(fileNamePublic)) {
            out.write("—-BEGIN RSA PUBLIC KEY—-");
            out.write("\n");

            out.write(encoder.encodeToString(publicKey.getEncoded()));
            out.write("\n");

            out.write("—-END RSA PUBLIC KEY—-");
            out.write("\n");
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }

        try (FileWriter out = new FileWriter(fileNamePrivate)) {
            out.write("—-BEGIN RSA PRIVATE KEY—-");
            out.write("\n");

            out.write(encoder.encodeToString(privateKey.getEncoded()));
            out.write("\n");

            out.write("—-END RSA PRIVATE KEY—-");
            out.write("\n");
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }

        String fileNamePublicBinary = "public.key.bin";
        String fileNamePrivateBinary = "private.key.bin";
        try (FileOutputStream out = new FileOutputStream(fileNamePublicBinary)) {
            out.write(publicKey.getEncoded());
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        try (FileOutputStream out = new FileOutputStream(fileNamePrivateBinary)) {
            out.write(privateKey.getEncoded());
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }

        LOG.info(String.format("Public key format: %s", publicKey.getFormat()));
        LOG.info(String.format("Private key format: %s", privateKey.getFormat()));
    }
}
