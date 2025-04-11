package it.epicode.s5_l5_gestione_prenotazioni.postazioni;


import it.epicode.s5_l5_gestione_prenotazioni.utenti.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PostazioneRepository extends JpaRepository<Postazione, Long> {

    // Trova le postazioni già prenotate dall'utente in una determinata data
    List<Postazione> findByUtenteAndDataPrenotazione(Utente utente, LocalDate dataPrenotazione);

    // Trova le postazioni per tipo e città
    List<Postazione> findByTipoAndEdificio_Citta(TipoPostazione tipo, String citta);

    @Query("SELECT p FROM Postazione p WHERE p.utente IS NULL")
    List<Postazione> findPostazioniDisponibili();

    @Query("SELECT p FROM Postazione p WHERE p.utente = :utente")
    List<Postazione> findPostazioniPrenotateByUtente(@Param("utente") Utente utente);
}
