/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Usluga;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Milos
 */
public class VratiListuUslugaSO extends OpstaSO{
    private List<Usluga> lu = new LinkedList<>();

    public VratiListuUslugaSO(DBBroker db) {
        super(db);
    }    
    
    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        List<OpstiDomenskiObjekat> lista = db.vratiSve(new Usluga(), "");
        for (OpstiDomenskiObjekat odo : lista) {
            lu.add((Usluga) odo);
        }
    }

    public List<Usluga> getLu() {
        return lu;
    }   
}
