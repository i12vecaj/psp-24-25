def udp_server():
    SERVER_IP = "127.0.0.1"  # Dirección del servidor
    SERVER_PORT = 12345  # Puerto del servidor
    BUFFER_SIZE = 1024  # Tamaño del buffer para recibir datos


    server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    server_socket.bind((SERVER_IP, SERVER_PORT))

    print("Servidor UDP en ejecución. Esperando mensajes...")
    
    while True:
        try:
            message, client_address = server_socket.recvfrom(BUFFER_SIZE)  # Recibir mensaje
            decoded_message = message.decode()
            print(f"Mensaje recibido de {client_address}: {decoded_message}")
            
            if decoded_message == "*":
                print("Cerrando servidor...")
                break  # Terminar si se recibe '*'

            response = decoded_message.upper()  # Convertir a mayúsculas
            server_socket.sendto(response.encode(), client_address)  # Enviar respuesta
        
        except socket.error as e:
            print(f"Error en el servidor: {e}")
            break
    
    server_socket.close()

if __name__ == "__main__":
    udp_server()
