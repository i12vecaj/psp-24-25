package dev_20.ua3tarea1;

import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class ua3tarea1 {
    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        String input = "https://github.com/i12vecaj/psp-24-25/tree/dev_20/UA3";
        boolean salir = false;
        while(!salir){
            System.out.print("Introduzca una url o una ip (utilize localhost para salir):");
            input = scann.nextLine();
            if(!Objects.equals(input, "localhost")){
             try{
                 URI uri = new URI(input);
                 URL url = uri.toURL();
                 System.out.println("La autoridad es de:" + url.getAuthority());
                 System.out.println("El path es:" + url.getPath());
                 System.out.println("El host es:" +url.getHost());
             } catch (Exception e) {
                 try{
                     InetAddress address = InetAddress.getByName(input);
                     System.out.println("El hostname es: " + address.getHostName());
                     System.out.println("Es una red local: " +address.isLinkLocalAddress());
                 } catch (Exception ex) {
                     System.out.println("No se ha podido detectar si lo introducido es una ip o una url, intente de nuevo.");
                 }
             }
            }else{
                salir = true;
                System.out.println("Espero volverlo a ver.");
            }
        }
    }
}
