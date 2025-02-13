# Revisión JD: Enhorabuena Dani.

# Ejercicio 2

This exercise serves as a "Notificator", where NotificationClient has to be executed first,
as many times as you'd like with the use of two Terminals.
When NotificationServer is executed, a default message will be sent to all clients executed
previously.

## Usage

Execute the compiled classes within the folder, you can do this with the use of terminals/consoles
or directly in your IDE. When the Client class is executed, it will act as a listener until 
Server is executed, in which the message "WARNING: NEW NOTIFICATION FROM THE SYSTEM!"
Will pop up on every client's console.

````java
        try (DatagramSocket socket = new DatagramSocket(5000)) {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            System.out.println("Client waiting for notification...");
            socket.receive(packet);

            String mensaje = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Notification received: " + mensaje);
````

## Contributing

Again, just making sure good practice is being followed.
UDP was chosen in this case, due to the conditions set by the exercise.
When speed is prioritized over security of data and arrival, and package loss is
not relevant for the good functionality of our program, UDP should be used.

