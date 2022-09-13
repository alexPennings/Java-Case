package com.alex.javacase.entitys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.security.PublicKey;

@Entity
public class Dj {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

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
}
