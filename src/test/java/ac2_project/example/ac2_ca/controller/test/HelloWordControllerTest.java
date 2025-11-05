package ac2_project.example.ac2_ca.controller.test;

import ac2_project.example.ac2_ca.controller.Hello_WorldController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Hello_WorldController.class)
class Hello_WorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deve retornar 'Hello, World!' ao acessar /api/example")
    void deveRetornarHelloWorld() throws Exception {
        mockMvc.perform(get("/api/example"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));
    }
}
