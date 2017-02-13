package package1;


import package1.Couleur;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.Enumeration;
import java.util.HashSet;

public class Partie{ 
    
    private Integer pid;
    private ArrayList<Joueur> aj = new ArrayList<>();
    private Integer passage;
    private Integer compteur;
    private Integer cas2;
    private Integer cas3;
    private Integer indice;
    private Boolean resultat;
    private ArrayList<String> valdeworld = new ArrayList<>();
    private ArrayList<Annonce> ttlesann = new ArrayList<>();
   
    public void addjoueur(Joueur j){
       this.aj.add(j);
    }

    public void adddes(String val){
       this.valdeworld.add(val);
    }
    
    public void addAnn(Annonce a){
        this.ttlesann.add(a);
    }
    public ArrayList<String> getValdeworld() {
        return valdeworld;
    }

    public void setValdeworld(ArrayList<String> valdeworld) {
        this.valdeworld = valdeworld;
    }

    public ArrayList<Annonce> getTtlesann() {
        return ttlesann;
    }

    public void setTtlesann(ArrayList<Annonce> ttlesann) {
        this.ttlesann = ttlesann;
    }
    public void cleartttlesann(){
        this.ttlesann.clear();
    }
    
    
    private static Vector classes = new Vector();

    public Partie(Integer pid, ArrayList<Joueur> aj, Integer passage, Integer compteur, Boolean resultat, Integer cas2, Integer cas3, Integer indice, ArrayList<String> valdesworld, ArrayList<Annonce> ttlesann){
        this.pid=pid;
        this.aj=aj;
        this.passage=passage;
        this.compteur=compteur;
        this.resultat=resultat;
        this.cas2=cas2;
        this.cas3=cas3;
        this.indice=indice;
        this.valdeworld=valdeworld;
        this.ttlesann=ttlesann;
    }
    
    
/*
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
*/

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public ArrayList<Joueur> getAj() {
        return aj;
    }

    public void setAj(ArrayList<Joueur> aj) {
        this.aj = aj;
    }

    public Integer getPassage() {
        return passage;
    }

    public void setPassage(Integer passage) {
        this.passage = passage;
    }

    public Integer getCompteur() {
        return compteur;
    }

    public void setCompteur(Integer compteur) {
        this.compteur = compteur;
    }

    public Integer getCas2() {
        return cas2;
    }

    public void setCas2(Integer cas2) {
        this.cas2 = cas2;
    }

    public Integer getCas3() {
        return cas3;
    }

    public void setCas3(Integer cas3) {
        this.cas3 = cas3;
    }

    public Integer getIndice() {
        return indice;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public Boolean getResultat() {
        return resultat;
    }

    public void setResultat(Boolean resultat) {
        this.resultat = resultat;
    }
 
}
