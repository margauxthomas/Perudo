package package1;
import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.Vector;

public class Couleur {
    private String couleur;
    
    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }
    
    public Couleur(String couleur){
        this.couleur=couleur;
    }
    
    private static Vector classes = new Vector();
    
    public static Couleur getInstanceC(String attribut1){        
        Couleur tmp = new Couleur(attribut1);
        if (classes.contains(tmp)) {
            Enumeration enume = classes.elements();
            while (enume.hasMoreElements()) {
                Couleur element = (Couleur) enume.nextElement();
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