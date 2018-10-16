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
public class PretraziKorisnikeSO extends OpstaSO{
    List<Korisnik> lk = new LinkedList<>();

    public PretraziKorisnikeSO(DBBroker db) {
        super(db);
    }    

    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        String filter = (String) obj;
        List<OpstiDomenskiObjekat> lista1 = db.vratiSve(new Korisnik(), filter);
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : lista1) {            
            Korisnik k = (Korisnik) opstiDomenskiObjekat;
            KoriscenjeUsluge ku = new KoriscenjeUsluge();            
            ku.setKorisnikID(k.getKorisnikID());
            List<KoriscenjeUsluge> lku = new LinkedList<>();
            List<OpstiDomenskiObjekat> lista2 = db.vratiSve(ku, "");
            for (OpstiDomenskiObjekat opstiDomenskiObjekat1 : lista2) {
                lku.add((KoriscenjeUsluge) opstiDomenskiObjekat1);
            }
            k.setLku(lku);
            lk.add(k);
        }
    }

    public List<Korisnik> getLk() {
        return lk;
    }    
}
