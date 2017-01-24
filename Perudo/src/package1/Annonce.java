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
    
   private ArrayList<String> annonce1=new ArrayList<>();
    private int nbDé;
    private int valDé;
    private String valeur;
    String nombreDé = Integer.toString(nbDé);
    String valeurDé = Integer.toString(valDé);
    String annonce = nombreDé+"dés"+valeurDé;

    public String getAnn() {
        return annonce;
    }    
    
     public void setAnn(String val) {
        this.valeur = val;
    }
}