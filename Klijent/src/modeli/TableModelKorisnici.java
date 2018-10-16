/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Korisnik;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Milos
 */
public class TableModelKorisnici extends AbstractTableModel{
    String[] kolone = {"Ime", "Prezime", "Adresa", "Mesto"};
    List<Korisnik> lk;

    public TableModelKorisnici(List<Korisnik> lk) {
        this.lk = lk;
    }        

    @Override
    public int getRowCount() {
        return lk.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Korisnik k = lk.get(rowIndex);
        switch(columnIndex){
            case 0: return k.getIme();
            case 1: return k.getPrezime();
            case 2: return k.getAdresa();
            case 3: return k.getMesto();
            default: return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    public Korisnik vratiSelektovanogKorisnika(int red){
        return lk.get(red);
    }    
}
