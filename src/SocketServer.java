import java.io.*;
import java.net.*;

public class SocketServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080); // Crear un servidor en el puerto 8080
            System.out.println("Servidor esperando conexiones...");

            while (true) {
                // Esperar una conexión entrante
                Socket clientSocket = serverSocket.accept();
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("Nueva Conexion Cliente");
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("Cliente conectado desde: " + clientSocket.getInetAddress()+" Puerto: "+clientSocket.getPort());

                // Aquí puedes manejar la comunicación con el cliente
                // Por ejemplo, puedes usar BufferedReader y PrintWriter para enviar y recibir datos.
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String mensajeEntrante = in.readLine();
                System.out.println("Cliente dice: " + mensajeEntrante);

                // Enviar una respuesta al cliente
                out.println("Hola, cliente");

                // Cerrar la conexión con el cliente
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

