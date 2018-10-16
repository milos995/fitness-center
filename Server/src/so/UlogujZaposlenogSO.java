/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DBBroker;
import domen.Zaposleni;

/**
 *
 * @author Milos
 */
public class UlogujZaposlenogSO extends OpstaSO{
    private Zaposleni zaposleni;

    public UlogujZaposlenogSO(DBBroker db) {
        super(db);
    }    
    
    @Override
    protected void proveriPreduslov(Object obj) throws Exception {        
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        Zaposleni z = (Zaposleni) obj;
        zaposleni = (Zaposleni) db.vrati(z);
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }    
}
