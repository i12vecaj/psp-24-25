import socket

def udp_client():
    SERVER_IP = "127.0.0.1"  # Dirección del servidor
    SERVER_PORT = 12345  # Puerto del servidor
    BUFFER_SIZE = 1024  # Tamaño del buffer para recibir datos
    TIMEOUT = 5  # Tiempo de espera en segundos


    client_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    client_socket.settimeout(TIMEOUT)  # Establecer tiempo de espera

    print("Cliente UDP iniciado. Escribe mensajes para enviar al servidor. Escribe '*' para salir.")
    
    while True:
        try:
            message = input("> ")  # Leer entrada del usuario
            client_socket.sendto(message.encode(), (SERVER_IP, SERVER_PORT))  # Enviar mensaje al servidor
            
            if message == "*":
                print("Cerrando cliente...")
                break  # Terminar si el usuario introduce '*'

            response, server_address = client_socket.recvfrom(BUFFER_SIZE)  # Recibir respuesta
            print(f"Servidor: {response.decode()}")
        
        except socket.timeout:
            print("Tiempo de espera agotado. No se recibió respuesta del servidor.")
        except socket.error as e:
            print(f"Error de conexión: {e}")
            break
    
    client_socket.close()

if __name__ == "__main__":
    udp_client()
