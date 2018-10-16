/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DBBroker;
import domen.KoriscenjeUsluge;
import domen.Korisnik;
import domen.OpstiDomenskiObjekat;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Milos
 */
public class PrikaziKorisnikaSO extends OpstaSO {

    private Korisnik k;

    public PrikaziKorisnikaSO(DBBroker db) {
        super(db);
    }

    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        int korisnikID = (int) obj;
        Korisnik kor = new Korisnik();
        kor.setKorisnikID(korisnikID);
        k = (Korisnik) db.vrati(kor);
        KoriscenjeUsluge ku = new KoriscenjeUsluge();
        ku.setKorisnikID(korisnikID);
        List<KoriscenjeUsluge> lku = new LinkedList<>();
        List<OpstiDomenskiObjekat> lista = db.vratiSve(ku, "");
        for (OpstiDomenskiObjekat opstiDomenskiObjekat1 : lista) {
            lku.add((KoriscenjeUsluge) opstiDomenskiObjekat1);
        }
        k.setLku(lku);
    }

    public Korisnik getK() {
        return k;
    }
}
