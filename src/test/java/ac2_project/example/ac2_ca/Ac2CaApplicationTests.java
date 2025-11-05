package ac2_project.example.ac2_ca;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Ac2CaApplicationTest {

	@Test
	@DisplayName("Deve retornar 'Hello World' no método home()")
	void deveRetornarHelloWorld() {
		Ac2CaApplication app = new Ac2CaApplication();
		String resultado = app.home();
		assertThat(resultado).isEqualTo("Hello World");
	}

	@Test
	@DisplayName("Deve executar o método main sem erros")
	void deveExecutarMainSemErros() {
		// Garante que o método main roda sem lançar exceções
		Ac2CaApplication.main(new String[] {});
		assertThat(true).isTrue(); // se chegou até aqui, está tudo certo
	}
}
