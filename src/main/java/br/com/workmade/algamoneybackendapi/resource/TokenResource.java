package br.com.workmade.algamoneybackendapi.resource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokens")
public class TokenResource {

	@DeleteMapping(value="/revoke")
	public ResponseEntity<Void> revoke(HttpServletRequest req, HttpServletResponse resp) {
		Cookie cookie = new Cookie("refreshToken", null);
		cookie.setHttpOnly(true);
		cookie.setSecure(false); //TODO em produção https true
		cookie.setPath(req.getContextPath()+ "/oauth/token");
		cookie.setMaxAge(0);
		resp.addCookie(cookie);
		resp.setStatus(HttpStatus.NO_CONTENT.value());
		return ResponseEntity.noContent().build();
	}

}
