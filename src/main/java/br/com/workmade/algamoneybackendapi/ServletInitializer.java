package br.com.workmade.algamoneybackendapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import br.com.workmade.algamoneybackendapi.model.Categoria;


public class ServletInitializer extends SpringBootServletInitializer {
	private Logger LOG = LoggerFactory.getLogger(AlgamoneyBackendApiApplication.class);
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		Categoria build = Categoria.builder().codigo(1L).nome("Teste").build();
		LOG.info(build.toString());
		return application.sources(AlgamoneyBackendApiApplication.class);
	}

}
