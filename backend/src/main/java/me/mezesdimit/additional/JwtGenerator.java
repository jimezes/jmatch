package me.mezesdimit.additional;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtGenerator {
    private static final long EXPIRATION_TIME = 86400000;
    private static final String SECRET_KEY = "AAAfOE#F0K4GPPL4-0GK0340H5KHB5GF23R24959I9FIJ583U8H@9EFJ294J9"; //randomly

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(String email){

        String result = Jwts.builder().setSubject(email)
                .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        System.out.println("generated JWT: "+result);
        return result;
    }

    public String authAndGetEmail(String token){
        return Jwts.parserBuilder().setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
