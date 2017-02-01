package package1;


import package1.Couleur;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.Enumeration;
import java.util.HashSet;

public class Partie{ 
    private String nom;
    private String desc;
    
    ArrayList<Joueur> partie= new ArrayList<>();
    ArrayList<Dés> des= new ArrayList<>();
   
    public void addjoueur(Joueur j){
       partie.add(j);
    }
    
    public void addDés(Dés d){
       des.add(d);
    }

    private static Vector classes = new Vector();

    public Partie(String n, String d){
        super();
        this.nom=n;
        this.desc=d;
    }

    public String getNom() {
        return nom;
    }

    public String getDesc() {
        return desc;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static Partie getInstanceP(String attribut1, String attribut2) {            
	Partie tmp = new Partie(attribut1, attribut2);
	if (classes.contains(tmp)) {
            Enumeration enume = classes.elements();
            while (enume.hasMoreElements()) {
                Partie element = (Partie) enume.nextElement();
                if (element.equals(tmp)) {
                    return element;
                }
            }
        }
        else {
            classes.add(tmp);
            return tmp;
        }
        return null;
    }
 
}
