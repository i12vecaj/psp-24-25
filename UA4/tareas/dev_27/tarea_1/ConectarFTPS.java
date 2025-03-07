package com.ceslopedevega.ftp;

import org.apache.commons.net.ftp.FTPSClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConectarFTPS {
    public static void main(String[] args) {
        // Iniciamos el cliente FTPS con modo explícito
        FTPSClient cliente = new FTPSClient(false); // Cambiar a 'true' si el servidor requiere SSL/TLS implícito

        String servidor = "127.0.0.1"; // Dirección del servidor FTPS
        String usuario = "prueba"; // Usuario de FTP
        String clave = "root"; // Contraseña de FTP
        String carpetaDestino = "/filezilla"; // Ruta en el servidor
        String archivoLocal = "C:\\Users\\home\\Desktop\\pruebafilezilla.txt"; // Archivo local a subir
        String nombreArchivoRemoto = "archivo_servidor.txt"; // Nombre del archivo en el servidor

        try {
            System.out.println("⏳ Conectando a " + servidor + " con FTPS...");
            cliente.connect(servidor, 21); // Cambiar a 990 si es FTPS implícito
            cliente.enterLocalPassiveMode();

            // 🔐 Habilitar TLS para FTPS explícito
            cliente.execAUTH("TLS");

            // Establecer protección de datos
            cliente.execPBSZ(0);
            cliente.execPROT("P"); // Usa "C" si el servidor no soporta "P"

            // Autenticación
            boolean login = cliente.login(usuario, clave);
            if (!login) {
                System.out.println("❌ Login incorrecto.");
                return;
            }
            System.out.println("✅ Login correcto en FTPS.");

            // 📍 Mostrar el directorio actual
            System.out.println("📍 Directorio actual: " + cliente.printWorkingDirectory());

            // Cambiar de directorio en el servidor
            if (!cliente.changeWorkingDirectory(carpetaDestino)) {
                System.out.println("❌ No se pudo cambiar de directorio.");
                return;
            }
            System.out.println("📂 Directorio cambiado a: " + carpetaDestino);

            // Verificar si el archivo local existe antes de subirlo
            File file = new File(archivoLocal);
            if (!file.exists()) {
                System.out.println("❌ Error: El archivo local no existe.");
                return;
            }

            // Subir el archivo al servidor
            try (InputStream archivoStream = new FileInputStream(file)) {
                boolean subida = cliente.storeFile(nombreArchivoRemoto, archivoStream);
                if (subida) {
                    System.out.println("✅ Archivo subido correctamente.");
                } else {
                    System.out.println("❌ Error al subir el archivo.");
                }
            }

            // Mostrar respuesta del servidor
            System.out.println("📜 Respuesta del servidor: " + cliente.getReplyString());

            // Cerrar sesión y desconectar
            cliente.logout();
            cliente.disconnect();
            System.out.println("🔌 Desconectado del servidor.");
        } catch (IOException e) {
            System.out.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

