/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domen.Korisnik;
import domen.Mesto;
import domen.Usluga;
import domen.Zaposleni;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalTime;
import java.util.List;
import konstante.Operacije;
import konstante.Status;
import logika.Kontroler;
import transfer.ObjekatOdgovor;
import transfer.ObjekatZahtev;

/**
 *
 * @author Milos
 */
public class NitKlijent extends Thread{
    int rb;
    Socket soket;
    LocalTime vreme;

    public NitKlijent(int rb, Socket soket, LocalTime vreme) {
        this.rb = rb;
        this.soket = soket;
        this.vreme = vreme;
    }    

    @Override
    public void run() {
        try {
            izvrsenjeOperacija();
        } catch (IOException | ClassNotFoundException ex) {
            this.interrupt();
            Kontroler.getInstance().ukloniKlijenta(this);
        }
        System.out.println("Klijent je otisao");
    }   

    private void izvrsenjeOperacija() throws IOException, ClassNotFoundException {
        while(!isInterrupted()){
            ObjekatZahtev zahtev = primiZahtev();
            ObjekatOdgovor odgovor = new ObjekatOdgovor();
            switch(zahtev.getOperacija()){
                case Operacije.ULOGUJ_ZAPOSLENOG:
                    try {
                        Zaposleni z = (Zaposleni) zahtev.getParametar();
                        Zaposleni zap = Kontroler.getInstance().ulogujZaposlenog(z);
                        odgovor.setOdgovor(zap);
                        odgovor.setStatus(Status.OK);
                    } catch (Exception ex) {
                        odgovor.setStatus(Status.ERROR);
                        odgovor.setPoruka(ex.getMessage());
                    }                    
                    break;
                case Operacije.ZAPAMTI_USLUGU:
                    try {
                        Usluga uZap = (Usluga) zahtev.getParametar();
                        Kontroler.getInstance().zapamtiUslugu(uZap);
                        odgovor.setStatus(Status.OK);
                    } catch (Exception ex) {
                        odgovor.setStatus(Status.ERROR);
                        odgovor.setPoruka(ex.getMessage());
                    }                    
                    break;
                case Operacije.IZMENI_USLUGU:
                    try {
                        Usluga uIzmeni = (Usluga) zahtev.getParametar();
                        Kontroler.getInstance().izmeniUslugu(uIzmeni);
                        odgovor.setStatus(Status.OK);
                    } catch (Exception ex) {
                        odgovor.setStatus(Status.ERROR);
                        odgovor.setPoruka(ex.getMessage());
                    }                      
                    break;
                case Operacije.PRETRAZI_USLUGE:
                    try {
                        String filterUsluge = (String) zahtev.getParametar();
                        List<Usluga> listaUsluga = Kontroler.getInstance().pretraziUsluge(filterUsluge);
                        odgovor.setOdgovor(listaUsluga);
                        odgovor.setStatus(Status.OK);
                    } catch (Exception ex) {
                        odgovor.setStatus(Status.ERROR);
                        odgovor.setPoruka(ex.getMessage());
                    }                     
                    break;
                case Operacije.IZBRISI_USLUGU:
                    try {
                        Usluga uObrisi = (Usluga) zahtev.getParametar();
                        Kontroler.getInstance().izbrisiUslugu(uObrisi);
                        odgovor.setStatus(Status.OK);
                    } catch (Exception ex) {
                        odgovor.setStatus(Status.ERROR);
                        odgovor.setPoruka(ex.getMessage());
                    }                   
                    break;
                case Operacije.PRETRAZI_KORISNIKE:
                    try {
                        String filterKor = (String) zahtev.getParametar();
                        List<Korisnik> listaKori = Kontroler.getInstance().pretraziKorisnike(filterKor);
                        odgovor.setOdgovor(listaKori);
                        odgovor.setStatus(Status.OK);
                    } catch (Exception ex) {
                        odgovor.setStatus(Status.ERROR);
                        odgovor.setPoruka(ex.getMessage());
                    }                       
                    break;
                case Operacije.IZBRISI_KORISNIKA:
                    try {
                        Korisnik obrisiKori = (Korisnik) zahtev.getParametar();
                        Kontroler.getInstance().izbrisiKorisnika(obrisiKori);
                        odgovor.setStatus(Status.OK);
                    } catch (Exception ex) {
                        odgovor.setStatus(Status.ERROR);
                        odgovor.setPoruka(ex.getMessage());
                    }                       
                    break;
                case Operacije.VRATI_LISTU_MESTA:   
                    try {
                        List<Mesto> listaMesta = Kontroler.getInstance().vratiListuMesta();
                        odgovor.setOdgovor(listaMesta);
                        odgovor.setStatus(Status.OK);
                    } catch (Exception ex) {
                        odgovor.setStatus(Status.ERROR);
                        odgovor.setPoruka(ex.getMessage());
                    }                       
                    break;
                case Operacije.ZAPAMTI_KORISNIKA:
                    try {
                        Korisnik korisnik = (Korisnik) zahtev.getParametar();
                        Kontroler.getInstance().zapamtiKorisnika(korisnik);
                        odgovor.setStatus(Status.OK);
                    } catch (Exception ex) {
                        odgovor.setStatus(Status.ERROR);
                        odgovor.setPoruka(ex.getMessage());
                    } 
                    break;
                case Operacije.IZMENI_KORISNIKA:
                    try {
                        Korisnik korisnikIzmena = (Korisnik) zahtev.getParametar();
                        Kontroler.getInstance().izmeniKorisnika(korisnikIzmena);
                        odgovor.setStatus(Status.OK);
                    } catch (Exception ex) {
                        odgovor.setStatus(Status.ERROR);
                        odgovor.setPoruka(ex.getMessage());
                    }                     
                    break;
                case Operacije.VRATI_LISTU_USLUGA:
                    try {
                        List<Usluga> listaUsluga = Kontroler.getInstance().vratiListuUsluga();
                        odgovor.setOdgovor(listaUsluga);
                        odgovor.setStatus(Status.OK);
                    } catch (Exception ex) {
                        odgovor.setStatus(Status.ERROR);
                        odgovor.setPoruka(ex.getMessage());
                    } 
                    break;
                case Operacije.PRIKAZI_KORISNIKA:
                    try {
                        int korisnikID = (int) zahtev.getParametar();
                        Korisnik prikaziKorisnika = Kontroler.getInstance().prikaziKorisnika(korisnikID);
                        odgovor.setOdgovor(prikaziKorisnika);
                        odgovor.setStatus(Status.OK);
                    } catch (Exception ex) {
                        odgovor.setStatus(Status.ERROR);
                        odgovor.setPoruka(ex.getMessage());
                    } 
                    break;
                case Operacije.PRIKAZI_USLUGU:
                    try {
                        int uslugaID = (int) zahtev.getParametar();
                        Usluga prikaziUslugu = Kontroler.getInstance().prikaziUslugu(uslugaID);
                        odgovor.setOdgovor(prikaziUslugu);
                        odgovor.setStatus(Status.OK);
                    } catch (Exception ex) {
                        odgovor.setStatus(Status.ERROR);
                        odgovor.setPoruka(ex.getMessage());
                    } 
                    break;
            }
            posaljiOdgovor(odgovor);
        }
    }
    
    private ObjekatZahtev primiZahtev() throws IOException, ClassNotFoundException {
        ObjekatZahtev zahtev = new ObjekatZahtev();
        ObjectInputStream ois = new ObjectInputStream(soket.getInputStream());
        zahtev = (ObjekatZahtev) ois.readObject();
        return zahtev;
    }

    private void posaljiOdgovor(ObjekatOdgovor odgovor) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(soket.getOutputStream());
        oos.writeObject(odgovor);
    }

    public Socket getSoket() {
        return soket;
    }

    public LocalTime getVreme() {
        return vreme;
    }   

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }    
}
