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
        
    public RMI connectServer() {
        try{
            Registry reg = LocateRegistry.getRegistry("127.0.0.1",1099);
            RMI rmi = (RMI) reg.lookup("server");
            System.out.println("Connected to Server");
            return rmi;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
        
        //ARRANGEMENT EN METHODE A FAIRE
            
        //Début du jeu, enregistrement des joueurs 
        public String saisiePseudo(RMI rmi) throws RemoteException{
            //Saisie du pseudo
            String text = rmi.getData("Lets the game begin ");
            System.out.println(text);
            Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez saisir votre pseudo :");
            String pseu = sc.nextLine();
            
            return pseu;
        }
        public String saisieCouleur(RMI rmi, String pseu) throws RemoteException{
            //Saisier du choix de dés
                //Manque afficher couleur déja choisit
            Scanner sc1 = new Scanner(System.in);
            System.out.println("Choisissez une couleur de dés : ");
            String col = sc1.nextLine();
            
            //Creation des User et des objets couleur
            String lancement =rmi.setPseudo(pseu)+rmi.setCouleur(col);
            System.out.println(lancement);
            
            return col;
        }
           
        public void CreationJoueur(RMI rmi, String pseu, String col) throws RemoteException{
            //Creation du joueur 
             System.out.println("c'est parti !");
            
            rmi.CreerJoueur(pseu, col);
          System.out.println("Je suis apres creerjoueur");
       //return jc;
        }
              //methode affichage des joueurss
        public void AfficherJoueur(RMI rmi) throws RemoteException{
              HashMap<String, String> h = new HashMap<>(); 
              
            h=rmi.AfficherJoueur();
          
              Set cles = h.keySet();
              Iterator it = cles.iterator();
              while (it.hasNext()){
              Object cle = it.next(); 
              Object valeur = h.get(cle); 
              System.out.println(cle+ " "+valeur);
                }
        }
           
           //Affichage des des en fonction des joueurs:
           
           
           
        public void FaireChoix(RMI rmi) throws RemoteException{
           // Décision surenchere, menteur, tout pile
           Scanner sc = new Scanner(System.in);
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
           
        }
            
          
                
           

        
    
           
        public static void main(String args[]) throws RemoteException{
        RMIClient client = new RMIClient();
        int j=0;
        String pseu;
        String col;
        RMI rmi2=client.getInstance().connectServer();
        if(rmi2==null){
            System.out.println("Erreur de connexion");
        }
        pseu=client.saisiePseudo(rmi2);
        col=client.saisieCouleur(rmi2, pseu);
        client.CreationJoueur(rmi2, pseu, col);
        
        //recupérer le nombre de joueur inscrit
        //tant que pas egal a 6 ne pas faire la suite
                
       client.AfficherJoueur(rmi2);
        
        
        
        
        
    }
}
