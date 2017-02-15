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

public class RMIClient {
    
    //déclaration variable clients temporaires
    private static Vector classes = new Vector();
    public ArrayList<String> pseudo = new ArrayList<>();
    public ArrayList<String> flag = new ArrayList<>();
    public static ArrayList<String> flag2 = new ArrayList<>();
    public static ArrayList<String> pseudo2 = new ArrayList<>();
       
    ArrayList<String> valdesworld = new ArrayList<>();
    ArrayList<Dés> dd = new ArrayList<>();
    
    int j=0;
    int fd;
    int nbd;
    String pseu;
    String col;
    Boolean ordre;
    Integer numpass=0;
    int count=0;
    int count1;
    int go2;
    int essai=0;
    boolean isNumber = true;
    Scanner scanch = new Scanner(System.in);
    int nbDé =0;
    int faceDé =0;
    int count2=0;
    int count3=0;
    int nump=0;
   
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
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Choisissez une couleur de dés ( rouge, bleu,jaune,vert ou orange) : ");
        String col = sc1.nextLine();     
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
        System.out.println("c'est parti !");
        rmi.CreerJoueur(pseu, col);
    }
        
        //methode affichage des joueurss
    public void AfficherJoueur(RMI rmi, Integer nump) throws RemoteException{

        System.out.println("\n Voici les joueurs présent à cet instant");
        System.out.println("\n On va attendre que tout le monde soit présent avant de commencer"
                + "Voici les joueurs de la partie");
        HashMap<String, String> h = new HashMap<>(); 
        h=rmi.AfficherJoueur(nump);
        Set cles = h.keySet();
        Iterator it = cles.iterator();
        while (it.hasNext()){
            Object cle = it.next(); 
            Object valeur = h.get(cle); 
            System.out.println("Pseudo : " +cle+ " avec les dés "+valeur);
        }
        System.out.println("\n On va attendre que tout le monde soit présent avant de commencer");
    }
        
    public Integer NbJoueurPresent(RMI rmi, Integer nump) throws RemoteException{
        int element=rmi.CompteJoueur(nump);
        return element;
    }
        
    //Affichage des des en fonction des joueurs:
    public Integer AfficherDesJoueur(RMI rmi ,String pseu, Integer nump) throws RemoteException{
        ArrayList<String> valeurdesj = new ArrayList<>();
        valeurdesj.clear();
        valeurdesj=rmi.AfficherDesJoueur(pseu, nump);
        int count=valeurdesj.size();
        System.out.println("\n Voici vos " +count+ " dés");
        for (String val : valeurdesj) {
            System.out.print(val+",");
        }
        return count;
    }

    public ArrayList<Dés> RemplirDesJoueur(RMI rmi ,String pseu, Integer nump) throws RemoteException{
        rmi.RemplirDesJoueur(pseu, nump);
        return dd;
    }
    
    public Integer AttribuerOrdre(String pseu,RMI rmi, Integer nump) throws RemoteException{
        Integer numero;
        numero=rmi.SetOrdre(pseu, nump);
        return numero;
    }
    
    public Integer ReattribuerOrdre(String pseu,RMI rmi, Integer nump) throws RemoteException{
        Integer numero;
        numero=rmi.ReSetOrdre(pseu, nump);
        return numero;
    }

    public Boolean AQuiDeJouer(String pseu, RMI rmi,Integer nump) throws RemoteException{
        Boolean ordre;
        ordre=rmi.RetrouverOrdreJoueur(pseu, nump);
        return ordre;
    }

    public Integer AfficherDesPartie(RMI rmi,Integer nump) throws RemoteException{
        int size;
        valdesworld=rmi.AfficherToutDes(nump);
        System.out.println("\n Dés de tout le monde :" );
        size=valdesworld.size();
        for(String vald: valdesworld){
            System.out.print(vald+",");
        }
        return size;
    }
    
    public void ResultatCompterDes(String pseu, RMI rmi, Integer nump) throws RemoteException{
        Boolean resultat=rmi.OnCompte(pseu, nump);
        if(resultat){
            System.out.println("\n Il y a bien le nombre de dés annoncé à cette valeur");
        }else{
            System.out.println("\n Menteur !! ");
        }
    }

    public void ResultatCompterDesTtPile(String pseu, RMI rmi,Integer nump) throws RemoteException{
        Boolean resultat=rmi.OnCompteTtPile(pseu, nump);
        if(resultat){
            System.out.println("\n Il y a bien le tout-pile !");
        }else{
            System.out.println("\n Désolé, vous vous êtes trompé ! Vous perdez un dé ! ");
        }
    }

    public void EnvoiEnchere(RMI rmi,Integer nbDé ,Integer valDé ,String pseu, Integer nump) throws RemoteException{
        rmi.RecuperationAnn(nbDé,valDé,pseu, nump);
    }

    public void AfficherAnnonce(RMI rmi, Integer nump) throws RemoteException{
        ArrayList<Annonce> annonces = new ArrayList<>();
        annonces=rmi.AfficherTouteAnnonces(nump);
        for(Annonce A:annonces){
            System.out.println("Nombre de dés :"+A.getDé()+ " ayant une valeur de "+A.getAnnValDé());
        }
    }

    public void Remisecompteur(RMI rmi, Integer nump) throws RemoteException{
        rmi.ResetCompteur(nump);
    }

    // Décision surenchere, menteur, tout pile
    public Integer FaireChoix(RMI rmi) throws RemoteException{
        int choix1=0;
        if(RecupIndice(rmi,nump)==0){
            choix1=0;
            System.out.println("\n Vous devez faire une annonce"); 
            do{
                System.out.println("Tapez 1 pour enchérir "); 
                try{
                    choix1 = (int) Integer.parseInt(scanch.next());
                    isNumber = false; // execute que si parseInt ne lance pas d'exception
                    System.out.println("Vous avez entré " + choix1);
                }catch(NumberFormatException e){
                    System.out.println("Vous devez entrer le chiffre 1.");
                } 

            }while(choix1 !=1 );
        }else
        {
            choix1=0;
            System.out.println("\n Vous devez surenchérir, accusez de menteur votre prédecesseur ou tenter le tout-pile !"); 
            do{
                System.out.println("Tapez 1 pour surenchérir, 2 pour le menteur, trois pour le tout-pile !"); 
                try{
                    choix1 = (int) Integer.parseInt(scanch.next());
                    isNumber = false; // execute que si parseInt ne lance pas d'exception
                    System.out.println("Vous avez entré " + choix1);
                }catch(NumberFormatException e){
                    System.out.println("Vous devez entrer les chiffres 1, 2 ou 3.");
                } 
            }while(choix1 !=1 && choix1!=2 && choix1!=3);
        }
        ChangeIndice(1, rmi,nump);
        return choix1;
    }

    public Integer Recupenbdélastann(RMI rmi, Integer nump) throws RemoteException{
        ArrayList<Annonce> annonce2 = new ArrayList<>();
        Integer nbdéslastann=0;
        Annonce lasta;
        annonce2=rmi.AfficherTouteAnnonces(nump);
        if(annonce2.size()==0){
            nbdéslastann=0;
        }
        else if(annonce2.size()==1){
            lasta=annonce2.get(0);
            nbdéslastann=lasta.getDé();
        }else{
            lasta =annonce2.get(annonce2.size()-1);
            nbdéslastann=lasta.getDé();
        }
        return nbdéslastann;
    }
    public Integer Recupevaldélastann(RMI rmi, Integer nump) throws RemoteException{
        ArrayList<Annonce> annonce2 = new ArrayList<>();
        Integer valdéslasta =0;
        Annonce lasta;
        annonce2=rmi.AfficherTouteAnnonces(nump);
        if(annonce2.size()==0){
            valdéslasta=0;
        }
        else if(annonce2.size()==1){
            lasta=annonce2.get(0);
            valdéslasta=lasta.getAnnValDé();
        }else{
            lasta =annonce2.get(annonce2.size()-1);
            valdéslasta=lasta.getAnnValDé();
        }
        return valdéslasta;
  }

    // Methode surenchere rajout de dés

    public Integer SurenchereDé(RMI rmi ) throws RemoteException{
        nbDé=0;      
        do{
            System.out.println("Veuillez entrer votre annonce dont le nombre de dé OU la valeur de la face est superieur "
                     + "à la précédente annonce : ");
            System.out.println("Nombre de dés sur la table :  ");
        try{
            nbDé = (int) Integer.parseInt(scanch.next());
            isNumber = false; // execute que si parseInt ne lance pas d'exception
        }catch(NumberFormatException e){
            System.out.println("Vous devez entrer des chiffres.");
        }
        }while(nbDé ==0 );

        return nbDé;
}  

