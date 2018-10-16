/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Milos
 */
public interface OpstiDomenskiObjekat extends Serializable{
    String vratiNazivTabele();
    String vratiAtributeZaInsert();
    String vratiVrednostiZaInsert();    
    String vratiJoinUpit();    
    String vratiWhereUpit();    
    String vratiID();
    String vratiWhereIzbrisi();
    String vratiSetZaIzmenu();
    String vratiWhereJedan();
    List<OpstiDomenskiObjekat> ucitaj(ResultSet rs) throws Exception;    
    OpstiDomenskiObjekat ucitajJedan(ResultSet rs) throws Exception;
}
