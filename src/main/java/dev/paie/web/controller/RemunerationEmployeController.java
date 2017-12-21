package dev.paie.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.service.GradeService;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {
	
	@Autowired 
	private GradeRepository gradeRepository;
	
	@Autowired 
	private ProfilRemunerationRepository profilRemunerationRepository;
	
	@Autowired 
	private EntrepriseRepository entrepriseRepository;
	
	@Autowired 
	private RemunerationEmployeRepository remunerationEmployeRepository;
	
	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");
		mv.addObject("entreprises", entrepriseRepository.findAll());
		mv.addObject("profils", profilRemunerationRepository.findAll());
		mv.addObject("grades", gradeRepository.findAll());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	public ModelAndView listerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("remunerationEmployes", remunerationEmployeRepository.findAll());
		mv.setViewName("employes/listerEmployes");
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	public String creerEmploye(@RequestParam("matricule") String matricule, @RequestParam("entreprise") Integer entreprise,
			@RequestParam("profil") Integer profil, @RequestParam("grade") Integer grade) {
		
		RemunerationEmploye remunerationEmploye = new RemunerationEmploye();
		remunerationEmploye.setMatricule(matricule);
		remunerationEmploye.setEntreprise(entrepriseRepository.findOne(entreprise));
		remunerationEmploye.setGrade(gradeRepository.findOne(grade));
		remunerationEmploye.setProfilRemuneration(profilRemunerationRepository.findOne(profil));
		System.out.println("avant le save");
		remunerationEmployeRepository.save(remunerationEmploye);
		System.out.println("apres le save");
		return "redirect:lister";
	}
	
}