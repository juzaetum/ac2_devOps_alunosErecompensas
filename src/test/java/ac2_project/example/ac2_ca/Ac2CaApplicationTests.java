package ac2_project.example.ac2_ca;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class Ac2CaApplicationTests {

	@Test
	void contextLoads() {
		// Skip test to avoid context load on Jenkins
	}
}
