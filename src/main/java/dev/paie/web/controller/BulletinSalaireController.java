package dev.paie.web.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.service.CalculerRemunerationService;
import dev.paie.service.CalculerRemunerationServiceSimple;

@Controller
@RequestMapping("/bulletins")
public class BulletinSalaireController {

	@Autowired 
	private PeriodeRepository periodeRepository;
	
	@Autowired 
	private RemunerationEmployeRepository remunerationEmployeRepository;
	
	@Autowired 
	private BulletinSalaireRepository bulletinSalaireRepository;
	
	@Autowired 
	private CalculerRemunerationService calculerRemunerationService;
	
	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerBulletin() {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/creerBulletin");
		mv.addObject("periodes", periodeRepository.findAll());
		mv.addObject("remunerationEmployes", remunerationEmployeRepository.findAll());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	public String creerBulletin(@RequestParam("periode") Integer periode, @RequestParam("matricule") Integer matricule, 
			@RequestParam("primeExceptionnelle") String primeExceptionnelle) {
		
		BulletinSalaire bulletinSalaire = new BulletinSalaire();
		bulletinSalaire.setRemunerationEmploye(remunerationEmployeRepository.findOne(matricule));
		bulletinSalaire.setPeriode(periodeRepository.findOne(periode));
		bulletinSalaire.setPrimeExceptionnelle(new BigDecimal(primeExceptionnelle));;
		bulletinSalaireRepository.save(bulletinSalaire);
		return "redirect:lister";
	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	public ModelAndView listerBulletin() {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/listerBulletins");	
		List<BulletinSalaire> bulletins = bulletinSalaireRepository.findAll();
		Map<BulletinSalaire,ResultatCalculRemuneration> bulletinsCalculer = new HashMap<>();
		bulletins.forEach(b -> bulletinsCalculer.put(b, calculerRemunerationService.calculer(b)));
		mv.addObject("bulletinsCalculer", bulletinsCalculer);
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/visualiser")
	public ModelAndView visualiserBulletin() {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/visualiserBulletin");
		return mv;
	}
	
}
