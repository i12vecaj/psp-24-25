package adriancruz.conceptos;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;


public class SecurityExamples {
    public static void encryptDecryptExample() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        String originalText = "Texto de prueba";
        byte[] encryptedBytes = cipher.doFinal(originalText.getBytes(StandardCharsets.UTF_8));

        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        String decryptedText = new String(decryptedBytes, StandardCharsets.UTF_8);

        System.out.println("Texto original: " + originalText);
        System.out.println("Texto descifrado: " + decryptedText);
    }

    // JSSE: Conexión segura con SSL/TLS
    public static void sslExample() throws Exception {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, null, new SecureRandom());
        SSLSocketFactory factory = sslContext.getSocketFactory();
        SSLSocket socket = (SSLSocket) factory.createSocket("www.google.com", 443);
        socket.startHandshake();

        OutputStream out = socket.getOutputStream();
        out.write("GET / HTTP/1.1\r\nHost: www.google.com\r\n\r\n".getBytes());
        out.flush();

        InputStream in = socket.getInputStream();
        Scanner scanner = new Scanner(in);
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
        socket.close();
    }

    // JAAS: Autenticación de usuario
    public static void jaasExample() {
        try {
            LoginContext loginContext = new LoginContext("MyLoginModule", new SimpleCallbackHandler());
            loginContext.login();
            System.out.println("Autenticación exitosa!");
        } catch (LoginException e) {
            System.out.println("Error de autenticación: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Ejemplo JCA & JCE:");
        encryptDecryptExample();

        System.out.println("\nEjemplo JSSE:");
        sslExample();

        System.out.println("\nEjemplo JAAS:");
        jaasExample();
    }
}

