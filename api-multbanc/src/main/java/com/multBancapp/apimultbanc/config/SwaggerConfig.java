package com.multBancapp.apimultbanc.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

      @Value("${multbanc.openapi.dev-url}")
      private String devUrl;

      @Value("${multbanc.openapi.prod-url}")
      private String prodUrl;

      @Bean
      public OpenAPI myOpenAPI() {
            Server devServer = new Server();
            devServer.setUrl(devUrl);
            devServer.setDescription("Server URL in Development environment");

            Server prodServer = new Server();
            prodServer.setUrl(prodUrl);
            prodServer.setDescription("Server URL in Production environment");

            Contact contact = new Contact();
            contact.setEmail("caioeduardosantos7@gmail.com");
            contact.setName("caioed77");
            contact.setUrl("https://github.com/caioed77");

            Info info = new Info()
                    .title("API MultBanc")
                    .version("1.0")
                    .contact(contact)
                    .description("api a fins de estudos").termsOfService("caioeduardosantos7@gmail.com");

            return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
      }
}
