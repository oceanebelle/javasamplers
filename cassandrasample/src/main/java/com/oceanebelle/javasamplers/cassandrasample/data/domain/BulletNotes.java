package com.oceanebelle.javasamplers.cassandrasample.data.domain;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.util.List;
import java.util.Set;

@Table(value="bullet_notes", forceQuote = true)
public class BulletNotes {

    @PrimaryKeyColumn(name = "id",ordinal = 0,type = PrimaryKeyType.PARTITIONED)
    private String id;

    @PrimaryKeyColumn(name = "notebook",ordinal = 1,type = PrimaryKeyType.PARTITIONED)
    private String notebook;

    @PrimaryKeyColumn(name = "entry",ordinal = 2,type = PrimaryKeyType.PARTITIONED)
    private DateEntry entry;

    @Column
    private String status;

    @Column
    private Set<String> tags;

    @Column
    private String task;

    @Column
    private List<TaskUpdates> updates;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotebook() {
        return notebook;
    }

    public void setNotebook(String notebook) {
        this.notebook = notebook;
    }

    public DateEntry getEntry() {
        return entry;
    }

    public void setEntry(DateEntry entry) {
        this.entry = entry;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public List<TaskUpdates> getUpdates() {
        return updates;
    }

    public void setUpdates(List<TaskUpdates> updates) {
        this.updates = updates;
    }
}
