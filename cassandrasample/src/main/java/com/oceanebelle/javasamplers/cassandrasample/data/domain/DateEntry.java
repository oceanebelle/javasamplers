package com.oceanebelle.javasamplers.cassandrasample.data.domain;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.UserDefinedType;

import java.time.LocalDateTime;

@UserDefinedType(value = "t_date", forceQuote = true)
public class DateEntry {
    @Column
    private int year;

    @Column
    private int month;

    @Column
    private int day;

    @Column
    private int hour;

    @Column
    private int minute;

    @Column
    private LocalDateTime time;

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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
