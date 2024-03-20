package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import med.voll.api.domain.user.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String generateToken(User user) {
        try {
            var algorithm = Algorithm.HMAC256("secret");
            return JWT.create()
                    .withIssuer("API Voll.med")
                    .withSubject(user.getUsername())
//                    .withClaim("id", user.getId())
                    .withExpiresAt(this.ExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            // Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException("Error to generate token.", exception);
        }
    }

    // Return the expiration date of the token
    private Instant ExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
