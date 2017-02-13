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


public class RMIServer extends UnicastRemoteObject implements RMI{
    
    // déclaration variables serveur :
    ArrayList<User> users= new ArrayList<>();
    ArrayList<String> pseudo= new ArrayList<>();
    ArrayList<String> flag= new ArrayList<>();
    ArrayList<Couleur> cols= new ArrayList<>();
    ArrayList<Joueur> joueurs= new ArrayList<>();
    ArrayList<Joueur> joueurspa= new ArrayList<>();
    ArrayList<Joueur> joueursann= new ArrayList<>();
    ArrayList<String> valeurdesj = new ArrayList<>();
    //ArrayList<String> valdesworld = new ArrayList<>();
    //ArrayList<Annonce> ttlesann = new ArrayList<>();
    ArrayList<Dés> ddre = new ArrayList<>();
    ArrayList<Partie> ttpartie = new ArrayList<>();
  
    
    
    //Integer passage=1;
    //Integer numpass=1;
    //Integer compteur=1;
    //Boolean resultat;
    Integer pid=0;
    
    //methodes serveurs

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
        Couleur c = new Couleur(col);
        users.add(u);
        cols.add(c);
        
        ArrayList<Dés> tmp = new ArrayList<>();
        tmp.clear();
        Joueur j = new Joueur(c,u, tmp,0,0);
        CreerPartie(j);
        joueurs.add(j); 
    }
    
    public void CreerJoueurRejPart(String pseu, String col, Integer nump) throws RemoteException{
        pseudo.add(pseu);
        flag.add(col);
        User u = new User(pseu);
        Couleur c = new Couleur(col);
        users.add(u);
        cols.add(c);
        
        ArrayList<Dés> tmp = new ArrayList<>();
        tmp.clear();
        Joueur j = new Joueur(c,u, tmp,0,nump);
        joueurs.add(j); 
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                System.out.println("Jajoute un joueru a l'arraylist de la partie");
                P.addjoueur(j);
            }
        }
    }
  
    public void CreerPartie(Joueur j)throws RemoteException{
        pid++;
        Integer passage=1;
        Integer compteur=1;
        Boolean resultat=false;
        Integer indice=0;
        Integer cas2=0;
        Integer cas3=0;
        ArrayList<String> tmp2 = new ArrayList<>();
        ArrayList<Annonce> tmp3= new ArrayList<>();
        ArrayList<Joueur> tmp = new ArrayList<>();
        Partie p = new Partie(pid, tmp, passage, compteur, resultat, cas2, cas3, indice, tmp2, tmp3);
        p.addjoueur(j);
        System.out.println("le numero de passage enregistrtré"+p.getPassage());
        j.setNump(pid);
        ttpartie.add(p);
    }
    
    public ArrayList<Integer> AfficherLesParties()throws RemoteException{
        ArrayList <Integer> ap= new ArrayList <>();
        ap.clear();
        System.out.println(ap.size());
        for(Partie P:ttpartie){
            ap.add(P.getPid());
        }
        return ap;
    }
    
    public ArrayList<String> AfficherMembre(Integer nump) throws RemoteException{
        ArrayList <String> pseudos= new ArrayList <>();
        ArrayList <Joueur> jj= new ArrayList <>();
        String pseupseu=null;
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                jj=P.getAj();
                for(Joueur J:jj){
                    pseupseu=J.getDude().getPseudo();
                    pseudos.add(pseupseu);
                } 
            }
        }
        return pseudos;
    }
    
    public Integer RecupNumPart(String attri1)throws RemoteException{
        Integer numerop=0;    
        for(Partie P:ttpartie){
            ArrayList<Joueur> joueurp = new ArrayList<>();
            joueurp=P.getAj();
            for(Joueur J: joueurp){
                if(J.getDude().getPseudo().equals(attri1)){
                    numerop=J.getNump();
                }
            }
        }
    return numerop;    
    }
   
    public Integer CompteJoueur(Integer nump){
        int element=0;
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                element=P.getAj().size();
            }
        }
    return element;
    }
        
    public ArrayList<String> AfficherDesJoueur(String attri1, Integer nump)throws RemoteException{
        ArrayList <String> a1= new ArrayList <>();
        a1.clear();
        System.out.println(a1.size());
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                for(Joueur J:P.getAj()){
                    if(J.getDude().getPseudo().equals(attri1)){
                    System.out.println("dans afficher joueur"+J.getDude().getPseudo());
                    a1=J.DonnerValeur();  
                    }
                }
            }
        }
        System.out.println(a1.size());

        return a1;
    }
    
    public ArrayList<Dés> RemplirDesJoueur(String attri1, Integer nump)throws RemoteException{
        ArrayList <Dés> a = new ArrayList<>(); 
        a.clear();
        System.out.println(a.size());
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                for(Joueur J:P.getAj()){
                    if(J.getDude().getPseudo().equals(attri1)){
                        System.out.println("dans afficher joueur"+J.getDude().getPseudo());
                        System.out.println(J.getDd().size());
                        J.setDd(J.RemplirDes());       
                        a=J.getDd();

                        for (String val : J.DonnerValeur()) {
                            P.adddes(val);
                            System.out.println("Dés du joueur lors du remplissage"+val);
                        }
                    }
                }
            }
        }
        
        return a;
            
    }
    
    public ArrayList <Dés> ReRemplirJoueur(String attri1, Integer nump) throws RemoteException{
        ArrayList<Dés> a2 = new ArrayList<>();
        a2.clear();
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                for(Joueur J:P.getAj()){
                    if(J.getDude().getPseudo().equals(attri1)){
                    J.setDd(J.ReRemplirDés());
                    a2=J.getDd();
               
                    for (String val : J.DonnerValeur()) {
                        P.adddes(val);
                        System.out.println(val);
                       }
                    }
                }
            }
        }
        
        return a2;
    }
        
    public void ClearArrayworld(Integer nump)throws RemoteException{
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                P.getValdeworld().clear();
            }
        }    
    }
    
    public Integer SetOrdre(String attri1, Integer nump)throws RemoteException{
        int jeton=0;
        //System.out.println("premier passageeeeeee"+passage);
        //Integer passage=0;
        Integer numero=0;
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                System.out.println("le numéro de partie est"+P.getPid());
                System.out.println("Le numéro de passage de la partie est"+P.getPassage());
                for(Joueur J:joueurs){
                    if(J.getDude().getPseudo().equals(attri1)){
                        jeton=P.getPassage();    
                        //passage=P.getPassage();
                        J.setPassage(jeton);
                        numero=J.getPassage();
                        System.out.println(J.getPassage());
                        System.out.println(numero);
                    }
                }
            P.setPassage(P.getPassage()+1);
            }
        }    
 
        return numero;
    }
    
    public void ReSetpassage(Integer nump)throws RemoteException{
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                P.setPassage(1);
            }
        }
    }
    
    public Integer ReSetOrdre(String attri1, Integer nump)throws RemoteException{
        Integer numero=0;
        Integer passage=0;
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                for(Joueur J:joueurs){
                    if(J.getDude().getPseudo().equals(attri1)){
                        J.setPassage(P.getPassage());
                        passage=P.getPassage();
                        numero=J.getPassage();
                    }
                }
            P.setPassage(P.getPassage()+1);
            }
        }    
        return numero;
    }
    
    
    public Boolean RetrouverOrdreJoueur(String attri1, Integer nump)throws RemoteException{
        Boolean ordre=true;
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                for(Joueur J:joueurs){
                    if(J.getDude().getPseudo().equals(attri1)){
                            
                        System.out.println("numero de passage "+J.getPassage());
                        System.out.println("jeton :"+P.getCompteur());
                        if(Objects.equals(J.getPassage(), P.getCompteur())){
                            ordre=true;
                        }else ordre=false;
                            System.out.println("Resultat ordre: "+ordre);
                    }
                }
            }    
        }
        return ordre;
    }
    
     //methode affichant les des de tt les joueurs 
    
    public ArrayList<String> AfficherToutDes(Integer nump){
        ArrayList<String> valdesworld = new ArrayList<>();
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                for(String vald: P.getValdeworld()){
                    valdesworld=P.getValdeworld();
                    System.out.println("des tt le monde :" +vald);
                }
            }
        }
        return valdesworld;
    }
    
    public boolean OnCompte(String attri1, Integer nump) throws RemoteException{
        Boolean resultat=true;
        RecupererLastJoueur(attri1, nump);
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                resultat=P.getResultat();
            }
        }
        return resultat;
    }
    
    public boolean OnCompteTtPile(String attri1, Integer nump) throws RemoteException{
        Boolean resultat=true;
        RecupererLastJoueurTtPile(attri1, nump);
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                resultat=P.getResultat();
            }
        }
        return resultat;
    }
    /*
    public void AfficherNumPassage(){
        for(Joueur J:joueurs){
            System.out.println(J.getPassage());
        }
    }
    */
    
    public void RecupererLastJoueur(String attri1, Integer nump) throws RemoteException{
        int numj=0;
        String pseulj=null;
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                for(Joueur J:P.getAj()){
                    if(J.getDude().getPseudo().equals(attri1)){
                    numj=J.getPassage();
                    }
                }
            }
        }
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                for(Joueur J:P.getAj()){
                    if(J.getPassage()==numj-1){
                        pseulj=J.getDude().getPseudo();
                    }else if(J.getPassage()==numj+1){
                        pseulj=J.getDude().getPseudo();
                    }
                }
            }
        }
                
        RecupererAnnonce(pseulj, nump);
        TraitementResultat(pseulj,attri1, nump);
    }
    
    public void RecupererLastJoueurTtPile(String attri1, Integer nump) throws RemoteException{
        int numj2=0;
        String pseulj=null;

        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                for(Joueur J:P.getAj()){
                    if(J.getDude().getPseudo().equals(attri1)){
                        numj2=J.getPassage();              
                    }
                }
            }   
        }
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                for(Joueur J:P.getAj()){
                    if(J.getPassage()==numj2-1){
                       pseulj=J.getDude().getPseudo();
                    }else if(J.getPassage()==numj2+1){
                        pseulj=J.getDude().getPseudo();
                    }
                }
            }
        }
        RecupererAnnonceTtPile(pseulj, nump);
        TraitementResultatTtPile(attri1, nump);
    }
     
    public void TraitementResultat(String pseulj,String pseujec, Integer nump) throws RemoteException{
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                if(P.getResultat()){
                    System.out.println("je suis dans le cas ou le resultat est bon");
                    EnleverDes(pseujec, nump);
                }
                else{
                    System.out.println("je suis dans le cas ou le resultat est pas bon");
                    EnleverDes(pseulj, nump);
                } 
            }
        }
    }
    
    public void TraitementResultatTtPile(String pseujec, Integer nump) throws RemoteException{
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                if(P.getResultat()){
                    System.out.println("je suis dans le cas ou le resultat tt pile est bon");
                    AjouterDes(pseujec, nump);
                }
                else{
                    System.out.println("je suis dans le cas ou le resultat est pas bon");
                    EnleverDes(pseujec, nump);
                }  
            }
        }
    }
     
    public void RecupererAnnonce(String attri1, Integer nump ){
        int nb=0;
        int val=0;

        for(Joueur J:joueursann){  
            if(J.getDude().getPseudo().equals(attri1)){
                nb=J.getVal().getDé();
                val=J.getVal().getAnnValDé();     
            }
        }
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                P.setResultat(Comparaison(nb,val, nump));
            }
        }
    }
    
    public void RecupererAnnonceTtPile(String attri1, Integer nump){
        int nb=0;
        int val=0;
       
        for(Joueur J:joueursann){     
            if(J.getDude().getPseudo().equals(attri1)){
                nb=J.getVal().getDé();
                val=J.getVal().getAnnValDé();
            }
        }
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                P.setResultat(ComparaisonTtPile(nb,val, nump));
            }
        }
    }

    public Boolean Comparaison(Integer nb, Integer val, Integer nump){
        String v =Integer.toString(val);
        int c=0;
        Boolean resultat=null;
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                for(String vald: P.getValdeworld()){
                    if(vald.equals("perudo")){
                        vald=v;
                        if (vald.equals(v)){
                            c++;
                        }
                    }else if(vald.equals(v)){
                        c++;
                    }
                }
            }
        }
        System.out.println("nombre "+c);
        if(c<nb){
            resultat=false;
        }else resultat=true;
            System.out.println(resultat);
            
        return resultat;
    }
    
    public Boolean ComparaisonTtPile(Integer nb, Integer val, Integer nump){
        String v =Integer.toString(val);
        int c=0;
        Boolean resultat=null;
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                for(String vald: P.getValdeworld()){
                    if(vald.equals("perudo")){
                        vald=v;
                        if (vald.equals(v)){
                            c++;
                        }
                    }else if(vald.equals(v)){
                        c++;
                    }
                }
            }
        }
        System.out.println("nombre de dés"+nb);
        System.out.println("nombre "+c);
        if(c!=nb){
            resultat=false;
        }else if(c==nb) resultat=true;
        System.out.println(resultat);
    
        return resultat;
    }
    
    public void EnleverDes(String attri1, Integer nump)throws RemoteException{
        System.out.println("je suis dans enlever des");
        System.out.println("en parametere : "+attri1);
        int rang=0;
        for(Joueur J: joueurs){
            if(J.getDude().getPseudo().equals(attri1)){
                rang=J.getPassage();
                System.out.println("jenleve des des");
                J.EnleverDes();
                //J.setPassage(1);
            }
        }
        
        //for(int i=0;i<rang;i++){
            int tmp=0;
            for(Partie P:ttpartie){
                if(P.getPid().equals(nump)){
                    for(Joueur J:P.getAj()){
                        System.out.println("tmp : "+tmp);
                        if(J.getPassage()<rang){
                            tmp= J.getPassage();
                            System.out.println("celui qui a le numero changé"+J.getDude().getPseudo());
                            System.out.println("numero de passage de leleement i"+tmp);
                            J.setPassage(tmp+1);
                            tmp=tmp+1;
                            System.out.println("attroibution de ce numero de passage : "+tmp);
                        }
                    }
                }
            }
        //}
        for(Joueur J: joueurs){
            if(J.getDude().getPseudo().equals(attri1)){
               // System.out.println("jenleve des des");
                //J.EnleverDes();
                J.setPassage(1);
            }
        }
    }
    
    public void ResetCompteur(Integer nump) throws RemoteException{
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
               P.setCompteur(1);
            }
        }
    }
    
    public void SetCompteur(Integer nump)throws RemoteException{
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                P.setCompteur(P.getCompteur()+1);
            }
        }
    }
   
    public void AjouterDes(String attri1, Integer nump)throws RemoteException{
        int rang=0;
        for(Joueur J: joueurs){
            if(J.getDude().getPseudo().equals(attri1)){
                J.AjouterDes();
                rang=J.getPassage();
                //J.setPassage(1);
            }
        }
        //for(int i=0;i<rang;i++){
            int tmp=0;
            for(Partie P:ttpartie){
                if(P.getPid().equals(nump)){
                    for(Joueur J:P.getAj()){
                       if(J.getPassage()<rang){
                            tmp= J.getPassage();
                            System.out.println("numero de passage de leleement i"+tmp);
                            J.setPassage(tmp+1);
                            tmp=tmp+1;
                            System.out.println("attroibution de ce numero de passage : "+tmp);
                        }
                    }
                }
            }
       // }
        for(Joueur J: joueurs){
            if(J.getDude().getPseudo().equals(attri1)){
               // J.AjouterDes();
               J.setPassage(1);
            }
        }
    }
    
    public String RecuperationAnn(int nombreDé, int faceduDé, String attri1, Integer nump) throws RemoteException {
        Annonce a = new Annonce(nombreDé,faceduDé);
        a=Annonce.getInstanceA(nombreDé, faceduDé);
        a.setAnnonce(nombreDé, faceduDé);
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){       
                P.addAnn(a);
            }
        } 
        for(Joueur J: joueurs){
            if(J.getDude().getPseudo().equals(attri1)){
                J.setVal(a);
                //joueursann.clear();
                joueursann.add(J);
            }
        }
        
        return "Vous avez annoncé "+nombreDé+" dés "+faceduDé; 
    }
    
    public void Afficherannjou(){
        for(Joueur J:joueursann){
            System.out.println(J.getVal().getAnnValDé());
            System.out.println(J.getVal().getDé());
            
        }
    }
    
    public ArrayList<Annonce> AfficherTouteAnnonces(Integer nump) throws RemoteException{
        ArrayList<Annonce> ttlesann = new ArrayList<>();
        ttlesann.clear();
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){     
                ttlesann=P.getTtlesann();
                for(Annonce A:P.getTtlesann()){
                    System.out.println("Nombre de dés :"+A.getDé()+ " ayant une valeur de "+A.getAnnValDé());
                }
            }
        }
        return ttlesann;
    }
  
    public HashMap<String, String> AfficherJoueur(Integer nump){
        HashMap<String, String> h1 = new HashMap<>(); 
        for(Partie P:ttpartie){
                if(P.getPid().equals(nump)){
                    for(Joueur J:P.getAj()){
                        Couleur ctmp=J.getPions();
                        User utmp=J.getDude();
                        h1.put(utmp.getPseudo(), ctmp.getCouleur());
                    }
                    Set cles = h1.keySet();
                    Iterator it = cles.iterator();
                    while (it.hasNext()){
                        Object cle = it.next(); 
                        Object valeur = h1.get(cle); 
                        System.out.println(cle+ " "+valeur);
                    }
                }
        }
        return h1; 
    }
    
    public void AfficherCouleurlist(){
        for (Couleur C : cols) {
            System.out.println(C.getCouleur());
        }
    }
        
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
    
    //int indice=0;
    public void ChangeIndice(Integer in, Integer nump) throws RemoteException{
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){       
                P.setIndice(in);
            }
        }
    }
    
    public Integer RecupIndice(Integer nump) throws RemoteException{
        int indice=0;
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){ 
                indice=P.getIndice();
            } 
        }
        return indice;
    }

    //int repere=1;
    public Integer RecupRepere(Integer nump) throws RemoteException{
        int compteur=0;
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){ 
                compteur=P.getCompteur();
            }
        }
        return compteur;
    }
    /*
    public void ChangeRepere(Integer rp) throws RemoteException{
        repere=rp;
        System.out.println("repere "+repere);
    }
    */
    
    //int cas2=0;
    public Integer RecupCasMenteur(Integer nump) throws RemoteException{
        int cas2=0;
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                cas2=P.getCas2();
            }
        }
        return cas2;
    }
    
    public void ChangeCasmenteur(Integer cm, Integer nump) throws RemoteException{
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                P.setCas2(cm);
                System.out.println("cas menteur"+P.getCas2());
            }
        }        
        //cas2=cm; 
    }
    
    //int cas3=0;
    public Integer RecupCasPile(Integer nump) throws RemoteException{
        int cas3=0;
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                cas3=P.getCas3();
            }
        }        
        return cas3;
    }
    
    public void ChangeCasPile(Integer ctp, Integer nump) throws RemoteException{
        for(Partie P:ttpartie){
            if(P.getPid().equals(nump)){
                P.setCas3(ctp);        
        //cas3=ctp;
                System.out.println("cas tt pile"+P.getCas3());
            }
        }
    }
    
    public void ClearttAnnonces(Integer nump) throws RemoteException{
        for(Partie P:ttpartie){
           if(P.getPid().equals(nump)){
                P.cleartttlesann();
           }
        }
    }
    
    public Integer RecuPassage(String attri1) throws RemoteException{
        int path=0;
        for(Joueur J: joueurs){
            if(J.getDude().getPseudo().equals(attri1)){
                path=J.getPassage();
                System.out.println("le numerod e passage recup"+path);
            }
        }
        return path;
    }
    
    public void Supprimerjoueur(String attri1, Integer nump) throws RemoteException{
        for(Partie P:ttpartie){
                if(P.getPid().equals(nump)){
                    for(Joueur J:P.getAj()){
                        if(J.getDude().getPseudo().equals(attri1)){
                        P.getAj().remove(J);
                        }
                    }
                }
        }
    }
    
    int pointeur=0;
    public void ChangePointe() throws RemoteException{
        pointeur=1;
    }
    
    public Integer RecupPointe() throws RemoteException{
        return pointeur;
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
