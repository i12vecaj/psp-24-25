import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class clienteUDP {
    public static void main(String[] argv) throws Exception {
        hilo h = new hilo();
        h.start();
    }
}
class hilo extends Thread{
    public void run(){
        try {
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");
            while (true) {
                System.out.print("Escribe un mensaje: ");
                String sentence = inFromUser.readLine();

                byte[] sendData = new byte[sentence.length()];
                sendData = sentence.getBytes();
                byte[] receiveData = new byte[sentence.length()];

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
                clientSocket.send(sendPacket);
                if(sentence.equals("*")){
                    break;
                }
                this.sleep(2000);
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String modifiedSentence = new String(receivePacket.getData());

                System.out.println("Respuesta desde el servidor:" + modifiedSentence);
            }
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
