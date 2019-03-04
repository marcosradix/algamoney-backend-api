package br.com.workmade.algamoneybackendapi.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;


public class CreateResourceEvent extends ApplicationEvent{
	private static final long serialVersionUID = -5941208923076404952L;

	@Getter
	private HttpServletResponse response;
	@Getter
	private Long codigo;
	
	public CreateResourceEvent(Object source, HttpServletResponse response, Long codigo) {
		super(source);
		this.response = response;
		this.codigo = codigo;
	}

}
