package dev.paie.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import dev.paie.entite.BulletinSalaire;

@Configuration
//@ComponentScan({"dev.paie.util"})
@ImportResource("classpath:jdd-config.xml")
public class JeuxDeDonneesConfig {

}
