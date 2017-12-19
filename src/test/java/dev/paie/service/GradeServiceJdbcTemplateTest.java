package dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.JeuxDeDonneesConfig;
import dev.paie.config.ServicesConfig;
import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Grade;
import dev.paie.spring.DataSourceH2Config;

@ContextConfiguration(classes = { ServicesConfig.class })
@RunWith(SpringRunner.class)
public class GradeServiceJdbcTemplateTest {
	
	@Autowired private DataSource dataSource;
	
	@Autowired private GradeService gradeService;
	
	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		
		Grade nouveauGrade = new Grade();
		nouveauGrade.setCode("codeTest");
		nouveauGrade.setNbHeuresBase(new BigDecimal("34.43"));
		nouveauGrade.setTauxBase(new BigDecimal("4.44"));
		gradeService.sauvegarder(nouveauGrade);
		
		Grade g = gradeService.getGradeByCode("codeTest");
		g.setTauxBase(new BigDecimal("9.99"));
		gradeService.mettreAJour(g);
		
		assertThat(nouveauGrade.getTauxBase()).isEqualTo(new BigDecimal("4.44"));
		assertThat(g.getTauxBase()).isEqualTo(new BigDecimal("9.99"));
	}
}
