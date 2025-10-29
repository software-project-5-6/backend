package com.Majstro.psmsbackend.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {


        String cognitoDomain = "https://eu-north-1feghcdpyb.auth.eu-north-1.amazoncognito.com";


        SecurityScheme oauthScheme = new SecurityScheme()
                .type(SecurityScheme.Type.OAUTH2)
                .description("AWS Cognito OAuth2 flow for Swagger")
                .flows(new OAuthFlows()
                        .authorizationCode(new OAuthFlow()
                                .authorizationUrl(cognitoDomain + "/oauth2/authorize")
                                .tokenUrl(cognitoDomain + "/oauth2/token")
                                .scopes(new Scopes()
                                        .addString("openid", "OpenID scope")
                                        .addString("profile", "Access user profile")
                                        .addString("email", "Access user email")
                                        .addString("api.read", "Read access to API")
                                        .addString("api.write", "Write access to API")
                                )
                        )
                );

        return new OpenAPI()
                .info(new Info()
                        .title("My Spring Boot API")
                        .description("Swagger UI integrated with AWS Cognito")
                        .version("1.0.0"))
                .addSecurityItem(new SecurityRequirement().addList("oauth2"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("oauth2", oauthScheme));
    }
}
