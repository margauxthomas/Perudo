package package1;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author stri
 */
public class RMIServer extends UnicastRemoteObject implements RMI{

    public RMIServer() throws RemoteException{
        super();
    }

    
    public String getData(String text) throws RemoteException {
       text = "Hi "+text;
       return text;
    }
    
  /*  public String getEnchere(int nbDé,int faceDé) throws RemoteException {
     String Dé = Integer.toString(nbDé);
      String faceduDé =Integer.toString(faceDé);
      return "Vous avez annoncé " + Dé+" dés " +faceduDé;
      
    }*/
    
    
    public String setPseudo(String pseu) throws RemoteException {
        
        pseu="Bienvenue dans le jeu du Perudo "+pseu;
   
        return pseu;
    }
    
    public String setCouleur(String col) throws RemoteException {
        col=". Vous jouez maintenant avec les "+col+" vous disposez de 5 dés au départ, ne les perdez pas, bonne chance !";
        
        return col;
    }
    
    //déclaration de tout les trucs 
    
    ArrayList<User> users= new ArrayList<>();
    ArrayList<String> pseudo= new ArrayList<>();
    ArrayList<String> flag= new ArrayList<>();
    ArrayList<Couleur> cols= new ArrayList<>();
    ArrayList<Joueur> joueurs= new ArrayList<>();
    ArrayList<String> valeurdesj = new ArrayList<>();
    ArrayList<String> valdesworld = new ArrayList<>();
    ArrayList<Annonce> ttlesann = new ArrayList<>();
    Partie  p= new Partie("prems","test");
    Integer passage=1;
    Integer compteur=1;
    Boolean resultat;
    //RMIServer rmiserver = new RMIServer();
    //Joueur jtest;
    
    public ArrayList<String> getUser(){
        return pseudo;
    }
    
    public ArrayList<String> getCouleurs(){
        return flag;
    }
     
    public void CreerJoueur(String pseu, String col) throws RemoteException{
        
        pseudo.add(pseu);
        flag.add(col);
        User u = new User(pseu);
        //User us= User.getInstanceU(pseu);
        Couleur c = new Couleur(col);
        //Couleur co = Couleur.getInstanceC(col);

        //ajout dans l'arret liste
         users.add(u);
         cols.add(c);
         
        //creation joueur avec les attributs declaré 
         Joueur j = new Joueur(c,u);
         //Joueur nj= Joueur.getInstanceJ(co,us);
         joueurs.add(j);
         p.addjoueur(j);
        
    }
    public Integer CompteJoueur(){
        int element=joueurs.size();
        return element;
    }
         //faire une methode appeler atttribution des qui en fonction parcour l'arraylist
         //des joueurs et attribut à chacun des des
    
 
        //methode affichant les des en fonction d'un joueur en particulier
        
    public ArrayList<String> AfficherDesJoueur(String attri1, String attri2)throws RemoteException{
        Couleur cattri1=Couleur.getInstanceC(attri1);
        User uattri2=User.getInstanceU(attri2);
                
        Joueur nj=Joueur.getInstanceJ(cattri1, uattri2);
        nj.RemplirDes();
         valeurdesj=nj.AfficherDés();
        for (String val : valeurdesj) {
                        valdesworld.add(val);
			System.out.println(val);
	}
        
        return valeurdesj;
    }
    
    public Integer SetOrdre(String attri1, String attri2)throws RemoteException{
        Couleur cattri1=Couleur.getInstanceC(attri1);
        User uattri2=User.getInstanceU(attri2);        
        Joueur nj=Joueur.getInstanceJ(cattri1, uattri2);
        System.out.println("Attributiion numéro de passage "+passage);
        nj.setPassage(passage);
        passage++;
        Integer numero;
        numero=nj.getPassage();
        System.out.println("num du joueur"+numero);
        return numero;
    }
    
    public Boolean RetrouverOrdreJoueur(Integer numero)throws RemoteException{
       
        
        Boolean ordre;
        
        System.out.println("numero de passage "+numero);
        System.out.println("jeton :"+compteur);
        if(Objects.equals(numero, compteur)){
            ordre=true;
        }else ordre=false;
        System.out.println("Resultat ordre: "+ordre);
         
        return ordre;
    }
    
    
    
    
    
     //methode affichant les des de tt les joueurs 
    
    public ArrayList<String> AfficherToutDes(){
        
        for(String vald: valdesworld){
            System.out.println("des tt le monde :" +vald);
                }
        
        return valdesworld;
    }
    public boolean OnCompte(Integer numj) throws RemoteException{
        RecupererLastJoueur(numj);
        return resultat;
    }
    
