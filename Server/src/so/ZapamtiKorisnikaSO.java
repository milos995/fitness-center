/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DBBroker;
import domen.KoriscenjeUsluge;
import domen.Korisnik;

/**
 *
 * @author Milos
 */
public class ZapamtiKorisnikaSO extends OpstaSO{

    public ZapamtiKorisnikaSO(DBBroker db) {
        super(db);
    }   

    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        Korisnik k = (Korisnik) obj;
        int korisnikID = db.vratiMaxID(k);
        k.setKorisnikID(korisnikID);
        db.zapamti(k);        
        for (KoriscenjeUsluge ku : k.getLku()) {
            ku.setKorisnikID(korisnikID);
            int kuID = db.vratiMaxID(ku);
            ku.setKoriscenjeID(kuID);
            db.zapamti(ku);
        }
    }
    
}
