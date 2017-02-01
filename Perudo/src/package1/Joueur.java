package package1;

import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.*;

public class Joueur {
    
    private Couleur pions;
    private User dude;
    private Annonce val;
    private Integer passage;
    private ArrayList<Dés> dd;
    private ArrayList<String> valeurdes=new ArrayList<>();
    private ArrayList<Annonce> ann= new ArrayList<>();
    ArrayList<User> users= new ArrayList<>();
    ArrayList<Couleur> col= new ArrayList<>();
    HashMap<String, String> h = new HashMap<>();
    
    public Joueur(Couleur pions, User dude,ArrayList<Dés> dd, Integer passage) {
        this.pions = pions;
        this.dude = dude;
        this.passage = passage;
        this.dd = dd;
    }
      
    public ArrayList<Dés> RemplirDes(){
        for(int i = 0; i <5; i++){
            Dés d= new Dés("0");
            String v=d.getValeur();
            d.setValeur(v);
            this.dd.add(d);
            setDd(dd);
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

    public void addUsers(User u){
       users.add(u);
    }

    public void addCouleur(Couleur c){
       col.add(c);
    }
    
}
