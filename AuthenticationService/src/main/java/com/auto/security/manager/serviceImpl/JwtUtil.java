package com.auto.security.manager.serviceImpl;


import com.auto.entity.Entities.User;
import com.auto.entity.Repositorys.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret ;
    @Value("${jwt.expiration}")
    private Long expiration;
    private Key key ;

    @Autowired
    private UserRepository userRepository ;

    @PostConstruct
    public void initKey() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public Date getExpirationDate(String token) {
        return getClaims(token).getExpiration();
    }

    public String generateTokenJwt(Long userId, String role , String userName) {
        Map<String, String> claims = Map.of("id", "" + userId, "role", role);

        final Date now = new Date();
        final Date exp = new Date(now.getTime() + this.expiration) ;

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key)
                .compact();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    private boolean isExpired(String token) {
        return getExpirationDate(token).before(new Date());
    }


    public boolean validateToken(String token) {
        final String username  ;
        try{
             username = extractUsername(token);
        }catch (Exception e){
            return false ;
        }
        Optional<User> user = userRepository.findByEmail(username) ;
        return (!user.isEmpty() && !isExpired(token));
    }
}
