package package1;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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
public class RMIClient {
 
       private static Vector classes = new Vector();
    
        public static RMIClient getInstance(){
            
		RMIClient tmp = new RMIClient();
		if (classes.contains(tmp)) {
			Enumeration enume = classes.elements();
			while (enume.hasMoreElements()) {
				RMIClient element = (RMIClient) enume.nextElement();
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
    public void connectServer(String nom) {
        try{
            Registry reg = LocateRegistry.getRegistry("127.0.0.1",1099);
            RMI rmi = (RMI) reg.lookup("server");
            System.out.println("Connected to Server");
            
            //appel de methode sur le server
            
            String text = rmi.getData("Lets the game begin "+nom);
            System.out.println(text);
            Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez saisir votre pseudo :");
            String pseu = sc.nextLine();
          
            Scanner sc1 = new Scanner(System.in);
            System.out.println("Choisissez une couleur de dés : ");
            String col = sc.nextLine();
            
            String lancement =rmi.setPseudo(pseu)+rmi.setCouleur(col);
            System.out.println(lancement);
            
            System.out.println("c'est parti !");
            
            
            HashMap<String, String> h = new HashMap<>(); 
            h=rmi.CreerJoueur(pseu, col);
            /*
            ArrayList<Couleur> names = new ArrayList<Couleur>();
            names=rmi.AfficherCouleur();
            for (Couleur C : names) {
			System.out.println(C);
		}*/
           Set cles = h.keySet();
           Iterator it = cles.iterator();
           while (it.hasNext()){
           Object cle = it.next(); 
           Object valeur = h.get(cle); 
           System.out.println(cle+ " "+valeur);
            }
           
           //HashMap<String, HashSet<String>> h2 = new HashMap<>(); 
           /*h2=rmi.CreerPartie();
          
           System.out.println("hmap de la partie ");
           
           Set cles2=h2.keySet();
           Iterator it2 = cles2.iterator();
           while(it2.hasNext()){
            Object cle2 =it2.next();
            Object valeur2 = h2.get(cle2);
            System.out.println(cle2+" "+valeur2);
        }*/
           
           
           
            
                
           // Décision surenchere, menteur, tout pile
           boolean isNumber = true;
             int choix=0;
            
           System.out.println("Vous devez surenchérir, accusez de menteur votre prédecesseur ou tenter le tout-pile !"); 
           
             do{
                System.out.println("Tapez 1 pour surenchérir, 2 pour le menteur, trois pour le tout-pile !"); 
                
                    try{
                    choix = (int) Integer.parseInt(sc.next());
                    isNumber = false; // execute que si parseInt ne lance pas d'exception
                    System.out.println("Vous avez entré " + choix);
                        } catch(NumberFormatException e){
                            System.out.println("Vous devez entrer les chiffres 1, 2 ou 3.");
                        } 
                } while(choix !=1 && choix!=2 && choix!=3);
                    if(choix==1){
                     // Surenchere 
                    int nbDé=0;
                    int faceDé=0;
                    
                   
                    do {
                         System.out.println("Veuillez entrer votre annonce : ");
                        System.out.println("Nombre de dés sur la table :  ");
                        
                    
                     try{
                    nbDé = (int) Integer.parseInt(sc.next());
                    isNumber = false; // execute que si parseInt ne lance pas d'exception
                        } catch(NumberFormatException e){
                            System.out.println("Vous devez entrer des chiffres.");
                        }
                    } while(nbDé ==0);
                             
                    while(faceDé !=2 && faceDé !=3 && faceDé !=4 && faceDé !=5 && faceDé !=6){
                        System.out.println("face des dés :  (compris entre 2 & 6)");
                        System.out.println(faceDé);
                        try{
                    faceDé = (int) Integer.parseInt(sc.next());
                    isNumber = false; // execute que si parseInt ne lance pas d'exception
                        } catch(NumberFormatException e){
                            System.out.println("Vous devez entrer des chiffres.");
                        } 
                    
                   }
          
            System.out.println(rmi.getEnchere(nbDé, faceDé));
            
                }
                
                if(choix==2){
                 // Menteur
                System.out.println("Vous avez choisi menteur");
                }
                //System.out.println("avant le else = " + choix);
                else if(choix==3){
                 // Tout pile
                 System.out.println("Dans le else j'ai une valeur de " + choix);
                System.out.println("Vous avez tenté le tout pile !");
                }
           
            
            
          
                
           

            
        }catch(Exception e){
            System.out.println(e);
        }
    }
           public void CreationPartie(){
           
        }
        public static void main(String args[]){
        RMIClient client = new RMIClient();
        int j=0;
        //for(int i = 1; i <= 3; i++)
        //{
        client.getInstance().connectServer("margx");
            
                
        //}
        
        
        
    }
}
