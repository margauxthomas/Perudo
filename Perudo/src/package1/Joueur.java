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
    private ArrayList<Dés> dd;

    public Joueur(Couleur pions, User dude,ArrayList<Dés> dd, Integer passage) {
        this.pions = pions;
        this.dude = dude;
        this.passage = passage;
        this.dd = dd;
    }
      
      
    private ArrayList<String> valeurdes=new ArrayList<>();
    private ArrayList<Annonce> ann= new ArrayList<>();
    /*
    public Joueur(Couleur pions, User dude, ArrayList<Dés> dd, Integer passage){
           this.pions=pions;
           this.dude=dude;
           this.dd=dd;
           this.passage=passage;
           //this.passage=passage;
    }
    */
     
    public ArrayList<Dés> RemplirDes(){
        
        for(int i = 0; i <5; i++){
        Dés d= new Dés("0");
        String v=d.getValeur();
        d.setValeur(v);
        //Dés d1 = d.getInstanceD();
        this.dd.add(d);
        setDd(dd);
        //int i=0;
        }
        return this.dd;
    }
    public ArrayList<Dés> ReRemplirDés(){
        for (Dés D : this.dd) {
                String v=D.getValeur();
                D.setValeur(v);
		}
        return this.dd;
    }
    public void ViderD(){
        this.dd.clear();
    }

    public ArrayList<Dés> getDd() {
        return dd;
    }

    public void setDd(ArrayList<Dés> dd) {
        this.dd = dd;
    }
    

    
    public void EnleverDes(){
       int tmp;
        
        tmp=this.dd.size();
        this.dd.clear();
        for(int i = 0; i <tmp-1; i++){
            Dés d= new Dés("0");
            this.dd.add(d);
        }
        
    }
    public void AjouterDes(){
        Dés d= new Dés("0");
        String v=d.getValeur();
        d.setValeur(v);
        this.dd.add(d);
    }
    public ArrayList<String> AfficherDés(){
        
        return valeurdes;
    }
    public ArrayList<String> DonnerValeur(){
        valeurdes.clear();
        for (Dés D : this.dd) {
		//String valtmp=D.getValeur();
                valeurdes.add(D.getVal());
		}
        return valeurdes;
    }
   


    public Annonce getVal() {
        return val;
    }

    public void setVal(Annonce val) {
        this.val = val;
    }
    public Integer GetSize(){
        return dd.size();
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
     
     public static Joueur getInstanceJ(Couleur pions, User dude, ArrayList<Dés> dd, Integer passage){
            
        Joueur tmp = new Joueur(pions, dude, dd, passage);
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
