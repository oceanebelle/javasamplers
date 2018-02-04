package com.oceanebelle.javasamplers.cassandrasample.app;

import com.oceanebelle.javasamplers.cassandrasample.data.BulletNotesDataService;
import com.oceanebelle.javasamplers.cassandrasample.data.domain.BulletNotes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class CassandraSampleApp        implements ApplicationRunner
{
    private static final Logger LOG = LoggerFactory.getLogger(CassandraSampleApp.class);


    @Autowired
    BulletNotesDataService service;



    public static void main(String... args) {
        SpringApplication.run(CassandraSampleApp.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOG.info("starting the app...");


        BulletNotes n = new BulletNotes();
        n.setId(UUID.randomUUID().toString());
        n.setNotebook("mynotes");
        n.setTask("This is a random task with missing date " + LocalDateTime.now().toString());

        // create a note
        service.saveBulletNotes(n);


        List<BulletNotes> notes = service.getBulletNotes("mynotes");

        LOG.info("Found {}", notes.size());


//        service.executeCommand(CqlSupport.builder(CqlSupport.Type.table, "dev")
//                .name("bullet_notes")
//                .drop());
//        service.executeCommand(CqlSupport.builder(CqlSupport.Type.type, "dev")
//                .name("t_date")
//                .drop());
//        service.executeCommand(CqlSupport.builder(CqlSupport.Type.type, "dev")
//                .name("t_update")
//                .drop());
//
//
//        service.executeCommand(CqlSupport.builder(CqlSupport.Type.type, "dev")
//                .name("t_date")
//                .addField("year int")
//                .addField("month int")
//                .addField("day int")
//                .addField("hour int")
//                .addField("minute int")
//                .addField("time timestamp")
//                .create());
//
//        service.executeCommand(CqlSupport.builder(CqlSupport.Type.type, "dev")
//                .name("t_update")
//                .addField("updated timestamp")
//                .addField("blobtype text")
//                .addField("detail blob")
//                .create());
//
//        service.executeCommand(CqlSupport.builder(CqlSupport.Type.table, "dev")
//                .name("bullet_notes")
//                .addField("id text")
//                .addField("notebook text") // name of the notebook
//                .addField("entry frozen<t_date>")
//                .addField("task text")
//                .addField("tags set<text>") // tags to this data
//                .addField("status text")   // modifiable status
//                .addField("updates frozen<list<t_update>>")
//                .addField("primary key (id, notebook, entry)")
//                .create());


    }

}
