package com.oceanebelle.javasamplers.cassandrasample.app;

import com.oceanebelle.javasamplers.cassandrasample.data.BulletNotesDataService;
import com.oceanebelle.javasamplers.cassandrasample.data.BulletNotesRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(basePackages = {"com.oceanebelle.javasamplers.cassandrasample.data", "com.oceanebelle.javasamplers.cassandrasample.data.domain"})
public class AppConfig {


    @Bean
    public BulletNotesDataService bulletNotesDataService(CassandraTemplate cassandraTemplate, BulletNotesRepository bulletNotesRepository)  {
        /**
         * Using spring-data-cassandra where there exists cassandraTemplate, session(sessionFactoryBean),
         * cassandraConverter, customConversions, cassandraMapping, cluster,
         */

        BulletNotesDataService service = new BulletNotesDataService(cassandraTemplate, bulletNotesRepository);
        return service;
    }

}
