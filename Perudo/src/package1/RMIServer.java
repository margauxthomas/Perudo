package package1;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Scanner;
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
     
    public HashMap<String, String> CreerJoueur(String pseu, String col){
        User u = new User(pseu);
        User us= u.getInstanceU(pseu);
        Couleur c = new Couleur(col);
        Couleur co = c.getInstanceC(col);
        
  
        /*
        Joueur j1 = j.getInstanceJ();
        j1.addCouleur(c);
        j1.addUsers(u);
        */
        
         users.add(us);
       
         cols.add(co);
          
         Joueur j = new Joueur();
        HashMap<String, String> h = new HashMap<>(); 
        h=j.getJoueurs(users, cols);
              for (Couleur C : cols) {
			System.out.println(C.getCouleur());
            }
              for (User U : users) {
		System.out.println(U.getPseudo());
            }
        return h;
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
