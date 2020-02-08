package com.itmo.web.weblab4.security;

import com.itmo.web.weblab4.Services.UserService;
import com.itmo.web.weblab4.entities.User;
import com.itmo.web.weblab4.security.jwt.JwtUser;
import com.itmo.web.weblab4.security.jwt.JwtUserFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.findByLogin(login);

        if(user==null) {
            throw new UsernameNotFoundException("User with login: " + login + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadByUsername - user with login: {} successfully loaded",login);
        return jwtUser;
    }
}
