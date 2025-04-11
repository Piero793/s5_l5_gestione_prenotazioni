package it.epicode.s5_l5_gestione_prenotazioni.utenti;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtenteConfig {

    // creo 3 utenti
    @Bean
    public static Utente utente1(){
        Utente utente1 = new Utente();
        utente1.setUsername("mario");
        utente1.setNomeCompleto("Mario Rossi");
        utente1.setEmail("mario-rossi@epicode.it");
        return utente1;
    }

    @Bean
    public static Utente utente2(){
        Utente utente2 = new Utente();
        utente2.setUsername("luigi");
        utente2.setNomeCompleto("Luigi Verdi");
        utente2.setEmail("luigi-verdi@epicode.it");
        return utente2;
    }

    @Bean
    public static Utente utente3(){
        Utente utente3 = new Utente();
        utente3.setUsername("giuseppe");
        utente3.setNomeCompleto("Giuseppe Bianchi");
        utente3.setEmail("giuseppe-bianchi@epicode.it");
        return utente3;
    }
}
