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
       ArrayList<Dés> dd = new ArrayList<>();
               int j=0;
        int fd;
        int nbd;
        String pseu;
        String col;
        Boolean ordre;
        Integer numpass;
 int count=0;
        
       
       boolean isNumber = true;
       Scanner scanch = new Scanner(System.in);
       int nbDé =0;
       int faceDé =0;

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
           
                     
            if(u.contains(pseu) || pseu.equals("")){
            return null;
            }else return pseu;
            
            
            
        }
        
        public String saisieCouleur(RMI rmi, String pseu, ArrayList<String> c) throws RemoteException{
            //Saisier du choix de dés
                //Manque afficher couleur déja choisit
            Scanner sc1 = new Scanner(System.in);

            System.out.println("Choisissez une couleur de dés ( rouge, bleu,jaune,vert ou orange) : ");
            String col = sc1.nextLine();
            
            //Creation des User et des objets couleur
            if(c.contains(col) || col.equals("") || !col.equals("rouge") && !col.equals("jaune") && !col.equals("vert") && !col.equals("bleu") && !col.equals("orange")){
                System.out.println(col);
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
            
            System.out.println("\n Voici les joueurs présent à cet instant");
            System.out.println("\n On va attendre que tout le monde soit présent avant de commencer");
              HashMap<String, String> h = new HashMap<>(); 
              
              h=rmi.AfficherJoueur();
          
              Set cles = h.keySet();
              Iterator it = cles.iterator();
              while (it.hasNext()){
              Object cle = it.next(); 
              Object valeur = h.get(cle); 
              System.out.println("Pseudo : " +cle+ " avec les dés "+valeur);
                }
              System.out.println("\n On va attendre que tout le monde soit présent avant de commencer");
        }
        
        public Integer NbJoueurPresent(RMI rmi) throws RemoteException{
            int element=rmi.CompteJoueur();
            return element;
        }
        
        //Affichage des des en fonction des joueurs:
        public Integer AfficherDesJoueur(RMI rmi ,String pseu, String col, ArrayList <Dés> dd) throws RemoteException{
            valeurdesj.clear();
            valeurdesj=rmi.AfficherDesJoueur(pseu,col, dd,numpass);
            int count=valeurdesj.size();
            System.out.println("\n Voici vos " +count+ " dés");
            for (String val : valeurdesj) {
			System.out.print(val+",");
                }
            return count;
        }
        public ArrayList<Dés> RemplirDesJoueur(RMI rmi ,String pseu, String col) throws RemoteException{
            dd=rmi.RemplirDesJoueur(pseu, col,numpass);
            return dd;
        }
        public Integer AttribuerOrdre(String pseu, String col, RMI rmi, ArrayList <Dés> dd) throws RemoteException{
            Integer numero;
            numero=rmi.SetOrdre(pseu, col, dd);
            return numero;
        }
        
        public Boolean AQuiDeJouer(Integer num, RMI rmi) throws RemoteException{
            Boolean ordre;
            ordre=rmi.RetrouverOrdreJoueur(num);
            //ystem.out.println(ordre);
            return ordre;
        }
        
        public Integer AfficherDesPartie(RMI rmi) throws RemoteException{
            int size;
            valdesworld=rmi.AfficherToutDes();
            System.out.println("\n Dés de tout le monde :" );
            size=valdesworld.size();
            for(String vald: valdesworld){
            System.out.print(vald+",");
            }
            return size;
        }
        public void ResultatCompterDes(Integer numj, RMI rmi) throws RemoteException{
            Boolean resultat=rmi.OnCompte(numj);
            if(resultat){
                System.out.println("\n Il y a bien le nombre de dés annoncé à cette valeur");
            }else{
                System.out.println("\n Menteur !! ");
            }
        }
        
        public void ResultatCompterDesTtPile(Integer numj, RMI rmi) throws RemoteException{
            Boolean resultat=rmi.OnCompteTtPile(numj);
            if(resultat){
                System.out.println("\n Il y a bien le tout-pile !");
            }else{
                System.out.println("\n Désolé, vous vous êtes trompé ! Vous perdez un dé ! ");
            }
        }

        
        public void EnvoiEnchere(RMI rmi,Integer nbDé ,Integer valDé ,String pseu,String col, ArrayList <Dés> dd) throws RemoteException{
            rmi.RecuperationAnn(nbDé,valDé,pseu,col, dd,numpass);
        }
        
        public void AfficherAnnonce(RMI rmi) throws RemoteException{
            ArrayList<Annonce> annonces = new ArrayList<>();
            annonces=rmi.AfficherTouteAnnonces();
            for(Annonce A:annonces){
                System.out.println("Nombre de dés :"+A.getDé()+ " ayant une valeur de "+A.getAnnValDé());
            }
        }
        
        public void Remisecompteur(RMI rmi) throws RemoteException{
            rmi.ResetCompteur();
        }
  
        // Décision surenchere, menteur, tout pile
        public Integer FaireChoix(RMI rmi) throws RemoteException{
          int choix1=0;
           System.out.println("\n Vous devez surenchérir, accusez de menteur votre prédecesseur ou tenter le tout-pile !"); 
           
             do{
                System.out.println("Tapez 1 pour surenchérir, 2 pour le menteur, trois pour le tout-pile !"); 
                
                    try{
                        
                    choix1 = (int) Integer.parseInt(scanch.next());
                    isNumber = false; // execute que si parseInt ne lance pas d'exception
                    System.out.println("Vous avez entré " + choix1);
                        } catch(NumberFormatException e){
                            System.out.println("Vous devez entrer les chiffres 1, 2 ou 3.");
                        } 
                } while(choix1 !=1 && choix1!=2 && choix1!=3);
             /*
             if(choix==1){
                SurenchereDé();
                SurenchereFaceDé();
             }*/
             /*
              if(choix1==2){
                  System.out.println("je suis dans le choix 2");
                 Menteur();
                 SwitchCase(rmi, choix1);
             }*//*
               if(choix==3){
                 ToutPile();
             }
             */
             return choix1;
        }
        
        // Methode surenchere rajout de dés
            public Integer SurenchereDé() throws RemoteException{
                          
 
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
                   
                   return nbDé;
            }  
            
            // Methode surenchere face de dés
             public Integer SurenchereFaceDé() throws RemoteException{                                   
                    while(faceDé !=2 && faceDé !=3 && faceDé !=4 && faceDé !=5 && faceDé !=6){
                        System.out.println("face des dés :  (compris entre 2 & 6)");
                        try{
                    faceDé = (int) Integer.parseInt(scanch.next());
                    isNumber = false; // execute que si parseInt ne lance pas d'exception
                        } catch(NumberFormatException e){
                            System.out.println("Vous devez entrer des chiffres.");
                        } 
                   }
                    return faceDé;
             }
             
             public void Menteur() throws RemoteException{
                
                 
                System.out.println("Vous avez choisi menteur");
                }


                public void ToutPile() throws RemoteException{
                 
                System.out.println("Vous avez tenté le tout pile !");
                }  
        public void ReRemplirJoueur(String pseu, String col, RMI rmi, ArrayList <Dés> dd1) throws RemoteException{
            dd=rmi.ReRemplirJoueur(pseu,col,dd1,numpass);
        }        
            
        public void LancementPartie(RMI rmi2) throws RemoteException, InterruptedException{      
   
            pseudo2=getPseudo(rmi2);
            flag2=getCool(rmi2);
            pseu=saisiePseudo(rmi2,pseudo2);
           
            while(pseu==null){
                 System.out.println("Pseudo non pris en compte, choisissez en un autre");
                 pseu=saisiePseudo(rmi2,pseudo2);
            }
             

            col=saisieCouleur(rmi2, pseu, flag2);
            while(col==null){
                 System.out.println("Couleur déja prise ou ne rentrant pas dans les couleurs à choisir !");
                 System.out.println("Rappel : rouge, bleu, jaune, vert, orange");
                 col=saisieCouleur(rmi2,pseu, flag2);
            }
             
            CreationJoueur(rmi2, pseu, col);
            dd=RemplirDesJoueur(rmi2, pseu, col);
            AfficherDesJoueur(rmi2, pseu, col, dd);
            AfficherJoueur(rmi2);

            //recupérer le nombre de joueur inscrit
           int go;
           go=NbJoueurPresent(rmi2);
           while(go!=2){
               System.out.println("\n On attend le nombre suffisant de joueur");
               Thread.sleep(4000);
               go=NbJoueurPresent(rmi2);
           }

           //AfficherJoueur(rmi2);
           numpass=AttribuerOrdre(pseu, col, rmi2, dd);
           //AfficherDesJoueur(rmi2, pseu, col);
          
    }
    
        public void Tour(RMI rmi2) throws RemoteException, InterruptedException{
            int choix=0;
            
            if(count>1){
                System.out.println("Jai rerempli");
                ReRemplirJoueur(pseu, col, rmi2, dd);
            }
            AfficherDesJoueur(rmi2, pseu, col, dd);
            while(AfficherDesJoueur(rmi2, pseu, col, dd)==0){
                System.out.println("you loose");
            }
            ordre=AQuiDeJouer(numpass, rmi2);
                while(!ordre){
                    System.out.println("\n Attendez votre tour");
                    Thread.sleep(4000);
                    ordre=AQuiDeJouer(numpass, rmi2);
                }
                System.out.println("\n A votre tour");
                AfficherAnnonce(rmi2);
                System.out.println(choix);
                choix=FaireChoix(rmi2);
                System.out.println(choix);
                System.out.println(choix);
                switch(choix)
                {
                     case 1:
                         nbd=SurenchereDé();
                         fd=SurenchereFaceDé();
                         EnvoiEnchere(rmi2, nbd, fd, pseu, col,dd);
                         Tour(rmi2);
                         //Thread.sleep(30000);
                         break;
                     case 2:
                         Menteur();
                         AfficherDesPartie(rmi2);
                         ResultatCompterDes(numpass, rmi2);
                         Remisecompteur(rmi2);
                         count++;
                         Tour(rmi2);
                         
                         break;

                     case 3:
                         ToutPile();
                         AfficherDesPartie(rmi2);
                         ResultatCompterDesTtPile(numpass, rmi2);
                         Remisecompteur(rmi2);
                         count++;
                         Tour(rmi2);
                         
                         break;
                }
                
                //ReRemplirJoueur(pseu,col,rmi2,dd);

            
    }/*
    public void SwitchCase(RMI rmi2, Integer choix2) throws RemoteException, InterruptedException{
                        switch(choix2)
                {
                     case 1:
                         System.out.println("je suis dans le case 1");
                         nbd=SurenchereDé();
                         fd=SurenchereFaceDé();
                         EnvoiEnchere(rmi2, nbd, fd, pseu, col,dd);
                         System.out.println("je suis apres le envoie enchere");
                         //Thread.sleep(30000);
                         break;
                     case 2:
                         System.out.println("je suis dans le case 2 ");
                         Menteur();
                         AfficherDesPartie(rmi2);
                         System.out.println("je suis apres afficher des partie ");
                         ResultatCompterDes(numpass, rmi2);
                         Remisecompteur(rmi2);
                         break;

                     case 3:
                         System.out.println("je suis dans le case 3 ");
                         ToutPile();
                         AfficherDesPartie(rmi2);
                         Remisecompteur(rmi2);
                         break;
                }
        Tour(rmi2);
    }
        */
    public static void main(String args[]) throws RemoteException, InterruptedException{
        RMIClient client = new RMIClient();
        RMI rmi2=client.connectServer();
        if(rmi2==null){
            System.out.println("Erreur de connexion");
        }
        int taille;
        client.LancementPartie(rmi2);
        //System.out.println(client.AfficherDesPartie(rmi2));
      taille=3;
        while(taille!=0){
        client.Tour(rmi2);
        //client.Remisecompteur(rmi2);
        //client.reremplirJoueur;
        }
        /*
        int j=0;
        int fd;
        int nbd;
        String pseu;
        String col;
        Boolean ordre;
        Integer num;
        int choix;
        int size;
       
        
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
        client.RemplirDesJoueur(rmi2, pseu, col);
        client.AfficherDesJoueur(rmi2, pseu, col);
       
        //recupérer le nombre de joueur inscrit
        //tant que pas egal a 6 ne pas faire la suite
       int go;
       go=client.NbJoueurPresent(rmi2);
       while(go!=2){
           System.out.println("\n On attend le nombre suffisant de joueur");
           Thread.sleep(4000);
           go=client.NbJoueurPresent(rmi2);
       }
       
       client.AfficherJoueur(rmi2);
      //boucle tour commence
       System.out.println("test d'affichage de d");
       client.AfficherDesJoueur(rmi2, pseu, col);
       //client.AfficherDesPartie(rmi2);
       num=client.AttribuerOrdre(pseu, col, rmi2);
       System.out.println("\n"+num);
       //while(client.AfficherDesPartie(rmi2)!=0){
       ordre=client.AQuiDeJouer(num, rmi2);
       while(!ordre){
           System.out.println("\n Attendez votre tour");
           Thread.sleep(4000);
           ordre=client.AQuiDeJouer(num, rmi2);
       }
       System.out.println("\n A votre tour");
       client.AfficherAnnonce(rmi2);
       System.out.println("je suis apres afficher annonce");
       choix=client.FaireChoix(rmi2);
       System.out.println(choix);
       switch(choix)
       {
            case 1:
                System.out.println("je suis dans le case 1");
                nbd=client.SurenchereDé();
                fd=client.SurenchereFaceDé();
                client.EnvoiEnchere(rmi2, nbd, fd, pseu, col);
                System.out.println("\n je suis apres le envoie enchere");
                //Thread.sleep(30000);
                break;
            case 2:
                System.out.println("\n je suis dans le case 2 ");
                client.Menteur();
                client.AfficherDesPartie(rmi2);
                System.out.println("\n je suis apres afficher des partie ");
                client.ResultatCompterDes(num, rmi2);
                break;
            
            case 3:
                System.out.println("je suis dans le case 3 ");
                client.ToutPile();
                client.AfficherDesPartie(rmi2);
                client.ResultatCompterDesTtPile(num, rmi2);
                System.out.println("je suis apres afficher des partie ");
                break;
       }
       }
       
       //client.ResultatCompterDes(num, rmi2);
       
       //client.ResultatCompterDes(3, 2, rmi2);
       
       */
        
        
        
    }
}
