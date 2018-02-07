package com.oceanebelle.javasamplers.entitiessample.app;

import com.oceanebelle.javasamplers.entitiessample.config.EntityConfiguration;
import com.oceanebelle.javasamplers.entitiessample.dataaccess.NotebookDataService;
import com.oceanebelle.javasamplers.entitiessample.entity.Notebook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.Objects;

@SpringBootApplication
@Import(EntityConfiguration.class)
public class EntitiesSampleApp implements ApplicationRunner {
    private static final Logger LOG = LoggerFactory.getLogger(EntitiesSampleApp.class);

    @Autowired
    NotebookDataService notebookService;


    public static void main(String ... args) {
        SpringApplication.run(EntitiesSampleApp.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {



        Notebook n1 = new Notebook();
        n1.setId(1l);
        n1.setName("test");
        n1.setCreatedAt(LocalDateTime.now());

        notebookService.save(n1);

        Notebook f = notebookService.find(1l);

        Objects.requireNonNull(f);

        LOG.info("Notebook has been saved successfully");

        Notebook n2 = new Notebook();
        n2.setId(2l);
        n2.setName("test note 2");
        n2.setCreatedAt(LocalDateTime.now());

        try {
            // demonstrate that the tx was rolled back
            notebookService.saveThenThrowError(n2);
        } catch (Exception e) {
            LOG.error("Error raised: {}", e.getMessage());

            f = notebookService.find(2l);

            LOG.info("N2 is {}", f);
        }

    }

//    private void printProperties() {
//        LOG.info("PRINTING PROPERTIES {}", jpaProperties.getDatabasePlatform());
//        for(Map.Entry<String, String> each : jpaProperties.getProperties().entrySet()) {
//            LOG.info(String.format("%30s: %s", each.getKey(), each.getValue()));
//        }
//    }
}
