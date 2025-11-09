package ac2_project.example.ac2_ca.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API - AC2 Controle Acadêmico")
                        .description("Documentação interativa dos endpoints da aplicação de controle acadêmico")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Juliane Zaetum")
                                .email("jzaetum@gmail.com")
                                .url("https://github.com/juzaetum/ac2_devOps_alunosErecompensas"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
