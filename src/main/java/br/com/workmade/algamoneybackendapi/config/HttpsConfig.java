package br.com.workmade.algamoneybackendapi.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(value="prod")
public class HttpsConfig {
	
	//https://drissamri.be/blog/java/enable-https-in-spring-boot/ we need a secure certificate
		@Bean
		  public EmbeddedServletContainerFactory servletContainer() {
		    TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
		        @Override
		        protected void postProcessContext(Context context) {
		          SecurityConstraint securityConstraint = new SecurityConstraint();
		          securityConstraint.setUserConstraint("CONFIDENTIAL");
		          SecurityCollection collection = new SecurityCollection();
		          collection.addPattern("/*");
		          securityConstraint.addCollection(collection);
		          context.addConstraint(securityConstraint);
		        }
		      };
		    
		    tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
		    return tomcat;
		  }
		  
		  private Connector initiateHttpConnector() {
		    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		    connector.setScheme("http");
		    connector.setPort(8080);
		    connector.setSecure(false);
		    connector.setRedirectPort(8443);
		    return connector;
		  }

}
