package com.itmo.web.weblab4.Services;

import com.itmo.web.weblab4.entities.User;

public interface UserService {
    User addUser(User user);
    User findByLogin (String login);
}
