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
            
        //ARRANGEMENT EN METHODE A FAIRE
            
        //Début du jeu, enregistrement des joueurs 
                    
            //Saisie du pseudo
            String text = rmi.getData("Lets the game begin "+nom);
            System.out.println(text);
            Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez saisir votre pseudo :");
            String pseu = sc.nextLine();
            
            //Saisier du choix de dés
                //Manque afficher couleur déja choisit
            Scanner sc1 = new Scanner(System.in);
            System.out.println("Choisissez une couleur de dés : ");
            String col = sc.nextLine();
            
            //Creation des User et des objets couleur
            String lancement =rmi.setPseudo(pseu)+rmi.setCouleur(col);
            System.out.println(lancement);
            
            System.out.println("c'est parti !");
            
            //Creation du joueur 
            HashMap<String, String> h = new HashMap<>(); 
            h=rmi.CreerJoueur(pseu, col);
          
              Set cles = h.keySet();
              Iterator it = cles.iterator();
              while (it.hasNext()){
              Object cle = it.next(); 
              Object valeur = h.get(cle); 
              System.out.println(cle+ " "+valeur);
                }
           
              //methode affichage des joueurss
    
           
           //Affichage des des en fonction des joueurs:
           
           
           
           
           // Décision surenchere, menteur, tout pile
             int choix=0;
           System.out.println("Vous devez surenchérir, accusez de menteur votre prédecesseur ou tenter le tout-pile !"); 
           
             while(choix <1 || choix>3){
                System.out.println("Tapez 1 pour surenchérir, 2 pour le menteur, trois pour le tout-pile !"); 
                choix = sc.nextInt();
             }
                if(choix==1){
                 // Surenchere 
                int nbDé=0;
                int faceDé=0;
                
                System.out.println("Veuillez entrez votre annonce : ");
                System.out.println("Nombre de dés sur la table :  ");
                 nbDé = sc.nextInt();
           
                    while(faceDé>6 || faceDé<2){
                        System.out.println("face des dés :  (compris entre 2 & 6)");
                        System.out.println(faceDé);
                    faceDé = sc.nextInt();
                   }
          
            System.out.println(rmi.getEnchere(nbDé, faceDé));
            
                }
                
                if(choix==2){
                 // Menteur
                System.out.println("Vous avez choisi menteur");
                }
                
                else{
                 // Tout pile
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
        //recupérer le nombre de joueur inscrit
        //tant que pas egal a 6 ne pas faire la suite
                
        //}
        
        
        
    }
}
