package it.epicode.s5_l5_gestione_prenotazioni.postazioni;

import it.epicode.s5_l5_gestione_prenotazioni.edifici.Edificio;
import it.epicode.s5_l5_gestione_prenotazioni.utenti.Utente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="postazioni")
public class Postazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false,length =50)
    private String descrizione;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPostazione tipo;
    @Column(nullable = false)
    private int numeroMassimoOccupanti;
    @ManyToOne
    private Edificio  edificio;
    @OneToOne
    private Utente utente;
    @Column(nullable = false)
    private LocalDate dataPrenotazione;
}
