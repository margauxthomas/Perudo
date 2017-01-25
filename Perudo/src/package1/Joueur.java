package package1;
import java.rmi.RemoteException;
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
    private Annonce val;
  //private ArrayList<Dés> dd;
    private Integer passage;
    
    private ArrayList<String> valeurdes=new ArrayList<>();
    private ArrayList<Annonce> ann= new ArrayList<>();

     private ArrayList<Dés> dd= new ArrayList<>();
     
    public void RemplirDes(){
        for(int i = 0; i <5; i++){
        Dés d= new Dés();
        //Dés d1 = d.getInstanceD();
        dd.add(d);
        //int i=0;
        }
    }
    public void EnleverDes(){
        dd.remove(4);
    }
    public void AjouterDes(){
        Dés d= new Dés();
        dd.add(d);
    }
    public ArrayList<String> AfficherDés(){
        for (Dés D : dd) {
		String valtmp=D.getValeur();
                valeurdes.add(valtmp);
		}
        return valeurdes;
    }
   
       public Joueur(Couleur pions, User dude) {
           this.pions=pions;
           this.dude=dude;
           //this.passage=passage;
    }

    public Annonce getVal() {
        return val;
    }

    public void setVal(Annonce val) {
        this.val = val;
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

    public Integer getPassage() {
        return passage;
    }

    public void setPassage(Integer passage) {
        this.passage = passage;
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
    /*public HashMap<String, String> getJoueurs(ArrayList<Joueur> nj)
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
    } */

}
