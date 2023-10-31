package com.example.demo.util;

import com.example.demo.entity.User;
import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
public class DataLoadUtility implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataLoadUtility(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args){
        if(roleRepository.findByAuthority("ROLE_ADMIN").isPresent()){
            return;
        }
        log.info("DummyMaker.run");
        Role adminRole = roleRepository.save(new Role("ROLE_ADMIN"));
        Role userRole = roleRepository.save(new Role("ROLE_USER"));
        Set<Role> roles3 = Set.of(adminRole, userRole);

        userRepository.save(new User("zombil8731", passwordEncoder.encode("1234"), roles3));

    }

}


