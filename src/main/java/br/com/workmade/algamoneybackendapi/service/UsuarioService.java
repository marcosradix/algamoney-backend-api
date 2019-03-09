package br.com.workmade.algamoneybackendapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.workmade.algamoneybackendapi.model.Usuario;
import br.com.workmade.algamoneybackendapi.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository useRepo;
	
	public Usuario findByEmail(String email) {
		Usuario findByEmail = useRepo.findByEmail(email);
		Optional<Usuario> usuarioOptional = Optional.ofNullable(findByEmail);
		return usuarioOptional.orElseThrow(() -> new UsernameNotFoundException(
				"Email e ou senha incorretos! Email : "+email +": "+Usuario.class.getName()));
		 
	}
}
