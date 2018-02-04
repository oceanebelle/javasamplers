package com.oceanebelle.javasamplers.cassandrasample.data.domain;

import com.datastax.driver.core.DataType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.UserDefinedType;

import java.util.Set;

@UserDefinedType("bullet_month")
public class BulletMonth {

    @Column
    private int year;

    @Column
    private int month;

    @Column
    @CassandraType(type = DataType.Name.UDT, userTypeName = "bulletdayentry")
    private Set<BulletDayEntry> dayEntry;


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Set<BulletDayEntry> getDayEntry() {
        return dayEntry;
    }

    public void setDayEntry(Set<BulletDayEntry> dayEntry) {
        this.dayEntry = dayEntry;
    }
}
