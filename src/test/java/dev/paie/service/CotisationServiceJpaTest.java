package dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Grade;

@ContextConfiguration(classes = { ServicesConfig.class })
@RunWith(SpringRunner.class)
public class CotisationServiceJpaTest {
	
	@Autowired
	private CotisationService cotisationService;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		
		Cotisation nouvelleCotisation = new Cotisation();
		nouvelleCotisation.setCode("codeTest");
		nouvelleCotisation.setLibelle("libelleTest");
		nouvelleCotisation.setTauxSalarial(new BigDecimal("23.23"));
		nouvelleCotisation.setTauxPatronal(new BigDecimal("12.5"));
		cotisationService.sauvegarder(nouvelleCotisation);
		
		Cotisation c = cotisationService.lister().stream().filter(p -> p.getCode()=="codeTest").findAny().orElse(null);
		if(c != null) {
			c.setTauxPatronal(new BigDecimal("18.88"));
			cotisationService.mettreAJour(c);
			assertThat(c.getTauxPatronal()).isEqualTo(new BigDecimal("18.88"));
		}
		
		assertThat(nouvelleCotisation.getTauxPatronal()).isEqualTo(new BigDecimal("12.5"));
	}
}

