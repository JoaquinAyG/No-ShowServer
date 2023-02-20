import Principal.Reserva;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

public class HiloAnfitrion extends Thread{
    private Sala sala;
    private boolean end = false;

    public HiloAnfitrion(Sala sala) {
        this.sala = sala;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (!end) {
            System.out.println("Introduce el número de sitios disponibles en la mesa");
            int num = readNum(sc);

            if (num < 0){
                end = true;
                return;
            }
            sala.putMesa(num);
        }

        System.out.println("El local ha cerrado, ya no ofreceremos más mesas");
    }
    public int readNum(Scanner sc){
        while (true){
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número");
            }
        }
    }
}
