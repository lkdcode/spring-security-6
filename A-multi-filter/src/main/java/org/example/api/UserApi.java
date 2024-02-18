package org.example.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserApi {

    private static final Map<String, String> REPOSITORY = new HashMap<>();

    @GetMapping
    public Map<String, String> getUserName() {
        return REPOSITORY;
    }

    @PostMapping
    public String createUser() {
        final String randomId = String.valueOf(UUID.randomUUID());
        REPOSITORY.put(randomId, new Date().toString());

        return randomId;
    }

    @GetMapping("/information")
    public String getInformation() {
        return "information";
    }

    @GetMapping("/rank")
    public String rank() {
        return "rank";
    }

    @GetMapping("/like")
    public String like() {
        return "like";
    }

    @PostMapping("/join")
    public String join() {

        return "Join";
    }

    @PostMapping("/login")
    public String login() {

        return "Login";
    }
}
