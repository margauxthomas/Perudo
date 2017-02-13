/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;
import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.Random;
import java.util.Vector;
import java.io.Serializable;

public class Dés implements Serializable{
   
    private String valeur;

    public Dés (String valeur){
       this.valeur=valeur;
    }
   
    public void setValeur(String v){
       this.valeur=v;
    }
    
    public String getVal(){
       return valeur;
    }
   
    public String getValeur() {
        Random rand = new Random();
        int nombreAleatoire = rand.nextInt(6 - 1 + 1) + 1;
        if(nombreAleatoire==1){
            valeur="perudo";   
        }
        else{
            valeur=Integer.toString(nombreAleatoire);
        }
        return valeur;
    }
    
    private static Vector classes = new Vector();

    public static Dés getInstanceD(String v){            
        Dés tmp = new Dés(v);
        if (classes.contains(tmp)) {
            Enumeration enume = classes.elements();
            while (enume.hasMoreElements()) {
                Dés element = (Dés) enume.nextElement();
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
   
}
