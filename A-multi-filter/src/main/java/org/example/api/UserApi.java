package org.example.api;

import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/users")
public class UserApi {

    private static final Map<String, String> REPOSITORY = new HashMap<>();

    @GetMapping
    @ResponseStatus(NO_CONTENT)
    public Map<String, String> getUserName() {
        return REPOSITORY;
    }

    @PostMapping
    @ResponseStatus(NO_CONTENT)
    public String createUser() {
        final String randomId = String.valueOf(UUID.randomUUID());
        REPOSITORY.put(randomId, new Date().toString());

        return randomId;
    }

    @GetMapping("/information")
    @ResponseStatus(NO_CONTENT)
    public String getInformation() {
        return "information";
    }

    @GetMapping("/rank")
    @ResponseStatus(NO_CONTENT)
    public String rank() {
        return "rank";
    }

    @GetMapping("/like")
    @ResponseStatus(NO_CONTENT)
    public String like() {
        return "like";
    }

    @PostMapping("/join")
    @ResponseStatus(NO_CONTENT)
    public String join() {
        return "Join";
    }

    @PostMapping("/login")
    @ResponseStatus(NO_CONTENT)
    public String login() {
        return "Login";
    }
}