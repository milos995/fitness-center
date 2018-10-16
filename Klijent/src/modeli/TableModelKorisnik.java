/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.KoriscenjeUsluge;
import domen.Korisnik;
import domen.Usluga;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Milos
 */
public class TableModelKorisnik extends AbstractTableModel{
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    private Korisnik k;
    private boolean detalji;
    String[] kolone = {"Usluga", "Datum od", "Datum do"};
    List<KoriscenjeUsluge> lkuObrisanih = new LinkedList<>();

    public TableModelKorisnik(Korisnik k, boolean detalji) {
        this.k = k;
        this.detalji = detalji;
    }   
    
    @Override
    public int getRowCount() {
        return k.getLku().size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        KoriscenjeUsluge ku = k.getLku().get(rowIndex);
        switch(columnIndex){
            case 0: return ku.getUsluga();
            case 1: if(ku.getDatumOd()!=null){
                        return sdf.format(ku.getDatumOd());
                    }else{
                        return ku.getDatumOd();
                    }
            case 2: if(ku.getDatumDo()!=null){
                        return sdf.format(ku.getDatumDo());
                    }else{
                        return ku.getDatumDo();
                    }
            default: return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    public void dodajKoriscenjeUsluge(){
        KoriscenjeUsluge ku = new KoriscenjeUsluge();
        ku.setOperacija("");
        k.getLku().add(ku);
        fireTableDataChanged();
    }
    
    public void ukloniKoriscenjeUsluge(int red){
        KoriscenjeUsluge ku = k.getLku().remove(red);
        ku.setOperacija("brisanje");
        lkuObrisanih.add(ku);
        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(detalji==true){
            return false;
        }
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        KoriscenjeUsluge ku = k.getLku().get(rowIndex);
        switch(columnIndex){
            case 0: ku.setUsluga((Usluga) aValue);
                    fireTableCellUpdated(rowIndex, 1);
                    break;
            case 1: {
                        try {
                            ku.setDatumOd(sdf.parse(aValue.toString()));
                        } catch (ParseException ex) {
                            JOptionPane.showMessageDialog(null, "Datum mora biti u formatu dd.MM.yyyy!");
                        }
                    }
                    fireTableCellUpdated(rowIndex, 2);
                    break;
            case 2: {
                        try {
                            ku.setDatumDo(sdf.parse(aValue.toString()));
                        } catch (ParseException ex) {
                            JOptionPane.showMessageDialog(null, "Datum mora biti u formatu dd.MM.yyyy!");
                        }
                    }
                    fireTableCellUpdated(rowIndex, 3);
                    break;
        }
    }   

    public List<KoriscenjeUsluge> vratiSvaKoriscenja() {
        List<KoriscenjeUsluge> lista = k.getLku();
        lista.addAll(lkuObrisanih);
        return lista;
    }
}
