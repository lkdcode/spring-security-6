package org.example.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PostApiTest {

    private static final String END_POINT = "/domain/posts";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void post_ok_test() throws Exception {
        mockMvc.perform(post(END_POINT))
                .andExpect(status().isNoContent());
    }

    @Test
    void get_ok_test() throws Exception {
        mockMvc.perform(get(END_POINT + "/get"))
                .andExpect(status().isNoContent());
    }

    @Test
    void get_all_ok_test() throws Exception {
        mockMvc.perform(get(END_POINT + "/get-all"))
                .andExpect(status().isNoContent());
    }

    @Test
    void delete_entry_point_test() throws Exception {
        mockMvc.perform(delete(END_POINT))
                .andExpect(status().isIAmATeapot());
    }

    @Test
    void put_entry_point_test() throws Exception {
        mockMvc.perform(put(END_POINT))
                .andExpect(status().isIAmATeapot());
    }
}