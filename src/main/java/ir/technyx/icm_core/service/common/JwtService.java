package ir.technyx.icm_core.service.common;


import java.util.Date;

public interface JwtService {

    String extractUsername(String jwtToken);

    Date getCurrentExpirationTime();

    Date getRefreshExpirationTime();

    boolean isTokenValid(String token, String username);

    String generateAccessToken(String username);

    String generateRefreshToken(String username);
}
