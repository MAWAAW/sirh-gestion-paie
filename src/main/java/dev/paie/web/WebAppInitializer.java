package dev.paie.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import dev.paie.config.WebAppConfig;

public class WebAppInitializer implements WebApplicationInitializer {
	
	
	// Comme le main dans une appli jar mais pour les webapp -> C'est la première chose executer au lancement de l'appli
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		// Initialisation du contexte Spring
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.register(WebAppConfig.class);

		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(webContext));
		dispatcher.setLoadOnStartup(1);

		dispatcher.addMapping("/mvc/*");

		servletContext.addListener(new ContextLoaderListener(webContext));
	}
}