package package1;

import java.util.Scanner;
import java.util.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author stri
 */
public class Joueur {
    
       public Joueur() {
    }
     private static Vector classes = new Vector();
     
     public static Joueur getInstanceJ(){
            
		Joueur tmp = new Joueur();
		if (classes.contains(tmp)) {
			Enumeration enume = classes.elements();
			while (enume.hasMoreElements()) {
				Joueur element = (Joueur) enume.nextElement();
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
       
    ArrayList<User> users= new ArrayList<>();
   
    public void addUsers(User u)
    {
       users.add(u);
     }
 
    
    ArrayList<Couleur> col= new ArrayList<>();
   
    public void addCouleur(Couleur c)
    {
       col.add(c);
     }
    
    public HashMap<String, String> getJoueurs(ArrayList<User> u, ArrayList<Couleur> c)
     {
        
        HashMap<String, String> h = new HashMap<>(); 
        for(User R : u){
            for(Couleur C :c){
                h.put(R.getPseudo(), C.getCouleur());
                }
            }       
    return h;
    } 

}
