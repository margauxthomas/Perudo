/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;

import java.util.*;

/**
 *
 * @author jonat
 */
public class Annonce {
    
      private int nbDé;
    private int valDé;
    
    //Constructeur
   public Annonce (int nbDé, int valDé) {
    this.nbDé= nbDé;
    this.valDé = valDé;
   }  
   public int getDé() {
        return nbDé;
    }    
    public int getAnnValDé() {
        return valDé;
    }    
     public void setAnnonce(int nbDé, int valDé) {
        this.nbDé = nbDé;
        this.valDé = valDé;
    }
}
