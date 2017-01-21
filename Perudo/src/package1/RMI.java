package package1;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

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
    public String setPseudo(String pseu) throws RemoteException;
    public String setCouleur(String col) throws RemoteException;
    public HashMap<String, String> CreerJoueur(String pseu, String col) throws RemoteException;
    public ArrayList<Couleur> AfficherCouleur() throws RemoteException;
    public ArrayList<User> AfficherUser() throws RemoteException;
}
