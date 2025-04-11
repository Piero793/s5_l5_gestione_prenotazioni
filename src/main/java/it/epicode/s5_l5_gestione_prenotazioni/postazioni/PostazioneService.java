package it.epicode.s5_l5_gestione_prenotazioni.postazioni;

import it.epicode.s5_l5_gestione_prenotazioni.utenti.Utente;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostazioneService {

    @Autowired
    private PostazioneRepository postazioneRepository;

    @Transactional
    public String prenotaPostazione(Postazione postazione, Utente utente, LocalDate dataPrenotazione) {
        // Controlla se l'utente ha già una prenotazione attiva per quel giorno
        List<Postazione> prenotazioniUtente = postazioneRepository.findByUtenteAndDataPrenotazione(utente, dataPrenotazione);

        if (!prenotazioniUtente.isEmpty()) {
            return "L'utente ha già una postazione prenotata per questo giorno!";
        }
        // Controlla se la postazione è disponibile per quel giorno
        if (postazione.getUtente() == null) {
            postazione.setUtente(utente);
            postazione.setDataPrenotazione(dataPrenotazione);
            postazioneRepository.save(postazione);
            return "Postazione prenotata con successo per il giorno " + dataPrenotazione + "!";
        } else {
            return "Postazione già prenotata per quel giorno!";
        }
    }

    public List<Postazione> cercaPostazione(TipoPostazione tipo, String citta) {
        List<Postazione> postazioni = postazioneRepository.findByTipoAndEdificio_Citta(tipo, citta);

        System.out.println("Richiesta di ricerca per tipo: " + tipo + " e città: " + citta);
        System.out.println("Postazioni trovate: " + postazioni.size());

        postazioni.forEach(p -> System.out.println("Postazione disponibile: " + p.getDescrizione()));

        return postazioni;
    }
}



