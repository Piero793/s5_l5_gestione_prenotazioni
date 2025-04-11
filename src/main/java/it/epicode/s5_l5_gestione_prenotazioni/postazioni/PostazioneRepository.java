package it.epicode.s5_l5_gestione_prenotazioni.postazioni;


import it.epicode.s5_l5_gestione_prenotazioni.utenti.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PostazioneRepository extends JpaRepository<Postazione, Long> {

    // Trova le postazioni già prenotate dall'utente in una determinata data
    List<Postazione> findByUtenteAndDataPrenotazione(Utente utente, LocalDate dataPrenotazione);

    // Trova le postazioni per tipo e città
    List<Postazione> findByTipoAndEdificio_Citta(TipoPostazione tipo, String citta);

}
