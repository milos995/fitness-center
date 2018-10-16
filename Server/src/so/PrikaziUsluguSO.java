/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DBBroker;
import domen.Usluga;

/**
 *
 * @author Milos
 */
public class PrikaziUsluguSO extends OpstaSO{
    Usluga u;
    
    public PrikaziUsluguSO(DBBroker db) {
        super(db);
    }
    
    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        int uslugaID = (int) obj;
        Usluga usluga = new Usluga();
        usluga.setUslugaID(uslugaID);
        u = (Usluga) db.vrati(usluga);
    }

    public Usluga getU() {
        return u;
    }   
}
