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
public class Usluga implements OpstiDomenskiObjekat{
    private int uslugaID;
    private String nazivUsluge;
    private Zaposleni zaposleni;

    public Usluga() {
    }

    public Usluga(int uslugaID, String nazivUsluge, Zaposleni zaposleni) {
        this.uslugaID = uslugaID;
        this.nazivUsluge = nazivUsluge;
        this.zaposleni = zaposleni;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public int getUslugaID() {
        return uslugaID;
    }

    public void setUslugaID(int uslugaID) {
        this.uslugaID = uslugaID;
    }

    public String getNazivUsluge() {
        return nazivUsluge;
    }

    public void setNazivUsluge(String nazivUsluge) {
        this.nazivUsluge = nazivUsluge;
    }

    @Override
    public String toString() {
        return nazivUsluge;
    }   

    @Override
    public String vratiNazivTabele() {
        return "usluga";
    }

    @Override
    public String vratiAtributeZaInsert() {
        return "UslugaID, NazivUsluge, ZaposleniID";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return uslugaID+", '"+nazivUsluge+"', "+zaposleni.getZaposleniID();
    }

    @Override
    public String vratiJoinUpit() {
        return "u JOIN zaposleni z ON u.ZaposleniID = z.ZaposleniID";
    }

    @Override
    public String vratiWhereUpit() {
        return "";
    }

    @Override
    public List<OpstiDomenskiObjekat> ucitaj(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista = new LinkedList<>();
        while(rs.next()){
            int zaposleniID = rs.getInt("ZaposleniID");
            String zIme = rs.getString("Ime");
            String zPrezime = rs.getString("Prezime");
            String korIme = rs.getString("KorisnickoIme");
            String lozinka = rs.getString("Lozinka");
            Zaposleni z = new Zaposleni(zaposleniID, zIme, zPrezime, korIme, lozinka);
            
            int uslugaID = rs.getInt("UslugaID");
            String nazivUsluge = rs.getString("NazivUsluge");
            Usluga u = new Usluga(uslugaID, nazivUsluge, z);
            lista.add(u);
        }
        return lista;
    }

    @Override
    public OpstiDomenskiObjekat ucitajJedan(ResultSet rs) throws Exception {
        Usluga u = null;
        while(rs.next()){
            int zaposleniID = rs.getInt("ZaposleniID");
            String zIme = rs.getString("Ime");
            String zPrezime = rs.getString("Prezime");
            String korIme = rs.getString("KorisnickoIme");
            String lozinka = rs.getString("Lozinka");
            Zaposleni z = new Zaposleni(zaposleniID, zIme, zPrezime, korIme, lozinka);
            
            int uslugaID = rs.getInt("UslugaID");
            String nazivUsluge = rs.getString("NazivUsluge");
            u = new Usluga(uslugaID, nazivUsluge, z);
        }
        return u;
    }

    @Override
    public String vratiID() {
        return "UslugaID";
    }

    @Override
    public String vratiWhereIzbrisi() {
        return "UslugaID = "+uslugaID;
    }

    @Override
    public String vratiSetZaIzmenu() {
        return "NazivUsluge = '"+nazivUsluge+"', ZaposleniID = "+zaposleni.getZaposleniID();
    }

    @Override
    public String vratiWhereJedan() {
        return "WHERE UslugaID = "+uslugaID;
    }
    
}
