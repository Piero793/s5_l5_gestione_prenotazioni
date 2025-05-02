package it.epicode.s5_l5_gestione_prenotazioni.utenti;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="utenti")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false,length =20,unique = true)
    private String username;
    @Column(nullable = false, length =20)
    private String nomeCompleto;
    @Column(nullable = false,unique = true)
    private String email;
}

// aggiungo dei commenti d'esempio per il git flow
