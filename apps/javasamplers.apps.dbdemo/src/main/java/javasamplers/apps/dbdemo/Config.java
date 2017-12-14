package javasamplers.apps.dbdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.URI;

/**
 * Created by ocean on 12/13/2017.
 */
@Configuration
@PropertySource("classpath:app.properties")
public class Config {


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        // N.B Needed to power the @PropertySource annotation.
        return new PropertySourcesPlaceholderConfigurer();
    }


    @Bean
    public DataSource getDataSource() throws IOException {
        // N.B create our in memory embedded database
        // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(Config.class.getClassLoader());

        Resource[] resources = resolver.getResources("db/sql/*.sql");

        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        builder.setType(EmbeddedDatabaseType.HSQL); //.H2 or .DERBY

        for(Resource resource : resources) {
            // Crude way for now.
            builder.addScript("db/sql/" + resource.getFilename());
        }

        return builder.build();
    }

    @Autowired
    DataSource dataSource;

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

}
