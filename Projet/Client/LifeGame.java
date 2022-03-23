
import java.rmi.*;
public interface LifeGame extends Remote {
    public Tache getTask()throws java.rmi.RemoteException;

 //   public void ReturneRes(int[][][] res, int nbSommetParTache)throws java.rmi.RemoteException;

    public Boolean ReturneRes(int[] res)throws java.rmi.RemoteException;
}
