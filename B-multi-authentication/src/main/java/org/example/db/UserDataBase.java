package org.example.db;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Component
//@RequiredArgsConstructor
public class UserDataBase {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private final Map<String, String> DB = Map.of(
            "user1", encoder.encode("1234"),
            "user2", encoder.encode("1234"),
            "user3", encoder.encode("1234"),
            "user4", encoder.encode("1234"),
            "user5", encoder.encode("1234")
    );


    public Map<String, String> getDB() {
        return new HashMap<>(DB);
    }

    public boolean existById(String userName) {
        return DB.get(userName) != null;
    }

    public String findPasswordById(String userName) {
        return DB.get(userName);
    }
}
