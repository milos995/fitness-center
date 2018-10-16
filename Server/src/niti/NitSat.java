/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;

/**
 *
 * @author Milos
 */
public class NitSat extends Thread{
    JLabel lblSat;
    DateTimeFormatter dtf;

    public NitSat(JLabel lblSat) {
        this.lblSat = lblSat;
        dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    }

    @Override
    public void run() {
        while(!isInterrupted()){
            LocalTime vreme = LocalTime.now();
            lblSat.setText(vreme.format(dtf));
        }
    }    
}
