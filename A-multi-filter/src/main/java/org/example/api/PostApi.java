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
@RequestMapping("/domain/posts/")
public class PostApi {

    private static final Map<String, String> REPOSITORY = new HashMap<>();

    @GetMapping
    public Map<String, String> getPost() {
        return REPOSITORY;
    }

    @PostMapping
    public String createPost() {
        final String randomId = String.valueOf(UUID.randomUUID());
        REPOSITORY.put(randomId, new Date().toString());

        return randomId;
    }
}
