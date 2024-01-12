import java.rmi.server.*;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class Serveur extends UnicastRemoteObject implements LifeGame {
    private BagOfTask b;
    private BagOfTask b2;
    private int courant=0;
    private int nb=0;
    private int grain;
    private int generation;
    private int[][][] tab ;
    public long start;
    Serveur(BagOfTask bot,int[][][] tab,int grain,int generation) throws RemoteException 
    { 
        super(); 
        this.b=bot;
        this.b2=new BagOfTask();
        this.tab=tab;
        this.grain=grain;
        this.generation=generation;
    }
    public void affiche()
    {
        for(int i=0;i<tab.length;i++)
          {
            for(int j=0;j<tab[i].length;j++)
             {
              for(int k=0;k<tab[i][j].length;k++)
                {
                    System.out.print(tab[j][i][k]);
                }
              System.out.println();
             } 
           System.out.println();
          }
    }
    public synchronized Tache getTask()throws RemoteException
    {
        if(this.courant==0)
             return this.b.getTackSuivante();
        else
            {
             return this.b2.getTackSuivante();
            }
    }
    public  synchronized Boolean ReturneRes(int[] res ) throws java.rmi.RemoteException
    {
        for(int i=0;i<res.length;i+=4)
          {
            this.tab[res[i+1]][res[i+2]][res[i]]=res[i+3];          
          }
        this.nb++;
        if(courant==0&&this.nb==this.b.getNb() )
          { 
         
            this.generation --;
            if(this.courant==0)
            this.courant=1;
            else
            this.courant=0; 
            this.b=new BagOfTask();
            this.b2.SetTable(this.tab);
            this.b2.diviser(this.grain);
            this.nb=0;
            //this.affiche();
          }
          else   
            if(courant==1&&this.nb==this.b2.getNb())
              {    
                this.generation --;
                if(this.courant==0)
                this.courant=1;
                else
                this.courant=0; 
                this.b2=new BagOfTask();
                this.b.SetTable(this.tab);
                this.b.diviser(this.grain);
                this.nb=0;
              //this.affiche();
              }  
        if(generation==0)
          {
            System.out.println("Resultat");
           System.out.println(((System.currentTimeMillis()-this.start)/1000)+" S");
         //  this.affiche();
           generation++;
           return false;
          }
        else
           return true;      
    }
    public static void main(String arg[]) 
    {
      int arg0=Integer.parseInt(arg[0])+2;

      int arg2=Integer.parseInt(arg[2]);
        int [][][] tab=new int[arg0][arg0][arg0];
    // int [][][] tab2=new int[32][32][32];
        for(int i=0;i<tab.length;i++)
        for(int j=0;j<tab[i].length;j++)
        for(int k=0;k<tab[i][j].length;k++)
            {
            if((int)(Math.random()*2)==1)
            tab[i][j][k]=1;
            else
            tab[i][j][k]=0;
            }
        BagOfTask bof=new BagOfTask(tab);
         int arg1= (int)Math.pow(arg0,3)/Integer.parseInt(arg[1]);
        bof.diviser(arg1);
        
        try 
        {
            Serveur s = new Serveur(bof,tab,arg0,arg2);
          //  s.affiche();
            s.start=System.currentTimeMillis();
            Registry r= LocateRegistry.createRegistry(1099);
            String nom = "Serveur";
            r.rebind(nom,s); //enregistrement
            
            System.out.println("Serveur enregistré");
        }
            
        catch (Exception e) 
        { 
                System.err.println("Erreur : "+e); 
        }
        
    }
}
