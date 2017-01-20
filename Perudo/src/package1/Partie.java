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
public class Partie extends RMIServer{
   
    private String nom;
    private String desc;
    
    HashMap<Joueur, Couleur> partie=new HashMap<>();
    private static Vector classes = new Vector();
    
    public Partie(String n, String d) throws RemoteException {
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

    public HashMap<Joueur, Couleur> getPartie() {
        return partie;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPartie(HashMap<Joueur, Couleur> partie) {
        this.partie = partie;
    }
    
    public static Partie getInstance(String attribut1, String attribut2) throws RemoteException {
            
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
