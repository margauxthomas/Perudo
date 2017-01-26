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
    ArrayList<Joueur> joueurspa= new ArrayList<>();
    ArrayList<Joueur> joueursann= new ArrayList<>();
    ArrayList<String> valeurdesj = new ArrayList<>();
    ArrayList<String> valdesworld = new ArrayList<>();
    ArrayList<Annonce> ttlesann = new ArrayList<>();
    ArrayList<Dés> ddre = new ArrayList<>();
    ArrayList<Dés> tmp = new ArrayList<>();
    Partie  p= new Partie("prems","test");
    
    Integer passage=1;
    Integer numpass=1;
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
         Joueur j = new Joueur(c,u, tmp, numpass);
         
         
        //j.setDd(j.RemplirDes());
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
        
    public ArrayList<String> AfficherDesJoueur(String attri1, String attri2, ArrayList <Dés> dd1, Integer p)throws RemoteException{
         Couleur cattri1=Couleur.getInstanceC(attri2);
        User uattri2=User.getInstanceU(attri1);
        Joueur nj=Joueur.getInstanceJ(cattri1, uattri2, dd1, p);
        //valeurdesj.clear();
        //valeurdesj=nj.AfficherDés();
        //valeurdesj=nj.DonnerValeur();
        
        return nj.DonnerValeur();
    }
    public ArrayList<Dés> RemplirDesJoueur(String attri1, String attri2, Integer p)throws RemoteException{
        Couleur cattri1=Couleur.getInstanceC(attri2);
        User uattri2=User.getInstanceU(attri1);
        tmp.clear();
        
        Joueur nj=Joueur.getInstanceJ(cattri1, uattri2, tmp, p);
        System.out.println("je suis la avant le setde");
        System.out.println(nj.getDude().getPseudo());
        nj.setDd(nj.RemplirDes());
        
        //nj.RemplirDes();
        //valeurdesj=nj.DonnerValeur();
                for (String val : nj.DonnerValeur()) {
                        valdesworld.add(val);
			System.out.println("Dés du joueur lors du remplissage"+val);
	}
        System.out.println("je suis avant le return");
        return nj.getDd();
    }
    public ArrayList <Dés> ReRemplirJoueur(String attri1, String attri2,ArrayList <Dés> dd1, Integer p) throws RemoteException{
        System.out.println("je suis dans reremplir");
        Couleur cattri1=Couleur.getInstanceC(attri2);
        User uattri2=User.getInstanceU(attri1);
        Joueur nj=Joueur.getInstanceJ(cattri1, uattri2, dd1, p);
        nj.ReRemplirDés();
               //valeurdesj=nj.DonnerValeur();
                for (String val : nj.DonnerValeur()) {
                        valdesworld.add(val);
			System.out.println(val);
	}
        
        return nj.getDd();
    }
    
    public Integer SetOrdre(String attri1, String attri2,ArrayList <Dés> dd1)throws RemoteException{
        Couleur cattri1=Couleur.getInstanceC(attri2);
        User uattri2=User.getInstanceU(attri1);        
        Joueur nj=Joueur.getInstanceJ(cattri1, uattri2, dd1, numpass);
        System.out.println("Attributiion numéro de passage "+passage);
        nj.setPassage(passage);
        passage++;
        Integer numero;
        numero=nj.getPassage();
        //joueurspa.add(nj);
        System.out.println("num du joueur"+numero);
        for(Joueur J:joueurs){
            System.out.println("dans setordre");
            System.out.println(J.getPassage());
        }
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
        public boolean OnCompteTtPile(Integer numj) throws RemoteException{
        RecupererLastJoueurTtPile(numj);
        return resultat;
        
    }
    
    public void AfficherNumPassage(){
        for(Joueur J:joueurs){
            System.out.println(J.getPassage());
        }
    }
    public void RecupererLastJoueur(Integer numj) throws RemoteException{
        String pseulj=null;
        String collj=null;
        ArrayList<Dés> delj=null;
        String pseujec=null;
        String coljec=null;
         ArrayList<Dés> dejec=null;
        System.out.println("je suis dans recuperer lasjoueur");
        AfficherNumPassage();
            for(Joueur J :joueurs){
                System.out.println("je suis dans la boucle for");
                int p=0;
                p=J.getPassage();
                if(p==(numj)){
                pseujec=J.getDude().getPseudo();
                coljec=J.getPions().getCouleur();
                dejec=J.getDd();
                }
                //System.out.println(J.getDude().getPseudo());
                if(p==(numj-1)){
                   System.out.println("recuperation du joueur precedent");
                    pseulj=J.getDude().getPseudo();
                    System.out.println("le pseudo recupérer"+pseulj);
                    collj=J.getPions().getCouleur();
                    System.out.println("la couleur recuperer"+collj);
                    delj=J.getDd();
                    RecupererAnnonce(pseulj,collj,delj,(numj-1));
                    TraitementResultat(pseujec, coljec,pseulj, collj,dejec, delj,numj, (numj-1));
                }

                
                }

    }
    
     public void RecupererLastJoueurTtPile(Integer numj) throws RemoteException{
        String pseulj=null;
        String collj=null;
         ArrayList<Dés> delj=null;
        String pseujec=null;
        String coljec=null;
        ArrayList<Dés> dejec=null;
        System.out.println("je suis dans recuperer lasjoueur");
        AfficherNumPassage();
            for(Joueur J :joueurs){
                System.out.println("je suis dans la boucle for");
                int p=0;
                p=J.getPassage();
                if(p==(numj)){
                pseujec=J.getDude().getPseudo();
                coljec=J.getPions().getCouleur();
                dejec=J.getDd();
                TraitementResultatTtPile(pseujec, coljec,dejec,numj);
                }
                //System.out.println(J.getDude().getPseudo());
                if(p==(numj-1)){
                   System.out.println("recuperation du joueur precedent");
                    pseulj=J.getDude().getPseudo();
                    System.out.println("le pseudo recupérer"+pseulj);
                    collj=J.getPions().getCouleur();
                    delj=J.getDd();
                    System.out.println("la couleur recuperer"+collj);
                    RecupererAnnonce(pseulj,collj,delj,(numj-1));
                    
                }

                
                }

    }
    public void TraitementResultat(String pseujec, String coljec, String pseulj, String collj, ArrayList <Dés> dejec, ArrayList <Dés> delj, Integer p1,Integer p2) throws RemoteException{
                    if(resultat){
                System.out.println("je suis dans le cas ou le resultat est bon");
                EnleverDes(pseujec, coljec, dejec,p1);
            }else {
                        System.out.println("je suis dans le cas ou le resultat est pas bon");
                        EnleverDes(pseulj,collj,delj,p2);
                    }
                    
                    
    }
    
     public void TraitementResultatTtPile(String pseujec, String coljec, ArrayList <Dés> dejec, Integer p) throws RemoteException{
                    if(resultat){
                System.out.println("je suis dans le cas ou le resultat est bon");
                AjouterDes(pseujec, coljec, dejec, p);
            }else {
                        System.out.println("je suis dans le cas ou le resultat est pas bon");
                        EnleverDes(pseujec,coljec, dejec, p);
                    }
                    
                    
    }
     
    public void RecupererAnnonce(String attri1, String attri2, ArrayList <Dés> dd1, Integer p){
        int nb=0;
        int val=0;
        
        System.out.println("attribut transmit : "+attri1);
        System.out.println("attribut transmit : "+attri2);
        Couleur cattri1=Couleur.getInstanceC(attri1);
        User uattri2=User.getInstanceU(attri2);
                
        Joueur nj=Joueur.getInstanceJ(cattri1, uattri2, dd1, p);
        System.out.println(nj.getDude().getPseudo());
        //System.out.println(nb);
        
        for(Joueur J:joueursann){
            
                nb=J.getVal().getDé();
                val=J.getVal().getAnnValDé();
            
            
        }
        //nb=nj.getVal().getDé();
        System.out.println(nb);
        System.out.println(val);
        //Annonce a;
        //a=nj.getVal();
        //val=a.getAnnValDé();
        //val=nj.getVal().getAnnValDé();
        //System.out.println(val);
        resultat=Comparaison(nb,val);
        System.out.println(resultat);
  
    }
    
    public void RecupererAnnonceTtPile(String attri1, String attri2, ArrayList <Dés> dd1, Integer p){
        int nb=0;
        int val=0;
        
        System.out.println("attribut transmit : "+attri1);
        System.out.println("attribut transmit : "+attri2);
        Couleur cattri1=Couleur.getInstanceC(attri1);
        User uattri2=User.getInstanceU(attri2);
                
        Joueur nj=Joueur.getInstanceJ(cattri1, uattri2, dd1, p);
        System.out.println(nj.getDude().getPseudo());
        //System.out.println(nb);
        
        for(Joueur J:joueursann){
            
                nb=J.getVal().getDé();
                val=J.getVal().getAnnValDé();
            
            
        }
        //nb=nj.getVal().getDé();
        System.out.println(nb);
        System.out.println(val);
        //Annonce a;
        //a=nj.getVal();
        //val=a.getAnnValDé();
        //val=nj.getVal().getAnnValDé();
        //System.out.println(val);
        resultat=ComparaisonTtPile(nb,val);
        System.out.println(resultat);
  
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
        System.out.println(resultat);
    return resultat;
    }
    
     public Boolean ComparaisonTtPile(Integer nb, Integer val){
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
        if(c!=nb){
            resultat=false;
        }else resultat=true;
        System.out.println(resultat);
    return resultat;
    }
    
    public void EnleverDes(String attri1, String attri2, ArrayList <Dés> dd1, Integer p)throws RemoteException{
        
        Couleur cattri1=Couleur.getInstanceC(attri2);
        User uattri2=User.getInstanceU(attri1);        
        Joueur nj=Joueur.getInstanceJ(cattri1, uattri2, dd1, p);
        nj.EnleverDes();
        String test=nj.getDude().getPseudo();
        //System.out.println("numero de passage du perdant"+nj.getPassage());
        int rang=0;
        System.out.println("je suis avant le for");
        for(Joueur J:joueurs){
            String test2=J.getDude().getPseudo();
            System.out.println("je suis dans le for");
             System.out.println("yen a marre"+test);
             System.out.println("putain de java de merde"+test2);
            //if(test2.equals(test)){
            if(J.getDude().getPseudo().equals(test)){
                nj=J;
                rang=J.getPassage();
                System.out.println(rang);
                J.setPassage(1);
            }
        }
        for(int i=0;i<rang-1;i++){
            int tmp;
            System.out.println("je suis avant le get(i)");
            tmp= joueurs.get(i).getPassage();
            System.out.println("get(i) suivant"+tmp);
            joueurs.get(i).setPassage(tmp+1);
            System.out.println("je vais passer dans le 1");
            
        }
        //nj.setPassage(1);
        //joueurspa.add(nj);
    }
    public void ResetCompteur() throws RemoteException{
        
        compteur=1;
    }
    
    public void AjouterDes(String attri1, String attri2, ArrayList <Dés> dd1, Integer p)throws RemoteException{
        Couleur cattri1=Couleur.getInstanceC(attri1);
        User uattri2=User.getInstanceU(attri2);        
        Joueur nj=Joueur.getInstanceJ(cattri1, uattri2, dd1, p);
        nj.AjouterDes();
        String test=nj.getDude().getPseudo();
        int rang=0;
        for(Joueur J:joueurs){
            if(J.getDude().getPseudo().equals(test)){
                nj=J;
                rang=J.getPassage();
                J.setPassage(1);
            }
        }
            for(int i=0;i<rang-1;i++){
            int tmp;
            tmp= joueurs.get(i).getPassage();
            joueurs.get(i).setPassage(tmp+1);
        }
        
    }
    
    public String RecuperationAnn(int nombreDé, int faceduDé, String attri1, String attri2, ArrayList <Dés> dd1,Integer p) throws RemoteException {
        
        Couleur cattri1=Couleur.getInstanceC(attri2);
        User uattri2=User.getInstanceU(attri1);
                
        Joueur nj=Joueur.getInstanceJ(cattri1, uattri2, dd1,p);
        Annonce a = new Annonce(nombreDé,faceduDé);
        a=Annonce.getInstanceA(nombreDé, faceduDé);
        a.setAnnonce(nombreDé, faceduDé);
        ttlesann.add(a);
        nj.setVal(a);
        compteur++;
        int test;
        int test2;
        test=nj.getVal().getAnnValDé();
        test2=nj.getVal().getDé();
        System.out.println("recuperation annon zfrez"+test2);
        System.out.println("recuperation annon"+test);
        joueursann.clear();
        joueursann.add(nj);
        
         return "Vous avez annoncé "+nombreDé+" dés "+faceduDé;
         
        }
    public void Afficherannjou(){
        for(Joueur J:joueursann){
            System.out.println(J.getVal().getAnnValDé());
            System.out.println(J.getVal().getDé());
            
        }
    }
    
    public ArrayList<Annonce> AfficherTouteAnnonces() throws RemoteException{
            System.out.println("je suis avnat l'affichage des annonces");
            for(Annonce A:ttlesann){
            System.out.println("Nombre de dés :"+A.getDé()+ " ayant une valeur de "+A.getAnnValDé());
            }
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
