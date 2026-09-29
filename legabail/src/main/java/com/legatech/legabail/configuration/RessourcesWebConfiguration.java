package com.legatech.legabail.configuration;

import java.nio.file.Path;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RessourcesWebConfiguration implements WebMvcConfigurer {

    private final Path repertoireImages;

    public RessourcesWebConfiguration(
            @Value("${legabail.images.directory:uploads/biens}") String repertoireImages) {
        this.repertoireImages = Path.of(repertoireImages).toAbsolutePath().normalize();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/biens/**")
                .addResourceLocations(repertoireImages.toUri().toString());
    }
}
