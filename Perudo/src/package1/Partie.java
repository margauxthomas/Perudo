package package1;


import package1.Couleur;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.Enumeration;
import java.util.HashSet;

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
    
    ArrayList<Joueur> partie= new ArrayList<>();
   
    public void addjoueur(Joueur j)
    {
       partie.add(j);
     }
    
    ArrayList<Dés> des= new ArrayList<>();
   
    public void addDés(Dés d)
    {
       des.add(d);
     }
    //HashSet<String> hset = new HashSet<String>();
    //HashMap<String,HashSet<String>> partie=new HashMap<>();
    
    private static Vector classes = new Vector();
    /*
        public void RemplirPartie(ArrayList<Joueur> jj)
    {
      
     for(Joueur J : jj){
          User utmp=J.getDude();
               //hset.clear();
               
            for(int i = 0; i <= 6; i++){
             //   Dés d= new Dés();
            
            //Dés d1 = d.getInstanceD();
             //hset.add(d1.getValeur());
            //for(Dés D :des){
            //partie.put(utmp.getPseudo(), hset);
              //  }
              //hset.clear();
              //HashSet<String> hset = new HashSet<String>();
    }
    }
    */
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
