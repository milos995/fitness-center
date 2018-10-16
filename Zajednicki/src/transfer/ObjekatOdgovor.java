/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author Milos
 */
public class ObjekatOdgovor implements Serializable{
    private int status;
    private String poruka;
    private Object odgovor;

    public ObjekatOdgovor() {
    }

    public ObjekatOdgovor(int status, String poruka, Object odgovor) {
        this.status = status;
        this.poruka = poruka;
        this.odgovor = odgovor;
    }   

    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }    
}
