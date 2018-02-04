package com.oceanebelle.javasamplers.cassandrasample.app;

import com.google.common.collect.Sets;
import com.oceanebelle.javasamplers.cassandrasample.data.BulletNotesDataService;
import com.oceanebelle.javasamplers.cassandrasample.data.BulletNotesRepository;
import com.oceanebelle.javasamplers.cassandrasample.data.domain.BulletNotes;
import com.oceanebelle.javasamplers.cassandrasample.data.domain.DateEntry;
import com.oceanebelle.javasamplers.cassandrasample.data.domain.TaskUpdates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.convert.CassandraConverter;
import org.springframework.data.cassandra.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.mapping.SimpleUserTypeResolver;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.Arrays;

@Configuration
@EnableCassandraRepositories(basePackages = {"com.oceanebelle.javasamplers.cassandrasample.data", "com.oceanebelle.javasamplers.cassandrasample.data.domain"})
public class AppConfig extends AbstractCassandraConfiguration {



    @Bean
    public CassandraMappingContext mappingContext() throws ClassNotFoundException {
        BasicCassandraMappingContext ctx = new BasicCassandraMappingContext();
        ctx.setInitialEntitySet(Sets.newLinkedHashSet(Arrays.asList(
                DateEntry.class,
                TaskUpdates.class,
                BulletNotes.class
        )));

        SimpleUserTypeResolver resolver = new SimpleUserTypeResolver(cluster().getObject(), getKeyspaceName());

        ctx.setUserTypeResolver(resolver);
        return ctx;
    }


    @Bean
    public CassandraConverter converter() throws ClassNotFoundException {
        return new MappingCassandraConverter(mappingContext());
    }


    @Bean
    public BulletNotesDataService bulletNotesDataService(CassandraTemplate cassandraTemplate, BulletNotesRepository bulletNotesRepository)  {
        /**
         * Using spring-data-cassandra where there exists cassandraTemplate, session(sessionFactoryBean),
         * cassandraConverter, customConversions, cassandraMapping, cluster,
         */

        BulletNotesDataService service = new BulletNotesDataService(cassandraTemplate, bulletNotesRepository);
        return service;
    }

    @Bean
    public CassandraSessionFactoryBean session() throws ClassNotFoundException {

        CassandraSessionFactoryBean session = new CassandraSessionFactoryBean();
        session.setCluster(cluster().getObject());
        session.setKeyspaceName("dev");
        session.setConverter(converter());
        session.setSchemaAction(SchemaAction.NONE);

        return session;
    }


    @Override
    protected String getKeyspaceName() {
        return "dev";
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.RECREATE;
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[] {
                "com.oceanebelle.javasamplers.cassandrasample.app.domain"
        };
    }
}
