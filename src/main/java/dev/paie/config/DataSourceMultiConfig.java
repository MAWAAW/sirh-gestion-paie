package dev.paie.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@PropertySource("classpath:application.properties")
public class DataSourceMultiConfig {
	
	@Bean
	@Profile("genericdb")
	public DataSource dataSource(@Value("${jdbc.driver}")String driver, @Value("${jdbc.database.url}")String url, 
			@Value("${jdbc.database.user}")String user, @Value("${jdbc.database.password}")String password) {
	
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		return dataSource;		
	}
	
	@Bean
	@Profile("h2")
	public DataSource dataSourceH2() {
	
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase dataSource = builder.setType(EmbeddedDatabaseType.H2).build();
		return dataSource;
	}
}