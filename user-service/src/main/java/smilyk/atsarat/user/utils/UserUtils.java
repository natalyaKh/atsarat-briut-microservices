package smilyk.atsarat.user.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import smilyk.atsarat.user.constants.SecurityConstants;

import java.util.Date;
import java.util.UUID;
/**
 * Generation
 */
@Service
public class UserUtils {

    public UUID generateUserId() {
        return UUID.randomUUID();
    }

    /**
     * generating  emailVerificationToken
     **/
    public String generateEmailVerificationToken(String userId) {
        String token = Jwts.builder()
                .setSubject(userId)
//                time of email verification = 864000000; // 10 days
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
                .compact();
        return token;
    }

    /**
     * checking of token`s validation
     **/
    public static boolean hasTokenExpired(String token) {
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
/**
 * Creating token for changing password. Time of validation of token is 1 day
 */
    public String generatePasswordResetToken(String userId)
    {
        String token = Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
                .compact();
        return token;
    }

//    /**   Создание токена для супер админа. Отличается от generateEmailVerificationToken срокос действия (1 день)**/
//    public String generateSuperAdminToken(String userId)
//    {
//        String token = Jwts.builder()
//                .setSubject(userId)
//                .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
//                .compact();
//        return token;
//    }

}

