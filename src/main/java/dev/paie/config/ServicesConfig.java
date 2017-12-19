package dev.paie.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import dev.paie.spring.DataSourceH2Config;

@Configuration
@ComponentScan({"dev.paie.service", "dev.paie.util"})
@Import(DataSourceH2Config.class)
public class ServicesConfig {

}
