package com.alex.javacase.entitys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
public class DjSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private Date startDate;
    private int duration;

    protected DjSet() {}

    public DjSet(String name, Date startDate, int duration){

        this.name = name;
        this.startDate = startDate;
        this.duration = duration;
    }

    @Override
    public String toString(){
        return String.format(
                "Dj[id=%d, name='%s', startdate='%d', duration = '%s']",
                id,name,startDate,duration);
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
