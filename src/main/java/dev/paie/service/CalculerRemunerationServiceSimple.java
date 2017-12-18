package dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.util.PaieUtils;

@Service
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService {

	@Autowired
	@Qualifier("paieUtils")
	private PaieUtils paieUtils;

	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {

		ResultatCalculRemuneration resultatCalculRemuneration = new ResultatCalculRemuneration();		

		resultatCalculRemuneration.setSalaireDeBase(paieUtils.formaterBigDecimal(bulletin.getRemunerationEmploye().getGrade().getNbHeuresBase().multiply(bulletin.getRemunerationEmploye().getGrade().getTauxBase())));
		BigDecimal salaireBase = new BigDecimal(resultatCalculRemuneration.getSalaireDeBase()); 
		
		resultatCalculRemuneration.setSalaireBrut(paieUtils.formaterBigDecimal(salaireBase.add(bulletin.getPrimeExceptionnelle())));
		BigDecimal salaireBrut = new BigDecimal(resultatCalculRemuneration.getSalaireBrut()); 

		resultatCalculRemuneration.setTotalRetenueSalarial(paieUtils.formaterBigDecimal(bulletin.getRemunerationEmploye().getProfilRemuneration()
				.getCotisationsNonImposables().stream().filter(c -> c.getTauxSalarial() != null)
				.map(c -> c.getTauxSalarial().multiply(salaireBrut)).reduce(BigDecimal.ZERO, (p, q) -> p.add(q))));
		BigDecimal totalRetenueSalariale = new BigDecimal(resultatCalculRemuneration.getTotalRetenueSalarial());

		BigDecimal totalCotisationPatronales = bulletin.getRemunerationEmploye().getProfilRemuneration()
				.getCotisationsNonImposables().stream().filter(c -> c.getTauxPatronal() != null)
				.map(c -> c.getTauxPatronal().multiply(salaireBrut)).reduce(BigDecimal.ZERO, (p, q) -> p.add(q));
		resultatCalculRemuneration.setTotalCotisationsPatronales(paieUtils.formaterBigDecimal(totalCotisationPatronales));

		BigDecimal netImposable = salaireBrut.subtract(totalRetenueSalariale);
		resultatCalculRemuneration.setNetImposable(paieUtils.formaterBigDecimal(netImposable));

		BigDecimal totalCotisationImposable = bulletin.getRemunerationEmploye().getProfilRemuneration()
				.getCotisationsImposables().stream().filter(c -> c.getTauxSalarial() != null)
				.map(c -> c.getTauxSalarial().multiply(salaireBrut)).reduce(BigDecimal.ZERO, (p, q) -> p.add(q));

		BigDecimal netAPayer = netImposable.subtract(totalCotisationImposable);
		resultatCalculRemuneration.setNetAPayer(paieUtils.formaterBigDecimal(netAPayer));

		return resultatCalculRemuneration;
	}

}
