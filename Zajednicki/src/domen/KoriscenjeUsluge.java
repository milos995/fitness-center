/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Milos
 */
public class KoriscenjeUsluge implements OpstiDomenskiObjekat{
    private int koriscenjeID;
    private int korisnikID;
    private Date datumOd;
    private Date datumDo;
    private Usluga usluga;
    private String operacija;

    public KoriscenjeUsluge() {
    }

    public KoriscenjeUsluge(int koriscenjeID, int korisnikID, Date datumOd, Date datumDo, Usluga usluga, String operacija) {
        this.koriscenjeID = koriscenjeID;
        this.korisnikID = korisnikID;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.usluga = usluga;
        this.operacija = operacija;
    }  

    public int getKoriscenjeID() {
        return koriscenjeID;
    }

    public void setKoriscenjeID(int koriscenjeID) {
        this.koriscenjeID = koriscenjeID;
    }

    public int getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(int korisnikID) {
        this.korisnikID = korisnikID;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public Usluga getUsluga() {
        return usluga;
    }

    public void setUsluga(Usluga usluga) {
        this.usluga = usluga;
    } 

    public String getOperacija() {
        return operacija;
    }

    public void setOperacija(String operacija) {
        this.operacija = operacija;
    }    

    @Override
    public String vratiNazivTabele() {
        return "koriscenjeusluge";
    }

    @Override
    public String vratiAtributeZaInsert() {
        return "KoriscenjeID, KorisnikID, DatumOd, DatumDo, UslugaID";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        Date datOd = new java.sql.Date(datumOd.getTime());
        Date datDo = new java.sql.Date(datumDo.getTime());
        return koriscenjeID+", "+korisnikID+", '"+datOd+"', '"+datDo+"', "+usluga.getUslugaID();
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
            String naziv = rs.getString("NazivUsluge");
            Usluga u = new Usluga(uslugaID, naziv, z);
            
            int koriscenjeUslugeID = rs.getInt("KoriscenjeID");
            int korisnikID = rs.getInt("KorisnikID");
            java.sql.Date datumOd = rs.getDate("DatumOd");
            java.sql.Date datumDo = rs.getDate("DatumDo");
            KoriscenjeUsluge ku = new KoriscenjeUsluge(koriscenjeUslugeID, korisnikID, datumOd, datumDo, u, "");
            lista.add(ku);
        }
        return lista;
    }

    @Override
    public String vratiJoinUpit() {
        return "ku JOIN usluga u ON ku.UslugaID = u.UslugaID JOIN zaposleni z ON u.ZaposleniID = z.ZaposleniID";
    }

    @Override
    public String vratiWhereUpit() {
        return "WHERE KorisnikID = "+korisnikID;
    }

    @Override
    public OpstiDomenskiObjekat ucitajJedan(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiID() {
        return "KoriscenjeID";
    }

    @Override
    public String vratiWhereIzbrisi() {
        return "KorisnikID = "+korisnikID+" and KoriscenjeID = "+koriscenjeID;
    }

    @Override
    public String vratiSetZaIzmenu() {
        Date datOd = new java.sql.Date(datumOd.getTime());
        Date datDo = new java.sql.Date(datumDo.getTime());
        return "DatumOd = '"+datOd+"', DatumDo = '"+datDo+"', UslugaID = "+usluga.getUslugaID();
    }

    @Override
    public String vratiWhereJedan() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
