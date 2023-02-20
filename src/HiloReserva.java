import Principal.Reserva;

import java.io.*;
import java.net.Socket;

public class HiloReserva extends Thread{

    Socket clientSocket;
    Sala sala;
    Reserva reserva;

    public HiloReserva(Socket clientSocket, Sala sala) {
        this.clientSocket = clientSocket;
        this.sala = sala;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            reserva = (Reserva) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            clientSocket.close();
            while (!sala.getMesa(reserva.getComensales())) {
                sala.waitForNewTables();
                System.out.println("Buscando mesas disponibles para " + reserva.getComensales() + " comensales");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
