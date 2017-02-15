package package1;


import package1.Couleur;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.Enumeration;
import java.util.HashSet;

public class Partie{ 
    //constructeur, une partie est compose de
    private Integer pid;//numero d'identification de la partie
    private ArrayList<Joueur> aj = new ArrayList<>();//Une liste de joueur(objet)
    private Integer passage;//un numero de passage a attribué au joueur
    private Integer compteur;//un jeton pour l'odre de passage 
    private Integer cas2;//Indique si le cas menteur est annonce
    private Integer cas3;//Indique si le cas tt pile est annonce
    private Integer indice;//Indique le debut d'un tour
    private Boolean resultat;//resultat de la comparaison apres menteur ou tt pile 
    private ArrayList<String> valdeworld = new ArrayList<>();//Les valeurs des des en jeu
    private ArrayList<Annonce> ttlesann = new ArrayList<>();//les annonces des joueurs
   
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
