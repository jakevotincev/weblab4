package com.itmo.web.weblab4.Controllers;

import com.itmo.web.weblab4.Services.UserService;
import com.itmo.web.weblab4.dto.AuthenticationRequestDto;
import com.itmo.web.weblab4.entities.User;
import com.itmo.web.weblab4.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final
    JwtTokenProvider jwtTokenProvider;

    @Qualifier("userServiceImpl")
    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDto requestDto) throws UsernameNotFoundException {
        try {
            String login = requestDto.getLogin();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, requestDto.getPassword()));
            User user = userService.findByLogin(login);
            if (user == null) {
                throw new UsernameNotFoundException("User with login: " + login + " not found");
            }

            String token = jwtTokenProvider.createToken(login);

            Map<Object, Object> response = new HashMap<>();
            response.put("login", login);
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid login or password");
        }
    }
    @PostMapping("register")
    public ResponseEntity<?> register (@RequestBody AuthenticationRequestDto requestDto){
            String login = requestDto.getLogin();
            String password = requestDto.getPassword();
            User user = userService.findByLogin(login);
            if(user!=null){
                throw new BadCredentialsException("User with login: " + login + " already exists");
            }
            User newUser = new User(login, password);
            userService.addUser(newUser);
            Map<Object, Object> response = new HashMap<>();
            response.put("login", login);
            response.put("password", password);
            return ResponseEntity.ok(response);

    }

}
