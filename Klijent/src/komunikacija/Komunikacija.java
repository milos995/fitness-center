/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import transfer.ObjekatOdgovor;
import transfer.ObjekatZahtev;

/**
 *
 * @author Milos
 */
public class Komunikacija {
    private static Komunikacija instance;
    private Socket soket;

    private Komunikacija() {    
    }

    public static Komunikacija getInstance() {
        if(instance==null){
            instance = new Komunikacija();
        }
        return instance;
    }    
    
    public void posaljiZahtev(ObjekatZahtev zahtev){
        try {
            ObjectOutputStream ous = new ObjectOutputStream(soket.getOutputStream());
            ous.writeObject(zahtev);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Greska prilikom slanja zahteva serveru!");
        }
    }  
    
    public ObjekatOdgovor primiOdgovor(){
        ObjekatOdgovor odgovor = new ObjekatOdgovor();
        
        try {
            ObjectInputStream ois = new ObjectInputStream(soket.getInputStream());
            odgovor = (ObjekatOdgovor) ois.readObject();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Greska prilikom prijema zahteva od servera!");
        }
        return odgovor;
    }
    
    public boolean poveziSeNaServer(String ip, int port){
        try {
            soket = new Socket(ip, port);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }    
}
