
import java.io.Serializable;
import java.util.*;
public class Tache implements Serializable {
  private  int nbSommet =0;
  private  ArrayList<int[]> donnee;
  private ArrayList<int[]> position;

    Tache()
    {
        donnee=new ArrayList<>();
        this.position=new ArrayList<>();
    }

    public int getNbsommet()
    {
        return this.nbSommet;
    }

    public void addElement(int[] box,int[] position)
    {
        this.donnee.add(box);
        this.nbSommet++;
        this.position.add(position);
    }

    public void Afficher()
    {
        System.out.println("il y'a en tous "+this.nbSommet);
        for(int i=0; i<donnee.size();i++)
        {
        System.out.println(Arrays.toString( donnee.get(i))+"  "+Arrays.toString(position.get(i)));

        }



    }    
public int[] traiter( int vie,int  mort)
{

 int res[]=new int [this.nbSommet*4];
 int[] donnee;
 int[] position;
 int somme;
 int j=0;
 for(int i=0; i<this.donnee.size();i++)
          {
            donnee  =this.donnee.get(i);
            position=this.position.get(i);
           
             res[j+1]=position[1];
             res[j+2]=position[2];
             res[j]=position[0];
             somme=0;
             for(int k=0;k<27;k++)
             somme+=donnee[k];

             somme=somme -donnee[13];

             if(somme>vie||somme<mort)
             res[j+3]=0;
             else
             if(somme==vie)
             res[j+3]=1;
             else
             res[j+3]=donnee[13];
             j+=4;
        
            }
return res;
}

    public int getnb()
{
    return nbSommet;
}

}
