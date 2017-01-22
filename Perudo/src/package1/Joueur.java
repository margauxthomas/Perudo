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
    
    private Couleur pions;
    private User dude;
    
       public Joueur(Couleur pions, User dude) {
           this.pions=pions;
           this.dude=dude;
    }

    public void setDude(User dude) {
        this.dude = dude;
    }

    public User getDude() {
        return dude;
    }

    public Couleur getPions() {
        return pions;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Couleur> getCol() {
        return col;
    }

    public void setPions(Couleur pions) {
        this.pions = pions;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void setCol(ArrayList<Couleur> col) {
        this.col = col;
    }
       
       
       
     private static Vector classes = new Vector();
     
     public static Joueur getInstanceJ(Couleur pions, User dude){
            
		Joueur tmp = new Joueur(pions, dude);
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
     HashMap<String, String> h = new HashMap<>();
    //public HashMap<String, String> getJoueurs(ArrayList<User> u, ArrayList<Couleur> c)
    //public HashMap<String, String> getJoueurs(ArrayList<User> u, Joueur nj)
    public HashMap<String, String> getJoueurs(ArrayList<Joueur> nj)
    {

        //for(User R : u){
            //for(Couleur C :c){        
            for(Joueur J:nj){
                Couleur ctmp=J.getPions();
                User utmp=J.getDude();
                 h.put(utmp.getPseudo(), ctmp.getCouleur());
            }
                //Couleur ctmp=nj.getPions();
                //h.put(u.getPseudo(), ctmp.getCouleur());
              //  }
              // }       
    return h;
    } 

}
