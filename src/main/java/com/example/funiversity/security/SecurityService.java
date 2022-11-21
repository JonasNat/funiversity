package com.example.funiversity.security;

import com.example.funiversity.security.domain.Feature;
import com.example.funiversity.security.domain.User;
import com.example.funiversity.security.domain.UserNamePassword;
import com.example.funiversity.security.exceptions.UnauthorizedException;
import com.example.funiversity.security.exceptions.UnknownUserException;
import com.example.funiversity.security.exceptions.WrongPasswordException;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;
import java.util.Optional;

@Service
public class SecurityService {
    private final UserRepository repository;
    private final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    public SecurityService(UserRepository repository) {
        this.repository = repository;
    }

    public void validateAuthorization(String authorization, Feature feature) {
        UserNamePassword usernamePassword = getUserNamePassword(authorization);
        User user = repository.getUser(usernamePassword.getUserName()).orElseThrow(UnknownUserException::new);
        if (!user.doesPasswordMatch(usernamePassword.getPassword())) {
            logger.error("Password does not match for user " + usernamePassword.getUserName());
            throw new WrongPasswordException();
        }
        if (!user.canHaveAccessTo(feature)) {
            logger.error("User " + usernamePassword.getUserName() + " does not have access to " + feature);
            throw new UnauthorizedException();
        }
    }

    private UserNamePassword getUserNamePassword(String authorization) {
        String decodedUsernameAndPassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        String username = decodedUsernameAndPassword.substring(0, decodedUsernameAndPassword.indexOf(":"));
        String password = decodedUsernameAndPassword.substring(decodedUsernameAndPassword.indexOf(":") + 1);
        return new UserNamePassword(username, password);
    }
}
