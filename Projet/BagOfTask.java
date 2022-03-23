import java.util.*;
public class BagOfTask 
{
    private Hashtable<Integer,Tache> Tasks;
    private int [][][] table;
    private int nb=0;
    private int suivant=0;

    BagOfTask()
    {
        Tasks= new Hashtable<Integer, Tache>();        
    }

    BagOfTask(int[][][] table)
    {
        Tasks= new Hashtable<Integer, Tache>(); 
        this.table=table;
    }

    public int getNb()
    {
      return  this.nb;
    }

    public void setNb(int nb)
    {
        this.nb=nb;
    }

    public void setSuivant(int suivant)
    {
        this.suivant=suivant;
    }
    
    public void SetTable( int [][][] t)
    {
        this.table=t;
    }

    public int[] voisin(int x,int y,int z)
    {
      // System.out.println(x +" "+y+" "+z);
        int[] res=new int[27];
        int conteur=0;
        for(int yy=y-1;yy<=y+1;yy++)
            for(int zz=z-1;zz<=z+1;zz++){
                for(int xx=x-1;xx<=x+1;xx++)
                {
                  //  System.out.println(conteur +" "+xx+" "+zz+" "+yy);
                    res[conteur]=table[yy][zz][xx];
                    conteur++;               
               //   System.out.print(table[yy][zz][xx]+" ");
                }
               // System.out.println();
            }

        return res;
    }
          
    public void diviser(int nbSommetParTache)
    {
       int nbsommettotale=(this.table.length-2)*(this.table[0].length-2)*(this.table[0][0].length-2) ;
       int reste=(nbsommettotale) %nbSommetParTache;
       int nbsommetparcouru=0;
       int nbSousTache=nbsommettotale -reste;
       Tache t=new Tache();;
       int conteur=0;
       int nbsommetamettre =nbSommetParTache;
       int[] position;
        for(int i=1;i<table.length-1;i++)
          for(int j=1;j<table[i].length-1;j++)
            for(int k=1;k<table[i][j].length-1;k++)
            {
      
                position= new int[3];
                position[0]=k;
                position[1]=i;
                position[2]=j;
               
                t.addElement(this.voisin(k, i,j), position);   
                nbsommetamettre--;
                nbsommetparcouru++;
                if(nbsommetamettre==0)
                {     
                    Tasks.put(conteur,t);
                    conteur++;
                  //  System.out.println("tache"+conteur);
                    t=new Tache();
                    if(nbsommetparcouru<nbSousTache)
                    nbsommetamettre=nbSommetParTache;
                    else
                    if(nbsommetparcouru<nbsommettotale)
                    nbsommetamettre=reste;              
                }
             }    
             this.nb=conteur;
          //   System.out.print( this.nb);
           //  this.afficher();
    }

    public void afficher()
    {
        for(int i=0;i<this.nb;i++)
        {
        this.Tasks.get(i).Afficher();
        System.out.println(this.Tasks.get(i).getnb()+"   BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        }

        System.out.println(this.nb);
    }


    public  Tache getTackSuivante()
    {
        Tache t;
       if(this.suivant<this.nb)
       {
        //   System.out.print("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        t =Tasks.get(suivant);
       // t.Afficher();
        this.Tasks.remove(suivant);
        this.suivant++;
       }
        else
         t=new Tache();
        return t;
       
    }


    
    
}
