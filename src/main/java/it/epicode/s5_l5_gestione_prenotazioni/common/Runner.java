package it.epicode.s5_l5_gestione_prenotazioni.common;

import it.epicode.s5_l5_gestione_prenotazioni.edifici.Edificio;
import it.epicode.s5_l5_gestione_prenotazioni.edifici.EdificioRepository;
import it.epicode.s5_l5_gestione_prenotazioni.postazioni.Postazione;
import it.epicode.s5_l5_gestione_prenotazioni.postazioni.PostazioneRepository;
import it.epicode.s5_l5_gestione_prenotazioni.postazioni.PostazioneService;
import it.epicode.s5_l5_gestione_prenotazioni.postazioni.TipoPostazione;
import it.epicode.s5_l5_gestione_prenotazioni.utenti.Utente;
import it.epicode.s5_l5_gestione_prenotazioni.utenti.UtenteRepository;
import it.epicode.s5_l5_gestione_prenotazioni.utenti.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private PostazioneService postazioneService;
    @Autowired
    private EdificioRepository edificioRepository;
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private PostazioneRepository postazioneRepository;
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
    @Autowired
    private Postazione postazione1;
    @Autowired
    private Postazione postazione2;
    @Autowired
    private Postazione postazione3;

    @Override
    public void run(String... args) throws Exception {
        // Salva gli edifici
        edificioRepository.save(edificio1);
        edificioRepository.save(edificio2);
        edificioRepository.save(edificio3);

        // Salva gli utenti
        utenteRepository.save(utente1);
        utenteRepository.save(utente2);
        utenteRepository.save(utente3);

        // Salva le postazioni
        postazioneRepository.save(postazione1);
        postazioneRepository.save(postazione2);
        postazioneRepository.save(postazione3);

        // TEST METODO PRENOTAZIONE
        LocalDate dataPrenotazione = LocalDate.now().plusDays(1); // Prenota per il giorno successivo
        System.out.println("\nTentativo di prenotazione della postazione 1 per " + dataPrenotazione);
        String risultatoPrenotazione = postazioneService.prenotaPostazione(postazione1, utente1, dataPrenotazione);
        System.out.println("Risultato prenotazione: " + risultatoPrenotazione);

        // TEST METODO RICERCA POSTAZIONI
        TipoPostazione tipoRicerca = TipoPostazione.PRIVATO;
        String cittaRicerca = "Roma";
        System.out.println("\nRicerca postazioni di tipo " + tipoRicerca + " in città " + cittaRicerca);
        List<Postazione> postazioniTrovate = postazioneService.cercaPostazione(tipoRicerca, cittaRicerca);

        if (postazioniTrovate.isEmpty()) {
            System.out.println("Nessuna postazione trovata.");
        } else {
            System.out.println("Postazioni trovate: " + postazioniTrovate.size());
            postazioniTrovate.forEach(p -> System.out.println("Postazione disponibile: " + p.getDescrizione() + " - Data prenotazione: " + p.getDataPrenotazione()));
        }
    }
}