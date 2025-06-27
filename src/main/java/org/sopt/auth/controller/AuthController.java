package org.sopt.auth.controller;

import org.sopt.auth.jwt.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestHeader Long userId) {
        // 실제로는 아이디/비밀번호 인증 후 토큰 발급
        return AuthService.generateToken(userId);
    }
}
