package com.oceanebelle.javasamplers.cassandrasample.data;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.oceanebelle.javasamplers.cassandrasample.data.domain.BulletNotes;
import com.oceanebelle.javasamplers.cassandrasample.data.domain.DateEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cassandra.core.CqlTemplate;
import org.springframework.data.cassandra.core.CassandraTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class BulletNotesDataService {
    private static final Logger LOG = LoggerFactory.getLogger(BulletNotesDataService.class);
    private final CassandraTemplate cassandraTemplate;
    private final BulletNotesRepository repo;

    public BulletNotesDataService(CassandraTemplate cassandraTemplate, BulletNotesRepository repo) {
        this.cassandraTemplate = cassandraTemplate;
        this.repo = repo;
    }


    public void executeQuery(String cql) throws ExecutionException, InterruptedException {
        CqlTemplate cqlTpl = cassandraTemplate;

        ResultSetFuture result = cqlTpl.executeAsynchronously(cql);

        ResultSet rs = result.get();

        for (Row r : rs.all()) {
            LOG.info("found {} ({})", r.getColumnDefinitions().toString(), r.getColumnDefinitions().size());
        }

    }

    protected CqlTemplate useCql() {
        return cassandraTemplate;
    }

    public void executeCommand(String cql) {
        LOG.info("Executing [{}]", cql);
        useCql().execute(cql);
    }

    public List<BulletNotes> getBulletNotes(String name) {
        return repo.findAllByNotebookEquals(name);
    }

    public void saveBulletNotes(BulletNotes n) {
        DateEntry date = new DateEntry();
        LocalDateTime created = LocalDateTime.now();
        date.setDay(created.getDayOfMonth());
        date.setYear(created.getYear());
        date.setMonth(created.getMonthValue());
        date.setHour(created.getHour());
        date.setMinute(created.getMinute());
        date.setTime(created);
        n.setEntry(date);
        n.setStatus("NEW");
        repo.save(n);
    }
}
