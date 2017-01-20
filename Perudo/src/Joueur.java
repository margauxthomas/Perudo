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
    private String couleur;
    private String pseudo;
    
    public Joueur(String pseudo, String couleur){
    super();
    this.couleur=couleur;
    this.pseudo=pseudo;

    }

    public String getCouleur() {
        return couleur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    
     public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir votre pseudo :");
        String pseu = sc.nextLine();
        
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Veuillez saisir une couleur de dés pour la partie :");
        String col = sc1.nextLine();
         Joueur j1= new Joueur(pseu, col);

         j1.connectServer(pseu);
         System.out.println(j1.getPseudo());
         System.out.println(j1.getCouleur());
         
     }

}
