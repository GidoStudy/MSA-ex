package com.sparta.msa_exam.auth.controller;

import com.sparta.msa_exam.auth.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestParam("user_id") String userId) {
       String token = authService.signIn(userId);
       log.info("token + {}", token);
       return ResponseEntity.ok(new AuthResponse(token));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class SignInRequest {
        private String userId;
        private String password;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class AuthResponse {
        private String access_token;
    }
}
