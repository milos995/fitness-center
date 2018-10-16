/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.OpstiDomenskiObjekat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import util.Util;

/**
 *
 * @author Milos
 */
public class DBBroker {
    private Connection konekcija;
    
    public void ucitajDrajver() throws Exception{
        try {
            Class.forName(Util.getInstance().getValue("driver"));
        } catch (Exception ex) {
            throw new Exception("Greska prilikom ucitavanja drajvera!"+ex);
        }
    }
    
    public void uspostaviKonekciju() throws Exception{
        try {
            String url = Util.getInstance().getValue("url");
            String user = Util.getInstance().getValue("user");
            String pass = Util.getInstance().getValue("pass");
            konekcija = DriverManager.getConnection(url, user, pass);
            konekcija.setAutoCommit(false);
        } catch (Exception ex) {
            throw new Exception("Greska prilikom uspostavljanja konekcije!"+ex);
        }
    }
    
    public void raskiniKonekciju() throws Exception{
        try {
            konekcija.close();
        } catch (Exception ex) {
            throw new Exception("Greska prilikom raskidanja konekcije!"+ex);
        }
    }
    
    public void commitTransakcije() throws Exception{
        try {
            konekcija.commit();
        } catch (Exception ex) {
            throw new Exception("Greska prilikom commita transakcije!"+ex);
        }
    }
    
    public void rollbackTransakcije() throws Exception{
        try {
            konekcija.commit();
        } catch (Exception ex) {
            throw new Exception("Greska prilikom rollbacka transakcije!"+ex);
        }
    }
    
    public List<OpstiDomenskiObjekat> vratiSve(OpstiDomenskiObjekat odo, String filter) throws Exception{
        try {
            String sql = "SELECT * FROM "+odo.vratiNazivTabele()+" "+odo.vratiJoinUpit()+" "+odo.vratiWhereUpit()+" "+filter;
            Statement sqlStatement = konekcija.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);
            return odo.ucitaj(rs);
        } catch (Exception ex) {
            throw new Exception("Neuspesno ucitavanje objekata! ", ex);
        }
    }
    
    public OpstiDomenskiObjekat vrati(OpstiDomenskiObjekat odo) throws Exception{
        try {
            String sql = "SELECT * FROM "+odo.vratiNazivTabele()+" "+odo.vratiJoinUpit()+" "+odo.vratiWhereJedan();
            Statement sqlStatement = konekcija.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);
            return odo.ucitajJedan(rs);
        } catch (Exception ex) {
            throw new Exception("Neuspesno ucitavanje objekta! ", ex);
        }
    }
    
    public int vratiMaxID(OpstiDomenskiObjekat odo) throws Exception{
        try {
            int max =0;
            String upit = "SELECT MAX("+odo.vratiID()+") as max FROM "+odo.vratiNazivTabele();
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            if (rs.next()) {
                max = rs.getInt("max");
            }            
            return ++max;
        } catch (Exception ex) {
            throw new Exception("Greska prilikom vracanja ID-ja objekta! "+ex);
        }
    }
    
    public void zapamti(OpstiDomenskiObjekat odo) throws Exception{
        try {
            String sql = "INSERT INTO "+odo.vratiNazivTabele()+" ("+odo.vratiAtributeZaInsert()+") VALUES("+odo.vratiVrednostiZaInsert()+")";
            Statement sqlStatement = konekcija.createStatement();
            sqlStatement.executeUpdate(sql);
        } catch (Exception ex) {
            throw new Exception("Neuspesno cuvanje objekta! ", ex);
        }
    }
    
    public void izbrisi(OpstiDomenskiObjekat odo) throws Exception{
        try {
            String sql = "DELETE FROM "+odo.vratiNazivTabele()+" WHERE "+odo.vratiWhereIzbrisi();
            Statement sqlStatement = konekcija.createStatement();
            sqlStatement.executeUpdate(sql);
        } catch (Exception ex) {
            throw new Exception("Neuspesno brisanje objekta! ", ex);
        }
    }
    
    public void izmeni (OpstiDomenskiObjekat odo) throws Exception{
        try {
            String sql = "UPDATE "+odo.vratiNazivTabele()+" SET "+odo.vratiSetZaIzmenu()+" WHERE "+odo.vratiWhereIzbrisi();
            Statement sqlStatement = konekcija.createStatement();
            sqlStatement.executeUpdate(sql);
        } catch (Exception ex) {
            throw new Exception("Neuspesna izmena objekta! ", ex);
        }
    }
}
