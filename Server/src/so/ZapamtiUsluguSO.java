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
public class ZapamtiUsluguSO extends OpstaSO{

    public ZapamtiUsluguSO(DBBroker db) {
        super(db);
    }    

    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        Usluga u = (Usluga) obj;
        int uID = db.vratiMaxID(u);
        u.setUslugaID(uID);
        db.zapamti(u);
    }
    
}
