/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import niti.NitKlijent;

/**
 *
 * @author Milos
 */
public class ModelTabeleKlijenti extends AbstractTableModel{
    List<NitKlijent> lk;
    String[] kolone = {"RB", "Klijent", "Vreme"};
    DateTimeFormatter dtf;

    public ModelTabeleKlijenti(List<NitKlijent> lk) {
        this.lk = lk;
        dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
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
        NitKlijent nk = lk.get(rowIndex);
        switch(columnIndex){
            case 0:
                return nk.getRb();
            case 1:
                return "Klijent "+nk.getRb()+" ("+nk.getSoket().getInetAddress().getHostName()+")";
            case 2:
                return nk.getVreme().format(dtf);
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }  

    public void setLk(List<NitKlijent> lk) {
        this.lk = lk;
        fireTableDataChanged();
    }    
    
    public void srediRB(){
        int brojac = 0;
        for (NitKlijent nk : lk) {
            brojac++;
            nk.setRb(brojac);
        }
        fireTableDataChanged();
    }
}
