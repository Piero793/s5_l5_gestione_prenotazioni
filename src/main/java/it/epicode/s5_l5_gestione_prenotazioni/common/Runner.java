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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class Runner implements CommandLineRunner {
    private final UtenteService utenteService;
    private final PostazioneService postazioneService;
    private final EdificioRepository edificioRepository;
    private final UtenteRepository utenteRepository;
    private final PostazioneRepository postazioneRepository;
    private final Edificio edificio1;
    private final Edificio edificio2;
    private final Edificio edificio3;
    private final Utente utente1;
    private final Utente utente2;
    private final Utente utente3;
    private final Postazione postazione1;
    private final Postazione postazione2;
    private final Postazione postazione3;

    @Override
    public void run(String... args) throws Exception {
        // Salva gli edifici
        edificioRepository.save(edificio1);
        edificioRepository.save(edificio2);
        edificioRepository.save(edificio3);
        log.info("Edifici salvati nel database.");

        // Salva gli utenti
        utenteRepository.save(utente1);
        utenteRepository.save(utente2);
        utenteRepository.save(utente3);
        log.info("Utenti salvati nel database.");

        // Salva le postazioni
        postazioneRepository.save(postazione1);
        postazioneRepository.save(postazione2);
        postazioneRepository.save(postazione3);
        log.info("Postazioni salvate nel database.");

        // METODO PRENOTAZIONE
        LocalDate dataPrenotazione = LocalDate.now().plusDays(2);
        log.info("Tentativo di prenotazione della postazione 1 per {}", dataPrenotazione);
        String risultatoPrenotazione = postazioneService.prenotaPostazione(postazione1, utente1, dataPrenotazione);
        log.info("Risultato prenotazione: {}", risultatoPrenotazione);

        // METODO RICERCA POSTAZIONI
        TipoPostazione tipoRicerca = TipoPostazione.PRIVATO;
        String cittaRicerca = "Roma";
        log.info("Ricerca postazioni di tipo {} in città {}", tipoRicerca, cittaRicerca);
        List<Postazione> postazioniTrovate = postazioneService.cercaPostazione(tipoRicerca, cittaRicerca);

        if (postazioniTrovate.isEmpty()) {
            log.warn("Nessuna postazione trovata.");
        } else {
            log.info("Postazioni trovate: {}", postazioniTrovate.size());
            postazioniTrovate.forEach(p -> log.info("Postazione disponibile: {} - Data prenotazione: {}", p.getDescrizione(), p.getDataPrenotazione()));
        }

        log.info("-------------------------------------");
        log.info("Ricerca postazioni disponibili...");
        List<Postazione> postazioniDisponibili = postazioneRepository.findPostazioniDisponibili();

        if (postazioniDisponibili.isEmpty()) {
            log.warn("Nessuna postazione disponibile.");
        } else {
            postazioniDisponibili.forEach(p -> log.info("Postazione libera: {}", p.getDescrizione()));
        }

        log.info("-------------------------------------");
        log.info("Postazioni prenotate da {}", utente1.getNomeCompleto());
        List<Postazione> postazioniPrenotate = postazioneRepository.findPostazioniPrenotateByUtente(utente1);

        if (postazioniPrenotate.isEmpty()) {
            log.warn("L'utente non ha prenotazioni.");
        } else {
            postazioniPrenotate.forEach(p -> log.info("Postazione prenotata: {} - Data: {}", p.getDescrizione(), p.getDataPrenotazione()));
        }
    }
}