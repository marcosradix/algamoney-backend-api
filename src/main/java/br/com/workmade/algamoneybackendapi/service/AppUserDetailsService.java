package br.com.workmade.algamoneybackendapi.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.workmade.algamoneybackendapi.model.Usuario;

@Service
public class AppUserDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioService usuarioService;
	
	@Override //carregar usuario e senha do banco para validar usuário de login
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = this.usuarioService.findByEmail(email);
		return new User(email, usuario.getSenha(), getPermissoes(usuario));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
	Set<SimpleGrantedAuthority> authorities = new HashSet<>(); 
	usuario.getPermissoes().forEach(p ->{
		authorities.add(new SimpleGrantedAuthority(p.getDescricao().toUpperCase()));
	});
		return authorities;
	}

}
