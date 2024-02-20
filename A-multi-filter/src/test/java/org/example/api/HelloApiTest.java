package org.example.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class HelloApiTest {

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void hello_api_test() throws Exception {
        mockMvc.perform(get("/hello")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isIAmATeapot());
    }

    @Test
    void hello_world_api_test() throws Exception {
        mockMvc.perform(get("/hello/world")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isIAmATeapot());
    }
}