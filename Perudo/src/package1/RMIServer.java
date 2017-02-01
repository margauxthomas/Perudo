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
    
    //déclaration variables serveur :
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
  
    Partie  p= new Partie("prems","test");
    
    Integer passage=1;
    Integer numpass=1;
    Integer compteur=1;
    Boolean resultat;
    
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
        Joueur j = new Joueur(c,u, tmp,0);
        joueurs.add(j);
        p.addjoueur(j);
    }
    
    public Integer CompteJoueur(){
        int element=joueurs.size();
    
        return element;
    }
        
    public ArrayList<String> AfficherDesJoueur(String attri1)throws RemoteException{
        ArrayList <String> a1= new ArrayList <>();
        a1.clear();
        System.out.println(a1.size());
        
        for(Joueur J:joueurs){
             if(J.getDude().getPseudo().equals(attri1)){
                 System.out.println("dans afficher joueur"+J.getDude().getPseudo());
                 a1=J.DonnerValeur();  
             }
        }
        System.out.println(a1.size());

        return a1;
    }
    
    public ArrayList<Dés> RemplirDesJoueur(String attri1)throws RemoteException{
        ArrayList <Dés> a = new ArrayList<>(); 
        a.clear();
        System.out.println(a.size());
        
        for(Joueur J: joueurs){
            if(J.getDude().getPseudo().equals(attri1)){
                System.out.println("dans afficher joueur"+J.getDude().getPseudo());
                System.out.println(J.getDd().size());
                J.setDd(J.RemplirDes());       
                a=J.getDd();

                for (String val : J.DonnerValeur()) {
                    valdesworld.add(val);
                    System.out.println("Dés du joueur lors du remplissage"+val);
                }
            }
        }
        
        return a;
            
    }
    
    public ArrayList <Dés> ReRemplirJoueur(String attri1) throws RemoteException{
        ArrayList<Dés> a2 = new ArrayList<>();
        a2.clear();
        for(Joueur J: joueurs){
            if(J.getDude().getPseudo().equals(attri1)){
                J.setDd(J.ReRemplirDés());
                a2=J.getDd();
               
                for (String val : J.DonnerValeur()) {
                    valdesworld.add(val);
                    System.out.println(val);
                }
            }
        }
        
        return a2;
    }
        
    public void ClearArrayworld()throws RemoteException{
        valdesworld.clear();
    }
    
    public Integer SetOrdre(String attri1)throws RemoteException{
        int jeton=0;
        System.out.println("premier passageeeeeee"+passage);
        Integer numero=0;
        for(Joueur J: joueurs){
            if(J.getDude().getPseudo().equals(attri1)){
                jeton=passage;    
                J.setPassage(jeton);
                numero=J.getPassage();
                System.out.println(J.getPassage());
                System.out.println(numero);
            }
        }
        passage++;
        
        return numero;
    }
    
    public void ReSetpassage()throws RemoteException{
        passage=1;
    }
    
    public Integer ReSetOrdre(String attri1)throws RemoteException{
        Integer numero=0;
        for(Joueur J: joueurs){
            if(J.getDude().getPseudo().equals(attri1)){
                J.setPassage(passage);
                numero=J.getPassage();
            }
            passage++;
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
    
    public boolean OnCompte(String attri1) throws RemoteException{
        RecupererLastJoueur(attri1);
        return resultat;
        
    }
    
    public boolean OnCompteTtPile(String attri1) throws RemoteException{
        RecupererLastJoueurTtPile(attri1);
        return resultat;
        
    }
    
    public void AfficherNumPassage(){
        for(Joueur J:joueurs){
            System.out.println(J.getPassage());
        }
    }
    
    public void RecupererLastJoueur(String attri1) throws RemoteException{
        int numj=0;
        String pseulj=null;
        for(Joueur J: joueurs){
            if(J.getDude().getPseudo().equals(attri1)){
                numj=J.getPassage();
            }
        }
        for(Joueur J: joueurs){
            if(J.getPassage()==numj-1){
                pseulj=J.getDude().getPseudo();
            }else if(J.getPassage()==numj+1){
                pseulj=J.getDude().getPseudo();
            }
        }
                
        RecupererAnnonce(pseulj);
        TraitementResultat(pseulj,attri1);
    }
    
    public void RecupererLastJoueurTtPile(String attri1) throws RemoteException{
        int numj2=0;
        String pseulj=null;

        for(Joueur J: joueurs){
            if(J.getDude().getPseudo().equals(attri1)){
                numj2=J.getPassage();              
            }
        }
        for(Joueur J: joueurs){
            if(J.getPassage()==numj2-1){
                pseulj=J.getDude().getPseudo();
            }else if(J.getPassage()==numj2+1){
                pseulj=J.getDude().getPseudo();
            }
        }
        RecupererAnnonceTtPile(pseulj);
        TraitementResultatTtPile(attri1);
    }
     
    public void TraitementResultat(String pseulj,String pseujec) throws RemoteException{
        if(resultat){
            System.out.println("je suis dans le cas ou le resultat est bon");
            EnleverDes(pseujec);
        }
        else{
            System.out.println("je suis dans le cas ou le resultat est pas bon");
            EnleverDes(pseulj);
        }          
    }
    
    public void TraitementResultatTtPile(String pseujec) throws RemoteException{
        if(resultat){
            System.out.println("je suis dans le cas ou le resultat tt pile est bon");
            AjouterDes(pseujec);
        }
        else{
            System.out.println("je suis dans le cas ou le resultat est pas bon");
            EnleverDes(pseujec);
        }                      
    }
     
    public void RecupererAnnonce(String attri1){
        int nb=0;
        int val=0;

        for(Joueur J:joueursann){            
            nb=J.getVal().getDé();
            val=J.getVal().getAnnValDé();     
        }
        resultat=Comparaison(nb,val);
    }
    
    public void RecupererAnnonceTtPile(String attri1){
        int nb=0;
        int val=0;
       
        for(Joueur J:joueursann){     
            nb=J.getVal().getDé();
            val=J.getVal().getAnnValDé();    
        }
        resultat=ComparaisonTtPile(nb,val);      
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
        System.out.println("nombre de dés"+nb);
        System.out.println("nombre "+c);
        if(c!=nb){
            resultat=false;
        }else if(c==nb) resultat=true;
        System.out.println(resultat);
    
        return resultat;
    }
    
    public void EnleverDes(String attri1)throws RemoteException{
        System.out.println("je suis dans enlever des");
        System.out.println("en parametere : "+attri1);
        int rang=0;
        for(Joueur J: joueurs){
            if(J.getDude().getPseudo().equals(attri1)){
                rang=J.getPassage();
                System.out.println("jenleve des des");
                J.EnleverDes();
                J.setPassage(1);
            }
        }
        
        for(int i=0;i<rang;i++){
            int tmp=0;
            System.out.println("tmp : "+tmp);
            tmp= joueurs.get(i).getPassage();
            joueurs.get(i).setPassage(tmp+1);
            System.out.println("attroibution de ce numero de passage : "+tmp+1);
        }
        for(Joueur J: joueurs){
            if(J.getDude().getPseudo().equals(attri1)){
               // System.out.println("jenleve des des");
                //J.EnleverDes();
                J.setPassage(1);
            }
        }
    }
    
    public void ResetCompteur() throws RemoteException{
        compteur=1;
    }
    
    public void SetCompteur()throws RemoteException{
        compteur++;
    }
   
    public void AjouterDes(String attri1)throws RemoteException{
        int rang=0;
        for(Joueur J: joueurs){
            if(J.getDude().getPseudo().equals(attri1)){
                J.AjouterDes();
                rang=J.getPassage();
                J.setPassage(1);
            }
        }
        for(int i=0;i<rang;i++){
            int tmp=0;
            tmp= joueurs.get(i).getPassage();
            System.out.println("tmp : "+tmp);
            joueurs.get(i).setPassage(tmp+1);
            System.out.println("attroibution de ce numero de passage : "+tmp+1);
        }
        for(Joueur J: joueurs){
            if(J.getDude().getPseudo().equals(attri1)){
               // J.AjouterDes();
               J.setPassage(1);
            }
        }
    }
    
    public String RecuperationAnn(int nombreDé, int faceduDé, String attri1) throws RemoteException {
        Annonce a = new Annonce(nombreDé,faceduDé);
        a=Annonce.getInstanceA(nombreDé, faceduDé);
        a.setAnnonce(nombreDé, faceduDé);
        ttlesann.add(a);
        for(Joueur J: joueurs){
            if(J.getDude().getPseudo().equals(attri1)){
                J.setVal(a);
                joueursann.clear();
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
    
    public ArrayList<Annonce> AfficherTouteAnnonces() throws RemoteException{
        for(Annonce A:ttlesann){
            System.out.println("Nombre de dés :"+A.getDé()+ " ayant une valeur de "+A.getAnnValDé());
        }
        return ttlesann;
    }
  
    public HashMap<String, String> AfficherJoueur(){
        HashMap<String, String> h1 = new HashMap<>(); 
        for(Joueur J:joueurs){
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
    
    int indice=0;
    public void ChangeIndice(Integer in) throws RemoteException{
        indice=in;
    }
    
    public Integer RecupIndice() throws RemoteException{
        return indice;
    }

    int repere=1;
    public Integer RecupRepere() throws RemoteException{
        return compteur;
    }
    
    public void ChangeRepere(Integer rp) throws RemoteException{
        repere=rp;
        System.out.println("repere "+repere);
    }
    
    int cas2=0;
    public Integer RecupCasMenteur() throws RemoteException{
        return cas2;
    }
    
    public void ChangeCasmenteur(Integer cm) throws RemoteException{
        cas2=cm;
        System.out.println("cas menteur"+cas2);
    }
    
    int cas3=0;
    public Integer RecupCasPile() throws RemoteException{
        return cas3;
    }
    
    public void ChangeCasPile(Integer ctp) throws RemoteException{
        cas3=ctp;
        System.out.println("cas tt pile"+cas3);
        }
    
    public void ClearttAnnonces() throws RemoteException{
        ttlesann.clear();
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
    
    public void Supprimerjoueur(String attri1) throws RemoteException{
        for(Joueur J: joueurs){
            if(J.getDude().getPseudo().equals(attri1)){
                joueurs.remove(J);
            }
        }
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
