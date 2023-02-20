import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Sala {
    private ArrayList<Integer> disponibles;

    //Esta lista de locks es para que cada mesa tenga su propio lock, asi se podria en un futuro
    //desbloquear una mesa en concreto cuando los clientes acaben, si no tambien se pueden eliminar las mesas
    //cuando una de estas sea ocupada y luego añadir nuevas pero esta solucion me parece mas elegante
    private ArrayList<ReentrantLock> locks;
    public Sala(){
        disponibles = new ArrayList<>();
        locks = new ArrayList<>();
    }

    synchronized void putMesa(int comensales){
        disponibles.add(comensales);
        locks.add(new ReentrantLock());
        notifyAll();
    }


    synchronized boolean getMesa(int comensales){
        for (int i = 0; i < disponibles.size(); i++) {
            if (!locks.get(i).isLocked() && disponibles.get(i) == comensales) {
                System.out.println("Mesa " + i + " asignada");
                ReentrantLock lock = locks.get(i);
                lock.lock();
                return true;
            }
        }
        return false;
    }

    synchronized void waitForNewTables() throws InterruptedException {
        wait();
    }
}
