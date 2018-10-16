/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import forme.FrmServer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Milos
 */
public class NitOsvezi extends Thread{
    FrmServer frmServer;

    public NitOsvezi(FrmServer frmServer) {
        this.frmServer = frmServer;
    }

    @Override
    public void run() {
        while(!isInterrupted()){
            frmServer.osveziTabelu();

            try {
                sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(NitOsvezi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
}
