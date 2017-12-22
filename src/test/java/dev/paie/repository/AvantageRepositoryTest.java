package dev.paie.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.DataSourceMultiConfig;
import dev.paie.config.ServicesConfig;
import dev.paie.entite.Avantage;

@ContextConfiguration(classes = { ServicesConfig.class })
@RunWith(SpringRunner.class)
public class AvantageRepositoryTest {
	
	@Autowired
	private AvantageRepository avantageRepository;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		
		Avantage nouvelleAvantage = new Avantage();
		nouvelleAvantage.setCode("codeTest");
		nouvelleAvantage.setNom("nomTest");
		nouvelleAvantage.setMontant(new BigDecimal("123.32"));
		avantageRepository.save(nouvelleAvantage);
		
		Avantage a = avantageRepository.findOne(nouvelleAvantage.getId());
		a.setMontant(new BigDecimal("188.88"));
		avantageRepository.save(a);
		
		assertThat(nouvelleAvantage.getMontant()).isEqualTo(new BigDecimal("123.32"));
		assertThat(a.getMontant()).isEqualTo(new BigDecimal("188.88"));
		
	}
}