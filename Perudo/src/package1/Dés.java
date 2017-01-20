/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;
   
import java.util.Random;

/**
 *
 * @author stri
 */
public class Dés {
   private String valeur;

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
    
    public static void main(String args[]){
            Dés d=new Dés();
           System.err.println("valeur aleatoir   "+d.getValeur());
  
    }
   
}
