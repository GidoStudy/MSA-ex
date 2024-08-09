package com.sparta.msa_exam.auth.service;

import com.sparta.msa_exam.auth.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class AuthService {

    @Value("${spring.application.name}")
    private String issuer;

    @Value("${service.jwt.access-expiration}")
    private Long accessExpiration;

    private final UserRepository userRepository;
    private final SecretKey secretKey;

    public AuthService(UserRepository userRepository, @Value("${service.jwt.secret-key}") String secretKey) {
        this.userRepository = userRepository;
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
    }

    public String signIn(String userId) {
        return createAccessToken(userId);
    }

    private String createAccessToken(String userId) {
        return Jwts.builder()
                .claim("user_id", userId)
                .issuer(issuer)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+ accessExpiration))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }
}
