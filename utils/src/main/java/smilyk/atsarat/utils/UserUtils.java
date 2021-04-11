package smilyk.atsarat.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * create token for confirmEmail
 */
@Service
public class UserUtils { public String generateEmailVerificationToken(String userId) {
    String token = Jwts.builder()
        .setSubject(userId)
        .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
        .compact();
    return token;
}

    /**
     * method check if token expired
     * @param token
     * @return
     */
    public boolean hasTokenExpired(String token) {
        boolean rez = false;
        try {
            Claims claims = Jwts.parser().setSigningKey(SecurityConstants.getTokenSecret()).parseClaimsJws(token)
                .getBody();
            Date tokenExpirationDate = claims.getExpiration();
            Date todayDate = new Date();
            rez = tokenExpirationDate.before(todayDate);
        } catch (ExpiredJwtException ex) {
            rez = true;
        }
        return rez;
    }
}
