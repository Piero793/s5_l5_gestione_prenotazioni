package it.epicode.s5_l5_gestione_prenotazioni.utenti;

import it.epicode.s5_l5_gestione_prenotazioni.postazioni.Postazione;
import it.epicode.s5_l5_gestione_prenotazioni.postazioni.PostazioneRepository;
import it.epicode.s5_l5_gestione_prenotazioni.postazioni.TipoPostazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {

    @Autowired
    private PostazioneRepository postazioneRepository;

    //metodo per ricercare le postazioni per tipo e città
    //un utente può avere piu prenotazioni in corso
    //ma non può prenotare piu di una postazione per un giorno
    public List<Postazione> cercaPostazione(TipoPostazione tipo, String citta) {
        List<Postazione> postazioni = postazioneRepository.findByTipoAndEdificio_Citta(tipo, citta);

        System.out.println("Richiesta di ricerca per tipo: " + tipo + " e città: " + citta);
        System.out.println("Postazioni trovate: " + postazioni.size());

        postazioni.forEach(p -> System.out.println("Postazione disponibile: " + p.getDescrizione()));

        return postazioni;
    }
}
