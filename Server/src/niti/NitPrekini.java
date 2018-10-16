/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Milos
 */
public class NitPrekini extends Thread{
    List<NitKlijent> lk;
    NitServer ns;

    public NitPrekini(List<NitKlijent> lk, NitServer ns) {
        this.lk = lk;
        this.ns = ns;
    }

    @Override
    public void run() {
        while(!isInterrupted()){
            if(ns.isInterrupted()){
                try {
                    if(!ns.getSs().isClosed()){
                        ns.getSs().close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(NitPrekini.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                this.interrupt();
            }
        }
    }   
}