// Methode surenchere face de dés
    public Integer SurenchereFaceDé(RMI rmi ) throws RemoteException{     
        faceDé=0;
        while(faceDé !=2 && faceDé !=3 && faceDé !=4 && faceDé !=5 && faceDé !=6){
            System.out.println("face des dés :  (compris entre 2 & 6)");
            try{
                faceDé = (int) Integer.parseInt(scanch.next());
                isNumber = false; // execute que si parseInt ne lance pas d'exception
            }catch(NumberFormatException e){
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
    
    public ArrayList<Dés> ReRemplirJoueur(String pseu, RMI rmi, Integer nump) throws RemoteException{
        rmi.ReRemplirJoueur(pseu, nump);
        return dd;
    }        
    
    public Integer RecupRepere(RMI rmi, Integer nump) throws RemoteException{
        int repere;
        repere=rmi.RecupRepere(nump);
        return repere;
    }
    /*
    public void ChangeRepere(RMI rmi, Integer rp,Integer nump)throws RemoteException{
        rmi.ChangeRepere(rp, nump);
    }
    */
    public Integer RecupCasMenteur(RMI rmi, Integer nump) throws RemoteException{
        int cas2=0;
        cas2=rmi.RecupCasMenteur(nump);
        return cas2;
    }
    
    public void ChangeCasMenteur(RMI rmi, Integer cm, Integer nump)throws RemoteException{
        rmi.ChangeCasmenteur(cm, nump);
    }
    
    public Integer RecupCasPile(RMI rmi, Integer nump) throws RemoteException{
        int cas3=0;
        cas3=rmi.RecupCasPile(nump);
        return cas3;
    }
    
    public void ChangeCasPile(RMI rmi, Integer ctp, Integer nump) throws RemoteException{
       rmi.ChangeCasPile(ctp, nump);
    }
    
    public void ClearttAnnonces(RMI rmi, Integer nump) throws RemoteException{
       rmi.ClearttAnnonces(nump);
    }

    public void ReSetpassage(RMI rmi, Integer nump)throws RemoteException{
        rmi.ReSetpassage(nump);
    }
    
    public void ClearArrayworld(RMI rmi, Integer nump)throws RemoteException{
        rmi.ClearArrayworld(nump);
    }
    
    public void setCompteur(RMI rmi, Integer nump) throws RemoteException{
        rmi.SetCompteur(nump);
    }
    
    public Integer RecuPassage(RMI rmi, String attri1) throws RemoteException{
        int path=0;
        path=rmi.RecuPassage(attri1);
        return path;
    }
    
    public void Supprimerjoueur(RMI rmi, String pseu, Integer nump) throws RemoteException{
        rmi.Supprimerjoueur(pseu,nump);
    }

    public void ChangeIndice(Integer in, RMI rmi,Integer nump) throws RemoteException{
        rmi.ChangeIndice(in, nump);
    }
    
    public Integer RecupIndice(RMI rmi, Integer nump) throws RemoteException{
        int indice;
        indice=rmi.RecupIndice(nump);

        return indice;
    }
    public void ChangePointe(RMI rmi) throws RemoteException{
       rmi.ChangePointe();
    }
    
    public Integer RecupPointe(RMI rmi) throws RemoteException{
        int pointeur;
        pointeur=rmi.RecupPointe();
        return pointeur;
    }
    
    public Integer AfficherLesParties(RMI rmi)throws RemoteException{
        int sizeprch=0;
        ArrayList<Integer> partie = new ArrayList<>();
        ArrayList<String> membre = new ArrayList<>();
        partie=rmi.AfficherLesParties();
        for(Integer I: partie){
            System.out.println("Numero de la partie :"+I);
            membre=rmi.AfficherMembre(I);
            for(String S:membre){
                System.out.println("Membre de la partie :"+S);
            }
        }
        sizeprch=partie.size();
        return sizeprch;
    }
    
    public void CreerJoueurRejPart(RMI rmi, String pseu, String col, Integer nump) throws RemoteException{
        System.out.println("c'est parti !");
        rmi.CreerJoueurRejPart(pseu, col, nump);
    }
    
    public Integer RecupNumPart(RMI rmi, String attri1)throws RemoteException{
        int nump;
        nump=rmi.RecupNumPart(attri1);
        return nump;
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
        int sizep;
        sizep=AfficherLesParties(rmi2);
        int chp=0;
        chp=ChoixPartie(rmi2);
        ChangePointe(rmi2);
        if(chp==1){
            CreationJoueur(rmi2, pseu, col);
        }else if(chp==2){
            int chqp=0;
            chqp=Quellepartie(sizep);
            CreerJoueurRejPart(rmi2,pseu,col,chqp);
        }
        nump=RecupNumPart(rmi2, pseu);
        RemplirDesJoueur(rmi2, pseu, nump);
        AfficherDesJoueur(rmi2, pseu, nump);
        AfficherJoueur(rmi2, nump);

        int go;
        go=NbJoueurPresent(rmi2, nump);
        while(go<2){
            System.out.println("\n On attend le nombre suffisant de joueur");
            Thread.sleep(4000);
            go=NbJoueurPresent(rmi2, nump);
        }

        numpass=AttribuerOrdre(pseu,rmi2, nump);
    }

    public void Tour(RMI rmi2) throws RemoteException, InterruptedException{
        count2=0;
        count3=0;
        int fin;
        fin=NbJoueurPresent(rmi2, nump);
        if(fin==1){
            System.out.println("Felicitation vous avez gagné");
            AfficherJoueur(rmi2, nump);
        }
        Thread.sleep(5000);
        int choix=0;
        while(AfficherDesJoueur(rmi2, pseu, nump)==0){
            System.out.println("you loose");
            Supprimerjoueur(rmi2, pseu, nump);
        }
        go2=NbJoueurPresent(rmi2, nump);
        ordre=AQuiDeJouer(pseu, rmi2, nump);
        if(RecupCasMenteur(rmi2, nump)>=2 || RecupCasPile(rmi2, nump)>=3){
            ordre=false;
            count2=0;
            count3=0;
        }
        while(!ordre){
           
            System.out.println("\n Attendez votre tour");
            Thread.sleep(6000);   
            //System.out.println("le cas menteur :"+RecupCasMenteur(rmi2, nump));
            
            //System.out.println("cas menteur avant la remise a zero :"+RecupCasMenteur(rmi2,nump));
            if(RecupCasMenteur(rmi2,nump)==2+(go2) && count2<1 ){
                System.out.println("Voici vos nouveau dés ");
                ReRemplirJoueur(pseu, rmi2,nump);
                AfficherDesJoueur(rmi2, pseu,nump);
                //System.out.println("je remet a zero le cas menteur");
                ChangeCasMenteur(rmi2, 0,nump);
                numpass=RecuPassage(rmi2, pseu);
                count2++;
            }
 
            //System.out.println("cas tt pile avant la remise a zero :"+RecupCasPile(rmi2,nump));
            if(RecupCasPile(rmi2,nump)==3+(go2) && count3<1){
                System.out.println("Voici vos nouveau des apres tt pile");
                ReRemplirJoueur(pseu, rmi2,nump);
                AfficherDesJoueur(rmi2, pseu,nump);
                ChangeCasPile(rmi2, 0,nump);
                //System.out.println("je remet a zero le cas tt pile");
                numpass=RecuPassage(rmi2, pseu);
                count3++;
            }
            
            if(RecupCasMenteur(rmi2,nump)>=2 && count2<1){
                Remisecompteur(rmi2, nump);
                System.out.println("Voici vos nouveau dés");
                ReRemplirJoueur(pseu, rmi2,nump);
                AfficherDesJoueur(rmi2, pseu,nump);
                ChangeCasMenteur(rmi2, RecupCasMenteur(rmi2,nump)+1,nump);
                ClearttAnnonces(rmi2, nump);
                numpass=RecuPassage(rmi2, pseu);
                count2++;
            }
            //System.out.println("le cas tt pile :"+RecupCasPile(rmi2,nump));
            if(RecupCasPile(rmi2,nump)>=3 && count3<1){
                Remisecompteur(rmi2,nump);
                System.out.println("Voici vos nouveau des apres tt pile");
                ReRemplirJoueur(pseu, rmi2,nump);
                AfficherDesJoueur(rmi2, pseu,nump);
                ChangeCasPile(rmi2, RecupCasPile(rmi2,nump)+1,nump);
                ClearttAnnonces(rmi2,nump);
                numpass=RecuPassage(rmi2, pseu);
                count3++;
                //&& count3<1
            }
   
            ordre=AQuiDeJouer(pseu, rmi2,nump);
        }
        System.out.println("\n A votre tour");
        Thread.sleep(3000);
        AfficherAnnonce(rmi2,nump);
        choix=FaireChoix(rmi2);
        ChangeCasPile(rmi2, 0,nump);
        ChangeCasMenteur(rmi2, 0,nump);
        SwitchCase(rmi2, choix);
    }
    
    public void SwitchCase(RMI rmi2, Integer choix2) throws RemoteException, InterruptedException{            
        switch(choix2)
        {
            case 1:
                int nbj=0;
                int repere;
                nbd=0;
                fd=0;
                do{
                    nbd=SurenchereDé(rmi2);
                    fd=SurenchereFaceDé(rmi2);  
                }while(Recupenbdélastann(rmi2,nump)>=nbd && Recupevaldélastann(rmi2,nump)>=fd);
                nbj=NbJoueurPresent(rmi2,nump);
                repere=RecupRepere(rmi2,nump);
                if(RecupRepere(rmi2,nump)==nbj){
                    EnvoiEnchere(rmi2, nbd, fd, pseu,nump);
                    Remisecompteur(rmi2,nump);
                    Tour(rmi2);
                }
                else{
                    EnvoiEnchere(rmi2, nbd, fd, pseu,nump);
                    setCompteur(rmi2,nump);
                    Tour(rmi2);
                }
                break;
                
            case 2:
                Menteur();
                AfficherDesPartie(rmi2,nump);
                ResultatCompterDes(pseu, rmi2,nump);
                ChangeCasMenteur(rmi2, 2,nump);
                count2=0;
                ClearArrayworld(rmi2,nump);
                ChangeIndice(0, rmi2,nump);
                Tour(rmi2);
                break;

            case 3:
                ToutPile();
                AfficherDesPartie(rmi2,nump);
                ResultatCompterDesTtPile(pseu, rmi2,nump);
                ChangeCasPile(rmi2, 3,nump);
                count3=0;
                ClearArrayworld(rmi2,nump);
                ChangeIndice(0, rmi2,nump);
                Tour(rmi2);
                break;
        }
        Tour(rmi2);
    }
    
    public Integer ChoixPartie(RMI rmi2) throws RemoteException{
        int choixpartie=0;
        choixpartie=0;
        if(RecupPointe(rmi2)==0){
            do{
                System.out.println("creer une nouvelle partie taper 1");        
                try{
                    choixpartie = (int) Integer.parseInt(scanch.next());
                }catch(NumberFormatException e){
                    System.out.println("Vous devez entrer des chiffres.");
                }
            }while(choixpartie!=1);
        }else{
            do{
                System.out.println("taper 1 pour creer une nouvelle partie ou 2 pour rejoindre une partie en cour :");        
                try{
                choixpartie = (int) Integer.parseInt(scanch.next());
                }catch(NumberFormatException e){
                    System.out.println("Vous devez entrer des chiffres.");
                }
            }while(choixpartie!=1 && choixpartie!=2);
        }
        return choixpartie;
    }
    
    public Integer Quellepartie(Integer s){
        int choixquellepartie=0;
        do{
            System.out.println("Choisissez le numéro de la partie que vous désirez rejoindre:");
            try{
            choixquellepartie = (int) Integer.parseInt(scanch.next());
            }catch(NumberFormatException e){
                System.out.println("Vous devez entrer des chiffres.");
            }
        }while(choixquellepartie>s);
        return choixquellepartie;
    }
    
    public static void main(String args[]) throws RemoteException, InterruptedException{
        RMIClient client = new RMIClient();
        RMI rmi2=client.connectServer();
        if(rmi2==null){
            System.out.println("Erreur de connexion");
        }
        int taille;
        client.LancementPartie(rmi2);
        taille=3;
        while(taille!=0){
            client.Tour(rmi2);
        }
       
    }
    
}
