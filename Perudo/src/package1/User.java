package package1;

import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.Vector;

public class User {
    
    private String pseudo;
    
    public User(String pseudo) {
        this.pseudo=pseudo;
    }
    
    public String getPseudo() {
        return pseudo;
    }
    
    public void setPseudoU(String pseudo) {
        this.pseudo = pseudo;
    }
    
    public void hello(){
        System.out.println("\n YOO : "+getPseudo());
    }
    
    private static Vector classes = new Vector();
     
    public static User getInstanceU(String attribut1){
        User tmp = new User(attribut1);
        if (classes.contains(tmp)) {
            Enumeration enume = classes.elements();
            while (enume.hasMoreElements()) {
                User element = (User) enume.nextElement();
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
