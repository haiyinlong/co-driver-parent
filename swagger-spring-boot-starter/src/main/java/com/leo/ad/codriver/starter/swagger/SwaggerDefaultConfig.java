package com.leo.ad.codriver.starter.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * SwaggerConfig
 *
 * @author HaiYinLong
 * @version 2024/06/03 11:45
 **/
@Configuration
public class SwaggerDefaultConfig {
    @Value("${spring.application.name:appName}")
    private String appName;

    @Value("${co-driver.swagger.authorized.field:token}")
    private String authorizedField;
    @Bean
    @ConditionalOnMissingBean(name = "swaggerOpenApi")
    public OpenAPI swaggerOpenApi() {

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("token",
                        new SecurityScheme().type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.HEADER).name(getAuthorizedField())))
                .addSecurityItem(new SecurityRequirement().addList("token"))
                .info(new Info().title(appName)
                        .contact(new Contact())
                        .description(appName +"API文档"));
    }

    private String getAuthorizedField() {
        if(ObjectUtils.isEmpty(authorizedField)){
            return "token";
        }
        return authorizedField;
    }
}
