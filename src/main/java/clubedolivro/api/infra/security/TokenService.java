package clubedolivro.api.infra.security;

import clubedolivro.api.domain.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    //CÓDIGO PRA GERAR O TOKEN
    public String getToken(User user) {
        var alghoritm = Algorithm.HMAC256(secret);

        try {
            return JWT.create()
                    .withIssuer("Clube do Livro API")
                    .withSubject(user.getUsername())
                    .withExpiresAt(expirationDate())
                    .sign(alghoritm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("erro ao gerar token jwt", e);
        }
    }
    //CÓDIGO PARA VERIFICAR O TOKEN E PEGAR O USÚARIO
    public String getSubject(String tokenJWT) {
        try {
            var alghoritm = Algorithm.HMAC256(secret);
            return JWT.require(alghoritm)
                    .withIssuer("Clube do Livro API")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
