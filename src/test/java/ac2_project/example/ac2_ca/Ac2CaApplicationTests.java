package ac2_project.example.ac2_ca;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;

class Ac2CaApplicationTest {

	@Test
	@DisplayName("Deve retornar 'Hello World' no método home()")
	void deveRetornarHelloWorld() {
		Ac2CaApplication app = new Ac2CaApplication();
		String resultado = app.home();
		assertThat(resultado).isEqualTo("Hello World");
	}

	@Test
	@DisplayName("Deve chamar SpringApplication.run() no método main() sem realmente subir contexto")
	void deveExecutarMainSemSubirContexto() {
		try (MockedStatic<SpringApplication> mocked = mockStatic(SpringApplication.class)) {
			// executa o método main e verifica se SpringApplication.run foi chamado
			Ac2CaApplication.main(new String[] {});
			mocked.verify(() -> SpringApplication.run(Ac2CaApplication.class, new String[] {}));
		}
		assertThat(true).isTrue(); // só pra manter o assert final
	}
}
