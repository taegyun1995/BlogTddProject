package tdd.blogProject.user.adapter.out.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tdd.blogProject.user.application.port.out.JwtTokenCreatePort;
import tdd.blogProject.user.domain.User;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenCreateUtil implements JwtTokenCreatePort {

    @Value("${jwt.key}")
    private String SECRET_KEY;

    @Override
    public String createJwtToken(User user, long expirationTime) {
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigningKey())
                .compact();
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

}