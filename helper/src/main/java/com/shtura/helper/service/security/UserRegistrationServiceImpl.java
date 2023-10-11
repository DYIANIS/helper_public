package com.shtura.helper.service.security;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shtura.helper.repositories.UserRepository;
import com.shtura.helper.entity.Role;
import com.shtura.helper.entity.User;
import com.shtura.helper.web.dto.UserDto;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Transactional
    @Override
    public User registerNewUser(UserDto accountDto) throws UsernameExistsException {
        if (usernameExist(accountDto.getUsername())) {
            throw new UsernameExistsException("There is an account with that username:" + accountDto.getUsername());
        }
        User user = new User();
        user.setLogin(accountDto.getUsername());
        user.setEmail(accountDto.getEmail());
        user.setPassword(encoder.encode(accountDto.getPassword()));

        user.setRoles(Arrays.asList(Role.DEFAULT));

        userRepository.save(user);
        return user;
    }

    private boolean usernameExist(String username) {
        User user = userRepository.findByLogin(username);
        if (user != null) {
            return true;
        }
        return false;
    }
}
