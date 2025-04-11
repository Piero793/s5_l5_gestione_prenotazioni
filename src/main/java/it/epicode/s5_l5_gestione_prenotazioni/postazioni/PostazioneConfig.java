package it.epicode.s5_l5_gestione_prenotazioni.postazioni;

import it.epicode.s5_l5_gestione_prenotazioni.edifici.Edificio;
import it.epicode.s5_l5_gestione_prenotazioni.utenti.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class PostazioneConfig {
    @Autowired
    private Edificio edificio1;
    @Autowired
    private Edificio edificio2;
    @Autowired
    private Edificio edificio3;
    @Autowired
    private Utente utente1;
    @Autowired
    private Utente utente2;
    @Autowired
    private Utente utente3;

    @Bean
    public Postazione postazione1() {
        Postazione postazione = new Postazione();
        postazione.setDescrizione("Postazione open space");
        postazione.setTipo(TipoPostazione.OPENSPACE);
        postazione.setNumeroMassimoOccupanti(10);
        postazione.setEdificio(edificio2);
        postazione.setUtente(utente1);
        postazione.setDataPrenotazione(LocalDate.now().plusDays(1));
        return postazione;
    }

    @Bean
    public Postazione postazione2() {
        Postazione postazione = new Postazione();
        postazione.setDescrizione("Postazione privata per 1 persona");
        postazione.setTipo(TipoPostazione.PRIVATO);
        postazione.setNumeroMassimoOccupanti(1);
        postazione.setEdificio(edificio1);
        postazione.setUtente(null);
        postazione.setDataPrenotazione(LocalDate.now().plusDays(2));
        return postazione;
    }

    @Bean
    public Postazione postazione3() {
        Postazione postazione = new Postazione();
        postazione.setDescrizione("sala di riunione per 20 persone");
        postazione.setTipo(TipoPostazione.SALA_RIUNIONI);
        postazione.setNumeroMassimoOccupanti(20);
        postazione.setEdificio(edificio3);
        postazione.setUtente(utente3);
        postazione.setDataPrenotazione(LocalDate.now().plusDays(3));
        return postazione;
    }
}