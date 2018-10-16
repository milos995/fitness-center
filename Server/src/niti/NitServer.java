/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import logika.Kontroler;
import util.Util;

/**
 *
 * @author Milos
 */
public class NitServer extends Thread{
    private ServerSocket ss;

    @Override
    public void run() {
        try {
            int port = Integer.parseInt(Util.getInstance().getValue("port"));
            ss = new ServerSocket(port);            
            System.out.println("Server je pokrenut");
            NitPrekini np = new NitPrekini(Kontroler.getInstance().getLk(), this);
            np.start();
            while(!isInterrupted()){
                Socket soket = ss.accept();
                System.out.println("Klijent se povezao");
                NitKlijent nk = new NitKlijent(Kontroler.getInstance().getLk().size()+1,soket, LocalTime.now());
                nk.start();
                Kontroler.getInstance().ubaciKlijenta(nk);
            }
        } catch (IOException ex) {
            System.out.println("Server je zaustavljen");
        }
    }    

    public ServerSocket getSs() {
        return ss;
    }   
}
