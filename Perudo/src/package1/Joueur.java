package package1;

import java.util.Scanner;
import java.util.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author stri
 */
public class Joueur extends RMIClient{
    
       public Joueur() {
    }
    
    ArrayList<User> users= new ArrayList<>();
   
    public void addUsers(User u)
    {
       users.add(u);
     }
    
    ArrayList<Couleur> col= new ArrayList<>();
   
    public void addCouleur(Couleur c)
    {
       col.add(c);
     }
    
    public HashMap<String, String> getJoueurs()
     {
        
        HashMap<String, String> h = new HashMap<>(); 
        for(User R : users){
            for(Couleur C :col){
                h.put(R.getPseudo(), C.getCouleur());
                }
            }       
    return h;
    } 

}
