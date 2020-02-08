package com.itmo.web.weblab4.Repositories;

import com.itmo.web.weblab4.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
