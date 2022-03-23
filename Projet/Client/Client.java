import java.rmi.*;
public class Client {
    public static void main(String arg[]) {
        try{        
               LifeGame h = (LifeGame) Naming.lookup("rmi://localhost:1099/Serveur");     
                boolean arreter=true;
                while(arreter)
                {
                    Tache  T = h.getTask();
                  //T.Afficher();
                    if(T.getNbsommet()!=0)
                        arreter= h.ReturneRes(T.traiter(Integer.parseInt(arg[0]),Integer.parseInt(arg[1])));
                }
           }
        catch (Exception e) { 
            System.err.println("Erreur: " +e); 
        }
    }
}
