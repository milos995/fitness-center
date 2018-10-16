/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Usluga;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Milos
 */
public class TableModelUsluge extends AbstractTableModel{
    String[] kolone = {"ID usluge", "Naziv usluge", "Zaposleni"};
    List<Usluga> lu;

    public TableModelUsluge(List<Usluga> lu) {
        this.lu = lu;
    }    
    
    @Override
    public int getRowCount() {
        return lu.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usluga u = lu.get(rowIndex);
        switch(columnIndex){
            case 0: return u.getUslugaID();
            case 1: return u.getNazivUsluge();
            case 2: return u.getZaposleni();
            default: return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    public Usluga vratiSelektovanuUslugu(int red){
        return lu.get(red);
    }
    
}
