package ru.kata.spring.boot_security.demo.init;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class DbInit implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DbInit(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Role role1 = new Role();
        role1.setName("ROLE_USER");
        Role role2 = new Role();
        role2.setName("ROLE_ADMIN");

        roleRepository.save(role1);
        roleRepository.save(role2);

        List<Role> adminRoles = new ArrayList<>();
        adminRoles.add(role1);
        adminRoles.add(role2);

        List<Role> userRoles = new ArrayList<>();
        userRoles.add(role1);

        User admin = new User();
        admin.setPassword(passwordEncoder.encode("12345"));
        admin.setRoles(adminRoles);
        admin.setUsername("admin");
        admin.setLastName("admin");
        admin.setAge(45);
        admin.setEmail("admin@mail.ru");
        userRepository.save(admin);

        User user = new User();
        user.setPassword(passwordEncoder.encode("12345"));
        user.setRoles(userRoles);
        user.setUsername("user");
        user.setLastName("user");
        user.setAge(25);
        user.setEmail("user@mail.ru");
        userRepository.save(user);
    }
}