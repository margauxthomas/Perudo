package package1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author stri
 */
public class User extends RMIClient{
    //declaration variable utilisateur
    private String pseudo;
    
    //constructeur
    public User(String pseudo){
    super();
    this.pseudo=pseudo;
  
    }
    //getteur et setteur

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    
    public void hello()
    {
        System.out.println("\n YOO : "+getPseudo());
    }

}
