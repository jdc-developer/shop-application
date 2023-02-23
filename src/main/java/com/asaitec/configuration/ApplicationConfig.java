package com.asaitec.configuration;

import com.asaitec.service.DatabaseService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    private final DatabaseService databaseService;
    private ModelMapper modelMapper;

    public ApplicationConfig(DatabaseService databaseService) {
        this.databaseService = databaseService;
        modelMapper = new ModelMapper();

        databaseService.populateDatabase();
    }

    @Bean
    public ModelMapper getModelMapper() {
        return this.modelMapper;
    }
}
