import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        Socket clientSocket;
        HiloReserva hiloReserva;
        boolean end = false;
        Sala sala = new Sala();

        try {
            serverSocket = new ServerSocket(8000);
            System.out.println("Servidor disponible");
        } catch (Exception e){
            System.out.println("Error");
        }

        HiloAnfitrion hiloAnfitrion = new HiloAnfitrion(sala);
        hiloAnfitrion.start();

        while (!end)
        {
            try {
                clientSocket = serverSocket.accept();
                hiloReserva = new HiloReserva(clientSocket, sala);
                hiloReserva.start();
            } catch (Exception e){
                System.out.println("Error; no se ha podido conectar");
            }
        }
        try {
            serverSocket.close();
        } catch(Exception ignored){

        }
    }
}