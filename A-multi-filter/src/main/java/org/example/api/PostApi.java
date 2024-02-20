package org.example.api;

import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/domain/posts")
public class PostApi {

    private static final Map<String, String> REPOSITORY = new HashMap<>();

    @PostMapping
    @ResponseStatus(NO_CONTENT)
    public String createPost() {
        final String randomId = String.valueOf(UUID.randomUUID());
        REPOSITORY.put(randomId, new Date().toString());

        return randomId;
    }

    @GetMapping("/get")
    @ResponseStatus(NO_CONTENT)
    public String getPost() {
        return "Get";
    }

    @GetMapping("/get-all")
    @ResponseStatus(NO_CONTENT)
    public Map<String, String> getAllPost() {
        return REPOSITORY;
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    public String delete() {
        return "Delete";
    }

    @PutMapping
    @ResponseStatus(NO_CONTENT)
    public String put() {
        return "Put";
    }
}