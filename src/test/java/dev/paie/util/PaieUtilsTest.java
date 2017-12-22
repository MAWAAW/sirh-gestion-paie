package dev.paie.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;

@ContextConfiguration(classes = { ServicesConfig.class })
@RunWith(SpringRunner.class)
public class PaieUtilsTest {

	@Autowired
	private PaieUtils paieUtils;
	
	@Test
	public void test_formaterBigDecimal_entier_positif() {
		String resultat = paieUtils.formaterBigDecimal(new BigDecimal("2"));
		assertThat(resultat, equalTo("2.00"));
	}
	
	@Test
	public void test_formaterBigDecimal_trois_chiffres_apres_la_virgule() {
		String resultat = paieUtils.formaterBigDecimal(new BigDecimal("2.199"));
		assertThat(resultat, equalTo("2.20"));
	}
	
	@Test
	public void test_formaterBigDecimal_entier_negatif() {
		String resultat = paieUtils.formaterBigDecimal(new BigDecimal("-2"));
		assertThat(resultat, equalTo("-2.00"));
	}
	
}
