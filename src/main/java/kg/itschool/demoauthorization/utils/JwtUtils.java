package kg.itschool.demoauthorization.utils;


import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.net.Authenticator;
import java.util.Date;
@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

//    public final static String LOGIN_URL = "/auth/login";
    public final static Long TOKEN_EXPIRATION = 300000L;
//    @Value(value = "{secret_key}")
    public final static String SECRET_KEY = "privet";

    // генерация токена
    public String generateToken(Authentication authentication){
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String getUserNameFromJwtToken(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }


    public boolean validateJwtToken(String authToken){
       try {
           Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
           return true;
       }catch (SignatureException e){
           logger.error("Invalid JWT signature: {}",e.getMessage());
       }catch (MalformedJwtException e){
           logger.error("Invalid Jwt token: {}", e.getMessage());
       }catch (ExpiredJwtException e){
           logger.error("Jwt token is expired: {}", e.getMessage());
       }catch (UnsupportedJwtException e){
           logger.error("Jwt token is unsupported {}", e.getMessage());
       }catch (IllegalArgumentException e){
           logger.error("Jwt claims string is empty: {}", e.getMessage());
       }
       return false;
    }
}
