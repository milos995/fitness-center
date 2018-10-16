/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DBBroker;
import domen.Mesto;
import domen.OpstiDomenskiObjekat;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Milos
 */
public class VratiListuMestaSO extends OpstaSO{
    List<Mesto> lm = new LinkedList<>();

    public VratiListuMestaSO(DBBroker db) {
        super(db);
    }    
    
    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        List<OpstiDomenskiObjekat> lista = db.vratiSve(new Mesto(), "");
        for (OpstiDomenskiObjekat odo : lista) {
            lm.add((Mesto) odo);
        }
    }

    public List<Mesto> getLm() {
        return lm;
    }    
}
