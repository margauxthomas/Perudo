package package1;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author stri
 */
public class RMIClient {
    public static void main(String args[]){
        RMIClient client = new RMIClient();
        
        client.connectServer(" margx");
  
    }

    public void connectServer(String nom) {
        try{
            Registry reg = LocateRegistry.getRegistry("127.0.0.1",1099);
            RMI rmi = (RMI) reg.lookup("server");
            System.out.println("Connected to Server");
            String text = rmi.getData("Lets the game begin "+nom);
            System.out.println(text);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
