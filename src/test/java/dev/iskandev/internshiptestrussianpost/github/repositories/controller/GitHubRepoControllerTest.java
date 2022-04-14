package dev.iskandev.internshiptestrussianpost.github.repositories.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class GitHubRepoControllerTest {

    private static final String URL = "https://localhost/v1.0/github/repositories";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testIncorrectFilterValue() throws Exception {
        this.mockMvc.perform(get(URL + "?topic=java&filter=commits"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCorrect() throws Exception {
        this.mockMvc.perform(get(URL + "?topic=java&filter=popular"))
                .andExpect(status().isOk());
        this.mockMvc.perform(get(URL + "?topic=devops&filter=popular&count=50"))
                .andExpect(status().isOk());
    }

    @Test
    void testCountOutOfRange() throws Exception {
        this.mockMvc.perform(get(URL + "?topic=java&filter=commits&count=101"))
                .andExpect(status().isBadRequest());
        this.mockMvc.perform(get(URL + "?topic=java&filter=commits&count=0"))
                .andExpect(status().isBadRequest());
    }
}