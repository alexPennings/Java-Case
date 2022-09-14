package com.alex.javacase.repository;

import com.alex.javacase.entitys.Dj;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface DjRepository extends JpaRepository<Dj, Integer> {
    ArrayList<Dj> findAllByName(String name);
}
