package dev.paie.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;

@Service
public class CotisationServiceJpa implements CotisationService {

	@PersistenceContext private EntityManager em;

	@Transactional
	@Override
	public void sauvegarder(Cotisation nouvelleCotisation) {
		
		em.persist(nouvelleCotisation);
	}

	@Transactional
	@Override
	public void mettreAJour(Cotisation cotisation) {
		
		em.merge(cotisation);
	}

	@Override
	public List<Cotisation> lister() {
		
		return em.createQuery("select c from Cotisation c",Cotisation.class).getResultList();
	}
	
}
