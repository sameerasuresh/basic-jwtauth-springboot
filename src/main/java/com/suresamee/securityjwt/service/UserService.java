package com.suresamee.securityjwt.service;

import com.suresamee.securityjwt.model.Role;
import com.suresamee.securityjwt.model.User;
import com.suresamee.securityjwt.repository.RoleRepository;
import com.suresamee.securityjwt.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user){
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRole(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByLogin(String login){
        return userRepository.findByLogin(login);
    }

    public User findByLoginAndPassword(String login, String password){
        User user = findByLogin(login);
        if(user != null){
            if(passwordEncoder.matches(password,user.getPassword())){
                return user;
            }
        }
        return null;
    }
}
