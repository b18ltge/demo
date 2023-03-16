package com.example.demo;

import com.example.demo.models.Person;
import com.example.demo.repositories.PersonRepository;
import org.junit.runner.RunWith;
import org.junit.Test;      // do not import org.junit.jupiter.api.Test; !!!
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = DemoApplication.class)
@AutoConfigureMockMvc
public class PersonRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PersonRepository repository;

    // write test cases here
    @Test
    public void test() throws Exception {
        mvc.perform(get("/people"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        repository.save(new Person("Bob", "Hanson", LocalDate.of(2000, 1, 2)));
        mvc.perform(get("/people/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        mvc.perform(get("/people/-1"))
                .andExpect(status().is(404))
                .andExpect(MockMvcResultMatchers.content()
                        .string("Could not find person (id = -1)"));
    }
}
