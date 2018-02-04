package com.oceanebelle.javasamplers.cassandrasample.data;

import com.oceanebelle.javasamplers.cassandrasample.data.domain.BulletNotes;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BulletNotesRepository extends CrudRepository<BulletNotes, String> {

    // TODO: Allow filtering is not safe! find other ways
    @Query("SELECT * FROM bullet_notes WHERE notebook = ?0 ALLOW FILTERING")
    List<BulletNotes> findAllByNotebookEquals(String notebook);
}
