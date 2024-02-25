package org.example.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/custom")
public class CustomApi {

    @GetMapping
    public String get() {
        return "Method: GET";
    }

    @PostMapping
    public String post() {
        return "Method: POST";
    }

    @DeleteMapping
    public String delete() {
        return "Method: DELETE";
    }

    @PutMapping
    public String put() {
        return "Method: PUT";
    }
}
