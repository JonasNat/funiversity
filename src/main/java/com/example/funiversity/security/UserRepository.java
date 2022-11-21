package com.example.funiversity.security;

import com.example.funiversity.security.domain.Role;
import com.example.funiversity.security.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {
    private Map<String, User> userMap = new HashMap<>(Map.of(
            "Jonas", new User("Jonas", "pwd", Role.ADMIN),
            "Erik", new User("Erik", "pwd", Role.MANAGER),
            "Jos", new User("Jos", "pwd", Role.MEMBER)));

            public Optional<User> getUser(String userName) {
                return Optional.of(userMap.get(userName));
            }
}
