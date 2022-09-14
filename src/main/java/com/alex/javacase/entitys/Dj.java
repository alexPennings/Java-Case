package com.alex.javacase.entitys;

import javax.persistence.*;
import java.security.PublicKey;
import java.util.Set;

@Entity
public class Dj {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;


    @OneToMany(mappedBy = "dj")
    private Set<DjSet> djSets;

    protected Dj () {}

    public Dj (String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return String.format(
                "Dj[id=%d, name='%s']",
                id,name);
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addDjSet(DjSet djSet){
        djSets.add(djSet);
        djSet.setDj(this);
    }
    public void removeDjSet(DjSet djSet){
        djSets.remove(djSet);
        djSet.setDj(null);
    }
}
