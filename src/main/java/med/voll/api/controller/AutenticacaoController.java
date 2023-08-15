package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.DadosAutenticacao;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.infra.security.DadosTokenJWT;
import med.voll.api.infra.security.TokenService;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
	try {	
		var Autenticacaotoken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha()); //DTO do próprio spring que representa os dados de login
		
		var autente = manager.authenticate(Autenticacaotoken);
		
		var tokenJWT = tokenService.gerarToken((Usuario)autente.getPrincipal());
		return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
	}catch(Exception e) {	
		e.printStackTrace();
		 return ResponseEntity.badRequest().body(e.getMessage());
	}
	}
}
