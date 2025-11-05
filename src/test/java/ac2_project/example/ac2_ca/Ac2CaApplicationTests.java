package ac2_project.example.ac2_ca;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class Ac2CaApplicationTest {

	@Autowired
	private MockMvc mockMvc;

	@LocalServerPort
	private int port;

	@Test
	@DisplayName("Deve carregar o contexto da aplicação corretamente")
	void contextLoads() {
		assertThat(port).isGreaterThan(0);
	}

	@Test
	@DisplayName("Deve retornar 'Hello World' na rota raiz '/'")
	void deveRetornarHelloWorld() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(content().string("Hello World"));
	}
}
