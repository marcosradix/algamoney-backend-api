package br.com.workmade.algamoneybackendapi.cors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsInteceptorFilter implements Filter{
	
	@Value(value="${dese.origin.permitida}")
	private String deseOriginPermitida;
	
	@Value(value="${homol.origin.permitida}")
	private String homolOriginPermitida;
	
	@Value(value="${prod.origin.permitida}")
	private String prodOriginPermitida;
	
	private String originPermitida() {
		String ambiente = System.getProperty("TP_AMBIENTE");
		if(ambiente == null) {
			return this.deseOriginPermitida;
		}
		switch (ambiente) 
		{
		case "dese":return this.deseOriginPermitida;
		case "homol":return this.homolOriginPermitida;
		case "prod":return this.prodOriginPermitida;
		default:break;
		}
		return this.deseOriginPermitida;
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		response.setHeader("Access-Control-Allow-Origin", this.originPermitida());
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		
		if("OPTIONS".equals(request.getMethod()) && this.originPermitida().equals(request.getHeader("Origin"))) {
			response.setHeader("Access-Control-Allow-Methods","POST, GET, DELETE, PUT, OPTIONS");
			response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept ");
			response.setHeader("Access-Control-Max-Age", "3600");
			
			
			
			response.setStatus(HttpServletResponse.SC_OK);
		}else {
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		
	}


	@Override
	public void destroy() {

		
	}

	
	
}
