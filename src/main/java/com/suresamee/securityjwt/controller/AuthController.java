package com.suresamee.securityjwt.controller;

import com.suresamee.securityjwt.config.jwt.JwtProvider;
import com.suresamee.securityjwt.dto.request.AuthRequest;
import com.suresamee.securityjwt.dto.request.RegistrationRequest;
import com.suresamee.securityjwt.dto.response.AuthResponse;
import com.suresamee.securityjwt.model.User;
import com.suresamee.securityjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest){
        User user = new User();
        user.setLogin(registrationRequest.getLogin());
        user.setPassword(registrationRequest.getPassword());
        userService.saveUser(user);
        return "OK";
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest authRequest){
        User user = userService.findByLoginAndPassword(authRequest.getLogin(),authRequest.getPassword());
        String token = jwtProvider.generateToken(user.getLogin());
        return new AuthResponse(token);
    }
}
