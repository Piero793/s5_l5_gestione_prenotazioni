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
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
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

        // Salva gli utenti
        utenteRepository.save(utente1);
        utenteRepository.save(utente2);
        utenteRepository.save(utente3);

        // Salva le postazioni
        postazioneRepository.save(postazione1);
        postazioneRepository.save(postazione2);
        postazioneRepository.save(postazione3);

        // METODO PRENOTAZIONE
        LocalDate dataPrenotazione = LocalDate.now().plusDays(2);
        System.out.println("\nTentativo di prenotazione della postazione 1 per " + dataPrenotazione);
        String risultatoPrenotazione = postazioneService.prenotaPostazione(postazione1, utente1, dataPrenotazione);
        System.out.println("Risultato prenotazione: " + risultatoPrenotazione);

        // METODO RICERCA POSTAZIONI
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

        System.out.println("-------------------------------------");
        System.out.println("\nRicerca postazioni disponibili...");
        List<Postazione> postazioniDisponibili = postazioneRepository.findPostazioniDisponibili();

        if (postazioniDisponibili.isEmpty()) {
            System.out.println("Nessuna postazione disponibile.");
        } else {
            postazioniDisponibili.forEach(p -> System.out.println("Postazione libera: " + p.getDescrizione()));
        }

        System.out.println("-------------------------------------");
        System.out.println("\nPostazioni prenotate da " + utente1.getNomeCompleto());
        List<Postazione> postazioniPrenotate = postazioneRepository.findPostazioniPrenotateByUtente(utente1);

        if (postazioniPrenotate.isEmpty()) {
            System.out.println("L'utente non ha prenotazioni.");
        } else {
            postazioniPrenotate.forEach(p -> System.out.println("Postazione prenotata: " + p.getDescrizione() + " - Data: " + p.getDataPrenotazione()));
        }
    }
}