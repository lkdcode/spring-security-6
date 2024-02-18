package org.example.api;

import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/domain/posts/")
public class PostApi {

    private static final Map<String, String> REPOSITORY = new HashMap<>();

    @PostMapping
    public String createPost() {
        final String randomId = String.valueOf(UUID.randomUUID());
        REPOSITORY.put(randomId, new Date().toString());

        return randomId;
    }

    @GetMapping("/get")
    public String getPost() {
        return "Get";
    }

    @GetMapping("/get-all")
    public Map<String, String> getAllPost() {
        return REPOSITORY;
    }

    @DeleteMapping
    public String delete() {
        return "Delete";
    }

    @PutMapping
    public String put() {
        return "Put";
    }
}
