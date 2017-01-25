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
       public  ArrayList<String> pseudo = new ArrayList<>();
       public  ArrayList<String> flag = new ArrayList<>();
       public  static ArrayList<String> flag2 = new ArrayList<>();
       public  static ArrayList<String> pseudo2 = new ArrayList<>();
       ArrayList<String> valeurdesj = new ArrayList<>();
       ArrayList<String> valdesworld = new ArrayList<>();
       ArrayList<Annonce> ttlesann = new ArrayList<>();
       //public  ArrayList<Joueur> joueurs = new ArrayList<>();
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
        public ArrayList<String> getPseudo(RMI rmi) throws RemoteException {
              pseudo=rmi.getUser();
              return pseudo;
        }
        public ArrayList<String> getCool(RMI rmi) throws RemoteException {
              flag=rmi.getCouleurs();
              return flag;
        }
        public String saisiePseudo(RMI rmi, ArrayList<String> u) throws RemoteException{
            //Saisie du pseudo
            String text = rmi.getData("Lets the game begin ");
            System.out.println(text);
            Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez saisir votre pseudo :");
            String pseu = sc.nextLine();
           
            if(u.contains(pseu)){
            return null;
                }else return pseu;
        }
        
        public String saisieCouleur(RMI rmi, String pseu, ArrayList<String> c) throws RemoteException{
            //Saisier du choix de dés
                //Manque afficher couleur déja choisit
            Scanner sc1 = new Scanner(System.in);
            System.out.println("Choisissez une couleur de dés : ");
            String col = sc1.nextLine();
            
            //Creation des User et des objets couleur
            if(c.contains(col)){
                return null;
            }else{
            String lancement =rmi.setPseudo(pseu)+rmi.setCouleur(col);
            System.out.println(lancement);
                return col;
            }
        }
           
        public void CreationJoueur(RMI rmi, String pseu, String col) throws RemoteException{
            //Creation du joueur 
             System.out.println("c'est parti !");
            
            rmi.CreerJoueur(pseu, col);
        }
        
        //methode affichage des joueurss
        public void AfficherJoueur(RMI rmi) throws RemoteException{
            
            System.out.println("Voici les joueurs présent à cet instant");
            System.out.println("On va attendre que tout le monde soit présent avant de commencer");
              HashMap<String, String> h = new HashMap<>(); 
              
              h=rmi.AfficherJoueur();
          
              Set cles = h.keySet();
              Iterator it = cles.iterator();
              while (it.hasNext()){
              Object cle = it.next(); 
              Object valeur = h.get(cle); 
              System.out.println("Pseudo : " +cle+ " avec les dés "+valeur);
                }
        }
        
        public Integer NbJoueurPresent(RMI rmi) throws RemoteException{
            int element=rmi.CompteJoueur();
            return element;
        }
        
        //Affichage des des en fonction des joueurs:
        public void AfficherDesJoueur(RMI rmi ,String pseu, String col) throws RemoteException{
            valeurdesj=rmi.AfficherDesJoueur(pseu,col);
            int count=valeurdesj.size();
            System.out.println("Voici vos " +count+ " dés");
            for (String val : valeurdesj) {
			System.out.print(val+",");
                }
        }
        public Integer AttribuerOrdre(String pseu, String col, RMI rmi) throws RemoteException{
            Integer numero;
            numero=rmi.SetOrdre(pseu, col);
            return numero;
        }
        
        public Boolean AQuiDeJouer(Integer num, RMI rmi) throws RemoteException{
            Boolean ordre;
            ordre=rmi.RetrouverOrdreJoueur(num);
            System.out.println(ordre);
            return ordre;
        }
        
        public void AfficherDesPartie(RMI rmi) throws RemoteException{
            valdesworld=rmi.AfficherToutDes();
            System.out.println("\n Dés de tout le monde :" );
            for(String vald: valdesworld){
            System.out.print(vald+",");
            }
        }
        public void ResultatCompterDes(Integer numj, RMI rmi) throws RemoteException{
            Boolean resultat=rmi.OnCompte(numj);
            if(resultat){
                System.out.println("\n Il y a bien le nombre de dés annoncé à cette valeur");
            }else{
                System.out.println("\n Menteur !! ");
            }
        }

        
        public void EnvoiEnchere(RMI rmi,Integer nbDé ,Integer valDé ,String pseu,String col) throws RemoteException{
            rmi.RecuperationAnn(nbDé,valDé,pseu,col);
        }
        
        public void AfficherAnnonce(RMI rmi) throws RemoteException{
            ttlesann=rmi.AfficherTouteAnnonces();
            for(Annonce A:ttlesann){
                System.out.println("Nombre de dés :"+A.getDé()+ " ayant une valeur de "+A.getAnnValDé());
            }
        }
        
        public void FaireChoix(RMI rmi) throws RemoteException{
            
           // Décision surenchere, menteur, tout pile
          
           boolean isNumber = true;
           Scanner scanch = new Scanner(System.in);
             int choix=0;
           System.out.println("\n Vous devez surenchérir, accusez de menteur votre prédecesseur ou tenter le tout-pile !"); 
           
             do{
                System.out.println("Tapez 1 pour surenchérir, 2 pour le menteur, trois pour le tout-pile !"); 
                
                    try{
                    choix = (int) Integer.parseInt(scanch.next());
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
                    nbDé = (int) Integer.parseInt(scanch.next());
                    isNumber = false; // execute que si parseInt ne lance pas d'exception
                        } catch(NumberFormatException e){
                            System.out.println("Vous devez entrer des chiffres.");
                        }
                    } while(nbDé ==0);
                             
                    while(faceDé !=2 && faceDé !=3 && faceDé !=4 && faceDé !=5 && faceDé !=6){
                        System.out.println("face des dés :  (compris entre 2 & 6)");
                        try{
                    faceDé = (int) Integer.parseInt(scanch.next());
                    isNumber = false; // execute que si parseInt ne lance pas d'exception
                        } catch(NumberFormatException e){
                            System.out.println("Vous devez entrer des chiffres.");
                        } 
                    
                   }
          /*
            System.out.println(rmi.getEnchere(nbDé, faceDé));
            */
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
           
        }
        
          
                
           

        
    
           
        public static void main(String args[]) throws RemoteException, InterruptedException{
        RMIClient client = new RMIClient();
        int j=0;
        String pseu;
        String col;
        Boolean ordre;
        Integer num;
        RMI rmi2=client.connectServer();
        if(rmi2==null){
            System.out.println("Erreur de connexion");
        }
        pseudo2=client.getPseudo(rmi2);
        flag2=client.getCool(rmi2);
        pseu=client.saisiePseudo(rmi2,pseudo2);
            while(pseu==null){
             System.out.println("Pseudo deja utilisé, choisissez en un autre");
             pseu=client.saisiePseudo(rmi2,pseudo2);
        }
       
        col=client.saisieCouleur(rmi2, pseu, flag2);
        while(col==null){
             System.out.println("Couleur déja prise !");
             col=client.saisieCouleur(rmi2,pseu, flag2);
        }
        client.CreationJoueur(rmi2, pseu, col);
        client.AfficherJoueur(rmi2);
        //recupérer le nombre de joueur inscrit
        //tant que pas egal a 6 ne pas faire la suite
       int go;
       go=client.NbJoueurPresent(rmi2);
       while(go!=2){
           System.out.println("On attend le nombre suffisant de joueur");
           Thread.sleep(4000);
           go=client.NbJoueurPresent(rmi2);
       }
       client.AfficherJoueur(rmi2);
      
       client.AfficherDesJoueur(rmi2, pseu, col);
       
       num=client.AttribuerOrdre(pseu, col, rmi2);
       ordre=client.AQuiDeJouer(num, rmi2);
       while(!ordre){
           System.out.println("Attendez votre tour");
           Thread.sleep(8000);
       }
       System.out.println("A votre tour");
       client.FaireChoix(rmi2);
       
       client.ResultatCompterDes(num, rmi2);
       client.AfficherDesPartie(rmi2);
       //client.ResultatCompterDes(3, 2, rmi2);
       
       
        
        
        
    }
}
