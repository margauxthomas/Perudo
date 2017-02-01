package package1;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author stri
 */
public interface RMI extends Remote{
    public String getData(String text) throws RemoteException;
   // public String getEnchere(int Dé,int faceduDé) throws RemoteException; 
    public String setPseudo(String pseu) throws RemoteException;
    public String setCouleur(String col) throws RemoteException;
    public void CreerJoueur(String pseu, String col) throws RemoteException;
     public String RecuperationAnn(int nombreDé, int faceduDé, String attri1, String attri2, ArrayList <Dés> dd1,Integer p) throws RemoteException; 
    public ArrayList<Couleur> AfficherCouleur() throws RemoteException;
    public ArrayList<User> AfficherUser() throws RemoteException;
    public HashMap<String, String> AfficherJoueur()  throws RemoteException ;
    public ArrayList<String> getUser() throws RemoteException;
    public ArrayList<String> getCouleurs() throws RemoteException;
    public Integer CompteJoueur() throws RemoteException;
    public ArrayList<String> AfficherDesJoueur(String attri1)throws RemoteException;
    public ArrayList<String> AfficherToutDes() throws RemoteException;
    public Boolean Comparaison(Integer nb, Integer val) throws RemoteException;
    public Boolean ComparaisonTtPile(Integer nb, Integer val) throws RemoteException;
    public Integer SetOrdre(String attri1)throws RemoteException;
    public Boolean RetrouverOrdreJoueur(Integer numero)throws RemoteException;
     public void EnleverDes(String attri1)throws RemoteException;
   public void AjouterDes(String attri1)throws RemoteException;
    public ArrayList<Annonce> AfficherTouteAnnonces() throws RemoteException;
    public boolean OnCompte(String attri1) throws RemoteException;
   public ArrayList<Dés> RemplirDesJoueur(String attri1)throws RemoteException;
    public boolean OnCompteTtPile(String attri1) throws RemoteException;
    public void ResetCompteur() throws RemoteException;
    public ArrayList <Dés> ReRemplirJoueur(String attri1) throws RemoteException;
    public Integer RecupRepere() throws RemoteException;
    public void ChangeRepere(Integer rp) throws RemoteException;
     public Integer ReSetOrdre(String attri1)throws RemoteException;
     public Integer RecupCasMenteur() throws RemoteException;
     public void ChangeCasmenteur(Integer cm) throws RemoteException;
     public void ReSetpassage()throws RemoteException;
     public void ClearArrayworld()throws RemoteException;
      public void SetCompteur()throws RemoteException;
      public Integer RecupCasPile() throws RemoteException;
       public void ChangeCasPile(Integer ctp) throws RemoteException;
       public void ClearttAnnonces() throws RemoteException;
 public Integer RecuPassage(String attri1) throws RemoteException;
  public void Supprimerjoueur(String attri1) throws RemoteException;
  public void ChangeIndice(Integer in) throws RemoteException;
   public Integer RecupIndice() throws RemoteException;
// public HashMap<String, HashSet<String>> CreerPartie() throws RemoteException;


}