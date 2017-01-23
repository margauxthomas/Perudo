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
    
    public String getEnchere(int nbDé,int faceDé) throws RemoteException {
     String Dé = Integer.toString(nbDé);
      String faceduDé =Integer.toString(faceDé);
      return "Vous avez annoncé " + Dé+" dés " +faceduDé;
      
    }
    
    public String getAnnonce(int choix) throws RemoteException {
        String choice =Integer.toString(choix);
     return "Vous avez annoncé "+choice;
             
    }
    public String setPseudo(String pseu) throws RemoteException {
        
        pseu="Bienvenue dans le jeu du Perudo "+pseu;
   
        return pseu;
      
        
    }
    public String setCouleur(String col) throws RemoteException {
        col=". Vous jouez maintenant avec les "+col+" vous disposez de 5 dés au départ, ne les perdez pas, bonne chance !";
        
        return col;
    }
    
    ArrayList<User> users= new ArrayList<>();
     ArrayList<Couleur> cols= new ArrayList<>();
     ArrayList<Joueur> joueurs= new ArrayList<>();
      private ArrayList<String> valeurdesj;
     Partie  p= new Partie("prems","test");
     
    public HashMap<String, String> CreerJoueur(String pseu, String col){
        System.err.println("je suis ici");
        User u = new User(pseu);
        User us= u.getInstanceU(pseu);
        Couleur c = new Couleur(col);
        Couleur co = c.getInstanceC(col);
         System.err.println("je suis apres les instances");
  
        /*
        Joueur j1 = j.getInstanceJ();
        j1.addCouleur(c);
        j1.addUsers(u);
        */
        
         users.add(us);
       
         cols.add(co);
         System.err.println("je suis apre les add arraylist");
         Joueur j = new Joueur(co,us);
         Joueur nj=j.getInstanceJ(co,us);
          System.err.println("je suis avant remplir des ");
         nj.RemplirDes();
         
         joueurs.add(nj);
         
         System.err.println("je suis av le afficher des");
         valeurdesj=nj.AfficherDés();
         for (String val : valeurdesj) {
			System.out.println(val);
	}
         System.err.println("je suis apres le afficher des");
         
        HashMap<String, String> h1 = new HashMap<>(); 
        //h=j.getJoueurs(users, cols);
        
        h1=j.getJoueurs(joueurs);
                    
           Set cles = h1.keySet();
           Iterator it = cles.iterator();
           while (it.hasNext()){
           Object cle = it.next(); // tu peux typer plus finement ici
           Object valeur = h1.get(cle); // tu peux typer plus finement ici
           System.out.println(cle+ " "+valeur);
        
           }
              for (Couleur C : cols) {
			System.out.println(C.getCouleur());
            }
              for (User U : users) {
		System.out.println(U.getPseudo());
            }
            
            p.addjoueur(nj);
              
             
        return h1;
    }
    
       // HashMap<String, HashSet<String>> h2 = new HashMap<>(); 
       
      
       // Partie p1 = p.getInstanceP("prems","test");
        //System.out.println(p1.getNom());
        
       
        //h2=p1.RemplirPartie(joueurs);
        
          /*System.out.println("hmap de la partie ");
           
           Set cles2=h2.keySet();
           Iterator it2 = cles2.iterator();
           while(it2.hasNext()){
            Object cle2 =it2.next();
            Object valeur2 = h2.get(cle2);
            System.out.println(cle2+" "+valeur2);
           }
           
           for (Joueur J : joueurs) {
               User tmp=J.getDude();
		System.out.println(tmp.getPseudo());
            }
        return h2;
        */
    
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
