package smilyk.atsarat.utils;


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
}
