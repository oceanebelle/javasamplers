package com.oceanebelle.javasamplers.cassandrasample.data.domain;

import com.datastax.driver.core.DataType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.UserDefinedType;

import java.time.LocalDate;
import java.util.List;

@UserDefinedType("bulletdayentry")
public class BulletDayEntry {

    @Column
    private LocalDate day;


    @Column
    @CassandraType(type = DataType.Name.UDT, userTypeName = "bullettask")
    private List<BulletTask> tasks;

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public List<BulletTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<BulletTask> tasks) {
        this.tasks = tasks;
    }
}
