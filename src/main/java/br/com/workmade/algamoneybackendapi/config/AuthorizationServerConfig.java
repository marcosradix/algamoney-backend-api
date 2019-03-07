package br.com.workmade.algamoneybackendapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private static final String GRANT_TYPE_PASSWORD = "password";

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Value(value = "${secret.client.password}")
	private String secretClientPassword;
	
	@Value(value = "${name.client.application}")
	private String nameClientApplication;
	
	@Value(value = "${read.scope}")
	private String readScope;
	
	@Value(value = "${write.scope}")
	private String writeScope;
	
	@Value(value = "${expire.time.token}")
	private Integer expireTimeToken;
	
	@Override // authentication application
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(this.nameClientApplication)
	    .secret(this.secretClientPassword)
	    .scopes(this.readScope, this.writeScope)
	    .authorizedGrantTypes(GRANT_TYPE_PASSWORD)
	    .accessTokenValiditySeconds(this.expireTimeToken);
		
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore())
		.authenticationManager(this.authenticationManager);
	}

	@Bean //store the token
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}
	
	
	
}





