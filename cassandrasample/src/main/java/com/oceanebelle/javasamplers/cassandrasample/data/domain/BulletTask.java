package com.oceanebelle.javasamplers.cassandrasample.data.domain;


import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.UserDefinedType;

import java.util.List;

@UserDefinedType("bullettask")
public class BulletTask {

    @Column
    private List<String> tags;

    @Column
    private String task;

    // N.B. Might need a bytebuffer for a more free for all
    @Column
    private List<String> updates;

    @Column
    private String status;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public List<String> getUpdates() {
        return updates;
    }

    public void setUpdates(List<String> updates) {
        this.updates = updates;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
