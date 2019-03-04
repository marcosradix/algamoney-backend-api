package br.com.workmade.algamoneybackendapi.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.workmade.algamoneybackendapi.event.CreateResourceEvent;

@Component
public class CreateResourceListener implements ApplicationListener<CreateResourceEvent>{

	@Override
	public void onApplicationEvent(CreateResourceEvent event) {
		HttpServletResponse response = event.getResponse();
		Long codigo = event.getCodigo();
		adicionarHeaderLocation(response, codigo);
	}

	private void adicionarHeaderLocation(HttpServletResponse response, Long codigo) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				 .buildAndExpand(codigo)
				.toUri();
		response.setHeader("Location", uri.toASCIIString());
	}

}
