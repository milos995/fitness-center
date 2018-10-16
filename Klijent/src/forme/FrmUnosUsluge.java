/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import domen.Usluga;
import domen.Zaposleni;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import konstante.Operacije;
import konstante.Status;
import modeli.TableModelUsluge;
import transfer.ObjekatOdgovor;
import transfer.ObjekatZahtev;

/**
 *
 * @author Milos
 */
public class FrmUnosUsluge extends javax.swing.JPanel {
    private Zaposleni ulogovaniZaposleni;
    private Usluga usluga;
    /**
     * Creates new form FrmUnosUsluge
     */
    public FrmUnosUsluge() {
        initComponents();
        try {
            jButtonIzmeni.setVisible(false);
        } catch (Exception e) {
        }        
        kreirajUslugu();
        srediTabelu();
    }
    
    public FrmUnosUsluge(Usluga u) {
        initComponents();
        try {
            jButtonSacuvaj.setVisible(false);
        } catch (Exception e) {
        }        
        jTextFieldNazivUsluge.setText(u.getNazivUsluge());
        usluga = u;
        srediTabelu();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelUlogovaniZaposleni = new javax.swing.JLabel();
        jLabelNazivUsluge = new javax.swing.JLabel();
        jTextFieldNazivUsluge = new javax.swing.JTextField();
        jButtonOdustani = new javax.swing.JButton();
        jButtonSacuvaj = new javax.swing.JButton();
        jButtonIzmeni = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableUsluge = new javax.swing.JTable();

        jLabelUlogovaniZaposleni.setText("Ulogovani zaposleni: ");

        jLabelNazivUsluge.setText("Naziv usluge: ");

        jButtonOdustani.setText("Odustani");
        jButtonOdustani.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOdustaniActionPerformed(evt);
            }
        });

        jButtonSacuvaj.setText("Sačuvaj");
        jButtonSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSacuvajActionPerformed(evt);
            }
        });

        jButtonIzmeni.setText("Izmeni");
        jButtonIzmeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIzmeniActionPerformed(evt);
            }
        });

        jTableUsluge.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableUsluge);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabelUlogovaniZaposleni, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabelNazivUsluge, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jTextFieldNazivUsluge))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonIzmeni, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 387, Short.MAX_VALUE)
                        .addComponent(jButtonSacuvaj, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonOdustani)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelUlogovaniZaposleni)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNazivUsluge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNazivUsluge))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSacuvaj)
                    .addComponent(jButtonOdustani)
                    .addComponent(jButtonIzmeni))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOdustaniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOdustaniActionPerformed
        this.getTopLevelAncestor().setVisible(false);
    }//GEN-LAST:event_jButtonOdustaniActionPerformed

    private void jButtonSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSacuvajActionPerformed
        String naziv = jTextFieldNazivUsluge.getText();

        if (naziv.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Morate uneti naziv usluge!");
            return;
        }

        usluga.setNazivUsluge(naziv);
        usluga.setZaposleni(ulogovaniZaposleni);
        
        ObjekatZahtev zahtev = new ObjekatZahtev(Operacije.ZAPAMTI_USLUGU, usluga);
        Komunikacija.getInstance().posaljiZahtev(zahtev);
        ObjekatOdgovor odgovor = Komunikacija.getInstance().primiOdgovor();
        
        if (odgovor.getStatus() == Status.ERROR) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da zamapti uslugu!");
            System.out.println(odgovor.getPoruka());
            return;
        }
        JOptionPane.showMessageDialog(this, "Sistem je zapamtio uslugu!");
        srediTabelu();
    }//GEN-LAST:event_jButtonSacuvajActionPerformed

    private void jButtonIzmeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIzmeniActionPerformed
        String naziv = jTextFieldNazivUsluge.getText();
        if (naziv.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Morate uneti naziv usluge!");
            return;
        }

        usluga.setNazivUsluge(naziv);
        usluga.setZaposleni(ulogovaniZaposleni);
        
        ObjekatZahtev zahtev = new ObjekatZahtev(Operacije.IZMENI_USLUGU, usluga);
        Komunikacija.getInstance().posaljiZahtev(zahtev);
        ObjekatOdgovor odgovor = Komunikacija.getInstance().primiOdgovor();
        
        if (odgovor.getStatus() == Status.ERROR) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da izmeni uslugu");
            System.out.println(odgovor.getPoruka());
            return;
        }
        JOptionPane.showMessageDialog(this, "Sistem je izmenio uslugu");
        srediTabelu();
    }//GEN-LAST:event_jButtonIzmeniActionPerformed

    public Zaposleni getUlogovaniZaposleni() {
        return ulogovaniZaposleni;
    }

    public void setUlogovaniZaposleni(Zaposleni ulogovaniZaposleni) {
        this.ulogovaniZaposleni = ulogovaniZaposleni;
        jLabelUlogovaniZaposleni.setText("Ulogovani zaposleni: "+ulogovaniZaposleni);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonIzmeni;
    private javax.swing.JButton jButtonOdustani;
    private javax.swing.JButton jButtonSacuvaj;
    private javax.swing.JLabel jLabelNazivUsluge;
    private javax.swing.JLabel jLabelUlogovaniZaposleni;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableUsluge;
    private javax.swing.JTextField jTextFieldNazivUsluge;
    // End of variables declaration//GEN-END:variables

    private void kreirajUslugu() {
        usluga = new Usluga();
    }

    private void srediTabelu() {
        ObjekatZahtev zahtev = new ObjekatZahtev(Operacije.VRATI_LISTU_USLUGA, "");
        Komunikacija.getInstance().posaljiZahtev(zahtev);
        ObjekatOdgovor odgovor = Komunikacija.getInstance().primiOdgovor();
        if(odgovor.getStatus() == Status.ERROR){
            JOptionPane.showMessageDialog(this, "Sistem ne može da nađe usluge po zadatoj vrednosti");
            System.out.println(odgovor.getPoruka());
            return;
        }        
        List<Usluga> lu = (List<Usluga>) odgovor.getOdgovor();
               
        TableModelUsluge tmu = new TableModelUsluge(lu);
        jTableUsluge.setModel(tmu);
    }
}
