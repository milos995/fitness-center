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
public class Korisnik implements OpstiDomenskiObjekat{
    private int korisnikID;
    private String ime;
    private String prezime;
    private String telefon;
    private String adresa;
    private Zaposleni zaposleni;
    private Mesto mesto;
    private List<KoriscenjeUsluge> lku;

    public Korisnik() {
        lku = new LinkedList<>();
    }

    public Korisnik(int korisnikID, String ime, String prezime, String telefon, String adresa, Zaposleni zaposleni, Mesto mesto, List<KoriscenjeUsluge> lku) {
        this.korisnikID = korisnikID;
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.adresa = adresa;
        this.zaposleni = zaposleni;
        this.mesto = mesto;
        this.lku = lku;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    public int getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(int korisnikID) {
        this.korisnikID = korisnikID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }
    
    public List<KoriscenjeUsluge> getLku() {
        return lku;
    }

    public void setLku(List<KoriscenjeUsluge> lku) {
        this.lku = lku;
    }

    @Override
    public String toString() {
        return ime+" "+prezime;
    }       

    @Override
    public String vratiNazivTabele() {
        return "korisnik";
    }

    @Override
    public String vratiAtributeZaInsert() {
        return "KorisnikID, Ime, Prezime, Telefon, Adresa, ZaposleniID, PTTBroj";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return korisnikID+", '"+ime+"', '"+prezime+"', '"+telefon+"', '"+adresa+"', "+zaposleni.getZaposleniID()+", "+mesto.getPttBroj();
    }

    @Override
    public List<OpstiDomenskiObjekat> ucitaj(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista = new LinkedList<>();
        while(rs.next()){
                int korisnikID = rs.getInt("KorisnikID");
                String ime = rs.getString("k.Ime");
                String prezime = rs.getString("k.Prezime");
                String telefon = rs.getString("Telefon");
                String adresa = rs.getString("Adresa");
                
                int zaposleniID = rs.getInt("ZaposleniID");
                String zIme = rs.getString("z.Ime");
                String zPrezime = rs.getString("z.Prezime");
                String korIme = rs.getString("KorisnickoIme");
                String lozinka = rs.getString("Lozinka");
                Zaposleni z = new Zaposleni(zaposleniID, zIme, zPrezime, korIme, lozinka);
                
                int pttBroj = rs.getInt("PTTBroj");
                String naziv = rs.getString("Naziv");
                Mesto m = new Mesto(pttBroj, naziv);
                
                Korisnik k = new Korisnik(korisnikID, ime, prezime, telefon, adresa, z, m, null);
                lista.add(k);
            }           
            return lista;
    }

    @Override
    public String vratiJoinUpit() {
        return "k JOIN mesto m ON k.PTTBroj = m.PTTBroj JOIN zaposleni z ON k.ZaposleniID = z.ZaposleniID";
    }

    @Override
    public String vratiWhereUpit() {
        return "";
    }

    @Override
    public OpstiDomenskiObjekat ucitajJedan(ResultSet rs) throws Exception {
        Korisnik k = null;
        while(rs.next()){
                int korisnikID = rs.getInt("KorisnikID");
                String ime = rs.getString("k.Ime");
                String prezime = rs.getString("k.Prezime");
                String telefon = rs.getString("Telefon");
                String adresa = rs.getString("Adresa");
                
                int zaposleniID = rs.getInt("ZaposleniID");
                String zIme = rs.getString("z.Ime");
                String zPrezime = rs.getString("z.Prezime");
                String korIme = rs.getString("KorisnickoIme");
                String lozinka = rs.getString("Lozinka");
                Zaposleni z = new Zaposleni(zaposleniID, zIme, zPrezime, korIme, lozinka);
                
                int pttBroj = rs.getInt("PTTBroj");
                String naziv = rs.getString("Naziv");
                Mesto m = new Mesto(pttBroj, naziv);
                
                k = new Korisnik(korisnikID, ime, prezime, telefon, adresa, z, m, null);
            }           
            return k;
    }

    @Override
    public String vratiID() {
        return "KorisnikID";
    }

    @Override
    public String vratiWhereIzbrisi() {
        return "KorisnikID = "+korisnikID;
    }

    @Override
    public String vratiSetZaIzmenu() {
        return "Ime = '"+ime+"', Prezime = '"+prezime+"', Telefon = '"+telefon+"', Adresa = '"+adresa+"', ZaposleniID = "+zaposleni.getZaposleniID()+", PTTBroj = "+mesto.getPttBroj();
    }

    @Override
    public String vratiWhereJedan() {
        return "WHERE KorisnikID = "+korisnikID;
    }
    
}
