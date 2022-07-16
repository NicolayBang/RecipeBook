package com.example.RecipeBook;

//import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.core.annotation.AnnotationFilter.packages;

@Configuration
public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig() {
        packages("com.example.RecipeBook");
        register(GSONHandler.class);
    }

    @Bean
    public GsonBuilderCustomizer typeAdapterRegistration() {
        return builder -> {
            builder.registerTypeAdapter(Recipe.class, new JSONConverter());
        };
    }

}
