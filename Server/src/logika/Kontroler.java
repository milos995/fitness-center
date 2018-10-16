/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import db.DBBroker;
import domen.Korisnik;
import domen.Mesto;
import domen.Usluga;
import domen.Zaposleni;
import java.util.LinkedList;
import java.util.List;
import niti.NitKlijent;
import so.IzbrisiKorisnikaSO;
import so.IzbrisiUsluguSO;
import so.IzmeniKorisnikaSO;
import so.IzmeniUsluguSO;
import so.UlogujZaposlenogSO;
import so.PretraziKorisnikeSO;
import so.VratiListuMestaSO;
import so.PretraziUslugeSO;
import so.PrikaziKorisnikaSO;
import so.PrikaziUsluguSO;
import so.VratiListuUslugaSO;
import so.ZapamtiKorisnikaSO;
import so.ZapamtiUsluguSO;

/**
 *
 * @author Milos
 */
public class Kontroler {
    private static Kontroler instance;
    private DBBroker db;
    private List<NitKlijent> lk;
    
    private Kontroler(){
        lk = new LinkedList<>();
        db = new DBBroker();
        try {
            db.ucitajDrajver();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    public static Kontroler getInstance(){
        if(instance==null){
            instance = new Kontroler();
        }
        return instance;
    }

    public Zaposleni ulogujZaposlenog(Zaposleni zap) throws Exception {
        UlogujZaposlenogSO uzso = new UlogujZaposlenogSO(db);
        uzso.izvrsenjeSO(zap);
        return uzso.getZaposleni();
    }
    
    public void zapamtiUslugu(Usluga u) throws Exception {
        ZapamtiUsluguSO zuso = new ZapamtiUsluguSO(db);
        zuso.izvrsenjeSO(u);
    }
    
    public List<Usluga> pretraziUsluge(String kriterijumPretrage) throws Exception {        
        PretraziUslugeSO puso = new PretraziUslugeSO(db);
        puso.izvrsenjeSO(kriterijumPretrage);
        return puso.getLu();
    }
    
    public void izbrisiUslugu(Usluga u) throws Exception {
        IzbrisiUsluguSO iuso = new IzbrisiUsluguSO(db);
        iuso.izvrsenjeSO(u);
    }
    
    public void izmeniUslugu(Usluga u) throws Exception {
        IzmeniUsluguSO iuso = new IzmeniUsluguSO(db);
        iuso.izvrsenjeSO(u);
    }
    
    public List<Mesto> vratiListuMesta() throws Exception {
        VratiListuMestaSO vlmso = new VratiListuMestaSO(db);
        vlmso.izvrsenjeSO(null);
        return vlmso.getLm();
    }

    public void zapamtiKorisnika(Korisnik k) throws Exception {
        ZapamtiKorisnikaSO zkso = new ZapamtiKorisnikaSO(db);
        zkso.izvrsenjeSO(k);
    }
    
    public List<Korisnik> pretraziKorisnike(String kriterijumPretrage) throws Exception {
        PretraziKorisnikeSO pkso = new PretraziKorisnikeSO(db);
        pkso.izvrsenjeSO(kriterijumPretrage);
        return pkso.getLk();
    }
    
    public void izbrisiKorisnika(Korisnik k) throws Exception {
        IzbrisiKorisnikaSO ikso = new IzbrisiKorisnikaSO(db);
        ikso.izvrsenjeSO(k);
    }

    public void izmeniKorisnika(Korisnik k) throws Exception {
        IzmeniKorisnikaSO ikso = new IzmeniKorisnikaSO(db);
        ikso.izvrsenjeSO(k);
    }
    
    public List<Usluga> vratiListuUsluga() throws Exception {
        VratiListuUslugaSO vluso = new VratiListuUslugaSO(db);
        vluso.izvrsenjeSO(null);
        return vluso.getLu();
    }
    
    public Korisnik prikaziKorisnika(int korisnikID) throws Exception {
        PrikaziKorisnikaSO pkso = new PrikaziKorisnikaSO(db);
        pkso.izvrsenjeSO(korisnikID);
        return pkso.getK();
    }
    
    public Usluga prikaziUslugu(int uslugaID) throws Exception {
        PrikaziUsluguSO puso = new PrikaziUsluguSO(db);
        puso.izvrsenjeSO(uslugaID);
        return puso.getU();
    }
    
    public List<NitKlijent> getLk() {
        return lk;
    }
    
    public void ubaciKlijenta(NitKlijent nk){
        lk.add(nk);
    }
    
    public void ukloniKlijenta(NitKlijent nk){
        lk.remove(nk);
    }   
}
