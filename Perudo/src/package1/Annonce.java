package package1;

import java.rmi.RemoteException;
import java.util.*;
import java.io.Serializable;

public class Annonce implements Serializable{    

    private int nbDé;
    private int valDé;
    
    //Constructeur
    public Annonce (Integer nbDé, Integer valDé) {
        this.nbDé= nbDé;
        this.valDé = valDé;
    }  
    public int getDé() {
        return nbDé;
    }    
    
    public int getAnnValDé() {
        return valDé;
    }    
    
    public void setAnnonce(Integer nbDé, Integer valDé) {
        this.nbDé = nbDé;
        this.valDé = valDé;
    }
     
    private static Vector classes = new Vector();
    public static Annonce getInstanceA(Integer nbdé, Integer valDé){
=======
     
    private static Vector classes = new Vector();
    public static Annonce getInstanceA(Integer nbdé, Integer valDé){
public class Annonce implements Serializable{
    
      private int nbDé;


public class Annonce implements Serializable{    

    private int nbDé;

    private int valDé;
    
    //Constructeur
    public Annonce (Integer nbDé, Integer valDé) {
        this.nbDé= nbDé;
        this.valDé = valDé;
    }  
    public int getDé() {
        return nbDé;
    }    
    
    public int getAnnValDé() {
        return valDé;
    }    
    
    public void setAnnonce(Integer nbDé, Integer valDé) {
        this.nbDé = nbDé;
        this.valDé = valDé;
    }

     private static Vector classes = new Vector();
/*     public static Annonce getInstanceA(Integer nbdé, Integer valDé){
=======
     
    private static Vector classes = new Vector();
    public static Annonce getInstanceA(Integer nbdé, Integer valDé){
        Annonce tmp = new Annonce(nbdé, valDé);
        if (classes.contains(tmp)) {
            Enumeration enume = classes.elements();
            while (enume.hasMoreElements()) {
                Annonce element = (Annonce) enume.nextElement();
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
