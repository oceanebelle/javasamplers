package com.oceanebelle.javasamplers.entitiessample.config;

import com.oceanebelle.javasamplers.entitiessample.dataaccess.NotebookDataService;
import com.oceanebelle.javasamplers.entitiessample.repositories.NotebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.oceanebelle.javasamplers.entitiessample.entity")
@EnableJpaRepositories("com.oceanebelle.javasamplers.entitiessample.repositories")
public class EntityConfiguration {

    @Autowired
    NotebookRepository repository;

    @Bean
    public NotebookDataService notebookDataService() {
        return new NotebookDataService(repository);
    }
}
