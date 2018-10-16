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
public class IzmeniKorisnikaSO extends OpstaSO {

    public IzmeniKorisnikaSO(DBBroker db) {
        super(db);
    }

    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        Korisnik k = (Korisnik) obj;
        db.izmeni(k);
        for (KoriscenjeUsluge ku : k.getLku()) {
            if (ku.getOperacija().equals("brisanje")) {
                db.izbrisi(ku);
            }
            if (ku.getKoriscenjeID() > 0) {
                db.izmeni(ku);
            }
            if (ku.getKoriscenjeID() == 0) {
                ku.setKorisnikID(k.getKorisnikID());
                int kuID = db.vratiMaxID(ku);
                ku.setKoriscenjeID(kuID);
                db.zapamti(ku);
            }            
        }
    }

}
