package com.alex.javacase.service;

import com.alex.javacase.repository.DjRepository;
import com.alex.javacase.repository.DjSetRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DjSetService {

    private DjSetRepository djSetRep;
    private DjRepository djRepository;

    @Autowired
    public DjSetService(DjSetRepository djSetRepository,DjRepository djRepository){

        this.djSetRep = djSetRepository;
        this.djRepository = djRepository;
    }
    public void saveSetDjRelation(int id){

        djRepository.findById(id);


    }


}
