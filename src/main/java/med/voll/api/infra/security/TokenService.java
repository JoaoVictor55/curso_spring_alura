package med.voll.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import med.voll.api.domain.usuario.Usuario;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String senha;
	
	private final String ISSUER = "API Voll.med";
	
	public String gerarToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(senha); //declara o algoritmo para criar a assinatura
            return JWT.create() //aqui estamos passando informações ao token. Existe um método withClaims que permite passar uma chave : valor 
                .withIssuer(ISSUER)
                .withSubject(usuario.getLogin()) //dono do token
                .withExpiresAt(dataExpiracao(2)) //tempo de expiração. Quando acaba, é preciso gerar um novo token. Vamos considerar 2 horas
                .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerrar token jwt", exception);
        } 
	}
	
	public String getSubject(String tokenJWT) {
        try {
                var algoritmo = Algorithm.HMAC256(senha);
                return JWT.require(algoritmo)
                                .withIssuer(ISSUER)
                                .build()
                                .verify(tokenJWT)
                                .getSubject();
        } catch (JWTVerificationException exception) {
                throw new RuntimeException("Token JWT inválido ou expirado!"+tokenJWT);
        }
}
	
	public Instant dataExpiracao(int tempo) {
		
		return LocalDateTime.now().plusHours(tempo).toInstant(ZoneOffset.of("-03:00"));
	}

}
