package ir.technyx.icm_core.service.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Transactional
public class JwtServiceImpl implements JwtService {

    @Value("${security_jwt_secret_key}")
    private String SECRET_KEY;

    @Value("${expiration_time}")
    private int EXPIRATION_TIME;

    @Value("${refresh_expiration_time}")
    private int REFRESH_EXPIRATION_TIME;

    @Override
    public boolean isTokenValid(String token, String username) {
        final String extractTokenUsername = extractUsername(token);
        return (extractTokenUsername.equals(username)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration);
    }

    @Override
    public String extractUsername(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public String generateAccessToken(String username) {
        return buildToken(new HashMap<>(), username, getCurrentExpirationTime());
    }

    @Override
    public String generateRefreshToken(String username) {
        return buildToken(new HashMap<>(), username, getRefreshExpirationTime());
    }

    private String buildToken(Map<String, Object> extraClaims, String username, Date expirationDate) {

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public Date getCurrentExpirationTime() {
        return new Date(System.currentTimeMillis() + EXPIRATION_TIME);
    }

    @Override
    public Date getRefreshExpirationTime() {
        return new Date(System.currentTimeMillis() + REFRESH_EXPIRATION_TIME);
    }


    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}