    public void RecupererLastJoueur(Integer numj) throws RemoteException{
        String pseulj=null;
        String collj=null;
        
        String pseujec=null;
        String coljec=null;
        
            for(Joueur J :joueurs){
                if(J.getPassage().equals(numj-1)){
                    pseulj=J.getDude().getPseudo();
                    collj=J.getPions().getCouleur();
                    RecupererAnnonce(pseulj,collj);
                }
                if(J.getPassage().equals(numj)){
                    pseujec=J.getDude().getPseudo();
                    coljec=J.getPions().getCouleur();
                }
                }
            if(resultat){
                EnleverDes(pseujec, coljec);
            }else EnleverDes(pseulj,collj );
    }
    
    public void RecupererAnnonce(String attri1, String attri2){
        int nb;
        int val;
        
        Couleur cattri1=Couleur.getInstanceC(attri1);
        User uattri2=User.getInstanceU(attri2);
                
        Joueur nj=Joueur.getInstanceJ(cattri1, uattri2);
        nb=nj.getVal().getDé();
        val=nj.getVal().getAnnValDé();
        resultat=Comparaison(nb,val);
  
    }
    
    
    
    public Boolean Comparaison(Integer nb, Integer val){
        String v =Integer.toString(val);
        int c=0;
        Boolean resultat=null;
        for(String vald: valdesworld){
            if(vald.equals("perudo")){
                vald=v;
                if (vald.equals(v)){
                c++;
                }
            }else if(vald.equals(v)){
                c++;
                }
            }
        System.out.println("nombre "+c);
        if(c<nb){
            resultat=false;
        }else resultat=true;
    return resultat;
    }
    
    public void EnleverDes(String attri1, String attri2)throws RemoteException{
        Couleur cattri1=Couleur.getInstanceC(attri1);
        User uattri2=User.getInstanceU(attri2);        
        Joueur nj=Joueur.getInstanceJ(cattri1, uattri2);
        nj.EnleverDes();
        
        for(int i=0;i<nj.getPassage();i++){
            int tmp;
            tmp= joueurs.get(i).getPassage();
            joueurs.get(i).setPassage(tmp+1);
        }
        nj.setPassage(1);
    }
    
    public void AjouterDes(String attri1, String attri2)throws RemoteException{
        Couleur cattri1=Couleur.getInstanceC(attri1);
        User uattri2=User.getInstanceU(attri2);        
        Joueur nj=Joueur.getInstanceJ(cattri1, uattri2);
        nj.AjouterDes();
            for(int i=0;i<nj.getPassage();i++){
            int tmp;
            tmp= joueurs.get(i).getPassage();
            joueurs.get(i).setPassage(tmp+1);
        }
        nj.setPassage(1);
    }
    
    public String RecuperationAnn(int nombreDé, int faceduDé, String attri1, String attri2) throws RemoteException {
        
        Couleur cattri1=Couleur.getInstanceC(attri1);
        User uattri2=User.getInstanceU(attri2);
                
        Joueur nj=Joueur.getInstanceJ(cattri1, uattri2);
        Annonce a = new Annonce(nombreDé,faceduDé);
        ttlesann.add(a);
        nj.setVal(a);
        compteur++;
        //int test;
        //test=nj.getVal().getAnnValDé();
         return "Vous avez annoncé "+nombreDé+" dés "+faceduDé;
         
        }
    public ArrayList<Annonce> AfficherTouteAnnonces() throws RemoteException{
      
        return ttlesann;
    }
    //methode affichant tous le joueurs
         public HashMap<String, String> AfficherJoueur(){
          
          HashMap<String, String> h1 = new HashMap<>(); 
          //h1=jtest.getJoueurs(joueurs);
         
          for(Joueur J:joueurs){
                Couleur ctmp=J.getPions();
                User utmp=J.getDude();
                 h1.put(utmp.getPseudo(), ctmp.getCouleur());
            }
           Set cles = h1.keySet();
           Iterator it = cles.iterator();
           while (it.hasNext()){
           Object cle = it.next(); // tu peux typer plus finement ici
           Object valeur = h1.get(cle); // tu peux typer plus finement ici
           System.out.println(cle+ " "+valeur);
        
           }
          return h1; 
         }
        public void AfficherCouleurlist(){
        //methode affichant les couleurs
              for (Couleur C : cols) {
			System.out.println(C.getCouleur());
            }
        }
        //methode affichant les utilisateurs
        
        public void AfficherPseudo(){
              for (User U : users) {
		System.out.println(U.getPseudo());
            }
        }

    public ArrayList<Couleur> AfficherCouleur(){
        return cols;
    }
    
    public ArrayList<User> AfficherUser(){
        return users;
    }
        
        
    
    
    public static void main(String args[]){
        try{
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("server", new RMIServer());
            System.out.println("server started");
           
        }catch(Exception e){
            System.out.println(e);
        }

      
    }
}
