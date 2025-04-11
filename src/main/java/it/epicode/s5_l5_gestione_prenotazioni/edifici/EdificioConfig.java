package it.epicode.s5_l5_gestione_prenotazioni.edifici;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EdificioConfig {

    @Bean
    public Edificio edificio1() {
        Edificio edificio = new Edificio();
        edificio.setNome("Edificio 1");
        edificio.setIndirizzo("Via Roma 1");
        edificio.setCitta("Roma");
        return edificio;
    }

    @Bean
    public Edificio edificio2() {
        Edificio edificio = new Edificio();
        edificio.setNome("Edificio 2");
        edificio.setIndirizzo("Via Milano 2");
        edificio.setCitta("Milano");
        return edificio;
    }

    @Bean
    public Edificio edificio3() {
        Edificio edificio = new Edificio();
        edificio.setNome("Edificio 3");
        edificio.setIndirizzo("Via Napoli 3");
        edificio.setCitta("Napoli");
        return edificio;
    }
}


