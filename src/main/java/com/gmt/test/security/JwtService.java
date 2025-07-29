package com.gmt.test.security;

import com.gmt.test.dao.UserDAO;
import com.gmt.test.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long EXPIRATION = 86400000; // 1 day

    private SecretKey getSigningKey() {
        return SECRET_KEY;
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("role", userDetails.getAuthorities().iterator().next().getAuthority())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername()) &&
                !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
                .parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }

    @Service
    public static class UserDetailsServiceImpl implements UserDetailsService {

        @Autowired
        private UserDAO userDAO;

        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            List<User> users = userDAO.loadUsers();

            return users.stream()
                    .filter(u -> u.getEmail().equalsIgnoreCase(email))
                    .findFirst()
                    .map(u -> new org.springframework.security.core.userdetails.User(
                            u.getEmail(),
                            u.getPassword(),
                            List.of(new SimpleGrantedAuthority("ROLE_" + u.getRole().toString().toUpperCase()))
                    ))
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        }
    }
}