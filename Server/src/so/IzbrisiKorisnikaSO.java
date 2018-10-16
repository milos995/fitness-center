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
public class IzbrisiKorisnikaSO extends OpstaSO{

    public IzbrisiKorisnikaSO(DBBroker db) {
        super(db);
    }
    
    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        Korisnik k = (Korisnik) obj;
        db.izbrisi(k);
        for (KoriscenjeUsluge ku : k.getLku()) {
            db.izbrisi(ku);
        }
    }
    
}
