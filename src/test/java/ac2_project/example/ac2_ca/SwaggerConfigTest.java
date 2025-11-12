package ac2_project.example.ac2_ca;

import ac2_project.example.ac2_ca.config.SwaggerConfig;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SwaggerConfigTest {

    @Test
    @DisplayName("Deve criar bean OpenAPI com informações corretas da API")
    void testCustomOpenAPI() {
        // Arrange
        SwaggerConfig swaggerConfig = new SwaggerConfig();

        // Act
        OpenAPI openAPI = swaggerConfig.customOpenAPI();
        Info info = openAPI.getInfo();
        Contact contact = info.getContact();
        License license = info.getLicense();

        // Assert
        assertThat(openAPI).isNotNull();
        assertThat(info).isNotNull();
        assertThat(info.getTitle()).isEqualTo("API - AC2 Controle Acadêmico");
        assertThat(info.getVersion()).isEqualTo("1.0.0");
        assertThat(info.getDescription())
                .contains("Documentação interativa dos endpoints");

        assertThat(contact).isNotNull();
        assertThat(contact.getName()).isEqualTo("Juliane Zaetum");
        assertThat(contact.getEmail()).isEqualTo("jzaetum@gmail.com");
        assertThat(contact.getUrl()).contains("github.com/juzaetum");

        assertThat(license).isNotNull();
        assertThat(license.getName()).isEqualTo("Apache 2.0");
        assertThat(license.getUrl()).contains("apache.org/licenses");
    }
}
