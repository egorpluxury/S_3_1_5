package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void add(User user);

    List<User> getAllUsers();

    void delete(Long id);

    void update(User user);

    User getById(Long id);

    User getCurrentUser();
}
