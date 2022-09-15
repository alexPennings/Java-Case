package com.alex.javacase.entitys;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
public class DjSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank
    private String name;
    @NotBlank
    private Date startDate;
    @NotBlank
    private int duration;

    @ManyToOne
    @JoinColumn(name = "dj_id",nullable = true)
    private Dj dj;



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

    public void setDj(Dj dj) {
        this.dj = dj;
    }

    public Dj getDj() {
        return dj;
    }
}
