package package1;


import package1.Couleur;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.Enumeration;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author stri
 */
public class Partie{
   
    private String nom;
    private String desc;
    
    ArrayList<Joueur> joueurs= new ArrayList<>();
   
    public void addjoueur(Joueur j)
    {
       joueurs.add(j);
     }
    
    ArrayList<Dés> des= new ArrayList<>();
   
    public void addDés(Dés d)
    {
       des.add(d);
     }
    
    HashMap<Joueur, String> partie=new HashMap<>();
    private static Vector classes = new Vector();
    
        public void addés()
    {
        int i = 0;
     for(Joueur J : joueurs){
         while(i<5){
            for(Dés D :des){
                partie.put(J, D.getValeur());
                }
            }
         i++;
     }
     
    }
    
    public Partie(String n, String d)  {
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

    
    public static Partie getInstance(String attribut1, String attribut2) {
            
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
