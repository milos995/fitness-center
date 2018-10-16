/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Milos
 */
public class Mesto implements OpstiDomenskiObjekat{
    private int pttBroj;
    private String naziv;

    public Mesto() {
    }

    public Mesto(int pttBroj, String naziv) {
        this.pttBroj = pttBroj;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getPttBroj() {
        return pttBroj;
    }

    public void setPttBroj(int pttBroj) {
        this.pttBroj = pttBroj;
    }

    @Override
    public String toString() {
        return naziv+" - "+pttBroj;
    }   

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Mesto){
            if(((Mesto) obj).pttBroj==this.pttBroj){
                return true;
            }
        }
        return false;
    }  

    @Override
    public String vratiNazivTabele() {
        return "mesto";
    }

    @Override
    public String vratiAtributeZaInsert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiVrednostiZaInsert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OpstiDomenskiObjekat> ucitaj(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista = new LinkedList<>();
        while(rs.next()){
                int pttBroj = rs.getInt("PTTBroj");
                String naziv = rs.getString("Naziv");
                Mesto m = new Mesto(pttBroj, naziv);
                lista.add(m);
            }
        return lista;
    }

    @Override
    public String vratiJoinUpit() {
        return "";
    }

    @Override
    public String vratiWhereUpit() {
        return "";
    }

    @Override
    public OpstiDomenskiObjekat ucitajJedan(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiWhereIzbrisi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiSetZaIzmenu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiWhereJedan() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
