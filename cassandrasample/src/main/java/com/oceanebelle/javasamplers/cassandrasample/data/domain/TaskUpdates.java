package com.oceanebelle.javasamplers.cassandrasample.data.domain;

import org.springframework.data.cassandra.mapping.UserDefinedType;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;

@UserDefinedType(value = "t_update", forceQuote = true)
public class TaskUpdates {
    private LocalDateTime updated;
    private String blobtype;
    private ByteBuffer detail;

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public String getBlobtype() {
        return blobtype;
    }

    public void setBlobtype(String blobtype) {
        this.blobtype = blobtype;
    }

    public ByteBuffer getDetail() {
        return detail;
    }

    public void setDetail(ByteBuffer detail) {
        this.detail = detail;
    }
}
