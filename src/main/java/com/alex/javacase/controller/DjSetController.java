package com.alex.javacase.controller;

import com.alex.javacase.entitys.Dj;
import com.alex.javacase.entitys.DjSet;
import com.alex.javacase.repository.DjRepository;
import com.alex.javacase.repository.DjSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DjSetController {

    @Autowired
    DjSetRepository djSetRepository;

    @GetMapping("/djset")
    public ArrayList<DjSet> getAllDjSet(){

        return (ArrayList<DjSet>) djSetRepository.findAll();
    }
    @PostMapping("/djset")
    public ArrayList<DjSet> createDj(@RequestBody DjSet djSet){
        DjSet newdjSet = new DjSet(djSet.getName(),djSet.getStartDate(),djSet.getDuration());
        djSetRepository.save(newdjSet);

        return (ArrayList<DjSet>) djSetRepository.findAll();
    }

    @DeleteMapping("/djset")
    public ArrayList<DjSet> deleteDjSet(@RequestParam int id){
        Optional<DjSet> optionalDjSetToDelete = djSetRepository.findById(id);

        if(optionalDjSetToDelete.isPresent() == true){
            DjSet djSetToDelete = optionalDjSetToDelete.get();
            djSetRepository.delete(djSetToDelete);
        }else{
            System.out.println("Couldnt find DjSet to delete");
        }
        return (ArrayList<DjSet>) djSetRepository.findAll();
    }

    @PutMapping("/djset")
    public DjSet editDjSet(@RequestBody DjSet receivedDjSet){
        Optional<DjSet> optionalDjToEditSet = djSetRepository.findById(receivedDjSet.getId());

        if(optionalDjToEditSet.isPresent() == true){
            DjSet djSetToEdit = optionalDjToEditSet.get();

            djSetToEdit.setName(receivedDjSet.getName());
            djSetToEdit.setDuration(receivedDjSet.getDuration());
            djSetToEdit.setStartDate(receivedDjSet.getStartDate());

            djSetRepository.save(djSetToEdit);
        }else {
            System.out.println("coulnt find Djset to edit");
        }
        return receivedDjSet;
    }
}
