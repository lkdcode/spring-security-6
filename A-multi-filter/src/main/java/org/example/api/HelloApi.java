package org.example.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/hello")
public class HelloApi {

    @GetMapping
    @ResponseStatus(NO_CONTENT)
    public String hello() {
        return "Hello";
    }

    @GetMapping("/world")
    @ResponseStatus(NO_CONTENT)
    public String world() {
        return "world";
    }
}
