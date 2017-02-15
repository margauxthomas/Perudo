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
    
    //declarations des méthodes sur le serveur appelées par les clients, le client passe par cette interface pour utiliser 
    //les fonctionnalités du serveur
    public String getData(String text) throws RemoteException;
    public String setPseudo(String pseu) throws RemoteException;
    public String setCouleur(String col) throws RemoteException;
    public void CreerJoueur(String pseu, String col) throws RemoteException;
    public String RecuperationAnn(int nombreDé, int faceduDé, String attri1, Integer nump) throws RemoteException; 
    public ArrayList<Couleur> AfficherCouleur() throws RemoteException;
    public ArrayList<User> AfficherUser() throws RemoteException;
    public HashMap<String, String> AfficherJoueur(Integer nump)  throws RemoteException ;
    public ArrayList<String> getUser() throws RemoteException;
    public ArrayList<String> getCouleurs() throws RemoteException;
    public Integer CompteJoueur(Integer nump) throws RemoteException;
    public ArrayList<String> AfficherDesJoueur(String attri1,Integer nump )throws RemoteException;
    public ArrayList<String> AfficherToutDes(Integer nump) throws RemoteException;
    public Boolean Comparaison(Integer nb, Integer val, Integer nump) throws RemoteException;
    public Boolean ComparaisonTtPile(Integer nb, Integer val,Integer nump) throws RemoteException;
    public Integer SetOrdre(String attri1, Integer nump)throws RemoteException;
    public Boolean RetrouverOrdreJoueur(String attri1, Integer nump)throws RemoteException;
    public void EnleverDes(String attri1, Integer nump)throws RemoteException;
    public void AjouterDes(String attri1, Integer nump)throws RemoteException;
    public ArrayList<Annonce> AfficherTouteAnnonces(Integer nump) throws RemoteException;
    public boolean OnCompte(String attri1, Integer nump) throws RemoteException;
    public ArrayList<Dés> RemplirDesJoueur(String attri1, Integer nump)throws RemoteException;
    public boolean OnCompteTtPile(String attri1, Integer nump) throws RemoteException;
    public void ResetCompteur(Integer nump) throws RemoteException;
    public ArrayList <Dés> ReRemplirJoueur(String attri1, Integer nump) throws RemoteException;
    public Integer RecupRepere(Integer nump) throws RemoteException;
    public Integer ReSetOrdre(String attri1, Integer nump)throws RemoteException;
    public Integer RecupCasMenteur(Integer nump) throws RemoteException;
    public void ChangeCasmenteur(Integer cm, Integer nump) throws RemoteException;
    public void ReSetpassage(Integer nump)throws RemoteException;
    public void ClearArrayworld(Integer nump)throws RemoteException;
    public void SetCompteur(Integer nump)throws RemoteException;
    public Integer RecupCasPile(Integer nump) throws RemoteException;
    public void ChangeCasPile(Integer ctp, Integer nump) throws RemoteException;
    public void ClearttAnnonces(Integer nump) throws RemoteException;
    public Integer RecuPassage(String attri1) throws RemoteException;
    public void Supprimerjoueur(String attri1, Integer nump) throws RemoteException;
    public void ChangeIndice(Integer in, Integer nump) throws RemoteException;
    public Integer RecupIndice(Integer nump) throws RemoteException;
    public ArrayList<Integer> AfficherLesParties()throws RemoteException;
    public void CreerJoueurRejPart(String pseu, String col, Integer nump) throws RemoteException;
    public Integer RecupNumPart(String attri1)throws RemoteException;
    public void ChangePointe() throws RemoteException;
    public Integer RecupPointe() throws RemoteException;
    public ArrayList<String> AfficherMembre(Integer nump) throws RemoteException;
    
}