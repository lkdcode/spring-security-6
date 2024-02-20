package org.example.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserApiTest {

    private static final String END_POINT = "/api/users";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void get_ok_test() throws Exception {
        this.mockMvc
                .perform(get(END_POINT))
                .andExpect(status().isNoContent());
    }

    @Test
    void post_ok_test() throws Exception {
        this.mockMvc
                .perform(post(END_POINT))
                .andExpect(status().isNoContent());
    }

    @Test
    void login_ok_test() throws Exception {
        this.mockMvc
                .perform(post(END_POINT + "/login"))
                .andExpect(status().isNoContent());
    }

    @Test
    void join_ok_test() throws Exception {
        this.mockMvc
                .perform(post(END_POINT + "/join"))
                .andExpect(status().isNoContent());
    }

    @Test
    void information_entry_point_test() throws Exception {
        this.mockMvc
                .perform(get(END_POINT + "/information"))
                .andExpect(status().isIAmATeapot());
    }

    @Test
    void rank_entry_point_test() throws Exception {
        this.mockMvc
                .perform(get(END_POINT + "/rank"))
                .andExpect(status().isIAmATeapot());
    }

    @Test
    void like_enrty_point_test() throws Exception {
        this.mockMvc
                .perform(get(END_POINT + "/like"))
                .andExpect(status().isIAmATeapot());
    }
}