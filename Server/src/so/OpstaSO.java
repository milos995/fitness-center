/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DBBroker;

/**
 *
 * @author Milos
 */
public abstract class OpstaSO {
    protected DBBroker db;

    public OpstaSO(DBBroker db) {
        this.db = db;
    }   
    
    public final void izvrsenjeSO(Object obj) throws Exception{
        try {
            ucitajDrajver();
            otvoriKonekciju();
            proveriPreduslov(obj);
            izvrsiOperaciju(obj);
            commitTransakcije();
        } catch (Exception ex) {
            rollbackTransakcije();
            throw ex;
        }finally {
            zatvoriKonekciju();
        }        
    }

    private void ucitajDrajver() throws Exception {
        db.ucitajDrajver();
    }

    private void otvoriKonekciju() throws Exception {
        db.uspostaviKonekciju();
    }

    protected abstract void proveriPreduslov(Object obj) throws Exception;

    protected abstract void izvrsiOperaciju(Object obj) throws Exception;

    private void commitTransakcije() throws Exception {
        db.commitTransakcije();
    }

    private void rollbackTransakcije() throws Exception {
        db.rollbackTransakcije();
    }

    private void zatvoriKonekciju() throws Exception {
        db.raskiniKonekciju();
    }
}
